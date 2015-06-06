package com.cntysoft.kelecms;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setTitle("新闻");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new NewsFragment(),"news").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_right:
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }else if(drawerLayout.isDrawerOpen(Gravity.LEFT) || !drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }else if(drawerLayout.isDrawerOpen(Gravity.RIGHT) || !drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
