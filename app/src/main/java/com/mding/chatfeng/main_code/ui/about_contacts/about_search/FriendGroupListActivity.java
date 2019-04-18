package com.mding.chatfeng.main_code.ui.about_contacts.about_search;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.model.DataFriendGroup;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.AddFriendFenzuAdapter;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：
public class FriendGroupListActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.choose_group_lin_main)
    LinearLayout mLinMain;

    @BindView(R.id.choose_group_recyc_fenzu)
    RecyclerView mRecyclerView;
    @BindView(R.id.choose_group_tv_nothing)
    TextView chooseGroupTvNothing;
    @BindView(R.id.choose_group_tv_add)
    TextView chooseGroupTvAdd;
    @BindView(R.id.choose_group_lin_fenzu)
    LinearLayout chooseGroupLinFenzu;

//    @BindView(R.id.share_switch_accept)
//    SwitchButton shareSwitchAccept;
//    @BindView(R.id.share_switch_nouse)
//    SwitchButton shareSwitchNouse;
//    @BindView(R.id.share_switch_no)
//    SwitchButton shareSwitchNo;

    String mShare = "1";
    public static String CHOOSE_GROUP_KEY = "choosegroup";
    public static String CHOOSE_NAME = "groupname";
    public static String CHOOSE_ID = "groupid";
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    String groupType = "";
    String addType = "1";

    @Override
    protected void initBaseView() {
        super.initBaseView();

        includeTopTvTital.setText("选择分组");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FriendGroupListActivity.this));
        Intent intent = getIntent();
        if (intent != null) {
            groupType = intent.getStringExtra(AppConfig.KEY_FRIEND_GROUP);
            if (!StrUtils.isEmpty(groupType))
                sendWeb(SplitWeb.getSplitWeb().groupManageInfo(groupType));
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataFriendGroup dataGroupManage = JSON.parseObject(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean record = dataGroupManage.getRecord();
                if (record == null) {
                    return;
                }
                List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo = record.getGroupInfo();
                if (groupInfo.size() > 0) {
                    if (StrUtils.isEmpty(groupInfo.get(0).getId())) {
                        groupInfo.remove(0);
                    }
                    if (groupInfo.size() > 0) {
                        chooseGroupLinFenzu.setVisibility(View.VISIBLE);
                        chooseGroupTvNothing.setVisibility(View.GONE);
                        chooseGroupTvAdd.setVisibility(View.GONE);
                        initAdapter(groupInfo);
                    } else {
                        chooseGroupLinFenzu.setVisibility(View.GONE);
                        chooseGroupTvNothing.setVisibility(View.VISIBLE);
                        chooseGroupTvAdd.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "addFriendGroup":
                sendWeb(SplitWeb.getSplitWeb().groupManageInfo(groupType));
                break;
        }
    }

    AddFriendFenzuAdapter blackAdapter = null;
    DataFriendGroup.RecordBean.GroupInfoBean item;
    public int positions;

    private void initAdapter(List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo) {
        blackAdapter = new AddFriendFenzuAdapter(this, groupInfo);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击同意");
                item = (DataFriendGroup.RecordBean.GroupInfoBean) adapter.getItem(position);
                dealItemClick(item);
            }
        });
    }

    private void dealItemClick(DataFriendGroup.RecordBean.GroupInfoBean item) {
        Intent intent = new Intent();
        // 获取用户计算后的结果
        intent.putExtra(CHOOSE_NAME, item.getGroupName());
        intent.putExtra(CHOOSE_ID, item.getId());
        setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
        AppManager.getAppManager().finishActivity(FriendGroupListActivity.this);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_choose_group;
    }

    @OnClick(R.id.choose_group_tv_add)
    public void onViewClicked() {
        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(FriendGroupListActivity.this, "增加分组", "");
        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowsign.setOnAddpopClickListener(new ChangeInfoWindow.OnAddContantClickListener() {
            @Override
            public void onSure(String contant) {
                sendWeb(SplitWeb.getSplitWeb().addFriendGroup(groupType, addType, contant, ""));//增加分组  type = 1
                if (blackAdapter != null)
                    blackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancle() {

            }
        });
    }
}
