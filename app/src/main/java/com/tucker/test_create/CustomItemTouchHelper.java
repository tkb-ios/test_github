package com.tucker.test_create;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;


/**
 * このクラス中に出てくるgetDefaultUIUtil()は ItemTouchUIUtilクラスにUI管理を任せるために必要なメソッド。
 * インタラクティブな処理を取り入れるために実装している。
 */

/**
 * コンテンツのタッチ操作に対する振る舞い管理するクラス,またどういう動作をされたかによって処理を仕分けする。
 */
public class CustomItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    /**インタフェースを仮実装,ListActivity.javaが継承している*/
    private CustomItemTouchHelperListener listener;
    /**インタフェースを仮実装,onSwiped()の実際の実装はListActivity.javaにて行われている。*/
    public interface CustomItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }



    /**操作の機能の詳細を設定する。
     * @param dragDirs ドラッグ可能な方向
     * @param swipeDirs スワイプ可能な方向
     * @param listener 操作情報を管理するListener*/
    CustomItemTouchHelper(int dragDirs, int swipeDirs, CustomItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }



    /** ViewHolderに紐づけられたリストアイテムが、スワイプorドラッグされた時に呼ばれるメソッド
     * @param viewHolder 選択されたコンテンツ
     * @param actionState 選択されたコンテンツの操作情報*/
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }



    /**コンテンツの背景装飾を描画するための処理であり、<bt>固定レイアウトの描画前</bt>にここで記述された描画を動的に行う
     * @param recyclerView コンテンツを描画するRecyclerViewインスタンス
     * @param viewHolder 描画するコンテンツ
     * @param actionState 描画するコンテンツの操作情報
     * @param dX 描画するコンテンツのX軸の移動量
     * @param dY 描画するコンテンツのY軸の移動量
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
        //描画するコンテンツにForeGroundのコンテンツのみを指定する。
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }



    /**コンテンツの前面装飾を描画するための処理であり、<bt>固定レイアウトの描画後</bt>にここで記述された描画を動的に行う
     * @param recyclerView コンテンツを描画するRecyclerViewインスタンス
     * @param viewHolder 描画するコンテンツ
     * @param actionState 描画するコンテンツの操作情報
     * @param dX 描画するコンテンツのX軸の移動量
     * @param dY 描画するコンテンツのY軸の移動量
     */
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }



    /**コンテンツの描画(アニメーション)が終了した時に呼ばれるメソッド
     * @param recyclerView メソッドを適用するRecyclerViewインスタンス
     * @param viewHolder メソッドが適用されるコンテンツ*/
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((CustomRecycleAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }



    /**リストアイテムがドラッグされた時の処理,今回はスワイプのみに処理を施すのでreturn false;*/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }



    /**リストアイテムがスワイプされた時の処理,今回は実装をListActivity.javaに委譲している。
     * @param viewHolder スワイプされたコンテンツ
     * @param direction スワイプされた方向**/
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
