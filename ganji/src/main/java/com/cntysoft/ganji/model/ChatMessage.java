package com.cntysoft.ganji.model;

import android.graphics.Bitmap;

/**
 * @Author：Tiger on 2015/4/18 16:45
 * @Email: ielxhtiger@163.com
 */
public class ChatMessage {
    
    private Bitmap icon;
    private String title;
    private String time;
    private String content;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
