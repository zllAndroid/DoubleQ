package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class LinkGroupAdapter extends BaseExpandableListAdapter {
    Context context;
    List<DataLinkGroupList.RecordBean.GroupInfoListBean> mGroupList;

    RealmLinkFriendHelper realmLinkFriendHelper;
    public LinkGroupAdapter(Context context, List<DataLinkGroupList.RecordBean.GroupInfoListBean> mGroupList) {
        this.mGroupList = mGroupList;
        this.context = context;
        realmLinkFriendHelper = new RealmLinkFriendHelper(context);
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
        if (mGroupList.get(groupPosition).getType().equals("1"))
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
      final   LinkGroupAdapter.ChildHolder holder;
        if (convertView == null) {
            holder = new LinkGroupAdapter.ChildHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_child_mng_contacts, null);
            holder.img_contacts_child_head = convertView.findViewById(R.id.frag_img_contacts_child_f_head);
            holder.tv_contacts_child_name = convertView.findViewById(R.id.frag_tv_contacts_child_f_name);
//            holder.tv_contacts_child_state = convertView.findViewById(R.id.frag_tv_contacts_child_f_state);
//            holder.tv_contacts_child_motto = convertView.findViewById(R.id.frag_tv_contacts_child_f_motto);
            convertView.setTag(holder);
        } else {
            holder = (LinkGroupAdapter.ChildHolder) convertView.getTag();
        }
        final  DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);

        if (!StrUtils.isEmpty(groupListBean.getGroupOfId())) {
            String imgPath = realmLinkFriendHelper.queryLinkFriendReturnImgPath(groupListBean.getGroupOfId());
            if (imgPath!=null) {
                holder.img_contacts_child_head.setImageURI(Uri.fromFile(new File(imgPath)));
//                Glide.with(context)
//                        .load(imgPath)
//                        .error(R.drawable.qun_head)
//                        .dontAnimate()
//                        .bitmapTransform(new CropCircleTransformation(context))
////
////                        .listener(new RequestListener<String, GlideDrawable>() {
////                            @Override
////                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//////                                加载错误时，加载网络图片
////                                realmLinkFriendHelper.deleteRealmFriend(groupListBean.getGroupOfId());
////                                Glide.with(context).load(groupListBean.getHeadImg())
////                                        .error(R.drawable.mine_head)
////                                        .bitmapTransform(new CropCircleTransformation(context))
////                                        .crossFade(1000) .into(holder.img_contacts_child_head);
////                                return false;
////                            }
////
////                            @Override
////                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
////                                return false;
////                            }
////                        })
//                        .into(holder.img_contacts_child_head);
            }else {
                Glide.with(context)
                        .load(groupListBean.getHeadImg())
                        .error(R.drawable.qun_head)
                        .bitmapTransform(new CropCircleTransformation(context))
                        .into(holder.img_contacts_child_head);
            }


//            Glide.with(context)
//                    .load(groupListBean.getHeadImg())
//                    .error(R.drawable.img_personal_head)
//                    .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
//                    .into(holder.img_contacts_child_head);
            holder.tv_contacts_child_name.setText(groupListBean.getNickName());
        }
//        holder.tv_contacts_child_state.setText(dataContactsManageChild.get());
//        holder.tv_contacts_child_motto.setText(dataContactsManageChild.getTv_child_motto_m());
        return convertView;
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
