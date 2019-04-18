package com.mding.chatfeng.about_utils.about_file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.GlideCacheUtil;
import com.mding.chatfeng.about_utils.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FilePath {

    public  static  String appPath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/AllChat/";
    public  static  String appFilePath =appPath+SplitWeb.getSplitWeb().getUserId()+"/";
    public  static  String myHeadImg =appFilePath+"chatHead/";
    public  static  String LinkImgPath =appFilePath+"imgLinkFriend/";

    //    个人中心头像部分
    public  static  String getAbsPath(){
        File file = new File(appFilePath);
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
//        File file = new File(getPath());
        return  appFilePath;
    }
    public  static  String getAbsPath(String mFileName){
        File file = new File(mFileName);
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
//        File file = new File(getPath());
        return  mFileName;
    }
    private static  String getPath(){
        File file = new File(appPath);
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
        return  appPath;
    }
//    private static  String getPathUser(){
//
//        return  Environment.getExternalStorageDirectory().getAbsolutePath()+"/AllChat/"+SplitWeb.getSplitWeb().getUserId()+"/";
//    }
    public static  String getLinkImgPath(){
        File file = new File(getAbsPath()+"imgLinkFriend/");
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
        return  getAbsPath()+"imgLinkFriend/";
    }
    public  static  String getHeadPath(){
//        File file = new File(getAbsPath() + "/chatHead/");
        File file = new File(getHeadPaths());
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
//            try {
////                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return getHeadPaths();
    }
    public static String getFileName(String pathandname){
        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return pathandname.substring(start+1,end);
        }else{
            return null;
        }
    }
    public static String getUserNewHead(Context context){
        GlideCacheUtil.getInstance().clearImageAllCache(context);
        List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath()+"chatHead/");
        if (fileName!=null&&fileName.size()>0)
        {
            String path=fileName.get(fileName.size()-1);
            return path;
        }else{
            return "";
        }
    }
    public static List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            s.add(files[i].getAbsolutePath());
        }
        return s;
    }
    public static List<String> getHeadAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            String fileName = getFileName(files[i].getAbsolutePath());
            s.add(fileName);
        }
        return s;
    }

    public  static  String getHeadPaths(){
//        File file = new File(getAbsPath() + SplitWeb.getSplitWeb().getUserId() + "head.jpg");
//        if (file.exists())
        return getAbsPath() + "chatHead/"+System.currentTimeMillis()+"-"+SplitWeb.getSplitWeb().getUserId() + "head.jpg";
    }
    public  static  File saveHeadPath(Context mContext,File resource){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//        bitmapOptions.inJustDecodeBounds = true;
        Bitmap bitmap ;
        bitmapOptions.inJustDecodeBounds = false;
        int be = (int) (bitmapOptions.outHeight / (float) 200);
        if (be <= 0)
            be = 1;
        bitmapOptions.inSampleSize = be;
//        File mFileAbs = new File(getAbsPath());
//        if (!mFileAbs.exists()){
//            mFileAbs.getParentFile().mkdirs();
//        }
        File mFileHead = new File(getHeadPath());
//        if (mFileHead.exists()){
//            mFileHead .delete();
//        }
        try {
            mFileHead.getParentFile().mkdirs();
            mFileHead.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        copyFile(resource.getPath(),mFileHead.getPath());
        bitmap = BitmapFactory.decodeFile(mFileHead.getPath(), bitmapOptions);
        File fileHead = ImageUtils.saveBitmap(mContext, bitmap);
        if (fileHead!=null)
            return   fileHead;
        else
            return null;
    }


    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);

            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  static boolean deleteSingleFile() {
        File file = new File(getHeadPath());
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + getHeadPath() + "成功！");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
