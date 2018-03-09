package com.tucker.test_create;

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
}
