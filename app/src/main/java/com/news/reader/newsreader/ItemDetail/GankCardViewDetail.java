package com.news.reader.newsreader.ItemDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.news.reader.newsreader.R;


public class GankCardViewDetail extends AppCompatActivity {
    private String url;
    private String type;
    private Toolbar toolbar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_cardview_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("_url");
        type = bundle.getString("_type");

        setToolBar();


        WebView wv = (WebView)findViewById(R.id.wv_content);

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        wv.loadUrl(url);

        //set up progress dialog
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd = ProgressDialog.show(GankCardViewDetail.this,null,"Loading");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(pd.isShowing()){
                    pd.dismiss();
                }
            }
        });




    }

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(type);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish(); //finish current Activity
                break;
        }
        return true;
    }
}
