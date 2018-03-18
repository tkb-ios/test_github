package com.tucker.test_create;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;


/**
 *
 *
 *
 * getDefaultUIUtil()は ItemTouchUIUtilクラスにUI管理を任せるために必要なメソッド。
 * インタラクティブな処理を取り入れるために実装している。
 *
 *
 *
 **/

public class CustomItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private CustomItemTouchHelperListener listener;

    /** インタフェースとして仮実装。ListActivity.javaが継承している。
     *  onSwiped()の実際の実装はListActivity.javaにて行われている。 **/
    public interface CustomItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }


    /** コンストラクタ **/
    CustomItemTouchHelper(int dragDirs, int swipeDirs, CustomItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }


    /** ViewHolderに紐づけられたリストアイテムが スワイプ or ドラッグ された時のメソッド **/
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    /**コンテンツの装飾を描画するための処理。
     * 固定レイアウトではなくこのメソッド内で描画処理を行うため、コンテンツの動的な描画を行うことができる。
     * ここでは、ViewHolderが管理するコンテンツの前面のみを動かしたいので、親のUI管理先にForeGroundの描画情報のみを渡している。
     *
     * 1. onChildDraw()は固定レイアウトの描画前に、メソッドで指定した描画を行う(背面装飾)
     * 2. onChildDrawOver()は固定レイアウトの描画後に、メソッドで指定した描画を行う(前面装飾)**/
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }
    /**End**/


    /**コンテンツの描画(アニメーション)が終了した時に呼ばれるメソッド**/
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }


    /**リストアイテムがドラッグされた時の処理。
     * 今回はスワイプに処理を施すので return false;**/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }


    /**リストアイテムがスワイプされた時の処理。
     * 今回は実装をListActivity.javaに委譲している。**/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        //adapter.remove(swipedPosition);
    }


    /**方向指定を調整するための処理。**/
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }


}
