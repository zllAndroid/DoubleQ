package com.mding.chatfeng.main_code.ui.about_discovery;

import android.os.Bundle;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;

import butterknife.BindView;

public class DongTuPlaySetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_dong_tu_play_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("动图播放设置");
    }

}
