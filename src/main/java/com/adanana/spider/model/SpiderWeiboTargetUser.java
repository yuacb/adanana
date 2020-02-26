package com.adanana.spider.model;

public class SpiderWeiboTargetUser {
    private Long id;
    private String weiboUserId;
    private String weiboUserNickName;
    private Integer status;
    private String refUser;
    private String remarks;

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

    public String getWeiboUserNickName() {
        return weiboUserNickName;
    }

    public void setWeiboUserNickName(String weiboUserNickName) {
        this.weiboUserNickName = weiboUserNickName;
    }

    public String getRefUser() {
        return refUser;
    }

    public void setRefUser(String refUser) {
        this.refUser = refUser;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
