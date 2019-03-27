package com.mding.chatfeng.main_code.ui.about_contacts.add_friend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_scan.ScanCodeActivity;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;


/**
 * 添加好友/群
 */
public class AddFriendsFragment extends Fragment {

    public AddFriendsFragment() {
    }
    View view;
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
                IntentUtils.JumpToHaveOne(SearchActivity.class,SearchActivity.SeacchKey,SearchActivity.SeacchGroup);
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
                IntentUtils.JumpToHaveOne(SearchActivity.class,SearchActivity.SeacchKey,SearchActivity.SeacchFriend);
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
