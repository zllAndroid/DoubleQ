package com.mding.chatfeng.main_code.ui.about_contacts.about_notice;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.mding.model.DataNews;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by  on 2017/11/8 0008.
 */

public class NoticeAdapter extends BaseQuickAdapter<DataNews.RecordBean.ListInfoBean, BaseViewHolder> {
    Context context;
    public List<DataNews.RecordBean.ListInfoBean> data;

    public NoticeAdapter(Context context, List<DataNews.RecordBean.ListInfoBean> data, ItemTouchListener mItemTouchListener) {
        super(R.layout.item_notice_menu, data);
        this.data = data;
        this.context = context;
        this.mItemTouchListener = mItemTouchListener;

    }

    @Override
    protected void convert(BaseViewHolder helper, DataNews.RecordBean.ListInfoBean item) {
        ImageView view = helper.getView(R.id.item_iv_head);
        String headImg = item.getHeadImg();
//        ImageUtils.useBase64(context, view, headImg.substring(0, headImg.indexOf("_")));
        ImageUtils.useBase64(context, view, item.getHeadImg());
//        Glide.with(context).load(item.getHeadImg())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .into((ImageView) helper.getView(R.id.item_iv_head));
        helper.setText(R.id.item_tv_name, item.getNickName());
        // 获取备注信息
        helper.setText(R.id.item_tv_remark, item.getRemark().getMessage());
        TextView mTvClick = helper.getView(R.id.item_tv_click_ok);
        switch (item.getIsAgree()) {
            case "0":
                mTvClick.setText("同意");
                mTvClick.setBackgroundResource(R.drawable.btn_stroke_sel);
                break;
            case "1":
                mTvClick.setText("已同意");
                mTvClick.setBackgroundResource(R.drawable.chat_btn_stroke_nor);
                break;
            case "2":
                mTvClick.setText("已拒绝");
                mTvClick.setBackgroundResource(R.drawable.chat_btn_stroke_nor);
                break;
        }

        helper.addOnClickListener(R.id.item_tv_click_ok);
        helper.addOnClickListener(R.id.item_re_notice);
    }

    //删除数据
    public void delItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        //必须调用这行代码
        notifyItemRangeChanged(position, data.size());
    }

    String id;
    SwipeItemLayout swipeLayout;
    private ItemTouchListener mItemTouchListener;

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        swipeLayout = (SwipeItemLayout) holder.itemView;
        final View lMenu = holder.getView(R.id.item_notice_del_menu);
        click(swipeLayout, lMenu, positions, data.get(positions).getNickName());
    }

    private void click(final SwipeItemLayout swipeLayout, final View view, final int positions, String name) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemTouchListener.onLeftMenuClick(view, positions, id);
                    swipeLayout.close();
                }
            });
        }
    }

    public interface ItemTouchListener {
        void onLeftMenuClick(View view, int positions, String WaybillNum);
    }
}