package com.muan.takeout.Model;

/**
 * Created by ${Muan} on 2016/11/4 09:25
 *  猜你喜欢
 */
public class RecomEntity {
    private String goodsName;
    private String imgUrl;
    private long goodsId;
    private int maxtimes;
    private int currenttimes;

    public int getMaxtimes() {
        return maxtimes;
    }

    public void setMaxtimes(int maxtimes) {
        this.maxtimes = maxtimes;
    }

    public int getCurrenttimes() {
        return currenttimes;
    }

    public void setCurrenttimes(int currenttimes) {
        this.currenttimes = currenttimes;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
