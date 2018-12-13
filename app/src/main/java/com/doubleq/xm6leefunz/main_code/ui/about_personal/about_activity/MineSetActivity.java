package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.ChangePwdActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.CountAndSafeActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.LaBlackActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.NewsRemindActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.ShareSetActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.YinSiActivity;
import com.doubleq.xm6leefunz.main_code.about_login.LoginActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.DataCleanManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
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
    @BindView(R.id.set_tv_cache)
    TextView setTvCache;
    @BindView(R.id.set_tv_versition)
    TextView setTvVersition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_mine_set;
    }
    RealmHomeHelper realmHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();

        realmHelper = new RealmHomeHelper(this);
        includeTopTvTital.setText("设置");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            setTvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String versionName = HelpUtils.getLocalVersionName();
            setTvVersition.setText("v" + versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    清理缓存
    private void cleanCaChe() {
        ACache mCache = ACache.get(this);
        String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
        DataCleanManager.clearAllCache(MyApplication.getAppContext());
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            setTvCache.setText(totalCacheSize);
            ToastUtil.show("清理缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ACache mCache2 = ACache.get(this);
        mCache2.put(AppAllKey.TOKEN_KEY, asString);
        String asString2 = mCache.getAsString(AppAllKey.TOKEN_KEY);
    }

    @OnClick({R.id.set_lin_pingbi,R.id.set_lin_share, R.id.set_lin_count, R.id.set_lin_yinsi, R.id.set_lin_message, R.id.set_lin_clear_cache, R.id.set_lin_versition, R.id.set_lin_about_me, R.id.set_btn_esc})
    public void onViewClicked(View view) {
        switch (view.getId()) {

//            打开  名片分享  界面
            case R.id.set_lin_share:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(ShareSetActivity.class);
                break;

//                打开  密码设置  界面
            case R.id.set_lin_count:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(CountAndSafeActivity.class);
                break;

//                打开 屏蔽设置  界面
            case R.id.set_lin_pingbi:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(LaBlackActivity.class);
                break;

//                打开隐私设置界面
            case R.id.set_lin_yinsi:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(YinSiActivity.class);
                break;

//                打开消息提醒界面
            case R.id.set_lin_message:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(NewsRemindActivity.class);
                break;

//                清理缓存
            case R.id.set_lin_clear_cache:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (!setTvCache.getText().toString().equals("0KB")) {
                        DialogUtils.showDialog("确认清理" + setTvCache.getText().toString() + "缓存？", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                                cleanCaChe();
                            }
                        });
                    } else {
                        ToastUtil.show("暂无缓存");
                    }
                }
                break;
//                版本升级
            case R.id.set_lin_versition:
                ToastUtil.show("已经是最新版本");
                break;

//                关于我们界面
            case R.id.set_lin_about_me:
                ToastUtil.show("这里是关于我们");
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ShareSetActivity.class);
                break;

//                退出账号    回到登录界面
            case R.id.set_btn_esc:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    DialogUtils.showDialog("确定退出本应用?", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
//                            realmHelper.deleteAll();
                            SplitWeb.USER_ID="";
                            AppManager.getAppManager().finishAllActivity();
                            Intent intent_recharge = new Intent(MineSetActivity.this, LoginActivity.class);
                            startActivity(intent_recharge);
                            overridePendingTransition(0,0);
                            ACache.get(MineSetActivity.this).clear();
                            SPUtils.clear(MineSetActivity.this);
//                            if (AppStartActivity.serviceConnection!=null)
//                            unbindService(AppStartActivity.serviceConnection);

                        }
                    });
                }
                break;
        }
    }
}
