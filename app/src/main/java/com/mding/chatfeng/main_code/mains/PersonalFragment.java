package com.mding.chatfeng.main_code.mains;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.ShowFullImgActivity;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MineSetActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.OrangePocketActivity;
import com.mding.model.DataMyZiliao;
import com.mding.model.HeadImgInfo;
import com.mding.model.PersonInfo;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之个人中心页面
 * 作者：zll
 * 修改者：ljj
 */
public class PersonalFragment extends BaseFragment {

    @BindView(R.id.include_frag_tv_title)
    TextView includeFragTvTitle;
    @BindView(R.id.mine_tv_name)
    TextView mineTvName;
    @BindView(R.id.mine_tv_sign)
    TextView mineTvSign;
    @BindView(R.id.mine_iv_person)
    ImageView mineIvPerson;
    @BindView(R.id.include_frag_img_add)
    ImageView mineIvAdd;
    @BindView(R.id.mine_lin_share)
    CusLinearLayout mineLinShare;
    @BindView(R.id.mine_lin_set)
    CusLinearLayout mineLinSet;
    @BindView(R.id.mine_lin_orange_pocket)
    CusLinearLayout mineLinOrangePocket;

    public PersonalFragment() {
    }

    ACache aCache;
    public static final String IMAGE_BASE64 = "imgBase64";

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_personal;
    }

    boolean isFirst = true;

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        if (aCache == null)
            aCache = ACache.get(getActivity());
        if (isFirst)
            initUI();
        isFirst = false;
        // 名字加粗
        mineTvName.getPaint().setFakeBoldText(true);
        initLinearLayout();
    }

    private void initLinearLayout() {
        // 分享
        initShare();
        // 设置
        initSet();
        // 橙子口袋
        initOrangePocket();
    }

    private void initShare() {
        mineLinShare.setViewLineVisible(false);
        mineLinShare.setImgLogo(getResources().getDrawable(R.drawable.mine_share));
        mineLinShare.setTvTitle("分享");
    }
    private void initSet() {
        mineLinSet.setImgLogo(getResources().getDrawable(R.drawable.mine_set));
        mineLinSet.setTvTitle("设置");
    }

    private void initOrangePocket() {
        mineLinOrangePocket.setLinGreyBacVisible(true);
        mineLinOrangePocket.setViewLineVisible(false);
        mineLinOrangePocket.setImgLogo(getResources().getDrawable(R.drawable.mine_orange_pocket));
        mineLinOrangePocket.setTvTitle("橙子口袋");
    }

    //    修改头像
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(final HeadImgInfo headImgInfo) {
        totalBase64Event = headImgInfo.getHeadImgBase64();
        ImageUtils.useBase64(getActivity(), mineIvPerson, totalBase64Event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSign(final PersonInfo personInfo) {
        String name = personInfo.getNickName();
        String sign = personInfo.getSign();
        if (!StrUtils.isEmpty(name))
            mineTvName.setText(name);
        if (!StrUtils.isEmpty(sign)) {
            String signature = StrUtils.isEmpty(sign) ? "快来设置您的个性签名吧" : sign;
            mineTvSign.setText(signature);
            if (signature.equals("快来设置您的个性签名吧")) {
                mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
            } else {
                mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
            }
        }
    }

    @Override
    protected String setFragmentTital() {
        return "个人中心";
    }

    @Override
    protected int setTopBarBackground() {
        return getContext().getResources().getColor(R.color.app_theme);
    }

    private void initUI() {
        initName();
    }

    private void initName() {

        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        Log.e("DataMyZiliao", "---个人中心DataMyZiliao----");
        if (!StrUtils.isEmpty(json)) {
            DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
            if (recordBean != null) {
                String headImg = recordBean.getHeadImg();
                ImageUtils.useBase64(getActivity(), mineIvPerson, headImg);
                aCache.put(IMAGE_BASE64, headImg);
                mineTvName.setText(recordBean.getNickName());
                userPhone = recordBean.getMobile();
                String signature = StrUtils.isEmpty(recordBean.getPersonaSignature()) ? "快来设置您的个性签名吧" : recordBean.getPersonaSignature();
                mineTvSign.setText(signature);
                if (signature.equals("快来设置您的个性签名吧")) {
                    mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
                } else {
                    mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
                }
            } else {
                sendWeb(SplitWeb.getSplitWeb().personalCenter());
            }
        } else {
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
    }

    public static boolean isChange = false;
    public static boolean isChangeHead = false;
    String userId;
    String userPhone;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "personalCenter":
                DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
                DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
                if (record != null) {
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                    String headImg = record.getHeadImg();
                    if (!StrUtils.isEmpty(headImg)) {
                        totalBase64 = headImg;
                        ImageUtils.useBase64(getActivity(), mineIvPerson, headImg);
                        aCache.put(IMAGE_BASE64, headImg);
                    }
                    mineTvName.setText(record.getNickName());
                    userPhone = record.getMobile();
                    String signature = StrUtils.isEmpty(record.getPersonaSignature()) ? "你还没有设置签名哦！" : record.getPersonaSignature();
                    mineTvSign.setText(signature);
                    if (signature.equals("你还没有设置签名哦！")) {
                        mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
                    } else {
                        mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
                    }
                }
                break;
        }
    }

    String totalBase64;
    String totalBase64Event;

    @OnClick({R.id.mine_iv_qrcode, R.id.mine_iv_person, R.id.include_frag_img_search, R.id.mine_lin_person_info,
            R.id.mine_lin_share, R.id.mine_lin_set, R.id.mine_lin_discover, R.id.mine_lin_orange_pocket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_iv_person:
                jumpBigImg();
                break;
            case R.id.include_frag_img_search:
                IntentUtils.JumpTo(SearchActivity.class);
                break;
            case R.id.mine_lin_person_info:
                IntentUtils.JumpTo(ChangeInfoActivity.class);
                break;
            case R.id.mine_lin_share:
                IntentUtils.JumpTo(MyAccountActivity.class);
                break;
            case R.id.mine_iv_qrcode:
                IntentUtils.JumpTo(MyAccountActivity.class);
                break;
            case R.id.mine_lin_set:
                IntentUtils.JumpToHaveOne(MineSetActivity.class, "phone", userPhone);
                break;
            case R.id.mine_lin_discover:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.mine_lin_orange_pocket:
                IntentUtils.JumpToHaveOne(OrangePocketActivity.class, "userId", userId);
                break;
        }
    }

    private void jumpBigImg() {
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json)) {
            DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
            if (recordBean != null) {
                IntentUtils.JumpToHaveOne(ShowFullImgActivity.class, IMAGE_BASE64, recordBean.getHeadImg());
            }
        }
    }
}
