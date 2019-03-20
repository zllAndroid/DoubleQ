package com.doubleq.xm6leefunz.main_code.about_login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLogin;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.EditCheckUtils;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PwdLoginActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.reg_ed_phone)
    EditText regEdPhone;
    @BindView(R.id.pwd_ed_code)
    EditText pwdEdCode;
    @BindView(R.id.pwd_tv_send_code)
    TextView pwdTvSendCode;
    @BindView(R.id.pwd_btn_login)
    Button pwdBtnLogin;
    @BindView(R.id.pwd_tv_new_resgister)
    TextView pwdTvNewResgister;
    @BindView(R.id.pwd_tv_notice)
    TextView pwdTvNotice;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
//        includeTopIvBack.setVisibility(View.INVISIBLE);
        includeTopTvTital.setText("短信登录");
        mCache = ACache.get(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_pwd_login;
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
    public boolean isSupportSwipeBack() {
        return false;
    }

    @OnClick({R.id.pwd_tv_send_code, R.id.pwd_btn_login, R.id.pwd_tv_new_resgister, R.id.pwd_tv_code_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pwd_tv_send_code:
                if (NoDoubleClickUtils.isDoubleClick())
                    initSendSms();
//                timer.start();
                break;
            case R.id.pwd_btn_login:
                if (NoDoubleClickUtils.isDoubleClick())
                    initCodeLogin();

                break;
            case R.id.pwd_tv_new_resgister:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(RegisterActivity.class);
                break;
            case R.id.pwd_tv_code_login:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpFinishTo(PwdLoginActivity.this, LoginActivity.class);
                break;
        }
    }

    private void initSendSms() {
        String phone = regEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号输入有误");
//            Tip.getDialog(this,"手机号输入有误");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(SplitWeb.smsCode(phone, "1"), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                timer.start();
            }
        });
    }

    private void initCodeLogin() {
        timer.cancel();
        final String phone = regEdPhone.getText().toString().trim();
        String code = pwdEdCode.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show("验证码不能为空");
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
//        if (!EditCheckUtils.isMobileNO(phone)) {
//            ToastUtil.show("手机号输入有误");
////            Tip.getDialog(this,"手机号输入有误");
//            return;
//        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(SplitWeb.smsLogin(phone, code), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                SPUtils.put(PwdLoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, phone);
                DataLogin dataLogin = JSON.parseObject(msg, DataLogin.class);
                DataLogin.RecordBean record = dataLogin.getRecord();
                if (record != null)
                    SaveLoginResultData(record);

            }
        }, new NetWorkUtlis.OnNetWorkError() {
            @Override
            public void onNetError(String msg) {
                pwdTvNotice.setVisibility(View.VISIBLE);
                notice_timer.start();
            }
        });
    }

    private ACache mCache;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("bindUid"))
            IntentUtils.JumpFinishTo(PwdLoginActivity.this, MainActivity.class);
    }

    private void SaveLoginResultData(DataLogin.RecordBean userInfo) {
        String json = JSON.toJSON(userInfo).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
        if (userInfo != null)
            initSetData(userInfo);
        if (!StrUtils.isEmpty(SplitWeb.getUserId()))
        sendWeb(SplitWeb.bindUid());
    }

    private void initSetData(DataLogin.RecordBean dataLogin) {
        SplitWeb.USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.MOBILE = dataLogin.getMobile();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.NICK_NAME = dataLogin.getNickName();
        SplitWeb.PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.WX_SNO = dataLogin.getWxSno();
        SplitWeb.USER_ID = dataLogin.getUserId();
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                pwdTvSendCode.setText((l / 1000) + "s");
                pwdTvSendCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
        }

        @Override
        public void onFinish() {
            pwdTvSendCode.setEnabled(true);
            pwdTvSendCode.setClickable(true);
            pwdTvSendCode.setText("获取验证码");
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };
    private CountDownTimer notice_timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            pwdTvNotice.setVisibility(View.INVISIBLE);
        }
    };


}
