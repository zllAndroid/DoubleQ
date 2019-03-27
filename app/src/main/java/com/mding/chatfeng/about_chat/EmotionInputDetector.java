package com.mding.chatfeng.about_chat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.about_utils.about_immersive.StatusBarUtil;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.rance.chatui.R;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.util.AudioRecoderUtils;
import com.rance.chatui.util.Constants;
import com.rance.chatui.util.PopupWindowFactory;
import com.rance.chatui.util.Utils;

import org.greenrobot.eventbus.EventBus;

public class EmotionInputDetector {

    private static final String SHARE_PREFERENCE_NAME = "com.dss886.emotioninputdetector";
    private static final String SHARE_PREFERENCE_TAG = "soft_input_height";

    private Activity mActivity;
    private InputMethodManager mInputManager;
    private SharedPreferences sp;
    private View mEmotionLayout;
    private EditText mEditText;
    private TextView mVoiceText;
    private View mContentView;
    private View inputlayout;
    private ViewPager mViewPager;
    private View mSendButton;
    private View mAddButton;
    private View mEmotionButton;
    private Boolean isShowEmotion = false;
    private Boolean isShowAdd = false;
    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mVoicePop;
    private TextView mPopVoiceText;

    private EmotionInputDetector() {
    }

    public static EmotionInputDetector with(Activity activity) {
        EmotionInputDetector emotionInputDetector = new EmotionInputDetector();
        emotionInputDetector.mActivity = activity;
        emotionInputDetector.mInputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        emotionInputDetector.sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return emotionInputDetector;
    }

    public EmotionInputDetector bindToContent(View contentView) {
        mContentView = contentView;
        return this;
    }
    public EmotionInputDetector bindToInputLayout(View mInputlayout) {
        inputlayout = mInputlayout;
        return this;
    }

    public EmotionInputDetector bindToEditText(EditText editText) {
        mEditText = editText;
        mEditText.requestFocus();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && mEmotionLayout.isShown()) {
//                    lockContentHeight();

                    hideEmotionLayout(true);
                    isShowEmotion = false;
                    isShowAdd = false;
//                    mEditText.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            unlockContentHeightDelayed();
//                        }
//                    }, 200L);
                }
                return false;
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mAddButton.setVisibility(View.GONE);
                    mSendButton.setVisibility(View.VISIBLE);
                } else {
                    mAddButton.setVisibility(View.VISIBLE);
                    mSendButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return this;
    }
    public EmotionInputDetector bindToEmotionButton(View emotionButton) {
        mEmotionButton=emotionButton;
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mEmotionLayout.getVisibility()==View.VISIBLE) {
//                是否显示表情
                if (mEmotionLayout.isShown()) {
                    if (isShowAdd) {
//                    在文件状态切换显示表情
                        mViewPager.setCurrentItem(0);
                        isShowEmotion = true;
                        isShowAdd = false;
                    } else {
//                        lockContentHeight();
                        hideEmotionLayout(false);
//                        showEmotionLayout();
//                        unlockContentHeightDelayed();
//                        mViewPager.setCurrentItem(0);
                        isShowEmotion = false;
                        isShowAdd = false;
                    }
                }
                else
                {
                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                        showInputAddr(true);
                        mViewPager.setCurrentItem(0);
                        isShowEmotion = true;
                        isShowAdd = false;
                    }else
                    {
                        showEmotionLayout();
                        unlockContentHeightDelayed();

                        mViewPager.setCurrentItem(0);
                        isShowEmotion = true;
                        isShowAdd = false;
                    }
                }
            }
        });
        return this;
    }

    public EmotionInputDetector bindToAddButton(View addButton) {
        mAddButton = addButton;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mEmotionLayout.getVisibility()==View.VISIBLE) {
                if (mEmotionLayout.isShown()) {
                    if (isShowEmotion) {
                        mViewPager.setCurrentItem(1);
                        isShowAdd = true;
                        isShowEmotion = false;
                    } else {
//                        lockContentHeight();
                        hideEmotionLayout(false);
//                        showEmotionLayout();
//                        unlockContentHeightDelayed();
//                        mViewPager.setCurrentItem(0);
                        isShowEmotion = false;
                        isShowAdd = false;


//                        lockContentHeight();
//                        hideEmotionLayout(true);
//                        isShowAdd = false;
//                        unlockContentHeightDelayed();
                    }
                } else {

//                    if (isSoftInputShown()) {
//                        lockContentHeight();
//                        showEmotionLayout();
//                        unlockContentHeightDelayed();
//                        mViewPager.setCurrentItem(1);
//                        isShowAdd = true;
//                        isShowEmotion = false;
//                    }

                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                        showInputAddr(true);
                        mViewPager.setCurrentItem(1);
                        isShowEmotion = false;
                        isShowAdd = true;
                    }else
                    {
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                        mViewPager.setCurrentItem(1);

                        isShowEmotion = false;
                        isShowAdd = true;
                    }
                }
            }
        });
        return this;
    }
    public EmotionInputDetector bindToSendButton(View sendButton) {
        mSendButton = sendButton;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddButton.setVisibility(View.VISIBLE);
                mSendButton.setVisibility(View.GONE);
                String ed = mEditText.getText().toString().trim();
//                BaseActivity.send(SplitWeb.privateSend(ChatActivity.FriendId,ed,ChatActivity.messageType, TimeUtil.getTime()));
                DataJieShou.RecordBean messageInfo = new DataJieShou.RecordBean();
//                MyWebSocketService.sendMsg(SplitWeb.privateSend(ChatActivity.FriendId,ed,ChatActivity.messageType));
                messageInfo.setMessageType(Constants.CHAT_TEXT);
                messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);

                messageInfo.setMessage(ed);
//                messageInfo.set
                EventBus.getDefault().post(messageInfo);
//                mEditText.setText("");
            }
        });
        return this;
    }

    public EmotionInputDetector bindToVoiceButton(View voiceButton) {
        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideEmotionLayout(false);
                hideSoftInput();
                mVoiceText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                mEditText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        return this;
    }

    public EmotionInputDetector bindToVoiceText(TextView voiceText) {
        mVoiceText = voiceText;
        mAudioRecoderUtils = new AudioRecoderUtils();
        mVoiceText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获得x轴坐标
                int x = (int) event.getX();
                // 获得y轴坐标
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mVoicePop.showAtLocation(v, Gravity.CENTER, 0, 0);
                        mVoiceText.setText("松开结束");
                        mPopVoiceText.setText("手指上滑，取消发送");
                        mVoiceText.setTag("1");
                        mAudioRecoderUtils.startRecord(mActivity);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (wantToCancle(x, y)) {
                            mVoiceText.setText("松开结束");
                            mPopVoiceText.setText("松开手指，取消发送");
                            mVoiceText.setTag("2");
                        } else {
                            mVoiceText.setText("松开结束");
                            mPopVoiceText.setText("手指上滑，取消发送");
                            mVoiceText.setTag("1");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mVoicePop.dismiss();
                        if (mVoiceText.getTag().equals("2")) {
                            //取消录音（删除录音文件）
                            mAudioRecoderUtils.cancelRecord();
                        } else {
                            //结束录音（保存录音文件）
                            mAudioRecoderUtils.stopRecord();
                        }
                        mVoiceText.setText("按住说话");
                        mVoiceText.setTag("3");
                        mVoiceText.setVisibility(View.GONE);
                        mEditText.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
        return this;
    }

    private boolean wantToCancle(int x, int y) {
        // 超过按钮的宽度
        if (x < 0 || x > mVoiceText.getWidth()) {
            return true;
        }
        // 超过按钮的高度
        if (y < -50 || y > mVoiceText.getHeight() + 50) {
            return true;
        }
        return false;
    }

    public EmotionInputDetector setEmotionView(View emotionView) {
        mEmotionLayout = emotionView;
        return this;
    }

    public EmotionInputDetector setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        return this;
    }

    public EmotionInputDetector build() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        hideSoftInput();
        mAudioRecoderUtils = new AudioRecoderUtils();

        View view = View.inflate(mActivity, R.layout.layout_microphone, null);
        mVoicePop = new PopupWindowFactory(mActivity, view);

        //PopupWindow布局文件里面的控件
        final ImageView mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        final TextView mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mPopVoiceText = (TextView) view.findViewById(R.id.tv_recording_text);
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(Utils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(long time, String filePath) {
                mTextView.setText(Utils.long2String(0));
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setFilepath(filePath);
                messageInfo.setVoiceTime(time);
                EventBus.getDefault().post(messageInfo);
            }

            @Override
            public void onError() {
                mVoiceText.setVisibility(View.GONE);
                mEditText.setVisibility(View.VISIBLE);
            }
        });
        return this;
    }

    public boolean interceptBackPress() {
        if (mEmotionLayout.isShown()) {
            hideEmotionLayout(false);
            return true;
        }
        return false;
    }
    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {

        //隐藏虚拟按键，并且全屏
//        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
//            View v = mActivity.getWindow().getDecorView();
//            v.setSystemUiVisibility(View.GONE);
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            //for new api versions.
//            View decorView = mActivity.getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
//            decorView.setSystemUiVisibility(uiOptions);
//        }
    }

    private void showEmotionLayout() {
//        showInputAddr(false);
        int softInputHeight = getSupportSoftInputHeight();
        int daoHangHeight = StatusBarUtil.getDaoHangHeight(mActivity);
        if (softInputHeight <50) {
            softInputHeight = sp.getInt(SHARE_PREFERENCE_TAG, 786);
            if (!mEmotionLayout.isShown())
                softInputHeight-=daoHangHeight;
//            else if (mEmotionLayout.isShown())
//                softInputHeight-=daoHangHeight;
        }
        mEmotionLayout.getLayoutParams().height = softInputHeight;
        mEmotionLayout.setVisibility(View.VISIBLE);
        hideSoftInput();
//        int statusBarHeight = WindowBugDeal.getStatusBarHeight(mActivity);
////        这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
//        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) mEmotionLayout.getLayoutParams();
////        获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0, 0, 0, -daoHangHeight);
////        设置button的新位置属性,left，top，right，bottom
//        mEmotionLayout.setLayoutParams(layout);

//        int uiOptions =View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        mActivity.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
//        mActivity.getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        hideBottomUIMenu();
//        mEmotionLayout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

    }

    public void showInputAddr(boolean isBot) {
        //键盘高度
        int softInputHeight = getSupportSoftInputHeight();
//        导航栏高度
        int daoHangHeight = StatusBarUtil.getDaoHangHeight(mActivity);

        int statusBarHeight = WindowBugDeal.getStatusBarHeight(mActivity);
//        这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
//        获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
            layout.height=softInputHeight+daoHangHeight;
//            layout.setMargins(0, 0,0, (daoHangHeight));
//        设置button的新位置属性,left，top，right，bottom
        mContentView.setLayoutParams(layout);

    }

    public void hideEmotionLayout(boolean showSoftInput) {
        if (mEmotionLayout.isShown()) {
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
        }
    }
    /**
     * 锁定内容高度，防止跳闪
     */
    private void lockContentHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
        params.height = mContentView.getHeight();
        params.weight = 0.0F;
    }

    private void unlockContentHeightDelayed() {
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) mContentView.getLayoutParams()).weight = 1.0F;
            }
        }, 200L);
    }

    private void showSoftInput() {
        mEmotionLayout.setVisibility(View.GONE);
        hideEmotionLayout(false);

        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        mEditText.findFocus();
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);// 显示输入法
//        hideBottomUIMenu();
//        mEditText.requestFocus();
//        mEditText.post(new Runnable() {
//            @Override
//            public void run() {
//                mInputManager.showSoftInput(mEditText, 0);
//            }
//        });


//        if (mActivity != null) {
//            InputMethodManager imm = (InputMethodManager) mActivity
//                    .getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInputFromInputMethod(mActivity.getCurrentFocus()
//                    .getWindowToken(), 0);
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//        showInputAddr(true);
    }

    public void hideSoftInput() {
//        if (mActivity != null) {
//            InputMethodManager imm = (InputMethodManager) mActivity
//                    .getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm.isActive() && mActivity.getCurrentFocus() != null) {
//                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus()
//                        .getWindowToken(), 0);
//            }
//        }
        mEditText.clearFocus();
//        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);

    }

    private boolean isSoftInputShown() {
        return getSupportSoftInputHeight() > 100;
    }
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = mActivity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        Log.e("getSoftButtonsBarHeight","结果="+(screenHeight - rect.bottom - getSoftButtonsBarHeight()));
        return (screenHeight - rect.bottom - getSoftButtonsBarHeight())!= 0;
//        return screenHeight - rect.bottom != 0;
    }

    private int getSupportSoftInputHeight() {
        Rect r = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
        int softInputHeight = screenHeight - r.bottom;
        if (Build.VERSION.SDK_INT >= 20) {
            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
        }
        if (softInputHeight < 0) {
//            Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
        }
        if (softInputHeight > 0) {
            sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
        }
        return softInputHeight;
//        Rect r = new Rect();
//        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//        int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
//        int softInputHeight = screenHeight - r.bottom;
//        if (Build.VERSION.SDK_INT >= 20) {
//            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
//            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
//        }
//        if (softInputHeight < 0) {
////            Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
//        }
//        if (softInputHeight > 0) {
//            sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
//        }
//        return softInputHeight;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

}
