package com.mding.chatfeng.main_code.ui.about_contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DongTaiSetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.friend_switch_hide_mine)
    SwitchButton friendSwitchHideMine;
    @BindView(R.id.friend_switch_hide_his)
    SwitchButton friendSwitchHideHis;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_dong_tai_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("动态设置");
//        radiogroup本身有监听的方法可以直接设置监听，这个监听需要一个回调接口OnCheckedChangeListener，这个接口里面的回调方法给我们返回了两个参数其中int型的参数就是当前你选中的RadioButton的ID
    }

    @OnClick({R.id.friend_switch_hide_mine, R.id.friend_switch_hide_his})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.friend_switch_hide_mine:

                break;
            case R.id.friend_switch_hide_his:

                break;
        }
    }
}
