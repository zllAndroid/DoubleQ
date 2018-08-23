package com.doubleq.xm6leefunz.main_code.about_fragment.about_message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_custom.CustomViewPager;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_message.MessageChildFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends BaseFragment {
    public MsgFragment() {
    }

    View view =null;
    TextView tv_title;
    CustomViewPager mViewpager;
    private SmartTabLayout viewPagerTab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_msg, container, false);
        }
        initUI(view);
        return view;
    }
    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;
    private String[] msg_tou = new String[]{"好友","群消息"};
    private void initUI(View view) {
        tv_title = view.findViewById(R.id.include_frag_tv_title);
        tv_title.setText("消息");
        viewPagerTab = view.findViewById(R.id.msg_viewpagertab);
        mViewpager = view.findViewById(R.id.msg_viewpager);
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        if (ismPager)
        {
            ismPager =!ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
        }
    }

    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        public CharSequence getPageTitle(int position)
        {
            return msg_tou[position];
        }

        private MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return msg_tou.length;
        }

        @Override
        public Fragment getItem(int position)
        {
            MessageChildFragment fragment = new MessageChildFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", msg_tou[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
