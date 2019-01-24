package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_move;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import java.util.List;
/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class GroupManageMoveAdapter extends BaseAdapter {
    Context context;
    public List<DataGroupManage.RecordBean.GroupInfoBean> data;
      OnItemMoveClickListener  onMoveListen;
    public interface OnItemMoveClickListener{
      void onName(int position);
      void onDelete(int position);
  }
    public GroupManageMoveAdapter(Context context, List<DataGroupManage.RecordBean.GroupInfoBean> data,OnItemMoveClickListener  onMoveListen) {
        this.data=data;
        this.context=context;
        this.onMoveListen=onMoveListen;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
     ViewHolder viewHolder = null;
        DataGroupManage.RecordBean.GroupInfoBean item = data.get(position);
        if (convertView == null) {
        convertView = View.inflate(context, R.layout.item_group_manage, null);
        viewHolder = new ViewHolder();
        viewHolder.mIcon = (ImageView) convertView.findViewById(R.id.item_group_iv_del);
        viewHolder.mName = (TextView) convertView.findViewById(R.id.item_group_tv_groupname);
        viewHolder.mMoveIcon = (ImageView) convertView.findViewById(R.id.item_group_iv_move);
//        viewHolder.mDelLin = (RelativeLayout) convertView.findViewById(R.id.item_group_lin_del);
//        viewHolder.mDelView = (View) convertView.findViewById(R.id.item_group_v_del);
        convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.mDelLin.setFocusable(true);
//        viewHolder.mDelLin.setFocusableInTouchMode(true);
//        viewHolder.mDelLin.requestFocus();

//        viewHolder.mDelLin.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                Log.e("onTouch",motionEvent.getAction()+"");
//                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
//                    onMoveListen.onDelete(position);
//                }
//                return false;
//            }
//        });
        viewHolder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMoveListen.onDelete(position);
            }
        });
//        viewHolder.mDelView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onMoveListen.onDelete(position);
//            }
//        });
        viewHolder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMoveListen.onName(position);
            }
        });
        viewHolder.mName.setText(item.getGroupName());
        viewHolder.mMoveIcon = (ImageView) convertView.findViewById(R.id.item_group_iv_move);
//        ((ViewGroup) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        return convertView;
    }
    private class ViewHolder {
        public ImageView mIcon;
        public TextView mName;
        public ImageView mMoveIcon;
        public RelativeLayout mDelLin;
//        public View mDelView;
    }
    //添加数据
    public void delItem(int position) {
        data.remove(position);
//        notifyItemRemoved(position);
//        //必须调用这行代码
//        notifyItemRangeChanged(position, data.size());
        notifyDataSetChanged();
    }
    private  DataGroupManage.RecordBean.GroupInfoBean getPlugin(int position) {
        if (position == 0) { // 跳过条目名称（已添加）
            return null;
        } else if (position < data.size() + 1) {
            return data.get(position - 1);
        } else if (position == data.size() + 1) { // 跳过条目名称（未添加）
            return null;
        } else {
            return null;
        }
    }
    public void notifyData() {
        //必须调用这行代码
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (data == null){
            return 0;
        }else {
            return data.size();
        }
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}