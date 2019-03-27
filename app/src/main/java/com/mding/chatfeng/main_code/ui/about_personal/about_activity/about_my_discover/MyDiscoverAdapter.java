package com.mding.chatfeng.main_code.ui.about_personal.about_activity.about_my_discover;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;

import java.util.List;
/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyDiscoverAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MyDiscoverAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.item_my_discover_tv_year, item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        switch (helper.getLayoutPosition() % 2) {
            case 0:
                helper.setText(R.id.item_my_discover_tv_content, "哈哈哈哈哈哈哈哈哈哈哈哈或或或或或或或或");
                break;
            case 1:
                helper.setText(R.id.item_my_discover_tv_content, "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
                break;
            default:
                break;

        }
    }
}
