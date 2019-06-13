package com.mding.chatfeng.about_chat.group_manage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.about_utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends BaseActivity {

    @BindView(R.id.report_cus_lin_unhealthy_news)
    CusLinearLayout reportCusLinUnhealthyNews;
    @BindView(R.id.report_cus_lin_cheat)
    CusLinearLayout reportCusLinCheat;
    @BindView(R.id.report_cus_lin_gamble)
    CusLinearLayout reportCusLinGamble;
    @BindView(R.id.report_cus_lin_ads)
    CusLinearLayout reportCusLinAds;
    @BindView(R.id.report_cus_lin_others)
    CusLinearLayout reportCusLinOthers;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_report;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText(getResources().getString(R.string.report_title));
        initCusLinearLayout();
    }

    private void initCusLinearLayout() {
        //该群发布不良信息
        initCusLinUnhealthyNews();
        //该群存在欺诈骗钱的行为
        initCusLinCheat();
        //该群存在聚众赌博行为
        initCusLinGambling();
        //该群发布广告骚扰信息
        initCusLinAds();
        //其他
        initCusLinOthers();

    }

    //该群发布不良信息
    private void initCusLinUnhealthyNews() {
        reportCusLinUnhealthyNews.setTvTitle(getResources().getString(R.string.report_unhealthy_news));
        reportCusLinUnhealthyNews.setLinImgLogoVisible(false);
        reportCusLinUnhealthyNews.setViewLineMatchVisible(false);
    }

    //该群存在欺诈骗钱的行为
    private void initCusLinCheat() {
        reportCusLinCheat.setTvTitle(getResources().getString(R.string.report_cheat));
        reportCusLinCheat.setLinImgLogoVisible(false);

    }

    //该群存在聚众赌博行为
    private void initCusLinGambling() {
        reportCusLinGamble.setTvTitle(getResources().getString(R.string.report_gamble));
        reportCusLinGamble.setLinImgLogoVisible(false);
    }

    //该群发布广告骚扰信息
    private void initCusLinAds() {
        reportCusLinAds.setTvTitle(getResources().getString(R.string.report_too_many_ads));
        reportCusLinAds.setLinImgLogoVisible(false);
    }

    //其他
    private void initCusLinOthers() {
        reportCusLinOthers.setTvTitle(getResources().getString(R.string.report_others));
        reportCusLinOthers.setLinImgLogoVisible(false);

    }


    @OnClick({R.id.report_cus_lin_unhealthy_news, R.id.report_cus_lin_cheat, R.id.report_cus_lin_gamble, R.id.report_cus_lin_ads, R.id.report_cus_lin_others})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //该群发布不良信息
            case R.id.report_cus_lin_unhealthy_news:

                break;
            //该群存在欺诈骗钱的行为
            case R.id.report_cus_lin_cheat:

                break;
            //该群存在聚众赌博行为
            case R.id.report_cus_lin_gamble:

                break;
            //该群发布广告骚扰信息
            case R.id.report_cus_lin_ads:

                break;
            //其他
            case R.id.report_cus_lin_others:
                IntentUtils.JumpTo(ReportOthersActivity.class);
                break;
        }
    }
}
