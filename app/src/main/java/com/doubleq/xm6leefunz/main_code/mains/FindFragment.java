package com.doubleq.xm6leefunz.main_code.mains;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_discovery.FriendCircleActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment {
    Unbinder unbinder;

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

    //    ConfirmPopWindow confirmPopWindow=null;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.discover_lin_animal_fuhua, R.id.discover_lin_AI, R.id.discover_lin_animal_shop, R.id.discover_lin_animal_life, R.id.discover_lin_yuyanjia, R.id.discover_lin_gaoBaiQiang, R.id.discover_lin_zhi_addFriend, R.id.discover_lin_app_store, R.id.discover_lin_music, R.id.discover_lin_shiYong, R.id.discover_lin_fangWei, R.id.discover_lin_guangGao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.discover_lin_friendcircle:
//                DialogUtils.showDialog("敬请期待！");
//                break;
            case R.id.discover_lin_animal_fuhua:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_AI:
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
