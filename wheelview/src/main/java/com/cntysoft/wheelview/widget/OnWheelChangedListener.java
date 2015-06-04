package com.cntysoft.wheelview.widget;

/**
 * Created by Administrator on 2015/5/14.
 */

/**
 * Wheel changed listener interface.
 * <p>
 * The onChanged() method is called whenever current wheel positions is changed:
 * <li>New Wheel position is set
 * <li>Wheel view is scrolled
 */
public interface OnWheelChangedListener {

    /**
     * Callback method to be invoked when current item changed
     *
     * @param wheelView
     *            the wheel view whose state has changed
     * @param oldValue
     *            the old value of current item
     * @param newValue
     *            the new value of current item
     */
    void onChanged(WheelView wheelView, int oldValue, int newValue);
}
