package com.doubleq.xm6leefunz.about_base;



import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.MD5Utils;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/03/10 0006.
 */
public class SignForXm6leefunJava {
    public static String getSing(ArrayList<String> mList) {
        // 头部URL
        String headTemp = "";
//        如果有token  ，添加进行加密
        if (!StrUtils.isEmpty(SplitWeb.USER_TOKEN))
        {
            mList.add("token=" + SplitWeb.USER_TOKEN);
        }
//        MyLog.e("token","token="+SplitWeb.USER_TOKEN);
        Collections.sort(mList);
        try {
            for (int i = 0; i < mList.size(); i++) {
                if (i==0)
                {
                    headTemp += mList.get(i).split("=")[0] +"="+ SignForXm6leefunJava.enUrlCode(mList.get(i).split("=")[1]);
//                    headTemp += StringUnicode.enUrlCode(mList.get(i));
                }else
                {
//                    headTemp += "&" +(mList.get(i)) ;
                    headTemp += "&" + mList.get(i).split("=")[0] +"="+ SignForXm6leefunJava.enUrlCode(mList.get(i).split("=")[1]);
                }
            }
        } catch (Exception e) {
        }
        return headTemp;
////        获取容器中的数据
//        String replace = mList.toString().replace("[", "{").replace("]", "}").replace(" ","");
//        String  s = MD5Utils.encryptMD5((MD5Utils.encryptMD5((replace)) + "xm6leefun"));
//        return ((headTemp)+"&outsideSign=" + s);
    }
    public static String enUrlCode(String text) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(text, "utf-8");
    }
   static Map<String, String> map = new TreeMap<>();
    public static Map<String, String> postRequests(ArrayList<String> mList)  {
        map.clear();
        if (!StrUtils.isEmpty(SplitWeb.USER_TOKEN))
        {
            mList.add("token=" + SplitWeb.USER_TOKEN);
        }
        for (int i = 0; i < mList.size(); i++)
        {
            map.put(mList.get(i).split("=")[0],mList.get(i).split("=")[1]);
        }
        String replace = map.toString().replace(" ", "");
        String md5ResultCode =  MD5Utils.encryptMD5((MD5Utils.encryptMD5((replace)) + "xm6leefun"));
        map.put("outsideSign",md5ResultCode);
        MyLog.e("map","map="+map.toString());
        return map;
    }
}
