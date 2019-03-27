package com.mding.chatfeng.main_code.ui.about_contacts;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;

import butterknife.BindView;

public class DongTaiSetActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.radioBtn_one)
    RadioButton radioBtnOne;
    @BindView(R.id.radioBtn_two)
    RadioButton radioBtnTwo;
    @BindView(R.id.radioBtn_thr)
    RadioButton radioBtnThr;
    @BindView(R.id.share_radioGroup)
    RadioGroup shareRadioGroup;

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
//        includeTopTvTital.setText("动态设置");
        radioBtnOne.setChecked(true);
//        radiogroup本身有监听的方法可以直接设置监听，这个监听需要一个回调接口OnCheckedChangeListener，这个接口里面的回调方法给我们返回了两个参数其中int型的参数就是当前你选中的RadioButton的ID
        shareRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkId就是当前选中的RadioButton
                switch (checkedId){
                    case R.id.radioBtn_one:

//                        ToastUtil.show("自动播放");
                        break;
                    case R.id.radioBtn_two:
//                        ToastUtil.show("WIFI下自动播放");
                        break;
                    case R.id.radioBtn_thr:
//                        ToastUtil.show("4G自动播放");
                        break;
                }
            }
        });
    }

}
