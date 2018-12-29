package com.doubleq.xm6leefunz.about_chat;

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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataChatHisList;
import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.adapter.ChatAdapter;
import com.doubleq.xm6leefunz.about_chat.adapter.CommonFragmentPagerAdapter;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatEmotionFragment;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatFunctionFragment;
import com.doubleq.xm6leefunz.about_chat.ui.StateButton;
import com.doubleq.xm6leefunz.about_utils.DensityUtil;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.NotificationUtil;
import com.doubleq.xm6leefunz.about_utils.SoftKeyboardUtils;
import com.doubleq.xm6leefunz.about_utils.SysRunUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmLinkManHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.example.zhouwei.library.CustomPopWindow;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
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
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.doubleq.xm6leefunz.about_utils.IntentUtils.JumpToHaveOne;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
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
    @BindView(R.id.include_top_tv_tital)
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

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //        设置导航栏颜色
    public void initStateBar() {

    }

    //    好友id
    public static String FriendId = "";
    //    消息类型
    public static String messageType = "1";
    //    好友头像
    public static String friendHeader = "";
    //    我的头像
    public static String MyHeader = "";
    RealmChatHelper realmHelper;
    RealmHomeHelper realmHomeHelper;
    HideControl hideControl;
    RealmLinkManHelper realmLink;
    CusJumpChatData cusJumpChatData;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        setAboutBar();
        SplitWeb.IS_CHAT = "1";
        realmHomeHelper = new RealmHomeHelper(this);
        realmHelper = new RealmChatHelper(this);
        hideControl = new HideControl();
        mChatTvShow.setBackgroundResource(R.color.chattrans);
//        realmLink = new RealmLinkManHelper(this);
        Intent intent = getIntent();
        cusJumpChatData = (CusJumpChatData) intent.getSerializableExtra(Constants.KEY_FRIEND_HEADER);
        FriendId = cusJumpChatData.getFriendId();
//        final CusDataFriendRealm friendRealm = realmLink.queryFriendRealmById(FriendId);
        friendHeader = cusJumpChatData.getFriendHeader();
        includeTopTvTital.setText("和" + cusJumpChatData.getFriendName() + "的聊天");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.person);

        initWidget();
//        初始化数据库的聊天记录
        initRealm();
//            通知栏点击进入后，需要刷新首页的消息条数，发送广播，在首页接收，并进行刷新页面；
        realmHomeHelper.updateNumZero(FriendId);
        Intent intent2 = new Intent();
        intent2.putExtra("message", FriendId);
        intent2.putExtra("id", FriendId);
        intent2.setAction("zero.refreshMsgFragment");
        sendBroadcast(intent2);
        listenEnter();
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
                        send(SplitWeb.privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
                    }
                }
                return true;
            }
        });
    }

    @OnClick(R.id.include_top_iv_more)
    public void onViewClicked() {
        JumpToHaveOne(ChatSetActivity.class,"FriendId",FriendId);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SplitWeb.IS_CHAT = "00";

        try {
            realmHomeHelper.updateNumZero(FriendId);
            Intent intent2 = new Intent();
            intent2.putExtra("message", FriendId);
            intent2.putExtra("id", FriendId);
            intent2.setAction("zero.refreshMsgFragment");
            sendBroadcast(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        realmHelper.close();
        realmHomeHelper.close();

    }

    ArrayList<DataJieShou.RecordBean> mList = new ArrayList<>();

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
                mList.add(recordBean);
            }
            chatAdapter.addAll(mList);
            chatAdapter.notifyDataSetChanged();
            //    滑动到底部
            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);


        } else {
//            sendWeb(SplitWeb.messageObtain(FriendId));
        }
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
        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);

        chatAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                return false;


            }
        });
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:

                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        if (emotionLayout.isShown()) {
                            emotionLayout.setVisibility(View.GONE);
                        }
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);

        chatList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN: //手指按下
//                        点击列表，软键盘或者表情列表存在，则关闭他们；
                        if (isSoftShowing() || emotionLayout.isShown()) {
                            emotionLayout.setVisibility(View.GONE);
                            chatAdapter.handler.removeCallbacksAndMessages(null);
                            mDetector.hideEmotionLayout(false);
                            mDetector.hideSoftInput();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                } //end switch
                return isSoftShowing();
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
            send(SplitWeb.privateSend(ChatActivity.FriendId, ed, ChatActivity.messageType, TimeUtil.getTime()));
        } else {
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            发送消息返回
            case "privateSend":
                String ed = editText.getText().toString().trim();
                if (!StrUtils.isEmpty(ed))
                {
                    editText.setText("");
                }
                dealSendResult(responseText);
                break;
            case "messageObtain":
                DataChatHisList dataChatHisList = JSON.parseObject(responseText, DataChatHisList.class);
                DataChatHisList.RecordBean record1 = dataChatHisList.getRecord();
                if (record1 != null) {
                    List<DataChatHisList.RecordBean.MessageListBean> messageList = record1.getMessageList();
//                    messageStoId
                    if (messageList != null && messageList.size() != 0) {
                        mList.clear();
                        if (messageList.size() != 0) {
                            String messageStoId = messageList.get(0).getMessageStoId();
                            if (StrUtils.isEmpty(messageStoId)) {
//                        ToastUtil.show("列表为空");
                                return;
                            }
                        }
                        for (int i = 0; i < messageList.size(); i++) {
                            DataJieShou.RecordBean recordBean = new DataJieShou.RecordBean();
                            recordBean.setType(messageList.get(i).getUserMessageType());
                            recordBean.setMessage(messageList.get(i).getMessage());
                            recordBean.setMessageType(messageList.get(i).getMessageType());
                            recordBean.setRequestTime(messageList.get(i).getCreated());
                            mList.add(recordBean);
                        }
                        chatAdapter.addAll(mList);
                        chatAdapter.notifyDataSetChanged();
                        //    滑动到底部
                        layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                    }
                }
                break;
//                接收消息返回
            case "privateReceive":
                dealReceiverResult(responseText);
                break;
        }
    }

    ChatNewsWindow chatWindow = null;

    private void dealReceiverResult(String responseText) {
        DataJieShou dataJieShou1 = JSON.parseObject(responseText, DataJieShou.class);
        final DataJieShou.RecordBean record2 = dataJieShou1.getRecord();
        if (record2 != null) {
//                    收到聊天页的此人的消息
            if (record2.getFriendsId().equals(FriendId)) {
                record2.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                record2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                CusChatData cusRealmChatMsg = new CusChatData();
//                    realmHelper.addRealmChat(FriendId,msg,messageType,Constants.CHAT_ITEM_TYPE_RIGHT, TimeUtil.sf.format(new Date()));
//                String myTime=record2.getRequestTime();
                String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME, "");
                if (StrUtils.isEmpty(time)) {
                    SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME, (String) record2.getRequestTime());
                } else {
                    try {
                        int i = TimeUtil.stringDaysBetween(record2.getRequestTime(), time);
                        Log.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME, (String) record2.getRequestTime());
                        if (Math.abs(i) < 5) {
                            record2.setRequestTime("");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
//                cusRealmChatMsg.setCreated(myTime);
//                cusRealmChatMsg.setMessage(record2.getMessage());
//                cusRealmChatMsg.setMessageType(record2.getMessageType());
//                cusRealmChatMsg.setReceiveId(record2.getFriendsId());
//                cusRealmChatMsg.setSendId(record2.getUserId());
//                cusRealmChatMsg.setUserMessageType(record2.getType());
//                        realmHelper.addRealmChat(cusRealmChatMsg);
//                    addChatRealm(record.getMessage());
                chatAdapter.add(record2);
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
                intent.putExtra("id", record2.getFriendsId());
                intent.setAction("zero.refreshMsgFragment");
                sendBroadcast(intent);
            } else {
//                        mChatTvShow.setText(record2.getFriendsName()+":"+record2.getMessage());
//                view = LayoutInflater.from(this).inflate(R.layout.item_chat_new, null);
//                TextView mTv = view.findViewById(R.id.item_tv_news);
//                mTv.setText(record2.getFriendsName()+":"+record2.getMessage());
//                chatWindow = new ChatNewsWindow(ChatActivity.this,record2.getFriendsName()+":"+record2.getMessage());
//
//                hideControl.resetHideTimer();
//                hideControl.startHideTimer();
                ToastUtil.show("收到一条来自" + record2.getFriendsName() + "的消息");
                Intent intent = new Intent();
                intent.putExtra("message", record2.getMessage());
                intent.putExtra("id", record2.getFriendsId());
                intent.setAction("action.refreshMsgFragment");
                sendBroadcast(intent);
            }

            if (!SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {

                Intent intent = new Intent();
                intent.putExtra("message", record2.getMessage());
                intent.putExtra("id", record2.getFriendsId());
                intent.setAction("action.refreshMsgFragment");
                sendBroadcast(intent);
                //APP在后台的时候处理接收到消息的事件
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bitmap = Glide.with(MyApplication.getAppContext())
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

    String time = null;


    private void dealSendResult(String responseText) {
        DataJieShou dataJieShou = JSON.parseObject(responseText, DataJieShou.class);
        DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME, "");
            if (StrUtils.isEmpty(time)) {
                SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
            } else {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    Log.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                    SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
                    if (Math.abs(i) < 5) {
                        record.setRequestTime("");
                    } else {
                        record.setRequestTime(record.getRequestTime());
//                        SPUtils.put(this,AppConfig.CHAT_SEND_TIME,"");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                    IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", friendId);
                    break;
                case Constants.CHAT_ITEM_TYPE_RIGHT:
//                    TODO 点击自己头像，显示自己的信息
                    IntentUtils.JumpTo(ChangeInfoActivity.class);
//                    IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", SplitWeb.getUserId());
                    break;
            }
        }

        @Override
        public void onConClick(View view, MotionEvent event, int position, String conText) {
            mRawX = event.getRawX();
            mRawY = event.getRawY();
            mPressedPos = position;
            Log.d("onConClick", "e.getRawX()横坐标=" + mRawX + ", e.getRawY()纵坐标=" + mRawY);
            Log.d("onConClick", "position=" + position);
            initPopWindow(view, position);
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
            startActivity(new Intent(ChatActivity.this, FullImageActivity.class));
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
