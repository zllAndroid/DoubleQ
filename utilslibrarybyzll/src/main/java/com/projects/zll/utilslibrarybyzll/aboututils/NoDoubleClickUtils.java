package com.projects.zll.utilslibrarybyzll.aboututils;



/**
 * 多次点击控制  工具类
 */

public class NoDoubleClickUtils {
    private static long lastClickTime;
    private final static int SPACE_TIME = 500;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }
//    boolean netWorkAvailable = HelpUtils.isNetWorkAvailable(LoginActivity.this);
public synchronized static boolean isDoubleClick() {
//    boolean networkable = HelpUtils.isNetworkable();
//    if (!networkable)
//    {
//        Tip.getDialog(AppManager.getAppManager().currentActivity(),"请检查网络设置");
//        return false;
//    }
    long currentTime = System.currentTimeMillis();
//        long lastClickTime=0;
    long time =currentTime - lastClickTime;
    lastClickTime = currentTime;
    if (time > SPACE_TIME) {
        return true;
//            if (HelpUtils.isNetWorkAvailable())
//            {
//                return true;
////                有网络
//            }
//            else
//            {
//                Tip.getDialog(AppManager.getAppManager().currentActivity(),CommonParameter.ERROR);
//                return false;
//            }
    } else {
        ToastUtil.show("点击太频繁，请稍后");
//        Tip.getDialog(AppManager.getAppManager().currentActivity(),"点击太频繁，请稍后");
        return false;
    }
}
}
