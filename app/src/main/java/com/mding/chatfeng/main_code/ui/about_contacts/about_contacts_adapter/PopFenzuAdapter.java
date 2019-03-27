package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.model.DataGroupManage;
import com.mding.chatfeng.R;

import java.util.List;

public class PopFenzuAdapter extends BaseQuickAdapter<DataGroupManage.RecordBean.GroupInfoBean,BaseViewHolder> {

    List<String> searchCityList;
//    Context context;
    public PopFenzuAdapter(List<DataGroupManage.RecordBean.GroupInfoBean> searchCityList)
    {
        super(R.layout.item_pop_fenzu,searchCityList);
//        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataGroupManage.RecordBean.GroupInfoBean item)
    {

//        Glide.with(context).load(item.getMsg_f_image())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .into((ImageView) helper.getView(R.id.frag_img_msg_f_head));
        helper.setText(R.id.item_tv_fenzu,item.getGroupName());
    }

}
