package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.about_count_and_safe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 更换绑定  原号码页面
 */
public class ChangeBindOldActivity extends BaseActivity {

    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.changebind_tv_old_getcode)
    TextView mTvCode;
    @BindView(R.id.changebind_ed_old_phone)
    EditText changebindEdPhone;
    @BindView(R.id.changebind_ed_old_code)
    EditText changebindEdCode;
    @BindView(R.id.include_top_lin_background)
    LinearLayout mLin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("更换绑定");
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_bind_old;
    }

    @OnClick({R.id.changebind_tv_old_getcode, R.id.changebind_btn_old_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            获取验证码按钮
            case R.id.changebind_tv_old_getcode:
                if (NoDoubleClickUtils.isDoubleClick())
                    initSendSms();
                break;
//                确认提交按钮
            case R.id.changebind_btn_old_sure:
                if (NoDoubleClickUtils.isDoubleClick())
                    initChange();
                break;
        }
    }

    private void initChange() {
        final String phone = changebindEdPhone.getText().toString().trim();
        String code = changebindEdCode.getText().toString().trim();
        if (!EditCheckUtils.isMobileNO(phone)) {
            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(ResgisterActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show("验证码不得为空");
//            return;
        }
//        sendWeb(SplitWeb.upPassWordSms(phone,code,psw));
    }

    private void initSendSms() {
        String phone = changebindEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (!EditCheckUtils.isMobileNO(phone)) {
            ToastUtil.show("手机号输入有误");
//            Tip.getDialog(this,"手机号输入有误");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(SplitWeb.smsCode(phone, "3"), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                timer.start();
            }
        });
    }

    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @SuppressLint("SetTextI18n")
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

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("upPassWordSms"))
        {
//            DialogUtils.showDialogOne("正在跳转", new DialogUtils.OnClickSureListener() {
//                @Override
//                public void onClickSure() {
//                    SplitWeb.USER_ID="";
//                    AppManager.getAppManager().finishAllActivity();
//                    overridePendingTransition(0,0);
                    Intent intent_recharge = new Intent(ChangeBindOldActivity.this, ChangeBindNewActivity.class);
                    startActivity(intent_recharge);
//                    overridePendingTransition(0,0);
//                    ACache.get(ChangeBindOldActivity.this).clear();
//                    SPUtils.clear(ChangeBindOldActivity .this);
////                    AppManager.getAppManager().finishActivity();
////
//                }
//            });
        }
    }
}
