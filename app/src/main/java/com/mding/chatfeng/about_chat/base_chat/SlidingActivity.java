package com.mding.chatfeng.about_chat.base_chat;

import android.os.Bundle;

import com.mding.chatfeng.about_base.BaseActivity;

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
    public boolean isSupportSwipeBack() {
        return false;
    }
}
