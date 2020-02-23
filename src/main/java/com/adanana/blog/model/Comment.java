package com.adanana.blog.model;

import java.util.Date;

public class Comment {
    private Long id;
    private String content;
    private Long commentUserId;
    private Integer deleted;
    private Date commentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
