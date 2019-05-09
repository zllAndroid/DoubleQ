package com.mding.chatfeng.main_code.mains;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.chatfeng.main_code.ui.about_discovery.FriendCircleActivity;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.mding.chatfeng.about_utils.IntentUtils.JumpTo;

/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之发现页面
 * 作者：zll
 */
public class FindFragment extends BaseFragment {
    public FindFragment() {
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(true);
//        if (this.getView() != null)
//            this.getView().setVisibility(isVisibleToUser ? View.VISIBLE : View.GONE);
//    }
    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void searchClickEvent() {
        // TODO  search
        JumpTo(LoadLinkManActivity.class);
    }
    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        initUI(view);
    }
    @Override
    protected String setFragmentTital() {
        return "朋友圈";
    }
    private void initUI(final View view) {
//        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (confirmPopWindow==null)
//                    confirmPopWindow = new ConfirmPopWindow(getActivity());
//                confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
//            }
//        });
//        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtils.JumpTo(SearchActivity.class);
//            }
//        });
//        //        朋友圈
//        view.findViewById(R.id.discover_lin_friendcircle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), FriendCircleActivity.class));
//            }
//        });
//        view.findViewById(R.id.find_fuhua).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ZllActivity.class));
//            }
//        });
    }

    @OnClick({ R.id.discover_lin_friendcircle,R.id.discover_lin_animal_fuhua, R.id.discover_lin_ai, R.id.discover_lin_animal_shop, R.id.discover_lin_animal_life, R.id.discover_lin_yuyanjia, R.id.discover_lin_gaoBaiQiang, R.id.discover_lin_zhi_addFriend, R.id.discover_lin_app_store, R.id.discover_lin_music, R.id.discover_lin_shiYong, R.id.discover_lin_fangWei, R.id.discover_lin_guangGao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.discover_lin_friendcircle:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_fuhua:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_ai:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_shop:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_life:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_yuyanjia:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_gaoBaiQiang:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_zhi_addFriend:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_app_store:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_music:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_shiYong:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_fangWei:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_guangGao:
                DialogUtils.showDialog("敬请期待！");
                break;
        }
    }
}
