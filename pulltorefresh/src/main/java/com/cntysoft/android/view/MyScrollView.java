package com.cntysoft.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/6/3.
 */
public class MyScrollView extends ScrollView {

    private float xDistance;
    private float xLast;
    private float yDistance;
    private float yLast;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDistance = 0.0f;
                yDistance = 0.0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = ev.getX();
                float y = ev.getY();
                xDistance += Math.abs(x - xLast);
                yDistance += Math.abs(y - yLast);
                xLast = x;
                yLast = y;
                if(xDistance > yDistance){
                    return false;
                }
                break;
            default:
                    break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
