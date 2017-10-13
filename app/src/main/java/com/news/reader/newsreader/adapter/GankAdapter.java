package com.news.reader.newsreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.reader.newsreader.ItemDetail.GankCardViewDetail;
import com.news.reader.newsreader.R;
import com.news.reader.newsreader.bean.GankResultItem;
import com.news.reader.newsreader.utils.DateUtils;

import java.util.List;

/**
 * Created by fengchengding on 17/10/12.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private List<GankResultItem> gankResultItemList;
    private Context context;
    private CardView cardView;


    public GankAdapter(Context context, List<GankResultItem> gankResultItemList) {
        this.gankResultItemList = gankResultItemList;
        this.context = context;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_gank_common_cardview, parent, false);
        cardView = v.findViewById(R.id.gank_cardview);
        return new GankViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, final int position) {

        holder.vTitle.setText(gankResultItemList.get(position).getDesc());

        holder.vWho.setText(gankResultItemList.get(position).getWho());

        DateUtils dateUtils = new DateUtils();
        String time = dateUtils.getDate(gankResultItemList.get(position).getPublishedAt());
        holder.vDate.setText(time);

        //set onClick event
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,GankCardViewDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("_url", gankResultItemList.get(position).getUrl());
                bundle.putString("_type",gankResultItemList.get(0).getType());
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return gankResultItemList.size();
    }

    class GankViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected TextView vWho;
        protected TextView vDate;

        public GankViewHolder(View v) {
            super(v);
            vTitle = v.findViewById(R.id.tv_gank_title);
            vWho = v.findViewById(R.id.tv_gank_who);
            vDate = v.findViewById(R.id.tv_gank_date);
        }
    }
}
