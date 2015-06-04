package com.cntysoft.android.view.pagerindicator;

/**
 * Created by Administrator on 2015/6/2.
 */
public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
