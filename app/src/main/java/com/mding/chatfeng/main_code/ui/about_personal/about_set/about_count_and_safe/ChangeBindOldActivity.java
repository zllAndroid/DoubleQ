package com.mding.chatfeng.main_code.ui.about_personal.about_set.about_count_and_safe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_base.BaseActivity;
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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

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
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show("验证码不得为空");
//            return;
        }
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().replaceMobileOld(phone, code), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                IntentUtils.JumpFinishTo(ChangeBindOldActivity.this,ChangeBindNewActivity.class);
//            }
//        }, new NetWorkUtlis.OnNetWorkError() {
//            @Override
//            public void onNetError(String msg) {
//                DialogUtils.showDialogOne("您输入的验证码有误！", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//
//                    }
//                });
//                timer.start();
//            }
//        });

        sendWeb(SplitWeb.getSplitWeb().replaceMobileOld(phone,code));
    }

    private void initSendSms() {
        String phone = changebindEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show("手机号输入有误");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().smsCode(phone, "4"), new NetWorkUtlis.OnNetWork() {
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
        }
        @Override
        public void onFinish() {
            mTvCode.setEnabled(true);
            mTvCode.setClickable(true);
            mTvCode.setText("获取验证码");
        }
    };

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("replaceMobileOld"))
        {
            timer.cancel();
            IntentUtils.JumpFinishTo(ChangeBindOldActivity.this,ChangeBindNewActivity.class);

        }
    }
}
