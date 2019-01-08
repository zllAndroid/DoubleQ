package com.doubleq.xm6leefunz.about_base.web_base;

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
import android.widget.Toast;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.ScreenUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.zll.websocket.AbsWebSocketActivity;
import com.zll.websocket.ErrorResponse;
import com.zll.websocket.IWebSocketPage;
import com.zll.websocket.Response;
import com.zll.websocket.WebSocketServiceConnectManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//public class BaseActivity extends AppCompatActivity {
public class TestBaseActivity extends AppCompatActivity  {

    public Handler mHandler = null;
    Unbinder bind =null;
    View view;
    String simpleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        simpleName = getClass().getSimpleName();
        ScreenUtils.setWindowStatusBarColor(AppManager.getAppManager().currentActivity(),R.color.app_theme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            显示 内屏返回键
            if (!simpleName.equals("MainActivity")) {
                WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
            } else
                WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
        }
        initSwipeBackFinish();
        initBeforeContentView();
        setContentView(getLayoutView());
        bind = ButterKnife.bind(this);

        if (mHandler==null)
            mHandler= new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg != null)
                        onHandleMessage(msg);
                    return false;
                }
            });
        initBaseView();
        EventBus.getDefault().register(this);
    }
    protected void sendWeb(String text) {
        MyApplication.getmConnectManager().sendText(text);
    }


    protected void initBeforeContentView() {
    }

    protected void initBaseView() {
    }
    protected int getLayoutView() {
        return 0;
    }

    public void onHandleMessage(Message msg) {
    }

    protected boolean isGones() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null) {
            bind.unbind();
            bind=null;
        }
        EventBus.getDefault().unregister(this);
//        DialogUtils.isShow();
    }
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        receiveResultMsg(messageEvent.getMessage());
//        textView.setText(messageEvent.getMessage());
    }

    private void initSwipeBackFinish() {
        if (isSupportSwipeBack()) {
            initSwiBack();
        }
    }
    private void initview(){
        if (isTopBack())
        {
            try {
                findViewById(R.id.include_top_iv_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppManager.getAppManager().finishActivity();
                    }
                });
                isGone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isGones())
        {
            isGone();
        }
    }

    private void isGone() {
        View mtv;
        mtv = (View) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mtv.setVisibility(View.VISIBLE);
        }else
        {
            mtv.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        initview();
    }

    protected boolean isSupportSwipeBack() {
        return true;
    }
    private void initSwiBack() {
        SlidingPaneLayout slidingPaneLayout =  new SlidingPaneLayout(this);
        //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认是32dp，现在给它改成0
        try {
            //属性
            Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            f_overHang.setAccessible(true);
            f_overHang.set(slidingPaneLayout, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {
                AppManager.getAppManager().finishActivity();
                overridePendingTransition(0, R.anim.slide_out_right);
            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

        View leftView = new View(this);
        leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftView, 0);

        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decorChild.setBackgroundColor(getResources().getColor(android.R.color.white));
        decor.removeView(decorChild);
        decor.addView(slidingPaneLayout);
        slidingPaneLayout.addView(decorChild, 1);
    }
    protected boolean isTopBack() {
        return true;
    }

//
//    public void sendText(String text) {
//        MyApplication.getmConnectManager().sendText(text);
//    }




    public void receiveResultMsg(String responseText) {

    }

//    @Override
//    public void onSendMessageError(ErrorResponse error) {
////        DialogUtils.showDialog("错误"+error.getRequestText());
//    }
}