package com.doubleq.xm6leefunz.about_base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;


import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataJieShou;
import com.doubleq.model.DataNoticAddFriendNews;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.web_base.MessageEvent;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.base_chat.SlidingLayout;
import com.doubleq.xm6leefunz.about_custom.loding.LoadingDialog;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NotificationUtil;
import com.doubleq.xm6leefunz.about_utils.SysRunUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.ScreenUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends BaseActivityForResult  {

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        if (isSupportSwipeBack()) {
            SlidingLayout rootView = new SlidingLayout(this);
//            rootView.setBackgroundColor(getResources().getColor(R.color.trans));
            rootView.bindActivity(this);
        }
    }

    protected boolean isSupportSwipeBack() {
        return true;
    }

}