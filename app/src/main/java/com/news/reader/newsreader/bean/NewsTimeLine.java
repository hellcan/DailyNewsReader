package com.news.reader.newsreader.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengchengding on 17/10/8.
 */

public class NewsTimeLine implements Serializable {

    private String date;
    private List<Stories> stories;
    private List<TopStories>top_stories;

    public String getDate() {
        return date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    @Override
    public String toString() {
        return "NewsTimeLine{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
