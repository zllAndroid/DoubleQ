package com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubleq.model.linkman.group_manager.DataContactsManage;
import com.doubleq.model.linkman.group_manager.DataContactsManageChild;
import com.doubleq.xm6leefunz.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ContactManageAdapter extends BaseExpandableListAdapter {

    Context context;
    ImageView img_parent_toright;
    private List<DataContactsManage> mList;
    public ContactManageAdapter(List<DataContactsManage> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getDataContactsManageChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getDataContactsManageChildList().get(childPosition);
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
        ContactManageAdapter.GroupHolder holder;
        if (convertView == null) {
            holder = new ContactManageAdapter.GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mng_contacts, null);
            holder.tv_contacts_parent_name = convertView.findViewById(R.id.frag_tv_contact_m_parent_name);
            holder.tv_contacts_parent_now = convertView.findViewById(R.id.frag_tv_contact_m_parent_now);
            holder.tv_contacts_parent_all = convertView.findViewById(R.id.frag_tv_contact_m_parent_all);
            convertView.setTag(holder);
        } else {
            holder = (ContactManageAdapter.GroupHolder) convertView.getTag();
        }
        holder.tv_contacts_parent_name.setText(mList.get(groupPosition).getTv_parent_name_m());
        holder.tv_contacts_parent_now.setText(mList.get(groupPosition).getTv_parent_now_m());
        holder.tv_contacts_parent_all.setText(mList.get(groupPosition).getTv_parent_all_m());
        img_parent_toright = convertView.findViewById(R.id.frag_img_contact_toright);

        for(int i = 0;i<getGroupCount();i++) {
            if (i == groupPosition) {
                if (isExpanded) {
                    img_parent_toright.setImageResource(R.drawable.to_down);
                    holder.tv_contacts_parent_name.setTextColor( context.getResources().getColor(R.color.doubleq_msg_num));
                } else {
                    img_parent_toright.setImageResource(R.drawable.to_right);
                    holder.tv_contacts_parent_name.setTextColor( context.getResources().getColor(R.color.grey555));
                }
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ContactManageAdapter.ChildHolder holder;
        if (convertView == null) {
            holder = new ContactManageAdapter.ChildHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_child_mng_contacts, null);
            holder.img_contacts_child_head = convertView.findViewById(R.id.frag_img_contacts_child_f_head);
            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_f_name);
            holder.tv_contacts_child_state = convertView.findViewById(R.id.frag_tv_contacts_child_f_state);
            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
            convertView.setTag(holder);
        } else {
            holder = (ContactManageAdapter.ChildHolder) convertView.getTag();
        }
        DataContactsManageChild dataContactsManageChild = mList.get(groupPosition).getDataContactsManageChildList().get(childPosition);
        Glide.with(context).load(R.drawable.img_personal_head)
                .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
                .into(holder.img_contacts_child_head);
        holder.tv_contacts_child_name.setText(dataContactsManageChild.getTv_child_name_m());
        holder.tv_contacts_child_state.setText(dataContactsManageChild.getTv_child_state_m());
        holder.tv_contacts_child_motto.setText(dataContactsManageChild.getTv_child_motto_m());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        TextView tv_contacts_parent_name,tv_contacts_parent_now,tv_contacts_parent_all;
    }

    class ChildHolder {
        TextView tv_contacts_child_name, tv_contacts_child_state,tv_contacts_child_motto;
        ImageView img_contacts_child_head;
    }

}
