package com.mding.chatfeng.main_code.ui.about_personal.about_set;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mding.model.DataSetAbout;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.suke.widget.SwitchButton;

import butterknife.BindView;

/**
 * 隐私设置界面
 */
public class YinSiActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;

    @BindView(R.id.yinsi_switch_count)
    SwitchButton mSwitchCount;
    @BindView(R.id.yinsi_switch_phone)
    SwitchButton mSwitchPhone;
    @BindView(R.id.yinsi_switch_ercode)
    SwitchButton mSwitcherCode;

    String isSno = "1";
    String isPhone = "1";
    String isQrcode = "1";
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("隐私设置");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendWeb( SplitWeb.getSplitWeb().getPermissStatu("3"));
                if (NoDoubleClickUtils.isDoubleClick())
                    sendWeb(SplitWeb.getSplitWeb().permissionSetThr("3",isSno,isQrcode));
            }
        });
        switchListen();
        initRequest();
    }


    private void initRequest() {
        sendWeb( SplitWeb.getSplitWeb().getPermissStatu("3"));
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "getPermissStatu":
                DataSetAbout dataSetAbout = JSON.parseObject(responseText, DataSetAbout.class);
                DataSetAbout.RecordBean record = dataSetAbout.getRecord();
                if (record!=null)
                {
                    String is_sno_show = record.getIsSnoShow();
                    String is_qrcode_show = record.getIsQrcodeShow();
                    mSwitchCount.setChecked(is_sno_show.equals("1"));
                    mSwitcherCode.setChecked(is_qrcode_show.equals("1"));
                }
                break;
            case "permissionSet":
//                DialogUtils.showDialogOne("隐私设置成功", new DialogUtils.OnClickSureListener() {
//                @Override
//                public void onClickSure() {
//                    AppManager.getAppManager().finishActivity();
//                }
//            });
                ToastUtil.show("隐私设置成功！");
                AppManager.getAppManager().finishActivity();
                break;
        }
    }

    private void switchListen() {
        mSwitchCount.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isSno = "1";
//                    ToastUtil.show("帐号显示打开");
                }else{
                    isSno = "0";
//                    ToastUtil.show("帐号显示关闭");
                }
            }
        });
        mSwitchPhone.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isPhone = "1";
//                    ToastUtil.show("手机号显示打开");
                }else{
                    isPhone = "0";
//                    ToastUtil.show("手机号显示关闭");
                }
            }
        });
        mSwitcherCode.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isQrcode = "1";
//                    ToastUtil.show("二维码显示打开");
                }else{
                    isQrcode = "0";
//                    ToastUtil.show("二维码显示关闭");
                }
            }
        });
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_yinsi;
    }

}
