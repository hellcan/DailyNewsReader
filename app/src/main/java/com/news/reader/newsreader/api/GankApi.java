package com.news.reader.newsreader.api;

import com.news.reader.newsreader.bean.GankResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by fengchengding on 17/10/11.
 */

public interface GankApi {

    //get gank data
    @GET("data/{type}/{count}/{page}")
    Observable<GankResult> getAndroid(@Path("type") String type, @Path("count") int count, @Path("page") int page);


}
