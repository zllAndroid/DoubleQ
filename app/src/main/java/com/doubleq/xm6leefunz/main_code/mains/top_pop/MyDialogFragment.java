package com.doubleq.xm6leefunz.main_code.mains.top_pop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_chat.GlobalOnItemClickManagerUtils;
import com.doubleq.xm6leefunz.about_chat.adapter.ChatAdapter;
import com.doubleq.xm6leefunz.about_chat.adapter.CommonFragmentPagerAdapter;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatEmotionFragment;
import com.doubleq.xm6leefunz.about_chat.fragment.ChatFunctionFragment;
import com.doubleq.xm6leefunz.about_chat.ui.StateButton;
import com.rance.chatui.widget.NoScrollViewPager;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class MyDialogFragment extends DialogFragment {
//    public MyDialogFragment() {
////        // Required empty public constructor
////    }
    String friend;
    public MyDialogFragment(String friend) {
        this.friend=friend;
        // Required empty public constructor
    }

    View mContentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ActionBar.Custom);
//        Dialog dialog = new Dialog(getActivity(), R.style.CustomDialog);
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //去出标题
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        mContentView = inflater.inflate(R.layout.pop_chat_home, container, false);
        // 设置宽度为屏宽, 靠近屏幕底部。
//        Window window =  getDialog().getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
////        lp.dimAmount = 0.0f;
//        lp.gravity = Gravity.BOTTOM; // 紧贴底部
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
////        window.setBackgroundDrawableResource(android.R.color.transparent);
//        window.setAttributes(lp);
//        ColorDrawable dw = new ColorDrawable(0x00000000);
        initUI();
        return mContentView;
    }
    EditText editText;
    TextView voiceText;
    ImageView emotionButton;
    ImageView emotionAdd;
    StateButton emotionSend;
    NoScrollViewPager viewpager;
    RelativeLayout emotionLayout;

    private void initUI() {
        editText= mContentView.findViewById(R.id.edit_text);

        voiceText= mContentView.findViewById(R.id.voice_text);

        emotionButton= mContentView.findViewById(R.id.emotion_button);
        emotionAdd= mContentView.findViewById(R.id.emotion_add);
        emotionSend= mContentView.findViewById(R.id.emotion_send);
        viewpager= mContentView.findViewById(R.id.viewpager);
        emotionLayout= mContentView.findViewById(R.id.emotion_layout);

        initWidget();
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
        adapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputManager.with((Activity) getActivity(),friend)
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
        mDetector.showSoftInput();
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(getActivity());
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
    @Override
    public void onStart() {
        super.onStart();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.TranslucentNoTitle);

        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
//        win.setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.transparent)));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        //        Window window =  getDialog().getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
        params.dimAmount = 0.0f;
//        lp.gravity = Gravity.BOTTOM; // 紧贴底部
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
////        window.setBackgroundDrawableResource(android.R.color.transparent);
//        window.setAttributes(lp);
//        ColorDrawable dw = new ColorDrawable(0x00000000);
        win.setAttributes(params);
    }

}
