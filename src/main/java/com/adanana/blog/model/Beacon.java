package com.adanana.blog.model;

import java.util.Date;
import java.util.Objects;

public class Beacon {
    private Long id;
    private String minor;
    private Date createTime;
    /**
     * 0 表示 之前的   1 表示自己配的
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beacon beacon = (Beacon) o;
        return minor.equals(beacon.minor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minor);
    }
}
