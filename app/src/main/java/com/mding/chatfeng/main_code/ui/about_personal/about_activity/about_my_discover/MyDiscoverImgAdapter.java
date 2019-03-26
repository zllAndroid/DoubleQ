package com.mding.chatfeng.main_code.ui.about_personal.about_activity.about_my_discover;

import android.content.Context;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyDiscoverImgAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    Context context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MyDiscoverImgAdapter(Context context,int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        this.context = context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.item_my_discover_tv_year, item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
//        Glide.with(context).load(userDetailInfo.getHeadImg())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .error(R.drawable.mine_head)
//                .into(mIvHead);
        helper.setImageResource(R.id.item_iv_fd_discover, R.drawable.discover_content_img);
    }
}
