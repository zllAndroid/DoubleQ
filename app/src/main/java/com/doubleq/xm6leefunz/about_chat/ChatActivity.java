package com.doubleq.xm6leefunz.about_chat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.ChatBaseActivity;
import com.doubleq.xm6leefunz.about_chat.adapter.ChatAdapter;
import com.doubleq.xm6leefunz.about_chat.adapter.CommonFragmentPagerAdapter;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatEmotionFragment;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatFunctionFragment;
import com.doubleq.xm6leefunz.about_chat.ui.StateButton;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.rance.chatui.enity.FullImageInfo;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.model.WebChatResult;
import com.rance.chatui.util.Constants;
import com.rance.chatui.util.MediaManager;
import com.rance.chatui.web.MyWebSocketService;
import com.rance.chatui.widget.EmotionInputDetector;
import com.rance.chatui.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatActivity extends ChatBaseActivity {

    @BindView(R.id.chat_list)
    EasyRecyclerView chatList;
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
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

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    private Intent websocketServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//        ButterKnife.bind(this);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("聊天");
        EventBus.getDefault().register(this);
        initServic();
        initWidget();
    }

//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat;
    }


//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }

    private void initServic() {
        websocketServiceIntent = new Intent(this, MyWebSocketService.class);
        startService(websocketServiceIntent);
        MyWebSocketService myWebSocketService = new MyWebSocketService();
        myWebSocketService.SetOnMsgListener(new MyWebSocketService.OnMsgListener() {
            @Override
            public void onMsg(String msg) {
            }

            @Override
            public void onSuc(String msg) {
//                Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

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

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
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
        LoadData();
    }

    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(ChatActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
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

    /**
     * 构造聊天数据
     */
    private void LoadData() {
        messageInfos = new ArrayList<>();

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        messageInfos.add(messageInfo);

        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
        messageInfo1.setVoiceTime(3000);
        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo1);

        MessageInfo messageInfo2 = new MessageInfo();
        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo2.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        messageInfos.add(messageInfo2);

        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setContent("[微笑][色][色][色]");
        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo3);

        chatAdapter.addAll(messageInfos);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    boolean isFirst = true;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        //                前两行是发送的消息
        messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        chatAdapter.notifyDataSetChanged();
        String ed = messageInfo.getContent();
//        String string="{\"ctn\":\"IM\", \"mtn\":\"bindUid\",\"data\":[{\"user_id\":\"1\",\"msg\":\""+ed+"\"}]}";
        String string = "{\"ctn\":\"IM\", \"mtn\":\"chatUser\",\"data\":[{\"user_id\":\"500\",\"msg\":\"" + ed + "\"}]}";
        MyWebSocketService myWebSocketService = new MyWebSocketService();
        myWebSocketService.SetOnMsgListener(new MyWebSocketService.OnMsgListener() {
            @Override
            public void onMsg(String msg) {


//                messageTv.setText(msg);
                MessageInfo message = new MessageInfo();
                WebChatResult webChatResult = JSON.parseObject(msg, WebChatResult.class);
                if (isFirst)
                    message.setContent("成功连接");
                else
                    message.setContent(webChatResult.getRecord().getMsg());

                isFirst = false;
                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                message.setHeader("http://p1cpgl5ic.bkt.clouddn.com/lurongbai.png");
                messageInfos.add(message);
                chatAdapter.add(message);
                chatList.scrollToPosition(chatAdapter.getCount() - 1);
            }

            @Override
            public void onSuc(String msg) {
//                Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                chatAdapter.notifyDataSetChanged();
//            }
//        }, 2000);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                MessageInfo message = new MessageInfo();
//                message.setContent("这是模拟消息回复");
//                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//                messageInfos.add(message);
//                chatAdapter.add(message);
//                chatList.scrollToPosition(chatAdapter.getCount() - 1);
//            }
//        }, 3000);
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }
}
