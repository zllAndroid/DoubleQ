package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set.about_count_and_safe;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.main_code.about_login.LoginActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class ChangePwdActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_lin_background)
    LinearLayout mLin;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.changepwd_tv_yanzhengma)
    TextView mTvYan;
    @BindView(R.id.changepwd_ed_old_psw)
    EditText changepwdEdOldPsw;
    @BindView(R.id.changepwd_ed_psw)
    EditText changepwdEdPsw;
    @BindView(R.id.changepwd_ed_sure_psw)
    EditText changepwdEdSurePsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("修改密码");
//        mLin.setBackgroundColor(0);
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        mTvYan.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_pwd;
    }
    private void clickSure() {
        final   String oldPwd = changepwdEdOldPsw.getText().toString().trim();
        String newPwd = changepwdEdPsw.getText().toString().trim();
        String surePwd = changepwdEdSurePsw.getText().toString().trim();

        if (StrUtils.isEmpty(oldPwd)) {
            DialogUtils.showDialog("旧密码不得为空");
            return;
        }
        if (StrUtils.isEmpty(newPwd)) {
            DialogUtils.showDialog("新密码不得为空");
            return;
        }
        if (StrUtils.isEmpty(surePwd)) {
            DialogUtils.showDialog("确认新密码不得为空");
            return;
        }
        if (!surePwd.equals(newPwd)) {
            DialogUtils.showDialog("两次密码输入不一致");
            return;
        }
        sendWeb(SplitWeb.upPassWord(oldPwd,newPwd,surePwd));
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);

        DialogUtils.showDialogOne("修改密码成功", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                SplitWeb.USER_ID="";
                AppManager.getAppManager().finishAllActivity();
                String mPhone = (String)SPUtils.get(ChangePwdActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.MOBILE);
                if (!StrUtils.isEmpty(mPhone))
                    IntentUtils.JumpToHaveOne(LoginActivity.class,"phone",mPhone);

//                Intent intent_recharge = new Intent(ChangePwdActivity.this, LoginActivity.class);
//                startActivity(intent_recharge);
//                overridePendingTransition(0,0);
                ACache.get(ChangePwdActivity.this).clear();
                SPUtils.clear(ChangePwdActivity.this);
//                AppManager.getAppManager().finishActivity();
            }
        });
    }

    @OnClick({R.id.changepwd_tv_yanzhengma, R.id.changepwd_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changepwd_tv_yanzhengma:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    IntentUtils.JumpFinishTo(ChangePwdActivity.this,ChangeCodeActivity.class);
                }
                break;
            case R.id.changepwd_btn_sure:
                if (NoDoubleClickUtils.isDoubleClick())
                    clickSure();
                break;
        }
    }
}
