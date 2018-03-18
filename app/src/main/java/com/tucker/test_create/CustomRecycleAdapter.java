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


public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder> {

    private ArrayList<Memos> memoList;
    private Context context;


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public TextView mainTxt;
        public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(View v) {
            super(v);
            titleTxt = v.findViewById(R.id.ListTitleText);
            mainTxt = v.findViewById(R.id.ListSubText);
            viewBackground = v.findViewById(R.id.mask);
            viewForeground = v.findViewById(R.id.listContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(v.getContext())
                        .setTitle(titleTxt.getText())
                        .setMessage(mainTxt.getText())
                        .setPositiveButton("確認", null).show();
                }
            });
        }
    }

    /**コンストラクタ。Adapterで管理するリストと表示されるActivityの情報を受け取る。**/
    public CustomRecycleAdapter(Context _context, ArrayList<Memos> _memoList) {
        this.context = _context;
        this.memoList = _memoList;
    }



    /**ViewHolderが管理するレイアウトをリソースから結びつける。**/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_view_layout, viewGroup, false);

        return new ViewHolder(v);
    }


    /**それぞれのリストアイテムのレイアウトに値を結びつける。**/
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.titleTxt.setText(memoList.get(position).getTitle());
        viewHolder.mainTxt.setText(String.valueOf(memoList.get(position).getMemo()));

    }


    @Override
    public int getItemCount() {
        return memoList.size();
    }


    /**削除時の削除取り消しを処理する。**/
    public void restore(Memos memo, int position) {
        memoList.add(position, memo);
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("insert into memos(title,maintext) values('"
                + memo.getTitle() + "', '"
                + memo.getMemo() + "');");
        notifyItemInserted(position);
    }

    /**削除時の処理。スワイプ時に呼び出される。
     * 1. Adapterの管理している位置(getAdapterPosition)からデータを取得
     * 2. 削除したという情報を明示するためにToastにてメッセージを表示
     * 3. Listから項目を削除
     * 4. データベースからも削除
     * 5. 削除完了をnotifyItemRemoved()にて通知**/
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
