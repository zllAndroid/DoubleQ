package com.mding.chatfeng.about_chat.chat_group.sub_group;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 群设置-编辑群聊资料
 */
public class GroupChatSetActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;

    public static String DataKey = "addgroup";
    @BindView(R.id.group_set_iv_head)
    ImageView groupSetIvHead;
    @BindView(R.id.group_set_tv_name)
    TextView groupSetTvName;
    @BindView(R.id.group_set_lin_change_head)
    LinearLayout groupSetLinChangeHead;
    @BindView(R.id.group_set_ed_groupname)
    EditText groupSetEdGroupname;
    @BindView(R.id.group_set_lin_main)
    LinearLayout groupSetLinMain;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("验证信息");
        incluTvRight.setVisibility(View.VISIBLE);
        incluTvRight.setText("提交");
//        Intent intent = getIntent();
//        if (intent != null) {
//             dataSearch = (DataSearch) intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
////            dataSearch.getHead_img()
//            if (dataSearch==null)
//                return;
//            Glide.with(this).load(dataSearch.getHeadImg()).error(R.drawable.mine_head).into(fdaIvHead);
//        groupSetEdGroupname.setText(dataSearch.getName());
//        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_chat_set;
    }

    //点击发送
    @OnClick(R.id.inclu_tv_right)
    public void onSend() {
        ToastUtil.show("我点击了发送");
//        String yanzheng = fdaEdYanzheng.getText().toString().trim();
////        String remark = fdaEdBeizhu.getText().toString().trim();
//        sendWeb(SplitWeb.addGroupOf(dataSearch.getSno(),yanzheng));
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addGroupOf":
                DialogUtils.showDialog("申请入群成功");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.group_set_lin_change_head, R.id.group_set_ed_groupname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_set_lin_change_head:

                break;
            case R.id.group_set_ed_groupname:

                break;
        }
    }
}
