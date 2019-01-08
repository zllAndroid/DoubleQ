package com.doubleq.xm6leefunz.main_code.mains;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

public class MainActivity22 extends BaseActivity {

    FragmentTabHost mTabHost;
    TabWidget tabs;
    //    View ac_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }
    @Override
    protected void initBaseView() {
        super.initBaseView();
        mTabHost= findViewById(android.R.id.tabhost);
        tabs= findViewById(android.R.id.tabs);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        去掉分割线
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

        mTabHost.addTab(mTabHost.newTabSpec("message").setIndicator(getIndecator(0)), MsgFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(getIndecator(1)), LinkManFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("discovery").setIndicator(getIndecator(2)), FindFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("personal").setIndicator(getIndecator(3)), PersonalFragment.class, null);
        initBro();
    }

    private void initBro() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshMain");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshMain"))
            {
                initRefresh(intent);
            }
        }
    };

    private void initRefresh(Intent intent) {

        String num = intent.getStringExtra("num");
//        mTabHost.removeViewAt();
        mTvNum.setText(num);
        Log.e("getNumData","getNumData="+num+"----------------------------main---------------");
//        mTabHost.removeViewAt(0);
//        mTabHost.setCurrentTab();
//        mTabHost.removeViewAt(0);
//        tabs.removeViewAt(0);
////        mTabHost.addView(getIndecatorMsg(num),0);
//        mTabHost.addTab(mTabHost.newTabSpec("message").setIndicator(getIndecatorMsg(num)), MsgFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(getIndecator(1)), LinkManFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("discovery").setIndicator(getIndecator(2)), FindFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("personal").setIndicator(getIndecator(3)), PersonalFragment.class, null);

//        mTabHost.addTab(mTabHost.newTabSpec("message").setIndicator(getIndecatorMsg(num)), MsgFragment.class, null);
//        ArrayList<String> strings = new ArrayList<>();


    }

    private String[] tvtab ={"消息","联系人","发现", "个人中心" };
    TextView mTvNum;
    int[] imgs = {R.drawable.tab_ac_main_msg,R.drawable.tab_ac_main_contacts, R.drawable.tab_ac_main_discovery,R.drawable.tab_ac_main_pesonal};
//    private View getIndecatorMsg(String num) {
//        View view = getLayoutInflater().inflate(R.layout.layout_tabin, null);
//        ImageView mImageView = view.findViewById(R.id.img_main_tab);
//        ImageView mImgPoint = view.findViewById(R.id.img_main_point);
//        TextView mTextView =  view.findViewById(R.id.tv_main_tab);
//         mTvNum =  view.findViewById(R.id.main_tv_num);
//        RelativeLayout mReNum =  view.findViewById(R.id.main_re_num);
//        try {
//            mTextView.setText(tvtab[0]);
//            mImageView.setImageResource(imgs[0]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        switch (0)
//        {
//            case 0:
//            case 1:
//                mImgPoint.setVisibility(View.GONE);
//                mReNum.setVisibility(View.VISIBLE);
//                mTvNum.setText(num+"");
//                break;
//            case 2:
//            case 3:
//                mImgPoint.setVisibility(View.VISIBLE);
//                mReNum.setVisibility(View.GONE);
//                break;
//        }
//
//        return view;
//    }
    private View getIndecator(int index) {
        View view = getLayoutInflater().inflate(R.layout.layout_tabin, null);
        ImageView mImageView = view.findViewById(R.id.img_main_tab);
        ImageView mImgPoint = view.findViewById(R.id.img_main_point);
        TextView mTextView =  view.findViewById(R.id.tv_main_tab);
         mTvNum =  view.findViewById(R.id.main_tv_num);
        RelativeLayout mReNum =  view.findViewById(R.id.main_re_num);
        try {
            mTextView.setText(tvtab[index]);
            mImageView.setImageResource(imgs[index]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (index)
        {
            case 0:
            case 1:
                mImgPoint.setVisibility(View.GONE);
                mReNum.setVisibility(View.VISIBLE);
                break;
            case 2:
            case 3:
                mImgPoint.setVisibility(View.VISIBLE);
                mReNum.setVisibility(View.GONE);
                break;
        }

        return view;
    }
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    boolean isExit;
    Handler mHandler = new Handler();
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
//            Intent grayIntent = new Intent(getApplicationContext(), GrayService.class);
//            startService(grayIntent);
            // 双击退出
//            if (isExit)
//            {
////                AppManager.getAppManager().finishActivity();
//                Intent home = new Intent(Intent.ACTION_MAIN);
//                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                home.addCategory(Intent.CATEGORY_HOME);
//                startActivity(home);
//            } else
//            {
//                isExit = true;
//
////                ToastUtil.show("再按一次退出应用");
//                Toast.makeText(this, "再按一次离开我", Toast.LENGTH_SHORT).show();
//                mHandler.postDelayed(new Runnable()
//                {
//                    public void run()
//                    {
//                        isExit = false;
//                    }
//                }, 2000);
//            }
            // return super.onKeyDown(keyCode, event);
            // 拦截系统按键
        }
        return true;
    }
}
