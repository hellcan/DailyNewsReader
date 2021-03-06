package com.news.reader.newsreader.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.reader.newsreader.R;
import com.news.reader.newsreader.adapter.GirlAdapter;
import com.news.reader.newsreader.api.ApiFactory;
import com.news.reader.newsreader.api.GankApi;
import com.news.reader.newsreader.bean.GankResult;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengchengding on 17/10/12.
 */

public class GirlFragment extends Fragment {

    private RecyclerView rv;
    private GirlAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_gank_common_fragment,container,false);

        //set up recyclerView
        rv = v.findViewById(R.id.gank_common_list);
        rv.setHasFixedSize(true);

        //set up layoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        //set up data with
        //retrofit
        GankApi gankApi = ApiFactory.getGankApiSingleton();
        //RxJava
        Subscription subscribe = gankApi.getAndroid("福利",20,1 )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankResult gankAndroid) {
                        //set up adapter
                        adapter = new GirlAdapter(getContext(),gankAndroid.getResultItem());
                        rv.setAdapter(adapter);
                    }
                });


        return v;


    }
}
