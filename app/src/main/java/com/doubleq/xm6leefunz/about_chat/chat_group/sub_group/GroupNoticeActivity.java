package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataNotice;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 群公告页面
 * */
public class GroupNoticeActivity extends BaseActivity {

    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_lin_newback)
    LinearLayout includeTopLinNewback;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    @BindView(R.id.ac_iv_head)
    ImageView acIvHead;
    @BindView(R.id.ac_tv_name)
    TextView acTvName;
    @BindView(R.id.ac_lin_time)
    LinearLayout acLinTime;
    @BindView(R.id.ac_tv_time)
    TextView acTvTime;
    @BindView(R.id.ac_et_content)
    EditText acEtContent;
    @BindView(R.id.ac_lin_grouper_data)
    LinearLayout acLinGrouperData;


    String content;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_notice;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群公告");

        Intent intent = getIntent();
        if (intent != null) {
            groupofId = intent.getStringExtra("groupId");
            isGrouper = intent.getBooleanExtra("isGrouper", false);
            content = intent.getStringExtra("content");
            Log.e("groupid", "++++++++++++++groupid++++++++++++++" + groupofId);
            Log.e("isGrouper", "++++++++++++++isGrouper++++++++++++++" + isGrouper);
            Log.e("content", "++++++++++++++isGrouper++++++++++++++" + content);
            sendWeb(SplitWeb.groupNoticeInfo(groupofId));
        }
        initNotice();
        // 判断是否为群主（或管理员），是则可编辑群公告，不是则不可编辑群公告
        if (isGrouper) {
            acEtContent.setFocusable(true);
            acEtContent.setFocusableInTouchMode(true);
            acEtContent.requestFocus();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            acEtContent.addTextChangedListener(textWatcher);
            acEtContent.setSelection(acEtContent.getText().toString().length());
        }
        else {
            acEtContent.setFocusable(false);
            acEtContent.setFocusableInTouchMode(false);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    // 设置从群聊资料界面传来的群公告
    private void initNotice() {
        if (isGrouper){
            if (content != null && !content.equals("暂无公告")) {
                acEtContent.setText(content);
            }
            else {
                acLinGrouperData.setVisibility(View.GONE);
            }
        }
        else {
            if (content != null && !content.equals("暂无公告")) {
                acEtContent.setText(content);
            }
            else {
                acLinGrouperData.setVisibility(View.GONE);
                acEtContent.setText("暂无公告");
            }
        }
    }

    // 群公告编辑监听，有改变则显示右上角“保存”按钮
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 判断当下输入的内容是否与之前的内容相同并且不为空，相同或者为空则不出现保存按钮，否则显示
            if (!content.equals(editable.toString()) && !editable.toString().equals(""))
                incluTvRight.setVisibility(View.VISIBLE);
            else
                incluTvRight.setVisibility(View.INVISIBLE);
        }
    };

    String groupofId;

    @OnClick(R.id.inclu_tv_right)
    public void onViewClicked() {
        if (groupofId!=null){
            sendWeb(SplitWeb.editGroupNotice(groupofId, acEtContent.getText().toString()));
        }
        else
            ToastUtil.show("系统故障");
    }

    boolean isGrouper;
public  static final  String GROUP_NOTICES="group_notice";
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "editGroupNotice":
                DialogUtils.showDialogOne("保存成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        Log.e("noticeContent","-------------noticeContent--------------"+acEtContent.getText());
                        Intent intent = new Intent();
                        intent.putExtra(GROUP_NOTICES,acEtContent.getText().toString());
                        setResult(AppConfig.EDIT_GROUP_NOTICE_RESULT,intent);

                        AppManager.getAppManager().finishActivity(GroupNoticeActivity.this);
                    }
                });
                break;
            case "groupNoticeInfo":
                DataNotice dataNotice = JSON.parseObject(responseText, DataNotice.class);
                DataNotice.RecordBean noticeRecord = dataNotice.getRecord();
                if (noticeRecord != null) {
                    DataNotice.RecordBean.GroupInfoBean groupInfoBean = noticeRecord.getGroupInfo();
                    if (groupInfoBean != null) {
                        DataNotice.RecordBean.MemberInfoBean memberInfoBean = noticeRecord.getMemberInfo();
                        if (memberInfoBean != null) {
                            acTvTime.setText(groupInfoBean.getCreated());
                            acTvName.setText(memberInfoBean.getNickName());
                            Glide.with(this).load(memberInfoBean.getHeadImg())
                                    .bitmapTransform(new CropCircleTransformation(GroupNoticeActivity.this))
                                    .error(R.drawable.qun_head)
                                    .into(acIvHead);
                            Log.e("acTvTime", "++++++++++++++++++++++++++++" + acTvTime.getText());
                            Log.e("acTvName", "++++++++++++++++++++++++++++" + acTvName.getText());
                        }
                    }
                }
                break;
        }
    }


}
