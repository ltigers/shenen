package com.cntysoft.circlescrollviewdemo;

/**
 * Created by Administrator on 2015/5/9.
 */
public class Bean {

    private int resId;
    private int color ;

    public Bean()
    {
    }

    public Bean(int resId, int color)
    {
        super();
        this.resId = resId;
        this.color = color;
    }

    public int getResId()
    {
        return resId;
    }
    public void setResId(int resId)
    {
        this.resId = resId;
    }
    public int getColor()
    {
        return color;
    }
    public void setColor(int color)
    {
        this.color = color;
    }
}
