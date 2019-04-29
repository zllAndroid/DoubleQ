package com.mding.chatfeng.main_code.about_login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.mding.model.DataLogin;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.main_code.mains.MainActivity;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.model.DataServer;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

/**
 * 项目：DoubleQ
 * 文件描述：验证码登录界面
 * 作者：zll
 * 修改者：刘佳佳
 */
public class PwdLoginActivity extends BaseLogin {
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
    @BindView(R.id.include_top_lin_newback)
    LinearLayout mLinBack;

    @Override
    protected void initBaseView() {
        super.initBaseView();
//        includeTopIvBack.setVisibility(View.INVISIBLE);
        includeTopTvTital.setText("短信登录");
        mLinBack.setVisibility(View.INVISIBLE);
        init(BaseApplication.getAppContext());
        initUrl();
        listenEnter();
    }
    //    初始化获取url
    private void initUrl() {
            MyJsonUtils.initBeforeLogin(PwdLoginActivity.this);

    }
    private void listenEnter() {
        pwdEdCode.setImeOptions(EditorInfo.IME_ACTION_SEND);
        pwdEdCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //处理事件
                    initCodeLogin();
                }
                return false;
            }
        });
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
    //获取短信验证码按钮
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
        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().smsCode(phone, "1"), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                timer.start();
            }
        });
    }
    //点击验证码登录，执行短信登录内容
    private void initCodeLogin() {
        timer.cancel();
        final String phone = regEdPhone.getText().toString().trim();
        String code = pwdEdCode.getText().toString().trim();

        if (StrUtils.isEmpty(SplitWeb.getSplitWeb().HttpURL))
        {
            initUrl();
        }
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

        try {
            SPUtils.put(PwdLoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, phone);
            if(iLoginRequst!=null)
            {

                String s = SplitWeb.getSplitWeb().smsLogin(phone, code);
                if(s.contains("http")) {
                    MyLog.e("request", "-------登录请求---------->>" + s);
                    iLoginRequst.loginRequest(s);
                }else
                {
                    initUrl();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    //短信倒计时
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                pwdTvSendCode.setText((l / 1000) + "s");
                pwdTvSendCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFinish() {
            pwdTvSendCode.setEnabled(true);
            pwdTvSendCode.setClickable(true);
            pwdTvSendCode.setText("获取验证码");
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };

    //    文本消失倒计时
    private CountDownTimer notice_timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            pwdTvNotice.setVisibility(View.INVISIBLE);
        }
    };
    //返回成功内容
    @Override
    void onLoginSuccees(String mLoginModel) {
        String isSucess = HelpUtils.HttpIsSucess(mLoginModel);
        if (isSucess.equals(AppAllKey.CODE_OK)) {
            AboutLoginSaveData aboutLoginSaveData = new AboutLoginSaveData(PwdLoginActivity.this);
            aboutLoginSaveData.initSaveData(mLoginModel);
        }else {
            ToastUtil.show(isSucess);
        }
    }
    //返回错误
    @Override
    void onLoginFail(String mLoginModel) {
//        显示短信验证码输入错误的内容
        pwdTvNotice.setVisibility(View.VISIBLE);
//        启动倒计时
        notice_timer.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            String asString = BaseApplication.getaCache().getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
                unbindService(this);
//            stopService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
