package com.mding.chatfeng.main_code.ui.about_personal.about_set.about_count_and_safe;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.main_code.about_login.LoginActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MineSetActivity;
import com.mding.core.pushservice.WsChannelService;
import com.mding.workservice.ChatService;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

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

        boolean b = StrUtils.validatePassword(newPwd);
        Log.e("validatePassword","我是否满足="+b+"");
        if (!b) {
//            ToastUtil.show("满足");
            DialogUtils.showDialog("密码至少要包括:\n字母、数字、标点符号\n的其中两项,长度为6-20位");
            return;
        }

//        DataMyZiliao.RecordBean recordBean = new DataMyZiliao.RecordBean();
//        String phone = recordBean.getMobile();
//        if (phone.equals(newPwd)){
//            DialogUtils.showDialog("密码不能与会员名相同");
//            return;
//        }

        sendWeb(SplitWeb.getSplitWeb().upPassWord(oldPwd,newPwd,surePwd));

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method){
            case "upPassWord":
                DialogUtils.showDialogOne("修改密码成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        WsChannelService.isBind = true;
                        BaseApplication.isHomeMsgFragment = true;
//                                    ChatService
//                                    unbindService(ChatService.this);
//                                    stopService(intent);
                        Intent intent2 = new Intent(ChangePwdActivity.this, ChatService.class);
                        stopService(intent2);// 关闭服务
//                                    unbindService(intent2);
//                                    ToastUtil.show("1");
                        SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
                        sendWeb(SplitWeb.getSplitWeb().kickUid());
                        SplitWeb.getSplitWeb().USER_ID = "";
                        SplitWeb.getSplitWeb().USER_TOKEN = "";
                        ACache.get(ChangePwdActivity.this).clear();
                        SPUtils.put(ChangePwdActivity.this, AppAllKey.USER_ID_KEY, "");
                        SPUtils.put(ChangePwdActivity.this, AppAllKey.USER_Token, "");
//                                    SPUtils.clear(MineSetActivity.this);
                        AppManager.getAppManager().onAppExit(ChangePwdActivity.this);
//                                    AppManager.getAppManager().finishAllActivity();
//                                    Intent intent_recharge = new Intent(MineSetActivity.this, LoginActivity.class);
//                                    startActivity(intent_recharge);
//                                    Log.e("userPhone","-------------mineSet-----------------"+userPhone);
                        String mPhone = (String)SPUtils.get(ChangePwdActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.getSplitWeb().MOBILE);
                        IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", mPhone);
                        overridePendingTransition(0, 0);


//                        SplitWeb.getSplitWeb().USER_ID="";
//                        AppManager.getAppManager().finishAllActivity();
//                        String mPhone = (String)SPUtils.get(ChangePwdActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.getSplitWeb().MOBILE);
//                        if (!StrUtils.isEmpty(mPhone))
//                            IntentUtils.JumpToHaveOne(LoginActivity.class,"phone",mPhone);
//
////                Intent intent_recharge = new Intent(ChangePwdActivity.this, LoginActivity.class);
////                startActivity(intent_recharge);
////                overridePendingTransition(0,0);
//                        ACache.get(ChangePwdActivity.this).clear();
//                        SPUtils.clear(ChangePwdActivity.this);
////                AppManager.getAppManager().finishActivity();
                    }
                });
                break;
        }

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
