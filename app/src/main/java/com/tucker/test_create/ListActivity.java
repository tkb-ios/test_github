package com.tucker.test_create;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity implements CustomItemTouchHelper.CustomItemTouchHelperListener{

    private ArrayList<Memos> memoList = new ArrayList<>();
    private CustomRecycleAdapter myAdapter;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Activity myActivity = this;
        constraintLayout = findViewById(R.id.listActivity);



        /**データベースから情報を受け取るための設定
         * 1. このActivityにおいてのデータベース取扱設定をまとめたSQLiteOpenHelperを用意する。
         *      x. 今回はSQLiteOpenHelperを継承したDBHelperクラスを使用している。
         *
         * 2. Helperを参照しデータベース起動
         * 3. query,delete,insert,汎用的なexecSQLなどを使ってデータベース処理をする**/
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("memos", null, null, null, null, null, null);
        boolean isEof = cursor.moveToFirst();
        while (isEof) {
            memoList.add(new Memos(cursor.getString(0), cursor.getString(1)));
            isEof = cursor.moveToNext();
        }
        /**End**/



        /**RecycleViewを設定(リストを表示するための管理クラス)
         * 1. RecycleViewのデザイン表示形式としてLinearLayoutManagerを指定
         * 2. RecycleViewにて行う操作をまとめたadapterを指定
         * 3. リストアイテムがタッチされた時の挙動を制御するItemTouchHelperを設定。
         *      x. ItemTouchHelperの設定はItemTouchHelper.SimpleCallBackにて管理される。
         *         今回はItemTouchHelper.SimpleCallBackを継承したCustomItemTouchHelperを使用している**/
        final RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager line = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(line);
        myAdapter = new CustomRecycleAdapter(this,memoList);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CustomItemTouchHelper(0, ItemTouchHelper.RIGHT, this));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CustomRecycleAdapter.ViewHolder) {

            String name = memoList.get(viewHolder.getAdapterPosition()).getTitle();

            //undo機能を実装するために、スワイプされたリストアイテムを取得
            final Memos deletedItem = memoList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            //スワイプされたリストアイテムの削除
            myAdapter.remove(viewHolder.getAdapterPosition());

            //削除が完了したことの通知(UNDOボタンを実装するにはToastでは困難)
            Snackbar snackbar = Snackbar.make(constraintLayout, name + " is removed", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myAdapter.restore(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
    /**End**/

    /** RecyclerViewを使って柔軟なデザインを実装しているため、
     *  こちらのListViewによる実装部分は省略 **/
//        ListView listView = findViewById(R.id.ListView);
//        /***下記はリストの表示方法の指定(コメントを付け替えて確認するとわかる。)
//         * 上の方は標準的なListViewの実装
//         * 下の方はカスタムレイアウトの実装方法***/
//        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        CustomListViewAdapter adapter = new CustomListViewAdapter(this, list);
//
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                /***ここではListViewにてタッチされた位置からデータを取得する。
//                 * その後、ダイアログにて選ばれた項目を表示させる***/
//                Memos memo = (Memos) parent.getItemAtPosition(position);
//                new AlertDialog.Builder(myActivity)
//                        .setTitle(memo.getTitle())
//                        .setMessage(memo.getMemo())
//                        .setPositiveButton("確認", null).show();
//            }
//        });
    /**End**/


    /**戻るボタンが押された時にこのアクティビティを終了し、親アクティビティに戻る。**/
    public void returnButton(View v) {
        finish();
    }
}
