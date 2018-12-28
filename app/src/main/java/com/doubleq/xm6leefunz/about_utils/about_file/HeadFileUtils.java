package com.doubleq.xm6leefunz.about_utils.about_file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_utils.ImageUtils;

import java.io.File;
import java.io.IOException;

public class HeadFileUtils {
    public  static File saveHeadPath(Context mContext, File resource){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        Bitmap bitmap ;
        bitmapOptions.inJustDecodeBounds = false;
        int be = (int) (bitmapOptions.outHeight / (float) 200);
        if (be <= 0)
            be = 1;
        bitmapOptions.inSampleSize = be;

        File mFileHead = new File(BaseFilePathUtils.getHeadPaths());
        try {
            mFileHead.getParentFile().mkdirs();
            mFileHead.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilePath.copyFile(resource.getPath(),mFileHead.getPath());
//        bitmap = BitmapFactory.decodeFile(mFileHead.getPath(), bitmapOptions);
//        File fileHead = ImageUtils.saveBitmap(mContext, bitmap);
        if (mFileHead!=null)
            return   mFileHead;
        else
            return null;
    }
    public  static File saveImgPath(File resource,String type,String id,String time){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        Bitmap bitmap ;
        bitmapOptions.inJustDecodeBounds = false;
        int be = (int) (bitmapOptions.outHeight / (float) 200);
        if (be <= 0)
            be = 1;
        bitmapOptions.inSampleSize = be;
        String url = null;
        switch (type)
        {
            case AppConfig.TYPE_FRIEND :
                url=  BaseFilePathUtils.getLinkFriendPaths(id,time);
                break;
            case AppConfig.TYPE_GROUP :
                url=  BaseFilePathUtils.getLinkGroupPaths(id,time);
                break;
        }
        if (url!=null) {
            File mFileHead = new File(url);
            try {
                mFileHead.getParentFile().mkdirs();
                mFileHead.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FilePath.copyFile(resource.getPath(), mFileHead.getPath());
//        bitmap = BitmapFactory.decodeFile(mFileHead.getPath(), bitmapOptions);
//        File fileHead = ImageUtils.saveBitmap(mContext, bitmap);
            if (mFileHead != null)
                return mFileHead;
            else
                return null;
        }else
            return null;
    }
}
