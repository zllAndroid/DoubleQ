package com.mding.chatfeng.about_chat.group_manage;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class GroupDetailActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    @BindView(R.id.group_detail_iv_head)
    ImageView groupDetailIvHead;
    @BindView(R.id.group_detail_iv_qrcode)
    ImageView groupDetailIvQrcode;
    @BindView(R.id.group_detail_tv_name)
    TextView groupDetailTvName;
    @BindView(R.id.group_detail_tv_chatnum)
    TextView groupDetailTvChatnum;
    @BindView(R.id.group_detail_recyc)
    RecyclerView groupDetailRecyc;
    @BindView(R.id.group_detail_cus_lin_manage)
    CusLinearLayout groupDetailCusLinManage;
    @BindView(R.id.group_detail_cus_lin_card)
    CusLinearLayout groupDetailCusLinCard;
    @BindView(R.id.group_detail_cus_lin_notice)
    CusLinearLayout groupDetailCusLinNotice;
    @BindView(R.id.group_detail_tv_notice_content)
    TextView groupDetailTvNoticeContent;
    @BindView(R.id.group_detail_cus_lin_chat_history)
    CusLinearLayout groupDetailCusLinChatHistory;
    @BindView(R.id.group_detail_cus_lin_clear_chat_history)
    CusLinearLayout groupDetailCusLinClearChatHistory;
    @BindView(R.id.group_detail_lin_main)
    LinearLayout groupDetailLinMain;

    boolean isGrouper = true;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_detail;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        initView();
        initCusLinearLayout();
    }

    private void initView() {
        includeTopTvTital.setText(getResources().getString(R.string.group_details_title));
//        includeTopTvTital.setText("群聊资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        groupDetailRecyc.setHasFixedSize(true);
        groupDetailRecyc.setNestedScrollingEnabled(false);
        groupDetailRecyc.setLayoutManager(new GridLayoutManager(this, 5));

        initRightPop();
    }

    private void initCusLinearLayout() {
        //群管理
        initGroupManage();
        //群名片
        initGroupCard();
        //群公告
        initNotice();
        //聊天记录
        initChatHistory();
        //清空聊天记录
        initClearChatHistory();
    }

    //群管理
    private void initGroupManage() {
        groupDetailCusLinManage.setLinGreyBacVisible(true);
        groupDetailCusLinManage.setViewLineVisible(false);
        groupDetailCusLinManage.setTvTitle(getResources().getString(R.string.group_details_manage));
        groupDetailCusLinManage.setLinImgLogoVisible(false);

    }

    //群名片
    private void initGroupCard() {
        groupDetailCusLinCard.setTvTitle(getResources().getString(R.string.group_details_card));
        groupDetailCusLinCard.setTvContent("密密酱");
        groupDetailCusLinCard.setLinImgLogoVisible(false);
    }

    //群公告
    private void initNotice() {
        groupDetailCusLinNotice.setLinImgLogoVisible(false);
        groupDetailCusLinNotice.setTvTitle(getResources().getString(R.string.group_details_notice));
    }

    //聊天记录
    private void initChatHistory() {
        groupDetailCusLinChatHistory.setLinImgLogoVisible(false);
        groupDetailCusLinChatHistory.setTvTitle(getResources().getString(R.string.group_details_chat_history));
        groupDetailCusLinChatHistory.setTvContent(getResources().getString(R.string.group_details_chat_history_item));
        groupDetailCusLinChatHistory.setTvContentTextColor(getResources().getColor(R.color.greye5));
    }

    //清空聊天记录
    private void initClearChatHistory() {
        groupDetailCusLinClearChatHistory.setLinImgLogoVisible(false);
        groupDetailCusLinClearChatHistory.setTvTitle(getResources().getString(R.string.group_details_clear_chat_history));
    }

    View mView;
    View mView_share;
    private void initRightPop() {
        mView = LayoutInflater.from(this).inflate(R.layout.pop_group_details_esc, null);
        mView.findViewById(R.id.pop_btn_esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了屏蔽");
                if (popWindow != null)
                    popWindow.dissmiss();
                if (isGrouper) {
//                    IntentUtils.JumpToHaveTwo(GrouperEscActivity.class, AppConfig.GROUPER_ESC, groupId, AppConfig.GROUPER_NAME, groupName);
                    IntentUtils.JumpTo(ExitOrTransferGroupActivity.class);
//                    AppManager.getAppManager().finishActivity(GroupDetailActivity.this);
                } else {
                    DialogUtils.showDialog(getResources().getString(R.string.group_details_dialog_is_sure_exit), new DialogUtils.OnClickSureListener() {
                        //                    DialogUtils.showDialog("是否确认退出群聊", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
//                            sendWebHaveDialog(SplitWeb.getSplitWeb().outGroupChat(groupId), "正在退出...", "退出成功");
                            DialogUtils.showDialog(getResources().getString(R.string.group_details_dialog_exit_succeed));
//                            DialogUtils.showDialog("退出成功");
                        }
                    });
                }
            }
        });
        mView.findViewById(R.id.pop_btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
                showSharePopWindow();

            }
        });
        mView.findViewById(R.id.pop_btn_jvbao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
                IntentUtils.JumpTo(ReportActivity.class);

            }
        });
        mView.findViewById(R.id.pop_btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();


            }
        });
    }
    // 显示分享popupWindow
    private void showSharePopWindow() {
        mView_share = LayoutInflater.from(this).inflate(R.layout.pop_group_details_share, null);
        if (popWindow_share == null) {
            popWindow_share = new CustomPopWindow.PopupWindowBuilder(GroupDetailActivity.this)
                    .setView(mView_share)
                    .setFocusable(true)
                    .setOutsideTouchable(true)
                    .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    .setAnimationStyle(0) // 添加自定义显示和消失动画
                    .create()
                    .showAsDropDown(groupDetailLinMain, 0, 0);

            mView_share.findViewById(R.id.share_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popWindow_share != null)
                        popWindow_share.dissmiss();
                    ToastUtil.isDebugShow("点击了分享");

                }
            });
        }
        else
            popWindow.showAsDropDown(groupDetailLinMain, 0, 0);
    }

    CustomPopWindow popWindow;
    CustomPopWindow popWindow_share;
    @OnClick({R.id.include_top_iv_more, R.id.group_detail_iv_head, R.id.group_detail_lin_name, R.id.group_detail_cus_lin_manage, R.id.group_detail_cus_lin_card, R.id.group_detail_cus_lin_notice, R.id.group_detail_cus_lin_chat_history, R.id.group_detail_cus_lin_clear_chat_history, R.id.group_detail_tv_to_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_iv_more:
                if (popWindow == null)
                    popWindow = new CustomPopWindow.PopupWindowBuilder(GroupDetailActivity.this)
                            .setView(mView)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                            .setAnimationStyle(0) // 添加自定义显示和消失动画
                            .create()
                            .showAsDropDown(groupDetailLinMain, 0, 0);
                else
                    popWindow.showAsDropDown(groupDetailLinMain, 0, 0);
                break;
            case R.id.group_detail_iv_head:
                break;
            case R.id.group_detail_lin_name:
                break;
            case R.id.group_detail_cus_lin_manage:
                IntentUtils.JumpTo(ManageGroupActivity.class);
                break;
            case R.id.group_detail_cus_lin_card:
                break;
            case R.id.group_detail_cus_lin_notice:
                break;
            case R.id.group_detail_cus_lin_chat_history:
                break;
            case R.id.group_detail_cus_lin_clear_chat_history:
                break;
            case R.id.group_detail_tv_to_chat:
                break;
        }
    }
}
