package com.adanana.spider.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpiderWeboInfo implements Serializable {
    private Long id;
    private String weiboUserId;
    private String weiboUserNickName;
    private String weiboFeelId;
    private String weiboContent;
    //private String spiderWeboInfocol;
    private double numberForward;
    private double numberFavorite;
    private double numberRepeat;
    private double numberPraised;
    private String repeatInfo;
    private Date feedTime;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(String feedTime)  {
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date=formatter.parse(feedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.feedTime = date;
    }

    public String getWeiboUserNickName() {
        return weiboUserNickName;
    }

    public void setWeiboUserNickName(String weiboUserNickName) {
        this.weiboUserNickName = weiboUserNickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeiboUserId() {
        return weiboUserId;
    }

    public void setWeiboUserId(String weiboUserId) {
        this.weiboUserId = weiboUserId;
    }

    public String getWeiboFeelId() {
        return weiboFeelId;
    }

    public void setWeiboFeelId(String weiboFeelId) {
        this.weiboFeelId = weiboFeelId;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }


    public double getNumberForward() {
        return numberForward;
    }

    public void setNumberForward(double numberForward) {
        this.numberForward = numberForward;
    }

    public double getNumberFavorite() {
        return numberFavorite;
    }

    public void setNumberFavorite(double numberFavorite) {
        this.numberFavorite = numberFavorite;
    }

    public double getNumberRepeat() {
        return numberRepeat;
    }

    public void setNumberRepeat(double numberRepeat) {
        this.numberRepeat = numberRepeat;
    }

    public double getNumberPraised() {
        return numberPraised;
    }

    public void setNumberPraised(double numberPraised) {
        this.numberPraised = numberPraised;
    }

    public String getRepeatInfo() {
        return repeatInfo;
    }

    public void setRepeatInfo(String repeatInfo) {
        this.repeatInfo = repeatInfo;
    }
}
