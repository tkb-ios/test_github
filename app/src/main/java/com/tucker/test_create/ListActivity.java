package com.tucker.test_create;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Activity myActivity = this;

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            list.add("item" + i*2);
        }

        ListView listView = findViewById(R.id.ListView);
        /***下記はリストの表示方法の指定(コメントを付け替えて確認するとわかる。)
         * 上の方は標準的なListViewの実装
         * 下の方はカスタムレイアウトの実装方法***/
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, list);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /***ここではListViewにてタッチされた位置からデータを取得する。
                 * その後、ダイアログにて選ばれた項目を表示させる***/
                String str = (String) parent.getItemAtPosition(position);
                new AlertDialog.Builder(myActivity)
                        .setTitle("ダイアログの表示です")
                        .setMessage("ここが本文です。\n"+str)
                        .setPositiveButton("確認", null).show();
            }
        });
    }

    public void returnButton(View v) {
        finish();
    }
}
