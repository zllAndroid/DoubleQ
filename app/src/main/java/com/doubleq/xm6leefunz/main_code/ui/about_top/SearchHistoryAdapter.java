package com.doubleq.xm6leefunz.main_code.ui.about_top;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<CusDataSearchRecord, BaseViewHolder> {
    Context context;
    private List<CusDataSearchRecord> list;
    private String text;
    public SearchHistoryAdapter(Context context, List<CusDataSearchRecord> data ) {
        super(R.layout.item_search_history, data);
        this.context=context;
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, CusDataSearchRecord item) {
        TextView mTital = helper.getView(R.id.item_search_tv_type);
        TextView mName = helper.getView(R.id.item_search_tv_name);
        TextView mNum = helper.getView(R.id.item_search_tv_num);
        ImageView mIvImg = helper.getView(R.id.item_search_iv_head);
        View mLine = helper.getView(R.id.search_line);
//        头部标题
        if (item.isFirst())
        {
            mTital.setText(item.getTotalName());
            mTital.setVisibility(View.VISIBLE);
        }else
        {
            mTital.setVisibility(View.GONE);
        }
        if (item.isMore())
        {
            mIvImg.setImageResource(R.drawable.search);
            mNum.setVisibility(View.GONE);
            mLine.setVisibility(View.VISIBLE);
        }else
        {
            mLine.setVisibility(View.GONE);
            mNum.setVisibility(View.VISIBLE);
            mName.setText(item.getName());
            mNum.setText(item.getNum());
            Glide.with(context).load(item.getImg())
                    .error(R.drawable.mine_head)
                    .bitmapTransform(new CropCircleTransformation(context))
//                    .crossFade(1000)
                    .into(mIvImg);
        }

//        if (item.getType().equals("1"))
//        {
//            Glide.with(context).load(item.getHeadImg())
//                    .error(R.drawable.mine_head)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .crossFade(1000).into((ImageView) helper.getView(R.id.item_search_iv_head));
////                    .into((ImageView) helper.getView(R.id.item_search_iv_head));
////            Glide.with(this).load(dataSearch.getHeadImg())
////                    .error(R.drawable.mine_head)
////                    .bitmapTransform(new CropCircleTransformation(AddGoodFriendActivity.this))
////                    .crossFade(1000).into(fdaIvHead);
//            helper.setText(R.id.item_search_tv_type,"好友");
//        }
//        else if (item.getType().equals("2"))
//        {
//            Glide.with(context).load(item.getHeadImg())
//                    .error(R.drawable.qun_head)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .into((ImageView) helper.getView(R.id.item_search_iv_head));
//
////            Glide.with(context).load(item.getHeadImg())
////                    .error(R.drawable.qun_head)
////                    .into((ImageView) helper.getView(R.id.item_search_iv_head));
//            helper.setText(R.id.item_search_tv_type,"群聊");
//        }
//        else
//        {
//            Glide.with(context).load(item.getHeadImg())
//                    .error(R.drawable.mine_head)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .crossFade(1000).into((ImageView) helper.getView(R.id.item_search_iv_head));
//            helper.setText(R.id.item_search_tv_type,"聊天记录");
//        }
//
//        helper.setText(R.id.item_search_tv_name,item.getName());
//        helper.setText(R.id.item_search_tv_num,item.getSno());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);

//        String name = list.get(positions).getName();
//        String id = list.get(positions).getSno();
//        /**
//         * 如果没有进行搜索操作或者搜索之后点击了删除按钮，我们会在SearchActivity中把text质控并传递过来
//         */
//        if (text!=null){
//            //  设置span
//            SpannableString span_name = matcherSearchText(Color.rgb(39,213,207),name,text);
//            SpannableString age = matcherSearchText(Color.rgb(39,213,207),id,text);
//            holder.tv_name.setText(span_name);
//        }else {
//            holder.tv_name.setText(name);
//            holder.tv_id.setText(id);
//        }
    }


    public void setText(String text){this.text = text;}

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }



}