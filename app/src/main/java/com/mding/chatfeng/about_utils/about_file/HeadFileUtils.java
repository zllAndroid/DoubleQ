package com.mding.chatfeng.about_utils.about_file;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.mding.chatfeng.about_base.AppConfig;

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
//        File fileHead = ImageUtil.saveBitmap(mContext, bitmap);
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
            case AppConfig.TYPE_GROUP_CHAT :
                url=  BaseFilePathUtils.getGroupChatHeadPaths(id,time);
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
//        File fileHead = ImageUtil.saveBitmap(mContext, bitmap);
            if (mFileHead != null)
                return mFileHead;
            else
                return null;
        }else
            return null;
    }
    public  static File saveImgPathForGroupId(File resource,String type,String id,String time){
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
//        File fileHead = ImageUtil.saveBitmap(mContext, bitmap);
            if (mFileHead != null)
                return mFileHead;
            else
                return null;
        }else
            return null;
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
}
