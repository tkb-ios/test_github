package com.tucker.test_create;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * フラグメントが実装できているか確認するためのクラス。
 */
public class PlusOneFragment extends Fragment {

    public PlusOneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        /***ボタンのidを参照しonClickメソッドを上書きする。
         * 同一のFragmentが同一のActivityに含まれていた場合は、idの解決がうまくいかなくなるため
         * 最初に処理されたメソッドのみ適応される***/
        Button button = getActivity().findViewById(R.id.fragmentButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TextView text = getActivity().findViewById(R.id.fragmentText);
                text.setText(text.getText() + "!");
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
