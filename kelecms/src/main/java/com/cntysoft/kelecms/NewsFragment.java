package com.cntysoft.kelecms;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/6.
 */
public class NewsFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager vp;

    private List<TextView> tvs;
    private Adapter adapter;
    private String[] items = {"头条","国际","军事","体育","房产","科技","财经","娱乐","汽车"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.news_fragment,null);
        }
        ViewGroup parent = (ViewGroup)view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        vp = (ViewPager) view.findViewById(R.id.vp);
        initListView();
        tabLayout.setTabTextColors(Color.BLACK, Color.RED);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new Adapter();
        vp.setAdapter(adapter);
        //用来设置tab的，同时也要覆写  PagerAdapter 的 CharSequence getPageTitle(int position) 方法，要不然 Tab 没有 title
        tabLayout.setupWithViewPager(vp);
        //关联 TabLayout viewpager
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ((MainActivity)getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity)
                    if (position == 0) {
                        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
                        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.LEFT);
                    } else if (position == tvs.size() - 1) {
                        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
                        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
                    } else {
                        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    private void initListView(){
        tvs = new ArrayList<TextView>();
        for (int i = 0; i < items.length; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText(items[i]);
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(lp);
            tv.setTextSize(22);
            tvs.add(tv);
        }
    }

    class Adapter extends PagerAdapter {
        public Adapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = tvs.get(position);
            ((ViewPager)container).addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(tvs.get(position));
        }

        @Override
        public int getCount() {
            return tvs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
