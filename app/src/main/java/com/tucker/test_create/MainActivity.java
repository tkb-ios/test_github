package com.tucker.test_create;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 一番最初に表示されるActivityを管理するクラス。
 * */
public class MainActivity extends AppCompatActivity{

    /**HelloWorld部分の表示変更を管理するフラグ*/
    private static int flag = 0;

    /**最初に画面を構築するために呼ばれるメソッド*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setLogo(R.mipmap.icon_memory);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        myToolbar.setTitle("THIS IS TITLE");
        myToolbar.setSubtitle("this is subtitle");
        setSupportActionBar(myToolbar);

        //checkNetworking(this);

        /**フラグメントの処理を行う。フラグメントを管理するフラグメントマネージャを用意し、
         * トランザクション内に設置したいフラグメントを追加していく。<br/>
         * 下記の場合は、事前にレイアウトファイルにてフラグメントの入れ物(FrameLayout等)を用意する必要がある。***/
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        Fragment fragment = new PlusOneFragment();
//        Fragment fragment2 = new PlusOneFragment();
//
//        transaction.add(R.id.FragmentContainer, fragment);
//        transaction.add(R.id.FragmentContainer, fragment2);
//
//
//        // FragmentContainer のレイアウトの中身を、MyFragment に置き換える
//        //transaction.replace(R.id.FragmentContainer, fragment);
//
//        // Fragment を削除する
//        //transaction.remove(fragment);
//
//        // 変更を確定して FragmentTransaction を終える
//        transaction.commit();

    }

    /**<p>右上ActionBarにMenuリソースを反映する処理。詳しくはres/menu/main.xml参照<br/>
     * メソッド内のコメントを解除すれば理解が深まるかと</p>
     * @param menu ActionBarのMenu部分の入れ物*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        //inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //TODO 各項目の機能を補充する
    /**<p>ActionBarのMenuで選択された項目に応じて処理を分けるメソッド。</p>
     * @param item menuレイアウトのタッチされた位置のコンテンツ情報*/
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

    /**<p>「Change」ボタンが押されたときの挙動を記述するメソッド</p>
     * @param view 現在のActivity情報*/
    public void hello(View view) {
        TextView hellotext = findViewById(R.id.helloText);

        switch (flag++) {
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
    }


    /**ネットワークの接続状況を確認するためのメソッド
     * @param context Toastを表示するContext*/
    public void checkNetworking(Context context) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info == null){
            Toast.makeText(context, "Can't connect ...", Toast.LENGTH_LONG).show();

        } else {
            //isConnected()をする前にnullチェックをしないとNullPointerExceptionで落ちる。
            if (info.isConnected()) {
                Toast.makeText(context, info.getTypeName() + " connected", Toast.LENGTH_LONG).show();
            }
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //接続が Wifi の時の処理を記述
            }

        }

    }


    /**<p>「新規メモ追加」画面への遷移を行う処理,「新規メモ追加」ボタンのonClick()に割り当て</p>
     * @param view 現在のActivity情報*/
    public void changeActivity(View view) {
//        Intent intent = new Intent(getApplication(), SubActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(this, SubActivity.class);
        Bundle bundle = new Bundle();

        //intent自体にコレクションする方法
        //intent.putExtra("data", "value");

        //bundleにコレクションする方法。最後にintentにBundleを紐付けする。
        bundle.putString("isThis","Name");
        intent.putExtras(bundle);
        
        startActivity(intent);
    }

    /**<p>「一覧表示」画面への遷移を行う処理,「一覧表示」ボタンのonClick()に割り当て</p>
     * @param view 現在のActivity情報*/
    public void changeActivity2(View view) {
        Intent intent = new Intent(getApplication(), ListActivity.class);
        startActivity(intent);
    }
}
