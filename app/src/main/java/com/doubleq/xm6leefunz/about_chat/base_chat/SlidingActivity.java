package com.doubleq.xm6leefunz.about_chat.base_chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doubleq.xm6leefunz.about_base.BaseActivity;

public abstract class SlidingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
}
