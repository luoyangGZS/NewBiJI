package com.example.luoyangcomputer.newbiji;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BiJiNumberDAO {

    private DBHelper dbHelper;
    public BiJiNumberDAO(Context paramContext){
        dbHelper = new DBHelper(paramContext);
    }

    //添加数据
    public void add(BiJiNumber biJiNumber){
        //获取连接
        SQLiteDatabase database = this.dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        //添加数据
        contentValues.put("number",biJiNumber.getNumber());
        long id=database.insert("BiJi_number",null,contentValues);
        Log.i("TAG","添加数据 生成id= "+id);
        biJiNumber.setId((int) id);
        //关闭连接
        database.close();
    }

    //删除数据
    public void deleteById(int paramInt){
        SQLiteDatabase database = this.dbHelper.getReadableDatabase();
        database.delete("BiJi_number","_id=?",new String[]{paramInt+""});
        database.close();
    }

    //更新数据
    public void updata(BiJiNumber biJiNumber){
        SQLiteDatabase localSQLiteDatabase = this.dbHelper.getReadableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("number", biJiNumber.getNumber());
        int i = localSQLiteDatabase.update("BiJi_number", localContentValues, "_id=" + biJiNumber.getId(), null);
        Log.e("TAG", "update=" + i);
        localSQLiteDatabase.close();
    }

    //查询数据
    public List<BiJiNumber> queryAll(){
        SQLiteDatabase database = this.dbHelper.getReadableDatabase();
        Cursor cursor = database.query("BiJi_number", null, null, null, null, null, "_id desc");
        ArrayList arrayList = new ArrayList();
        for(;;){
            if(!cursor.moveToNext()){
                cursor.close();
                database.close();
                return arrayList;
            }
            arrayList.add(new BiJiNumber(cursor.getInt(0),cursor.getString(1)));
        }
    }


}
