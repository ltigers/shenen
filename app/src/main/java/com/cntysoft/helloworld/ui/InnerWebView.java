package com.cntysoft.helloworld.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/5/14.
 */
public class InnerWebView extends WebView {

    private boolean isScroll;
    private float downY;
    private  float moveY;
    private int touchSlop;
    public InnerWebView(Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public InnerWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public InnerWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return isScroll;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        /*switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                isScroll = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = event.getY(event.getPointerCount() - 1);
                if(isBottom() || isTop()) {
                    isScroll = false;
                }
                if(isBottom() && moveY - downY > 0){
                    isScroll = true;
                }
                if(isTop() && moveY - downY < 0){
                    isScroll = true;
                }

                getParent().requestDisallowInterceptTouchEvent(isScroll);
                break;
            case MotionEvent.ACTION_UP:
                isScroll = false;
                break;

        }*/

        if( ((ScrollView)getParent().getParent()).getScrollY() >= getTop()){
            Log.i("sss", ((ScrollView) getParent().getParent()).getScrollY() + "");
            getParent().requestDisallowInterceptTouchEvent(true);
        }else{
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(event);
    }

    private boolean isTop(){
        return getScrollY() == 0;
    }

    private boolean isBottom(){

        float contentHeight = getContentHeight() * getScale();
        float currentHeight = getHeight() + getScrollY();

        return contentHeight - currentHeight < 1;
    }
}
