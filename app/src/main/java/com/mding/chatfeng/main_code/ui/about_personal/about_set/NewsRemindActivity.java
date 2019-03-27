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


public class NewsRemindActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;

    @BindView(R.id.news_switch_jieshou_news)
    SwitchButton newsSwitchJieshouNews;
    @BindView(R.id.news_switch_jieshou_yuyin)
    SwitchButton newsSwitchJieshouYuyin;
    @BindView(R.id.news_switch_jieshou_video)
    SwitchButton newsSwitchJieshouVideo;
//    @BindView(R.id.news_switch_xianshi)
//    SwitchButton newsSwitchXianshi;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("消息提醒");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick())
                    sendWeb( SplitWeb.permissionSetTwo("2",isMsg,isVoice,isVideo));
            }
        });
        switchListen();
        sendWeb( SplitWeb.getPermissStatu("2"));
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
                    String is_msg_remind = record.getIsMsgRemind();
                    String is_voice_remind = record.getIsVoiceRemind();
                    String is_video_remind = record.getIsVideoRemind();
                    newsSwitchJieshouNews.setChecked(is_msg_remind.equals("1"));
                    newsSwitchJieshouYuyin.setChecked(is_voice_remind.equals("1"));
                    newsSwitchJieshouVideo.setChecked(is_video_remind.equals("1"));
                }
                break;
            case "permissionSet":
//                DialogUtils.showDialogOne("消息提醒设置成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                ToastUtil.show("消息提醒设置成功！");
                AppManager.getAppManager().finishActivity();
                break;
        }
    }
    String isMsg = "1";
    String isVoice = "1";
    String isVideo = "1";
    private void switchListen() {
        newsSwitchJieshouNews.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isMsg = "1";
//                    ToastUtil.show("接收新消息通知打开");
                }else{
                    isMsg = "0";
//                    ToastUtil.show("接收新消息通知关闭");
                }
            }
        });
        newsSwitchJieshouYuyin.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isVoice = "1";
//                    ToastUtil.show("语音通知打开");
                }else{
                    isVoice = "0";
//                    ToastUtil.show("语音通知关闭");
                }
            }
        });
        newsSwitchJieshouVideo.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isVideo = "1";
//                    ToastUtil.show("视频通知打开");
                }else{
                    isVideo = "0";
//                    ToastUtil.show("视频通知关闭");
                }
            }
        });
//        newsSwitchXianshi.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if(isChecked){
//                    ToastUtil.show("推送显示内容打开");
//                }else{
//                    ToastUtil.show("推送显示内容关闭");
//                }
//            }
//        });
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_news_remind;
    }

}
