package com.mding.chatfeng.about_chat.group_manage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportOthersActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.report_others_cus_lin_to_chatfeng)
    CusLinearLayout reportOthersCusLinToChatfeng;
    @BindView(R.id.report_others_cus_lin_to_blocked_list)
    CusLinearLayout reportOthersCusLinToBlockedList;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_report_others;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        includeTopTvTital.setText(getResources().getString(R.string.report_others_title));
        initCusLinearLayout();
    }

    private void initCusLinearLayout() {

        //提交给信风团队审核处理
        init2ChatFeng();
        //将此账号加入屏蔽名单
        init2BlockedList();
    }

    private void init2ChatFeng() {
        reportOthersCusLinToChatfeng.setTvTitle(getResources().getString(R.string.report_others_to_chat_feng));
        reportOthersCusLinToChatfeng.setLinImgLogoVisible(false);
        reportOthersCusLinToChatfeng.setViewLineVisible(false);
    }

    private void init2BlockedList() {
        reportOthersCusLinToBlockedList.setTvTitle(getResources().getString(R.string.report_others_to_blocked_list));
        reportOthersCusLinToBlockedList.setLinImgLogoVisible(false);

    }

    @OnClick({R.id.report_others_cus_lin_to_chatfeng, R.id.report_others_cus_lin_to_blocked_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.report_others_cus_lin_to_chatfeng:

                break;
            case R.id.report_others_cus_lin_to_blocked_list:

                break;
        }
    }
}
