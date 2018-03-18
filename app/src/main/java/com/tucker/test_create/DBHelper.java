package com.tucker.test_create;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "TestDB", null, 1);
    }

    /***onUpgrade()はデータベースが更新された際に呼ばれる***/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /***onCreate()は一番最初だけ(データベースができた時のみ)呼ばれる***/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memos("
                + " title text not null,"
                + " maintext text" + ")"
        );
    }
}
