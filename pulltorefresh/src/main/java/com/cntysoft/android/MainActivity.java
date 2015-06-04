package com.cntysoft.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cntysoft.android.utils.CommonUtils;
import com.cntysoft.android.view.RollViewPager;
import com.cntysoft.android.view.pullrefreshview.PullToRefreshBase;
import com.cntysoft.android.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @ViewInject(R.id.top_news_viewpager)
    private LinearLayout mViewPagerLay;
    @ViewInject(R.id.top_news_title)
    private TextView textView;
    @ViewInject(R.id.dots_ll)
    private LinearLayout dotLl;
    @ViewInject(R.id.pulllistview)
    private PullToRefreshListView pullToRefreshListView;

    private ArrayList<View> dotList;
    private int[] imgIds = {R.drawable.a,R.drawable.b,R.drawable.c};
    private ArrayList<String> titleList;
    private ArrayList<String> urlList;
    private int focus_ids = R.drawable.dot_focus;
    private int normal_ids = R.drawable.dot_normal;

    private RollViewPager mViewPager;
    private View view;
    private String[] str = {"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"};
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_roll_view);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        view = LayoutInflater.from(this).inflate(R.layout.layout_roll_view,null);
        ViewUtils.inject(this,view);
        initDots(imgIds.length);
        initUrl(imgIds.length);
        initTitle(imgIds.length);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,str);
        initView();

    }

    private void initDots(int size){
        dotList = new ArrayList<View>();
        dotLl.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonUtils.dip2px(this, 6), CommonUtils.dip2px(this, 6));
            params.setMargins(5, 0, 5, 0);
            View m = new View(this);
            if (i == 0) {
                m.setBackgroundResource(R.drawable.dot_focus);
            } else {
                m.setBackgroundResource(R.drawable.dot_normal);
            }
            m.setLayoutParams(params);
            dotLl.addView(m);
            dotList.add(m);
        }
    }

    private void initTitle(int size){
        titleList = new ArrayList<String>();
        for(int i = 0; i< size; i++){
            titleList.add("图片" + i);
        }
    }

    private void initView(){
        mViewPager = new RollViewPager(this, dotList, focus_ids, normal_ids, new RollViewPager.OnPagerClickCallback() {
            @Override
            public void onPagerClick(int position) {
                Log.i(TAG," " + position);
                Toast.makeText(MainActivity.this,"" + position,Toast.LENGTH_SHORT).show();
            }
        });
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
       // mViewPager.setResImageIds(imgIds);
        mViewPager.setUriList(urlList);
        mViewPager.setTitle(textView, titleList);
        mViewPager.startRoll();
        mViewPagerLay.removeAllViews();
        mViewPagerLay.addView(mViewPager);
        pullToRefreshListView.setPullRefreshEnabled(true);
        pullToRefreshListView.setPullLoadEnabled(true);
        setLastUpdateTime();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.setHasMoreData(false);
                                onLoaded();
                                setLastUpdateTime();
                            }
                        });

                    }
                }.start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.setHasMoreData(false);
                                onLoaded();
                                setLastUpdateTime();
                            }
                        });
                    }
                }.start();
            }
        });
        if (pullToRefreshListView.getRefreshableView().getHeaderViewsCount() < 1) {
            pullToRefreshListView.getRefreshableView().addHeaderView(view);
        }
        pullToRefreshListView.getRefreshableView().setAdapter(arrayAdapter);

    }

    private void initUrl(int size){
        urlList = new ArrayList<String>();
        urlList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
        urlList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
        urlList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
    }

    private void setLastUpdateTime() {
        String text = CommonUtils.getStringDate();
        pullToRefreshListView.setLastUpdatedLabel(text);
    }

    private void onLoaded() {
        pullToRefreshListView.onPullDownRefreshComplete();
        pullToRefreshListView.onPullUpRefreshComplete();
    }


}
