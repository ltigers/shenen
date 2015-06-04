package com.cntysoft.android.view.pullrefreshview;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2015/6/3.
 */
public class PullToRefreshGridView  extends PullToRefreshBase<GridView> implements AbsListView.OnScrollListener {

    private LoadingLayout mFooterLayout;
    private GridView mGridView;
    private AbsListView.OnScrollListener mScrollListener;
    public PullToRefreshGridView(Context context)
    {
        this(context, null);
    }

    public PullToRefreshGridView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public PullToRefreshGridView(Context context, AttributeSet attrs, int defstyle)
    {
        super(context, attrs);
        setPullLoadEnabled(false);
    }

    @Override
    protected GridView createRefreshableView(Context context, AttributeSet attrs) {
        GridView localGridView = new GridView(context, attrs);
        localGridView.setId(R.id.list);
        this.mGridView = localGridView;
        localGridView.setOnScrollListener(this);
        return localGridView;
    }

    @Override
    protected LoadingLayout createHeaderLoadingLayout(Context context, AttributeSet attrs) {
        return new HeaderLoadingLayout(context);
    }

    public void setHasMoreData(boolean hasMoreData) {
        if (!hasMoreData) {
            if (null != mFooterLayout) {
                mFooterLayout.setState(ILoadingLayout.State.NO_MORE_DATA);
            }

            LoadingLayout footerLoadingLayout = getFooterLoadingLayout();
            if (null != footerLoadingLayout) {
                footerLoadingLayout.setState(ILoadingLayout.State.NO_MORE_DATA);
            }
        }
    }
    /**
     * 表示是否还有更多数据
     *
     * @return true表示还有更多数据
     */
    private boolean hasMoreData() {
        if ((null != mFooterLayout) && (mFooterLayout.getState() == ILoadingLayout.State.NO_MORE_DATA)) {
            return false;
        }

        return true;
    }

    private boolean isFirstItemVisible()
    {
        ListAdapter localListAdapter = this.mGridView.getAdapter();
        boolean bool;
        if (localListAdapter == null || localListAdapter.isEmpty())
        {
            bool = true;
            return bool;
        }
        int mTop = (mGridView.getChildCount() > 0) ? mGridView.getChildAt(0).getTop() : 0;
        LogUtils.d("FirstItemVisible " + this.mGridView.getChildCount());
        bool = false;
        if(mTop >= 0){
            LogUtils.d("FirstItemVisible " + this.mGridView.getChildCount() + "-----" + this.mGridView.getChildAt(0).getTop());
            return true;
        }
        return bool;
    }

    private boolean isLastItemVisible() {
        final Adapter adapter = mGridView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        }

        final int lastItemPosition = adapter.getCount() - 1;
        final int lastVisiblePosition = mGridView.getLastVisiblePosition();

        /**
         * This check should really just be: lastVisiblePosition == lastItemPosition, but ListView
         * internally uses a FooterView which messes the positions up. For me we'll just subtract
         * one to account for it and rely on the inner condition which checks getBottom().
         */
        if (lastVisiblePosition >= lastItemPosition) {
            final int childIndex = lastVisiblePosition - mGridView.getFirstVisiblePosition();
            final int childCount = mGridView.getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = mGridView.getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= mGridView.getBottom();
            }
        }

        return false;
    }
    public void setOnScrollListener(AbsListView.OnScrollListener scrollListener)
    {
        this.mScrollListener = scrollListener;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if ((isScrollLoadEnabled()) && (hasMoreData()) && ((scrollState == 0) || (scrollState == 2)) && (isReadyForPullUp()))
            startLoading();
        if (this.mScrollListener != null)
            this.mScrollListener.onScrollStateChanged(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(this.mScrollListener != null){
            this.mScrollListener.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
        }
    }


    @Override
    protected boolean isReadyForPullDown() {
        return isFirstItemVisible();
    }

    @Override
    protected boolean isReadyForPullUp() {
        return isLastItemVisible();
    }

    @Override
    public void onPullUpRefreshComplete() {
        super.onPullUpRefreshComplete();
        if (this.mFooterLayout != null)
            this.mFooterLayout.setState(ILoadingLayout.State.RESET);
    }

    @Override
    public void setScrollLoadEnabled(boolean scrollLoadEnabled) {
        super.setScrollLoadEnabled(scrollLoadEnabled);
        if(scrollLoadEnabled && this.mFooterLayout == null){
            this.mFooterLayout = new FooterLoadingLayout(getContext());
        }
    }

    @Override
    protected void startLoading() {
        super.startLoading();
        if (null != mFooterLayout) {
            mFooterLayout.setState(ILoadingLayout.State.REFRESHING);
        }
    }
}
