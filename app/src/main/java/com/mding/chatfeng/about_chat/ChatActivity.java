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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
import com.mding.chatfeng.about_chat.chat_group.ShowChatImgActivity;
import com.mding.chatfeng.about_chat.fragment.ChatEmotionFragment;
import com.mding.chatfeng.about_chat.fragment.ChatFunctionFragment;
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
import com.rance.chatui.widget.BubbleImageView;
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
    TextView includeTopTvTital;
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
    //    外来消息弹窗显示时间
    int showTime = 2000;
//    private Intent websocketServiceIntent;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        WindowBugDeal.SetTop(this);
        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
//        StateBarUtils.setFullscreen(this, false, false);
        StateBarUtils.setAndroidNativeLightStatusBar(this,false);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.WHITE);
    }

    //        设置导航栏颜色
    public void initStateBar() {
//        hideBottomUIMenu();
    }

    @Override
    protected boolean isChenjinshi() {
        return false;
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }
//    @Override
//    protected void initBeforeContentView() {
//        super.initBeforeContentView();
//
//        StateBarUtils.setFullscreen(this,true,false);
////            设置状态栏的颜色
//        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
//    }

//    @Override
//    protected boolean isChat() {
//        return true;
//    }


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

    @Override
    protected void initBaseView() {
        super.initBaseView();
        setAboutBar();
        SplitWeb.getSplitWeb().IS_CHAT = "1";
        if (realmHelper == null)
            realmHelper = new RealmChatHelper(this);

        if (realmHomeHelper == null)
            realmHomeHelper = new RealmHomeHelper(this);


        if (hideControl == null)
            hideControl = new HideControl();

        mChatTvShow.setBackgroundResource(R.color.chattrans);
//        realmLink = new RealmLinkManHelper(this);
        Intent intent = getIntent();
        cusJumpChatData = (CusJumpChatData) intent.getSerializableExtra(Constants.KEY_FRIEND_HEADER);
        FriendId = cusJumpChatData.getFriendId();
//        final CusDataFriendRealm friendRealm = realmLink.queryFriendRealmById(FriendId);
        friendHeader = cusJumpChatData.getFriendHeader();
        String nameText = StrUtils.isEmpty(cusJumpChatData.getFriendRemarkName()) ? cusJumpChatData.getFriendName() : cusJumpChatData.getFriendRemarkName();
        includeTopTvTital.setText(nameText);
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.person);
//        includeTopIvDrop.setImageResource(R.drawable.spinner_right);
        MyLog.e("ChatActivity","--------------------标题------------------------->>"+includeTopTvTital.getText().toString());
        if (StrUtils.isEmpty(includeTopTvTital.getText().toString())) {
            sendWeb(SplitWeb.getSplitWeb().privateSendInterface(FriendId));
        }
        if (isLocked){
            includeTopIvLock.setVisibility(View.VISIBLE);
        }
        else {
            includeTopIvLock.setVisibility(View.GONE);
        }

        initWidget();
//        初始化数据库的聊天记录
        initRealm();

//        通知栏点击进入后，需要刷新首页的消息条数，发送广播，在首页接收，并进行刷新页面；
        realmHomeHelper.updateNumZero(FriendId);
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
                    //处理事件
                    String ed = editText.getText().toString().trim();
                    if (!StrUtils.isEmpty(ed)) {
                        send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
                    }
                }
                return true;
            }
        });
    }
    @OnClick({R.id.include_top_iv_more, R.id.include_top_lin_title, R.id.include_top_iv_drop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_iv_more:
                IntentUtils.JumpToHaveOne(ChatSetActivity.class, "FriendId", FriendId);
                break;
            case R.id.include_top_lin_title:
                includeTopIvDrop.setActivated(true);
                cusChatPop = new CusChatPop();
                cusChatPop.setContext(this);
                cusChatPop.setType("1");
                cusChatPop.setFriendId(FriendId);
//                cusChatPop.setGroupingName(groupName);
//                cusChatPop.setRemarkName(remarkName);
                cusChatPop.setGroupingName(StrUtils.isEmpty(cusJumpChatData.getFriendGroupName()) ? "暂无" : cusJumpChatData.getFriendGroupName());
                cusChatPop.setRemarkName(StrUtils.isEmpty(cusJumpChatData.getFriendRemarkName()) ? "暂无" : cusJumpChatData.getFriendRemarkName());
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
        EventBus.getDefault().post(new MsgHomeEvent("",FriendId,AppConfig.MSG_ZERO_REFRESH));
        realmHelper.close();
        realmHomeHelper.close();
        realmHelper = null;
        realmHomeHelper = null;
        if (chatPopWindow != null) {
            chatPopWindow.dismiss();
            chatPopWindow = null;
        }
    }

    ArrayList< DataJieShou.RecordBean> mList = new ArrayList<>();

    private void initRealm() {
        List<CusChatData> cusRealmChatMsgs = realmHelper.queryAllRealmChat(FriendId);
        if (cusRealmChatMsgs != null && cusRealmChatMsgs.size() != 0) {
            mList.clear();
            for (int i = 0; i < cusRealmChatMsgs.size(); i++) {
                DataJieShou.RecordBean recordBean = new DataJieShou.RecordBean();
                recordBean.setType(cusRealmChatMsgs.get(i).getUserMessageType());
                recordBean.setMessage(cusRealmChatMsgs.get(i).getMessage());
                recordBean.setMessageType(cusRealmChatMsgs.get(i).getMessageType());
                recordBean.setRequestTime(cusRealmChatMsgs.get(i).getCreated());
                recordBean.setFriendsId(cusRealmChatMsgs.get(i).getReceiveId());
                recordBean.setHeadImg(cusRealmChatMsgs.get(i).getImgUrl());
                mList.add(recordBean);
            }
            chatAdapter.addAll(mList);
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
        
//        layoutManager = new LinearLayoutManager(this);
//
//
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        layoutManager.setRecycleChildrenOnDetach(true);//复用RecycledViewPool
//        chatList.setHasFixedSize(true);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
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
    }
    public int absInt(int a) {
        if (a < 0) {
            a = -a;
        }
        return a;
    }
    private void sofeDeal() {
        if (isSoftShowing()) {
//                if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            if (layoutManager != null && chatAdapter != null)
                layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
        }
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
        String ed = editText.getText().toString().trim();
        if (!StrUtils.isEmpty(ed)) {
            try {
                BaseApp.mIChatRequst.sendMsg(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
        } else {
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            发送消息返回
            case Methon.PrivateSend:  // sendPrivateChat  私聊新接口
//                String ed = editText.getText().toString().trim();
//                if (!StrUtils.isEmpty(ed)) {
//                    editText.setText("");
//                }
                dealSendResult(responseText);
                break;
            case Methon.PrivateChat:
                dealReceiverResult(responseText);
                break;
            case "friendRemarkName":
                String nameText = StrUtils.isEmpty(remarkName) ? "暂无" : remarkName;
//                String nameText = StrUtils.isEmpty(cusJumpChatData.getFriendRemarkName()) ? cusJumpChatData.getFriendName() : cusJumpChatData.getFriendRemarkName();
                includeTopTvTital.setText(nameText);
//                sendWeb(SplitWeb.getSplitWeb().privateSendInterface(FriendId));
                break;
            case "privateSendInterface":
                dataChatPop = JSON.parseObject(responseText, DataChatPop.class);
                DataChatPop.RecordBean recordBean = dataChatPop.getRecord();
                if (recordBean != null) {
                    String name = StrUtils.isEmpty(recordBean.getRemarkName()) ? recordBean.getNickName() : recordBean.getRemarkName();
                    includeTopTvTital.setText(name);
                    remarkName = StrUtils.isEmpty(recordBean.getRemarkName()) ? "暂无" : recordBean.getRemarkName();
                    groupName = StrUtils.isEmpty(recordBean.getGroupName()) ? "暂无" : recordBean.getGroupName();
                    includeTopTvTital.setText(name);
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
                    ToastUtil.show("收到一条来自" + record2.getFriendsName() + "的消息");
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
    Intent mIntent = null;
    String time = null;
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
                ToastUtil.isDebugShow("发送图片成功!");
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
    CustomPopWindow popWindow = null;
    View view = null;

    private int mPressedPos; // 被点击的位置
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
        public void onConClick( int position, String conText) {
//            mRawX = event.getRawX();
//            mRawY = event.getRawY();
            mPressedPos = position;
//            Log.d("onConClick", "e.getRawX()横坐标=" + mRawX + ", e.getRawY()纵坐标=" + mRawY);
//            Log.d("onConClick", "position=" + position);
//            initPopWindow(view, position);
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
//            String text = "hello world";
            myClip = ClipData.newPlainText("text", conText);
            myClipboard.setPrimaryClip(myClip);
            ToastUtil.show("复制成功");
            //            TODO  在这里复制
        }

        @Override
        public void onImageClick(BubbleImageView view, int position, String imgHttp) {
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

        private Runnable hideRunable = new Runnable() {

            @Override
            public void run() {
                mHideHandler.obtainMessage(MSG_HIDE).sendToTarget();
            }
        };

        public void startHideTimer() {//开始计时,三秒后执行runable
            mHideHandler.removeCallbacks(hideRunable);
            chatWindow.showAtLocation(mLinTop, Gravity.TOP, 0, mLinTop.getHeight());
//            if(popWindow==null)
//                popWindow = new CustomPopWindow.PopupWindowBuilder(ChatActivity.this)
//                        .setView(view)
//                        .setFocusable(true)
//                        .setOutsideTouchable(true)
//                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                        .setAnimationStyle(R.style.AnimDown) // 添加自定义显示和消失动画
//                        .create()
//                        .showAsDropDown(mLinTop,0,0);
//            if (mChatTvShow.getVisibility() == View.GONE) {
//                mChatTvShow.setVisibility(View.VISIBLE);
//                mChatTvShow.setBackgroundColor(getResources().getColor(R.color.white));
//            }
            mHideHandler.postDelayed(hideRunable, showTime);
        }

        public void endHideTimer() {//移除runable,将不再计时
            mHideHandler.removeCallbacks(hideRunable);
        }

        public void resetHideTimer() {//重置计时
            mHideHandler.removeCallbacks(hideRunable);
            mHideHandler.postDelayed(hideRunable, showTime);
        }

    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().removeStickyEvent(this);
//        EventBus.getDefault().unregister(this);
//    }

    private void showPopWindows(View v, final String msg) {

        /** pop view */
        View mPopView = LayoutInflater.from(this).inflate(R.layout.popup, null);
        final PopupWindow mPopWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        /** set */
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        /** 这个很重要 ,获取弹窗的长宽度 */
        mPopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = mPopView.getMeasuredWidth();
        int popupHeight = mPopView.getMeasuredHeight();
        /** 获取父控件的位置 */
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        /** 显示位置 */
        mPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1]
                - popupHeight);
        mPopWindow.update();

//    final String copyTxt = (String) v.getTag();
        mPopView.findViewById(R.id.tv_copy_txt).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", msg);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                ToastUtil.show("复制成功");
//            copyToClip(copyTxt);
                if (mPopWindow != null) {
                    mPopWindow.dismiss();
                }
            }
        });
        mPopView.findViewById(R.id.tv_shoucang_txt).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ToastUtil.show("点击收藏");
//            copyToClip(copyTxt);
                if (mPopWindow != null) {
                    mPopWindow.dismiss();
                }
            }
        });
    }

    private float mRawX;
    private float mRawY;

    private PopupWindow mPopupWindow;
    private View mPopContentView;
    private void initPopWindow(final View selectedView, final int position) {
        if (mPopContentView == null) {
            mPopContentView = View.inflate(this, R.layout.popup, null);
        }
//        LinearLayout layoutDelete = (LinearLayout) mPopContentView.findViewById(R.id.layout_delete);
        // 在popupWindow还没有弹出显示之前就测量获取其宽高（单位是px像素）
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mPopContentView.measure(w, h);
        int viewWidth = mPopContentView.getMeasuredWidth();//获取测量宽度px
        int viewHeight = mPopContentView.getMeasuredHeight();//获取测量高度px
        final int screenWidth = DensityUtil.getScreenWidth(this.getWindow().getDecorView().getContext());
        final int screenHeight = DensityUtil.getScreenHeight(this.getWindow().getDecorView().getContext());
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mPopContentView, viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        }
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(drawable);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        int offX = 0; // 可以自己调整偏移
        int offY = 0; // 可以自己调整偏移
        float rawX = mRawX;
        float rawY = mRawY;
        if (mRawX <= screenWidth / 2) {
            rawX = mRawX - offX;
            if (mRawY < screenHeight / 3) {
                rawY = mRawY;
                mPopupWindow.setAnimationStyle(R.style.pop_anim_left_top); //设置动画
            } else {
                rawY = mRawY - viewHeight - offY;
                mPopupWindow.setAnimationStyle(R.style.pop_anim_left_bottom); //设置动画
            }
        } else {
            rawX = mRawX - viewWidth;
            if (mRawY < screenHeight / 3) {
                rawY = mRawY - 100;
                mPopupWindow.setAnimationStyle(R.style.pop_anim_right_top); //设置动画
            } else {
                rawY = mRawY - viewHeight;
                mPopupWindow.setAnimationStyle(R.style.pop_anim_right_bottom); //设置动画
            }
        }
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.NO_GRAVITY, (int) rawX, (int) rawY);
//        layoutDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPopupWindow.dismiss();
//                if (mChatMessages.size() <= 0) {
//                    return;
//                } else {
//                    mChatMessages.remove(position);
//                    mChatDetailAdapter.notifyDataSetChanged();
//                    Toast.makeText(MainActivity.this, "已删除此条聊天内容", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                selectedView.setSelected(false);
            }
        });
    }

}
