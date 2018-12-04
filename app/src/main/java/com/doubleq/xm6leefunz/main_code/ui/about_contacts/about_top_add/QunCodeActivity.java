package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.doubleq.model.DataMyZiliao;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.ImageUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
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
import java.sql.Struct;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊二维码");
        sendWeb(SplitWeb.personalCenter());
    }
    String urlCode ="";
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        if (HelpUtils.backMethod(responseText).equals("personalCenter")) {
            DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
            DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
            if (record != null) {
                qrcodeTvName.setText(record.getNickName());
                Glide.with(this).load(record.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
                        .crossFade(1000).into(qrcodeIvHead);
                urlCode= record.getQrcode();
                Glide.with(this).load(record.getQrcode())
//                        .bitmapTransform(new CropCircleTransformation(QunCodeActivity.this))
                        .crossFade(1000).into(qrcodeIvQrcode);
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
                break;
            case R.id.quncode_tv_share:
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
