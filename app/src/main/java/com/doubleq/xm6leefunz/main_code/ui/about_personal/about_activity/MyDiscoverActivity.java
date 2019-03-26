package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doubleq.model.DataMyZiliao;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_custom.about_top_bar.TopBarLayout;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.FilePath;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MyDiscoverActivity extends BaseActivity {

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_discover);
//    }

    String userId;
    @BindView(R.id.top_bar)
    TopBarLayout topBar;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_background_discover)
    LinearLayout includeTopLinBackgroundDiscover;
    @BindView(R.id.include_discover_iv_head)
    ImageView includeDiscoverIvHead;
    @BindView(R.id.include_discover_tv_name)
    TextView includeDiscoverTvName;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_my_discover;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setVisibility(View.INVISIBLE);
//        includeTopLinBackgroundDiscover.setVisibility(View.INVISIBLE);
        topBar.setVisibility(View.GONE);
//        topBar.setRightImgDrawable(R.drawable.discover_my_comment);
//        topBar.setOnBackClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");

        }
        sendWeb(SplitWeb.personalCenter());
        getHead();
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @Override
    protected boolean isGonesStatus() {
        return true;
    }

    private void getHead() {
        String userNewHead = FilePath.getUserNewHead(this);
        if (!StrUtils.isEmpty(userNewHead)) {
//            discoverIvHead.setImageURI(Uri.fromFile(new File(userNewHead)));
            Glide.with(this).load(Uri.fromFile(new File(userNewHead)))
                    .bitmapTransform(new CropCircleTransformation(MyDiscoverActivity.this))
//                        .thumbnail(0.1f)
                    .into(includeDiscoverIvHead);
        }
//        GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
//        List<String> fileName = FilePath.getFilesAllName(FilePath.myHeadImg);
//        if (fileName!=null&&fileName.size()>0)
//        {
//            String path=fileName.get(fileName.size()-1);
//            Glide.with(this).load(path)
//                    .bitmapTransform(new CropCircleTransformation(getActivity()))
////                        .thumbnail(0.1f)
//                    .into(mineIvPerson);
//        }
        else {
            sendWeb(SplitWeb.personalCenter());
        }
    }

    String nickName;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "personalCenter":
                DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
                DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
                if (record != null) {
                    List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath() + "chatHead/");
                    if (fileName != null && fileName.size() > 0) {
                    } else {
                        String headImg = record.getHeadImg();
                        if (!StrUtils.isEmpty(headImg))
                            Glide.with(this)
                                    .load(headImg)
                                    .downloadOnly(new SimpleTarget<File>() {
                                        @Override
                                        public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                            File file = HeadFileUtils.saveHeadPath(MyDiscoverActivity.this, resource);
                                            Glide.with(MyDiscoverActivity.this).load(file)
                                                    .bitmapTransform(new CropCircleTransformation(MyDiscoverActivity.this))
//                            .thumbnail(0.1f)
                                                    .into(includeDiscoverIvHead);
                                        }
                                    });
                    }

                    nickName = record.getNickName();
                    includeDiscoverTvName.setText(nickName);
                    Log.e("discover", "------------------------------------nickName = " + nickName);
                }
                break;
        }
    }

    @OnClick(R.id.include_top_lin_newback_discover)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity(this);
    }
}
