package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
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
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.DefaultItemTouchHelpCallback;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.DefaultItemTouchHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.GroupManageAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_group.MyItemTouchHelperCallback;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.doubleq.xm6leefunz.main_code.ui.about_pop.PopAddWindow;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(GroupManageActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupManageActivity.this));
        sendWeb(SplitWeb.groupManageInfo(type));
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
    GroupManageAdapter blackAdapter = null;
    public int positions;
    List<DataGroupManage.RecordBean.GroupInfoBean> mListGroupInfo =new ArrayList<>();
    private void initAdapter() {
        blackAdapter = new GroupManageAdapter(this, mListGroupInfo);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
                                item_type = "3";
                                sendWeb(SplitWeb.addFriendGroup(type, "2", contant, item.getId()));//修改分组  type = 2
                            }
                            @Override
                            public void onCancle() {
                            }
                        });
                        break;
                }
            }
        });
        DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        itemTouchHelper.setDragEnable(true);
        itemTouchHelper.setSwipeEnable(false);

//        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(new CallbackItemTouch() {
//            @Override
//            public void itemTouchOnMove(int oldPosition, int newPosition) {
//                //交换位置
//                Collections.swap(group_info, oldPosition, newPosition);
//                Log.e("position",oldPosition+"--------------------------"+newPosition);
//                //刷新adapter
//                blackAdapter.notifyItemMoved(oldPosition, newPosition);
//                includeTopTvRight.setVisibility(View.VISIBLE);
////                try {
////                    group_info.add(newPosition, group_info.remove(oldPosition));// change position
////                    blackAdapter.notifyItemMoved(oldPosition, newPosition); //notifies changes in adapter, in this case use the notifyItemMoved
////                    includeTopTvRight.setVisibility(View.VISIBLE);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
//            }
//        });
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback); // Create ItemTouchHelper and pass with parameter the MyItemTouchHelperCallback
//        touchHelper.attachToRecyclerView(mRecyclerView); // Attach ItemTouchHelper to RecyclerView

//        initTouch();



    }
    private DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(int adapterPosition) {
            // 滑动删除的时候，从数据源移除，并刷新这个Item。
//            if (group_info != null) {
//                group_info.remove(adapterPosition);
//                blackAdapter.notifyItemRemoved(adapterPosition);
//            }
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (group_info != null) {
                // 更换数据源中的数据Item的位置
                Collections.swap(group_info, srcPosition, targetPosition);
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                blackAdapter.notifyItemMoved(srcPosition, targetPosition);
                includeTopTvRight.setVisibility(View.VISIBLE);
                return true;
            }
            return false;
        }
    };
    private void initTouch() {

        // 实现左边侧滑删除 和 拖动排序
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 获取触摸响应的方向   包含两个 1.拖动dragFlags 2.侧滑删除swipeFlags
                // 代表只能是向左侧滑删除，当前可以是这样ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
                int swipeFlags = ItemTouchHelper.LEFT;


                // 拖动
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    // GridView 样式四个方向都可以
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.LEFT |
                            ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT;
                } else {
                    // ListView 样式不支持左右，只支持上下
                    dragFlags = ItemTouchHelper.UP |
                            ItemTouchHelper.DOWN;
                }

                return makeMovementFlags(dragFlags, swipeFlags);
            }

            /**
             * 拖动的时候不断的回调方法
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // 获取原来的位置
                int fromPosition = viewHolder.getAdapterPosition();
                // 得到目标的位置
                int targetPosition = target.getAdapterPosition();
                if (fromPosition > targetPosition) {
                    for (int i = fromPosition; i < targetPosition; i++) {
                        Collections.swap(group_info, i, i + 1);// 改变实际的数据集
                    }
                } else {
                    for (int i = fromPosition; i > targetPosition; i--) {
                        Collections.swap(group_info, i, i - 1);// 改变实际的数据集
                    }
                }

                includeTopTvRight.setVisibility(View.VISIBLE);
                blackAdapter.notifyItemMoved(fromPosition, targetPosition);
                return true;
            }

            /**
             * 侧滑删除后会回调的方法
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // 获取当前删除的位置
//                int position = viewHolder.getAdapterPosition();
//                mItems.remove(position);
//                // adapter 更新notify当前位置删除
//                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 拖动选择状态改变回调
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    // ItemTouchHelper.ACTION_STATE_IDLE 看看源码解释就能理解了
                    // 侧滑或者拖动的时候背景设置为灰色
                    viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            /**
             * 回到正常状态的时候回调
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 正常默认状态下背景恢复默认
                viewHolder.itemView.setBackgroundColor(0);
                ViewCompat.setTranslationX(viewHolder.itemView,0);
            }
        });
//        itemTouchHelper.isLongPressDragEnabled(true);
        // 这个就不多解释了，就这么attach
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
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