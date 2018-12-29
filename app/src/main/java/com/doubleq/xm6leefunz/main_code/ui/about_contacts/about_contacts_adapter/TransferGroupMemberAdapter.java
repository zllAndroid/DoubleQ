package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubleq.model.DataTransferGroupMember;
import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class TransferGroupMemberAdapter extends BaseExpandableListAdapter {

    Context context;
    List<DataTransferGroupMember.RecordBean.MemberListBean> mGroupList;
    public TransferGroupMemberAdapter(Context context, List<DataTransferGroupMember.RecordBean.MemberListBean> mGroupList
    ) {
        this.mGroupList = mGroupList;
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        mList.get(groupPosition).getFriend_list()

        return mGroupList.get(groupPosition).getGroupList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return mGroupList.get(groupPosition).getGroupList().get(childPosition);
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

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        convertView =  ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_mng_contacts, null);
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_mng_contacts, null);
        tv_contacts_parent_name = convertView.findViewById(R.id.frag_tv_contact_m_parent_name);
        mLinManage = convertView.findViewById(R.id.cusmanage_lin_top);
        img_parent_toright = convertView.findViewById(R.id.frag_img_contact_toright);

        if (mGroupList.get(groupPosition).getGroupName().equals("~"))
        {
            tv_contacts_parent_name.setText("#");
        }else
            tv_contacts_parent_name.setText(mGroupList.get(groupPosition).getGroupName());
        img_parent_toright.setVisibility(View.GONE);
        mLinManage.setBackgroundColor(context.getResources().getColor(R.color.grayeee));
//        }
        return convertView;
    }
    TextView tv_contacts_parent_name;
    LinearLayout mLinManage;
    ImageView img_parent_toright;
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TransferGroupMemberAdapter.ChildHolder holder;
        if (convertView == null) {
            holder = new TransferGroupMemberAdapter.ChildHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_child_mng_contacts, null);
            holder.img_contacts_child_head = convertView.findViewById(R.id.frag_img_contacts_child_f_head);
            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_f_name);
//            holder.tv_contacts_child_state = convertView.findViewById(R.id.frag_tv_contacts_child_f_state);
//            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
            convertView.setTag(holder);
        } else {
            holder = (TransferGroupMemberAdapter.ChildHolder) convertView.getTag();
        }

        DataTransferGroupMember.RecordBean.MemberListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);

//            DataLinkManList.RecordBean.FriendGroupBean dataContactsManageChild = mList.get(groupPosition).getDataLinkChildList().get(childPosition);
        if (!StrUtils.isEmpty(groupListBean.getMemberId())) {
            Glide.with(context)
                    .load(groupListBean.getHeadImg())
                    .error(R.drawable.img_personal_head)
                    .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
                    .into(holder.img_contacts_child_head);
            holder.tv_contacts_child_name.setText(groupListBean.getNickName());
        }else {
//            delItem(groupPosition);
        }
//        holder.tv_contacts_child_state.setText(dataContactsManageChild.get());
//        holder.tv_contacts_child_motto.setText(dataContactsManageChild.getTv_child_motto_m());
        return convertView;
    }
    //添加数据
    public void delItem(int groupPosition) {
        mGroupList.get(groupPosition).getGroupList().remove(0);
        notifyDataSetChanged();
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        TextView tv_contacts_parent_name;
        LinearLayout mLinManage;
        ImageView img_parent_toright;
    }

    class ChildHolder {
        TextView tv_contacts_child_name, tv_contacts_child_state,tv_contacts_child_motto;
        ImageView img_contacts_child_head;
    }

}
