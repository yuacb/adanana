package com.spider.model;

public class SpiderWeboInfo {
    private Long id;
    private String weiboUserId;
    private String weiboContent;
    private String spiderWeboInfocol;
    private double numberForward;
    private double numberFavorite;
    private double numberRepeat;
    private double numberPraised;
    private String repeatInfo;

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

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public String getSpiderWeboInfocol() {
        return spiderWeboInfocol;
    }

    public void setSpiderWeboInfocol(String spiderWeboInfocol) {
        this.spiderWeboInfocol = spiderWeboInfocol;
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
