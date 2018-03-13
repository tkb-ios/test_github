package com.tucker.test_create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean change_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ActivityMover = findViewById(R.id.moveButton);
        ActivityMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                startActivity(intent);
            }
        });
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

    public void hello(View view){
        TextView hellotext = findViewById(R.id.helloText);
        if(change_flag){
            change_flag = false;
            hellotext.setText("Good Night...");
        } else {
            change_flag = true;
            hellotext.setText("Hello World!!");
        }
    }
}
