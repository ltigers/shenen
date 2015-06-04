package com.cntysoft.wheelview.widget;

/**
 * Created by Administrator on 2015/5/14.
 */
/**
 * Range for visible items.
 */
public class ItemsRange {

    // First item number
    private int first;

    // Items count
    private int count;

    /**
     *
     * @param first
     *               the number of first item
     * @param count
     *               the count of items
     */
    public ItemsRange(int first, int count){
        this.first = first;
        this.count = count;
    }

    public ItemsRange(){
        this(0,0);
    }

    /**
     * Gets number of first item
     *
     * @return the number of the first item
     */
    public int getFirst(){
        return first;
    }

    /**
     * Get items count
     *
     * @return the count of items
     */
    public int getCount(){
        return count;
    }

    /**
     * Gets number of last item
     *
     * @return the number of last item
     */
    public int getLast(){
        return getFirst() + getCount() - 1;
    }

    public boolean contains(int index){
        return index >= getFirst() && index <= getLast();
    }
}
