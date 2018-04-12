package com.tucker.test_create;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

//絵を描くページの制御
public class drawer extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Button Cap=(Button)findViewById(R.id.capture);
        Cap.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        final String SAVE_DIR="/MyPhoto/";


        File file =  Environment.getExternalStorageDirectory();//ギャラリーへのパス
        try{
            if(!file.exists()){
                file.mkdir();
            }
        }catch(SecurityException e){
            e.printStackTrace();
            throw e;
        }
        switch (view.getId()){
            case R.id.capture:
                Toast.makeText(this, "画像を保存します", Toast.LENGTH_SHORT).show();
                saveCapture(findViewById(R.id.paintView),file);
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

    protected String getFileName(){//日時に紐づいた名前を付ける
        Calendar c = Calendar.getInstance();
        String s = c.get(Calendar.YEAR)
                + "_" + (c.get(Calendar.MONTH)+1)
                + "_" + c.get(Calendar.DAY_OF_MONTH)
                + "_" + c.get(Calendar.HOUR_OF_DAY)
                + "_" + c.get(Calendar.MINUTE)
                + "_" + c.get(Calendar.SECOND)
                + "_" + c.get(Calendar.MILLISECOND)
                + ".png";
        return s;
    }
    public void saveCapture(View view,File file){
        //書いた絵の保存を行う
        //Toast.makeText(this," saved",Toast.LENGTH_SHORT).show();
        Bitmap capture =getViewCapture(view);
        FileOutputStream fos=null;
        String fileName = getFileName();

        if(capture==null){
            Toast.makeText(this,"null",Toast.LENGTH_SHORT).show();
        }

        try {
//            File extStrageDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//pictureに保存
//            File dir=new File(extStrageDir.getAbsolutePath(),getFileName());
            Toast.makeText(this," 3",Toast.LENGTH_SHORT).show();

//            FileOutputStream outstream=new FileOutputStream(dir);//<-ここがおかしい
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            Toast.makeText(this," 4",Toast.LENGTH_SHORT).show();

            capture.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(this," 5",Toast.LENGTH_SHORT).show();
            fos.close();

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
