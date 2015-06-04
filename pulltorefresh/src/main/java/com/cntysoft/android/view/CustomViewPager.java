package com.cntysoft.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/6/2.
 */
public class CustomViewPager extends LazyViewPager {

    private boolean isScrollable;
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isScrollable(){
        return isScrollable;
    }

    public void setScrollable(boolean isScrollable){
        this.isScrollable = isScrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isScrollable == false){
            return false;
        }else{
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isScrollable == false){
            return false;
        }else{
            return super.onTouchEvent(ev);
        }
    }
}
