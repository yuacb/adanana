package com.adanana.blog.model;

import java.util.Date;

public class BaseModel {
    private Date createTime;
    private Date updateTime;
    public void BaseModel(){
        System.out.println("父类构造方法");
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
