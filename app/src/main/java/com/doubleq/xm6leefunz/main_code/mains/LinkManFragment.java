package com.doubleq.xm6leefunz.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_custom.CustomViewPager;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.ContactChildFragment;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_pop.PopAddWindow;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.ConfirmPopWindow;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import static com.doubleq.xm6leefunz.about_utils.IntentUtils.JumpTo;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkManFragment extends BaseFragment {

    public LinkManFragment() {}

    View mView =null;
    CustomViewPager mViewpager;
    private SmartTabLayout viewPagerTab;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        if(mView == null) {
//            mView = inflater.inflate(R.layout.fragment_contacts, container, false);
//
//////            TextView head;
//////            ImageView search,add;
//////            head = view.findViewById(R.id.include_frag_tv_title);
////////            search = view.findViewById(R.id.include_frag_img_search);
////////            add = view.findViewById(R.id.include_frag_img_add);
//////            head.setText("联系人");
//        }
//        initUI(mView);
//        return mView;
//    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        view = getTopBarView();
        initUI(view);
    }

    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;
    private String[]  contacts_tou = new String[]{"好友","群组"};
    PopAddWindow popAddWindow =null;

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
//        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new ConfirmPopWindow(getActivity()).showAtBottom(view.findViewById(R.id.include_frag_img_add));
//            }
//        });
//        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JumpTo(SearchActivity.class);
//            }
//        });


//        PopAddWindow

        viewPagerTab = mView.findViewById(R.id.contacts_viewpagertab);
        mViewpager = mView.findViewById(R.id.contacts_viewpager);
        myPagerAdapter = new LinkManFragment.MyPagerAdapter(getChildFragmentManager());
        if (ismPager)
        {
            ismPager = !ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
            mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    isChangePage = i;
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
