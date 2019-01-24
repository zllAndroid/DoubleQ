package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.DefaultItemTouchHelpCallback;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.DefaultItemTouchHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.GroupManageAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.IDragListener;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_move.DragListView;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_move.GroupManageMoveAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_move.ViewUtil;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：联系人 - 分组信息
public class GroupManageActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener,GroupManageMoveAdapter.OnItemMoveClickListener {
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

    //    @BindView(R.id.group_recyc)
//    RecyclerView mRecyclerView;
    @BindView(R.id.draglist)
    DragListView mListView;


    String mShare = "1";
    public static String ManagerType = "type";
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
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
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(GroupManageActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupManageActivity.this));
        sendWeb(SplitWeb.groupManageInfo(type));


        initListView();
    }

    private void initListView() {


        mListView.setDragItemListener(new DragListView.SimpleAnimationDragItemListener() {

            private Rect mFrame = new Rect();
            private boolean mIsSelected;

            @Override
            public boolean canDrag(View dragView, int x, int y) {
                // 获取可拖拽的图标
                View dragger = dragView.findViewById(R.id.item_group_iv_move);
                if (dragger == null || dragger.getVisibility() != View.VISIBLE) {
                    return false;
                }
                float tx = x - ViewUtil.getX(dragView);
                float ty = y - ViewUtil.getY(dragView);
                dragger.getHitRect(mFrame);
                if (mFrame.contains((int) tx, (int) ty)) { // 当点击拖拽图标才可进行拖拽
                    return true;
                }
                return false;
            }


            @Override
            public void beforeDrawingCache(View dragView) {
                mIsSelected = dragView.isSelected();
                View drag = dragView.findViewById(R.id.item_group_iv_move);
                dragView.setSelected(true);
                if (drag != null) {
                    drag.setSelected(true);
                }
            }

            @Override
            public Bitmap afterDrawingCache(View dragView, Bitmap bitmap) {
                dragView.setSelected(mIsSelected);
                View drag = dragView.findViewById(R.id.item_group_iv_move);
                if (drag != null) {
                    drag.setSelected(false);
                }
                return bitmap;
            }

            @Override
            public boolean canExchange(int srcPosition, int targetPosition) {
                if (mListGroupInfo != null) {
                    // 更换数据源中的数据Item的位置
                    Collections.swap(mListGroupInfo, srcPosition, targetPosition);
                    // 更新UI中的Item的位置，主要是给用户看到交互效果
//                    mListGroupInfo.add(targetPosition,mListGroupInfo.remove(srcPosition));
//                    blackAdapter.notifyItemMoved(srcPosition, targetPosition);
                    View view = mListView.getChildAt(srcPosition);
                    View viewNew = mListView.getChildAt(targetPosition);

                    blackAdapter.getView(srcPosition, view, mListView);//核心方法
                    blackAdapter.getView(targetPosition, viewNew, mListView);//核心方法

                    includeTopTvRight.setVisibility(View.VISIBLE);
//                    blackAdapter.notifyDataSetChanged();
                    return  true;
                }
                return false;
//        }

//                boolean result = blackAdapter.exchange(srcPosition, targetPosition);
//                return result;
            }
        });


    }

    //    public List<DataBlack.RecordBean> record =null;
    List<DataGroupManage.RecordBean.GroupInfoBean> group_info;
    boolean isChange=false;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataGroupManage dataGroupManage = JSON.parseObject(responseText, DataGroupManage.class);
                DataGroupManage.RecordBean record = dataGroupManage.getRecord();
                mListGroupInfo.clear();
                if (record==null) {
                    return;
                }
                group_info = record.getGroupInfo();
                if (group_info.size() > 0) {
                    if (StrUtils.isEmpty(group_info.get(0).getId()))
                    {
                        group_info.remove(0);
                    }
                    mListGroupInfo.addAll(group_info);
                    if (isChange)
                    {
                        blackAdapter.notifyData();
                    }else
                        initAdapter();
                }
                break;
            case "moveGroupSort":
//                DialogUtils.showDialogOne("保存成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                ToastUtil.show("保存成功");
                AppManager.getAppManager().finishActivity();
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
                        isChange=true;
                        sendWeb(SplitWeb.groupManageInfo(type));
                        break;
//                        改
                    case "3":
                        isChange=true;
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
    GroupManageMoveAdapter blackAdapter = null;
    public int positions;
    List<DataGroupManage.RecordBean.GroupInfoBean> mListGroupInfo =new ArrayList<>();
    private void initAdapter() {
        blackAdapter = new GroupManageMoveAdapter(this, mListGroupInfo,this);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mListView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
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
                sendWeb(SplitWeb.addFriendGroup(type, "1", contant, ""));//增加分组  type = 1
                if(blackAdapter != null)
                    blackAdapter.notifyDataSetChanged();
                break;
            case "1"://修改
//                在 190行写 修改后触发事件
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
            tmpObj.put("group_name" , mListGroupInfo.get(i).getGroupName());
            tmpObj.put("id", mListGroupInfo.get(i).getId());
            tmpObj.put("sort_id", mListGroupInfo.get(i).getSortId());
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

    @Override
    public void onName(int position) {
     final    DataGroupManage.RecordBean.GroupInfoBean item = mListGroupInfo.get(position);
        isAddOrChange = "1";
        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(this, "修改分组", item.getGroupName());
        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowsign.setOnAddpopClickListener(new ChangeInfoWindow.OnAddContantClickListener() {
            @Override
            public void onSure(String contant) {
                item_type = "3";
                sendWeb(SplitWeb.addFriendGroup(type, "2", contant, item.getId()));//修改分组  type = 2
            }
            @Override
            public void onCancle() {
            }
        });
    }

    @Override
    public void onDelete(final int position) {
        DialogUtils.showDialog("是否删除此分组", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                positions = position;
                DataGroupManage.RecordBean.GroupInfoBean item = mListGroupInfo.get(position);
                String user_id = item.getId();
                if (!StrUtils.isEmpty(user_id)) {
                    item_type = "1";
                    sendWeb(SplitWeb.addFriendGroup(type, "3", "", user_id));//删除
                }
            }
        });
    }
}