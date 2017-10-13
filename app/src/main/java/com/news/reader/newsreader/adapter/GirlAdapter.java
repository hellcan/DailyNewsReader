package com.news.reader.newsreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.reader.newsreader.ItemDetail.AndroidCardViewDetail;
import com.news.reader.newsreader.R;
import com.news.reader.newsreader.bean.GankResultItem;
import com.news.reader.newsreader.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fengchengding on 17/10/12.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {

    private List<GankResultItem> gankResultItemList;
    private Context context;
    private CardView cardView;


    public GirlAdapter(Context context, List<GankResultItem> gankResultItemList) {
        this.gankResultItemList = gankResultItemList;
        this.context = context;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_gank_girl_cardview, parent, false);
        cardView = v.findViewById(R.id.girl_cardview);
        return new GirlViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, final int position) {

        //set img
        Glide.with(context).load(gankResultItemList.get(position).getUrl()).into(holder.ivGirl);

        //set date
        DateUtils dateUtils = new DateUtils();
        String s = dateUtils.getDate(gankResultItemList.get(position).getPublishedAt());
        holder.tvDate.setText(s);

        //set onClick event
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AndroidCardViewDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("_url", gankResultItemList.get(position).getUrl());
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return gankResultItemList.size();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvDate;
        protected ImageView ivGirl;

        public GirlViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tv_girl_date);
            ivGirl = v.findViewById(R.id.iv_girl_image);
        }
    }
}
