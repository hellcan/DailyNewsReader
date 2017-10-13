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
import com.news.reader.newsreader.ItemDetail.ZhihuCardViewDetail;
import com.news.reader.newsreader.R;
import com.news.reader.newsreader.bean.Stories;

import java.util.List;

/**
 * Created by fengchengding on 17/10/8.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ZhihuViewHolder> {

    private List<Stories> storiesList;
    private Context context;
    private CardView cardView;


    public ZhihuAdapter(Context context, List<Stories> storiesList) {
        this.storiesList = storiesList;
        this.context = context;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_zhihu_cardview, parent, false);
        cardView = v.findViewById(R.id.view_card);

        return new ZhihuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ZhihuViewHolder holder, final int position) {

        //set title
        holder.vTitle.setText(storiesList.get(position).getTitle());

        //set image src
        String imageUrl = storiesList.get(position).getImages()[0];
        Glide.with(context).load(imageUrl).centerCrop().into(holder.vImage);

        //set onClick event
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ZhihuCardViewDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("_id",storiesList.get(position).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    class ZhihuViewHolder extends RecyclerView.ViewHolder {

        protected ImageView vImage;
        protected TextView vTitle;

        public ZhihuViewHolder(View v) {
            super(v);
            vTitle = v.findViewById(R.id.tv_title);
            vImage = v.findViewById(R.id.iv_image);
        }
    }



}
