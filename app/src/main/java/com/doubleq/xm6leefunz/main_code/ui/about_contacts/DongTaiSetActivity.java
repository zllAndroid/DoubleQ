package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.os.Bundle;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DongTaiSetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_dong_tai_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("动态设置");

    }

}
