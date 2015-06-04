package com.cntysoft.micromart.model;

import java.util.List;

/**
 * Created by Administrator on 2015/5/26.
 */
public class ShopInfos {

    private ShopInfo shopInfo;
    private List<GoodsInfo> goodsInfo;
    private List<CategoryInfo> categoryInfo;

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public List<GoodsInfo> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfo> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public List<CategoryInfo> getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(List<CategoryInfo> categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}
