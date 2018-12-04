package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.DataFriendGroup;
import com.doubleq.xm6leefunz.R;

import java.util.List;

public class AddFriendFenzuAdapter extends BaseQuickAdapter<DataFriendGroup.RecordBean.GroupInfoBean,BaseViewHolder> {

    List<String> searchCityList;
//    Context context;
    public AddFriendFenzuAdapter(List<DataFriendGroup.RecordBean.GroupInfoBean> searchCityList)
    {
        super(R.layout.item_pop_fenzu,searchCityList);
//        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataFriendGroup.RecordBean.GroupInfoBean item)
    {

//        Glide.with(context).load(item.getMsg_f_image())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .into((ImageView) helper.getView(R.id.frag_img_msg_f_head));
        helper.setText(R.id.item_tv_fenzu,item.getGroupName());
    }

}
