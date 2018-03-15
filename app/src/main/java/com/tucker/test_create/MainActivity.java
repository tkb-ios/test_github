package com.tucker.test_create;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /***練習としてbooleanじゃなくてintでswitchするようにしてみました
     ***あと、当該class内でしか使わないのでprivateにしてみた、どうかなあ。あと、あまり意味なさそうだけどstaticにもしておいた***/
    //public boolean change_flag = true;
    private static int flag = 0;

    /***AppCompatActivity(大元はActivity)のonCreateメソッドのオーバーライド。よくわからない。***/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***フラグメントの処理を行う。フラグメントを管理するフラグメントマネージャを用意し、
         * トランザクション内に設置したいフラグメントを追加していく。
         * 下記の場合は、事前にレイアウトファイルにてフラグメントの入れ物(FrameLayout等)を用意する必要がある。***/
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = new PlusOneFragment();
        Fragment fragment2 = new PlusOneFragment();

        transaction.add(R.id.FragmentContainer, fragment);
        transaction.add(R.id.FragmentContainer2, fragment2);


        // FragmentContainer のレイアウトの中身を、MyFragment に置き換える
//        transaction.replace(R.id.FragmentContainer, );

        // Fragment を削除する
        //transaction.remove(fragment);

        // 変更を確定して FragmentTransaction を終える
        transaction.commit();

    }

    /***右上ActionBarにMenuリソースを反映するために必要。詳しくはres/menu/main.xml参照。
     ***メソッド内のコメントを解除すれば理解が深まるかと***/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        //inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /***Menuで選択された項目に応じて処理を分ける部分。画面遷移処理を加工中***/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_memo:
                //add_memo(); メソッドを実行
                return true;
            case R.id.share_art:
                //share_art(); メソッドを実行
                return true;
            case R.id.share_memo:
                //share_memo(); メソッドを実行
                return true;
            case R.id.info:
                //displayInfo(); メソッドを実行
                return true;
            case R.id.upload:
                //upload_play(); メソッドを実行
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***CHANGE!!ボタンが押されたときの挙動を記述するメソッド***/
    public void hello(View view){
        TextView hellotext = findViewById(R.id.helloText);

        switch (flag++){
            case 0:
                hellotext.setText(R.string.one);
                break;
            case 1:
                hellotext.setText(R.string.two);
                break;
            case 2:
                hellotext.setText(R.string.three);
                break;
            case 3:
                hellotext.setText(R.string.goal);
                flag = 0;
                break;
        }

//        if(change_flag){
//            change_flag = false;
//            hellotext.setText("Good Night...");
//        } else {
//            change_flag = true;
//            hellotext.setText("Hello World!!");
//        }
    }

    public void changeActivity(View view) {
        Intent intent = new Intent(getApplication(), SubActivity.class);
        startActivity(intent);
    }
}
