package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.model.DataLinkManList;
import com.mding.chatfeng.R;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class LinkFriendAdapter extends BaseExpandableListAdapter {
    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
    Context context;
    List<DataLinkManList.RecordBean.FriendListBean> mGroupList;
    public LinkFriendAdapter( Context context,List<DataLinkManList.RecordBean.FriendListBean> mGroupList
    ) {
        this.context = context;
        this.mGroupList = mGroupList;
        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(context);
    }
    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }
    public void notifyFriend() {
       notifyDataSetChanged();
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
        DataLinkManList.RecordBean.FriendListBean friendListBean = mGroupList.get(groupPosition);
        String groupName = friendListBean.getGroupName();
        if (groupName!=null) {
            if (friendListBean.getGroupName().equals("~")) {
                tv_contacts_parent_name.setText("#");
            } else {
                tv_contacts_parent_name.setText(friendListBean.getGroupName());
            }
        }
        if (friendListBean.getType().equals("1"))
        {
            if (isExpanded) {
                img_parent_toright.setImageResource(R.drawable.to_down);
                tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.doubleq_msg_num));
            } else {
                img_parent_toright.setImageResource(R.drawable.to_right);
                tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.grey555));
            }
            mLinManage.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else {
//            tv_contacts_parent_name.setText(mTotalList.get(groupPosition).getChart());
            img_parent_toright.setVisibility(View.GONE);
            tv_contacts_parent_name.setTextColor(context.getResources().getColor(R.color.app_theme));
            mLinManage.setBackgroundColor(context.getResources().getColor(R.color.linkfriend_bac));
        }
        return convertView;
    }
    TextView tv_contacts_parent_name;
    LinearLayout mLinManage;
    ImageView img_parent_toright;
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final  LinkFriendAdapter.ChildHolder holder;
        if (convertView == null) {
            holder = new LinkFriendAdapter.ChildHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_child_mng_contacts, null);
            holder.img_contacts_child_head = convertView.findViewById(R.id.frag_img_contacts_child_f_head);
            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_f_name);
//            holder.tv_contacts_child_state = convertView.findViewById(R.id.frag_tv_contacts_child_f_state);
//            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
            convertView.setTag(holder);
        } else {
            holder = (LinkFriendAdapter.ChildHolder) convertView.getTag();
        }
        if (mGroupList.size()>0)
        {
            if ( mGroupList.get(groupPosition).getGroupList().size()>0) {
                final DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);
                if (!StrUtils.isEmpty(groupListBean.getUserId())) {
                    String headImg = groupListBean.getHeadImg();
                    ImageUtils.useBase64(context, holder.img_contacts_child_head, headImg);
                    String name = groupListBean.getNickName();
                    if (!StrUtils.isEmpty(groupListBean.getRemarkName())) {
                        name = groupListBean.getRemarkName();
                    }
                    holder.tv_contacts_child_name.setText(name);
                }
            }
        }
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
