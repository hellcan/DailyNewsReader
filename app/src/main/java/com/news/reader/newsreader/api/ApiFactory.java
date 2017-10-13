package com.news.reader.newsreader.api;

/**
 * Created by fengchengding on 17/10/9.
 */

public class ApiFactory {

    protected static final Object monitor = new Object();
    static ZhihuApi zhihuApiSingleton = null;
    static GankApi gankApiSingleton = null;


    public static ZhihuApi getZhihuApiSingleton() {
        synchronized (monitor) {
            if (zhihuApiSingleton == null) {
                zhihuApiSingleton = new ApiRetrofit().getZhihuApiService();
            }

        }
        return zhihuApiSingleton;
    }

    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
        if (gankApiSingleton == null) {
            gankApiSingleton = new ApiRetrofit().getGankApiService();
        }
        }
        return gankApiSingleton;
    }
}
