package com.mding.chatfeng.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_custom.CustomViewPager;
import com.mding.chatfeng.main_code.ui.about_contacts.ContactChildFragment;
import com.mding.chatfeng.main_code.ui.about_contacts.ModelbyMissABC;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.chatfeng.main_code.ui.about_pop.PopAddWindow;
import com.mding.chatfeng.about_base.BaseFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;

import org.greenrobot.eventbus.EventBus;

import static com.mding.chatfeng.about_utils.IntentUtils.JumpTo;

/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之联系人页面
 * 作者：zll
 * 修改者：ljj
 */
public class LinkManFragment extends BaseFragment {

    public LinkManFragment() {}
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(true);
//        if (this.getView() != null)
//            this.getView().setVisibility(isVisibleToUser ? View.VISIBLE : View.GONE);
//    }
    CustomViewPager mViewpager;
    private SmartTabLayout viewPagerTab;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        initUI(view);
    }

    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;
    private String[]  contacts_tou = new String[]{"好友","群组"};

    @Override
    protected String setFragmentTital() {
        return "联系人";
    }

    @Override
    protected void searchClickEvent() {
        JumpTo(SearchActivity.class);
    }

    public static int isChangePage = 0;
    private void initUI(View mView) {
        viewPagerTab = mView.findViewById(R.id.contacts_viewpagertab);
        mViewpager = mView.findViewById(R.id.contacts_viewpager);
        myPagerAdapter = new LinkManFragment.MyPagerAdapter(getChildFragmentManager());
        if (ismPager)
        {
            ismPager = !ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
            mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }

                @Override
                public void onPageSelected(int i) {
                    isChangePage = i;
                    ModelbyMissABC modelbyMissABC = new ModelbyMissABC();
                    modelbyMissABC.setMissWho(i);
                    EventBus.getDefault().post(modelbyMissABC);
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                }
            });
        }
    }
    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        public CharSequence getPageTitle(int position)
        {
            return contacts_tou[position];
        }

        private MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return contacts_tou.length;
        }

        @Override
        public Fragment getItem(int position)
        {
            ContactChildFragment fragment = new ContactChildFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", contacts_tou[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
