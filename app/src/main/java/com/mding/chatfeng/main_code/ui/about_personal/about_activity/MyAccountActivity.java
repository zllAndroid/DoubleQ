package com.mding.chatfeng.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.model.DataLogin;
import com.mding.model.DataMyZiliao;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ZXingUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.PersonData;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import butterknife.BindView;

import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

/**
 *  我的二维码
 *
 *   不需要网络请求，纯数据展示（1、传值显示  2、缓存显示）
 */
public class MyAccountActivity extends BaseActivity  {
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
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    public  static final String TITAL_NAME = "tital_name";

    ACache aCache;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
        Intent intent = getIntent();
        if (intent!=null)
        {
//            接收传值过来显示
            PersonData personData = (PersonData)intent.getSerializableExtra(TITAL_NAME);
            if (personData!=null)
            {
                qrcodeTvSao.setText(personData.getScanTital());
                qrcodeTvName.setText(personData.getName());
                String headImg = personData.getHeadImg();
                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
                String string = personData.getQrCode();
                createQrCodeImg(string);
                includeTopTvTital.setText(personData.getTital());
                return;
            }
        }
//        显示自个人资料的缓存数据
        aCache = ACache.get(this);
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json))
        {
            DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
            if (recordBean!=null) {
                String headImg = recordBean.getHeadImg();
                String qrCodeString = recordBean.getQrcode();
                createQrCodeImg(qrCodeString);
                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
                String nickName = recordBean.getNickName();
                qrcodeTvName.setText(nickName);
            }
        }
        includeTopTvTital.setText("我的二维码");
    }

//    不需要网络请求，纯数据展示（1、传值显示  2、缓存显示）
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        if (HelpUtils.backMethod(responseText).equals("personalCenter")) {
//            DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
//            DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
//            if (record != null) {
//                String s = MyJsonUtils.toChangeJson(record);
//                aCache.put(AppAllKey.PPERSON_iNFO,s);
//                qrcodeTvName.setText(record.getNickName());
//                String headImg = record.getHeadImg();
//                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
//                createQrCodeImg(record.getQrcode());
//            }
//        }
//    }
    private void createQrCodeImg(String qrcode) {
        Bitmap bitmap = ZXingUtils.createQRImage(qrcode, 300, 300);
        Drawable drawable = new BitmapDrawable(bitmap);
        qrcodeIvQrcode.setBackground(drawable);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_mine_ziliao;
    }


}
