package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.DataNoticeDetail;
import com.doubleq.xm6leefunz.R;
import java.util.List;

public class FriendNoticeDetailsAdapter extends BaseQuickAdapter<DataNoticeDetail,BaseViewHolder> {

    Context context;
    List<DataNoticeDetail> mList;
    public FriendNoticeDetailsAdapter(Context context,List<DataNoticeDetail> mList)
    {
        super(R.layout.item_notice_detail,mList);
        this.context = context;
        this.mList = mList;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataNoticeDetail item)
    {
        helper.setText(R.id.item_tv_notice_detail_remark,item.getNoticeDetail());
//        helper.setText(R.id.item_tv_notice_detail_remark,item.get());
    }

//
//    @Override
//    public void onBindViewHolder(final BaseViewHolder helper, int positions) {
//        super.onBindViewHolder(helper, positions);
//        final DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = mList.get(positions);
//    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }
}
