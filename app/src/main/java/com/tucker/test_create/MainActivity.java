package com.tucker.test_create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean change_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
