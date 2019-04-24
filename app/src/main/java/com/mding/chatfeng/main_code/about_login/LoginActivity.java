package com.mding.chatfeng.main_code.about_login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.mding.model.DataLogin;
import com.mding.model.DataServer;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

/**
 * 项目：DoubleQ
 * 文件描述：登陆界面
 * 作者：zll
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.login_ed_phone)
    EditText loginEdPhone;
    @BindView(R.id.login_ed_psw)
    EditText loginEdPsw;
    @BindView(R.id.include_top_lin_newback)
    LinearLayout mLinBack;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    //绑定成功后的操作
//    @Override
//    public void onServiceBindSuccess() {
//        super.onServiceBindSuccess();
//    }
//    @Override
//    public boolean isGonesStatus() {
//        return true;
//    }

//    @Override
//    protected void initBeforeContentView() {
//        super.initBeforeContentView();
//        StateBarUtils.setFullscreen(this,false,true);
//    }

    public static int screenWidth;
    public static int screenHeight;

    private ACache mCache;
    String QR_CODE = "qrCode";
    String swooleServer;
    @Override
    protected void initBaseView() {
        super.initBaseView();
//        initSQL();
//        writableDatabase.query("home_msg",null,"id",null,null,null,null);
//        includeTopIvBack.setVisibility(View.INVISIBLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        includeTopTvTital.setText("登录");
        mLinBack.setVisibility(View.INVISIBLE);
        Bundle bundle = this.getIntent().getExtras();
        String userPhone = null;
        if (bundle != null) {
            userPhone = bundle.getString("phone");
        }
        if (userPhone != null){
            loginEdPhone.setText(userPhone);
            loginEdPhone.setSelection(userPhone.length());//将光标移至文字末尾
        }
        mCache = ACache.get(this);
        //TODO  获取第一层url
        initUrl();
        listenEnter();

        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction("start_application");
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }


    public  BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("start_application"))
            {
                if (!StrUtils.isEmpty(SplitWeb.getSplitWeb().getUserId()))
                sendWeb(SplitWeb.getSplitWeb().bindUid());
//                if (!isFirst) {
//                    IntentUtils.JumpFinishTo(LoginActivity.this,LoadDataActivity.class);
////                IntentUtils.JumpFinishTo(LoginActivity.this,MainActivity.class);
//                }
//                else
//                    IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);
////                        initManagerService();
            }
        }
    };
    IntentFilter intentFilter;
    private void initUrl() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().PreRequest, new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                Log.e("result=", result + "---------------------------");
                DataServer dataServer = JSON.parseObject(result, DataServer.class);
                //测试
                String swooleServer = dataServer.getSwooleServer();
//                String swooleServer = dataServer.getSwooleServer_v1();

                SplitWeb.getSplitWeb().HttpURL = swooleServer;
                SPUtils.put(LoginActivity.this, AppConfig.TYPE_URL, swooleServer+"");
            }
        });
    }

    private void initSetData(DataLogin.RecordBean dataLogin) {
        SPUtils.put(this,AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        SPUtils.put(this,AppAllKey.USER_Token,dataLogin.getUserToken());

        SPUtils.put(this, AppConfig.TYPE_NAME,dataLogin.getNickName());
        SPUtils.put(this,AppConfig.TYPE_NO,dataLogin.getWxSno());
        SPUtils.put(this,AppConfig.TYPE_PHONE,dataLogin.getWxSno());
        SPUtils.put(this,AppConfig.User_HEAD_URL,dataLogin.getHeadImg());
        SPUtils.put(this,AppConfig.TYPE_SIGN,dataLogin.getPersonaSignature());


//        SPUtils.put(this,AppConfig.TYPE_WS_REQUEST,dataLogin.getServerIpWs());
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
        //TODO 集群
        try {
            SplitWeb.getSplitWeb().WS_REQUEST = dataLogin.getServerIpWs();
            SplitWeb.getSplitWeb().HTTP_REQUEST = dataLogin.getServerIpHttp();
            String serverIpWs = dataLogin.getServerIpWs();
            String serverIpHttp = dataLogin.getServerIpHttp();
            mCache.remove(AppConfig.TYPE_WS_REQUEST);
            mCache.put(AppConfig.TYPE_WS_REQUEST,serverIpWs);
            mCache.remove(AppConfig.TYPE_URL);
            mCache.put(AppConfig.TYPE_URL,serverIpHttp);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //TODO 集群
        Intent intent = new Intent();
        intent.setAction("server_application");
        sendBroadcast(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String phone =(String ) SPUtils.get(LoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, "");
        if (!phone.isEmpty()) {
            loginEdPhone.setText(phone);
            loginEdPhone.setSelection(phone.length());//将光标移至文字末尾
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isLogin() {
        return true;
    }
    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected boolean isGones() {
        return false;
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("bindUid")) {
            if (!isFirst) {
//                TODO 修改
                IntentUtils.JumpFinishTo(LoginActivity.this,LoadLinkManActivity.class);
//                IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);
            }
            else
                IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);
        }
    }
    private void listenEnter() {
        loginEdPsw.setImeOptions(EditorInfo.IME_ACTION_SEND);
        loginEdPsw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //处理事件
                    clickLogin();
                }
                return false;
            }
        });
    }

    @OnClick({R.id.login_tv_code_login, R.id.login_tv_forget_pwd, R.id.login_btn_login, R.id.login_tv_new_resgister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_code_login:
                IntentUtils.JumpFinishTo(LoginActivity.this,PwdLoginActivity.class);
                break;
            case R.id.login_tv_forget_pwd:
//                DialogUtils.showDialogKnow("请直接输入手机号，用短信登录","知道了");
                IntentUtils.JumpFinishTo(LoginActivity.this,PwdLoginActivity.class);
                break;
            case R.id.login_btn_login:
                if (NoDoubleClickUtils.isDoubleClick())
                    clickLogin();
                break;
            case R.id.login_tv_new_resgister:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(RegisterActivity.class);
                break;
        }
    }
    private void clickLogin() {
        final String phone = loginEdPhone.getText().toString().trim();
        final String pwd = loginEdPsw.getText().toString().trim();

        if (StrUtils.isEmpty(phone)) {
            DialogUtils.showDialog(getResources().getString(R.string.phone_is_null));
            return;
        }
//        if (StrUtils.isEmpty(phone)) {
//            DialogUtils.showDialog(getResources().getString(R.string.phone_is_error));
//            return;
//        }
        if (StrUtils.isEmpty(pwd)) {
            DialogUtils.showDialog("密码不得为空");
            return;
        }
        if (StrUtils.isEmpty(SplitWeb.getSplitWeb().HttpURL))
        {
            initUrl();
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower,SplitWeb.getSplitWeb().loginIn(phone, pwd), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                SPUtils.put(LoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
                SPUtils.put(LoginActivity.this,AppConfig.TYPE_PSW,pwd);
                SplitWeb.getSplitWeb().PSW=pwd;
                DataLogin dataLogin = JSON.parseObject(msg, DataLogin.class);
                DataLogin.RecordBean record = dataLogin.getRecord();
                if (record != null)
                    SaveLoginResultData(record);
//                IntentUtils.JumpFinishTo(MainActivity.class);
            }
        });
    }
    boolean isFirst = false;
    private void SaveLoginResultData(DataLogin.RecordBean userInfo) {
        String json = JSON.toJSON(userInfo).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
        mCache.put(IMAGE_BASE64, userInfo.getHeadImg());
        mCache.put(QR_CODE, userInfo.getQrcode());
        if (userInfo!=null) {
            String is_first_login = userInfo.getIsFirstLogin();
            if (is_first_login.equals("1"))
                isFirst = true;
            else
                isFirst = false;

            initSetData(userInfo);
        }

        //TODO 集群  屏蔽
//        if (!isFirst) {
//            IntentUtils.JumpFinishTo(LoginActivity.this,LoadDataActivity.class);
////                IntentUtils.JumpFinishTo(LoginActivity.this,MainActivity.class);
//        }
//        else
//            IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mRefreshBroadcastReceiver!=null)
                unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
