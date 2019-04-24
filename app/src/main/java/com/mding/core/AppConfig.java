/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AppConfig
 * Author: xm6leefun
 * Date: 2019/4/4 上午2:56
 * Description: chatfeng
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.mding.core;

import android.util.Log;

/**
 *
 * @ProjectName: ChatFengIM
 * @Package: my.maya.android.core
 * @ClassName: AppConfig
 * @Description: java类作用描述
 * @Author: wdh
 * @CreateDate: 2019/4/4 上午2:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/4 上午2:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppConfig {
    public final static  boolean DEBUG = true;
    public final static  String TAG = "xf";
    private final static  int NotifyDeamonID=-1001;
    public static String ok="11111";


     public static void logs(String logmsg){
         if (DEBUG)
            Log.e(TAG,logmsg);
     }

    public static void logs(String tag,String logmsg){
        if (DEBUG)
            Log.d(tag,logmsg);
    }
}