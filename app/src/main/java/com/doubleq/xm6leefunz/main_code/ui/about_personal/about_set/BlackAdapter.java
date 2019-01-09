package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_set;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.DataBlack;
import com.doubleq.xm6leefunz.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class BlackAdapter extends BaseQuickAdapter<DataBlack.RecordBean, BaseViewHolder> {
    Context context;
    public List<DataBlack.RecordBean> data;
    public BlackAdapter(Context context,List<DataBlack.RecordBean> data) {
        super(R.layout.item_black, data);
        this.data=data;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBlack.RecordBean item) {
        Glide.with(context).load(item.getHeadImg())
                .bitmapTransform(new CropCircleTransformation(context))
                .into((ImageView) helper.getView(R.id.item_iv_black));
        helper.setText(R.id.item_tv_name,item.getNickName());
        helper.addOnClickListener(R.id.item_tv_click_del);

    }

    //添加数据
    public void delItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        //必须调用这行代码
        notifyItemRangeChanged(position, data.size());
    }
}