package com.example.luoyangcomputer.newbiji;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context paramContext){
        super(paramContext,"Luoy_newBiJi.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table BiJi_number (_id integer primary key autoincrement,number varchar)");
        sqLiteDatabase.execSQL("insert into BiJi_number (number) values ('20181019206-jun')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.i("TAG","onUpgrade");
    }
}
