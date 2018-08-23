package com.doubleq.xm6leefunz.main_code.about_fragment.about_personal.about_activity;

import android.os.Bundle;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;


public class MyAccountActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_account);
//
//        TextView tv_title;
//        tv_title = findViewById(R.id.include_ac_tv_title);
//        tv_title.setText("我的账号");
//        ImageView img_back;
//        img_back = findViewById(R.id.include_ac_img_back);
//        img_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppManager.getAppManager().finishActivity();
//            }
//        });
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_my_account;
    }
}
