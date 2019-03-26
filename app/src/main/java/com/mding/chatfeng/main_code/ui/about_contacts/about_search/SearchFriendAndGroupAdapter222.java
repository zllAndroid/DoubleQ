package com.mding.chatfeng.main_code.ui.about_contacts.about_search;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class SearchFriendAndGroupAdapter222 extends BaseQuickAdapter<DataSearch, BaseViewHolder> {
    Context context;
    public SearchFriendAndGroupAdapter222(Context context, List<DataSearch> data ) {
        super(R.layout.item_search, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataSearch item) {
        if (item.getType().equals("1"))
        {
            Glide.with(context).load(item.getHeadImg())
                    .error(R.drawable.mine_head)
                    .bitmapTransform(new CropCircleTransformation(context))
                   .into((ImageView) helper.getView(R.id.item_search_iv_head));
//                    .into((ImageView) helper.getView(R.id.item_search_iv_head));
//            Glide.with(this).load(dataSearch.getHeadImg())
//                    .error(R.drawable.mine_head)
//                    .bitmapTransform(new CropCircleTransformation(AddGoodFriendActivity.this))
//                    .crossFade(1000).into(fdaIvHead);
            helper.setText(R.id.item_search_tv_type,"人");
        }
        else
        {
            Glide.with(context).load(item.getHeadImg())
                    .error(R.drawable.qun_head)
                    .bitmapTransform(new CropCircleTransformation(context))
                  .into((ImageView) helper.getView(R.id.item_search_iv_head));

//            Glide.with(context).load(item.getHeadImg())
//                    .error(R.drawable.qun_head)
//                    .into((ImageView) helper.getView(R.id.item_search_iv_head));
            helper.setText(R.id.item_search_tv_type,"群");
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

//    @Override
//    public boolean isFooterViewAsFlow() {
//        return true;
//    }
//
//    @Override
//    public int setFooterView(View header) {
//        return R.layout.item_search_bot;
//    }
    //    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TYPE_HEAD){
//            View itemView = LayoutInflater.from(GridActivity.this).inflate(R.layout.head_for_recyclerview,parent,false);
//            return new HeadHolder(itemView);
//        }else if(viewType == TYPE_CONTENT){
//            View itemView = LayoutInflater.from(GridActivity.this).inflate(R.layout.item_for_recycler_view,parent,false);
//            return new RecyclerAdapter.ContentHolder(itemView);
//        }else{
//            View itemView = LayoutInflater.from(GridActivity.this).inflate(R.layout.foot_for_recyclerview,parent,false);
//            return new RecyclerAdapter.FootHolder(itemView);
//        }
//    }

}