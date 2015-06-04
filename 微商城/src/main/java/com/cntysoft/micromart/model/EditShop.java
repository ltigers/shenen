package com.cntysoft.micromart.model;

/**
 * Created by Administrator on 2015/5/26.
 */
public class EditShop {
    private int id;
    private String name;
    private String shopLogo;
    private String shopSign;
    private String announce;
    private String qrCode;
    private String shopAddr;
    //@SerializedName("weChat")
    //private String weixin;//假如变量命名与解析数据不同，则加上@SerializedName("")
    private String weChat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopSign() {
        return shopSign;
    }

    public void setShopSign(String shopSign) {
        this.shopSign = shopSign;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }
}
