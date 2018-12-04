package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.util.Log;
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
                .crossFade(1000)
                .into((ImageView) helper.getView(R.id.item_creat_iv_head));
        helper.setText(R.id.item_creat_tv_name,item.getNickName());
    }
    private HashMap<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean,Boolean> checkMap = new HashMap<>();
    @Override
    public void onBindViewHolder(BaseViewHolder holder,final int positions) {
        super.onBindViewHolder(holder, positions);

        CheckBox mCheck = holder.getView(R.id.item_creat_check);
        final   DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = searchCityList.get(positions);

        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkMap.put(groupListBean,isChecked);
                if (mCheckedChangeListener != null){
                    mCheckedChangeListener.onCheckedChanged(positions,buttonView,isChecked);
                }
            }
        });
        if (checkMap.get(searchCityList)!=null)
           mCheck.setChecked(checkMap.get(groupListBean));
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
    private CreatGroupSeachAdapter.CheckedChangeListener mCheckedChangeListener;
    public void setCheckedChangeListener(CreatGroupSeachAdapter.CheckedChangeListener checkedChangeListener){
        mCheckedChangeListener = checkedChangeListener;
    }

    public interface CheckedChangeListener{
        void onCheckedChanged(int position, CompoundButton buttonView, boolean isChecked);
    }
}
