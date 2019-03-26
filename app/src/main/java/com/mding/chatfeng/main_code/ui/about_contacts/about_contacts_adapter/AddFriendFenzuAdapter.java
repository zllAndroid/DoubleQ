package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.model.DataFriendGroup;

import java.util.List;

public class AddFriendFenzuAdapter extends BaseQuickAdapter<DataFriendGroup.RecordBean.GroupInfoBean,BaseViewHolder> {

    List<DataFriendGroup.RecordBean.GroupInfoBean> searchCityList;
    Context context;

    public AddFriendFenzuAdapter(Context context, List<DataFriendGroup.RecordBean.GroupInfoBean> searchCityList)
    {
        super(R.layout.item_pop_fenzu,searchCityList);
        this.context = context;
        this.searchCityList = searchCityList;
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

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (searchCityList.size() > 0){
            String groupName = searchCityList.get(searchCityList.size() - 1).getGroupName();
            if (positions == (searchCityList.size()-1) && groupName.equals("移出该分组")){
                holder.setTextColor(R.id.item_tv_fenzu, ContextCompat.getColor(context,R.color.app_theme));
            }
        }
    }
}
