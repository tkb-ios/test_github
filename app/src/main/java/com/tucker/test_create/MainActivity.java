package com.tucker.test_create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /***練習としてbooleanじゃなくてintでswitchするようにしてみました***/
    /***あと、当該class内でしか使わないのでprivateにしてみた、どうかなあ。あと、あまり意味なさそうだけどstaticにもしておいた***/
    //public boolean change_flag = true;
    private static int flag = 0;

    /***AppCompatActivity(大元はActivity)のonCreateメソッドのオーバーライド。よくわからない。***/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /***CHANGE!!ボタンが押されたときの挙動を記述するメソッド***/
    public void hello(View view){
        TextView hellotext = findViewById(R.id.helloText);

        switch (flag++){
            case 0:
                hellotext.setText("ONE!");
                break;
            case 1:
                hellotext.setText("TWO!");
                break;
            case 2:
                hellotext.setText("THREE!");
                break;
            case 3:
                hellotext.setText("GOAL!");
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
