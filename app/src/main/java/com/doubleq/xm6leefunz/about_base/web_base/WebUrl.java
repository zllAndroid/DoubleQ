package com.doubleq.xm6leefunz.about_base.web_base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doubleq.xm6leefunz.about_utils.MD5Utils;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
public class WebUrl {
    static  String  key = "0a62aa3d661cc934473b4bff633ec047";//固定值
    static String api_key ="20180903";
    public static String request(String ctnData, String mtnData,TreeMap<String, String> treemap ){
        JSONObject params = new JSONObject();
//        JSONObject command = new JSONObject();
        JSONObject parameters = new JSONObject();
        JSONArray objects = new JSONArray();
        long l = System.currentTimeMillis()/1000;
//        String s=treemap.toString();
        String data = "";
        if (treemap.size()>0&&treemap!=null) {
            Iterator titer = treemap.entrySet().iterator();
            while (titer.hasNext()) {
                Map.Entry ent = (Map.Entry) titer.next();
                String keyt = ent.getKey().toString();
                String valuet = ent.getValue().toString();
                parameters.put(keyt, valuet);
                data += (keyt + valuet);
            }
        }
        objects.add(parameters);

        params.put("ctn", ctnData);//控制器名
        params.put("mtn", mtnData);//方法名
        params.put("timestamp", l);//时间戳
        params.put("api_key",api_key);//固定时间
        params.put("data", objects);
        params.put("sign", postRequests(data,l));
        MyLog.e("request","请求最终结果="+params.toJSONString());
        return  params.toJSONString();
    }
//    private static String postRequests(TreeMap<String, String> treemap)  {
//        String replace = treemap.toString().replace(" ", "");
//        String md5ResultCode =  MD5Utils.encryptMD5((MD5Utils.encryptMD5((replace)) + key));
//        return md5ResultCode;
//    }
    private static String postRequests(String replace,long time)  {
        String md5ResultCode =  MD5Utils.encryptMD5((MD5Utils.encryptMD5((replace)+time) + key)).toUpperCase();
        return md5ResultCode;
    }
}
