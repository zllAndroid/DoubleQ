package com.mding.chatfeng.main_code.ui.about_message.about_message_adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.model.home_msg.DataMsgFriend;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MsgFriendAdapter extends BaseQuickAdapter<DataMsgFriend,BaseViewHolder> {

    Context context;
    public MsgFriendAdapter(List<DataMsgFriend> data,Context context)
    {
        super(R.layout.item_f_msg,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataMsgFriend item)
    {
        Glide.with(context).load(item.getMsg_f_image())
                .bitmapTransform(new CropCircleTransformation(context))
                .into((ImageView) helper.getView(R.id.frag_img_msg_f_head));
        helper.setText(R.id.frag_img_msg_f_name,item.getMsg_f_name());
        helper.setText(R.id.frag_img_msg_f_contants,item.getMsg_f_contants());
        helper.setText(R.id.frag_img_msg_f_time,item.getMsg_f_time());
        helper.setText(R.id.frag_img_msg_f_num,item.getMsg_f_num());

    }

}
