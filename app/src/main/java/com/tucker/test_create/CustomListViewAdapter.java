package com.tucker.test_create;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/***ArrayAdapterクラスを継承して、ListViewに独自レイアウトを指定できる***/
public class CustomListViewAdapter extends ArrayAdapter<String> {

    private LayoutInflater myLayoutInflater;

    public CustomListViewAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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
        String item = getItem(position);

        TextView text1 = view.findViewById(R.id.ListTitleText);
        text1.setText("題名：" + item);
        TextView text2 = view.findViewById(R.id.ListSubText);
        text2.setText("Sub Title:" + item);

        return view;
    }
}
