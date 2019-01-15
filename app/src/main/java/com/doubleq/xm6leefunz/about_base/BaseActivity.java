package com.doubleq.xm6leefunz.about_base;

import com.doubleq.xm6leefunz.about_chat.base_chat.SlidingLayout;

public class BaseActivity extends BaseActivityForResult  {

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        if (isSupportSwipeBack()) {
            SlidingLayout rootView = new SlidingLayout(this);
//            rootView.setBackgroundColor(getResources().getColor(R.color.trans));
            rootView.bindActivity(this);
        }
    }

    protected boolean isSupportSwipeBack() {
        return true;
    }

}