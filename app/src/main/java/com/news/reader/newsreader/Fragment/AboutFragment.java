package com.news.reader.newsreader.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.reader.newsreader.R;



/**
 * Created by fengchengding on 17/10/12.
 */

public class AboutFragment extends Fragment {
    private TextView textView;
    private TextView textView1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_about,container,false);

        textView = v.findViewById(R.id.textview);
        textView1 = v.findViewById(R.id.textview1);

        textView.setText("Practice for Restful web service");
        textView1.setText("Zhihu API from ZhihuDailyPurify\n" +
                "Gank API from gank.io");

        return v;
    }
}
