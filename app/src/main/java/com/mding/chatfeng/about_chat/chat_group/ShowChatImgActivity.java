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
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowChatImgActivity extends BaseActivity {


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

    @OnClick({R.id.showChatImg_iv_full, R.id.showChatImg_lin_full})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showChatImg_iv_full:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity(ShowChatImgActivity.this);
                        overridePendingTransition(0, 0);
                    }
                });
                break;
            case R.id.showChatImg_lin_full:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity(ShowChatImgActivity.this);
                        overridePendingTransition(0, 0);
                    }
                });
                break;
        }
    }


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
                AppManager.getAppManager().finishActivity(ShowChatImgActivity.this);
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
