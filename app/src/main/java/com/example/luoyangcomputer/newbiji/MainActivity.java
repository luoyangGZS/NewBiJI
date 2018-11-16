package com.example.luoyangcomputer.newbiji;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PictureInPictureParams;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {
    private BiJiNumberAdapter adapter;
    private BiJiNumberDAO dao;
    private List<BiJiNumber> data;
    private ListView lv_main;
    private int position;

    class BiJiNumberAdapter extends BaseAdapter {
        BiJiNumberAdapter() {
        }

        public int getCount() {
            return MainActivity.this.data.size();
        }

        public Object getItem(int position) {
            return MainActivity.this.data.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(new StringBuilder(String.valueOf(position)).append(". ").append(((BiJiNumber) MainActivity.this.data.get(position)).getNumber()).toString());
            return convertView;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window =getWindow();
        window.requestFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_main);
        window.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.lybiji);


        this.lv_main = getListView();
        this.adapter = new BiJiNumberAdapter();
        this.dao = new BiJiNumberDAO(this);
        this.data = this.dao.queryAll();
        this.lv_main.setAdapter(this.adapter);
        this.lv_main.setOnCreateContextMenuListener(this);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");
        this.position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
    }

    public boolean onContextItemSelected(MenuItem item) {
        BiJiNumber bijiNumber = (BiJiNumber) this.data.get(this.position);
        switch (item.getItemId()) {
            case 1:
                showUpdataDialog(bijiNumber);
                break;
            case 2:
                this.dao.deleteById(bijiNumber.getId());
                this.data.remove(this.position);
                this.adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdataDialog(final BiJiNumber bijiNumber) {
        final EditText editText = new EditText(this);
        editText.setText(bijiNumber.getNumber());
        new AlertDialog.Builder(this)
                .setTitle("更新笔记")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                bijiNumber.setNumber(editText.getText().toString());
                MainActivity.this.dao.updata(bijiNumber);
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("取消", null).show();
    }

    public void addhmd(View v) {
        final EditText editText = new EditText(this);
        editText.setHint("笔记输入");
        new AlertDialog.Builder(this)
                .setTitle("添加笔记")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BiJiNumber bijiNumber = new BiJiNumber(-1, editText.getText().toString());
                MainActivity.this.dao.add(bijiNumber);
                MainActivity.this.data.add(0, bijiNumber);
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("取消", null).show();
    }
}