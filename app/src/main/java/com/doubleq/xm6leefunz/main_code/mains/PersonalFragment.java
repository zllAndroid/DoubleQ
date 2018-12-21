package com.doubleq.xm6leefunz.main_code.mains;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doubleq.model.DataMyZiliao;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.FullImageActivity;
import com.doubleq.xm6leefunz.about_utils.about_file.FilePath;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.GlideCacheUtil;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.main_code.about_login.FirstAddHeaderActivity;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.ConfirmPopWindow;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MineSetActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomDialog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.enity.FullImageInfo;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

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

    private PopupWindow mNoticePopWindow;
    View view;
    int head;
    TextView tv_title;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_personal, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        initUI();
////圆形头像
        return view;
    }
    private void initUI() {
        includeFragTvTitle.setText("个人中心");

//        if (!StrUtils.isEmpty(SplitWeb.NICK_NAME))
//        {
//            mineTvName.setText(SplitWeb.NICK_NAME);
//        }
//        if (!StrUtils.isEmpty(SplitWeb.PERSON_SIGN))
//        {
//            mineTvSign.setText(SplitWeb.PERSON_SIGN);
//        }
//        getHead();
//        Glide.with(this).load(record.getHead_img())
//                .bitmapTransform(new CropCircleTransformation(getActivity()))
//                .crossFade(1000).into(mineIvPerson);
//        Glide.with(this).load(R.drawable.login_bac)
////                    .asBitmap()
//                .bitmapTransform(new CropCircleTransformation(getActivity()))
//                .thumbnail(0.1f)
//                .into(mineIvPerson);


//            Glide.with(getActivity()).load(FilePath.getHeadPath())
//                    .bitmapTransform(new CropCircleTransformation(getActivity())
//                    .crossFade(1000).into(mineIvPerson);
    }
    @Override
    public void onResume() {
        super.onResume();
//        if (!StrUtils.isEmpty(FilePath.getHeadPath()))
//        mineIvPerson.setBackgroundResource(0);
//        Glide.get(getActivity()).clearMemory();//清理内存缓存 可以在UI主线程中进行
        getHead();

        sendWeb(SplitWeb.personalCenter());
    }

    private void getHead() {
        GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
        List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath()+"chatHead/");
            if (fileName!=null&&fileName.size()>0)
            {
                String path=fileName.get(fileName.size()-1);
                Glide.with(this).load(path)
                        .bitmapTransform(new CropCircleTransformation(getActivity()))
                        .thumbnail(0.1f)
                        .into(mineIvPerson);
            }else
            {
                Glide.with(this).load(R.drawable.first_head_nor)
                        .bitmapTransform(new CropCircleTransformation(getActivity()))
                        .thumbnail(0.1f)
                        .into(mineIvPerson);
            }
    }

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
                    if (record.getNickName()!=null)
                        mineTvName.setText(record.getNickName());

//                    String signText=StrUtils.isEmpty(record.getPersonaSignature())?"暂未签名":"";
                    if (StrUtils.isEmpty(record.getPersonaSignature()))
                    {
                        mineTvSign.setHint("暂未签名");
                    }else {
                        mineTvSign.setText(record.getPersonaSignature());
                    }
//                    GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
                    List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath()+"chatHead/");
                    if (fileName!=null&&fileName.size()>0)
                    {
                    }else
                    {
                        String headImg = record.getHeadImg();
                        if (!StrUtils.isEmpty(headImg))
                            Glide.with(this)
                                    .load(headImg)
                                    .downloadOnly(new SimpleTarget<File>() {
                                        @Override
                                        public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                            File file = HeadFileUtils.saveHeadPath(getActivity(), resource);
                                        }
                                    });
                        Glide.with(this).load(record.getHeadImg())
                            .bitmapTransform(new CropCircleTransformation(getActivity()))
                            .thumbnail(0.1f)
                            .into(mineIvPerson);
                    }
//                    if (StrUtils.isEmpty(FilePath.getHeadPath()))
//                    Glide.with(this).load(record.getHeadImg())
//                            .bitmapTransform(new CropCircleTransformation(getActivity()))
//                            .thumbnail(0.1f)
//                            .into(mineIvPerson);


                    SplitWeb.USER_HEADER=record.getHeadImg();
                    SPUtils.put(getActivity(),"header",record.getHeadImg());
                    SPUtils.put(getActivity(),"name",record.getNickName());
                }
                break;
        }
    }

    private void showNoticePopWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_notice_popwindow, null);
        mNoticePopWindow = new PopupWindow(contentView);
        mNoticePopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mNoticePopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_vibration = contentView.findViewById(R.id.frag_tv_popwindow_vibration);
        TextView tv_voice = contentView.findViewById(R.id.frag_tv_popwindow_voice);
        TextView tv_del_group = contentView.findViewById(R.id.frag_tv_popwindow_del_group);
        tv_vibration.setOnClickListener(this);
        tv_voice.setOnClickListener(this);
        tv_del_group.setOnClickListener(this);

        mNoticePopWindow.setBackgroundDrawable(new ColorDrawable());
        mNoticePopWindow.setOutsideTouchable(true);
        mNoticePopWindow.showAsDropDown(mineIvAdd);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.frag_tv_popwindow_vibration: {
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(), "点击了“震动”", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_voice: {
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(), "点击了“声音”", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_del_group: {
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(), "点击了“删除群组”", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.mine_iv_person,R.id.include_frag_img_search, R.id.include_frag_img_add, R.id.mine_lin_person_info, R.id.mine_lin_share, R.id.mine_lin_set})
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
                List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath()+"chatHead/");
                if (fileName!=null&&fileName.size()>0)
                {
                    String path=fileName.get(fileName.size()-1);
                    fullImageInfo.setImageUrl(path);
                    EventBus.getDefault().postSticky(fullImageInfo);
                    startActivity(new Intent(getActivity(), FullImageActivity.class));
                    getActivity().overridePendingTransition(0, 0);
                }

                break;
            case R.id.include_frag_img_search:
                IntentUtils.JumpTo(SearchActivity.class);
                break;
            case R.id.include_frag_img_add:
                new ConfirmPopWindow(getActivity()).showAtBottom(view.findViewById(R.id.include_frag_img_add));
//                showNoticePopWindow();
                break;
            case R.id.mine_lin_person_info:
                IntentUtils.JumpTo(ChangeInfoActivity.class);
                break;
            case R.id.mine_lin_share:
//                IntentUtils.JumpTo(MyAccountActivity.class);
                IntentUtils.JumpTo(MyAccountActivity.class);
//                startActivity(new Intent(getActivity(), MyAccountActivity.class));
                break;
            case R.id.mine_lin_set:
                IntentUtils.JumpTo(MineSetActivity.class);
                break;
        }
    }
}
