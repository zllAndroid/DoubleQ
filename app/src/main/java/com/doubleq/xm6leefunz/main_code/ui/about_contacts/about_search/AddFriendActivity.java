package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_chat.base_chat.SlidingActivity;
import com.doubleq.xm6leefunz.about_custom.CustomViewPager;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.add_friend.AddFriendsFragment;
import com.doubleq.xm6leefunz.main_code.mains.LinkManFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;


public class AddFriendActivity extends SlidingActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout mLinBack;
    @BindView(R.id.add_friend_viewpagertab)
    SmartTabLayout viewPagerTab;
    @BindView(R.id.add_friend_viewpager)
    CustomViewPager mViewpager;

    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("添加");
        mLinBack.setBackgroundColor(getResources().getColor(R.color.app_theme));
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        if (ismPager)
        {
            ismPager = !ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
        }
    }

    private String[] contacts_tou = new String[]{"找人","找群"};
    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_friend;
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
            AddFriendsFragment fragment = new AddFriendsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", contacts_tou[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
