package com.mding.chatfeng.main_code.ui.about_contacts;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.AddFriendFenzuAdapter;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.mding.model.DataFriendGroup;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：联系人 - 通知
public class ChooseGroupActivity extends BaseActivity {
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
    public static String CHOOSE_NAME_GROUP = "groupName";
    public static String CHOOSE_ID_GROUP = "groupId";
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String FriendId = "";
    String GroupId = "";
    String groupType;
    String addType = "1";
    boolean isAdd = true;
    @Override
    protected void initBaseView() {
        super.initBaseView();

        includeTopTvTital.setText("选择分组");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ChooseGroupActivity.this));
        Intent intent = getIntent();
        if (intent != null) {
//            好友选择分组 1       群选择分组 2
            groupType = intent.getStringExtra("type");
            Log.e("groupingName", "-------------------------groupType-----------------------------" + groupType);
            if (groupType.equals("1")) {
                FriendId = intent.getStringExtra("FriendId");
                sendWeb(SplitWeb.getSplitWeb().friendGroupList(FriendId));
            } else {
                GroupId = intent.getStringExtra("groupId");
                sendWeb(SplitWeb.getSplitWeb().groupOfGroupList(GroupId));
            }
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "friendGroupList":
                DataFriendGroup dataFriendGroupManage = JSON.parseObject(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean record = dataFriendGroupManage.getRecord();
                if (record == null) {
                    return;
                }
                List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo = record.getGroupInfo();
                if (groupInfo.size() > 0) {
                    if (StrUtils.isEmpty(groupInfo.get(0).getId())) {
                        groupInfo.remove(0);
                    }
                    if (groupInfo.size() > 0) {
                        initAdapter(groupInfo);
                    } else {
                        chooseGroupLinFenzu.setVisibility(View.GONE);
                        chooseGroupTvNothing.setVisibility(View.VISIBLE);
                        chooseGroupTvAdd.setVisibility(View.VISIBLE);
                    }
                }
//                else {
//                    Log.e("chooseGroupActivity","------------------------------------------------"+groupType);
//                    mRecyclerView.setVisibility(View.GONE);
//                    chooseGroupTvNothing.setVisibility(View.VISIBLE);
//                    chooseGroupTvAdd.setVisibility(View.VISIBLE);
//                }
                break;
            case "friendGroupModify":
                dealItemClick();
                break;
            case "groupOfGroupList":
                DataFriendGroup dataGroupManage = JSON.parseObject(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean records = dataGroupManage.getRecord();
                if (records == null) {
                    return;
                }
                List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfos = records.getGroupInfo();
                if (groupInfos.size() > 0) {
                    if (StrUtils.isEmpty(groupInfos.get(0).getId())) {
                        groupInfos.remove(0);
                    }
                    if (groupInfos.size() > 0) {
                        initAdapter(groupInfos);
                    } else {
                        chooseGroupLinFenzu.setVisibility(View.GONE);
                        chooseGroupTvNothing.setVisibility(View.VISIBLE);
                        chooseGroupTvAdd.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "groupOfGroupModify":
                dealItemClick();
                break;
            case "addFriendGroup":
                isAdd = false;
                sendWeb(SplitWeb.getSplitWeb().groupManageInfo(groupType));
                break;
            case "groupManageInfo":
                DataFriendGroup dataGroupManages = JSON.parseObject(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean Record = dataGroupManages.getRecord();
//                mListGroupInfo.clear();
                if (Record == null) {
                    return;
                }
                group_info = Record.getGroupInfo();
                if (group_info.size() > 0) {
                    if (StrUtils.isEmpty(group_info.get(0).getId())) {
                        group_info.remove(0);
                    }
                    if (group_info.size() > 0){
                        chooseGroupLinFenzu.setVisibility(View.VISIBLE);
                        chooseGroupTvNothing.setVisibility(View.GONE);
                        chooseGroupTvAdd.setVisibility(View.GONE);
                        initAdapter(group_info);
                    }else {
                        chooseGroupLinFenzu.setVisibility(View.GONE);
                        chooseGroupTvNothing.setVisibility(View.VISIBLE);
                        chooseGroupTvAdd.setVisibility(View.VISIBLE);
                    }
                }

                break;
        }
    }

    //    List<DataFriendGroup.RecordBean.GroupInfoBean> mListGroupInfo = new ArrayList<>();
    List<DataFriendGroup.RecordBean.GroupInfoBean> group_info;
    AddFriendFenzuAdapter blackAdapter = null;
    DataFriendGroup.RecordBean.GroupInfoBean mItem;
    public int positions;

    private void initAdapter(List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo) {
        blackAdapter = new AddFriendFenzuAdapter(this,groupInfo);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击同意");
                mItem = (DataFriendGroup.RecordBean.GroupInfoBean) adapter.getItem(position);
                if (groupType.equals("1")) {
                    if (!StrUtils.isEmpty(FriendId)) {
                        sendWebHaveDialog(SplitWeb.getSplitWeb().friendGroupModify(FriendId, mItem.getId()), "设置分组中...", "设置成功");
                    } else {
                        dealItemClick();
                    }
                } else {
                    if (!StrUtils.isEmpty(GroupId)) {
                        sendWebHaveDialog(SplitWeb.getSplitWeb().groupOfGroupModify(GroupId, mItem.getId()), "设置分组中...", "设置成功");
                    } else {
                        dealItemClick();
                    }
                }

//                positions=position;
//                DataBlack.RecordBean item = (DataBlack.RecordBean)adapter.getItem(position);
//                String user_id = item.getUser_id();
//                if (!StrUtils.isEmpty(user_id))
//                    sendWeb(SplitWeb.getSplitWeb().removeBlack(user_id));
            }
        });
    }

    private void dealItemClick() {
        if(isAdd){
            if (mItem.getFriendGroup().equals("2")) {
                ToastUtil.show("好友已经在该分组");
                return;
            }
        }
        if (groupType.equals("1")) {
            if (mItem.getId().equals("0")) {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME, "暂无");
                intent.putExtra(CHOOSE_ID, mItem.getId());
                setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
                AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
            } else {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME, mItem.getGroupName());
                intent.putExtra(CHOOSE_ID, mItem.getId());
                setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
                AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
            }
        } else {
            if (mItem.getId().equals("0")) {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME_GROUP, "暂无");
                intent.putExtra(CHOOSE_ID_GROUP, mItem.getId());
                setResult(AppConfig.GROUP_DATA_GROUPING_REQUEST, intent);
                AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
            } else {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME_GROUP, mItem.getGroupName());
                intent.putExtra(CHOOSE_ID_GROUP, mItem.getId());
                setResult(AppConfig.GROUP_DATA_GROUPING_REQUEST, intent);
                AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
            }
        }


    }

    private void dealGroupItemClick(DataFriendGroup.RecordBean.GroupInfoBean item) {

        if (item.getFriendGroup().equals("2")) {
            ToastUtil.show("好友已经在该分组");
            return;
        }
        if (item.getId().equals("0")) {
            Intent intent = new Intent();
            // 获取用户计算后的结果
            intent.putExtra(CHOOSE_NAME, "暂无");
            intent.putExtra(CHOOSE_ID, item.getId());
            setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
            AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
        } else {
            Intent intent = new Intent();
            // 获取用户计算后的结果
            intent.putExtra(CHOOSE_NAME, item.getGroupName());
            intent.putExtra(CHOOSE_ID, item.getId());
            setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
            AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
        }

    }
//    private void dealItemTypeClick(DataFriendGroup.RecordBean.GroupInfoBean item) {
//
//        if (item.getFriendGroup().equals("2"))
//        {
//            ToastUtil.show("好友已经在该分组");
//            return;
//        }
//        if (item.getId().equals("0"))
//        {
//            modifyGroupListener.groupingName("暂无");
////            Intent intent = new Intent();
////            // 获取用户计算后的结果
////            intent.putExtra(CHOOSE_NAME, "暂无");
////            intent.putExtra(CHOOSE_ID, item.getId());
////            setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
//            AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
//        }else {
//            modifyGroupListener.groupingName( item.getGroupName());
////            Intent intent = new Intent();
////            // 获取用户计算后的结果
////            intent.putExtra(CHOOSE_NAME, item.getGroupName());
////            intent.putExtra(CHOOSE_ID, item.getId());
////            setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
//            AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
//        }
//
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_choose_group;
    }

    //修改好友分组事件回调接口
    OnModifyGroupListener modifyGroupListener = null;

    @OnClick(R.id.choose_group_tv_add)
    public void onViewClicked() {
        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(ChooseGroupActivity.this, "增加分组", "");
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

    public interface OnModifyGroupListener {
        void groupingName(String groupingName);
    }

    public void setOnModifyGroupListener(OnModifyGroupListener modifyGroupListener) {
        this.modifyGroupListener = modifyGroupListener;
    }
}
