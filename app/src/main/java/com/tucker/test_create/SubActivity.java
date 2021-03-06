package com.tucker.test_create;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 新しいメモを追加するためのActivityを管理するクラス
 */
public class SubActivity extends AppCompatActivity {

    /**「メモを追加」を押されたことを判定するために必要*/
    public Button commitButton;
    /**タイトル名の入力を管理する*/
    private EditText titleTxt;
    /**本文の入力を管理する*/
    private EditText mainTxt;

    /**最初に画面を構築するために呼ばれるメソッド*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        final Activity myActive = this;

        /** フラグメントの実装を行うためのコード **/
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        Fragment fragment = new PlusOneFragment();
//        transaction.add(R.id.FragmentContainer, fragment);
//        transaction.commit();
        /**End**/


        /** ボタンにonClick()の処理を追加する。 **/
        commitButton = findViewById(R.id.addButton);
        commitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                titleTxt = findViewById(R.id.titleText);
                mainTxt = findViewById(R.id.mainText);

                //メモ追加の事前確認をダイアログにて表示する。
                new AlertDialog.Builder(myActive)
                        .setTitle(titleTxt.getText().toString())
                        .setMessage(mainTxt.getText().toString())
                        .setPositiveButton("登録", new DialogInterface.OnClickListener() {

                            //ダイアログ内の登録が押されたらデータベース処理を行う。
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper helper = new DBHelper(myActive);
                                SQLiteDatabase db = helper.getReadableDatabase();
                                db.execSQL("insert into memos(title,maintext) values ('"
                                        + titleTxt.getText().toString() + "','"
                                        + mainTxt.getText().toString() + "');");
                                finish();
                            }
                        })
                        .setNegativeButton("キャンセル", null).show();

            }
        });
        /**End**/

    }

    /**戻るボタンが押された時にこのアクティビティを終了し、親アクティビティに戻る。**/
    public void returnButton(View v) {
        finish();
    }
}
