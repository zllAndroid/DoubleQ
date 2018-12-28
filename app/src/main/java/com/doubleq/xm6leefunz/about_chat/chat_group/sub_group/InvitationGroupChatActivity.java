package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataCreatGroupChat;
import com.doubleq.model.DataCreatGroupResult;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.about_intent_data.IntentDataInvitation;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusJumpGroupChatData;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.ImageUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.CreatGroupChatAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.CreatGroupSeachAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.LetterBar;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

//位置：邀请好友进群
public class InvitationGroupChatActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.tv_abc)
    TextView mTvAbc;

    @BindView(R.id.group_team_letter)
    LetterBar mLetterBar;




    @BindView(R.id.creat_exlist_friend)
    ExpandableListView mExList;


    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_invitation_group_chat;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent!=null)
        {
            IntentDataInvitation dataInvitation = (IntentDataInvitation)intent.getSerializableExtra(AppConfig.GROUP_ID);
            String groupTital = dataInvitation.getGroupTital();
            includeTopTvTital.setText(groupTital);


        }
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setText("确定");
        initGroup();
    }

    private void initGroup() {
        initABC2();
//        initHttp();
        sendWeb(SplitWeb.getGroupWebInfo());
    }
    List<String> ABCList = new ArrayList<>();
    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouuchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧")) {
                    mExList.setSelection(0);
//                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
//                    mRecyclerView.scrollToPosition(0);
//                    mRecyclerView.setSelection(0);
                    return;
                }
                for (int i = 0; i < mFriendList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (mFriendList.get(i).getGroupName());
                    if (letter.equals(firstLetter)) {
                        mExList.setSelectedGroup(i);
                        break;
                    }
                }
            }

            @Override
            public void onTouchUp() {
                mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }

    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }
//    大列表选中的数据
    List<String> mList = new ArrayList<>();
    List<DataCreatGroupChat.RecordBean.FriendListBean> mFriendList = new ArrayList<>();

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "getGroupWebInfo":
                DataCreatGroupChat dataCreatGroupChat = JSON.parseObject(responseText, DataCreatGroupChat.class);
                DataCreatGroupChat.RecordBean record = dataCreatGroupChat.getRecord();
                if (record != null) {
                    List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list = record.getFriendList();
                    mFriendList.addAll(friend_list);
                    if (friend_list.size() > 0)
                        initAdapter(friend_list);
                }
                break;
            case "createdUserGroup":
//                在application处理
//                DataCreatGroupResult dataCreatGroupResult = JSON.parseObject(responseText, DataCreatGroupResult.class);
//                record1 = dataCreatGroupResult.getRecord();
//                if (record1 != null) {
//                    DialogUtils.showDialogOne("群创建成功，快去聊天吧", new DialogUtils.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
////     TODO 应该跳转到群聊天界面
//                            CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
//                            cusJumpGroupChatData.setGroupId(record1.getGroupOfId());
//                            cusJumpGroupChatData.setGroupName(record1.getGroupNickName());
//                            IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
//                            AppManager.getAppManager().finishActivity(InvitationGroupChatActivity.this);
//                        }
//                    });
////                    send(SplitWeb.groupSend(record1.getGroupOfId(),"群创建成功，快去聊天吧",AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
//                }
                break;
        }
    }

    CreatGroupChatAdapter creatGroupChatAdapter = null;

    private void initAdapter(final List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list) {
        creatGroupChatAdapter = new CreatGroupChatAdapter(this, friend_list);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mExList.setAdapter(creatGroupChatAdapter);
        mExList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });
        for (int i = 0; i < creatGroupChatAdapter.getGroupCount(); i++) {
            mExList.expandGroup(i);
        }
        creatGroupChatAdapter.setOnMyLinChangeListener(new CreatGroupChatAdapter.OnMyLinChangeListener() {
            @Override
            public void onCheckedChanged(String friendId, boolean isChecked) {
                if (isChecked)
                {
                    if (!mList.contains(friendId))
                    mList.add(friendId);
                }else
                {
                    if (mList.contains(friendId))
                        mList.remove(friendId);
                }
                List<String> checkString = creatGroupChatAdapter.getCheckString();

                Log.e("checkChat","friendId="+friendId+isChecked+"++++"+mList.toString()+"---"+checkString.toString());
            }
        });
//        mExList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = friend_list.get(groupPosition).getGroupList().get(childPosition);
////                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
////                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",userId);
//                Log.e("checkChat","groupListBean="+groupListBean.getNickName());
//
//                return false;
//            }
//        });
        creatGroupChatAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.seach_iv_close,  R.id.inclu_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//                点击确定
            case R.id.inclu_tv_right:
                List<String> checkString = creatGroupChatAdapter.getCheckString();
                if (checkString.size() > 0) {
//                    String check[]= new String[checkData.size()];
                    String checkChat = "";
                    for (int i = 0; i < checkString.size(); i++) {
                        if (i == 0) {
                            checkChat += checkString.get(i);
                        } else {
                            checkChat += "," + checkString.get(i);
                        }
//                        check[i]=checkData.get(i).getWx_sno();
                    }
//                    sendWebHaveDialog(SplitWeb.createdUserGroup(checkChat, imageBase64)
//                            , "创建中...", "群聊创建成功");
                } else {
                    ToastUtil.show("请选择邀请的成员");
                }
                break;
        }
    }

}
