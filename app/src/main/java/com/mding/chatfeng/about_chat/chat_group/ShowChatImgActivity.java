package com.mding.chatfeng.about_chat.chat_group;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowChatImgActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_chat_img);
//    }

    @BindView(R.id.showChatImg_iv_full)
    ImageView showChatImgIvFull;
    @BindView(R.id.showImg_tv_decoding)
    TextView showImgTvDecoding;

    public static String SHOW_CHAT_IMG_REGION = "show_chat_img_http";
    String imgRegion;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_show_chat_img;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        Intent intent = getIntent();
        if (intent != null) {
            imgRegion = intent.getStringExtra(SHOW_CHAT_IMG_REGION);
        }
        if (imgRegion != null){
            Glide.with(ShowChatImgActivity.this).load(imgRegion)
                    .dontAnimate()
                    .placeholder(showChatImgIvFull.getDrawable())
                    .into(showChatImgIvFull);
            MyLog.e("ChatSendViewHolder","----------------------showChatImg-----------------------"+imgRegion);
        }
        showChatImgIvFull.setVisibility(View.VISIBLE);
        showImgTvDecoding.setVisibility(View.GONE);
    }

//    private void initDataImage() {
//        final int left = fullImageInfo.getLocationX();
//        final int top = fullImageInfo.getLocationY();
//        final int width = fullImageInfo.getWidth();
//        final int height = fullImageInfo.getHeight();
//        showChatImgIvFull.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                showChatImgIvFull.getViewTreeObserver().removeOnPreDrawListener(this);
//                int location[] = new int[2];
//                showChatImgIvFull.getLocationOnScreen(location);
//                mLeft = left - location[0];
//                mTop = top - location[1];
//                mScaleX = width * 1.0f / showChatImgIvFull.getWidth();
//                mScaleY = height * 1.0f / showChatImgIvFull.getHeight();
//                activityEnterAnim();
//                return true;
//            }
//        });
////        Glide.with(this).load(showChatImgIvFullInfo.getImageUrl()).into(showChatImgIvFull);
////        ImageUtils.useBase64(ShowChatImgActivity.this, showChatImgIvFull, imgHttp);
////        ImageUtils.useBase64Origin(FullImageActivity.this, fullImage, fullImageInfo.getImageBase64());
//    }

    @OnClick({R.id.showChatImg_iv_full, R.id.showChatImg_lin_full})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showChatImg_iv_full:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                });
                break;
            case R.id.showChatImg_lin_full:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                });
                break;
        }
    }

//    private void activityEnterAnim() {
//        showChatImgIvFull.setPivotX(0);
//        showChatImgIvFull.setPivotY(0);
////        if (imgWidth != 0 && imgHeight != 0) {
////            showChatImgIvFull.setScaleX(imgWidth);
////            showChatImgIvFull.setScaleY(imgHeight);
////        } else {
//        showChatImgIvFull.setScaleX(mScaleX);
//        showChatImgIvFull.setScaleY(mScaleY);
////        }
//
//        showChatImgIvFull.setTranslationX(mLeft);
//        showChatImgIvFull.setTranslationY(mTop);
//        showChatImgIvFull.animate().scaleX(1).scaleY(1).translationX(0).translationY(0).
//                setDuration(200).setInterpolator(new DecelerateInterpolator()).start();
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(new ColorDrawable(Color.BLACK), "alpha", 0, 180);
//        objectAnimator.setInterpolator(new DecelerateInterpolator());
//        objectAnimator.setDuration(500);
//        objectAnimator.start();
//    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void activityExitAnim(Runnable runnable) {
        showChatImgIvFull.setPivotX(0);
        showChatImgIvFull.setPivotY(0);
        showChatImgIvFull.animate().scaleX(1).scaleY(1).translationX(0).translationY(0).
                withEndAction(runnable).
                setDuration(100).setInterpolator(new DecelerateInterpolator()).start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(new ColorDrawable(Color.BLACK), "alpha", 180, 180);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }

    @Override
    public void onBackPressed() {
        activityExitAnim(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
