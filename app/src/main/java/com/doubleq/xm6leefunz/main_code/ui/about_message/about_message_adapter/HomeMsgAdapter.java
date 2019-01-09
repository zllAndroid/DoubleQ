package com.doubleq.xm6leefunz.main_code.ui.about_message.about_message_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_utils.about_realm.realm_data.CusDataRealmMsg;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HomeMsgAdapter extends BaseQuickAdapter<CusDataRealmMsg, BaseViewHolder> {
    Context context;
    public List<CusDataRealmMsg> data;
    public HomeMsgAdapter(Context context, List<CusDataRealmMsg> data, ItemTouchListener mItemTouchListener) {
        super(R.layout.item_home_message, data);
        this.data=data;
        this.context=context;
        this.mItemTouchListener=mItemTouchListener;

    }

    @Override
    protected void convert(BaseViewHolder helper, CusDataRealmMsg item) {
        Glide.with(context).load(item.getHeadImg())
                .bitmapTransform(new CropCircleTransformation(context))
                .into((ImageView) helper.getView(R.id.item_iv_head));
        helper.setText(R.id.item_tv_name,item.getNickName());
        helper.addOnClickListener(R.id.item_tv_click_ok);
        helper.addOnClickListener(R.id.item_msg_re);
    }

    //添加数据
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
        click(swipeLayout, lMenu,positions,data.get(positions).getNickName());
    }
    private void click(final SwipeItemLayout swipeLayout, final  View view,final  int positions,String name) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemTouchListener.onLeftMenuClick(view,positions,id);
                    swipeLayout.close();
                }
            });
        }
    }
    public  interface ItemTouchListener {
        void onLeftMenuClick(View view, int positions, String WaybillNum);
    }
}