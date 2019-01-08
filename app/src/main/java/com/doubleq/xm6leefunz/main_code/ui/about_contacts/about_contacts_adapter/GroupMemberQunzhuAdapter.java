package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.model.DataAddQunDetails;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_chat.chat_group.group_realm.RealmGroupChatHeaderHelper;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
//群主或者群成员列表
public class GroupMemberQunzhuAdapter extends BaseQuickAdapter<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean,BaseViewHolder> {

    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> searchCityList;
    Context context;
    boolean isShowName;//是否显示用户名字
    boolean isGrouper;//是否群主
    RealmGroupChatHeaderHelper realmGroupChatHeaderHelper;
    public GroupMemberQunzhuAdapter(Context context,
                                    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> searchCityList,
                                    boolean isShowName,boolean isGrouper)
    {
        super(R.layout.item_group_data_member,searchCityList);
        this.context = context;
        this.searchCityList=searchCityList;
        this.isShowName=isShowName;
        this.isGrouper=isGrouper;
        realmGroupChatHeaderHelper = new RealmGroupChatHeaderHelper(context);
    }
    @Override
    protected void convert(BaseViewHolder helper, DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item)
    {
    }
    @Override
    public void onBindViewHolder(final BaseViewHolder helper, int positions) {
        super.onBindViewHolder(helper, positions);
        final DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = searchCityList.get(positions);
        String imgPath = realmGroupChatHeaderHelper.queryGroupChatReturnImgPath(item.getUserId());
        if (imgPath!=null) {
            Glide.with(context)
                    .load(imgPath)
                    .error(R.drawable.mine_head)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                                加载错误时，加载网络图片
                            realmGroupChatHeaderHelper.deleteRealmFriend(item.getUserId());
                            Glide.with(context).load(item.getHeadImg())
                                    .error(R.drawable.mine_head)
                                    .bitmapTransform(new CropCircleTransformation(context))
                                    .crossFade(1000).into((ImageView) helper.getView(R.id.item_iv_group_member_head));
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
                    .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
        }else {
            Glide.with(context)
                    .load(item.getHeadImg())
                    .error(R.drawable.mine_head)
                    .bitmapTransform(new CropCircleTransformation(context)).crossFade(1000)
                    .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
        }
//            Glide.with(context).load(item.getHeadImg())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .error(R.drawable.mine_head)
//                .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
        if (isShowName) {
            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
        }
        helper.setText(R.id.item_tv_group_member_name, item.getNickName());

        if (isGrouper)
        {
            if ((searchCityList.size()-2)==positions)
            {
                initInvitation(helper,true);
            }
            if ((searchCityList.size()-1)==positions)
            {
                initInvitation(helper,false);
            }
        }else {
            if ((searchCityList.size()-1)==positions)
            {
                initInvitation(helper,true);
            }
        }
    }

    private void initInvitation(BaseViewHolder helper,boolean isInvitation) {
        int img ;
        String howType;
//        是否邀请
        if (isInvitation)
        {
            img=R.drawable.group_add;
            howType ="邀请";
        }else {
            img=R.drawable.group_del;
            howType ="删除";
        }
        Glide.with(context).load(img)
                .bitmapTransform(new CropCircleTransformation(context))
                .crossFade(1000)
                .error(img)
                .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
        if (isShowName) {
            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
        }
        helper.setText(R.id.item_tv_group_member_name, howType);
    }
}
