package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmGroupMemberHelper;
import com.mding.model.DataAddQunDetails;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_chat.chat_group.group_realm.RealmGroupChatHeaderHelper;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
//群主或者群成员列表
public class GroupMemberQunzhuAdapter extends BaseQuickAdapter<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean,BaseViewHolder> {

    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> searchCityList;
    Context context;
    boolean isShowName;//是否显示用户名字
    boolean isGrouper;//是否群主
    RealmGroupChatHeaderHelper realmGroupChatHeaderHelper;
    RealmGroupMemberHelper realmGroupMemberHelper;
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
         realmGroupMemberHelper = new RealmGroupMemberHelper(context);


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
        ImageView mIvHead = (ImageView) helper.getView(R.id.item_iv_group_member_head);
        String headImg = realmGroupMemberHelper.queryLinkFriendReturnImgPath(item.getUserId());

        if (headImg!=null) {
//            ImageUtils.useBase64WithError(context, mIvHead,  headImg.substring(0, headImg.indexOf("_")), R.drawable.first_head_nor);
            ImageUtils.useBase64WithError(context, mIvHead, headImg, R.drawable.first_head_nor);
//            headImg = headImg.replace("data:image/png;base64,", "");
//            Log.e("ChatAcceptViewHolder", headImg);
//            byte[] decode = Base64.decode(headImg, Base64.DEFAULT);
//            Glide.with(context)
//                    .load(decode)
//                    .dontAnimate()
//                    .error(R.drawable.first_head_nor)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .into(mIvHead);
        }else
        {
            String headImg1 = item.getHeadImg();
//            ImageUtils.useBase64WithError(context, mIvHead,  headImg1.substring(0, headImg1.indexOf("_")), R.drawable.first_head_nor);
            ImageUtils.useBase64WithError(context, mIvHead, item.getHeadImg(), R.drawable.first_head_nor);
//            byte[] decode = Base64.decode(item.getHeadImg(), Base64.DEFAULT);
//            Glide.with(context)
//                    .load(decode)
//                    .dontAnimate()
//                    .error(com.mding.chatfeng.R.drawable.first_head_nor)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .into(mIvHead);
        }
//        if (imgPath!=null) {
//            mIvHead.setImageURI(Uri.fromFile(new File(imgPath)));
//        }else {
//            Glide.with(context)
//                    .load(item.getHeadImg())
//                    .error(R.drawable.first_head_nor)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .into(mIvHead);
//        }
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
