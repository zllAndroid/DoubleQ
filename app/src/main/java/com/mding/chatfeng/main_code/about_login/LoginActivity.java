package com.mding.chatfeng.main_code.about_login;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.core.pushservice.WsChannelService;
import com.mding.model.DataForceLogin;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目：DoubleQ
 * 文件描述：登陆界面
 * 作者：zll
 */
public class LoginActivity extends BaseLogin {
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


    public static int screenWidth;
    public static int screenHeight;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        includeTopTvTital.setText("登录");
        mLinBack.setVisibility(View.INVISIBLE);
        init(BaseApplication.getAppContext());
        //TODO  获取第一层url
        initUrl();

        listenEnter();
    }

    private void initUrl() {
        MyJsonUtils.initBeforeLogin(LoginActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WsChannelService.isBind=true;
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

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String isSucess = HelpUtils.HttpIsSucess(responseText);
        if (isSucess.equals(AppAllKey.CODE_OK)) {
            String s = HelpUtils.backMethod(responseText);
            if (s.equals("sendForceLogin")) {
                DataForceLogin dataForceLogin = JSON.parseObject(responseText, DataForceLogin.class);
                DataForceLogin.RecordBean record = dataForceLogin.getRecord();
                if (record!=null)
                {
                    DialogUtils.showDialog(record.getMessage());
                }


            }
        }
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
        try {
            SPUtils.put(LoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
            SPUtils.put(LoginActivity.this,AppConfig.TYPE_PSW,pwd);
            SplitWeb.getSplitWeb().PSW=pwd;
            if(iLoginRequst!=null)
            {
                String s = SplitWeb.getSplitWeb().loginIn(phone, pwd);
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

    @Override
    void onLoginSuccees(String mLoginModel) {
        MyLog.e("request", "-------登录返回消息---------->>" + mLoginModel);
        String isSucess = HelpUtils.HttpIsLoginSucess(mLoginModel);
        if (isSucess.equals(AppAllKey.CODE_OK)) {
            AboutLoginSaveData aboutLoginSaveData = new AboutLoginSaveData(LoginActivity.this);
            aboutLoginSaveData.initSaveData(mLoginModel);
        }
        else if (isSucess.equals(AppConfig.CODE_EPC)){
            String httpReturnMsg = HelpUtils.HttpReturnMsg(mLoginModel);
            try {
                ToastUtil.show(httpReturnMsg);
            } catch (Exception e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what=BackError;
                message.obj=httpReturnMsg;
                successHandle.sendMessage(message);
            }

//            Message message = new Message();

        }
    }
    public  static  final  int BackError=1;
    Handler successHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what)
            {
                case BackError:
                    try {
                        ToastUtil.show((String) message.obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return false;
        }
    });
    @Override
    void onLoginFail(String mLoginModel) {

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
