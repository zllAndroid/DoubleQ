package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.os.Bundle;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

import butterknife.BindView;


public class CopyActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("修改密码");
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_pwd;
    }
}
