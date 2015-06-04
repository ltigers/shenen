package com.cntysoft.circlescrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/5/9.
 */
public class CircleScrollView extends ScrollView implements View.OnClickListener {

    private static final String TAG = CircleScrollView.class.getSimpleName();

    /**
     * 点击的回调
     */
    public interface OnItemClickListener
    {
        void onItemClick(int pos, View view);
    }
    private OnItemClickListener mListener;
    private Adapter mAdapter;

    private int mScreenHeight;
    private int mItemHeight;
    private ViewGroup mContainer;

    private int mItemCount;
    private boolean flag;

    public static abstract class Adapter{
        public abstract  View getView(CircleScrollView parent,int pos);
        public abstract int getCount();
    }
    @Override
    public void onClick(View v) {

        int pos = (Integer) v.getTag();
        if (mListener != null)
        {
            mListener.onItemClick(pos, v);
        }
    }

    public CircleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScreenHeight -= getStatusHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!flag){
            mContainer = (ViewGroup) getChildAt(0);
            if(mAdapter != null){
                mItemCount = mAdapter.getCount();
                Log.i(TAG,"ItemCount: " + mItemCount);
                //mItemHeight = mScreenHeight / mItemCount;
                mItemHeight = mScreenHeight / 5;
                mContainer.removeAllViews();
                for (int i = 0; i < mAdapter.getCount(); i++)
                {
                    addChildView(i);
                }
            }
            addChildView(0);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在容器末尾添加一个Item
     * @param i
     */
    private void addChildView(int i)
    {
        View item = mAdapter.getView(this, i);
        //设置参数
        android.view.ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        //设置Tag
        item.setTag(i);
        //添加事件
        item.setOnClickListener(this);
        mContainer.addView(item);
    }

    /**
     * 在容器指定位置添加一个Item
     * @param i
     * @param index
     */
    private void addChildView(int i, int index)
    {
        View item = mAdapter.getView(this, i);
        android.view.ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        item.setTag(i);
        item.setOnClickListener(this);
        mContainer.addView(item, index);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        flag = true;
        int action = ev.getAction();
        int scrollY = getScrollY();
        switch (action)
        {
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "scrollY = " + scrollY);
                // 表示此时ScrollView的顶部已经达到屏幕顶部
                if (scrollY == 0)
                {
                    addChildToFirst();
                }
                // ScrollView的底部已经到达屏幕底部
                //int height = mItemHeight;
                int height = 8 * mItemHeight;
                if (Math.abs(scrollY - height) <= mItemCount)
                {
                    addChildToLast();
                }

                break;
            case MotionEvent.ACTION_UP:
                checkForReset();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
    /**
     * 在底部添加一个View，并移除第一个View
     */
    private void addChildToLast()
    {
        Log.e("TAG", "addChildToLast");
        int pos = (Integer) mContainer.getChildAt(1).getTag();
        addChildView(pos);
        mContainer.removeViewAt(0);
        //this.scrollTo(0, 0);
        scrollTo(0, 7 * mItemHeight);
    }

    /**
     * 在顶部添加一个View，并移除最后一个View
     */
    protected void addChildToFirst()
    {
        Log.e("TAG", "addChildToFirst");
        int pos = (Integer) mContainer.getChildAt(mItemCount - 1).getTag();
        addChildView(pos, 0);
        Log.i(TAG,"getChildCount(): "+mContainer.getChildCount());
        mContainer.removeViewAt(mContainer.getChildCount() - 1);
        this.scrollTo(0, mItemHeight);
    }

    /**
     * 检查当前getScrollY,显示完成Item，或者收缩此Item
     */
    private void checkForReset()
    {
        int val = getScrollY() % mItemHeight;
        /*if (val >= mItemHeight / 2)
        {
            smoothScrollTo(0, mItemHeight);
        } else
        {
            smoothScrollTo(0, 0);
        }*/
        if(val == 0){

        }else{
            if(val >= mItemHeight / 2){
                smoothScrollTo(0,getScrollY() - val + mItemHeight);
            }else{
                smoothScrollTo(0,getScrollY() - val);
            }
        }

    }
    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusHeight(Context context)
    {

        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e){
            e.printStackTrace();
        }
        return statusHeight;
    }

    public void setAdapter(Adapter mAdapter)
    {
        this.mAdapter = mAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener mListener)
    {
        this.mListener = mListener;
    }

}
