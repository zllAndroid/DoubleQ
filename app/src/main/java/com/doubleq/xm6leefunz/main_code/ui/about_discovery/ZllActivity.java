//package com.doubleq.xm6leefunz.main_code.ui.about_discovery;
//
//import android.animation.Animator;
//import android.animation.ObjectAnimator;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.AccelerateInterpolator;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.alibaba.fastjson.JSON;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.doubleq.model.DataMyZiliao;
//import com.doubleq.model.find_friend.DataDiscoveryFriendCircle;
//import com.doubleq.xm6leefunz.R;
//import com.doubleq.xm6leefunz.about_base.BaseActivity;
//import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
//import com.doubleq.xm6leefunz.about_utils.HelpUtils;
//import com.doubleq.xm6leefunz.about_utils.about_file.FilePath;
//import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
//import com.doubleq.xm6leefunz.about_utils.about_immersive.StateBarUtils;
//import com.doubleq.xm6leefunz.about_utils.windowStatusBar;
//import com.doubleq.xm6leefunz.main_code.ui.about_discovery.about_discovery_fragment.DiscoveryFriendCircleAdapter;
//import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
//import com.race604.flyrefresh.FlyRefreshLayout;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;
//
//public class ZllActivity extends BaseActivity {
//
//    @BindView(R.id.friendcircle_recyc)
//    RecyclerView tvRecyclerView;
//    @BindView(R.id.fly_layout)
//    FlyRefreshLayout mFly;
//
//    String nickName;
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_zll;
//    }
//
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        tvRecyclerView.setHasFixedSize(true);
//        tvRecyclerView.setNestedScrollingEnabled(false);
//        tvRecyclerView.setLayoutManager(new GridLayoutManager(ZllActivity.this, 1));
//        initData();
//        initFly();
//    }
//
//    @Override
//    protected void initBeforeContentView() {
//        super.initBeforeContentView();
//        StateBarUtils.setFullscreen(this,true,false);
////            设置状态栏的颜色
//        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.trans), 255);
//    }
//
//    private void initFly() {
//        mFly.setOnPullRefreshListener(new FlyRefreshLayout.OnPullRefreshListener() {
//            @Override
//            public void onRefresh(FlyRefreshLayout flyRefreshLayout) {
//                View child = tvRecyclerView.getChildAt(0);
//                if (child != null) {
//                    bounceAnimateView(child.findViewById(R.id.icon));
//                }
//
//                new AsyncTask<Void,Void,Void>(){
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        super.onPostExecute(aVoid);
//                        ToastUtil.show("刷新完成");
//                        mFly.onRefreshFinish();
//                    }
//
//                }.execute();
//            }
//
//
//            @Override
//            public void onRefreshAnimationEnd(FlyRefreshLayout flyRefreshLayout) {
//                System.out.println("Refresh end");
//            }
//        });
//    }
//    private void bounceAnimateView(View view) {
//        if (view == null) {
//            return;
//        }
//
//        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
//        swing.setDuration(400);
//        swing.setInterpolator(new AccelerateInterpolator());
//        swing.start();
//    }
//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }
//
//    @Override
//    protected boolean isGones() {
//        return false;
//    }
//
//    List<DataDiscoveryFriendCircle> frndccList = new ArrayList<>();
//    DiscoveryFriendCircleAdapter discoveryFriendCircleAdapter = null;
//
//    private void initData() {
//        sendWeb(SplitWeb.personalCenter());
////        discoverTvName.setText(nickName);
//        DataDiscoveryFriendCircle dataDiscoveryFriendCircle = new DataDiscoveryFriendCircle();
//        dataDiscoveryFriendCircle.setImg_head("http://pbmyhukzs.bkt.clouddn.com/Conan.png");
//        dataDiscoveryFriendCircle.setTv_name("Conan");
//        dataDiscoveryFriendCircle.setTv_words("真相永远只有一个！勿忘初心，内心要和水一样清澈。");
//        dataDiscoveryFriendCircle.setImg_pic("http://pbmyhukzs.bkt.clouddn.com/background_vertical.png");
//
//        DataDiscoveryFriendCircle dataDiscoveryFriendCircle2 = new DataDiscoveryFriendCircle();
//        dataDiscoveryFriendCircle2.setImg_head("http://pbmyhukzs.bkt.clouddn.com/img_personal_head2.png");
//        dataDiscoveryFriendCircle2.setTv_name("Amy");
//        dataDiscoveryFriendCircle2.setTv_words("只要坚定脚步，成功就在眼前，就在旭日在天空升起的那刻。");
//        dataDiscoveryFriendCircle2.setImg_pic("http://pbmyhukzs.bkt.clouddn.com/background5_vertical.png");
//
//        for (int i = 0; i < 3; i++) {
//            frndccList.add(dataDiscoveryFriendCircle);
//            frndccList.add(dataDiscoveryFriendCircle2);
//            frndccList.add(dataDiscoveryFriendCircle);
//            frndccList.add(dataDiscoveryFriendCircle2);
//        }
//
//        discoveryFriendCircleAdapter = new DiscoveryFriendCircleAdapter(frndccList, ZllActivity.this);
//        tvRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        tvRecyclerView.setAdapter(discoveryFriendCircleAdapter);
//        discoveryFriendCircleAdapter.notifyDataSetChanged();
//        discoveryFriendCircleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            }
//        });
//        discoveryFriendCircleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(ZllActivity.this, "我是item", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}
