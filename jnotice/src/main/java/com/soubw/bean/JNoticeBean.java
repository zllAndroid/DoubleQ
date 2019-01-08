package com.soubw.bean;

import java.io.Serializable;

/**
 * author：WX_JIN
 * email:wangxiaojin@soubw.com
 */
public class JNoticeBean implements Serializable {

    private String name;
    private String text;
    private String time;
    private String url;
    private int img;
    private int count;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public JNoticeBean(int count) {
        this.count = count;
    }
    public JNoticeBean(int count,String name,String text,int img) {
        this.count = count;
        this.name = name;
        this.text = text;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
