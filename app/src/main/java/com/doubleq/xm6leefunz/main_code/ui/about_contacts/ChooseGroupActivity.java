package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataFriendGroup;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.AddFriendFenzuAdapter;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//位置：联系人 - 通知
public class ChooseGroupActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;

    @BindView(R.id.choose_group_recyc_fenzu)
    RecyclerView mRecyclerView;

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
    String type;
    @BindView(R.id.choose_group_tv_nothing)
    TextView chooseGroupTvNothing;

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
            type = intent.getStringExtra("type");
            Log.e("groupingName", "-------------------------type-----------------------------" + type);
            if (type.equals("1")) {
                FriendId = intent.getStringExtra("FriendId");
                sendWeb(SplitWeb.friendGroupList(FriendId));
            } else {
                GroupId = intent.getStringExtra("groupId");
                sendWeb(SplitWeb.groupOfGroupList(GroupId));
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
                    initAdapter(groupInfo);
                } else {
                    mRecyclerView.setVisibility(View.GONE);
                    chooseGroupTvNothing.setVisibility(View.VISIBLE);
                }
                break;
            case "friendGroupModify":
                dealItemClick(item);
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
                    initAdapter(groupInfos);
                }else {
                    mRecyclerView.setVisibility(View.GONE);
                    chooseGroupTvNothing.setVisibility(View.VISIBLE);
                }
                break;
            case "groupOfGroupModify":
                dealItemClick(item);
                break;
        }
    }

    AddFriendFenzuAdapter blackAdapter = null;
    DataFriendGroup.RecordBean.GroupInfoBean item;
    public int positions;

    private void initAdapter(List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo) {
        blackAdapter = new AddFriendFenzuAdapter(groupInfo);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击同意");
                item = (DataFriendGroup.RecordBean.GroupInfoBean) adapter.getItem(position);
                Log.e("groupingName", "-------------------------type----------111-------------------" + type);
                if (type.equals("1")) {
                    if (!StrUtils.isEmpty(FriendId)) {
                        sendWebHaveDialog(SplitWeb.friendGroupModify(FriendId, item.getId()), "设置分组中...", "设置成功");
                    } else {
                        dealItemClick(item);
                    }
                } else {
                    if (!StrUtils.isEmpty(GroupId)) {
                        sendWebHaveDialog(SplitWeb.groupOfGroupModify(GroupId, item.getId()), "设置分组中...", "设置成功");
                    } else {
                        dealItemClick(item);
                    }
                }

//                positions=position;
//                DataBlack.RecordBean item = (DataBlack.RecordBean)adapter.getItem(position);
//                String user_id = item.getUser_id();
//                if (!StrUtils.isEmpty(user_id))
//                    sendWeb(SplitWeb.removeBlack(user_id));
            }
        });
    }

    private void dealItemClick(DataFriendGroup.RecordBean.GroupInfoBean item) {

        if (item.getFriendGroup().equals("2")) {
            ToastUtil.show("好友已经在该分组");
            return;
        }
        Log.e("groupingName", "-------------------------type------------222-----------------" + type);
        if (type.equals("1")) {
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
        } else {
            if (item.getId().equals("0")) {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME_GROUP, "暂无");
                intent.putExtra(CHOOSE_ID_GROUP, item.getId());
                setResult(AppConfig.GROUP_DATA_GROUPING_REQUEST, intent);
                AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
            } else {
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra(CHOOSE_NAME_GROUP, item.getGroupName());
                intent.putExtra(CHOOSE_ID_GROUP, item.getId());
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
    public interface OnModifyGroupListener {
        void groupingName(String groupingName);
    }

    public void setOnModifyGroupListener(OnModifyGroupListener modifyGroupListener) {
        this.modifyGroupListener = modifyGroupListener;
    }
}
