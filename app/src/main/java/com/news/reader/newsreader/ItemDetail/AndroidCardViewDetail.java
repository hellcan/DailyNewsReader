package com.news.reader.newsreader.ItemDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.news.reader.newsreader.R;

public class AndroidCardViewDetail extends AppCompatActivity {
    private String url;
    private Toolbar toolbar;
//    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_cardview_detail);

        setToolBar();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("_url");

//        pd = ProgressDialog.show(AndroidCardViewDetail.this,null,"Loading");

        WebView wv = (WebView)findViewById(R.id.wv_content);

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.loadUrl(url);


    }

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
