package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.chatfeng.main_code.ui.about_contacts.about_custom.Allcity;

import java.util.List;

public class SeachAdapter extends BaseQuickAdapter<Allcity,BaseViewHolder> {

    List<Allcity> searchCityList;
    Context context;
    public SeachAdapter(Context context, List<Allcity> searchCityList)
    {
        super(R.layout.list_allcity_item,searchCityList);
        this.context = context;
        this.searchCityList=searchCityList;
    }
    @Override
    protected void convert(BaseViewHolder helper, Allcity item)
    {

//        Glide.with(context).load(item.getMsg_f_image())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .into((ImageView) helper.getView(R.id.frag_img_msg_f_head));
        helper.setText(R.id.tv_list_city,item.getName());
    }

}
