package com.cntysoft.wheelview.adapters;

import android.content.Context;

import com.cntysoft.wheelview.widget.WheelAdapter;

/**
 * Created by Administrator on 2015/5/15.
 */
@Deprecated
public class AdapterWheel extends AbstractWheelTextAdapter {

    // Source adapter
    private WheelAdapter adapter;

    /**
     * Constructor
     *
     * @param context
     *            the current context
     * @param adapter
     *            the source adapter
     */
    public AdapterWheel(Context context, WheelAdapter adapter) {
        super(context);

        this.adapter = adapter;
    }

    /**
     * Gets original adapter
     *
     * @return the original adapter
     */
    public WheelAdapter getAdapter() {
        return adapter;
    }

    @Override
    public int getItemsCount() {
        return adapter.getItemsCount();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return adapter.getItem(index);
    }
}
