package com.doubleq.xm6leefunz.main_code.ui.about_discovery.about_discovery_fragment;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.find_friend.DataDiscoveryFriendCircle;
import com.doubleq.xm6leefunz.R;


import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class DiscoveryFriendCircleAdapter extends BaseQuickAdapter<DataDiscoveryFriendCircle, BaseViewHolder> {
    Context context;
    public DiscoveryFriendCircleAdapter(List<DataDiscoveryFriendCircle> data, Context context) {
        super(R.layout.item_discovery_friendcircle, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataDiscoveryFriendCircle item) {
        helper.addOnClickListener(R.id.ac_img_discovery_friendcircle_comments);
        Glide.with(context).load(item.getImg_head()).into((ImageView) helper.getView(R.id.ac_img_discovery_frinedcircle_head));
        helper.setText(R.id.ac_tv_discovery_frinedcircle_name,item.getTv_name());
        helper.setText(R.id.ac_tv_discovery_frinedcircle_words,item.getTv_words());
        Glide.with(context).load(item.getImg_pic()).into((ImageView) helper.getView(R.id.ac_img_discovery_frinedcircle_pic));
        helper.setText(R.id.ac_et_discovery_friendcircle_comments,item.getEt_comments());
        helper.setText(R.id.ac_tv_discovery_friendcircle_comments,item.getTv_comments());
//        Glide.with(context).load(item.getImg()).bitmapTransform(new CropCircleTransformation(context)).crossFade(1000).into((ImageView) helper.getView(R.id.album_img));
//        helper.setText(R.id.album_time, item.getTime()+"");
    }
}