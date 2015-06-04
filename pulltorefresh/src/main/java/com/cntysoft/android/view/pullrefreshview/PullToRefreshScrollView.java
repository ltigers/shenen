package com.cntysoft.android.view.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.cntysoft.android.view.MyScrollView;

/**
 * Created by Administrator on 2015/6/3.
 */
public class PullToRefreshScrollView extends PullToRefreshBase<MyScrollView> {

    public PullToRefreshScrollView(Context context) {
        this(context,null);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPullLoadEnabled(false);
    }


    @Override
    protected MyScrollView createRefreshableView(Context context, AttributeSet attrs) {
        return new MyScrollView(context);
    }

    @Override
    protected boolean isReadyForPullDown() {
        return ((MyScrollView)this.mRefreshableView).getScrollY() == 0;
    }

    @Override
    protected boolean isReadyForPullUp() {
        View localView = ((MyScrollView)this.mRefreshableView).getChildAt(0);
        if (localView != null)
            return ((MyScrollView)this.mRefreshableView).getScrollY() >= localView.getHeight() - getHeight();
        return false;
    }
}
