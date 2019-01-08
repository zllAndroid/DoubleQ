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
import com.doubleq.model.DataBlack;
import com.doubleq.model.DataFriendGroup;
import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.AddFriendFenzuAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.PopFenzuAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice.NoticeAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    String mShare= "1";
    public  static  String CHOOSE_GROUP_KEY = "choosegroup";
    public  static  String CHOOSE_NAME = "groupname";
    public  static  String CHOOSE_ID= "groupid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String string="";
    @Override
    protected void initBaseView() {
        super.initBaseView();


        includeTopTvTital.setText("选择分组");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ChooseGroupActivity.this));
        Intent intent = getIntent();
        if (intent!=null)
        {
                string = intent.getStringExtra("string");
        }
        sendWeb(SplitWeb.groupManageInfo("1"));
    }
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataFriendGroup dataGroupManage = JSON.parseObject(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean record = dataGroupManage.getRecord();
                List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo =  record.getGroupInfo();

                if (record != null && groupInfo.size() > 0) {
                    initAdapter(groupInfo);
                }
                break;
            case "friendGroupModify":
                dealItemClick(item);
                break;
        }
    }
    AddFriendFenzuAdapter blackAdapter =null;
    DataFriendGroup.RecordBean.GroupInfoBean item;
    public int positions;
    private void initAdapter( List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo) {
        blackAdapter = new AddFriendFenzuAdapter(groupInfo);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击同意");
                item =(DataFriendGroup.RecordBean.GroupInfoBean) adapter.getItem(position);
                if (!StrUtils.isEmpty(string))
                {
                    sendWebHaveDialog(SplitWeb.friendGroupModify(string,item.getId()),"设置分组中...","设置成功");
                }else
                {
                    dealItemClick(item);
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
        Intent intent = new Intent();
        // 获取用户计算后的结果
        intent.putExtra(CHOOSE_NAME, item.getGroupName());
        intent.putExtra(CHOOSE_ID, item.getId());
        setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
        AppManager.getAppManager().finishActivity(ChooseGroupActivity.this);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_choose_group;
    }
}
