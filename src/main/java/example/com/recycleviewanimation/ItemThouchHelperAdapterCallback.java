package example.com.recycleviewanimation;

/**
 * Created by wanghao on 16/5/11.
 */
public interface ItemThouchHelperAdapterCallback {
    /**
     * 拖拽会掉
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * 侧滑删除
     *
     * @param adapterPosition
     */
    void onItemSwiped(int adapterPosition);
}
