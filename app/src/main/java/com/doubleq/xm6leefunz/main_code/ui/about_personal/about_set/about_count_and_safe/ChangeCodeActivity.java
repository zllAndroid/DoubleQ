package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.about_count_and_safe;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.EditCheckUtils;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
import com.doubleq.xm6leefunz.main_code.about_login.LoginActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class ChangeCodeActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @BindView(R.id.changecode_tv_psw)
    TextView mTvYan;
    //    @BindView(R.id.changecode_ed_count)
//    EditText changecodeEdCount;
    @BindView(R.id.changecode_tv_count)
    TextView changecodeTvCode;
    @BindView(R.id.changecode_ed_code)
    EditText changecodeEdCode;
    @BindView(R.id.changecode_ed_new_psw)
    EditText changecodeEdNewPsw;
    @BindView(R.id.changecode_tv_code)
    TextView mTvCode;
    @BindView(R.id.include_top_lin_background)
    LinearLayout mLin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("修改密码");
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
//        给密码修改按钮添加下划线
        mTvYan.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        String mPhone = (String)SPUtils.get(this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.MOBILE);
        if (!StrUtils.isEmpty(mPhone))
            changecodeTvCode.setText(mPhone);
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_code;
    }

    @OnClick({R.id.changecode_tv_code, R.id.changecode_tv_psw, R.id.changecode_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            获取验证码按钮
            case R.id.changecode_tv_code:
                if (NoDoubleClickUtils.isDoubleClick())
                    initSendSms();
                break;
            //跳转密码修改界面
            case R.id.changecode_tv_psw:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    IntentUtils.JumpFinishTo(ChangeCodeActivity.this,ChangePwdActivity.class);
//                    overridePendingTransition(0,0);
                }
                break;
//                确认提交按钮
            case R.id.changecode_btn_sure:
                if (NoDoubleClickUtils.isDoubleClick())
                    initChange();
                break;
        }
    }

        String phone = null;
    private void initChange() {
//        final String phone = changecodeTvCode.getText().toString().trim();
        phone = changecodeTvCode.getText().toString().trim();
        String code = changecodeEdCode.getText().toString().trim();
        String psw = changecodeEdNewPsw.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(ResgisterActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show("验证码不得为空");
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            ToastUtil.show("密码不得为空");
            return;
        }

        boolean b = StrUtils.validatePassword(psw);
        Log.e("validatePassword","我是否满足="+b+"");
        if (!b) {
//            ToastUtil.show("满足");
            DialogUtils.showDialog("密码至少要包括:\n字母、数字、标点符号\n的其中两项,长度为6-20位");
            return;
        }

//        if (phone.equals(psw)){
//            DialogUtils.showDialog("密码不能与会员名相同");
//            return;
//        }

        sendWeb(SplitWeb.upPassWordSms(phone,code,psw));
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, SplitWeb.smsCode(phone,psw,code), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String result) {
//                DialogUtils.showDialogOne("注册成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                        overridePendingTransition(0,0);
//                    }
//                });
////                Tip.getDialog(RegisterActivity.this,"注册成功",true);
//            }
//        });
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("upPassWordSms"))
        {
            DialogUtils.showDialogOne("修改密码成功", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    timer.cancel();
                    SplitWeb.USER_ID="";
                    IntentUtils.JumpToHaveOne(LoginActivity.class,"phone",phone);
                    AppManager.getAppManager().finishAllActivity();
                    overridePendingTransition(0,0);

//                    Intent intent_recharge = new Intent(ChangeCodeActivity.this, LoginActivity.class);
//                    startActivity(intent_recharge);
//                    overridePendingTransition(0,0);

                    ACache.get(ChangeCodeActivity.this).clear();
                    SPUtils.clear(ChangeCodeActivity .this);
//                    AppManager.getAppManager().finishActivity();
//
                }
            });
        }

    }

    private void initSendSms() {
        String phone = changecodeTvCode.getText().toString().trim();
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
//        sendWeb(SplitWeb.upPassWordSms(phone));
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(SplitWeb.smsCode(phone, "3"), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                timer.start();
            }
        });
    }
    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            mTvCode.setText((l / 1000) + "s");
            mTvCode.setClickable(false);
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
        }
        @Override
        public void onFinish() {
            mTvCode.setEnabled(true);
            mTvCode.setClickable(true);
            mTvCode.setText("获取验证码");
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };
}
