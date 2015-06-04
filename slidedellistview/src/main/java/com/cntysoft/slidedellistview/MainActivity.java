package com.cntysoft.slidedellistview;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    private List<ApplicationInfo> mAppList;
    private SlideDelListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (SlideDelListView) findViewById(R.id.listView);
        mAppList = getPackageManager().getInstalledApplications(0);
        mListView.setAdapter(new AppAdapter(mAppList));
    }

    class AppAdapter extends BaseAdapter {
        private List<ApplicationInfo> mAppList;

        public AppAdapter(List<ApplicationInfo> appList) {
            this.mAppList = appList;
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int loc = position;
            ViewHolder holder = null;
            View menuView = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.swipecontent, null);
                menuView = View.inflate(getApplicationContext(),
                        R.layout.swipemenu, null);
                convertView = new SlideDelItem(convertView, menuView);
                holder = new ViewHolder(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
            holder.tv_name.setText(item.loadLabel(getPackageManager()));
            holder.tv_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(MainActivity.this, "open:" + loc, Toast.LENGTH_SHORT).show();
                }
            });
            holder.tv_del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;
            TextView tv_open, tv_del;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_open = (TextView) view.findViewById(R.id.tv_open);
                tv_del = (TextView) view.findViewById(R.id.tv_del);
                view.setTag(this);
            }
        }
    }
}
