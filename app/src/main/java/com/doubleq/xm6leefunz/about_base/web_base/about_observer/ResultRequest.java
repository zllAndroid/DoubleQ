package com.doubleq.xm6leefunz.about_base.web_base.about_observer;

import java.util.Observable;
import java.util.Observer;

public class ResultRequest extends Observable {
    public String result;

    public ResultRequest() {
    }
    public ResultRequest(String result) {
        this.result = result;
    }
    private static ResultRequest request = null;

    public static ResultRequest getInstance() {
        if (request == null) {
            synchronized (ResultRequest.class) {
                if (request == null) {
                    request = new ResultRequest();
                }
            }

        }
        return request;

    }
    public void postMessage(String content){
        //内容发生改变
        setChanged();
        //通知所有订阅者改变的内容
        notifyObservers(content);
    }
}
