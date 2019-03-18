package com.doubleq.xm6leefunz.main_code.mains;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.ConfirmPopWindow;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_discovery.FriendCircleActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment {
    public FindFragment() {
    }

    View view;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        if(view == null) {
//            view = inflater.inflate(R.layout.fragment_discovery, container, false);
//        }
//        initUI(view);
//        return view;
//    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        view = getTopBarView();
        initUI(view);
    }
    @Override
    protected String setFragmentTital() {
        return "朋友圈";
    }
    private void initUI(final View view) {
        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmPopWindow(getActivity()).showAtBottom(view.findViewById(R.id.include_frag_img_add));
            }
        });
        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.JumpTo(SearchActivity.class);
            }
        });
        //        朋友圈
        view.findViewById(R.id.discover_lin_friendcircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FriendCircleActivity.class));
            }
        });
    }
}
