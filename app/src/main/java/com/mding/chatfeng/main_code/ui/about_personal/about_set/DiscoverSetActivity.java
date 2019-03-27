package com.mding.chatfeng.main_code.ui.about_personal.about_set;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.DongTaiSetActivity;
import com.mding.chatfeng.about_base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DiscoverSetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
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
        incluTvRight.setVisibility(View.VISIBLE);
        includeTopTvTital.setText("朋友圈设置");
        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }
    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
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
