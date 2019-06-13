package com.mding.chatfeng.about_chat.group_manage;

import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;

import butterknife.BindView;

public class ManageGroupActivity extends BaseActivity {

    @BindView(R.id.manage_group_cus_lin_edit_data)
    CusLinearLayout maganeGroupCusLinEditData;
    @BindView(R.id.manage_group_cus_lin_upgrade)
    CusLinearLayout maganeGroupCusLinUpgrade;
    @BindView(R.id.manage_group_cus_lin_add_group_way)
    CusLinearLayout maganeGroupCusLinAddGroupWay;
    @BindView(R.id.manage_group_cus_lin_transfer_group)
    CusLinearLayout maganeGroupCusLinTransferGroup;
    @BindView(R.id.manage_group_cus_lin_administrator)
    CusLinearLayout maganeGroupCusLinAdministrator;
    @BindView(R.id.manage_group_cus_lin_find_way)
    CusLinearLayout maganeGroupCusLinFindWay;

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    boolean isGrouper = true;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_manage_group;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        initView();
        initLinearLayout();
    }

    private void initView() {
        includeTopTvTital.setText(getResources().getString(R.string.manage_group_title));
    }

    private void initLinearLayout() {

        //群编辑资料
        initEditData();
        //群升级
        initGroupUpgrade();

        //加群方式
        initAddGroupWay();
        if (isGrouper) {
            //转让群主
            initTransferGrouper();
            //管理员
            initAdministrator();
        }
        //查找方式
        initFindWay();
    }

    //群编辑资料
    private void initEditData() {
        maganeGroupCusLinEditData.setLinImgLogoVisible(false);
        maganeGroupCusLinEditData.setViewLineMatchVisible(false);
        maganeGroupCusLinEditData.setTvTitle(getResources().getString(R.string.manage_group_edit_data));
//        maganeGroupCusLinEditData.setTvTitle("群编辑资料");
    }

    //群升级
    private void initGroupUpgrade() {
        maganeGroupCusLinUpgrade.setLinImgLogoVisible(false);
        maganeGroupCusLinUpgrade.setTvTitle(getResources().getString(R.string.manage_group_upgrade));
//        maganeGroupCusLinEditData.setTvTitle("群编辑资料");
    }

    //加群方式
    private void initAddGroupWay() {
        maganeGroupCusLinAddGroupWay.setLinGreyBacVisible(true);
        maganeGroupCusLinAddGroupWay.setViewLineVisible(false);
        maganeGroupCusLinAddGroupWay.setLinImgLogoVisible(false);
        maganeGroupCusLinAddGroupWay.setTvTitle(getResources().getString(R.string.manage_group_add_group_way));
    }

    //转让群主
    private void initTransferGrouper() {
        maganeGroupCusLinTransferGroup.setVisibility(View.VISIBLE);
        maganeGroupCusLinTransferGroup.setLinImgLogoVisible(false);
        maganeGroupCusLinTransferGroup.setTvTitle(getResources().getString(R.string.manage_group_transfer_grouper));

    }

    //管理员
    private void initAdministrator() {
        maganeGroupCusLinAdministrator.setVisibility(View.VISIBLE);
        maganeGroupCusLinAdministrator.setLinImgLogoVisible(false);
        maganeGroupCusLinAdministrator.setTvTitle(getResources().getString(R.string.manage_group_administrator));

    }

    //查找方式
    private void initFindWay() {
        maganeGroupCusLinFindWay.setLinImgLogoVisible(false);
        maganeGroupCusLinFindWay.setTvTitle(getResources().getString(R.string.manage_group_find_way));
        maganeGroupCusLinFindWay.setTvContent(getResources().getString(R.string.find_way_allow));
        maganeGroupCusLinFindWay.setTvContentTextColor(getResources().getColor(R.color.grey999));
    }
}
