package com.cntysoft.android.view.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Administrator on 2015/6/3.
 */
public class PullToRefreshWebView extends PullToRefreshBase<WebView> {

    public PullToRefreshWebView(Context context) {
        this(context,null);
    }

    public PullToRefreshWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPullLoadEnabled(false);
    }


    @Override
    protected WebView createRefreshableView(Context context, AttributeSet attrs) {
        return new WebView(context);
    }

    @Override
    protected boolean isReadyForPullDown() {
        return ((WebView)this.mRefreshableView).getScrollY() == 0;
    }

    @Override
    protected boolean isReadyForPullUp() {
        float contentHeight = ((WebView)this.mRefreshableView).getContentHeight() * ((WebView)this.mRefreshableView).getScale();
        return ((WebView)this.mRefreshableView).getScrollY() >= contentHeight - ((WebView)this.mRefreshableView).getHeight();
    }
}
