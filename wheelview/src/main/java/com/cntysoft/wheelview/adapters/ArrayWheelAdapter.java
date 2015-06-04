package com.cntysoft.wheelview.adapters;

import android.content.Context;

/**
 * Created by Administrator on 2015/5/15.
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {

    // items
    private T items[];

    public ArrayWheelAdapter(Context context, T items[]){
        super(context);
        this.items = items;
    }

    @Override
    public  CharSequence getItemText(int index) {
        if(index >= 0 && index < items.length){
            T item = items[index];
            if(item instanceof  CharSequence){
                return (CharSequence) item;
            }
            return item.toString();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return items.length;
    }
}
