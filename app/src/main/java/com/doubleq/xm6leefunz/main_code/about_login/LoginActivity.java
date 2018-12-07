package com.doubleq.xm6leefunz.main_code.about_login;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.EditCheckUtils;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.doubleq.model.DataLogin;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.login_ed_phone)
    EditText loginEdPhone;
    @BindView(R.id.login_ed_psw)
    EditText loginEdPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //绑定成功后的操作
//    @Override
//    public void onServiceBindSuccess() {
//        super.onServiceBindSuccess();
//        Log.e("bindUid", "----------onServiceBindSuccess--------------------------------------");
//    }
    public static int screenWidth;
    public static int screenHeight;

    private ACache mCache;
    @Override
    protected void initBaseView() {
        super.initBaseView();
//        includeTopIvBack.setVisibility(View.INVISIBLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        includeTopTvTital.setText("登录");
        mCache = ACache.get(this);
        listenEnter();
    }




    private void initSetData(DataLogin.RecordBean dataLogin) {
        SPUtils.put(HelpUtils.activity,"userId",dataLogin.getUserId());
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
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected boolean isGones() {
        return true;
    }
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("bindUid")) {
            if (!isFirst) {
                IntentUtils.JumpFinishTo(LoginActivity.this,MainActivity.class);
//                IntentUtils.JumpTo(MainActivity.class);
//                AppManager.getAppManager().finishActivity(LoginActivity.this);
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
                DialogUtils.showDialogKnow("请直接输入手机号，用短信登录","知道了");
                break;
            case R.id.login_btn_login:
//                IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);
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
        final   String phone = loginEdPhone.getText().toString().trim();
        String pwd = loginEdPsw.getText().toString().trim();

        if (StrUtils.isEmpty(phone)) {
            DialogUtils.showDialog(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!EditCheckUtils.isMobileNO(phone)) {
            DialogUtils.showDialog(getResources().getString(R.string.phone_is_error));
//            Tip.getDialog(LoginActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(pwd)) {
            DialogUtils.showDialog("密码不得为空");
//            Tip.getDialog(LoginActivity.this,"密码不得为空");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower,SplitWeb.loginIn(phone, pwd), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                SPUtils.put(LoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
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
        if (userInfo!=null) {
            String is_first_login = userInfo.getIsFirstLogin();
            if (is_first_login.equals("1"))
                isFirst = true;
            else
                isFirst = false;

            initSetData(userInfo);
        }
        sendWeb(SplitWeb.bindUid());
    }
}
