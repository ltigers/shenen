package com.cntysoft.wheelview.widget;

/**
 * Created by Administrator on 2015/5/14.
 */

/**
 * WheelView clicked listener interface.
 * <p>
 * The onItemClicked() method is called whenever a wheelview item is clicked
 * <li>New WheelView position is set
 * <li>WheelView is scrolled
 */
public interface OnWheelClickedListener {

    /**
     * Callback method to be invoked when current item clicked
     *
     * @param wheelView
     * @param itemIndex
     *                the index of clicked item
     */
    void onItemClicked(WheelView wheelView, int itemIndex);
}
