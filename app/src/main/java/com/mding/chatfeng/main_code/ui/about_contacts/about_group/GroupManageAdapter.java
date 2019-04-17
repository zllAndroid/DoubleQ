package com.mding.chatfeng.main_code.ui.about_contacts.about_group;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.model.DataGroupManage;
import com.mding.chatfeng.R;

import java.util.List;

/**
 * Created by  on 2017/11/8 0008.
 */

public class GroupManageAdapter extends BaseQuickAdapter<DataGroupManage.RecordBean.GroupInfoBean, BaseViewHolder> {
    Context context;
    public List<DataGroupManage.RecordBean.GroupInfoBean> data;

    private IDragListener listener;
    public GroupManageAdapter(Context context, IDragListener listener,List<DataGroupManage.RecordBean.GroupInfoBean> data) {
        super(R.layout.item_group_manage, data);
        this.data=data;
        this.context=context;
        this.listener = listener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, DataGroupManage.RecordBean.GroupInfoBean item) {
//        Glide.with(context).load(item.getHead_img())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000).into((ImageView) helper.getView(R.id.item_iv_head));
        helper.setText(R.id.item_group_tv_groupname,item.getGroupName());
        helper.getView(R.id.item_group_lin).setBackgroundResource(R.color.white);
        helper.addOnClickListener(R.id.item_group_iv_del).addOnClickListener(R.id.item_group_iv_move).addOnClickListener(R.id.item_group_tv_groupname);
        helper.getView(R.id.item_group_iv_move).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                listener.startDrag(helper);
                return false;
            }
        });
    }

    //添加数据
    public void delItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        //必须调用这行代码
        notifyItemRangeChanged(position, data.size());
    }
    public void notifyData() {
        //必须调用这行代码
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }else {
            return data.size();
        }
    }
}