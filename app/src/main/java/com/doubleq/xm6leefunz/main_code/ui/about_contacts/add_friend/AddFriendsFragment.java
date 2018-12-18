package com.doubleq.xm6leefunz.main_code.ui.about_contacts.add_friend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_scan.ScanCodeActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendsFragment extends Fragment {

    public AddFriendsFragment() {
    }
    View view;
    String url ="http://omyk3uve8.bkt.clouddn.com/cities.txt";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        String text = (String) bundle.get("text");
//        View view = getLayoutInflater().inflate(R.layout., null);
//
        if (view==null) {
            if (position == 0) {
                view = inflater.inflate(R.layout.fragment_add_friends, container, false);
                initFriend(view);
            }
            else if (position == 1)
            {
                view = inflater.inflate(R.layout.fragment_add_qun,container,false);
                initQun(view);
            }
        }
        return view;
    }

    private void initQun(View view) {
//        搜索群
        view.findViewById(R.id.add_qun_lin_seach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(SearchActivity.class);
            }
        });
//        扫一扫
        view.findViewById(R.id.add_qun_lin_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(ScanCodeActivity.class);
            }
        });
    }

    private void initFriend(View view) {
//        搜索好友
        view.findViewById(R.id.add_friend_lin_seach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(SearchActivity.class);
            }
        });
//        扫一扫
        view.findViewById(R.id.add_friend_lin_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(ScanCodeActivity.class);
            }
        });


    }


}
