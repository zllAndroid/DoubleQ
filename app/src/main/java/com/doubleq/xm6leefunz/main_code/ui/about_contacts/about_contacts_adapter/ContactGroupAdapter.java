package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseExpandableListAdapter;

import com.doubleq.model.linkman.group_list.DataContactsGroup;
import com.doubleq.model.linkman.group_list.DataContactsGroupChild;
import com.doubleq.xm6leefunz.R;

import java.util.List;

public class ContactGroupAdapter extends BaseExpandableListAdapter{

    Context context;
    ImageView img_parent_toright;
    private List<DataContactsGroup> gList;
    public ContactGroupAdapter(List<DataContactsGroup> gList, Context context) {
        this.gList = gList;
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return gList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gList.get(groupPosition).getDataContactsGroupChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gList.get(groupPosition).getDataContactsGroupChildList().get(childPosition);
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
        ContactGroupAdapter.GroupHolder holder;
        if (convertView == null) {
            holder = new ContactGroupAdapter.GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_g_contacts, null);
            holder.tv_contacts_parent_name = convertView.findViewById(R.id.frag_tv_contact_g_parent_name);
            holder.tv_contacts_parent_now = convertView.findViewById(R.id.frag_tv_contact_g_parent_now);
            holder.tv_contacts_parent_all = convertView.findViewById(R.id.frag_tv_contact_g_parent_all);
            convertView.setTag(holder);
        } else {
            holder = (ContactGroupAdapter.GroupHolder) convertView.getTag();
        }
        holder.tv_contacts_parent_name.setText(gList.get(groupPosition).getTv_parent_name_g());
        holder.tv_contacts_parent_now.setText(gList.get(groupPosition).getTv_parent_now_g());
        holder.tv_contacts_parent_all.setText(gList.get(groupPosition).getTv_parent_all_g());
//        Log.e("isExpanded","isExpanded="+isExpanded+"-------------------------------------------------------------"+groupPosition);
        img_parent_toright = convertView.findViewById(R.id.frag_img_contact_toright);

        for(int i = 0;i<getGroupCount();i++) {
//            Log.e("isExpanded","isExpanded="+isExpanded+"---------------*****---------"+groupPosition);
            if (i == groupPosition) {
//                Log.e("isExpanded","isExpanded="+isExpanded+"---------------------------------------------------------"+groupPosition);
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
        ContactGroupAdapter.ChildHolder holder;
        if (convertView == null) {
            holder = new ContactGroupAdapter.ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_g_contacts, null);
            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_g_name);
            convertView.setTag(holder);
        } else {
            holder = (ContactGroupAdapter.ChildHolder) convertView.getTag();
        }
        DataContactsGroupChild dataContactsGroupChild = gList.get(groupPosition).getDataContactsGroupChildList().get(childPosition);
        holder.tv_contacts_child_name.setText(dataContactsGroupChild.getTv_child_name_g());
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
        TextView tv_contacts_child_name;
    }

}
