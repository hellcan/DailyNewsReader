package com.news.reader.newsreader.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.reader.newsreader.R;
import com.news.reader.newsreader.adapter.ZhihuAdapter;
import com.news.reader.newsreader.api.ApiFactory;
import com.news.reader.newsreader.api.ZhihuApi;
import com.news.reader.newsreader.bean.NewsTimeLine;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengchengding on 17/10/2.
 */

public class ZhihuFragment extends Fragment  {

    private ZhihuAdapter adapter;
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //set up ProgressDialog
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.show();

        View v = inflater.inflate(R.layout.layout_zhihu_fragment, container, false);

        //set up recyclerView
        rv = (RecyclerView) v.findViewById(R.id.zhihu_list);
        rv.setHasFixedSize(true);

        //set up layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        //set up data with retrofit and RxaJava
        ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();
        Subscription subscribe = zhihuApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<NewsTimeLine>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(NewsTimeLine newsTimeLine) {
                                //set up adapter
                                adapter = new ZhihuAdapter(getContext(),newsTimeLine.getStories());
                                rv.setAdapter(adapter);
                                pd.dismiss();
                            }
                        }

                );
        return v;
    }
}
