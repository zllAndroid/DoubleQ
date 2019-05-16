package com.mding.chatfeng.main_code.ui.about_contacts.about_search;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.ImageUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 搜索适配器
 */

public class SearchFriendAndGroupAdapter extends BaseQuickAdapter<DataSearch, BaseViewHolder> {
    Context context;
    private List<DataSearch> list;
    private String text;
    public SearchFriendAndGroupAdapter(Context context, List<DataSearch> data ) {
        super(R.layout.item_search, data);
        this.context=context;
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataSearch item) {
        ImageView mIvHead = (ImageView) helper.getView(R.id.item_search_iv_head);
        if (item.getType().equals("1"))
        {
            ImageUtils.useBase64WithError(context,mIvHead,item.getHeadImg(),R.drawable.first_head_nor);
            helper.setText(R.id.item_search_tv_type,"好友");
        }
        else if (item.getType().equals("2"))
        {
            ImageUtils.useBase64WithError(context,mIvHead,item.getHeadImg(),R.drawable.qun_head);
            helper.setText(R.id.item_search_tv_type,"群聊");
        }
        else
        {
            ImageUtils.useBase64WithError(context,mIvHead,item.getHeadImg(),R.drawable.first_head_nor);
            helper.setText(R.id.item_search_tv_type,"聊天记录");
        }
        helper.setText(R.id.item_search_tv_name,item.getName());
        helper.setText(R.id.item_search_tv_num,item.getSno());
    }
    private final static int HEAD_COUNT = 1;
    private final static int FOOT_COUNT = 1;

    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;
    private final static int TYPE_FOOTER = 2;

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
    }
    public void setText(String text){this.text = text;}
    @Override
    public int getItemCount() {
        return super.getItemCount();
    }



}