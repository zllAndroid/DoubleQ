package com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_custom.CustomViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    public ContactsFragment() {
    }

    View view =null;
    CustomViewPager mViewpager;
    private SmartTabLayout viewPagerTab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_contacts, container, false);

            TextView head;
            ImageView search,add;
            head = view.findViewById(R.id.include_frag_tv_title);
            search = view.findViewById(R.id.include_frag_img_search);
            add = view.findViewById(R.id.include_frag_img_add);
            head.setText("联系人");
            search.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        }
        initUI(view);
        return view;
    }

    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;
    private String[] contacts_tou = new String[]{"好友列表","分组管理","群列表"};
    private void initUI(View view) {
        viewPagerTab = view.findViewById(R.id.contacts_viewpagertab);
        mViewpager = view.findViewById(R.id.contacts_viewpager);
        myPagerAdapter = new ContactsFragment.MyPagerAdapter(getChildFragmentManager());
        if (ismPager)
        {
            ismPager = !ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
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
