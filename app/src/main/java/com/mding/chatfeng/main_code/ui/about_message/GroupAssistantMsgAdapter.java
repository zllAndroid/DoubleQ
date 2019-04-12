package com.mding.chatfeng.main_code.ui.about_message;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class GroupAssistantMsgAdapter extends BaseQuickAdapter<CusHomeRealmData, BaseViewHolder> {
    Context context;
    public List<CusHomeRealmData> data;
    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
    public GroupAssistantMsgAdapter(Context context, List<CusHomeRealmData> data) {
//    public GroupAssistantMsgAdapter(Context context, List<CusHomeRealmData> data, ItemTouchListener mItemTouchListener) {
        super(R.layout.item_home_message, data);
        this.data=data;
        this.context=context;
//        this.mItemTouchListener=mItemTouchListener;
        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(context);
    }
    public void addData(CusHomeRealmData cusData) {
        data.add(0, cusData);
        notifyItemInserted(0);  //插入
    }
    public void removeData(int position) {
        data.remove(position);
        notifyItemRemoved(position);  //删除
    }
    public void notifyPosition(int position) {
        notifyItemRemoved(position);
    }
    public int getNumData() {
        int num=0;
        if (data!=null&&data.size()>0)
        {
            for (int i=0;i<data.size();i++)
            {
                CusHomeRealmData cusHomeRealmData = data.get(i);

                if (cusHomeRealmData!=null&&cusHomeRealmData.getMsgIsDisTurb()!=null&&!cusHomeRealmData.getMsgIsDisTurb().equals("2"))
                    num += cusHomeRealmData.getNum();
            }
        }
        return  num;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final CusHomeRealmData item) {
//        CusDataLinkFriend linkFriend = realmMsgInfoTotalHelper.queryLinkFriend(item.getFriendId());
        String type = item.getType();
//        String assistantType = item.getAssistantType();
//        if (type!=null&&assistantType!=null)
//            if (type.equals("2")&&assistantType.equals("2"))
//            {
////            TextView mTvNum = helper.getView(R.id.item_tv_num);
//                TextView mTvMsg = helper.getView(R.id.item_tv_msg);
//                ImageView mIvHead = (ImageView) helper.getView(R.id.item_iv_head);
//                helper.setText(R.id.item_tv_msg,item.getMsg());
//                helper.setText(R.id.item_tv_name,"群助手");
//                mTvMsg.setTextColor(context.getResources().getColor(R.color.app_theme));
//                Glide.with(context).load(R.drawable.msg_grouper)
//                        .bitmapTransform(new CropCircleTransformation(context))
//                        .into(mIvHead);
//                return;
//            }
        if (type.equals("2")) {
            if (!StrUtils.isEmpty(item.getFriendId())) {
                String imgPath = realmMsgInfoTotalHelper.queryLinkFriendReturnImgPath(item.getFriendId());
                ImageView mIvHead = (ImageView) helper.getView(R.id.item_iv_head);
                if (imgPath != null) {
                    Uri uri = Uri.fromFile(new File(imgPath));
                    mIvHead.setImageURI(uri);
                } else {
                    Glide.with(context).load(item.getHeadImg())
                            .error(R.drawable.qun_head)
                            .bitmapTransform(new CropCircleTransformation(context))
                            .into(mIvHead);
                }
            }
            helper.setText(R.id.item_tv_name, item.getNickName());
            helper.setText(R.id.item_tv_msg, item.getMsg());
            helper.setText(R.id.item_tv_time, TimeUtil.formatDisplayTime(item.getTime(), null));
//        helper.setText(R.id.item_tv_time,item.getTime());
            TextView mTvNum = helper.getView(R.id.item_tv_num);
            int num = 0;
            try {
                num = item.getNum();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (num == 0) {
                mTvNum.setVisibility(View.INVISIBLE);
            } else {
                mTvNum.setVisibility(View.VISIBLE);
                helper.setText(R.id.item_tv_num, item.getNum() + "");
            }

//        消息免打扰  的消息背景
//        if(item.getMsgIsDisTurb()!=null)
//            if (!item.getMsgIsDisTurb().equals("2"))
//                mTvNum.setBackgroundResource(R.drawable.linkman_news);
//            else {
//                mTvNum.setBackgroundResource(R.drawable.news_disturb);
//            }
            mTvNum.setBackgroundResource(R.drawable.news_disturb);
//        helper.addOnClickListener(R.id.item_tv_click_ok);
            helper.addOnClickListener(R.id.item_msg_re);
        }
    }


    //添加数据
    public void delItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        //必须调用这行代码
        notifyItemRangeChanged(position, data.size());
    }
    String id;
    SwipeItemLayout swipeLayout;
//    private ItemTouchListener mItemTouchListener;
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        swipeLayout = (SwipeItemLayout) holder.itemView;
        final View lMenu = holder.getView(R.id.item_notice_del_menu);
//        click(swipeLayout, lMenu,positions,data.get(positions).getNickName());
    }
//    private void click(final SwipeItemLayout swipeLayout, final  View view,final  int positions,String name) {
//        if (view != null) {
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mItemTouchListener.onLeftMenuClick(view,positions,id);
//                    swipeLayout.close();
//                }
//            });
//        }
//    }
//    public  interface ItemTouchListener {
//        void onLeftMenuClick(View view, int positions, String WaybillNum);
//    }
}