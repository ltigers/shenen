package com.cntysoft.micromart.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.cntysoft.micromart.R;
import com.cntysoft.micromart.utils.CommonUtils;


public class SplashActivity extends Activity {

    TextView textView;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp =  getSharedPreferences(getString(R.string.micromart_config),MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.version);
        textView.setText(CommonUtils.getVersion(this));
        final boolean first = sp.getBoolean("first",true);
        delay(first,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void goActivity(Context context, Class<?> cls,boolean end){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
        if(end){
            finish();
        }
    }

    private void delay(final boolean first,final Context context){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(first){
                    goActivity(context,LoginActivity.class,true);
                }else{
                    goActivity(context,HomeActivity.class,true);
                }
            }
        }.start();
    }

}
