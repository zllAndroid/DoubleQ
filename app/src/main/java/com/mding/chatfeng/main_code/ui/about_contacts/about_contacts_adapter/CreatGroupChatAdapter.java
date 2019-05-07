package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.model.DataCreatGroupChat;
import com.mding.chatfeng.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class CreatGroupChatAdapter extends BaseExpandableListAdapter {

    public List<DataCreatGroupChat.RecordBean.FriendListBean> mDataList;
    Context context;
    public CreatGroupChatAdapter(Context context, List<DataCreatGroupChat.RecordBean.FriendListBean> mDataList)
    {
        this.context = context;
        this.mDataList=mDataList;
    }
    @Override
    public int getGroupCount() {
        return mDataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        mList.get(groupPosition).getFriend_list()
        return mDataList.get(groupPosition).getGroupList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDataList.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataList.get(groupPosition).getGroupList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    TextView tv_contacts_parent_name;
    LinearLayout mLinManage;
    ImageView img_parent_toright;
    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        convertView =  ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_mng_contacts, null);
        tv_contacts_parent_name = convertView.findViewById(R.id.frag_tv_contact_m_parent_name);
        mLinManage = convertView.findViewById(R.id.cusmanage_lin_top);
        img_parent_toright = convertView.findViewById(R.id.frag_img_contact_toright);
        if (mDataList.get(groupPosition).getGroupName().equals("~"))
        {
            tv_contacts_parent_name.setText("#");
        }else {
            tv_contacts_parent_name.setText(mDataList.get(groupPosition).getGroupName());
        }
        img_parent_toright.setVisibility(View.GONE);
        tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.app_theme));
        mLinManage.setBackgroundColor(context.getResources().getColor(R.color.linkfriend_bac));
        return convertView;
    }
    class ChildHolder {
        LinearLayout mLinCheck;
        TextView mTvName;
        ImageView mIvHead;
        CheckBox mCheck;
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
    List<String> files = new ArrayList<>();
    public List<String> getCheckString(){
        return files;
    }

    @Override
    public View getChildView(int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder holder;
        final   DataCreatGroupChat.RecordBean.FriendListBean friendListBean = mDataList.get(groupPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_craet_group_chat, null);
            holder.mLinCheck = convertView.findViewById(R.id.lin_check_choose);
            holder.mTvName = convertView.findViewById(R.id.item_creat_tv_name);
            holder.mIvHead = convertView.findViewById(R.id.item_creat_iv_head);
            holder.mCheck = convertView.findViewById(R.id.item_creat_check);
//            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        final   DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = mDataList.get(groupPosition).getGroupList().get(childPosition);
        try {
            String headImg = groupListBean.getHeadImg();
//            ImageUtils.useBase64WithError(context, holder.mIvHead, headImg.substring(0, headImg.indexOf("_")), R.drawable.first_head_nor);
            ImageUtils.useBase64WithError(context, holder.mIvHead, groupListBean.getHeadImg(), R.drawable.qun_head);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Glide.with(context)
//                .load(groupListBean.getHeadImg())
//                .error(R.drawable.first_head_nor)
//                .bitmapTransform(new CropCircleTransformation(context))
//                .into(holder.mIvHead);
        holder.mTvName.setText(groupListBean.getNickName());
        holder.mLinCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkMap.put(groupListBean,holder.mCheck.isChecked());

                if (holder.mCheck.isChecked())
                {
                    holder.mCheck.setChecked(false);
                    if (files.contains(groupListBean.getUserId()))
                        files.remove(groupListBean.getUserId());
                }else
                {
                    if (!files.contains(groupListBean.getUserId()))
                        files.add(groupListBean.getUserId());
                    holder.mCheck.setChecked(true);
                }
                if (mCheckedChangeListener != null){
                    mCheckedChangeListener.onCheckedChanged(groupListBean.getUserId(),holder.mCheck.isChecked());
                }
            }
        });

//        holder.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                checkMap.put(groupListBean,isChecked);
//                if (mCheckedChangeListener != null){
//                    mCheckedChangeListener.onCheckedChanged(childPosition,buttonView,isChecked);
//                }
//            }
//        });
        if (checkMap.get(mDataList)!=null)
            holder.mCheck.setChecked(checkMap.get(friendListBean));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private HashMap<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean,Boolean> checkMap = new HashMap<>();
    private OnMyLinChangeListener mCheckedChangeListener;
    public void setOnMyLinChangeListener(OnMyLinChangeListener checkedChangeListener){
        mCheckedChangeListener = checkedChangeListener;
    }

    public interface OnMyLinChangeListener{
        void onCheckedChanged(String friendId, boolean isChecked);
    }
}
