package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.home_msg.DataMsgFriend;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.Allcity;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GroupTeamAdapter extends BaseQuickAdapter<Allcity,BaseViewHolder> {

    List<Allcity> allCitiesList;
    Context context;
    public GroupTeamAdapter(Context context,List<Allcity> allCitiesList)
    {
        super(R.layout.cites_item,allCitiesList);
        this.context = context;
        this.allCitiesList=allCitiesList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        TextView tv_zimu= holder.getView(R.id.tv_zimu);
        TextView tv_cities= holder.getView(R.id.tv_cities);
        Allcity allcity = allCitiesList.get(positions);
        tv_cities.setText(allcity.getName());
        String firstABC = getFirstABC(allcity.getPinyin());
        if (positions==0)
        {
            tv_zimu.setVisibility(View.VISIBLE);
            tv_zimu.setText(firstABC);
        }else {
            String upFirstABC = getFirstABC(allCitiesList.get(positions-1).getPinyin());
            if (firstABC.equals(upFirstABC))
            {
                tv_zimu.setVisibility(View.GONE);
            }
            else {
                tv_zimu.setVisibility(View.VISIBLE);
                tv_zimu.setText(firstABC);
            }
        }
    }
    public String getFirstABC(String pinyin)
    {
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
    }
    @Override
    protected void convert(BaseViewHolder helper, Allcity item)
    {

//        Glide.with(context).load(item.getMsg_f_image())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .into((ImageView) helper.getView(R.id.frag_img_msg_f_head));
//        helper.setText(R.id.frag_img_msg_f_name,item.getMsg_f_name());
//        helper.setText(R.id.frag_img_msg_f_contants,item.getMsg_f_contants());
//        helper.setText(R.id.frag_img_msg_f_time,item.getMsg_f_time());
//        helper.setText(R.id.frag_img_msg_f_num,item.getMsg_f_num());
//        TextView tv_zimu= helper.getView(R.id.tv_zimu);
//        TextView tv_cities= helper.getView(R.id.tv_cities);
//        helper.setText(R.id.tv_cities,item.getName());
    }

}
