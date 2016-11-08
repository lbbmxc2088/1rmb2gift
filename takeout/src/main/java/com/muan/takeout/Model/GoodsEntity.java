package com.muan.takeout.Model;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class GoodsEntity {


    /**
     * goods_id : 1
     * goods_sn : 001
     * goods_name : Apple iPhone6s Plus 128G 颜色随机
     * shop_price : 10.00
     * yunjiage : 1
     * goods_thumb : iphoneplust_1.jpg
     * copies : 658
     * sell_copies : 658
     * page_count : 8
     */

    private String goods_id;
    private String goods_sn;
    private String goods_name;
    private String shop_price;
    private String yunjiage;
    private String goods_thumb;
    private String copies;
    private String sell_copies;
    private String page_count;

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public void setYunjiage(String yunjiage) {
        this.yunjiage = yunjiage;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public void setSell_copies(String sell_copies) {
        this.sell_copies = sell_copies;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public String getYunjiage() {
        return yunjiage;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public String getCopies() {
        return copies;
    }

    public String getSell_copies() {
        return sell_copies;
    }

    public String getPage_count() {
        return page_count;
    }
}
