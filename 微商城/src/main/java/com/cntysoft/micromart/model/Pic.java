package com.cntysoft.micromart.model;

/**
 * Created by Administrator on 2015/5/26.
 */
public class Pic {
    private String url;
    private String order;
    private boolean isCover;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean getIsCover() {
        return isCover;
    }

    public void setIsCover(boolean isCover) {
        this.isCover = isCover;
    }
}
