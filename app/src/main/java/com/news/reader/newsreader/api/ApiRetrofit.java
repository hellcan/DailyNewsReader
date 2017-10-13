package com.news.reader.newsreader.api;


import com.news.reader.newsreader.utils.MyApp;
import com.news.reader.newsreader.utils.StateUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fengchengding on 17/10/8.
 */

public class ApiRetrofit {

    public ZhihuApi ZhihuApiService;
    public GankApi gankApiService;
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String GANk_BASE_URL = "http://gank.io/api/";


    public ZhihuApi getZhihuApiService() {

        return ZhihuApiService;
    }

    public GankApi getGankApiService() {
        return gankApiService;
    }

    ApiRetrofit() {
        //cache url
        File cacheFile = new File(MyApp.mContext.getCacheDir(), "responses");
        //cache size 10 Mib
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);

        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365, TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();

                Request request = chain.request();
                if (!StateUtils.isNetworkAvailable(MyApp.mContext)) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();

                }
                Response originalResponse = chain.proceed(request);
                if (StateUtils.isNetworkAvailable(MyApp.mContext)) {
                    int maxAge = 0; // read from cache
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();

        Retrofit retrofit_zhihu = new Retrofit.Builder()
                .baseUrl(ZHIHU_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit retrofit_gank = new Retrofit.Builder()
                .baseUrl(GANk_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        ZhihuApiService = retrofit_zhihu.create(ZhihuApi.class);
        gankApiService = retrofit_gank.create(GankApi.class);


    }
}
