package com.cntysoft.testmaterial;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TabActivity extends Activity {

    private ViewPager vp;
    private TabLayout tabLayout;
    private List<TextView> tvs;
    private Adapter adapter;
    private String[] items = {"第一页","第二页","第三页","第四页","第五页","第六页"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tvs = new ArrayList<TextView>();
        for (int i = 0; i < items.length; i++) {
            TextView tv = new TextView(this);
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
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabTextColors(Color.WHITE, Color.GRAY);//设置文本在选中和为选中时候的颜色
        vp = (ViewPager) findViewById(R.id.vp);
        adapter = new Adapter();
        vp.setAdapter(adapter);
        //用来设置tab的，同时也要覆写  PagerAdapter 的 CharSequence getPageTitle(int position) 方法，要不然 Tab 没有 title
        tabLayout.setupWithViewPager(vp);
        //关联 TabLayout viewpager
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    class Adapter extends PagerAdapter{
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
            /*tabLayout.setTabTextColors(Color.WHITE, Color.GRAY);//设置文本在选中和为选中时候的颜色
            tabLayout.addTab(tabLayout.newTab().setText("第一个"), true);//添加 Tab,默认选中
            tabLayout.addTab(tabLayout.newTab().setText("第二个"),false);//添加 Tab,默认不选中
            tabLayout.addTab(tabLayout.newTab().setText("第三个"),false);//添加 Tab,默认不选中
            return tabLayout.getTabAt(position).getText();*/
            return items[position];
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
