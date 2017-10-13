package com.news.reader.newsreader.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.reader.newsreader.R;

import java.util.HashMap;

/**
 * Created by fengchengding on 17/10/5.
 */

public class GankFragment extends Fragment {
    private static String[] tabName = {"Android", "iOS", "前端", "拓展资源", "福利"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.layout_gank_fragment, container, false);

        //Set up tabLayout
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout_ganhuo);
        tabLayout.addTab(tabLayout.newTab().setText(tabName[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[2]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[3]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[4]));

        //Set up viewPager.
        //NestFragment need use getChildFragmentManager()
        //getFragmentManager()是所在fragment 父容器的碎片管理，
        //getChildFragmentManager()是在fragment 里面子容器的碎片管理
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager_ganhuo);
        viewPager.setAdapter(new PageAdapter(getChildFragmentManager(), tabLayout.getTabCount()));

        //Combine tabLayout and viewPager.
        //Need override getPageTitle() to get tabName again
        tabLayout.setupWithViewPager(viewPager);


        return v;
    }

    //set up FragmentPageAdapter
    private class PageAdapter extends FragmentPagerAdapter {
        private String[] tabName = {"Android", "iOS", "前端", "拓展资源", "福利"};
        private int tabNum;
        private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

        public PageAdapter(FragmentManager fm, int tabNum) {
            super(fm);
            this.tabNum = tabNum;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(position);
        }

        @Override
        public int getCount() {
            return tabNum;
        }

        private Fragment createFragment(int position) {
            Fragment fragment = mFragmentHashMap.get(position);

            if (fragment == null) {
                switch (position) {
                    case 0:
                        fragment = new AndroidFragment(); //Android
                        break;
                    case 1:
                        fragment = new IosFragment(); //iOS
                        break;
                    case 2:
                        fragment = new FrontFragment(); //前端
                        break;
                    case 3:
                        fragment = new ExpandFragment(); //拓展资源
                        break;
                    case 4:
                        fragment = new GirlFragment(); //福利
                        break;
                }
                mFragmentHashMap.put(position, fragment);
            }
            return fragment;
        }
    }
}


