package com.cntysoft.slidedellistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;


/**
 * Created by Administrator on 2015/5/20.
 */
public class SlideDelItem extends LinearLayout {

    public static final int STATE_CLOSE = 0;
    public static final int STATE_OPEN = 1;
    private View mContentView;
    public View mMenuView;
    public int mDownx;
    public int state = STATE_CLOSE;
    public boolean isFling;
    private int mBaseX;
    private Scroller scroller;

    public SlideDelItem(View contentView, View menuView){
        super(contentView.getContext());
        scroller = new Scroller(getContext());
        mContentView = contentView;
        mMenuView = menuView;
        init();
    }

    private SlideDelItem(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    private SlideDelItem(Context context){
        super(context);
    }

    private void init(){
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        LayoutParams contentParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mContentView.setLayoutParams(contentParams);
        mMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        addView(mContentView);
        addView(mMenuView);
    }

    public void swipe(int dis){
        if(dis > mMenuView.getWidth()){
            dis = mMenuView.getWidth();
        }
        if(dis < 0){
            dis = 0;
        }
        mContentView.layout(-dis,mContentView.getTop(),
                mContentView.getWidth() - dis,getMeasuredHeight());
        mMenuView.layout(mContentView.getWidth() - dis,mMenuView.getTop(),
                mContentView.getWidth() + mMenuView.getWidth() - dis,
                mMenuView.getBottom());
       /* scrollTo(dis,0);
        postInvalidate();*/
    }

    @Override
    public void computeScroll() {
        if(state == STATE_OPEN){
            if(scroller.computeScrollOffset()){
                swipe(scroller.getCurrX());
                postInvalidate();
            }
        }else{
            if(scroller.computeScrollOffset()){
                swipe(mBaseX - scroller.getCurrX());
                postInvalidate();
            }
        }
    }

    public void smoothCloseMenu(){
        state = STATE_CLOSE;
        mBaseX = -mContentView.getLeft();
        scroller.startScroll(0,0,mBaseX,0,350);
        postInvalidate();
    }

    public void smoothOpenMenu(){
        state = STATE_OPEN;
        scroller.startScroll(-mContentView.getLeft(),0,mMenuView.getWidth()/2,0,350);
        postInvalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMenuView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
        mContentView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mContentView.layout(0, 0, getMeasuredWidth(),
                mContentView.getMeasuredHeight());
        mMenuView.layout(getMeasuredWidth(), 0,
                getMeasuredWidth() + mMenuView.getMeasuredWidth(),
                mContentView.getMeasuredHeight());

    }
}
