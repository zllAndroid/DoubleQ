package com.doubleq.xm6leefunz.main_code.about_login;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLogin;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.WindowService;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.Timer;
import java.util.TimerTask;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.RuntimePermissions;
import permissions.dispatcher.PermissionRequest;
import site.gemus.openingstartanimation.OpeningStartAnimation;

@RuntimePermissions
public class AppStartActivity extends BaseActivity {

    Timer timer = null;
    //    @BindView(R.id.appstart_lin)
//    LinearLayout appstartLin;
//    @BindView(R.id.videoview)
//    CustomVideoView videoview;
    boolean isClick =true;
    //创建播放视频的控件对象
//    private CustomVideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    OpeningStartAnimation openingStartAnimation;
    @Override
    protected void initBaseView() {
        super.initBaseView();
//         openingStartAnimation = new OpeningStartAnimation.Builder(this)
//                .setDrawStategy(new RotationDrawStrategy()) //设置动画效果
////                .setAppIcon((Drawable) getResources().getDrawable(R.drawable.tttest)) //设置图标
////                .setAppIcon((Drawable)bmp) //设置图标
//                .setColorOfAppIcon(getResources().getColor(R.color.app_theme)) //设置绘制图标线条的颜色
////                .setAppName() //设置app名称
////                .setColorOfAppName() //设置app名称颜色
//                .setAppStatement("just  do  it") //设置一句话描述
//                .setColorOfAppStatement(getResources().getColor(R.color.app_theme)) // 设置一句话描述的颜色
//                .setAnimationInterval(3000) // 设置动画时间间隔
//                .setAnimationFinishTime(1500) // 设置动画的消失时长
//                .create();
//
//        serviceConnection = new MyServiceConnection();
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
    public int getLayoutView() {
        return R.layout.activity_app_start;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
//            IntentUtils.JumpFinishTo(LoginActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                showFloatingWindow();
            }
//            IntentUtils.JumpFinishTo(MainActivity.class);
//            IntentUtils.JumpTo(LoginActivity.class);
//            AppManager.getAppManager().finishActivity(AppStartActivity.this);
            initCaChe();

            if (isClick) {
//                IntentUtils.JumpFinishTo(MainActivity.class);
//                openingStartAnimation.show(AppStartActivity.this);

            }
        }
    };

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
    //    @OnClick(R.id.appstart_lin)
//    public void onViewClicked() {
//        if (timer != null) {
//            timer.cancel();
//        }
//        isClick=false;
//        IntentUtils.JumpFinishTo(LoginDealActivity.class);
//    }
//    SYSTEM_ALERT_WINDOW
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void need() {
//        openingStartAnimation.show(AppStartActivity.this);
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 1500);
        }

//        ToastUtil.show("权限通过执行");
    }
    private ACache mCache;
    private void initCaChe() {
        mCache = ACache.get(this);
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {
                Log.e("result","token信息"+asString.toString());
                DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
                if (dataLogin!=null) {
                    initSetData(dataLogin);
//               自动登录
//                    sendWeb(SplitWeb.bindUid());
                    IntentUtils.JumpFinishTo(AppStartActivity.this,MainActivity.class);
                    overridePendingTransition(0,0);
                    return;
                }
            }
        }
        IntentUtils.JumpFinishTo(AppStartActivity.this,LoginActivity.class);
        overridePendingTransition(0,0);

    }
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String s = HelpUtils.backMethod(responseText);
//        if (s.equals("bindUid")) {
//            IntentUtils.JumpFinishTo(AppStartActivity.this,MainActivity.class);
//            overridePendingTransition(0,0);
//        }
//    }
//
//    @Override
//    public void errorResult(String s) {
//        super.errorResult(s);
//        String backMethod = HelpUtils.backMethod(s);
//        if (backMethod.equals("bindUid")) {
//            IntentUtils.JumpFinishTo(AppStartActivity.this,LoginActivity.class);
//            overridePendingTransition(0,0);
//        }
//
//    }

    private void initSetData(DataLogin.RecordBean dataLogin) {
        if(!StrUtils.isEmpty(dataLogin.getUserId()))
        SPUtils.put(this,AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        if(!StrUtils.isEmpty(dataLogin.getUserToken()))
            SPUtils.put(this,AppAllKey.USER_Token,dataLogin.getUserToken());
        if(!StrUtils.isEmpty(dataLogin.getMobile()))
            SPUtils.put(this, AppAllKey.SP_LOGIN_ACCOUNT,dataLogin.getMobile());
        SplitWeb.USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.MOBILE = dataLogin.getMobile();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.NICK_NAME = dataLogin.getNickName();
        SplitWeb.PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.WX_SNO = dataLogin.getWxSno();
        SplitWeb.USER_ID = dataLogin.getUserId();
        SplitWeb.USER_HEADER = dataLogin.getHeadImg();
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
        DialogUtils.showDialogOne("信任是美好的开始，请授权相机等权限，让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
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
