package com.mding.chatfeng.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.cus_data_group.RealmGroupChatHelper;
import com.mding.chatfeng.about_custom.about_cus_dialog.DialogExitUtils;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_utils.VersionCheckUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmChatHelper;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.about_login.LoginActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.CountAndSafeActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.DiscoverSetActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.LaBlackActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.NewsRemindActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.ShareSetActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_set.YinSiActivity;
import com.mding.core.pushservice.WsChannelService;
import com.mding.workservice.ChatService;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.DataCleanManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.set_cus_lin_share)
    CusLinearLayout setCusLinShare;
    @BindView(R.id.set_lin_count)
    CusLinearLayout setLinCount;
    @BindView(R.id.set_lin_yinsi)
    CusLinearLayout setLinYinsi;
    @BindView(R.id.set_lin_message)
    CusLinearLayout setLinMessage;
    @BindView(R.id.set_lin_pingbi)
    CusLinearLayout setLinPingbi;
    @BindView(R.id.set_lin_discover)
    CusLinearLayout setLinDiscover;
    @BindView(R.id.set_lin_clear_cache)
    CusLinearLayout setLinClearCache;
    @BindView(R.id.set_lin_version)
    CusLinearLayout setLinVersion;
    @BindView(R.id.set_lin_about_me)
    CusLinearLayout setLinAboutMe;

    @Override
    public int getLayoutView() {
        return R.layout.activity_mine_set;
    }

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    String userPhone;
    String totalCacheSize;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        SplitWeb.getSplitWeb().IS_SET_ACTIVITY = "1";
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        includeTopTvTital.setText("设置");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        Intent intent = getIntent();
        if (intent != null) {
            userPhone = intent.getStringExtra("phone");
        }
        initLinearLaout();
    }

    private void initLinearLaout() {
//        名片分享与设置
        initShare();

//        帐号与安全
        initSafeCount();
//        隐私设置
        initYinSi();
//        消息提醒
        initMsgNotify();
//        屏蔽设置
        initPingBi();
//        朋友圈设置
        initDiscover();

//        清理缓存
        initCache();
//        检查更新
        initUpdateCheck();
//        关于我们
        initAboutUs();
    }

    //        名片分享与设置
    private void initShare() {
        setCusLinShare.setViewLineVisible(false);
        setCusLinShare.setImgLogo(getResources().getDrawable(R.drawable.mine_share));
        setCusLinShare.setTvTitle("名片分享设置");
    }
    //        帐号与安全
    private void initSafeCount() {
        setLinCount.setLinGreyBacVisible(true);
        setLinCount.setViewLineVisible(false);
        setLinCount.setImgLogo(getResources().getDrawable(R.drawable.set_count));
        setLinCount.setTvTitle("帐号与安全");
    }

    //        隐私设置
    private void initYinSi() {
        setLinYinsi.setImgLogo(getResources().getDrawable(R.drawable.set_yinsi));
        setLinYinsi.setTvTitle("隐私设置");
    }

    //        消息提醒
    private void initMsgNotify() {
        setLinMessage.setImgLogo(getResources().getDrawable(R.drawable.set_message));
        setLinMessage.setTvTitle("消息提醒");
    }

    //        屏蔽设置
    private void initPingBi() {
        setLinPingbi.setImgLogo(getResources().getDrawable(R.drawable.set_pingbi));
        setLinPingbi.setTvTitle("屏蔽设置");
    }

    //        朋友圈设置
    private void initDiscover() {
        setLinDiscover.setImgLogo(getResources().getDrawable(R.drawable.set_pingbi));
        setLinDiscover.setTvTitle("朋友圈设置");
    }

    //        清理缓存
    private void initCache() {
        setLinClearCache.setLinGreyBacVisible(true);
        setLinClearCache.setViewLineVisible(false);
        setLinClearCache.setImgLogo(getResources().getDrawable(R.drawable.set_clean));
        setLinClearCache.setTvTitle("" + "清理缓存");
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(MineSetActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLinClearCache.setTvContent(totalCacheSize);
    }

    //        检查更新
    private void initUpdateCheck() {
        setLinVersion.setImgLogo(getResources().getDrawable(R.drawable.set_upda));
        setLinVersion.setTvTitle("检查更新");
        String versionName = HelpUtils.getLocalVersionName();
        setLinVersion.setTvContent("v" + versionName);
    }
    //        关于我们
    private void initAboutUs() {
        setLinAboutMe.setImgLogo(getResources().getDrawable(R.drawable.set_aboutme));
        setLinAboutMe.setTvTitle("关于我们");
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        if (SplitWeb.getSplitWeb().IS_SET_ACTIVITY.equals("1")) {
            String method = HelpUtils.backMethod(responseText);
            switch (method) {
                case "kickUid":
                    Log.e("kickUid", "----------------------kickUid-----------------");
                    break;
            }
        }
    }

    //    清理缓存
    private void cleanCaChe() {
        try {
            // 群聊删除聊天记录
            RealmGroupChatHelper realmGroupChatHelper = new RealmGroupChatHelper(MineSetActivity.this);
            realmGroupChatHelper.deleteAll();
            // 私聊删除聊天记录
            RealmChatHelper realmChatHelper = new RealmChatHelper(MineSetActivity.this);
            realmChatHelper.deleteAll();

            ToastUtil.show("清理缓存成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.set_lin_pingbi, R.id.set_cus_lin_share, R.id.set_lin_count, R.id.set_lin_yinsi, R.id.set_lin_message, R.id.set_lin_discover,
            R.id.set_lin_clear_cache, R.id.set_lin_version, R.id.set_lin_about_me, R.id.set_btn_esc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            打开  名片分享  界面
            case R.id.set_cus_lin_share:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(ShareSetActivity.class);
                break;

//                打开  帐号与安全  界面
            case R.id.set_lin_count:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(CountAndSafeActivity.class);
                break;

//                打开 隐私设置 界面
            case R.id.set_lin_yinsi:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(YinSiActivity.class);
                break;

//                打开消息提醒界面
            case R.id.set_lin_message:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(NewsRemindActivity.class);
                break;

//                打开 屏蔽设置  界面
            case R.id.set_lin_pingbi:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(LaBlackActivity.class);
                break;

//                打开 朋友圈设置 界面
            case R.id.set_lin_discover:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(DiscoverSetActivity.class);
                break;

//                清理缓存
            case R.id.set_lin_clear_cache:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    String tvContent = setLinClearCache.getTvContent();
                    if (!tvContent.equals("0KB")) {
                        DialogUtils.showDialog("确认清理" + tvContent + "缓存？", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
//                                        cleanCaChe();
                                setLinClearCache.setTvContent("0KB");
                            }
                        });
                    } else {
                        ToastUtil.show("暂无缓存");
                    }
                }
                break;
//                检查更新
            case R.id.set_lin_version:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    //        版本更新
                    try {
                        int localVersion = HelpUtils.getLocalVersion(this);
//                    sendWeb(SplitWeb.getSplitWeb().appUpdate("" + localVersion));
                        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
                        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
                            @Override
                            public void onNetSuccess(String result) {
                                String isSucess = HelpUtils.HttpIsSucess(result);
                                if (isSucess.equals(AppConfig.CODE_OK))
                                    VersionCheckUtils.initUpdata(result, false);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

//                关于我们界面
            case R.id.set_lin_about_me:
                ToastUtil.show("这里是关于我们\n敬请期待！");
                break;

//                退出帐号    回到登录界面
            case R.id.set_btn_esc:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    DialogExitUtils.showDialog("仅退出帐号", "退出并删除帐号信息", new DialogExitUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure(String checkingId) {
                            switch (checkingId) {
                                case "1":
                                    WsChannelService.isBind = true;
                                    BaseApplication.isHomeMsgFragment = true;
                                    Intent intent2 = new Intent(MineSetActivity.this, ChatService.class);
                                    stopService(intent2);// 关闭服务
                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
                                    SplitWeb.getSplitWeb().USER_ID = "";
                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
                                    ACache.get(MineSetActivity.this).clear();
                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
                                    AppManager.getAppManager().onAppExit(MineSetActivity.this);
                                    IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", userPhone);
                                    overridePendingTransition(0, 0);

                                    break;
                                case "2":
                                    WsChannelService.isBind = true;
                                    BaseApplication.isHomeMsgFragment = true;
                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
                                    SplitWeb.getSplitWeb().USER_ID = "";
                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
                                    ACache.get(MineSetActivity.this).clear();
                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
                                    SPUtils.clear(MineSetActivity.this);
                                    AppManager.getAppManager().finishAllActivity();
                                    Intent intent = new Intent(MineSetActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                    realmHelper.deleteAll();
                                    realmChatHelper.deleteAll();
                                    break;
                            }

                        }
                    });
                }
                break;
        }
    }
}