package example.com.recycleviewanimation;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by wanghao on 16/5/11.
 */
public class WhItemTouchCallback extends ItemTouchHelper.Callback {
    private final ItemThouchHelperAdapterCallback itemtouchcallback;

    public WhItemTouchCallback(ItemThouchHelperAdapterCallback itemtouchcallback) {//动画的返回接口
        this.itemtouchcallback = itemtouchcallback;
    }
    @Override
    public boolean isLongPressDragEnabled() {//可以进行长按拖动
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }//进行侧滑拖动

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        callback监听那些动作?ItemTouchHelperItemTouchHelper--判断方向的
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//上下拖动
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//左右拖动
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        监听滑动(垂直方向)
        itemtouchcallback.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//          滑动删除的动作的回调
        itemtouchcallback.onItemSwiped(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {//侧滑状态
//            属性动画搞起来  侧滑是让item有个变小的动画 在这里可以进行你所有想做的动画效果
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setScaleX((float) Math.max(0.7, alpha));
            viewHolder.itemView.setScaleY((float) Math.max(0.7, alpha));
            if (alpha <=0) {//让属性动画回复原状
                viewHolder.itemView.setAlpha(1);
                viewHolder.itemView.setScaleX(1);
                viewHolder.itemView.setScaleY(1);
            }
        }
        else {//让属性动画回复原状
            viewHolder.itemView.setAlpha(1);
            viewHolder.itemView.setScaleX(1);
            viewHolder.itemView.setScaleY(1);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }

    private static final String TAG = "WhItemTouchCallback";
}
