//package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.doubleq.model.DataLinkTotal;
//import com.doubleq.xm6leefunz.R;
//
//import java.util.List;
//
//import jp.wasabeef.glide.transformations.CropCircleTransformation;
//
//public class LinkFriendAdapter22 extends BaseExpandableListAdapter {
//
//    Context context;
//
//    private List<DataLinkTotal> mList;
//    public LinkFriendAdapter22(List<DataLinkTotal> mList, Context context) {
//        this.mList = mList;
//        this.context = context;
//    }
//    @Override
//    public int getGroupCount() {
//        return mList.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
////        mList.get(groupPosition).getFriend_list()
//        return mList.get(groupPosition).getDataLinkChildList().size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return mList.get(groupPosition);
//    }
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return mList.get(groupPosition).getDataLinkChildList().get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
//        convertView =  ((LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_mng_contacts, null);
//
////            convertView = LayoutInflater.from(context).inflate(R.layout.item_mng_contacts, null);
//        tv_contacts_parent_name = convertView.findViewById(R.id.frag_tv_contact_m_parent_name);
//        mLinManage = convertView.findViewById(R.id.cusmanage_lin_top);
//        img_parent_toright = convertView.findViewById(R.id.frag_img_contact_toright);
//        tv_contacts_parent_name.setText(mList.get(groupPosition).getGroupName());
//        if (groupPosition<5)
//        {
//            if (isExpanded) {
//                img_parent_toright.setImageResource(R.drawable.to_down);
//                tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.doubleq_msg_num));
//            } else {
//                img_parent_toright.setImageResource(R.drawable.to_right);
//                tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.grey555));
//            }
//        }else {
//            img_parent_toright.setVisibility(View.GONE);
//            mLinManage.setBackgroundColor(context.getResources().getColor(R.color.grayeee));
//        }
//        Log.e("groupPosition",groupPosition+"----------------------------------------------------------------------------------");
//        return convertView;
//    }
//    TextView tv_contacts_parent_name;
//    LinearLayout mLinManage;
//    ImageView img_parent_toright;
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LinkFriendAdapter22.ChildHolder holder;
//        if (convertView == null) {
//            holder = new LinkFriendAdapter22.ChildHolder();
//            convertView = LayoutInflater.from(context).inflate(
//                    R.layout.item_child_mng_contacts, null);
//            holder.img_contacts_child_head = convertView.findViewById(R.id.frag_img_contacts_child_f_head);
//            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_f_name);
////            holder.tv_contacts_child_state = convertView.findViewById(R.id.frag_tv_contacts_child_f_state);
////            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
//            convertView.setTag(holder);
//        } else {
//            holder = (LinkFriendAdapter22.ChildHolder) convertView.getTag();
//        }
//        DataLinkTotal.DataLinkTotalChild dataContactsManageChild = mList.get(groupPosition).getDataLinkChildList().get(childPosition);
//        Glide.with(context).load(R.drawable.img_personal_head)
//                .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
//                .into(holder.img_contacts_child_head);
//        holder.tv_contacts_child_name.setText(dataContactsManageChild.getNickName());
////        holder.tv_contacts_child_state.setText(dataContactsManageChild.get());
////        holder.tv_contacts_child_motto.setText(dataContactsManageChild.getTv_child_motto_m());
//        return convertView;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//
//    class GroupHolder {
//        TextView tv_contacts_parent_name;
//        LinearLayout mLinManage;
//        ImageView img_parent_toright;
//    }
//
//    class ChildHolder {
//        TextView tv_contacts_child_name, tv_contacts_child_state,tv_contacts_child_motto;
//        ImageView img_contacts_child_head;
//    }
//
//}
