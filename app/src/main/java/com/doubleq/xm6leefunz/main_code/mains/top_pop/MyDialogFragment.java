package com.doubleq.xm6leefunz.main_code.mains.top_pop;

import android.annotation.SuppressLint;
import android.app.Activity;
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
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog);
        //去出标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContentView = inflater.inflate(R.layout.pop_chat_home, container, false);
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

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
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
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.transparent)));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
    }

}
