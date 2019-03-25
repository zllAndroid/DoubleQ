package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

public class SideChainActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_side_chain);
//    }

    String userId;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_side_chain;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        Intent intent = getIntent();
        if (intent!=null)
        {
            userId = intent.getStringExtra("userId");
        }

    }

    @Override
    protected boolean isGonesStatus() {
        return true;
    }
}
