package com.tucker.test_create;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//スクリーンショットをとるためのクラス
public class ScreenShots extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //キャプチャする
        Button capture = (Button)findViewById(R.id.capture);
        capture.setOnClickListener(this);
        Log.d("onClick","hoge");
    }

    @Override
    public void onClick(View v) {

        File file =new File(Environment.getExternalStorageDirectory()+"/capture.jpeg");

        file.getParentFile().mkdir();
        Log.d("onClick","hoge");
        switch(v.getId()){
            case R.id.capture://ボタンを押すとキャプチャする
                saveCapture(findViewById(android.R.id.content),file);
                break;

        }
    }

    public Bitmap getViewCapture(View view){
        view.setDrawingCacheEnabled(true);
        Bitmap cache=view.getDrawingCache();
        Bitmap screenshot=Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);
        return screenshot;
    }

    public void saveCapture(View view,File file){
        //viewを保存する
        Bitmap capture =getViewCapture(view);
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(file ,false);
            capture.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try{
                    fos.close();
                }catch (IOException ie){
                    fos=null;
                }
            }
        }
    }
}
