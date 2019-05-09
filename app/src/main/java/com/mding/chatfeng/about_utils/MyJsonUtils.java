package com.mding.chatfeng.about_utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.model.DataServer;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/27 0027
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class MyJsonUtils {
    public static  String toChangeJson( Object recordBean ){
//        Log.e("toChangeJson","JSONObject--recordBean-->>="+recordBean.toString());
//        String jsonString = (String) JSONObject.toJSON(recordBean);//将java对象转换为json对象
//        String jsonString = JSON.toJSONString(recordBean);
        String objectStr = null;//把对象转为JSON格式的字符串
        try {
            Gson gs = new Gson();
            objectStr = gs.toJson(recordBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.e("toChangeJson","JSONObject--objectStr-->>="+objectStr);
        return objectStr;
    }
    public static  void setJson( String key, String value ){
        BaseApplication.getaCache().put(key, value);

    }
    public static String getJson(String key){
        String value = BaseApplication.getaCache().getAsString(key);
       return value;
    }

    public static  void initBeforeLogin(final Context mContext) {
        if (StrUtils.isEmpty(SplitWeb.getSplitWeb().HttpURL)) {
//            Activity activity = AppManager.getAppManager().currentActivity();
            VolleyRequest.RequestGet(mContext,SplitWeb.getSplitWeb().PreRequest, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
                @Override
                public void onSuccess(final String result) {
                    try {
                        final String sucess = HelpUtils.HttpIsSucess(result);
                        if (sucess.equals(AppConfig.CODE_OK))
                        {
                            DataServer dataServer = JSON.parseObject(result, DataServer.class);
                            DataServer.RecordBean record = dataServer.getRecord();
                            if (record != null) {
                                try {
                                    //                            TODO 固定ws
                                    String serverIpHttp = record.getHttpProtocolIp();
//                                    String serverIpHttp = "http://192.168.4.133:5052";
                                    String serverIpWs = record.getWsProtocolIp();
                                    SplitWeb.getSplitWeb().HttpURL = serverIpHttp;
                                    BaseApplication.getaCache().remove(AppConfig.TYPE_URL);
                                    BaseApplication.getaCache().put(AppConfig.TYPE_URL, serverIpHttp);
                                    SplitWeb.getSplitWeb().WS_REQUEST = serverIpWs;
                                    SPUtils.put(mContext,AppConfig.TYPE_WS_REQUEST,serverIpWs);
                                    SPUtils.put(mContext,AppConfig.TYPE_URL,serverIpHttp);
//                                    BaseApplication.getaCache().remove(AppConfig.TYPE_WS_REQUEST);
                                    BaseApplication.getaCache().put(AppConfig.TYPE_WS_REQUEST, serverIpWs);
                                    String asString = BaseApplication.getaCache().getAsString(AppConfig.TYPE_WS_REQUEST);
                                    Log.e("mACache","----------initBeforeLogin------------------>>>"+asString);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onError(VolleyError result) {
                    try {
                        ToastUtil.show(AppConfig.ERROR);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                Tip.getError(CommonParameter.ERROR);
                }
            });
        }
    }

}
