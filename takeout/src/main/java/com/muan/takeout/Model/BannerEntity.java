package com.muan.takeout.Model;

import java.util.List;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class BannerEntity {

    /**
     * Lbt : [{"ad_id":"1","ad_name":"轮播图1","ad_code":"lbt1.jpg","ad_link":"www.1.163.com"},{"ad_id":"2","ad_name":"轮播图2","ad_code":"lbt2.jpg","ad_link":"www.baidu.com"}]
     */
    /**
     * ad_id : 1
     * ad_name : 轮播图1
     * ad_code : lbt1.jpg
     * ad_link : www.1.163.com
     */

    private String ad_id;
    private String ad_name;
    private String ad_code;
    private String ad_link;

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public void setAd_code(String ad_code) {
        this.ad_code = ad_code;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public String getAd_id() {
        return ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public String getAd_code() {
        return ad_code;
    }

    public String getAd_link() {
        return ad_link;
    }
}
