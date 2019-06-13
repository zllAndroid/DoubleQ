package com.mding.chatfeng.about_chat;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApp;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.Methon;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.example.zhouwei.library.CustomPopWindow;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.about_chat.adapter.ChatAdapter;
import com.mding.chatfeng.about_chat.adapter.CommonFragmentPagerAdapter;
import com.mding.chatfeng.about_chat.chat_group.ShowChatImgActivity;
import com.mding.chatfeng.about_chat.fragment.ChatEmotionFragment;
import com.mding.chatfeng.about_chat.fragment.ChatFunctionFragment;
import com.mding.chatfeng.about_chat.ui.PopupList;
import com.mding.chatfeng.about_chat.ui.StateButton;
import com.mding.chatfeng.about_utils.DensityUtil;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MathUtils;
import com.mding.chatfeng.about_utils.NotificationUtil;
import com.mding.chatfeng.about_utils.SoftKeyboardUtils;
import com.mding.chatfeng.about_utils.SysRunUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusChatData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmChatHelper;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.about_utils.windowStatusBar;
import com.mding.chatfeng.main_code.mains.top_pop.ChatPopWindow;
import com.mding.chatfeng.main_code.ui.about_contacts.ChooseGroupActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.FriendDataMixActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_group_team.GroupTeamActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.model.CusChatPop;
import com.mding.model.CusJumpChatData;
import com.mding.model.DataChatPop;
import com.mding.model.DataJieShou;
import com.mding.model.DataQueryRepetition;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.util.Constants;
import com.rance.chatui.util.MediaManager;
import com.rance.chatui.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mding.chatfeng.about_chat.fragment.ChatFunctionFragment.imgMD5FunFrag;
import static com.mding.chatfeng.about_chat.fragment.ChatFunctionFragment.imgTotalFunFrag;

/**
 * 项目：DoubleQ
 * 文件描述：私聊界面
 * 作者：zll
 * 修改者：ljj
 */
public class ChatActivity extends BaseActivity {

    @BindView(R.id.chat_list)
    EasyRecyclerView chatList;
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R.id.include_top_iv_back)
    ImageView mIvBack;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.voice_text)
    TextView voiceText;
    @BindView(R.id.emotion_button)
    ImageView emotionButton;
    @BindView(R.id.emotion_add)
    ImageView emotionAdd;
    @BindView(R.id.emotion_send)
    StateButton emotionSend;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;
    //
    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;
    @BindView(R.id.chat_tv_show)
    TextView mChatTvShow;
    @BindView(R.id.include_top_lin_back)
    LinearLayout mLinTop;
    @BindView(R.id.input_lin_main)
    LinearLayout mInputLinMain;
    @BindView(R.id.input_lin_in)
    LinearLayout mInputLin;
    @BindView(R.id.chat_lin_main)
    LinearLayout mLinChatMain;
    @BindView(R.id.include_top_iv_drop)
    ImageView includeTopIvDrop;
    @BindView(R.id.include_top_lin_title)
    LinearLayout includeTopLinTitle;
    @BindView(R.id.chat_lin_main_whole)
    LinearLayout chatLinMainWhole;
    @BindView(R.id.include_top_iv_lock)
    ImageView includeTopIvLock;
    @BindView(R.id.chat_scroll_bot)
    ImageView mIvScroll;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private StaggeredGridLayoutManager layoutManager;
    //    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    private List<DataJieShou.RecordBean> messageList;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        WindowBugDeal.SetTop(this);
        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 0);
//        StateBarUtils.setFullscreen(this, false, false);
        StateBarUtils.setAndroidNativeLightStatusBar(this,false);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.WHITE);
    }
    @Override
    protected boolean isChenjinshi() {
        return false;
    }

    //    好友id
    public static String FriendId = "";
    //    消息类型
    public static String messageType = "1";
    public static String messageTypeImg = "2";
    //    好友头像
    public static String friendHeader = "";
    //    我的头像
    public static String MyHeader = "";
    RealmChatHelper realmHelper;
    RealmHomeHelper realmHomeHelper;
    HideControl hideControl;
    //    RealmLinkManHelper realmLink;
    CusJumpChatData cusJumpChatData;
    ChatPopWindow chatPopWindow;
    DataChatPop dataChatPop;
    String remarkName;
    String groupName;
    boolean isLocked = false;
    CusChatPop cusChatPop;
    private  RealmChatHelper   getRealmChatHelper()
    {
        if (realmHelper == null)
            realmHelper = new RealmChatHelper(this);
        return realmHelper;
    }
    private  RealmHomeHelper   getRealmHomeHelper()
    {
        if (realmHomeHelper == null)
            realmHomeHelper = new RealmHomeHelper(this);
        return realmHomeHelper;
    }
    @Override
    protected void initBaseView() {
        super.initBaseView();
        setAboutBar();
        SplitWeb.getSplitWeb().IS_CHAT = "1";
        if (hideControl == null)
            hideControl = new HideControl();
        mChatTvShow.setBackgroundResource(R.color.chattrans);
        Intent intent = getIntent();
        cusJumpChatData = (CusJumpChatData) intent.getSerializableExtra(Constants.KEY_FRIEND_HEADER);
        FriendId = cusJumpChatData.getFriendId();
        friendHeader = cusJumpChatData.getFriendHeader();
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.person);
        String friendName = new RealmFriendUserHelper(this).queryLinkFriendReturnname(FriendId);//获取私聊好友名
//        String friendName = cusJumpChatData.getFriendName();
        includeTopTvTitle.setText(friendName);
        includeTopTvTitle.setMovementMethod(new ScrollingMovementMethod());
        sendWeb(SplitWeb.getSplitWeb().privateSendInterface(FriendId));
        if (isLocked){
            includeTopIvLock.setVisibility(View.VISIBLE);
        }
        else {
            includeTopIvLock.setVisibility(View.GONE);
        }

        initWidget();
//        初始化数据库的聊天记录
        initRealm();

        listenEnter();

    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat;
    }

    private void listenEnter() {
        editText.setSingleLine();
        editText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    sendMsgDataDeal();
//                    //处理事件
//                    String ed = editText.getText().toString().trim();
//                    if (!StrUtils.isEmpty(ed)) {
//
//
//                        send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
//                    } else {
//                        editText.setText("");
//                        ToastUtil.show("发送的内容不能为空");
//                    }
                }
                return true;
            }
        });
    }
    @OnClick({R.id.include_top_iv_more, R.id.include_top_lin_title, R.id.include_top_iv_drop,R.id.chat_scroll_bot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_iv_more:
                IntentUtils.JumpToHaveOne(ChatSetActivity.class, "FriendId", FriendId);
                break;
            case R.id.chat_scroll_bot:
                if (mIvScroll.getVisibility()==View.VISIBLE)
                    layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                break;
            case R.id.include_top_lin_title:
                includeTopIvDrop.setActivated(true);
                cusChatPop = new CusChatPop();
                cusChatPop.setContext(this);
                cusChatPop.setType("1");
                cusChatPop.setFriendId(FriendId);
                cusChatPop.setGroupingName(groupName);
                cusChatPop.setRemarkName(remarkName);
//                cusChatPop.setGroupingName(StrUtils.isEmpty(cusJumpChatData.getFriendGroupName()) ? "暂无" : cusJumpChatData.getFriendGroupName());
//                cusChatPop.setRemarkName(StrUtils.isEmpty(cusJumpChatData.getFriendRemarkName()) ? "暂无" : cusJumpChatData.getFriendRemarkName());
                cusChatPop.setChatLinMainWhole(chatLinMainWhole);
                cusChatPop.setLocked(isLocked);
                if (chatPopWindow == null)
                    chatPopWindow = new ChatPopWindow(cusChatPop);
                chatPopWindow.showAtBottom(includeTopLinTitle);
                chatPopWindow.setOnClickOutSideListener(new ChatPopWindow.OnClickOutSideListener() {
                    @Override
                    public void Clicked(String type) {
                        includeTopIvDrop.setActivated(false);
                        if (type != null && type.equals("1")) {
                            Intent intent = new Intent(ChatActivity.this, ChooseGroupActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("FriendId", FriendId);
                            bundle.putString("type", "1");
                            intent.putExtras(bundle);
                            startActivityForResult(intent, AppConfig.FRIEND_DATA_Chat_REQUEST);
                        }
                    }
                });
                chatPopWindow.setOnReRemarkListener(new ChatPopWindow.OnReRemarkListener() {
                    @Override
                    public void reRemark(String RemarkName) {
                        remarkName = RemarkName;
                        sendWeb(SplitWeb.getSplitWeb().friendRemarkName(FriendId, RemarkName));
                    }
                });
                chatPopWindow.setOnClickLockListener(new ChatPopWindow.OnClickLockListener() {
                    @Override
                    public void Locked(boolean isLock) {
                        isLocked = isLock;
                        if (isLocked){
                            includeTopIvLock.setVisibility(View.VISIBLE);
                        }
                        else {
                            includeTopIvLock.setVisibility(View.GONE);
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.FRIEND_ADD_GROUP_RESULT) {
            if (requestCode == AppConfig.FRIEND_DATA_Chat_REQUEST) {
                chatPopWindow = null;
                String name = data.getStringExtra(ChooseGroupActivity.CHOOSE_NAME);
//                String id = data.getStringExtra(ChooseGroupActivity.CHOOSE_ID);
                groupName = name;
                //设置结果显示框的显示数值
            }
        }
    }
    //    设置状态栏的高度为负状态栏高度，因为xml 设置了 android:fitsSystemWindows="true",会占用一个状态栏的高度；
    private void setAboutBar() {
//        获取状态栏的高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
//        这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) mLinChatMain.getLayoutParams();
//        获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
        layout.setMargins(0, -statusBarHeight, 0, 0);
//        设置button的新位置属性,left，top，right，bottom
        mLinChatMain.setLayoutParams(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (chatPopWindow != null)
            chatPopWindow.dismiss();
        includeTopIvDrop.setClickable(false);// 屏蔽主动获得点击
        includeTopIvDrop.setPressed(false);
        includeTopIvDrop.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SplitWeb.getSplitWeb().IS_CHAT = "00";
        getRealmHomeHelper().updateNumZero(FriendId);//更新首页聊天界面数据（未读消息数目）
        EventBus.getDefault().post(new MsgHomeEvent("",FriendId,AppConfig.MSG_ZERO_REFRESH));
        getRealmChatHelper().close();
        getRealmHomeHelper().close();
        realmHelper = null;
        realmHomeHelper = null;
        if (chatPopWindow != null) {
            chatPopWindow.dismiss();
            chatPopWindow = null;
        }
    }
    private void initRealm() {
        List<CusChatData> cusRealmChatMsgs = getRealmChatHelper().queryAllRealmChat(FriendId);
        if (cusRealmChatMsgs != null && cusRealmChatMsgs.size() != 0) {
//            mList.clear();
            chatAdapter.clear();
            for (int i = 0; i < cusRealmChatMsgs.size(); i++) {
                DataJieShou.RecordBean recordBean = new DataJieShou.RecordBean();
                recordBean.setType(cusRealmChatMsgs.get(i).getUserMessageType());
                recordBean.setMessage(cusRealmChatMsgs.get(i).getMessage());
                recordBean.setMessageType(cusRealmChatMsgs.get(i).getMessageType());
                recordBean.setRequestTime(cusRealmChatMsgs.get(i).getCreated());
                recordBean.setFriendsId(cusRealmChatMsgs.get(i).getReceiveId());
                recordBean.setHeadImg(cusRealmChatMsgs.get(i).getImgUrl());
                recordBean.setMessageStoId(cusRealmChatMsgs.get(i).getMessageStoId());
//                mList.add(recordBean);
                chatAdapter.add(recordBean);
            }
//            chatAdapter.addAll(mList);
            chatAdapter.notifyDataSetChanged();
            //    滑动到底部
            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .bindToInputLayout(mInputLinMain)
                .build();

        mInputLinMain.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, final int left, final int top, final int right, final int bottom, final int oldLeft, final int oldTop, final int oldRight, final int oldBottom) {
//                sofeDeal();
                if (emotionLayout.isShown() || isSoftShowing()) {
                    if (layoutManager != null && chatAdapter != null)
                        layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                }
            }
        });
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);
        chatAdapter = new ChatAdapter(this);
        layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);

        initPopMenu();
        chatList.getRecyclerView().setItemViewCacheSize(20);
//        chatAdapter.setHasStableIds(true);
        chatAdapter.addItemClickListener(itemClickListener);
        chatList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
//                    case MotionEvent.ACTION_DOWN: //手指按下
//                        点击列表，软键盘或者表情列表存在，则关闭他们；
                    Log.e("chatList",isSoftShowing()+"---"+emotionLayout.isShown());
                    if (isSoftShowing() || emotionLayout.isShown()) {
                        emotionLayout.setVisibility(View.GONE);
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                    }
//                        initChatPopWindow();
//                        break;
                } //end switch
                return false;
            }
        });

        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (chatList != null) {
                    boolean visBottom = isVisBottom();
                    if (visBottom) {
                        mIvScroll.setVisibility(View.GONE);
                    } else {
                        mIvScroll.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public  boolean isVisBottom(){
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) chatList.getRecyclerView().getLayoutManager();

        //屏幕中最后一个可见子项的position
        int[] lastVisibleItemPosition = layoutManager.findLastVisibleItemPositions(null);
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
//        int state = recyclerView.getScrollState();
        for (int position : lastVisibleItemPosition) {
            if (visibleItemCount > 0 && position == totalItemCount - 1) {
                return true;
            }
        }
        return false;
    }
    private List<String> popupMenuItemList = new ArrayList<>();
    private void initPopMenu() {

        popupMenuItemList.add(getString(R.string.copy));
        popupMenuItemList.add(getString(R.string.delete));
        popupMenuItemList.add(getString(R.string.share));
    }
    public int absInt(int a) {
        if (a < 0) {
            a = -a;
        }
        return a;
    }

    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }

    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DataJieShou.RecordBean messageInfo) {
        sendMsgDataDeal();
    }

    private void sendMsgDataDeal() {
        String ed = editText.getText().toString().trim();
        if (!StrUtils.isEmpty(ed)) {
//            TODO  写入数据库先
//            addRealm(ed);

            try {
                BaseApp.mIChatRequst.sendMsg(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
        } else {
            editText.setText("");
            ToastUtil.show("发送的内容不能为空");
        }
    }

    private void addRealm( String msg) {
        CusChatData cusRealmChatMsg = new CusChatData();
//        cusRealmChatMsg.setCreated(TimeUtil.getTime());
        cusRealmChatMsg.setTimeSort(TimeUtil.getTime());
//            cusRealmChatMsg.setCreated(TimeUtil.sf.format(new Date()));
        cusRealmChatMsg.setMessage(msg);
        cusRealmChatMsg.setMessageType(Constants.CHAT_TEXT);
        cusRealmChatMsg.setReceiveId(FriendId);
        cusRealmChatMsg.setSendId(SplitWeb.getSplitWeb().getUserId());
        cusRealmChatMsg.setUserMessageType(Constants.CHAT_ITEM_TYPE_RIGHT);
        cusRealmChatMsg.setMsgState(Constants.CHAT_ITEM_SENDING);
//        cusRealmChatMsg.setMessageStoId(record.getMessageStoId());
        cusRealmChatMsg.setTotalId(FriendId + SplitWeb.getSplitWeb().getUserId());
        getRealmChatHelper().addRealmChat(cusRealmChatMsg);//更新聊天数据

        DataJieShou.RecordBean recordBean = new DataJieShou.RecordBean();
        recordBean.setMessage(msg);
        recordBean.setMessageType(Constants.CHAT_TEXT);
        recordBean.setSendState(Constants.CHAT_ITEM_SENDING);
        recordBean.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        chatAdapter.add(recordBean);
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            发送消息返回
            case Methon.PrivateSend:  // sendPrivateChat  私聊新接口
                dealSendResult(responseText);
                break;
            case Methon.PrivateChat:
                dealReceiverResult(responseText);
                break;
            case "friendRemarkName":
                String nameText = StrUtils.isEmpty(remarkName) ? "暂无" : remarkName;
//                String nameText = StrUtils.isEmpty(cusJumpChatData.getFriendRemarkName()) ? cusJumpChatData.getFriendName() : cusJumpChatData.getFriendRemarkName();
                if (!StrUtils.isEmpty(nameText))
                    includeTopTvTitle.setText(nameText);
                break;
            case "privateSendInterface":
                dataChatPop = JSON.parseObject(responseText, DataChatPop.class);
                DataChatPop.RecordBean recordBean = dataChatPop.getRecord();
                if (recordBean != null) {
                    String name = StrUtils.isEmpty(recordBean.getRemarkName()) ? recordBean.getNickName() : recordBean.getRemarkName();
                    remarkName = StrUtils.isEmpty(recordBean.getRemarkName()) ? "暂无" : recordBean.getRemarkName();
                    groupName = StrUtils.isEmpty(recordBean.getGroupName()) ? "暂无" : recordBean.getGroupName();
                    if(!StrUtils.isEmpty(name))
                        includeTopTvTitle.setText(name);
                }
                break;
            // TODO 请求MD5查询，返回值判断是否存在该key
            case "getQueryRepetition":
                DataQueryRepetition dataQueryRepetition = JSON.parseObject(responseText, DataQueryRepetition.class);
                if (dataQueryRepetition != null){
                    DataQueryRepetition.RecordBean recordBeanQuery = dataQueryRepetition.getRecord();
                    if (recordBeanQuery != null){
                        if (recordBeanQuery.getFileType() == 1){
                            // 是否发送 1需发送 2无需发送
                            send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, imgTotalFunFrag, messageTypeImg, TimeUtil.getTime()));
                        }else if (recordBeanQuery.getFileType() == 2){
                            send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, imgMD5FunFrag, messageTypeImg, TimeUtil.getTime()));
                        }
                    }
                }
                break;
        }
    }


    ChatNewsWindow chatWindow = null;
    private void dealReceiverResult(String responseText) {
        DataJieShou dataJieShou1 = JSON.parseObject(responseText, DataJieShou.class);
        final DataJieShou.RecordBean record2 = dataJieShou1.getRecord();
        if (record2 != null) {
            if (record2.getShieldType().equals("2"))
                return;
            if (SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {
//                    收到聊天页的此人的消息
                if (record2.getFriendsId().equals(FriendId)) {
                    record2.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                    record2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                    String time = AppConfig.mCHAT_RECEIVE_TIME_REALM;
                    AppConfig.mCHAT_RECEIVE_TIME_REALM = record2.getRequestTime();
                    if (!StrUtils.isEmpty(time)) {
                        try {
                            int i = TimeUtil.stringDaysBetween(record2.getRequestTime(), time);
                            if (MathUtils.abs(i) < 5) {
                                record2.setRequestTime("");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    chatAdapter.add(record2);
                    chatAdapter.notifyDataSetChanged();
//                    滑动到底部
                    layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                } else {
                    ToastUtil.show("收到一条私聊的消息");
                    EventBus.getDefault().post(new MsgHomeEvent( record2.getMessage(), record2.getFriendsId(),AppConfig.MSG_ACTION_REFRESH));

                }
            } else if (!SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {
                EventBus.getDefault().post(new MsgHomeEvent( record2.getMessage(), record2.getFriendsId(),AppConfig.MSG_ACTION_REFRESH));

                //APP在后台的时候处理接收到消息的事件
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bitmap = Glide.with(BaseApplication.getAppContext())
                                    .load(record2.getHeadImg())
                                    .asBitmap() //必须
                                    .centerCrop()
                                    .into(500, 500)
                                    .get();
                            NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                            notificationUtils.sendNotification(cusJumpChatData, record2.getFriendsName(), record2.getMessage(), bitmap, AppConfig.TYPE_CHAT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
    private void dealSendResult(String responseText) {
        DataJieShou dataJieShou = JSON.parseObject(responseText, DataJieShou.class);
        DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME, "");
            SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    if (MathUtils.abs(i) < 5) {
                        record.setRequestTime("");
                    } else {
                        record.setRequestTime(record.getRequestTime());
//                        SPUtils.put(this,AppConfig.CHAT_SEND_TIME,"");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (record.getMessageType().equals(messageType)){
                String ed = editText.getText().toString().trim();
                if (!StrUtils.isEmpty(ed)) {
                    editText.setText("");
                }
            }
            else if (record.getMessageType().equals(messageTypeImg)){
                ToastUtil.show("发送图片成功!");
            }

            record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            record.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
            chatAdapter.add(record);
            chatAdapter.notifyDataSetChanged();
//                    滑动到底部
            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
        }
    }

    Bitmap bitmap;
    View view = null;
    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {

        @Override
        public void onHeaderClick(int position, int type, String friendId) {
            switch (type) {
                case Constants.CHAT_ITEM_TYPE_LEFT:
                    IntentUtils.JumpToHaveTwo(FriendDataMixActivity.class, "id", friendId, "esc", "esc");
//                    IntentUtils.JumpToHaveTwo(FriendDataActivity.class, "id", friendId,"esc","esc");
                    break;
                case Constants.CHAT_ITEM_TYPE_RIGHT:
//                    TODO 点击自己头像，显示自己的信息
                    IntentUtils.JumpTo(ChangeInfoActivity.class);
//                    IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", SplitWeb.getSplitWeb().getUserId());
                    break;
            }
        }

        @Override
        public void onConClick(View view, int position, String conText) {
            initPopChoose(view, position, conText);
        }

        @Override
        public void onImageClick( int position, String imgHttp) {
            IntentUtils.JumpToHaveOne(ShowChatImgActivity.class, ShowChatImgActivity.SHOW_CHAT_IMG_REGION, imgHttp);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }

        @Override
        public void onAddFriendClick(boolean isCancleClick, int position) {
            DataJieShou.RecordBean item = chatAdapter.getItem(position);
            if (isCancleClick&&item.getMessageType().equals(Constants.CHAT_NO_FRIEND))
            {
                IntentUtils.JumpToHaveOne(FriendDataMixActivity.class,"id",item.getFriendsId());
            }
        }
    };

    private void initPopChoose(View view, int position, String conText) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        PopupList popupList = new PopupList(view.getContext());
        popupList.showPopupListWindow(view, position, location[0] + view.getWidth() / 2,
                location[1], popupMenuItemList, new PopupList.PopupListListener() {
                    @Override
                    public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                        return true;
                    }
                    @Override
                    public void onPopupListClick(View contextView, int contextPosition, int positions) {
//                            第二个参数是第几条数据，第三个参数本列表选择第几个
                        switch (positions)
                        {
                            case  0://复制
//                                mPressedPos = contextPosition;
                                ClipboardManager myClipboard;
                                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                                ClipData myClip;
                                myClip = ClipData.newPlainText("text", conText);
                                myClipboard.setPrimaryClip(myClip);
                                ToastUtil.show("复制成功，可以去粘贴板黏贴哦");
                                break;
                            case  1://删除
                                boolean b = getRealmChatHelper().deletePosition(FriendId, contextPosition);
//                                        boolean b = getRealmChatHelper().deletePosition(FriendId, item.getMessageStoId());
                                if (b) {
//                                        chatAdapter.deleteWho(contextPosition);

                                    chatAdapter.remove(contextPosition);
                                    ToastUtil.show("删除成功");
                                    chatAdapter.notifyItemChanged(contextPosition);
//                                        chatAdapter.notifyAdapter(contextPosition);
//                                            chatAdapter.notifyDataSetChanged();
                                } else {
                                    ToastUtil.show("删除失败，请重试");
                                }
                                break;
                            case  2://转发
//                                TODO
                                DataJieShou.RecordBean item = chatAdapter.getItem(contextPosition);
                                String message = item.getMessage();

                                break;
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    public class HideControl {
        public final static int MSG_HIDE = 0x01;

        private HideHandler mHideHandler;

        public HideControl() {
            mHideHandler = new HideHandler();
        }

        public class HideHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_HIDE:
                        if (chatWindow != null) {
                            chatWindow.dismiss();
                            chatWindow = null;
                            if (SoftKeyboardUtils.isSoftShowing(ChatActivity.this)) {
                                SoftKeyboardUtils.showSoftKeyboard(ChatActivity.this);
//                                SoftKeyboardUtils.showORhideSoftKeyboard(ChatActivity.this);
                            }
                        }
//                        mChatTvShow.setVisibility(View.GONE);
                        break;
                }

            }
        }
    }

}
