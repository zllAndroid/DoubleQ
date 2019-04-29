package com.mding.chatfeng.about_chat.chat_group;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_utils.about_file.HeadFileUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目：DoubleQ
 * 文件描述：显示聊天图片大图
 * 作者：ljj
 */
public class ShowChatImgActivity extends BaseActivity {


    @BindView(R.id.showChatImg_iv_full)
    ImageView showChatImgIvFull;
    @BindView(R.id.showImg_tv_decoding)
    TextView showImgTvDecoding;
    @BindView(R.id.showChatImg_iv_bac)
    ImageView showChatImgIvBac;
    @BindView(R.id.showChatImg_iv_download)
    ImageView showChatImgIvDownload;

    public static String SHOW_CHAT_IMG_REGION = "show_chat_img_http";
    String imgRegion;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_show_chat_img;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        //设置图片旋转
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_img);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        animation.setInterpolator(linearInterpolator);
        showChatImgIvBac.startAnimation(animation);

        Intent intent = getIntent();
        if (intent != null) {
            imgRegion = intent.getStringExtra(SHOW_CHAT_IMG_REGION);
        }
        if (imgRegion != null) {
            Glide.with(ShowChatImgActivity.this).load(imgRegion)
                    .dontAnimate()
                    .placeholder(showChatImgIvFull.getDrawable())
                    .into(showChatImgIvFull);
            MyLog.e("ChatSendViewHolder", "----------------------showChatImg-----------------------" + imgRegion);
        }
        showChatImgIvDownload.setVisibility(View.VISIBLE);
//        showChatImgIvFull.setVisibility(View.VISIBLE);
//        showImgTvDecoding.setVisibility(View.GONE);
    }

    @OnClick({R.id.showChatImg_iv_full, R.id.showChatImg_rel_full, R.id.showChatImg_iv_download})
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
            case R.id.showChatImg_rel_full:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity(ShowChatImgActivity.this);
                        overridePendingTransition(0, 0);
                    }
                });
                break;
            case R.id.showChatImg_iv_download:
                SaveBitmapFromView(showChatImgIvFull);
                break;
        }
    }

//    保存文件的方法
    public void SaveBitmapFromView(View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        view.layout(0, 0, w, h);
        view.draw(c);
        // 缩小图片
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f,0.5f); //长和宽放大缩小的比例
        bmp = Bitmap.createBitmap(bmp,0,0, bmp.getWidth(),bmp.getHeight(),matrix,true);
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        saveBitmap(bmp,format.format(new Date())+".JPEG");
    }

    // 下载图片到本地相册
//    private void downLoadToAlbum() {
//        Glide.with(this)
//                .load(imgRegion)
//                .downloadOnly(new SimpleTarget<File>() {
//                    @Override
//                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
////                        这里拿到的resource就是下载好的文件
////                        File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_FRIEND, friendId, modified);
//                        String fileName ;
//                        File file ;
//                        if(Build.BRAND .equals("Xiaomi") ){ // 小米手机
//                            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+bitName ;
//                        }else{  // Meizu 、Oppo
//                            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/"+bitName ;
//                        }
//                        file = new File(fileName);
//
//                        if(file.exists()){
//                            file.delete();
//                        }
//                        FileOutputStream out;
//                        try{
//                            out = new FileOutputStream(file);
//                            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
//                            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out))
//                            {
//                                out.flush();
//                                out.close();
//// 插入图库
//                                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), bitName, null);
//
//                            }
//                        }
//                        catch (FileNotFoundException e)
//                        {
//                            e.printStackTrace();
//                        }
//                        catch (IOException e)
//                        {
//                            e.printStackTrace();
//
//                        }
//                    }
//                });
//        saveImageToGallery(ShowChatImgActivity.this, bitmap);
//    }

    /*
     * 保存文件，文件名为当前日期
     */
    public void saveBitmap(Bitmap bitmap, String bitName){
        String fileName ;
        File file ;
        if(Build.BRAND .equals("Xiaomi") ){ // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+bitName ;
        }else{  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/"+bitName ;
        }
        file = new File(fileName);

        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out))
            {
                out.flush();
                out.close();
//               插入图库
                MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), bitName, null);
                ToastUtil.isDebugShow("图片保存在："+ file.getAbsolutePath());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        // 发送广播，通知刷新图库的显示
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
        ToastUtil.isDebugShow("保存成功");
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
