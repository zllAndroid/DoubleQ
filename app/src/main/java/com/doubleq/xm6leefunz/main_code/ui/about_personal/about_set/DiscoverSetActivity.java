package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.DongTaiSetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverSetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_discover_set);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_discover_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("朋友圈设置");
        incluTvRight.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.discover_lin_gifPlay, R.id.discover_lin_hideMine, R.id.discover_lin_hideHis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.discover_lin_gifPlay:
                IntentUtils.JumpTo(DongTaiSetActivity.class);
                break;
            case R.id.discover_lin_hideMine:
                break;
            case R.id.discover_lin_hideHis:
                break;
        }
    }
}
