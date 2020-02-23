package com.spider.model;

public class SpiderWeiboUserTarget {
    private Long id;
    private String weiboName;
    private String weiboId;
    private String status;
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeiboName() {
        return weiboName;
    }

    public void setWeiboName(String weiboName) {
        this.weiboName = weiboName;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
