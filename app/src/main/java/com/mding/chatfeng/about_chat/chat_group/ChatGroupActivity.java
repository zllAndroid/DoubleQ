package com.mding.chatfeng.about_chat.chat_group;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.ChatNewsWindow;
import com.mding.chatfeng.about_chat.EmotionInputDetector;
import com.mding.chatfeng.about_chat.FullImageActivity;
import com.mding.chatfeng.about_chat.GlobalOnItemClickManagerUtils;
import com.mding.chatfeng.about_chat.adapter.CommonFragmentPagerAdapter;
import com.mding.chatfeng.about_chat.fragment.ChatEmotionFragment;
import com.mding.chatfeng.about_chat.fragment.ChatFunctionFragment;
import com.mding.chatfeng.about_chat.ui.StateButton;
import com.example.zhouwei.library.CustomPopWindow;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.mding.chatfeng.about_chat.cus_data_group.CusJumpGroupChatData;
import com.mding.chatfeng.about_chat.cus_data_group.RealmGroupChatHelper;
import com.mding.chatfeng.about_utils.DensityUtil;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MathUtils;
import com.mding.chatfeng.about_utils.SoftKeyboardUtils;
import com.mding.chatfeng.about_utils.SysRunUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.about_utils.windowStatusBar;
import com.mding.chatfeng.main_code.mains.top_pop.ChatPopWindow;
import com.mding.chatfeng.main_code.ui.about_contacts.FriendDataMixActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.model.CusChatPop;
import com.mding.model.DataChatGroupPop;
import com.mding.model.DataGroupChatResult;
import com.mding.model.DataGroupChatSend;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.FullImageInfo;
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

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 项目：DoubleQ
 * 文件描述：群聊界面
 * 作者：zll
 */
public class ChatGroupActivity extends BaseActivity {

    @BindView(R.id.chat_list)
    EasyRecyclerView chatList;
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R.id.include_top_iv_back)
    ImageView mIvBack;
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

    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;
    @BindView(R.id.chat_lin_main_whole)
    LinearLayout chatLinMainWhole;
    @BindView(R.id.include_top_iv_drop)
    ImageView includeTopIvDrop;
    @BindView(R.id.include_top_lin_title)
    LinearLayout includeTopLinTitle;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatGroupAdapter chatAdapter;
    private LinearLayoutManager layoutManager;

    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    //    外来消息弹窗显示时间
    int showTime = 2000;


    RealmGroupChatHelper realmGroupChatHelper;
    RealmHomeHelper realmHomeHelper;

    HideControl hideControl;
    CusJumpGroupChatData jumpGroupChatData;
    DataSearch GroupChatData;

    public static String groupId;

    private List<MessageInfo> messageInfos;

    ChatPopWindow chatGroupPopWindow;
    DataChatGroupPop dataChatGroupPop;
    CusChatPop cusChatPop;
    String cardName;
    String isChecked = "3";

    @Override
    protected boolean isChenjinshi() {
        return false;
    }
    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        WindowBugDeal.SetTop(this);
        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
//        StateBarUtils.setFullscreen(this, false, false);

//        设置状态栏字体颜色为浅色（白色）
        StateBarUtils.setAndroidNativeLightStatusBar(this,false);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.WHITE);
    }
    @Override
    protected void initBaseView() {
        super.initBaseView();
        setAboutBar();
        SplitWeb.IS_CHAT_GROUP = "2";
        if (realmHomeHelper == null)
            realmHomeHelper = new RealmHomeHelper(this);
        if (realmGroupChatHelper == null)
            realmGroupChatHelper = new RealmGroupChatHelper(this);
        if (hideControl == null)
            hideControl = new HideControl();
        mChatTvShow.setBackgroundResource(R.color.chattrans);
//        realmLink = new RealmLinkManHelper(this);
        // 初始化接收广播
        initReceiver();
        Intent intent = getIntent();
        if (intent != null) {
            jumpGroupChatData = (CusJumpGroupChatData) intent.getSerializableExtra(Constants.KEY_FRIEND_HEADER);
            GroupChatData = (DataSearch) intent.getSerializableExtra("dataSearch");
//        final CusDataFriendRealm friendRealm = realmLink.queryFriendRealmById(FriendId);
            if (jumpGroupChatData != null) {
                groupId = jumpGroupChatData.getGroupId();
                includeTopTvTitle.setText(jumpGroupChatData.getGroupName());
            } else if (GroupChatData != null) {
                groupId = GroupChatData.getId();
                includeTopTvTitle.setText(GroupChatData.getName());
            }
            initWidget();
//        初始化数据库的聊天记录
            initRealm();
//            通知栏点击进入后，需要刷新首页的消息条数，发送广播，在首页接收，并进行刷新页面；
            realmHomeHelper.updateNumZero(groupId);
//        Intent intent2 = new Intent();
//        intent2.putExtra("message",groupId);
//        intent2.putExtra("id",groupId);
//        intent2.setAction("zero.refreshMsgFragment");
//        sendBroadcast(intent2);
            listenEnter();
        }

        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.group_chat_head_right);
        sendWeb(SplitWeb.groupSendInterface(groupId));
    }

    IntentFilter intentFilter;

    //广播接收消息推送
    private void initReceiver() {
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME);
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }

    static RealmHomeHelper realmHelper;
    public BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (realmHelper == null) {
                realmHelper = new RealmHomeHelper(ChatGroupActivity.this);
            }
            if (action.equals(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME)) {
                initGroupName(intent);
            }
//            sendBroadcast();
        }
    };

    private void initGroupName(Intent intent) {
//        String groupId = intent.getStringExtra("id");
        String groupName = intent.getStringExtra("groupName");
        includeTopTvTitle.setText(groupName);
    }

    //设置状态栏的高度为负状态栏高度，因为xml 设置了 android:fitsSystemWindows="true",会占用一个状态栏的高度；
    private void setAboutBar() {
//        获取状态栏的高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) mLinChatMain.getLayoutParams();
//获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
        layout.setMargins(0, -statusBarHeight, 0, 0);
//设置button的新位置属性,left，top，right，bottom
        mLinChatMain.setLayoutParams(layout);
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
                        if (jumpGroupChatData != null)
                            send(SplitWeb.groupSend(jumpGroupChatData.getGroupId(), ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
                        else if (GroupChatData != null) {
                            send(SplitWeb.groupSend(GroupChatData.getId(), ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
                        }
//                        send(SplitWeb.privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
                    }
                }
                return true;
            }
        });
//        editText.setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//        InputMethodManager inputManager =
//                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.showSoftInput(editText, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (chatGroupPopWindow != null)
            chatGroupPopWindow.dismiss();
        includeTopIvDrop.setClickable(false);// 屏蔽主动获得点击
        includeTopIvDrop.setPressed(false);
        includeTopIvDrop.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SplitWeb.IS_CHAT_GROUP = "00";
        try {
//            realmHomeHelper.updateNumZero(groupId);
//            Intent intent2 = new Intent();
//            intent2.putExtra("message",groupId);
//            intent2.putExtra("id",groupId);
//            intent2.setAction("zero.refreshMsgFragment");
//            sendBroadcast(intent2);
            realmGroupChatHelper.close();
            realmHomeHelper.close();
            realmGroupChatHelper = null;
            realmHomeHelper = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mRefreshBroadcastReceiver != null)
                unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (chatGroupPopWindow != null){
            chatGroupPopWindow.dismiss();
            chatGroupPopWindow=null;
        }
    }

    ArrayList<DataJieShou.RecordBean> mList = new ArrayList<>();

    private void initRealm() {
        List<CusGroupChatData> cusRealmChatMsgs = realmGroupChatHelper.queryAllGroupChat(groupId);
        if (cusRealmChatMsgs != null && cusRealmChatMsgs.size() != 0) {
            chatAdapter.addAll(cusRealmChatMsgs);
            chatAdapter.notifyDataSetChanged();
            //    滑动到底部
            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
        } else {
        }
    }

    @OnClick({R.id.include_top_iv_more, R.id.include_top_lin_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_iv_more:
//                IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupId);
                IntentUtils.JumpToHaveTwo(GroupChatDetailsActivity.class, AppConfig.GROUP_ID,groupId,AppConfig.IS_CHATGROUP_TYPE,AppConfig.CHATGROUP);
//

                break;
            case R.id.include_top_lin_title:
                includeTopIvDrop.setActivated(true);
                cusChatPop = new CusChatPop();
                cusChatPop.setType("2");
                cusChatPop.setContext(this);
                cusChatPop.setChatLinMainWhole(chatLinMainWhole);
                cusChatPop.setGroupId(groupId);
                cusChatPop.setIsChecked(isChecked);
                cusChatPop.setCardName(cardName);
                if (chatGroupPopWindow == null)
                    chatGroupPopWindow = new ChatPopWindow(cusChatPop);
                chatGroupPopWindow.showAtBottom(includeTopLinTitle);
                chatGroupPopWindow.setOnClickOutSideListener(new ChatPopWindow.OnClickOutSideListener() {
                    @Override
                    public void Clicked(String type) {
                        includeTopIvDrop.setActivated(false);
                    }
                });
                chatGroupPopWindow.setOnReCardNameListener(new ChatPopWindow.OnReCardNameListener() {
                    @Override
                    public void reCardName(String cardName) {
                        if (cardName != null)
                            sendWeb(SplitWeb.setGroupCarteModify(groupId, cardName));
                    }
                });
                break;
        }
//        AppManager.getAppManager().finishActivity(ChatGroupActivity.this);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat;
    }

    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

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

        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
//        mInputLin.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (emotionLayout.getVisibility()==View.VISIBLE)
//                {
//                    if (layoutManager!=null&&chatAdapter!=null)
//                        layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
//                }
//                return false;
//            }
//        });
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
        chatAdapter = new ChatGroupAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.setRecycleChildrenOnDetach(true);//复用RecycledViewPool
        chatList.setLayoutManager(layoutManager);

//        chatList.setRefreshingColorResources(R.color.red);
//        chatList.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                chatList.setRefreshing(false);
//                ToastUtil.show("刷新");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        chatList.setRefreshing(false);
//                    }
//                },2000);
//            }
//        });
        // 外部对RecyclerView设置监听
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 查看源码可知State有三种状态：SCROLL_STATE_IDLE（静止）、SCROLL_STATE_DRAGGING（上升）、SCROLL_STATE_SETTLING（下落）
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
                    chatAdapter.setScrolling(false);
                    chatAdapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else
                    chatAdapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        chatList.setAdapter(chatAdapter);

//        chatAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(int position) {
//                return false;
//
//
//            }
//        });
//        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        chatAdapter.handler.removeCallbacksAndMessages(null);
//                        chatAdapter.notifyDataSetChanged();
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        if (emotionLayout.isShown()) {
//                            emotionLayout.setVisibility(View.GONE);
//                        }
//                        chatAdapter.handler.removeCallbacksAndMessages(null);
//                        mDetector.hideEmotionLayout(false);
//                        mDetector.hideSoftInput();
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
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
//        LoadData();
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
            if (jumpGroupChatData != null)
                send(SplitWeb.groupSend(jumpGroupChatData.getGroupId(), ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
            else if (GroupChatData != null)
                send(SplitWeb.groupSend(GroupChatData.getId(), ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
        } else {
            Log.e("chat", "---------------------------------" + ed);
            ToastUtil.show("发送信息不能为空");
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            发送消息返回
            case "groupSend":
                String ed = editText.getText().toString().trim();
                if (!StrUtils.isEmpty(ed)) {
                    editText.setText("");
                }
                dealSendResult(responseText);
                break;
//                接收消息返回
            case "groupReceive":
                dealReceiverResult(responseText);
                break;
            case "groupSendInterface":
                dataChatGroupPop = JSON.parseObject(responseText, DataChatGroupPop.class);
                DataChatGroupPop.RecordBean recordBean = dataChatGroupPop.getRecord();
                if (recordBean != null) {
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean groupDetailInfoBean = recordBean.getGroupDetailInfo();
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean.UserInfoBean userInfoBean = groupDetailInfoBean.getUserInfo();
                    cardName = StrUtils.isEmpty(userInfoBean.getCarteName()) ? "暂未设置" : userInfoBean.getCarteName();
                    isChecked = userInfoBean.getDisturbType();
                    Log.e("setUserGroupDisturb","-------------------群聊界面请求-----------------"+isChecked);
                }
                break;
        }
    }

    ChatNewsWindow chatWindow = null;
    private void dealReceiverResult(String responseText) {
        DataGroupChatResult dataJieShou1 = JSON.parseObject(responseText, DataGroupChatResult.class);
        final DataGroupChatResult.RecordBean record2 = dataJieShou1.getRecord();
        if (record2 != null) {
//                    收到聊天页的此人的消息
            if (record2.getGroupId().equals(groupId)) {
//                record2.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                record2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                CusChatData cusRealmChatMsg = new CusChatData();
//                    realmHelper.addRealmChat(FriendId,msg,messageType,Constants.CHAT_ITEM_TYPE_RIGHT, TimeUtil.sf.format(new Date()));
                String myTime = record2.getRequestTime();
                String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME, "");
                SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME, (String) record2.getRequestTime());
                if (!StrUtils.isEmpty(time)) {
                    try {
                        int i = TimeUtil.stringDaysBetween(myTime, time);
                        if (MathUtils.abs(i) < 5) {
                            myTime = "";
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                CusGroupChatData groupChatData = new CusGroupChatData();
                groupChatData.setNameFriend(record2.getMemberName());
                groupChatData.setNameGroup(record2.getGroupName());
                groupChatData.setMessage(record2.getMessage());
                groupChatData.setImgGroup(record2.getGroupHeadImg());
                groupChatData.setImgHead(record2.getMemberHeadImg());
                groupChatData.setGroupId(record2.getGroupId());
                groupChatData.setFriendId(record2.getMemberId());
                groupChatData.setGroupUserId(record2.getGroupId() + SplitWeb.getUserId());
                groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                groupChatData.setCreated(myTime);
                groupChatData.setMessageType(record2.getMessageType());
                groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
//                cusRealmChatMsg.setCreated(myTime);
//                cusRealmChatMsg.setMessage(record2.getMessage());
//                cusRealmChatMsg.setMessageType(record2.getMessageType());
//                cusRealmChatMsg.setReceiveId(record2.getFriendsId());
//                cusRealmChatMsg.setSendId(record2.getUserId());
//                cusRealmChatMsg.setUserMessageType(record2.getType());
//                realmGroupChatHelper.addRealmChat(groupChatData);
//                    addChatRealm(record.getMessage());
                chatAdapter.add(groupChatData);
                chatAdapter.notifyDataSetChanged();
//                    滑动到底部
                layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
//                CusHomeRealmData homeRealmData = realmHomeHelper.queryAllRealmChat(record2.getFriendsId());
//
//                if (homeRealmData!=null) {
////                    realmHomeHelper.updateMsg(record2.getFriendsId(), record2.getMessage(), record2.getRequestTime());//更新首页聊天界面数据（消息和时间）
//                    realmHomeHelper.updateNumZero(record2.getFriendsId());//更新首页聊天界面数据（未读消息数目）
//                }
                Intent intent = new Intent();
                intent.putExtra("message", record2.getMessage());
                intent.putExtra("id", record2.getGroupId());
                intent.setAction("zero.refreshMsgFragment");
                sendBroadcast(intent);
            } else {
                ToastUtil.show("收到一条来自" + record2.getGroupName() + "的消息");
                Intent intent = new Intent();
                intent.putExtra("message", record2.getMessage());
                intent.putExtra("id", record2.getGroupId());
                intent.setAction("action.refreshMsgFragment");
                sendBroadcast(intent);
            }

            if (!SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {

                Intent intent = new Intent();
                intent.putExtra("message", record2.getMessage());
                intent.putExtra("id", record2.getGroupId());
                intent.setAction("action.refreshMsgFragment");
                sendBroadcast(intent);
                //APP在后台的时候处理接收到消息的事件
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            bitmap = Glide.with(MyApplication.getAppContext())
//                                    .load(record2.getHeadImg())
//                                    .asBitmap() //必须
//                                    .centerCrop()
//                                    .into(500, 500)
//                                    .get();
//                            NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
//                            notificationUtils.sendNotification(cusJumpChatData, record2.getFriendsName(), record2.getMessage(), bitmap, AppConfig.TYPE_CHAT);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
            }
        }
    }

    String time = null;
    private void dealSendResult(String responseText) {
        DataGroupChatSend dataJieShou = JSON.parseObject(responseText, DataGroupChatSend.class);
        DataGroupChatSend.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME, "");
            if (StrUtils.isEmpty(time)) {
                SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
            } else {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
                    if (MathUtils.abs(i) < 5) {
                        record.setRequestTime("");
                    } else {
                        record.setRequestTime(record.getRequestTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            CusGroupChatData groupChatData = new CusGroupChatData();
            groupChatData.setMessage(record.getMessage());
            groupChatData.setGroupId(record.getGroupId());
            groupChatData.setFriendId(record.getUserId());
            groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getUserId());
            groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            groupChatData.setCreated(record.getRequestTime());
            groupChatData.setMessageType(record.getMessageType());
//            groupChatData.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
            groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_RIGHT);
            chatAdapter.add(groupChatData);
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
    private ChatGroupAdapter.onItemClickListener itemClickListener = new ChatGroupAdapter.onItemClickListener() {

        @Override
        public void onHeaderClick(int position, int type, String friendId) {
            switch (type) {
                case Constants.CHAT_ITEM_TYPE_LEFT:
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    break;
                case Constants.CHAT_ITEM_TYPE_RIGHT:
//                    TODO 点击自己头像，显示自己的信息
                    IntentUtils.JumpTo(ChangeInfoActivity.class);
//                    IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",SplitWeb.getUserId());
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
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(ChatGroupActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
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
                            if (SoftKeyboardUtils.isSoftShowing(ChatGroupActivity.this)) {
                                SoftKeyboardUtils.showSoftKeyboard(ChatGroupActivity.this);
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
