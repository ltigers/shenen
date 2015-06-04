package com.cntysoft.micromart.activity;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by Administrator on 2015/5/22.
 * <application
 *  android:icon="@drawable/icon"
 *  android:label="@string/app_name"
 *  android:name="your_package_name_here.MyApplication">
 */
public class ExitApplication extends Application {

    private static Stack<Activity> activityStack;
    private static ExitApplication exitApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        exitApplication = this;
    }

    public static ExitApplication getInstance(){
        return exitApplication;
    }

    public void addAvtivity(Activity activity){
        if(activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }
    public void finishActivity(Activity activity){
        if(activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls){
        for(Activity activity : activityStack){
            if(activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity(){
        for(int i = 0, size = activityStack.size();i < size; i++){
            if(activityStack.get(i) != null){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        System.exit(0);
    }

    public void exitApp(){
        try{
            finishAllActivity();
        }catch(Exception e){

        }
    }
}
