package com.mding.chatfeng.main_code.about_login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.mding.model.DataLogin;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;

import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/29 0029
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class AboutLoginSaveData {

    AppCompatActivity mContext;
    ACache mCache;
   public static  final String QR_CODE = "qrCode";
    public AboutLoginSaveData(AppCompatActivity mContext) {
        this.mContext = mContext;
        this.mCache = BaseApplication.getaCache();
    }

    public  void initSaveData(String data)
    {
       DataLogin dataLogin = JSON.parseObject(data, DataLogin.class);
        DataLogin.RecordBean record = dataLogin.getRecord();
        if (record != null)
        {
            mCache.clear();
            mCache.put(AppAllKey.TOKEN_KEY, data);
            mCache.put(IMAGE_BASE64, record.getHeadImg());
            mCache.put(QR_CODE, record.getQrcode());
            initSetData(record);
        }

    }

    private void initSetData(DataLogin.RecordBean dataLogin)
    {
        SPUtils.put(mContext, AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        SPUtils.put(mContext,AppAllKey.USER_Token,dataLogin.getUserToken());

        SPUtils.put(mContext, AppConfig.TYPE_NAME,dataLogin.getNickName());
        SPUtils.put(mContext,AppConfig.TYPE_NO,dataLogin.getWxSno());
        SPUtils.put(mContext,AppConfig.TYPE_PHONE,dataLogin.getWxSno());
        SPUtils.put(mContext, AppConfig.User_HEAD_URL,dataLogin.getHeadImg());
        SPUtils.put(mContext,AppConfig.TYPE_SIGN,dataLogin.getPersonaSignature());

        SplitWeb.getSplitWeb().USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.getSplitWeb().MOBILE = dataLogin.getMobile();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().NICK_NAME = dataLogin.getNickName();
        SplitWeb.getSplitWeb().PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().WX_SNO = dataLogin.getWxSno();
        SplitWeb.getSplitWeb().USER_ID = dataLogin.getUserId();
        SplitWeb.getSplitWeb().USER_HEADER = dataLogin.getHeadImg();
        mCache.put(AppAllKey.USER_ID_KEY,dataLogin.getUserId());

        if (dataLogin.getIsFirstLogin().equals("1")) {
            IntentUtils.JumpFinishTo(mContext,FirstAddHeaderActivity.class);
        }
        else
            IntentUtils.JumpFinishTo(mContext,LoadLinkManActivity.class);
    }
}
