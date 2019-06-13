package com.mding.chatfeng.main_code.mains;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.MainTabNumEvent;
import com.mding.chatfeng.about_custom.about_cus_dialog.DialogRiskTestUtils;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_utils.VersionCheckUtils;
import com.mding.chatfeng.about_utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.about_utils.about_immersive.StatusBarUtil;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_utils.windowStatusBar;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
/**
 * 项目：DoubleQ
 * 文件描述：程序主界面,app更新接口
 * 作者：zll
 * 创建时间：
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class MainActivity extends BaseActivity  {

    FragmentTabHost mTabHost;
    TabWidget tabs;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    public static  int stateHight;
    public static  int naigertionHight;
    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();

        WindowBugDeal.SetTop(this);
        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 0);
//        StateBarUtils.setFullscreen(this, false, false);
        StateBarUtils.setAndroidNativeLightStatusBar(this,false);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.WHITE);
    }

    //        设置导航栏颜色
    public void initStateBar() {
//        hideBottomUIMenu();
    }

    @Override
    protected boolean isChenjinshi() {
        return false;
    }
    boolean isLogin = false;
    @Override
    protected void initBaseView() {
        super.initBaseView();
//        tvtab ={getResources().getString(R.string.msg),getResources().getString(R.string.contacts),getResources().getString(R.string.discover), getResources().getString(R.string.personal)};
        ((BaseApplication)getApplication()).startBase(getApplicationContext());

        stateHight=StatusBarUtil.getStatusBarHeight(this);
        naigertionHight=StatusBarUtil.getDaoHangHeight(this);
        initUIData();
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
        int num = (int) SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
        if (num>0)
        {
            EventBus.getDefault().post(new MainTabNumEvent(num,AppConfig.MAIN_TAB_TWO));
        }
        Intent intent_dialog = getIntent();
        if (intent_dialog != null){
            isLogin = intent_dialog.getBooleanExtra(AppConfig.IS_LOGIN, false);
            if (isLogin){
                DialogRiskTestUtils.showDialog(getResources().getString(R.string.risk_test_title), getResources().getString(R.string.risk_test),
                        getResources().getString(R.string.risk_test_organization), getResources().getString(R.string.risk_test_time),
                        new DialogRiskTestUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {

                            }
                        });
            }
        }
//initUpData();
        if (BaseApplication.isMain) {
//        版本更新
            int localVersion = 0;
            try {
                localVersion = HelpUtils.getLocalVersion(MainActivity.this);
                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
                netWorkUtlis.setOnNetWorkNormal(SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
                    @Override
                    public void onNetSuccess(String result) {
                        MyLog.e("result","appUpdateHttp------------------->"+result);
                        String isSucess = HelpUtils.HttpIsSucess(result);
                        if (isSucess.equals(AppConfig.CODE_OK))
                            VersionCheckUtils.initUpdata(result, true);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
        {
            sendWeb(SplitWeb.getSplitWeb().bindUid());
        }
        BaseApplication.isMain=false;
    }

    private void initUpData() {
        int localVersion = 0;
        try {
            localVersion = HelpUtils.getLocalVersion(MainActivity.this);
            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
            netWorkUtlis.setOnNetWorkNormal(SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
                @Override
                public void onNetSuccess(String result) {
                    Log.e("result","appUpdateHttp------------------->"+result);
                    String isSucess = HelpUtils.HttpIsSucess(result);
                    if (isSucess.equals(AppConfig.CODE_OK))
                        VersionCheckUtils.initUpdata(result, true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogRiskTestUtils.isShow();
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

    private List<TabItem> mFragmentList=null;
    private void initUIData() {
        if(mFragmentList==null) {
            mFragmentList = new ArrayList<>();
            mFragmentList.clear();
            mFragmentList.add(new TabItem(MsgFragment.class));

            mFragmentList.add(new TabItem( LinkManFragment.class));

            mFragmentList.add(new TabItem(FindFragment.class));

            mFragmentList.add(new TabItem(PersonalFragment.class));
        }
    }
    private void initTabNum(int num, String action) {
        if (action.equals(AppConfig.MAIN_TAB_ONE))
        {
//                更新消息列表
            updateMsgCount(0,Integer.valueOf(num));
        }else  if (action.equals(AppConfig.MAIN_TAB_TWO))
        {
//            更新联系人列表
            updateMsgCount(1,num);
        }
    }
    //获取tab条数并设置
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainTabNumEvent event) {
        int num = event.getNum();
        String type = event.getType();
        initTabNum(num, type);
//   发送     EventBus.getDefault().post(new MessageEvent(message.getResponseText()));
    }
    private String[] tvtab ={BaseApplication.getAppContext().getResources().getString(R.string.msg_fragment),BaseApplication.getAppContext().getResources().getString(R.string.contacts_fragment),BaseApplication.getAppContext().getResources().getString(R.string.discover_fragment), BaseApplication.getAppContext().getResources().getString(R.string.personal_fragment)};
//    private String[] tvtabs ={getResources().getString(R.string.msg),getResources().getString(R.string.contacts),getResources().getString(R.string.discover), getResources().getString(R.string.personal)};
//    private String[] tvtab ={"消息","联系人","发现", "个人中心" };
    int[] imgs = {R.drawable.tab_ac_main_msg,R.drawable.tab_ac_main_contacts, R.drawable.tab_ac_main_discovery,R.drawable.tab_ac_main_pesonal};
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
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

        public TabItem(Class<? extends Fragment>fragmentClass) {
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