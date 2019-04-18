package com.mding.chatfeng.about_utils.about_file;

import android.os.Environment;

import com.mding.chatfeng.about_base.web_base.SplitWeb;

import java.io.File;

/**
 * 本应用文件基类
 * 一个应用一个文件(文件名AllChat) -->
 *    一个用户一个文件（文件名用户id）
 *     头像文件夹
 *
 */
public class BaseFilePathUtils {
    //    本应用的文件保存地址
    private static String  appPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/AllChat/"+SplitWeb.getSplitWeb().getUserId()+"/";

//    用户个人头像保存位置
    private static String  headPath=appPath+ "chatHead/";
//    联系人好友保存位置
    private static String  ImgLinkFriend=appPath+ "imgLinkFriend/";
    private static String  ImgLinkGroup=appPath+ "imgLinkGroup/";
    private static String  ImgGroupChat=appPath+ "imgGroupChat/";

    //    本应用的文件保存地址
//    private static  String getAppPath(){
//        return  Environment.getExternalStorageDirectory().getAbsolutePath()+"/AllChat/"+SplitWeb.getSplitWeb().getUserId()+"/";
//    }

//获取绝对地址
    public  static  String getAbsHeadPath(){
        File file = new File(headPath);
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
        return  headPath;
    }
//    获取用户头像地址
    public  static  String getHeadPaths(){
//        File file = new File(getAbsPath() + SplitWeb.getSplitWeb().getUserId() + "head.jpg");
//        if (file.exists())
        return headPath+System.currentTimeMillis()+"-"+SplitWeb.getSplitWeb().getUserId() + "head.jpg";
    }
    public  static  String getLinkFriendPaths(String friendId,String time){
//        File file = new File(getAbsPath() + SplitWeb.getSplitWeb().getUserId() + "head.jpg");
//        if (file.exists())
        return ImgLinkFriend+time+"-"+friendId+ ".jpg";
    }
    public  static  String getLinkGroupPaths(String friendId,String time){
//        File file = new File(getAbsPath() + SplitWeb.getSplitWeb().getUserId() + "head.jpg");
//        if (file.exists())
        return ImgLinkFriend+time+"-"+friendId+ ".jpg";
    }
    public  static  String getGroupChatHeadPaths(String friendId,String time){
//        File file = new File(getAbsPath() + SplitWeb.getSplitWeb().getUserId() + "head.jpg");
//        if (file.exists())
        return ImgGroupChat+time+"-"+friendId+ ".jpg";
    }

}
