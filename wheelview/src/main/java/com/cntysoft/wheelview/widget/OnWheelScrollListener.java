package com.cntysoft.wheelview.widget;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface OnWheelScrollListener {

    /**
     * Callback method to be invoked when scrolling started.
     *
     * @param wheelView
     *                 the wheel view whose state has changed.
     */
    void onScrollingStarted(WheelView wheelView);

    /**
     * Callback method to be invoked when scrolling ended.
     *
     * @param wheelView
     *                 the wheel view whose state has changed.
     */
    void onScrollingFinished(WheelView wheelView);
}
