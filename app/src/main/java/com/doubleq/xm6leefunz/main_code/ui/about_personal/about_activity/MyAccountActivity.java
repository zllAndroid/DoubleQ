package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataMyZiliao;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.ZXingUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.PersonData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.Serializable;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 *  我的二维码
 */
public class MyAccountActivity extends BaseActivity {
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
    public  static final String TITAL_NAME = "tital_name";
    public  static  String USER_NAME = "user_name";
    public  static  String QRCODE = "code";
    String type = "1";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent!=null)
        {
            PersonData personData = (PersonData)intent.getSerializableExtra(TITAL_NAME);
//            String userId = intent.getStringExtra("userId");
            if (personData!=null)
            {
                qrcodeTvSao.setText(personData.getScanTital());
                qrcodeTvName.setText(personData.getName());
                Glide.with(this).load(personData.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
                        .error(R.drawable.mine_head)
                      .into(qrcodeIvHead);

                String string = personData.getQrCode();
//                String string = type + "_xm6leefun_" + userId;
                Log.e("qrcode","----------string000--------------"+string);
                Bitmap bitmap = ZXingUtils.createQRImage(string,300,300);
                Drawable drawable = new BitmapDrawable(bitmap);
                Log.e("qrcode","-------record.getQrcode()000---------"+drawable);
                qrcodeIvQrcode.setBackground(drawable);
                includeTopTvTital.setText(personData.getTital());
                return;
            }
        }
        includeTopTvTital.setText("我的二维码");
        sendWeb(SplitWeb.personalCenter());
    }
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        if (HelpUtils.backMethod(responseText).equals("personalCenter")) {
            DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
            DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
            if (record != null) {
                qrcodeTvName.setText(record.getNickName());
                Glide.with(this).load(record.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
                       .into(qrcodeIvHead);

//                String string = type + "_xm6leefun_" + SplitWeb.getUserId();
                Log.e("qrcode","----------string_myAccount--------------"+record.getQrcode());
                Bitmap bitmap = ZXingUtils.createQRImage(record.getQrcode(),300,300);
                Drawable drawable = new BitmapDrawable(bitmap);
                Log.e("qrcode","-------record.getQrcode()_myAccount---------"+drawable);
                qrcodeIvQrcode.setBackground(drawable);

                Log.e("qrcode","-------myAccount---------"+record.getQrcode());
            }
        }
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_mine_ziliao;
    }

}
