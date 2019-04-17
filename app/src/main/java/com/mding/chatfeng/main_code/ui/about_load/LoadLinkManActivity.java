package com.mding.chatfeng.main_code.ui.about_load;

import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_utils.about_file.HeadFileUtils;
import com.mding.chatfeng.main_code.mains.MainActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataFriendRelation;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataGroup;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendRelationHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmGroupHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataLinkManList;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.io.File;
import java.util.List;

import app.dinus.com.loadingdrawable.LoadingView;
import butterknife.BindView;

/**
 * 项目：DoubleQ
 * 文件描述：第一次登陆成功后预加载联系人数据界面
 * 作者：zll
 */
public class LoadLinkManActivity extends BaseActivity implements LoadView {

    @BindView(R.id.electric_fan_view)
    LoadingView electricFanView;
    LoadPresenter loadPresenter;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        loadPresenter = new LoadPresenter(LoadLinkManActivity.this, this, new LoadInteractor());
        loadPresenter.loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_load_data;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    @Override
    protected void onDestroy() {
        loadPresenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void navigateToHome() {
        Log.e("navigateToHome","----------------------------------------------------"+AppConfig.IS_LOGIN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (electricFanView!=null)
                electricFanView.stopNestedScroll();
        }
        IntentUtils.JumpFinishToHaveOneBoolean(LoadLinkManActivity.this,MainActivity.class,AppConfig.IS_LOGIN,true);
        overridePendingTransition(0,0);
    }

    @Override
    public void requestError() {
        DialogUtils.showDialog("请求失败,请退出重试", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                AppManager.getAppManager().finishActivity(LoadLinkManActivity.this);
            }
        });
//        ToastUtil.show("请求失败,请退出重试");
    }
}
