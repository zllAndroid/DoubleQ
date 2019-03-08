package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_custom.about_top_bar.TopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDiscoverActivity extends BaseActivity {

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_discover);
//    }

    String userId;
    @BindView(R.id.top_bar)
    TopBarLayout topBar;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_my_discover;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");

        }

        topBar.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
