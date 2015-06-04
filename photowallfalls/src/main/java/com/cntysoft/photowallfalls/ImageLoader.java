package com.cntysoft.photowallfalls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2015/5/28.
 */
public class ImageLoader {

    private static LruCache<String,Bitmap> mMemoryCache;

    private static ImageLoader mImageLoader;

    private ImageLoader(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public static ImageLoader getInstance(){
        if(mImageLoader == null){
            mImageLoader = new ImageLoader();
        }
        return mImageLoader;
    }

    public void addBitmapToMemoryCache(String key,Bitmap bitmap){

        if(getBitmapFromMemoryCache(key) == null){
            mMemoryCache.put(key,bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key){

        return mMemoryCache.get(key);
    }

    public static int calculateSampleSize(BitmapFactory.Options options,int reqWidth){
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(width > reqWidth){
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String pathName,int reqWidth){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName,options);
        options.inSampleSize = calculateSampleSize(options,reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName,options);
    }
}
