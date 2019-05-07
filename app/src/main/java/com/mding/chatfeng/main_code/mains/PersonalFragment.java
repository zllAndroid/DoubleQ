package com.mding.chatfeng.main_code.mains;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.FullImageActivity;
import com.mding.chatfeng.about_utils.GlideCacheUtil;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.OrangePocketActivity;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MineSetActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.mding.model.DataLogin;
import com.mding.model.DataMyZiliao;
import com.mding.model.HeadImgInfo;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.FullImageInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
//    @BindView(R.id.include_frag_img_search)
//    ImageView mineIvSearch;

    public PersonalFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(true);
    }

    private PopupWindow mNoticePopWindow;
    View view;
    int head;
    TextView tv_title;
    Unbinder unbinder;
    ACache aCache;
    public static String IMAGE_BASE64 = "headImageBase64";
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        if (view == null) {
//            view = inflater.inflate(R.layout.fragment_personal, container, false);
//        }
//        unbinder = ButterKnife.bind(this, view);
//        initUI();
//////圆形头像
//        return view;
//    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        view = getTopBarView();
        unbinder = ButterKnife.bind(this, view);
        aCache = ACache.get(getActivity());
        initUI();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
    public void onDataSynEvent(final HeadImgInfo headImgInfo) {
        imageBase64Event = headImgInfo.getHeadImgBase64();
//        ImageUtils.useBase64(getActivity(),mineIvPerson, imageBase64);
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
        setImgHead();
    }

//    public static Bitmap base64ToBitmap(String base64String) {
//
//        byte[] decode = Base64.decode(base64String, Base64.DEFAULT);
//
//        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
//
//        return bitmap;
//    }

    private void setImgHead() {
        String asString = aCache.getAsString(IMAGE_BASE64);

        if (StrUtils.isEmpty(asString))
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        else
        {
            imageBase64 = asString;
            ImageUtils.useBase64(getActivity(),mineIvPerson,asString);
            MyLog.e("PersonalFragment","无网时所传头像");
        }

//        if (!SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD) {
//            getHead();
//        }
    }

    private void initName() {
//        String name = SplitWeb.getSplitWeb().getName();
//        String sign = SplitWeb.getSplitWeb().getSign();
//        String phone = SplitWeb.getSplitWeb().getUserMobile();
//        if (!StrUtils.isEmpty(name))
//        {
//            mineTvName.setText(name);
//        }
//        if (!StrUtils.isEmpty(sign))
//        {
//            mineTvSign.setText(sign);
//        }
//        if (!StrUtils.isEmpty(phone)){
//            userPhone = phone;
//        }
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json)) {
            DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
//            DataLogin.RecordBean recordBean = dataLogin.getRecord();
            if (recordBean != null) {
                mineTvName.setText(recordBean.getNickName());
                userPhone = recordBean.getMobile();
                String signature = StrUtils.isEmpty(recordBean.getPersonaSignature()) ? "你还没有设置签名哦！" : recordBean.getPersonaSignature();
                mineTvSign.setText(signature);
                if (signature.equals("你还没有设置签名哦！")){
                    mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
                }
//                changeinfoTvCount.setText(dataLogin.getWxSno());
            }else
            {
                sendWeb(SplitWeb.getSplitWeb().personalCenter());
            }
        }else
        {
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
    }

    public  static  boolean isChange=false;
    public  static  boolean isChangeHead=false;

    @Override
    public void onResume() {
        super.onResume();
//        getHead("aCacheBase64");
        if (isChange)
        {
            initName();
        }
        if (isChangeHead)
        {
//            getHead();
            if (!imageBase64Event.equals("")){
                ImageUtils.useBase64(getActivity(),mineIvPerson,imageBase64Event);
                aCache.put(IMAGE_BASE64, imageBase64Event);
                MyLog.e("personalFragment changeinfo","eventBus不为空");
            }
        }
        if (SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD){
//            getHead();
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
        isChange=false;
        isChangeHead=false;
        SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD=false;

    }
    String userId;
    String userPhone;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "personalCenter":
                DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
                DataMyZiliao.RecordBean record= dataMyZiliao.getRecord();
                if (record!=null)
                {
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO,jsonString);
                    String headImg = record.getHeadImg();
//                    String substring = headImg.substring(22);
                    if (!StrUtils.isEmpty(headImg)){
                        imageBase64 = headImg;
                        ImageUtils.useBase64(getActivity(), mineIvPerson, headImg);
                        aCache.put(IMAGE_BASE64, headImg);
//                        ToastUtil.isDebugShow("无网时所传头像");
//                        MyLog.e("PersonalFragment","无网时所传头像");
                    }
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        if (unbinder!=null) {
            unbinder.unbind();
            unbinder=null;
        }
    }
    String imageBase64 = "";
    String imageBase64Event = "";
    //    ConfirmPopWindow confirmPopWindow=null;
    @OnClick({R.id.mine_iv_qrcode,R.id.mine_iv_person,R.id.include_frag_img_search, R.id.mine_lin_person_info,
            R.id.mine_lin_share, R.id.mine_lin_set,R.id.mine_lin_discover, R.id.mine_lin_orange_pocket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_iv_person:
                int location[] = new int[2];
                view.getLocationOnScreen(location);
                FullImageInfo fullImageInfo = new FullImageInfo();
                fullImageInfo.setLocationX(location[0]);
                fullImageInfo.setLocationY(location[1]);
                fullImageInfo.setWidth(view.getWidth());
                fullImageInfo.setHeight(view.getHeight());
                GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
//                String mPath= FilePath.getAbsPath(FilePath.appPath+ SplitWeb.getSplitWeb().getUserId()+"/")+"chatHead/";
//                List<String> fileName = FilePath.getFilesAllName(mPath);
//                if (fileName!=null&&fileName.size()>0)
//                {
//                    String path=fileName.get(fileName.size()-1);
//                    fullImageInfo.setImageUrl(path);
//                    EventBus.getDefault().postSticky(fullImageInfo);
////
////                    if (ChangeInfoActivity.imgWidth != 0 && ChangeInfoActivity.imgHeight != 0){
////                        Intent intent = new Intent(getActivity(), FullImageActivity.class);
////                        Bundle bundle = new Bundle();
////                        bundle.putString("imgWidth", String.valueOf(ChangeInfoActivity.imgWidth));
////                        bundle.putString("imgHeight", String.valueOf(ChangeInfoActivity.imgHeight));
////                        intent.putExtras(bundle);
////                        startActivity(intent);
////                    }else
//                    startActivity(new Intent(getActivity(), FullImageActivity.class));
//                    getActivity().overridePendingTransition(0, 0);
//                }
                if (!imageBase64Event.equals("")){
                    fullImageInfo.setImageBase64(imageBase64Event);
//                    ToastUtil.isDebugShow("imageBase64Event");
                }
                else{
                    fullImageInfo.setImageBase64(imageBase64);
                }
                EventBus.getDefault().postSticky(fullImageInfo);
                startActivity(new Intent(getActivity(), FullImageActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.include_frag_img_search:
                IntentUtils.JumpTo(SearchActivity.class);
                break;
//            case R.id.include_frag_img_add:
//                if (confirmPopWindow==null)
//                    confirmPopWindow = new ConfirmPopWindow(getActivity());
//                confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
////                showNoticePopWindow();
//                break;
            case R.id.mine_lin_person_info:
//                HeadImgInfo headImgInfo = new HeadImgInfo();
//                headImgInfo.setHeadImgBase64(imageBase64);
//                EventBus.getDefault().postSticky(headImgInfo);
                IntentUtils.JumpTo(ChangeInfoActivity.class);
                break;
            case R.id.mine_lin_share:
//                IntentUtils.JumpToHaveOne(MyAccountActivity.class,"userId",userId);
                IntentUtils.JumpTo(MyAccountActivity.class);
                break;
            case R.id.mine_iv_qrcode:
//                IntentUtils.JumpToHaveOne(MyAccountActivity.class,"userId",userId);
                IntentUtils.JumpTo(MyAccountActivity.class);
                break;
            case R.id.mine_lin_set:
//                Log.e("userPhone","-------------personal-----------------"+userPhone);
                IntentUtils.JumpToHaveOne(MineSetActivity.class,"phone",userPhone);
                break;
//            case R.id.mine_lin_test:
//                IntentUtils.JumpTo(TestActivity.class);
//                break;
            case R.id.mine_lin_discover:
                DialogUtils.showDialog("敬请期待！");
//                IntentUtils.JumpToHaveOne(MyDiscoverActivity.class,"userId",userId);
                break;
            case R.id.mine_lin_orange_pocket:
//                DialogUtils.showDialog("敬请期待！");
                IntentUtils.JumpToHaveOne(OrangePocketActivity.class,"userId",userId);
                break;
        }
    }
}
