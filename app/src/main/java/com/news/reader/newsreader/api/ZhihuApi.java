package com.news.reader.newsreader.api;

import com.news.reader.newsreader.bean.News;
import com.news.reader.newsreader.bean.NewsTimeLine;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by fengchengding on 17/10/8.
 */

public interface ZhihuApi {

    //latest news list
    //NewsTimeLine, entity to save news from Internet
    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    //latest news detail
    //use @Path when url include parameter
    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);

}
