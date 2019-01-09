package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.CallbackItemTouch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.GroupManageAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.MyItemTouchHelperCallback;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：联系人 - 分组信息
public class GroupManageActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.group_manage_tv_add)
    TextView mTvAdd;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.group_manage_lin_main)
    LinearLayout mLinMain;

    @BindView(R.id.group_recyc)
    RecyclerView mRecyclerView;


    String mShare = "1";
    public static String ManagerType = "type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String type ="1";
    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        type = intent.getStringExtra(ManagerType);
        if (type.equals("1"))
        {
            includeTopTvTital.setText("好友分组管理");
        }else
        {
            includeTopTvTital.setText("群组分组管理");
        }
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(GroupManageActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupManageActivity.this));
        sendWeb(SplitWeb.groupManageInfo(type));
    }

    //    public List<DataBlack.RecordBean> record =null;
    List<DataGroupManage.RecordBean.GroupInfoBean> group_info;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataGroupManage dataGroupManage = JSON.parseObject(responseText, DataGroupManage.class);
                DataGroupManage.RecordBean record = dataGroupManage.getRecord();
                group_info = record.getGroupInfo();

                if (record != null && group_info.size() > 0) {
                    initAdapter(group_info);
                }
                break;
            case "moveGroupSort":
                DialogUtils.showDialogOne("保存成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity();
                    }
                });
                break;
            case "addFriendGroup":
                switch (item_type) {
//                    删除
                    case "1":
                        if (blackAdapter != null) {
                            blackAdapter.delItem(positions);
                        }
                        break;
//                        添加
                    case "2":
                        sendWeb(SplitWeb.groupManageInfo(type));
                        break;
//                        改
                    case "3":
                        sendWeb(SplitWeb.groupManageInfo(type));
                        break;
                }
                break;
            default:
                break;
        }
    }

    //    1为删除
    String item_type = "1";
    GroupManageAdapter blackAdapter = null;
    public int positions;

    private void initAdapter(final List<DataGroupManage.RecordBean.GroupInfoBean> group_info) {
        blackAdapter = new GroupManageAdapter(this, group_info);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击行");
            }
        });
        blackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final DataGroupManage.RecordBean.GroupInfoBean item = (DataGroupManage.RecordBean.GroupInfoBean) adapter.getItem(position);
                String disabledStatus = item.getDisabledStatus();
                if (disabledStatus.equals("1"))
                {
                    ToastUtil.show("默认分组不可删除与修改");
                    return;
                }
                RecyclerView recyclerView = mRecyclerView;
                switch (view.getId()) {
                    case R.id.item_group_iv_del:
                        DialogUtils.showDialog("是否删除此分组", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                                positions = position;
                                String user_id = item.getId();
                                if (!StrUtils.isEmpty(user_id)) {
                                    item_type = "1";
                                    sendWeb(SplitWeb.addFriendGroup(type, "3", "", user_id));//删除
                                }
                            }
                        });

                        break;
                    case R.id.item_group_iv_move:

                        break;
                    case R.id.item_group_tv_groupname:
                        isAddOrChange = "1";
                        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(GroupManageActivity.this, "修改分组", item.getGroupName());
                        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
                        changeInfoWindowsign.setOnAddpopClickListener(new ChangeInfoWindow.OnAddContantClickListener() {
                            @Override
                            public void onSure(String contant) {
                                item_type = "2";
                                sendWeb(SplitWeb.addFriendGroup(type, "2", contant, item.getId()));//增加
                            }
                            @Override
                            public void onCancle() {
                            }
                        });
                        break;
                }
            }
        });
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(new CallbackItemTouch() {
            @Override
            public void itemTouchOnMove(int oldPosition, int newPosition) {

                try {
                    group_info.add(newPosition, group_info.remove(oldPosition));// change position
                    blackAdapter.notifyItemMoved(oldPosition, newPosition); //notifies changes in adapter, in this case use the notifyItemMoved
                    includeTopTvRight.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });// create MyItemTouchHelperCallback
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback); // Create ItemTouchHelper and pass with parameter the MyItemTouchHelperCallback
        touchHelper.attachToRecyclerView(mRecyclerView); // Attach ItemTouchHelper to RecyclerView
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_manage;
    }

    @OnClick(R.id.group_manage_tv_add)
    public void onViewClicked() {
        isAddOrChange = "0";
        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(GroupManageActivity.this, "增加分组", "");
        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowsign.setOnAddpopClickListener(this);
    }

    //    默认为增加分组（字段判断点击的popwindow）
    String isAddOrChange = "0";
    @Override
    public void onSure(String contant) {
        switch (isAddOrChange) {
            case "0"://增加
                item_type = "2";
                sendWeb(SplitWeb.addFriendGroup(type, "1", contant, ""));//增加
                break;
            case "1"://修改
//                在 155行写 修改后触发事件
//                sendWeb(SplitWeb.upUserSno(contant));
                break;
        }
    }
    @Override
    public void onCancle() {

    }
    public String dataInfo(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject tmpObj = null;
        jsonArray.clear();
        int count = group_info.size();
        for(int i = 0; i < count; i++)
        {
            /**
             * id : 1
             * group_name : 好友
             * sort_id : 1
             */
            tmpObj = new JSONObject();
            tmpObj.put("group_name" , group_info.get(i).getGroupName());
            tmpObj.put("id", group_info.get(i).getId());
            tmpObj.put("sort_id", group_info.get(i).getSortId());
            jsonArray.add(tmpObj);
            tmpObj = null;
        }
        String personInfos = jsonArray.toString(); // 将JSONArray转换得到String
        jsonObject.put("personInfos" , personInfos);   // 获得JSONObject的String
        return personInfos;
    }
    @OnClick(R.id.inclu_tv_right)
    public void onSave() {
        sendWeb(SplitWeb.moveGroupSort(dataInfo()));//拖拽移动的
        Log.e("personInfos","----------------personInfos----------------------------"+dataInfo());
    }
}