package com.news.reader.newsreader.ItemDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.reader.newsreader.R;
import com.news.reader.newsreader.api.ApiFactory;
import com.news.reader.newsreader.api.ZhihuApi;
import com.news.reader.newsreader.bean.News;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ZhihuCardViewDetail extends AppCompatActivity {
    String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_cardview_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        newsId = bundle.getString("_id");


        final WebView wv_content = (WebView) findViewById(R.id.wv_content);
        final ImageView iv_detail_img = (ImageView) findViewById(R.id.iv_detail_img);
        final TextView tv_title = (TextView) findViewById(R.id.tv_title);
        final TextView tv_img_source =  (TextView) findViewById(R.id.tv_img_source);

        ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();
        Subscription subscribe = zhihuApi.getDetailNews(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<News>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(News news) {
                                //set img
                                Glide.with(getApplicationContext()).load(news.getImage()).centerCrop().into(iv_detail_img);
                                //set title
                                tv_title.setText(news.getTitle());
                                //set img source
                                tv_img_source.setText("图片: " + news.getImage_source());
                                //set content(WebView)
                                //adjust webview content fit screen size
                                WebSettings settings = wv_content.getSettings();
                                settings.setJavaScriptEnabled(true);
                                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                                //add css style link in head
                                // \n，回车换行
                                String head = "<head><link rel=\"Stylesheet\" type = \"text/css\" href=\""+ news.getCss()[0]+"\"/></head>";
                                String img = "<div class=\"headline\">"; //delete blank space before the content
                                String html = head + news.getBody().replace(img," ");
                                //set webView content with html code
                                wv_content.loadDataWithBaseURL(null,html,"text/html","utf-8",null);

                            }
                        }
                );

    }
}
