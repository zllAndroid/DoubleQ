package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.add_friend.FenZuFriendPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请入群后进入确认发送页面
 */
public class AddGoodGroupActivity extends BaseActivity {

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
//    @BindView(R.id.fda_ed_beizhu)
//    EditText fdaEdBeizhu;
    @BindView(R.id.fda_tv_group)
    TextView fdaTvGroup;
    @BindView(R.id.fda_lin_main)
    LinearLayout mLinMain;

    public static String DataKey = "addgroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    DataSearch dataSearch=null;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("验证信息");
        incluTvRight.setVisibility(View.VISIBLE);
        incluTvRight.setText("发送");
        Intent intent = getIntent();
        if (intent != null) {
             dataSearch = (DataSearch) intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
//            dataSearch.getHead_img()
            if (dataSearch==null)
                return;
            Glide.with(this).load(dataSearch.getHeadImg()).error(R.drawable.mine_head).into(fdaIvHead);
            fdaTvName.setText(dataSearch.getName());
        }
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_group_send;
    }
//点击发送
    @OnClick(R.id.inclu_tv_right)
    public void onSend() {
        ToastUtil.show("我点击了发送");
        String yanzheng = fdaEdYanzheng.getText().toString().trim();
//        String remark = fdaEdBeizhu.getText().toString().trim();
        sendWeb(SplitWeb.addGroupOf(dataSearch.getSno(),yanzheng));
    }
//点击分组
    @OnClick(R.id.fda_tv_group)
    public void onGroup() {
        sendWeb(SplitWeb.groupManageInfo("1"));
    }
    List<DataGroupManage.RecordBean.GroupInfoBean> group_info;
    String ids=null;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataGroupManage dataGroupManage = JSON.parseObject(responseText, DataGroupManage.class);
                DataGroupManage.RecordBean record = dataGroupManage.getRecord();
                group_info = record.getGroupInfo();

                if (record != null && group_info.size() > 0) {
//                    FenZuFriendPopWindow
                    FenZuFriendPopWindow changeInfoWindowsign = new FenZuFriendPopWindow(AddGoodGroupActivity.this, group_info);
                    changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
                    changeInfoWindowsign.setOnClickWhoListener(new FenZuFriendPopWindow.OnClickWhoListener() {
                        @Override
                        public void WhoListener(String msg, String id) {
                            fdaTvGroup.setText(msg);
                            ids=id;
                        }

                    });
                }
                break;
            case "addGroupOf":
                DialogUtils.showDialog("申请入群成功");
                break;
            default:
                break;
        }
    }
}
