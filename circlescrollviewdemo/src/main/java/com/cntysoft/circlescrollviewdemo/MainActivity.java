package com.cntysoft.circlescrollviewdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    private CircleScrollView mScrollView;
    private CircleScrollView.Adapter mAdapter;
    private LayoutInflater mInflater;
//    private List<Bean> mDatas = new ArrayList<Bean>(Arrays.asList(//
//            new Bean(R.drawable.icon_about, Color.rgb(123, 34, 45)),//
//            new Bean(R.drawable.icon_map, Color.rgb(12, 34, 145)),//
//            new Bean(R.drawable.icon_message, Color.rgb(177, 234, 145)),//
//            new Bean(R.drawable.icon_oil, Color.rgb(88, 134, 145))//
//    ));

    private String[] mDatas = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);

        mScrollView = (CircleScrollView) findViewById(R.id.id_scrollview);
        mAdapter = new CircleScrollView.Adapter()
        {

            @Override
            public View getView(CircleScrollView parent, int pos)
            {
                View view = mInflater.inflate(R.layout.item, null);
                /*ImageView iv = (ImageView) view.findViewById(R.id.id_title);
                iv.setImageResource(mDatas.get(pos).getResId());
                view.setBackgroundColor(mDatas.get(pos).getColor());*/
                TextView tv = (TextView) view.findViewById(R.id.id_title);
                tv.setText(mDatas[pos]);
                return view;
            }

            @Override
            public int getCount()
            {
                return 12;
            }

        };

        mScrollView.setAdapter(mAdapter);

        mScrollView.setOnItemClickListener(new CircleScrollView.OnItemClickListener()
        {
            @Override
            public void onItemClick(int pos, View view)
            {
                Toast.makeText(getApplicationContext(), pos + "",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
