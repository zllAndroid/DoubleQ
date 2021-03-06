package com.mding.chatfeng.main_code.about_login;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 项目：DoubleQ
 * 文件描述：注册界面
 * 作者：zll
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.reg_ed_phone)
    EditText regEdPhone;
    @BindView(R.id.reg_ed_code)
    EditText regEdCode;
    @BindView(R.id.reg_tv_send_code)
    TextView regTvSendCode;
    @BindView(R.id.reg_ed_psw)
    EditText regEdPsw;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopIvBack.setVisibility(View.VISIBLE);
//        includeTopTvTital.setText("注册");
        includeTopTvTital.setText(getResources().getString(R.string.register_title));
    }

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @OnClick({R.id.reg_tv_send_code, R.id.reg_btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_tv_send_code:
                if (NoDoubleClickUtils.isDoubleClick())
                    initSendSms();
//                   timer.start();
                break;
            case R.id.reg_btn_login:
                if (NoDoubleClickUtils.isDoubleClick())
                    initResgister();
                break;
        }
    }

    private void initResgister() {
        timer.cancel();
        final String phone = regEdPhone.getText().toString().trim();
        String code = regEdCode.getText().toString().trim();
        String psw = regEdPsw.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
//            ToastUtil.show("手机号不能为空");
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            Tip.getDialog(ResgisterActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
//            ToastUtil.show("验证码不得为空");
            ToastUtil.show(getResources().getString(R.string.verification_code_is_null));
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
//            ToastUtil.show("密码不得为空");
            return;
        }

        boolean b = StrUtils.validatePassword(psw);
        Log.e("validatePassword","我是否满足="+b+"");
        if (!b) {
//            ToastUtil.show("满足");
            DialogUtils.showDialog(getResources().getString(R.string.register_pwd_set_error));
//            DialogUtils.showDialog("密码至少要包括:\n字母、数字、标点符号\n的其中两项,长度为6-20位");
            return;
        }
        String register = SplitWeb.getSplitWeb().register(phone, psw, code);
        if(register.contains("http"))
        {
            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
            netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, register, new NetWorkUtlis.OnNetWork() {
                @Override
                public void onNetSuccess(String result) {
                    SPUtils.put(RegisterActivity.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
                    DialogUtils.showDialogOne(getResources().getString(R.string.register_succeed), new DialogUtils.OnClickSureListener() {
//                    DialogUtils.showDialogOne("注册成功", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            AppManager.getAppManager().finishActivity();
                            overridePendingTransition(0,0);
                        }
                    });
                }
            });
        }else
        {
            ToastUtil.show(getResources().getString(R.string.try_again_later));
//            ToastUtil.show("请稍后再试");
            MyJsonUtils.initBeforeLogin(RegisterActivity.this);
        }
    }
    private void initSendSms() {
        String phone = regEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
//            Tip.getDialog(this,"手机号输入有误");
            return;
        }
        String smsCodeHttp = SplitWeb.getSplitWeb().smsCode(phone, "2");
        if (smsCodeHttp.contains("http"))
        {
            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
            netWorkUtlis.setOnNetWork(smsCodeHttp, new NetWorkUtlis.OnNetWork() {
                @Override
                public void onNetSuccess(String msg) {
                    String isSucess = HelpUtils.HttpIsSucess(msg);
                    if (isSucess.equals(AppAllKey.CODE_OK))
                        timer.start();
                }
            });
        }else{
            ToastUtil.show(getResources().getString(R.string.try_again_later));
//            ToastUtil.show("请稍后再试");
            MyJsonUtils.initBeforeLogin(RegisterActivity.this);
        }
    }
    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                regTvSendCode.setText((l / 1000) + "s");
                regTvSendCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
        }
        @Override
        public void onFinish() {
            try {
                regTvSendCode.setEnabled(true);
                regTvSendCode.setClickable(true);
                regTvSendCode.setText(getResources().getString(R.string.login_get_code));
//                regTvSendCode.setText("获取验证码");
            } catch (Exception e) {
                e.printStackTrace();
            }
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };
}
