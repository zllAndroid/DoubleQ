package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.model.DataNoticeDetails;
import com.mding.chatfeng.R;

import java.util.List;

public class FriendNoticeDetailsAdapter extends BaseQuickAdapter<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean,BaseViewHolder> {

    Context context;
    List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> mList;
    public FriendNoticeDetailsAdapter(Context context,List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> mList)
    {
        super(R.layout.item_notice_detail,mList);
        this.context = context;
        this.mList = mList;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean item) {
        helper.setText(R.id.item_tv_notice_detail_remark,item.getMessage());
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
