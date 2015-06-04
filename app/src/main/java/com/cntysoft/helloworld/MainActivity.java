package com.cntysoft.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    int width;
    int height;
    float density;
    long[] mHits = new long[2];
    int count;

    private WebView innerWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        StringBuffer sb = new StringBuffer();
        TextView textView = (TextView) findViewById(R.id.hello);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        width = display.getWidth();
        height = display.getHeight();
        sb.append("\n getWidth:"+String.valueOf(width));
        sb.append("\n getheight:"+String.valueOf(height));
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        density = displayMetrics.density;
        sb.append("\n widthPixels:"+String.valueOf(width));
        sb.append("\n heightPixels:"+String.valueOf(height));
        sb.append("\n density:"+String.valueOf(density));
        sb.append("\n density:"+String.valueOf(displayMetrics.densityDpi));
        textView.setText(sb.toString());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
                mHits[mHits.length-1] = SystemClock.uptimeMillis();
                System.out.println(mHits[0]);
                if (mHits[0] >= (SystemClock.uptimeMillis()-2000)) {
                    Toast.makeText(MainActivity.this,String.valueOf(mHits[0]),Toast.LENGTH_LONG).show();
                    mHits[0] = 0;
                    mHits[1] = 0;
                }
            }
        });

        innerWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = innerWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        innerWebView.loadUrl("http://www.apkbus.com/android-166155-1-1.html");*/
        setContentView(R.layout.test_layout);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ll.setPadding(0,-300,0,0);
    }
}
