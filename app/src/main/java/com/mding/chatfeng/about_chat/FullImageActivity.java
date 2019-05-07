package com.mding.chatfeng.about_chat;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.mains.PersonalFragment;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.FullImageInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用：聊天界面大图查看
 */
public class FullImageActivity extends AppCompatActivity {

    @BindView(R.id.full_image)
    ImageView fullImage;
    @BindView(R.id.full_lay)
    LinearLayout fullLay;
    private int mLeft;
    private int mTop;
    private float mScaleX;
    private float mScaleY;
    private Drawable mBackground;

    float imgWidth;
    float imgHeight;
    String totalImg;
    String totalImageBase64;
    ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_full_image);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            imgWidth = intent.getFloatExtra("imgWidth", 0);
            imgHeight = intent.getFloatExtra("imgHeight", 0);
        }
        if (aCache == null){
            aCache =  ACache.get(this);
        }
//        totalImg = aCache.getAsString(AppAllKey.User_HEAD_URL);
        totalImg = aCache.getAsString(PersonalFragment.IMAGE_BASE64);
        mBackground = new ColorDrawable(getResources().getColor(R.color.greybf_trans));
        fullLay.setBackground(mBackground);
        ImageUtils.useBase64Origin(FullImageActivity.this, fullImage, totalImg);
        EventBus.getDefault().register(this);
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final FullImageInfo fullImageInfo) {
        final int left = fullImageInfo.getLocationX();
        final int top = fullImageInfo.getLocationY();
        final int width = fullImageInfo.getWidth();
        final int height = fullImageInfo.getHeight();
        mBackground = new ColorDrawable(Color.BLACK);
        fullLay.setBackground(mBackground);
        fullImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fullImage.getViewTreeObserver().removeOnPreDrawListener(this);
                int location[] = new int[2];
                fullImage.getLocationOnScreen(location);
                mLeft = left - location[0];
                mTop = top - location[1];
                mScaleX = width * 1.0f / fullImage.getWidth();
                mScaleY = height * 1.0f / fullImage.getHeight();
                activityEnterAnim();
                return true;
            }
        });
//        Glide.with(this).load(fullImageInfo.getImageUrl()).into(fullImage);
//        String totalImage = "abcdefg_123456";
        String totalImage = fullImageInfo.getTotalImage();
        String s = totalImage.contains("_")?"yes" : "no";
        MyLog.i("imageBase64","------------fullImageActivity--------------"+s);
//        MyLog.i("imageBase64","----------------------------------------------" + totalImage.substring(0, totalImage.indexOf("_")));
        ImageUtils.useBase64Origin(FullImageActivity.this, fullImage, totalImage);
//        ImageUtils.useBase64(FullImageActivity.this, fullImage, totalImage.substring(0, totalImage.indexOf("_")));
//        ImageUtils.useBase64Origin(FullImageActivity.this, fullImage, fullImageInfo.getImageBase64());
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    // TODO 异步加载高清图片



    private void activityEnterAnim() {
        fullImage.setPivotX(0);
        fullImage.setPivotY(0);
        if (imgWidth != 0 && imgHeight != 0) {
            fullImage.setScaleX(imgWidth);
            fullImage.setScaleY(imgHeight);
        } else {
            fullImage.setScaleX(mScaleX);
            fullImage.setScaleY(mScaleY);
        }

        fullImage.setTranslationX(mLeft);
        fullImage.setTranslationY(mTop);
        fullImage.animate().scaleX(1).scaleY(1).translationX(0).translationY(0).
                setDuration(200).setInterpolator(new DecelerateInterpolator()).start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBackground, "alpha", 0, 180);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void activityExitAnim(Runnable runnable) {
        fullImage.setPivotX(0);
        fullImage.setPivotY(0);
        fullImage.animate().scaleX(1).scaleY(1).translationX(0).translationY(0).
                withEndAction(runnable).
                setDuration(100).setInterpolator(new DecelerateInterpolator()).start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBackground, "alpha", 180, 180);
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

    @OnClick(R.id.full_image)
    public void onClick() {
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

    @OnClick(R.id.full_lay)
    public void onViewClicked() {
        activityExitAnim(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }
}
