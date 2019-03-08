package com.doubleq.xm6leefunz.main_code.ui.about_discovery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataMyZiliao;
import com.doubleq.model.find_friend.DataDiscoveryFriendCircle;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.FilePath;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_discovery.about_discovery_fragment.DiscoveryFriendCircleAdapter;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class FriendCircleActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.friendcircle_recyc)
    RecyclerView tvRecyclerView;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_lin_newback)
    LinearLayout includeTopLinNewback;
    @BindView(R.id.include_top_lin_right)
    LinearLayout includeTopLinRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLinBack;
    @BindView(R.id.include_discover_iv_head)
    ImageView includeDiscoverIvHead;
    @BindView(R.id.include_discover_tv_name)
    TextView includeDiscoverTvName;

    String nickName;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_circle;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("朋友圈");
        includeTopTvTital.setBackgroundColor(getResources().getColor(R.color.trans));
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.trans));
        includeTopLinNewback.setBackgroundColor(getResources().getColor(R.color.trans));
        includeTopLinRight.setBackgroundColor(getResources().getColor(R.color.trans));
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.discover_publish);
        tvRecyclerView.setHasFixedSize(true);
        tvRecyclerView.setNestedScrollingEnabled(false);
        tvRecyclerView.setLayoutManager(new GridLayoutManager(FriendCircleActivity.this, 1));
        getHead();
        initData();

    }

    private void getHead() {
        String userNewHead = FilePath.getUserNewHead(this);
        if (!StrUtils.isEmpty(userNewHead)) {
//            discoverIvHead.setImageURI(Uri.fromFile(new File(userNewHead)));
            Glide.with(this).load(Uri.fromFile(new File(userNewHead)))
                    .bitmapTransform(new CropCircleTransformation(FriendCircleActivity.this))
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

    List<DataDiscoveryFriendCircle> frndccList = new ArrayList<>();
    DiscoveryFriendCircleAdapter discoveryFriendCircleAdapter = null;

    private void initData() {
        sendWeb(SplitWeb.personalCenter());
//        discoverTvName.setText(nickName);
        DataDiscoveryFriendCircle dataDiscoveryFriendCircle = new DataDiscoveryFriendCircle();
        dataDiscoveryFriendCircle.setImg_head("http://pbmyhukzs.bkt.clouddn.com/Conan.png");
        dataDiscoveryFriendCircle.setTv_name("Conan");
        dataDiscoveryFriendCircle.setTv_words("真相永远只有一个！勿忘初心，内心要和水一样清澈。");
        dataDiscoveryFriendCircle.setImg_pic("http://pbmyhukzs.bkt.clouddn.com/background_vertical.png");

        DataDiscoveryFriendCircle dataDiscoveryFriendCircle2 = new DataDiscoveryFriendCircle();
        dataDiscoveryFriendCircle2.setImg_head("http://pbmyhukzs.bkt.clouddn.com/img_personal_head2.png");
        dataDiscoveryFriendCircle2.setTv_name("Amy");
        dataDiscoveryFriendCircle2.setTv_words("只要坚定脚步，成功就在眼前，就在旭日在天空升起的那刻。");
        dataDiscoveryFriendCircle2.setImg_pic("http://pbmyhukzs.bkt.clouddn.com/background5_vertical.png");

        for (int i = 0; i < 3; i++) {
            frndccList.add(dataDiscoveryFriendCircle);
            frndccList.add(dataDiscoveryFriendCircle2);
            frndccList.add(dataDiscoveryFriendCircle);
            frndccList.add(dataDiscoveryFriendCircle2);
        }

        discoveryFriendCircleAdapter = new DiscoveryFriendCircleAdapter(frndccList, FriendCircleActivity.this);
        tvRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        tvRecyclerView.setAdapter(discoveryFriendCircleAdapter);
        discoveryFriendCircleAdapter.notifyDataSetChanged();
        discoveryFriendCircleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        discoveryFriendCircleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(FriendCircleActivity.this, "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                                            File file = HeadFileUtils.saveHeadPath(FriendCircleActivity.this, resource);
                                            Glide.with(FriendCircleActivity.this).load(file)
                                                    .bitmapTransform(new CropCircleTransformation(FriendCircleActivity.this))
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
}
