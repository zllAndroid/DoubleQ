package com.doubleq.xm6leefunz.about_utils.about_file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
        bitmap = BitmapFactory.decodeFile(mFileHead.getPath(), bitmapOptions);
        File fileHead = ImageUtils.saveBitmap(mContext, bitmap);
        if (fileHead!=null)
            return   fileHead;
        else
            return null;
    }
}
