package com.mding.chatfeng.main_code.about_login;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.mding.model.DataLogin;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.NetReceiver;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.about_immersive.StatusBarUtil;
import com.mding.chatfeng.main_code.mains.MainActivity;
import com.mding.chatfeng.main_code.mains.top_pop.WindowService;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import site.gemus.openingstartanimation.OpeningStartAnimation;
/**
 * 项目：DoubleQ
 * 文件描述：欢迎页面（APP启动界面）
 * 作者：zll
 */
@RuntimePermissions
public class AppStartActivity extends BaseLogin {

    Timer timer = null;
    OpeningStartAnimation openingStartAnimation;
    @Override
    protected void initBaseView() {
        super.initBaseView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            AppStartActivityPermissionsDispatcher.needWithCheck(this);
//            need();
        else
            need();
    }
    private WindowService.MyBinder mybinder;

    public class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mybinder = (WindowService.MyBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        if (mybinder != null) {
            if (mybinder.isMiUI8) {
//                mybinder.hidwindowMiUI8();
            } else {
                mybinder.hidFloatingWindow();
            }
        }
    }
    public static MyServiceConnection serviceConnection;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showFloatingWindow() {
        Intent service = new Intent(AppStartActivity.this, WindowService.class);

        if (mybinder != null&& !mybinder.isMiUI8) {
            mybinder.showFloatingWindow();
        } else {
            startService(service);
            bindService(service, serviceConnection, Service.BIND_AUTO_CREATE);
            if (mybinder!=null) {
                mybinder.showFloatingWindowMiUI8();
            }
//            MiExToast miToast = new MiExToast(getApplicationContext());
//            miToast.setDuration(MiExToast.LENGTH_ALWAYS);
//            miToast.show();
        }
//        startActivity(service.setClass(AppStartActivity.this, MainActivity.class));
    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_app_start;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                showFloatingWindow();
            }
            initCaChe();
        }
    };

    @Override
    public void initStateBar() {
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
    @Override
    public boolean isGonesStatus() {
        return true;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    void onLoginSuccees(String mLoginModel) {
    }
    @Override
    void onLoginFail(String mLoginModel) {
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mCache==null)
                mCache=  ACache.get(this);
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {
                MyLog.e("unbindService","-----------un----------------->>appstartActivity");
                unbindService(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (timer != null)
            timer.cancel();

    }
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void need() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 1500);
        }
    }
    private ACache mCache;
    private void initCaChe() {
            MyJsonUtils.initBeforeLogin(AppStartActivity.this);
        if (mCache==null)
            mCache = ACache.get(this);
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {
                Log.e("result","token信息"+asString.toString());
                DataLogin dataLogin = JSON.parseObject(asString, DataLogin.class);
                DataLogin.RecordBean record = dataLogin.getRecord();
//                DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
                if (record!=null) {
                    initSetData(AppStartActivity.this,record);
                    String asFriend = mCache.getAsString(AppAllKey.FRIEND_DATA);
                    Log.e("result","FRIEND_DATA信息="+asFriend);
//                    IntentUtils.JumpFinishTo(AppStartActivity.this, MainActivity.class);

                    if (StrUtils.isEmpty(asFriend))
                    {
                        IntentUtils.JumpFinishTo(AppStartActivity.this, LoadLinkManActivity.class);
                    }else {
                        IntentUtils.JumpFinishTo(AppStartActivity.this, MainActivity.class);
                    }
                    MyLog.e("unbindService","------------init---------------->>appstartActivity");
                    init(BaseApplication.getAppContext());
                    overridePendingTransition(0, 0);
                    return;
                }
            }
        }
        IntentUtils.JumpFinishTo(AppStartActivity.this,LoginActivity.class);
        overridePendingTransition(0,0);
    }
    private void initSetData(Context mContext,DataLogin.RecordBean dataLogin) {
        SPUtils.put(mContext, AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        SPUtils.put(mContext,AppAllKey.USER_Token,dataLogin.getUserToken());

        SPUtils.put(mContext, AppConfig.TYPE_NAME,dataLogin.getNickName());
        SPUtils.put(mContext,AppConfig.TYPE_NO,dataLogin.getWxSno());
        SPUtils.put(mContext,AppConfig.TYPE_PHONE,dataLogin.getWxSno());
        SPUtils.put(mContext, AppConfig.User_HEAD_URL,dataLogin.getHeadImg());
        SPUtils.put(mContext,AppConfig.TYPE_SIGN,dataLogin.getPersonaSignature());

        SplitWeb.getSplitWeb().USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.getSplitWeb().MOBILE = dataLogin.getMobile();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().NICK_NAME = dataLogin.getNickName();
        SplitWeb.getSplitWeb().PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().WX_SNO = dataLogin.getWxSno();
        SplitWeb.getSplitWeb().USER_ID = dataLogin.getUserId();
        SplitWeb.getSplitWeb().USER_HEADER = dataLogin.getHeadImg();
        mCache.put(AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        mCache.put(AppAllKey.USER_Token,dataLogin.getUserToken());
    }
    //    @NeedsPermission(value = {Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE}, maxSdkVersion = 16)
//    void OnNeed() {
//    }
//
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppStartActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    PermissionRequest requests =null;
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onshow(final permissions.dispatcher.PermissionRequest request) {
        requests=request;
        DialogUtils.showDialogOne("信任是美好的开始，\n请授权相机等权限，\n让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                request.proceed();
            }
        });
    }
    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onper() {
        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
//                        AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent,0);
//                    startActivity(intent);
                }
            }
        });
    }
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onnesver() {
//        ToastUtil.show("OnNeverAskAgain");
        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
//                    AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent,0);
//                    startActivity(intent);
                }
            }
        });
    }
}
