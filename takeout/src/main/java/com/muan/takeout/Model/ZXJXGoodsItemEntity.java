package com.muan.takeout.Model;

/**
 * Created by ${Muan} on 2016/11/1 15:30
 */
public class ZXJXGoodsItemEntity {
    /**
     *   issue:当前期
     title:商品title
     img:商品图片
     gid:商品id
     ghid:记录id
     uid:获奖人id
     nickname:获奖人昵称
     joinintimes:参与次数
     luckyno:幸运数字
     opened_at:揭晓时间
     seconds:倒计时秒数
     status:1已开奖2开奖中
     */
    public long ghid;
    public long gid;
    public String title;
    public long issue;
    public String img;
    public long uid;
    public String nickname;
    public int joinintimes;
    public int luckyno;
    public String opened_at;
    public int seconds;
    public int status;
}
