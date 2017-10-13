package com.news.reader.newsreader.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by fengchengding on 17/10/11.
 */

public class GankResultItem implements Serializable {

    private String url;
    private String type;
    public String desc;
    private String who;
    private boolean used;
    private Date createdAt;
    private Date updatedAt;
    private Date publishedAt;

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getWho() {
        return who;
    }

    public boolean isUsed() {
        return used;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }


}
