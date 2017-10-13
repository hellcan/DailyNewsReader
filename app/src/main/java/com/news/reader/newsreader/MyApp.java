package com.news.reader.newsreader;

import android.app.Application;
import android.content.Context;

/**
 * Created by fengchengding on 17/10/9.
 */

public class MyApp extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
