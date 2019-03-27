package com.mding.chatfeng.about_base.web_base.about_observer;

import java.util.Observable;

public class ChangeData extends Observable {
    public void postNewArticle(String content){
        //内容发生改变
        setChanged();
        //通知所有订阅者改变的内容
        notifyObservers(content);
    }
}
