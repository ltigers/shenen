package com.cntysoft.slidedellistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/5/20.
 */
public class SlideDelListView extends ListView {

    private Context mContext;
    private boolean moveable=false;
    private boolean closed=true;
    private float mDownX,mDownY;
    private int mTouchPosition,oldPosition=-1;
    private SlideDelItem mTouchView,oldView;

    public SlideDelListView(Context context) {
        super(context);
        this.mContext = context;
    }

    public SlideDelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public SlideDelListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mTouchPosition = pointToPosition((int)ev.getX(),(int)ev.getY());
                mTouchView = (SlideDelItem)getChildAt(mTouchPosition - getFirstVisiblePosition());
                mDownX = ev.getX();
                mDownY = ev.getY();
                if(oldPosition == mTouchPosition || closed){
                    moveable = true;
                    mTouchView.mDownx = (int)mDownX;
                }else{
                    moveable = false;
                    if(oldView != null){
                        oldView.smoothCloseMenu();
                    }
                }
                oldPosition = mTouchPosition;
                oldView = mTouchView;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mDownX-ev.getX()) < Math.abs(mDownY-ev.getY()) * dp2px(2)) {
                    break;
                }
                if(moveable){
                    int dis = (int) (mTouchView.mDownx - ev.getX());
                    if(mTouchView.state == mTouchView.STATE_OPEN){
                        dis += mTouchView.mMenuView.getWidth();
                    }
                    mTouchView.swipe(dis);
                    //ev.setAction(MotionEvent.ACTION_CANCEL);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(moveable){
                    if ((mTouchView.mDownx -ev.getX()) > (mTouchView.mMenuView.getWidth()/2)){
                        mTouchView.smoothOpenMenu();
                        closed = false;
                    }else{
                        mTouchView.smoothCloseMenu();
                        closed = true;
                    }
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                getContext().getResources().getDisplayMetrics());
    }
}
