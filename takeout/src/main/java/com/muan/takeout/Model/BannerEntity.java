package com.muan.takeout.Model;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class BannerEntity {
    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShare_icon() {
        return share_icon;
    }

    public void setShare_icon(String share_icon) {
        this.share_icon = share_icon;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    /**
     * "gid": 1,
     * "img": "http://zerogo-img.b0.upaiyun.com/2015/06/2015063015025928971img.jpg",
     * "url": "@1",
     * "title": "小米移动电源"
     */


    private long gid;
    private String img;
    private String url;
    private String title;

    private String share_icon;
    private String share_title;
    private String share_content;
    private String share_url;
}
