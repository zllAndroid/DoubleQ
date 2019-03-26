package com.mding.chatfeng.about_utils;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.model.about_update.DataUpdate;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class VersionCheckUtils {
    static DataUpdate.RecordBean record = null;
    public static  void initUpdata(final String result, final  boolean isReturn) {
//        final int localVersion = HelpUtils.getLocalVersion(AppManager.getAppManager().currentActivity());
//                Log.e("result", "请求结果result----------==" + result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(AppConfig.CODE_OK)) {
//                    是否主动请求，无版本更新会弹出提示
                    if (isReturn)
                    {
                        initJson(result);
                    }else
                    {
                        initJsonReturn(result);
                    }

                }  else {
                    ToastUtil.show(sucess);
                }
    }
    public static void initJson(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpdate dataUpVersion = JSON.parseObject(msg, DataUpdate.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getUpdate();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("2"))
                    {
                        if (record.getForce().equals("1"))
                        {
                            new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, record.getContent());
                            try {
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true, record.getContent());
                            try {
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void initJsonReturn(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpdate dataUpVersion = JSON.parseObject(msg, DataUpdate.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getUpdate();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("2"))
                    {
//                        是否强制更新
                        if (record.getForce().equals("1"))
                        {
                            try {
//                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, msg);
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            try {
//                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true,msg);
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
                        DialogUtils.showDialogOne(msg, new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                            }
                        });
//                        DialogUtils.showDialogOne("已经是最新版本", new DialogUtils.OnClickSureListener() {
//                            @Override
//                            public void onClickSure() {
//
//                            }
//                        });
//                       Tip.getDialog(AppManager.getAppManager().currentActivity(),"已经是最新版本");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
