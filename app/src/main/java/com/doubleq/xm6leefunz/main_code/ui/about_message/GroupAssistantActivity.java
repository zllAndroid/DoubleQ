package com.doubleq.xm6leefunz.main_code.ui.about_message;

import android.os.Bundle;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

import butterknife.BindView;


public class GroupAssistantActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群助手");
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_assistant;
    }
}
