package com.muan.takeout.Model;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class GoodsEntity {
    //id
    private long id;
    private long gid;
    //标题
    private String title;
    //第几期
    private String issue;
    //总需
    private int total;
    //剩余
    private int remain;
    //图片
    private String img;
    //期数
    private int multi;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMulti() {
        return multi;
    }

    public void setMulti(int multi) {
        this.multi = multi;
    }


}
