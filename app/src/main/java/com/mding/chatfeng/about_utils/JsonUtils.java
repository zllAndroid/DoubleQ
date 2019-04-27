package com.mding.chatfeng.about_utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/27 0027
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class JsonUtils {
    public static  String toChangeJson( Object recordBean ){
        Log.e("toChangeJson","JSONObject--recordBean-->>="+recordBean.toString());
//        String jsonString = (String) JSONObject.toJSON(recordBean);//将java对象转换为json对象
//        String jsonString = JSON.toJSONString(recordBean);
        Gson gs = new Gson();
        String objectStr = gs.toJson(recordBean);//把对象转为JSON格式的字符串
        Log.e("toChangeJson","JSONObject--objectStr-->>="+objectStr);
        return objectStr;
    }
}
