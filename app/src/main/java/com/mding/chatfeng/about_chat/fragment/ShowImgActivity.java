package com.mding.chatfeng.about_chat.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.Methon;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.model.DataGroupChatResult;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mding.chatfeng.about_custom.about_img_clip.ClipViewLayout.decodeSampledBitmap;
import static com.mding.chatfeng.about_custom.about_img_clip.ClipViewLayout.getExifOrientation;
import static com.mding.chatfeng.about_utils.about_file.HeadFileUtils.getRealFilePathFromUri;

public class ShowImgActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    private ImageView clipViewLayout;
    //    private ClipViewLayout clipViewLayout;
    private TextView btnCancel;
    private TextView btnOk;

    String messageTypeImg = "2";
    public static String imgTotal;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_clip_image);
//        type = getIntent().getIntExtra("type", 1);
//        initView();
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_show_img;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("大图预览");
        initView();
        clipViewLayout.setImageBitmap(bitmap);
    }


    /**
     * 初始化组件
     */
    public void initView() {
        clipViewLayout = findViewById(R.id.showImg_iv_full);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.bt_ok);
        //设置点击事件监听器
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        //设置图片资源
        data = getIntent().getData();
//        clipViewLayout.setImageURI(getIntent().getData());
        bitmap = setImageSrc(data);
    }

    Uri data;

    @Override
    protected void onResume() {
        super.onResume();
//        //设置图片资源
//        data = getIntent().getData();
////        clipViewLayout.setImageURI(getIntent().getData());
//        bitmap = setImageSrc(data);

    }

    Bitmap bitmap;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:
                generateUriAndReturn();
                break;
        }
    }

    /**
     * 初始化图片
     */
    public Bitmap setImageSrc(final Uri uri) {
        //需要等到imageView绘制完毕再初始化原图
        ViewTreeObserver observer = clipViewLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
//                bitmap = initSrcPic(uri);
                clipViewLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        return initSrcPic(uri);
    }

    private Bitmap initSrcPic(Uri uri) {
        if (uri == null) {
            return null;
        }
        String path = getRealFilePathFromUri(this, uri);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        //这里decode出720*1280 左右的照片,防止OOM
        Bitmap bitmap = decodeSampledBitmap(path, 720, 1280);
        if (bitmap == null) {
            return null;
        }
        //竖屏拍照的照片，直接使用的话，会旋转90度，下面代码把角度旋转过来
        int rotation = getExifOrientation(path); //查询旋转角度
        Matrix m = new Matrix();
        m.setRotate(rotation);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);

        return bitmap;
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
////        调用返回剪切图
//        Drawable drawable = clipViewLayout.getDrawable();
//        Bitmap zoomedCropBitmap = ImageUtils.drawableToBitmap(drawable);

//        Bitmap zoomedCropBitmap = clipViewLayout.clip();
        if (bitmap == null) {
            Log.e("android", "bitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
//            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), mSaveUri);
//            // 原图
//            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//            String imgNoZoom = ImageUtils.Bitmap2StrByBase64(bitMap);
//            // 压缩图
//            Bitmap bm = ImageUtils.imageZoom(bitMap);
//            String imgByZoom = ImageUtils.Bitmap2StrByBase64(bm);
//            // TODO 发送图片（格式：压缩图片的base64_高清原图的base64）
//            imgTotal = imgByZoom + "_" + imgNoZoom;
//            String is_chat = SplitWeb.getSplitWeb().IS_CHAT;
//            String is_chat_group = SplitWeb.getSplitWeb().IS_CHAT_GROUP;
//            if (is_chat.equals("1")){
//                send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, imgTotal, messageTypeImg, TimeUtil.getTime()));
//            }
//            else if (is_chat_group.equals("2")){
//                send(SplitWeb.getSplitWeb().groupSend(ChatGroupActivity.groupId, imgTotal, messageTypeImg, TimeUtil.getTime()));
//            }
//            finish();
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case Methon.PrivateSend:
                DataJieShou dataJieShou = JSON.parseObject(responseText, DataJieShou.class);
                DataJieShou.RecordBean recordBean = dataJieShou.getRecord();
                ToastUtil.isDebugShow("发送图片成功");
                break;
            case Methon.GroupChatSend:
                DataGroupChatResult dataGroupChatResult = JSON.parseObject(responseText, DataGroupChatResult.class);
                DataGroupChatResult.RecordBean recordBean1 = dataGroupChatResult.getRecord();
                ToastUtil.isDebugShow("发送群聊图片成功");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
