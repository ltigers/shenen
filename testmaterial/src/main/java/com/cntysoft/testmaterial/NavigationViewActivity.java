package com.cntysoft.testmaterial;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class NavigationViewActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private Button button;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ActionBar actionBar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        button = (Button) findViewById(R.id.button);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("新闻");
        toolbar.setTitleTextColor(0xffffffff);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        //取得ActionBar
        //actionBar = getSupportActionBar();
        //设置不显示标题
        /*actionBar.setDisplayShowTitleEnabled(false);
        //设置显示logo
        //actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.netease_top);
        //设置actionbar背景
        Drawable background = getResources().getDrawable(R.drawable.top_bar_background);
        actionBar.setBackgroundDrawable(background);
        actionBar.setDisplayHomeAsUpEnabled(true);*/
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
       // actionBar.setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_item_2:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        menuItem.setChecked(true);
                        return true;
                }
                return false;
            }
        });
        //actionBar.setDefaultDisplayHomeAsUpEnabled(true);

       // 设置drawerlayout监听
		//drawerLayout.setDrawerListener(new NetEaseDrawerListener());
				// toggle
		this.actionBarDrawerToggle = new ActionBarDrawerToggle(this,this.drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //actionBarDrawerToggle.syncState();
      // drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    //加载menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else{
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //actionBarDrawerToggle.syncState();
    }

    private class NetEaseDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerClosed(View arg0) {
            actionBarDrawerToggle.onDrawerClosed(arg0);
        }

        @Override
        public void onDrawerOpened(View arg0) {
            actionBarDrawerToggle.onDrawerOpened(arg0);
        }

        @Override
        public void onDrawerSlide(View arg0, float arg1) {
            actionBarDrawerToggle.onDrawerSlide(arg0, arg1);
        }

        @Override
        public void onDrawerStateChanged(int arg0) {
            actionBarDrawerToggle.onDrawerStateChanged(arg0);
        }
    }
}
