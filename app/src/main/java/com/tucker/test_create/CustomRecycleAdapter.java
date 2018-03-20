package com.tucker.test_create;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**コンテンツのレイアウトを管理するクラス,RecyclerView限定で使うことができる。*/
public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder> {

    /**リスト表示するコレクション*/
    private ArrayList<Memos> memoList;
    /**リスト表示するViewの情報*/
    private Context context;

    /**リストのアイテムごとのレイアウトを管理するクラス*/
    class ViewHolder extends RecyclerView.ViewHolder {
        /**メモの題名*/
        TextView titleTxt;
        /**メモの本文*/
        TextView mainTxt;
        /**コンテンツの背景部分のレイアウト*/
        RelativeLayout viewBackground;
        /**コンテンツの前面部分のレイアウト*/
        RelativeLayout viewForeground;

        /**各アイテムへの値の紐付け*/
        ViewHolder(View v) {
            super(v);
            titleTxt = v.findViewById(R.id.ListTitleText);
            mainTxt = v.findViewById(R.id.ListSubText);
            viewBackground = v.findViewById(R.id.mask);
            viewForeground = v.findViewById(R.id.listContent);

            //個々のコンテンツにタッチされた時の処理を記述
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ダイアログでコンテンツの中身を表示
                    new AlertDialog.Builder(v.getContext())
                        .setTitle(titleTxt.getText())
                        .setMessage(mainTxt.getText())
                        .setPositiveButton("確認", null).show();
                }
            });
        }
    }

    /**リスト表示されるActivityの情報と管理するListを受取り、初期化する。
     * @param _context 表示する画面情報
     * @param _memoList リスト表示するコレクション*/
    public CustomRecycleAdapter(Context _context, ArrayList<Memos> _memoList) {
        this.context = _context;
        this.memoList = _memoList;
    }



    /**ViewHolderが管理するレイアウトをリソースから結びつける
     * @param viewGroup ViewHolderの属するViewGroup
     * @param viewType 不明(おそらくLinearとかの選択)*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        /**ViewHolderが実際に表示するレイアウトを紐付けする*/
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_view_layout, viewGroup, false);

        return new ViewHolder(v);
    }



    /**それぞれのリストアイテムのレイアウトに値を結びつける。
     * @param viewHolder 値を紐付けるViewHolder
     * @param position インデックス*/
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.titleTxt.setText(memoList.get(position).getTitle());
        viewHolder.mainTxt.setText(String.valueOf(memoList.get(position).getMemo()));

    }



    /**リストアイテムの総数を返す
     * @return リストアイテム数*/
    @Override
    public int getItemCount() {
        return memoList.size();
    }



    /**削除時の削除取り消し(UNDO)を処理する。
     * @param memo リストアするデータ
     * @param position インデックス*/
    public void restore(Memos memo, int position) {
        memoList.add(position, memo);
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("insert into memos(title,maintext) values('"
                + memo.getTitle() + "', '"
                + memo.getMemo() + "');");
        notifyItemInserted(position);
    }



    /*削除時の処理。スワイプ時に呼び出される。
     * 1. Adapterの管理している位置(getAdapterPosition)からデータを取得
     * 2. 削除したという情報を明示するためにToastにてメッセージを表示
     * 3. Listから項目を削除
     * 4. データベースからも削除
     * 5. 削除完了をnotifyItemRemoved()にて通知*/
    /**削除時の処理
     * @param position インデックス*/
    public void remove(int position) {
        Memos memo = memoList.get(position);
//        Toast.makeText(context,title + "\nremoved",Toast.LENGTH_SHORT).show();
        memoList.remove(position);
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.delete("memos", "title = ? AND maintext = ?", new String[]{memo.getTitle(), memo.getMemo()});
        notifyItemRemoved(position);
    }
}
