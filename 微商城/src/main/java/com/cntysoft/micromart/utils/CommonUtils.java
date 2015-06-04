package com.cntysoft.micromart.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/5/22.
 */
public class CommonUtils {

    public static String getVersion(Context context){
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    public static String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return sb.toString();
    }

    public static int dip2px(Context context, int dip){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dip * density + 0.5f);
    }

    public static int px2dip(Context context, int px){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(px / density + 0.5f);
    }
}
