package com.news.reader.newsreader.bean;

import java.util.List;

/**
 * Created by fengchengding on 17/10/11.
 */

public class GankResult {

    private boolean error;
    private List<GankResultItem> results;

    public void setError(boolean error) {
        this.error = error;
    }
    public boolean getError() {
        return error;
    }

    public void setResults(List<GankResultItem> results) {
        this.results = results;
    }
    public List<GankResultItem> getResultItem() {
        return results;
    }

}
