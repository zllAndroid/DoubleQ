package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataMyZiliao;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.PersonData;

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
    public  static  String TITAL_NAME = "tital_name";
    public  static  String USER_NAME = "user_name";
    public  static  String QRCODE = "code";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent!=null)
        {
            PersonData personData = (PersonData)intent.getSerializableExtra(TITAL_NAME);
            if (personData!=null)
            {
                qrcodeTvSao.setText(personData.getScanTital());
                qrcodeTvName.setText(personData.getName());
                Glide.with(this).load(personData.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
                        .error(R.drawable.mine_head)
                        .crossFade(1000).into(qrcodeIvHead);
                Glide.with(this).load(personData.getQrCode())
//                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
                        .crossFade(1000).into(qrcodeIvQrcode);
//                Glide.with(this).load(personData.getQrCode())
//                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
//                        .error(R.drawable.ziliao_ercode)
//                        .crossFade(1000).into(qrcodeIvQrcode);
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
                        .crossFade(1000).into(qrcodeIvHead);
                Glide.with(this).load(record.getQrcode())
//                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
                        .crossFade(1000).into(qrcodeIvQrcode);
//                Glide.with(this).load(record.getQrcode())
////                        .bitmapTransform(new CropCircleTransformation(MyAccountActivity.this))
//                        .crossFade(1000).into(qrcodeIvQrcode);
            }
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_mine_ziliao;
    }

}
