package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubleq.model.DataFriendGroup;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.FriendGroupListActivity;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 添加好友发送界面
 */
public class AddGoodFriendActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fda_iv_head)
    ImageView fdaIvHead;
    @BindView(R.id.fda_tv_name)
    TextView fdaTvName;
    @BindView(R.id.fda_ed_yanzheng)
    EditText fdaEdYanzheng;
    @BindView(R.id.fda_ed_beizhu)
    EditText fdaEdBeizhu;
    @BindView(R.id.fda_tv_group)
    TextView fdaTvGroup;
    @BindView(R.id.fda_lin_main)
    LinearLayout mLinMain;
    @BindView(R.id.fda_tv_count_yanzheng)
    TextView fdaTvCountYanzheng;
    @BindView(R.id.fda_tv_count_remark)
    TextView fdaTvCountRemark;

    public static String DataKey = "addfriend";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("添加好友");

        incluTvRight.setVisibility(View.VISIBLE);
        incluTvRight.setText("发送");
        Intent intent = getIntent();
        if (intent != null) {
            dataSearch = (DataSearch) intent.getSerializableExtra(DataKey);
//            dataSearch.getHead_img()
            if (dataSearch == null)
                return;
            Glide.with(this).load(dataSearch.getHeadImg())
                    .error(R.drawable.mine_head)
                    .bitmapTransform(new CropCircleTransformation(AddGoodFriendActivity.this))
                    .into(fdaIvHead);
            fdaTvName.setText(dataSearch.getName());
        }

        fdaEdYanzheng.addTextChangedListener(textWatcher_yanzheng);
        fdaEdBeizhu.addTextChangedListener(textWatcher_remark);
    }

    TextWatcher textWatcher_yanzheng = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            fdaEdYanzheng.setSelection(fdaEdYanzheng.getText().toString().length());
            if (editable.length() > 20) {
                editable.delete(20, editable.length());
            }
            fdaTvCountYanzheng.setText(editable.length()+"");
        }
    };

    TextWatcher textWatcher_remark = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            fdaEdBeizhu.setSelection(fdaEdBeizhu.getText().toString().length());
            if (editable.length() > 10) {
                editable.delete(10, editable.length());
            }
            fdaTvCountRemark.setText(editable.length()+"");
        }
    };
    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_good_friend;
    }

    //点击发送
    @OnClick(R.id.inclu_tv_right)
    public void onSend() {
        String yanzheng = fdaEdYanzheng.getText().toString().trim();
        String remark = fdaEdBeizhu.getText().toString().trim();
        if (yanzheng.length() <= 20 && remark.length() <= 10)
            sendWeb(SplitWeb.addFriend(dataSearch.getSno(), ids, yanzheng, remark));
    }

    //点击分组
    @OnClick(R.id.fda_lin_group)
    public void onGroup() {
        Intent intent = new Intent(this, FriendGroupListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.KEY_FRIEND_GROUP, AppConfig.VALUE_FRIEND);
        intent.putExtras(bundle);
        startActivityForResult(intent, AppConfig.FRIEND_ADD_GROUP_REQUEST);
    }

    List<DataFriendGroup.RecordBean.GroupInfoBean> group_info;
    String ids = "";

    @SuppressLint("RestrictedApi")
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addFriend":
//                DialogUtils.showDialogOne("添加好友发送成功，请等待对方同意", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity(AddGoodFriendActivity.this);
//                    }
//                });
                ToastUtil.show("添加好友发送成功，请等待对方同意");
                AppManager.getAppManager().finishActivity(AddGoodFriendActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.FRIEND_ADD_GROUP_RESULT) {
            if (requestCode == AppConfig.FRIEND_ADD_GROUP_REQUEST) {
//            if (requestCode == AppConfig.FRIEND_ADD_GROUP_REQUEST) {
                String name = data.getStringExtra(FriendGroupListActivity.CHOOSE_NAME);
                String id = data.getStringExtra(FriendGroupListActivity.CHOOSE_ID);
                fdaTvGroup.setText(name);
                ids = id;
                //设置结果显示框的显示数值
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
