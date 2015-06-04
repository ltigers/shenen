package com.cntysoft.android.view.pullrefreshview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2015/6/1.
 *
 * 这个类定义了Header和Footer的共通行为
 */
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout{

    /**容器布局*/
    protected View mContainer;
    /**当前的状态*/
    private State mCurState = State.NONE;
    /**前一个状态*/
    private State mPreState = State.NONE;

    public LoadingLayout(Context context) {
        this(context,null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context context
     * @param attrs attrs
     */
    protected void init(Context context,AttributeSet attrs){
        mContainer = createLoadingView(context,attrs);
        if (null == mContainer) {
            throw new NullPointerException("Loading view can not be null.");
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        addView(mContainer, params);
    }

    /**
     * 显示或隐藏这个布局
     *
     * @param show flag
     */
    public void show(boolean show){
        if(show == (View.VISIBLE == getVisibility())){
            return ;
        }
        ViewGroup.LayoutParams params = mContainer.getLayoutParams();
        if(params != null){
            if(show){
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else{
                params.height = 0;
            }
            setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 设置最后更新的时间文本
     *
     * @param label 文本
     */
    public void setLastUpdatedLabel(CharSequence label) {

    }

    /**
     * 设置加载中的图片
     *
     * @param imageDrawable 图片
     */
    protected abstract void onLoadingDrawableSet(Drawable imageDrawable);

    /**
     * 设置拉动的文本，典型的是“下拉可以刷新”
     *
     * @param pullLabel 拉动的文本
     */
    public void setPullLabel(CharSequence pullLabel) {

    }

    /**
     * 设置正在刷新的文本，典型的是“正在刷新”
     *
     * @param refreshingLabel 刷新文本
     */
    public void setRefreshingLabel(CharSequence refreshingLabel) {

    }

    /**
     * 设置释放的文本，典型的是“松开可以刷新”
     *
     * @param releaseLabel 释放文本
     */
    public void setReleaseLabel(CharSequence releaseLabel) {

    }
    @Override
    public void setState(State state) {
         if (mCurState != state){
             mPreState = mCurState;
             mCurState = state;
             onStateChanged(state,mPreState);
         }
    }

    @Override
    public State getState() {
        return mCurState;
    }

    @Override
    public void onPull(float scale) {

    }

    protected State getPreState(){
        return mPreState;
    }

    /**
     * 当状态改变时调用
     *
     * @param curState 当前状态
     * @param oldState 老的状态
     */
    protected void onStateChanged(State curState,State oldState){
        switch (curState){
            case RESET:
                onReset();
                break;
            case RELEASE_TO_REFRESH:
                onReleaseToRefresh();
                break;
            case PULL_TO_REFRESH:
                onPullToRefresh();
                break;
            case REFRESHING:
                onRefreshing();
                break;
            case NO_MORE_DATA:
                onNoMoreData();
                break;
            default:
                break;
        }
    }

    /**
     * 当状态设置为{@link State#RESET}时调用
     */
    protected abstract void onReset();

    /**
     * 当状态设置为{@link State#PULL_TO_REFRESH}时调用
     */
    protected abstract void onPullToRefresh();

    /**
     * 当状态设置为{@link State#RELEASE_TO_REFRESH}时调用
     */
    protected abstract void onReleaseToRefresh();

    /**
     * 当状态设置为{@link State#REFRESHING}时调用
     */
    protected abstract void onRefreshing();

    /**
     * 当状态设置为{@link State#NO_MORE_DATA}时调用
     */
    protected abstract void onNoMoreData() ;

    /**
     * 得到当前Layout的内容大小，它将作为一个刷新的临界点
     *
     * @return 高度
     */
    public abstract int getContentSize();
    /**
     * 创建Loading的View
     *
     * @param context context
     * @param attrs attrs
     * @return Loading的View
     */
    protected abstract View createLoadingView(Context context, AttributeSet attrs);
}
