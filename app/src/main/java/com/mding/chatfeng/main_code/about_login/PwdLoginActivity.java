package com.mding.chatfeng.main_code.about_login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
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
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.main_code.mains.LoadDataActivity;
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
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 项目：DoubleQ
 * 文件描述：验证码登录界面
 * 作者：zll
 */
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
    @BindView(R.id.include_top_lin_newback)
    LinearLayout mLinBack;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
//        includeTopIvBack.setVisibility(View.INVISIBLE);
        includeTopTvTital.setText("短信登录");
        mLinBack.setVisibility(View.INVISIBLE);
        mCache = ACache.get(this);
        initUrl();
        listenEnter();

        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction("start_application");
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }
    IntentFilter intentFilter;
    public BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("start_application"))
            {
                if (!StrUtils.isEmpty(SplitWeb.getUserId()))
                    sendWeb(SplitWeb.bindUid());
            }
        }
    };
    private void initUrl() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.PreRequest, new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                Log.e("result=", result + "---------------------------");
                DataServer dataServer = JSON.parseObject(result, DataServer.class);
                String swooleServer = dataServer.getSwooleServer();

                SplitWeb.HttpURL = swooleServer;
                SPUtils.put(PwdLoginActivity.this, AppConfig.TYPE_URL, swooleServer+"");
            }
        });
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
    boolean isFirst = false;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("bindUid"))
        if (!isFirst) {
//                TODO 修改
            IntentUtils.JumpFinishTo(PwdLoginActivity.this,LoadDataActivity.class);
//                IntentUtils.JumpFinishTo(LoginActivity.this,FirstAddHeaderActivity.class);
        }
        else
            IntentUtils.JumpFinishTo(PwdLoginActivity.this,FirstAddHeaderActivity.class);
    }

    private void SaveLoginResultData(DataLogin.RecordBean userInfo) {
        String json = JSON.toJSON(userInfo).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
        if (userInfo != null) {
            String is_first_login = userInfo.getIsFirstLogin();
            if (is_first_login.equals("1"))
                isFirst = true;
            else
                isFirst = false;

            initSetData(userInfo);
        }
//        if (!StrUtils.isEmpty(SplitWeb.getUserId()))
//        sendWeb(SplitWeb.bindUid());
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
        SplitWeb.USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.MOBILE = dataLogin.getMobile();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.NICK_NAME = dataLogin.getNickName();
        SplitWeb.PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.WX_SNO = dataLogin.getWxSno();
        SplitWeb.USER_ID = dataLogin.getUserId();
        SplitWeb.USER_HEADER = dataLogin.getHeadImg();
        mCache.put(AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        //TODO 集群
        try {
            SplitWeb.WS_REQUEST = dataLogin.getServerIpWs();
            SplitWeb.HTTP_REQUEST = dataLogin.getServerIpHttp();
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
