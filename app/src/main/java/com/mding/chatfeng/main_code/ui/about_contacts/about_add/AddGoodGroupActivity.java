package com.mding.chatfeng.main_code.ui.about_contacts.about_add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.FriendGroupListActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请入群后进入确认发送页面
 */
public class AddGoodGroupActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fda_iv_head)
    ImageView fdaIvHead;
    @BindView(R.id.fda_tv_name)
    TextView fdaTvName;
    @BindView(R.id.fda_ed_yanzheng)
    EditText fdaEdYanzheng;
//    @BindView(R.id.fda_ed_beizhu)
//    EditText fdaEdBeizhu;
    @BindView(R.id.fda_tv_group)
    TextView fdaTvGroup;
    @BindView(R.id.fda_lin_main)
    LinearLayout mLinMain;

    public static String DataKey = "addgroup";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch=null;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("验证信息");
        incluTvRight.setVisibility(View.VISIBLE);
        incluTvRight.setText("发送");
        Intent intent = getIntent();
        if (intent != null) {
             dataSearch = (DataSearch) intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
            if (dataSearch==null)
                return;
            ImageUtils.useBase64WithError(this, fdaIvHead, dataSearch.getHeadImg(), R.drawable.mine_head);
//            Glide.with(this).load(dataSearch.getHeadImg()).error(R.drawable.mine_head).into(fdaIvHead);
            fdaTvName.setText(dataSearch.getName());

        }
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_group_send;
    }
//点击发送
    @OnClick(R.id.inclu_tv_right)
    public void onSend() {
        String yanzheng = fdaEdYanzheng.getText().toString().trim();
        sendWeb(SplitWeb.addGroupOf(dataSearch.getSno(),yanzheng));
    }
//点击分组
    @OnClick(R.id.fda_tv_group)
    public void onGroup() {
        Intent intent = new Intent(this, FriendGroupListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.KEY_FRIEND_GROUP, AppConfig.VALUE_GROUP);
        intent.putExtras(bundle);
        startActivityForResult(intent, AppConfig.GROUP_ADD_GROUP_REQUEST);
    }
    String ids=null;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addGroupOf":
                DialogUtils.showDialogOne("申请入群成功,等待群主确认", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(AddGoodGroupActivity.this);
                    }
                });
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.FRIEND_ADD_GROUP_RESULT) {
            if (requestCode == AppConfig.GROUP_ADD_GROUP_REQUEST) {
//            if (requestCode == AppConfig.FRIEND_ADD_GROUP_REQUEST) {
                String name = data.getStringExtra(FriendGroupListActivity.CHOOSE_NAME);
                String id = data.getStringExtra(FriendGroupListActivity.CHOOSE_ID);
                fdaTvGroup.setText(name);
                ids=id;
                //设置结果显示框的显示数值
            }
        }
    }
}
