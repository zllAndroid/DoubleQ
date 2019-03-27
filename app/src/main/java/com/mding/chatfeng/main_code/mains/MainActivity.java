package com.mding.chatfeng.main_code.mains;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.VersionCheckUtils;
import com.mding.chatfeng.about_utils.about_immersive.StatusBarUtil;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 项目：DoubleQ
 * 文件描述：程序主界面
 * 作者：zll
 * 创建时间：
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class MainActivity extends BaseActivity {

    FragmentTabHost mTabHost;
    TabWidget tabs;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isGones() {
        return true;
    }
    public static  int stateHight;
    public static  int naigertionHight;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        stateHight=StatusBarUtil.getStatusBarHeight(this);
        naigertionHight=StatusBarUtil.getDaoHangHeight(this);
        initUIData();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                send(SplitWeb.coroutineKeep());
//            }
//        }).start();
        mTabHost= findViewById(android.R.id.tabhost);
        tabs= findViewById(android.R.id.tabs);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        去掉分割线
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        for (int i = 0; i < mFragmentList.size(); i++) {
            TabItem tabItem = mFragmentList.get(i);
            // 创建 tab
            mTabHost.addTab(mTabHost.newTabSpec(tvtab[i]).setIndicator(tabItem.getTabView(i)),  tabItem.getFragmentClass(), null);
            // 底部 tab 栏设置背景图片
            mTabHost.getTabWidget().setBackgroundResource(R.color.white);
        }
//        刷新首页tab数量
//          刷新联系人tab数量
        initBro();
//        initBroc();
        int num = (int) SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
        if (num>0)
        {
            Intent intent = new Intent();
            intent.putExtra("num", num );
            intent.setAction("action.addFriend");
            sendBroadcast(intent);
        }

//        版本更新
        int localVersion = 0;
        try {
            localVersion = HelpUtils.getLocalVersion(MainActivity.this);
            sendWeb(SplitWeb.appUpdate(""+localVersion));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        if(!SplitWeb.IS_SET_ACTIVITY.equals("1")) {
            String method = HelpUtils.backMethod(responseText);
            switch (method) {
                case "appUpdate":
                    VersionCheckUtils.initUpdata(responseText, true);
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {

            if (mRefreshBroadcastReceiver!=null)
                this.unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新新消息数量
     *
     * @param tabIndex tab 的索引
     * @param msgCount 消息数量
     */
    public void updateMsgCount(int tabIndex, int msgCount) {
        if (mFragmentList!=null)
            mFragmentList.get(tabIndex).setNewMsgCount(msgCount);
    }

    private List<TabItem> mFragmentList;
    private void initUIData() {

        mFragmentList = new ArrayList<>();
        mFragmentList.clear();
        mFragmentList.add(new TabItem(
                MsgFragment.class
        ));

        mFragmentList.add(new TabItem(
                LinkManFragment.class
        ));

        mFragmentList.add(new TabItem(
                FindFragment.class
        ));

        mFragmentList.add(new TabItem(
                PersonalFragment.class
        ));
    }

    private void initBro() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshMain");
        intentFilter.addAction("action.addFriend");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
    }
    //    private void initBroc() {
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("action.addFriend");
//        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
//    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshMain"))
            {
                String num = intent.getStringExtra("num");
//                更新消息列表
                updateMsgCount(0,Integer.valueOf(num));
            }else  if (action.equals("action.addFriend"))
            {
                int num = intent.getIntExtra("num",0);
                updateMsgCount(1,num);
            }
        }
    };

    private String[] tvtab ={"消息","联系人","发现", "个人中心" };
    TextView mTvNum;
    //    int[] imgs = {R.drawable.tab_ac_main_msg,R.drawable.tab_ac_main_contacts, R.drawable.tab_ac_main_pesonal};
    int[] imgs = {R.drawable.tab_ac_main_msg,R.drawable.tab_ac_main_contacts, R.drawable.tab_ac_main_discovery,R.drawable.tab_ac_main_pesonal};
    @Override
    public boolean isSupportSwipeBack() {
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

    class TabItem {

        private ImageView mIvTab;


        private TextView mTvTab;

        /**
         * Fragment
         */
        private Class<? extends Fragment> fragmentClass;

        private View mTabView;

        public TabItem(
                Class<? extends Fragment>
                        fragmentClass) {
            this.fragmentClass = fragmentClass;
        }
        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }
        /**
         * 新消息
         */
        private TextView mTvNewMsg;
        RelativeLayout mReNum;
        ImageView mImgPoint;
        public View getTabView(int type) {
            mTabView = View.inflate(MainActivity.this, R.layout.layout_tabin, null);

            mIvTab = mTabView.findViewById(R.id.img_main_tab);
            mImgPoint = mTabView.findViewById(R.id.img_main_point);
            mTvTab =  mTabView.findViewById(R.id.tv_main_tab);
            mTvNewMsg =  mTabView.findViewById(R.id.main_tv_num);
            mReNum =  mTabView.findViewById(R.id.main_re_num);

            mTvTab.setText(tvtab[type]);
            mIvTab.setImageResource(imgs[type]);
//            switch (type)
//            {
//                case 0:
//                case 1:
//                    mImgPoint.setVisibility(View.GONE);
//                    mReNum.setVisibility(View.GONE);
//                    break;
//                case 2:
//                case 3:
//                    mImgPoint.setVisibility(View.GONE);
//                    mReNum.setVisibility(View.GONE);
//                    break;
//            }
            return mTabView;
        }
        /**
         * 设置新消息数量
         */
        public void setNewMsgCount(int count) {
            if (count > 0) {
                mReNum.setVisibility(View.VISIBLE);
                mImgPoint.setVisibility(View.GONE);
                if (count>99) {
                    mTvNewMsg.setText("99+");
                }else {
                    mTvNewMsg.setText(String.valueOf(count));
                }
            }else
            {
                mReNum.setVisibility(View.GONE);
                mImgPoint.setVisibility(View.GONE);
                mTvNewMsg.setText(String.valueOf(count));
            }
        }
    }
}