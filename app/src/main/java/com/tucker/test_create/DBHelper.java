package com.tucker.test_create;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**データベース処理の基本設定を管理するクラス*/
public class DBHelper extends SQLiteOpenHelper {

    /**@param context データベース設定をするView*/
    DBHelper(Context context) {
        super(context, "TestDB", null, 1);
    }



    /**データベースが更新された際に呼ばれるメソッド
     * @param db 更新されたデータベース
     * @param oldVersion 更新前のバージョン
     * @param newVersion 更新後のバーション*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



    /**一番最初(データベースができた時)のみ呼ばれるメソッド,データベースの初期化に用いる
     * @param db データベースの指定*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memos("
                + " title text not null,"
                + " maintext text" + ")"
        );
    }
}
