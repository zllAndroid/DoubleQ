package com.doubleq.xm6leefunz.main_code.about_fragment.about_discovery;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.find_friend.DataDiscoveryFriendCircle;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_discovery.about_discovery_fragment.DiscoveryFriendCircleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FriendCircleActivity extends BaseActivity {

//    RecyclerView tvRecyclerView;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.friendcircle_recyc)
    RecyclerView tvRecyclerView;

    //    RecyclerView vdRecyclerView;
//    ImageView img_comments;
//    EditText et_comments;
//    TextView tv_comments;
//    String write_comments;
//    LinearLayout layout_item;
//    View view;
//    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_friend_circle);
//        tvRecyclerView = findViewById(R.id.friendcircle_recyc);
//        ImageView img_back;
//        img_back = findViewById(R.id.img_fcircle_back);
//        img_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                AppManaager.getAppManager().finishActivity();
//            }
//        });
//
//        tvRecyclerView.setHasFixedSize(true);
//        tvRecyclerView.setNestedScrollingEnabled(false);
//        tvRecyclerView.setLayoutManager(new GridLayoutManager(FriendCircleActivity.this,1));
//        initData();
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
//        tvRecyclerView = findViewById(R.id.friendcircle_recyc);
        includeTopTvTital.setText("朋友圈");
        tvRecyclerView.setHasFixedSize(true);
        tvRecyclerView.setNestedScrollingEnabled(false);
        tvRecyclerView.setLayoutManager(new GridLayoutManager(FriendCircleActivity.this, 1));
        initData();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_circle;
    }

    List<DataDiscoveryFriendCircle> frndccList = new ArrayList<>();
    DiscoveryFriendCircleAdapter discoveryFriendCircleAdapter = null;

    private void initData() {
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

//            public void onClick(View v) {
////                img_comments.setImageResource(R.drawable.comments);
//                et_comments.setVisibility(View.VISIBLE);
//                write_comments = et_comments.getText().toString();
//                et_comments.setText(write_comments);
//                tv_comments.setVisibility(View.VISIBLE);
//                tv_comments.setText(write_comments);
//            }
//

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
                if (view.getId() == R.id.ac_img_discovery_friendcircle_comments) {
                    Toast.makeText(FriendCircleActivity.this, "我是评论", Toast.LENGTH_SHORT).show();
//                    img_comments.setImageResource(R.drawable.comments);
//                    layout_item = (LinearLayout)view;
//                    et_comments = findViewById(R.id.ac_et_discovery_friendcircle_comments);
//                    et_comments.setVisibility(View.VISIBLE);

                }
            }
        });
        discoveryFriendCircleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(FriendCircleActivity.this, "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    protected boolean isNeedBackToFormer() {
//        return true;
//    }
}
