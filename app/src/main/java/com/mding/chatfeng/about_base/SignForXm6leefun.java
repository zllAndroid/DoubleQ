package com.mding.chatfeng.about_base;


import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.MD5Utils;
import com.mding.chatfeng.about_utils.StringUnicode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by  on 2017/11/6 0006.
 */

public class SignForXm6leefun {
    static String key = "f15e867d82baf4e3f591f20bdbe55a6e";// 秘钥
    static String api_key = "20171106";// api秘钥
    static String device = "android";// 机型
    static String versionName = HelpUtils.getLocalVersionName();
    // SystemTool.getDataTime()

    public static String getSing(ArrayList<String> mList) {
        // 时间戳
        long time = System.currentTimeMillis() / 1000;
        // time=1500439026;
        // 头部URL
        String headTemp = "";
        // 签名字段Sign
        String nameTemp = "";
        Collections.sort(mList);
        try {
            for (int i = 0; i < mList.size(); i++) {
                headTemp = headTemp + "&" + ("data[" + enUrlCode(mList.get(i).split("=")[0]) + "]" + "="
                        + enUrlCode(mList.get(i).split("=")[1]));
                String temp = mList.get(i).split("=")[0] + mList.get(i).split("=")[1];
                nameTemp = nameTemp + temp;
            }
        } catch (Exception e) {
        }
        if (SplitWeb.USER_TOKEN.equals("")) {
            return StringUnicode.decode(headTemp) + "&sign="
                    + MD5Utils.encryptMD5(MD5Utils.encryptMD5(StringUnicode.decode(nameTemp) + time) + key).toUpperCase()
                    + "&timestamp=" + time + "&api_key=" + api_key+ "&device=" + device+ "&v=" + versionName;
        } else {
            return StringUnicode.decode(headTemp) + "&sign="
                    + MD5Utils.encryptMD5(MD5Utils.encryptMD5(StringUnicode.decode(nameTemp) + time) + key).toUpperCase()
                    + "&timestamp=" + time + "&api_key=" + api_key + "&user_token=" + SplitWeb.USER_TOKEN+ "&device=" + device+ "&v=" + versionName;
        }
    }

    public static String enUrlCode(String text) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(text, "utf-8");
    }
}
