package com.doubleq.xm6leefunz.main_code.mains.top_pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_chat.EmotionInputDetector;
import com.doubleq.xm6leefunz.about_chat.GlobalOnItemClickManagerUtils;
import com.doubleq.xm6leefunz.about_chat.adapter.ChatAdapter;
import com.doubleq.xm6leefunz.about_chat.adapter.CommonFragmentPagerAdapter;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatEmotionFragment;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatFunctionFragment;
import com.doubleq.xm6leefunz.about_chat.ui.StateButton;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 物品选择
 */
public class MsgChatWindow extends PopupWindow {

    Context mContext;
    private LayoutInflater mInflater;


    EditText editText;
    TextView voiceText;
    ImageView emotionButton;
    ImageView emotionAdd;
    StateButton emotionSend;
    NoScrollViewPager viewpager;
    RelativeLayout emotionLayout;

    private View mContentView;
    RecyclerView mRecyclerView;
    String firendId;
    List<String> arrayList = new ArrayList<>();
    public MsgChatWindow(Context context,String firendId) {
        super(context);

        this.firendId = firendId;
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.pop_chat_home, null);
        //设置View
        setContentView(mContentView);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);

//        setHeight((MainActivity.screenHeight) / 4);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        /**
         * 设置进出动画
         */
//        setAnimationStyle(R.style.AnimDown);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
//        setBackgroundDrawable(new ColorDrawable());
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);


        /**pop_lin_choose
         * 设置点击外部可以消失
         */
        mContentView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mContentView.findViewById(R.id.pop_in_top).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {

                        dismiss();
                    }
                }
                return true;
            }
        });
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });
        /**
         * 初始化View与监听器
         */
        initView();
    }
    private void initView() {
         editText= mContentView.findViewById(R.id.edit_text);

         voiceText= mContentView.findViewById(R.id.voice_text);

         emotionButton= mContentView.findViewById(R.id.emotion_button);
         emotionAdd= mContentView.findViewById(R.id.emotion_add);
         emotionSend= mContentView.findViewById(R.id.emotion_send);
         viewpager= mContentView.findViewById(R.id.viewpager);
         emotionLayout= mContentView.findViewById(R.id.emotion_layout);

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        initWidget();
    }
    private EmotionInputManager mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
//        adapter = new CommonFragmentPagerAdapter(mContentView.getChildFragmentManager(), fragments);
//        viewpager.setAdapter(adapter);
//        viewpager.setCurrentItem(0);

        mDetector = EmotionInputManager.with((Activity) mContext,firendId)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
//                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
//                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(mContext);
        globalOnItemClickListener.attachToEditText(editText);
//        chatAdapter = new ChatAdapter(mContext);
//        layoutManager = new LinearLayoutManager(mContext);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        chatList.setLayoutManager(layoutManager);
//        chatList.setAdapter(chatAdapter);
//        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        chatAdapter.handler.removeCallbacksAndMessages(null);
//                        chatAdapter.notifyDataSetChanged();
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
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
//        chatAdapter.addItemClickListener(itemClickListener);
//        LoadData();
    }
}