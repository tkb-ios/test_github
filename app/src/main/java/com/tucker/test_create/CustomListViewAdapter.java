package com.tucker.test_create;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/***ArrayAdapterクラスを継承して、ListViewに独自レイアウトを指定できる***/
public class CustomListViewAdapter extends ArrayAdapter {

    private LayoutInflater myLayoutInflater;

    /**コンストラクタ**/
    CustomListViewAdapter(Context context, List<Memos> objects) {
        super(context, 0, objects);
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /** リストの表示・管理にはGoogle推奨のViewHolderパターンを使用。
     *  ViewHolderパターンを使うことによって、メモリリークや内部処理を少なく抑えることができる
     *  getView()メソッドでは、画面に表示されるそれぞれのリストアイテムに値を結びつける役割を果たす。**/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (convertView == null) {
            //独自レイアウトのレイアウトファイルを読み込ませる
            view = myLayoutInflater.inflate(R.layout.list_view_layout, parent, false);
        } else {
            view = convertView;
        }

        // リストアイテムに対応するデータを取得する
        Memos item = (Memos)getItem(position);

        TextView text1 = view.findViewById(R.id.ListTitleText);
        TextView text2 = view.findViewById(R.id.ListSubText);
        text1.setText(item.getTitle());
        text2.setText(item.getMemo());

        return view;
    }
}
