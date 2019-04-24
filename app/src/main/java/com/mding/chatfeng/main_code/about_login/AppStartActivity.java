package com.mding.chatfeng.main_code.about_login;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentValues;
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
public class AppStartActivity extends BaseActivity {

    Timer timer = null;
    //    @BindView(R.id.appstart_lin)
//    LinearLayout appstartLin;
//    @BindView(R.id.videoview)
//    CustomVideoView videoview;
    boolean isClick =true;
    //创建播放视频的控件对象
//    private CustomVideoView videoview;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
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
//        initNetReceive();
////        TODO 测试创建数据库，添加一条数据在home_msg表
//        DBgreatTable dBgreatTable = new DBgreatTable(this);
//        SQLiteDatabase writableDatabase = dBgreatTable.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("userId","1234");
//        values.put("friendId","456");
//        writableDatabase.insert(TotalEntry.TABLE_NAME_Msg,null,values);

//        SQLiteDatabase   db= (new DBgreatTable(this)).getWritableDatabase();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            AppStartActivityPermissionsDispatcher.needWithCheck(this);
//            need();
        else
            need();
    }
    private NetReceiver mReceiver;
    private void initNetReceive() {
        mReceiver = new NetReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
       this.registerReceiver(mReceiver, mFilter);
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
//            IntentUtils.JumpFinishTo(LoginActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                showFloatingWindow();
            }
//            IntentUtils.JumpFinishTo(MainActivity.class);
//            IntentUtils.JumpTo(LoginActivity.class);
//            AppManager.getAppManager().finishActivity(AppStartActivity.this);
            initCaChe();

//            if (isClick) {
////                IntentUtils.JumpFinishTo(MainActivity.class);
////                openingStartAnimation.show(AppStartActivity.this);
//
//            }
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
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();

        try {
            if (mReceiver!=null)
               unregisterReceiver(mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
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
        if (mCache==null)
            mCache = ACache.get(this);
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {
                Log.e("result","token信息"+asString.toString());
                DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
                if (dataLogin!=null) {
                    initSetData(dataLogin);
                    String asFriend = mCache.getAsString(AppAllKey.FRIEND_DATA);
                    Log.e("result","FRIEND_DATA信息="+asFriend);
//                    IntentUtils.JumpFinishTo(AppStartActivity.this, MainActivity.class);

                    if (StrUtils.isEmpty(asFriend))
                    {
//                        IntentUtils.JumpFinishTo(AppStartActivity.this, LoadDataActivity.class);
                        IntentUtils.JumpFinishTo(AppStartActivity.this, LoadLinkManActivity.class);
                    }else {
                        IntentUtils.JumpFinishTo(AppStartActivity.this, MainActivity.class);
                    }
                    overridePendingTransition(0, 0);
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
        SplitWeb.getSplitWeb().USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.getSplitWeb().MOBILE = dataLogin.getMobile();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().NICK_NAME = dataLogin.getNickName();
        SplitWeb.getSplitWeb().PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().WX_SNO = dataLogin.getWxSno();
        SplitWeb.getSplitWeb().USER_ID = dataLogin.getUserId();
        SplitWeb.getSplitWeb().USER_HEADER = dataLogin.getHeadImg();
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
