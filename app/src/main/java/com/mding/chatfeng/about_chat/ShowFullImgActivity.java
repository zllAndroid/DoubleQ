package com.mding.chatfeng.about_chat;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_chat.fragment.ShowImgActivity;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.mains.PersonalFragment;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowFullImgActivity extends BaseActivity {
    @BindView(R.id.full_image)
    ImageView fullImage;
    @BindView(R.id.full_lay)
    LinearLayout fullLay;
    private Drawable mBackground;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        mBackground = new ColorDrawable(getResources().getColor(R.color.black_trans));
        Intent intent = getIntent();
        if (intent != null) {
            String image = intent.getStringExtra(PersonalFragment.IMAGE_BASE64);
            Log.e("image",""+image);
            ImageUtils.useBase64Origin(ShowFullImgActivity.this, fullImage, image);
        }


    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_show_full_img;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public boolean isGonesStatus() {
        return true;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @OnClick({R.id.full_image, R.id.full_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.full_image:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity();
                        overridePendingTransition(0, 0);
                    }
                });
                break;
            case R.id.full_lay:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity();
                        overridePendingTransition(0, 0);
                    }
                });
                break;
        }
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
}
