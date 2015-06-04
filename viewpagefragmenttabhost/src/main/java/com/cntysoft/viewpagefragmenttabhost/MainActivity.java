package com.cntysoft.viewpagefragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private FragmentTabHost fragmentTabHost;
    private ViewPager viewPager;
    private Class fragmentArray[] = { Fragment1.class, Fragment2.class,
            Fragment3.class, Fragment4.class };
    private int imageViewArray[] = { R.drawable.mywork, R.drawable.mypatient,
            R.drawable.infusion, R.drawable.personal };
    private String textViewArray[] = {"微信","通讯录","发现","我"};
    private LayoutInflater layoutInflater;
    private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(this);
        layoutInflater = LayoutInflater.from(this);
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.pager);
        fragmentTabHost.setOnTabChangedListener(this);
        int count = textViewArray.length;
        for(int i = 0;i < count; i++){
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(textViewArray[i]).setIndicator(getTabItemView(i));
            fragmentTabHost.addTab(tabSpec,fragmentArray[i],null);
            fragmentTabHost.setTag(i);
        }
    }

    private void initPage() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        TabWidget widget = fragmentTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        fragmentTabHost.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);
        fragmentTabHost.getTabWidget().getChildAt(position)
                .setBackgroundResource(R.drawable.selector_tab_background);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = fragmentTabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }
}
