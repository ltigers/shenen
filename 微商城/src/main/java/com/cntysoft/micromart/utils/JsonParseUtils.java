package com.cntysoft.micromart.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/26.
 */
public class JsonParseUtils {

    public static <T> T getClassFromString(String jsonString,Class<T> cls){
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString,cls);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> getListClassFromString(String jsonString,Class<T> cls){
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString,new TypeToken<List<T>>(){

            }.getType()) ;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getListStringFromString(String jsonString){
        List<String> list = new ArrayList<String>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString,new TypeToken<List<String>>(){

            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Map<String,Object>> getListMapFromString(String jsonString){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString,new TypeToken<List<Map<String,Object>>>(){

            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }
}
