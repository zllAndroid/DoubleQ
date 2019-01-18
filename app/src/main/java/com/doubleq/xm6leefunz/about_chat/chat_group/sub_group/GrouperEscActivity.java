package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.GroupChatDetailsActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.GroupTeamActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 群设置-编辑群聊资料
 */
public class GrouperEscActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String  groupId = null;
    String  groupName = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("退出该群");
        Intent intent = getIntent();
        if (intent != null) {
            groupId=intent.getStringExtra(AppConfig.GROUPER_ESC);
            groupName=intent.getStringExtra(AppConfig.GROUPER_NAME);
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_grouper_esc;
    }


    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addGroupOf":
                DialogUtils.showDialog("申请入群成功");
                break;
            case "outGroupChat":
                DialogUtils.showDialogOne("已解散该群", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        RealmHomeHelper realmHomeHelper = new RealmHomeHelper(GrouperEscActivity.this);
                        realmHomeHelper.deleteRealmMsg(groupId);
                        Intent intent2 = new Intent();
                        intent2.putExtra("id",groupId);
                        intent2.setAction("del.refreshMsgFragment");
                        sendBroadcast(intent2);
                        AppManager.getAppManager().finishActivity(GrouperEscActivity.this);
                        AppManager.getAppManager().finishActivity(ChatGroupActivity.class);
                    }
                });
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.group_esc_tv_zhuanrang, R.id.group_esc_tv_jiesan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_esc_tv_zhuanrang:
                IntentUtils.JumpToHaveTwo(ZhuanRangGroupActivity.class, ZhuanRangGroupActivity.GROUP_ID, groupId,ZhuanRangGroupActivity.GROUP_NAME,groupName);
                AppManager.getAppManager().finishActivity();

                break;
            case R.id.group_esc_tv_jiesan:
                DialogUtils.showDialog("是否确认解散该群", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        sendWebHaveDialog(SplitWeb.outGroupChat(groupId), "正在解散...", "解散成功");
                    }
                });
                break;
        }
    }
}
