package com.mding.chatfeng.main_code.ui.about_contacts;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_add.AddGoodFriendActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.mding.model.DataMyFriend;
import com.mding.model.DataScanFirendRequest;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：好友资料界面  (添加)  还未添加的页面
 */
public class FriendDataAddActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.data_iv_head)
    ImageView mIvHead;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fda_tv_name)
    TextView mTvName;
    @BindView(R.id.fda_tv_num)
    TextView fdaTvNum;
    @BindView(R.id.fda_tv_sign)
    TextView fdaTvSign;
    @BindView(R.id.data_iv_qrcode)
    ImageView dataIvQrcode;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
//    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;
    String type = "1";
//    public static final String FRIENG_ID_KEY = "friendId";

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("好友资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.GONE);
//        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        Intent intent = getIntent();
        if (intent != null) {
//            String friendId = intent.getStringExtra(FRIENG_ID_KEY);
            dataSearch = (DataSearch) intent.getSerializableExtra("dataSearch");
            if (dataSearch != null) {
                mTvName.setText(dataSearch.getName());
                fdaTvNum.setText(dataSearch.getSno());
                fdaTvSign.setText(StrUtils.isEmpty(dataSearch.getSign()) ? "暂未设置签名" : dataSearch.getSign());
                Glide.with(this).load(dataSearch.getHeadImg())
                        .bitmapTransform(new CropCircleTransformation(FriendDataAddActivity.this))
                        .into(mIvHead);
            } else {
                String id = intent.getStringExtra("id");
                Log.e("scan_id", "-------------------------" + id);
                sendWebHaveDialog(SplitWeb.addFriendQrCode(id), "搜索好友信息中...", "获取成功");
//                sendWebHaveDialog(SplitWeb.getFriendInfo(id),"搜索好友信息中...","获取成功");
            }
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            //            获取好友数据
            case "getFriendInfo":
                initDataFriend(responseText);
                break;
            case "addFriendQrCode":
                DataScanFirendRequest dataScanFirendRequest = JSON.parseObject(responseText, DataScanFirendRequest.class);
                DataScanFirendRequest.RecordBean record = dataScanFirendRequest.getRecord();
                if (record != null) {
                    mTvName.setText(record.getNickName());
                    fdaTvNum.setText(record.getWxSno());
                    String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未签名" : record.getPersonaSignature();
                    fdaTvSign.setText(signText);
                    Glide.with(this).load(record.getHeadImg())
                            .bitmapTransform(new CropCircleTransformation(FriendDataAddActivity.this))
                            .into(mIvHead);

                    dataSearch = new DataSearch();
                    dataSearch.setSno(record.getWxSno());
                    dataSearch.setName(record.getNickName());
                    dataSearch.setId(record.getFriendId());
                    dataSearch.setHeadImg(record.getHeadImg());
                }
                break;
        }

    }

//    DataMyFriend.RecordBean dataRecord;

    private void initDataFriend(String responseText) {

        DataMyFriend dataMyFriend = JSON.parseObject(responseText, DataMyFriend.class);

        DataMyFriend.RecordBean record = dataMyFriend.getRecord();
        if (record != null) {
//            dataRecord = record;
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataAddActivity.this))
                    .into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdaTvSign.setText(signText);
//            fdaTvFenzu.setText(record.getGroupName() + "");
            fdaTvNum.setText(record.getWxSno());
            mTvName.setText(record.getNickName());
            String beizhuText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : record.getRemarkName();
//            mTvName.setText(nameText);
//            fdTvBeizhu.setText(beizhuText);
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_data_add;
    }

    @OnClick({R.id.include_top_iv_more, R.id.data_iv_qrcode, R.id.data_iv_head, R.id.fda_tv_send_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            顶部点点点按钮
            case R.id.include_top_iv_more:
                break;
//                二维码按钮
            case R.id.data_iv_qrcode:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataSearch != null) {
                        PersonData personData = new PersonData();
                        personData.setHeadImg(dataSearch.getHeadImg());
                        personData.setName(dataSearch.getName());
                        personData.setScanTital("扫一扫,添加" + dataSearch.getName() + "为好友");
                        personData.setTital("好友二维码");

                        if (dataSearch.getId() != null) {
                            String string = type + "_xm6leefun_" + dataSearch.getId();
                            Log.e("qrcode", "----------FriendDataAddActivity--------------" + string);
                            personData.setQrCode(string);
                        }
                        IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                    }
                }
                break;
//                头像按钮
            case R.id.data_iv_head:
                break;
//                发送消息
            case R.id.fda_tv_send_msg:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpToHaveObj(AddGoodFriendActivity.class, AddGoodFriendActivity.DataKey, dataSearch);
                break;
        }
    }
}
