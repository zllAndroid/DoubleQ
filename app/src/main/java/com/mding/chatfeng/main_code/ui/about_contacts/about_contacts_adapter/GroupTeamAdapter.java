package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.model.DataGroupMember;
import com.mding.chatfeng.R;

import java.util.List;

public class GroupTeamAdapter extends BaseQuickAdapter<DataGroupMember.RecordBean.MemberListBean,BaseViewHolder> {

    List<DataGroupMember.RecordBean.MemberListBean> allCitiesList;
    Context context;
    public GroupTeamAdapter(Context context,List<DataGroupMember.RecordBean.MemberListBean> allCitiesList)
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
        DataGroupMember.RecordBean.MemberListBean memberListBean = allCitiesList.get(positions);


        tv_zimu.setText(allCitiesList.get(positions).getGroupName());
        List<DataGroupMember.RecordBean.MemberListBean.GroupListBean> groupList = memberListBean.getGroupList();

        for (int i =0;i<groupList.size();i++)
        {

            tv_cities.setText(groupList.get(i).getNickName());
        }
//        tv_cities.setText();
//        Allcity allcity = allCitiesList.get(positions);
//        tv_cities.setText(allcity.getName());
//        String firstABC = getFirstABC(allcity.getPinyin());
//        if (positions==0)
//        {
//            tv_zimu.setVisibility(View.VISIBLE);
//            tv_zimu.setText(firstABC);
//        }else {
//            String upFirstABC = getFirstABC(allCitiesList.get(positions-1).getPinyin());
//            if (firstABC.equals(upFirstABC))
//            {
//                tv_zimu.setVisibility(View.GONE);
//            }
//            else {
//                tv_zimu.setVisibility(View.VISIBLE);
//                tv_zimu.setText(firstABC);
//            }
//        }
    }
    public String getFirstABC(String pinyin)
    {
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
    }


    @Override
    protected void convert(BaseViewHolder helper, DataGroupMember.RecordBean.MemberListBean item)
    {


//        TextView tv_cities= helper.getView(R.id.tv_cities);
//        List<DataGroupMember.RecordBean.MemberListBean.GroupListBean> groupList = item.getGroupList();
//
//        for (int i =0;i<groupList.size();i++)
//        {
//
//            tv_cities.setText(groupList.get(i).getNickName());
//        }

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
