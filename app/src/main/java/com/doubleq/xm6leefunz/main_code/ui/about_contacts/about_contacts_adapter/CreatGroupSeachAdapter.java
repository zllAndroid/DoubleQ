package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.DataCreatGroupChat;
import com.doubleq.xm6leefunz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class CreatGroupSeachAdapter extends BaseQuickAdapter<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean,BaseViewHolder> {

    List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> searchCityList;
    Context context;
    public CreatGroupSeachAdapter(Context context, List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> searchCityList)
    {
        super(R.layout.item_craet_group_chat,searchCityList);
        this.context = context;
        this.searchCityList=searchCityList;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean item)
    {
        Log.e("searchCityList","---------输入------------->"+item.getNickName());
        Glide.with(context).load(item.getHeadImg())
                .bitmapTransform(new CropCircleTransformation(context))

                .into((ImageView) helper.getView(R.id.item_creat_iv_head));
        helper.setText(R.id.item_creat_tv_name,item.getNickName());


    }
    List<String> files = new ArrayList<>();
    List<String> mList = new ArrayList<>();
    public List<String> getCheckString(){
        return files;
    }
    public void setChoose(List<String> mList){
        this.mList.clear();
        this.mList=mList;
        notifyDataSetChanged();
    }
    private HashMap<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean,Boolean> checkMap = new HashMap<>();
    @Override
    public void onBindViewHolder(BaseViewHolder holder,final int positions) {
        super.onBindViewHolder(holder, positions);
        final   DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = searchCityList.get(positions);
        final  CheckBox mCheck = holder.getView(R.id.item_creat_check);
//        如果列表已经选中，在搜索结果中包含这个结果，则显示选中，并加入
        if ( mList.contains(groupListBean.getUserId())) {
            mCheck.setChecked(true);
            if (!files.contains(groupListBean.getUserId())) {
                files.add(groupListBean.getUserId());
//                mCheckedChangeListener.onCheckedChanged(groupListBean.getUserId(),mCheck.isChecked());
            }else
            {
                mCheck.setChecked(false);
                if (files.contains(groupListBean.getUserId()))
                    files.remove(groupListBean.getUserId());
            }
        }
        holder.getView(R.id.lin_check_choose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mCheck.isChecked())
                        {
                            mCheck.setChecked(false);
                            if (files.contains(groupListBean.getUserId()))
                                files.remove(groupListBean.getUserId());
                        }else
                        {
                            if (!files.contains(groupListBean.getUserId()))
                                files.add(groupListBean.getUserId());
                            mCheck.setChecked(true);
                        }
//                        mCheckedChangeListener.onCheckedChanged(groupListBean.getUserId(),mCheck.isChecked());
                    }
                });
    }

    public List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> getCheckData(){
        List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> files = new ArrayList<>();
        Iterator iter = checkMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if ((Boolean) entry.getValue()){
                files.add((DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean) entry.getKey());
            }
        }
        return files;
    }
    private CreatGroupSeachAdapter.OnMyCheckedChangeListener mCheckedChangeListener;
    public void setCheckedChangeListener(CreatGroupSeachAdapter.OnMyCheckedChangeListener checkedChangeListener){
        mCheckedChangeListener = checkedChangeListener;
    }

    public interface OnMyCheckedChangeListener{
        void onCheckedChanged(String friendId, boolean isChecked);
    }
}
