package com.mding.chatfeng.main_code.ui.about_contacts.about_top_add;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.model.DataAddQunDetails;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ZXingUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.PersonData;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 二维码
 */
public class QunCodeActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.qrcode_iv_head)
    ImageView qrcodeIvHead;
    @BindView(R.id.qrcode_tv_name)
    TextView qrcodeTvName;
    @BindView(R.id.qrcode_tv_sao)
    TextView qrcodeTvSao;
    @BindView(R.id.qrcode_iv_qrcode)
    ImageView qrcodeIvQrcode;
    public static String TITAL_NAME = "tital_name";
    public static String USER_NAME = "user_name";
    public static String QRCODE = "code";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String groupSno = null;
    String type = "2";
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊二维码");
        Intent intent = getIntent();
        if (intent != null){
//            groupId = intent.getStringExtra("groupId");
//            sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId));
//            groupSno = intent.getStringExtra(AppConfig.GROUP_SNO);
            PersonData personData = (PersonData)intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
            PersonData groupInfo = (PersonData)intent.getSerializableExtra(AppConfig.GROUP_INFO);
//            String userId = intent.getStringExtra("userId");
            if (personData!=null) {
                qrcodeTvSao.setText(personData.getScanTital());
                qrcodeTvName.setText(personData.getName());
                String headImg = personData.getHeadImg();
                ImageUtils.useBase64WithError(this, qrcodeIvHead, headImg, R.drawable.qun_head);
//                Glide.with(this).load(headImg.substring(0, headImg.indexOf("_")))
////                Glide.with(this).load(personData.getHeadImg())
//                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
//                        .error(R.drawable.qun_head)
//                        .into(qrcodeIvHead);

                String string = personData.getQrCode();
//                String string = type + "_xm6leefun_" + userId;
                Log.e("qrcode","----------string000--------------"+string);
                Bitmap bitmap = ZXingUtils.createQRImage(string,300,300);
                Drawable drawable = new BitmapDrawable(bitmap);
                Log.e("qrcode","-------record.getQrcode()000---------"+drawable);
                qrcodeIvQrcode.setBackground(drawable);
                includeTopTvTital.setText(personData.getTital());
            }
            else if (groupInfo != null){
                qrcodeTvSao.setText(groupInfo.getScanTital());
                qrcodeTvName.setText(groupInfo.getName());
                Glide.with(this).load(groupInfo.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
                        .error(R.drawable.first_head_nor)
                        .into(qrcodeIvHead);

                String string = groupInfo.getQrCode();
//                String string = type + "_xm6leefun_" + userId;
                Log.e("qrcode","----------string000--------------"+string);
                Bitmap bitmap = ZXingUtils.createQRImage(string,300,300);
                Drawable drawable = new BitmapDrawable(bitmap);
                Log.e("qrcode","-------record.getQrcode()000---------"+drawable);
                qrcodeIvQrcode.setBackground(drawable);
                includeTopTvTital.setText(groupInfo.getTital());
            }
        }
    }
    String urlCode ="";
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        if (HelpUtils.backMethod(responseText).equals("searchDetailInfo")) {
            DataAddQunDetails dataAddQunDetails = JSON.parseObject(responseText, DataAddQunDetails.class);

            if (dataAddQunDetails.getRecord().getGroupDetailInfo().getGroupInfo() != null) {
                qrcodeTvName.setText(dataAddQunDetails.getRecord().getGroupDetailInfo().getGroupInfo().getGroupName());
                Glide.with(this).load(dataAddQunDetails.getRecord().getGroupDetailInfo().getGroupInfo().getGroupHeadImg())
                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
                        .into(qrcodeIvHead);
                urlCode= dataAddQunDetails.getRecord().getGroupDetailInfo().getGroupInfo().getGroupQrcode();
//                String string = personData.getQrCode();
//                String string = type + "_xm6leefun_" + userId;
                Log.e("qrcode","----------urlCode--------------"+urlCode);
                Bitmap bitmap = ZXingUtils.createQRImage(urlCode,300,300);
                Drawable drawable = new BitmapDrawable(bitmap);
                qrcodeIvQrcode.setBackground(drawable);


//                Glide.with(this).load(dataAddQunDetails.getRecord().getGroupDetailInfo().getGroupInfo().getGroupQrcode())
////                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
//                        .into(qrcodeIvQrcode);
            }
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_qun_code;
    }

    @OnClick({R.id.quncode_tv_save, R.id.quncode_tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quncode_tv_save:
//                保存本地图片
                if (!StrUtils.isEmpty(urlCode))
                {
                    new Thread(runnable).start();
                }
                DialogUtils.showDialog(getResources().getString(R.string.coming_soon));
                break;
            case R.id.quncode_tv_share:
                DialogUtils.showDialog(getResources().getString(R.string.coming_soon));
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            ToastUtil.show(val);
        }
    };
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            Message msg = new Message();
            Bundle data = new Bundle();

            Bitmap bitmap = decodeUriAsBitmapFromNet(urlCode);
            boolean isSuc=  saveBitmaps( bitmap);
//            File saveBitmap = saveBitmaps( bitmap);
            if (isSuc)
            {
                data.putString("value","图片保存成功");
            }else
            {
                data.putString("value","图片保存失败");
            }
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
    public boolean saveBitmaps(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),"zxing_image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "zxing_image" + ".png";
        File  file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  false;
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/namecard/")));
        return  true;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap decodeUriAsBitmapFromNet(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
