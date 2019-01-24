package com.doubleq.xm6leefunz.main_code.ui.about_message.about_message_adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.realm_data.CusDataRealmMsg;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class MsgAdapter extends BaseQuickAdapter<CusHomeRealmData, BaseViewHolder> {
    Context context;
    public List<CusHomeRealmData> data;
    RealmLinkFriendHelper realmLinkFriendHelper;
    public MsgAdapter(Context context, List<CusHomeRealmData> data, ItemTouchListener mItemTouchListener) {
        super(R.layout.item_home_message, data);
        this.data=data;
        this.context=context;
        this.mItemTouchListener=mItemTouchListener;
        realmLinkFriendHelper = new RealmLinkFriendHelper(context);
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
                if (cusHomeRealmData!=null)
                num += cusHomeRealmData.getNum();
            }
        }
        return  num;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final CusHomeRealmData item) {
//        CusDataLinkFriend linkFriend = realmLinkFriendHelper.queryLinkFriend(item.getFriendId());
        if (!StrUtils.isEmpty(item.getFriendId())) {
            String imgPath = realmLinkFriendHelper.queryLinkFriendReturnImgPath(item.getFriendId());
           final int errorImg;
            if (item.getType().equals("1"))
            {
                errorImg=R.drawable.mine_head;
            }else
            {
                errorImg=R.drawable.qun_head;
            }
            ImageView mIvHead = (ImageView) helper.getView(R.id.item_iv_head);
            if (imgPath != null) {
                mIvHead.setImageURI(Uri.fromFile(new File(imgPath)));
//                Glide.with(context).load(imgPath)
//                        .error(errorImg)
//                        .bitmapTransform(new CropCircleTransformation(context))
//                        .into((ImageView) helper.getView(R.id.item_iv_head));
            } else {
                Glide.with(context).load(item.getHeadImg())
                        .error(errorImg)
                        .bitmapTransform(new CropCircleTransformation(context))
//                        .crossFade(1000)
                        .into(mIvHead);
            }
        }
        helper.setText(R.id.item_tv_name,item.getNickName());
        helper.setText(R.id.item_tv_msg,item.getMsg());
        helper.setText(R.id.item_tv_time, TimeUtil.formatDisplayTime(item.getTime(),null));
//        helper.setText(R.id.item_tv_time,item.getTime());
        TextView mTvNum = helper.getView(R.id.item_tv_num);
        int num =0;
        try {
            num =item.getNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num==0) {
            mTvNum .setVisibility(View.INVISIBLE);
        }else
        {
            mTvNum .setVisibility(View.VISIBLE);
            helper.setText(R.id.item_tv_num,item.getNum()+"");
        }
        helper.addOnClickListener(R.id.item_tv_click_ok);
        helper.addOnClickListener(R.id.item_msg_re);
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
    private ItemTouchListener mItemTouchListener;
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        swipeLayout = (SwipeItemLayout) holder.itemView;
        final View lMenu = holder.getView(R.id.item_notice_del_menu);
        click(swipeLayout, lMenu,positions,data.get(positions).getNickName());
    }
    private void click(final SwipeItemLayout swipeLayout, final  View view,final  int positions,String name) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemTouchListener.onLeftMenuClick(view,positions,id);
                    swipeLayout.close();
                }
            });
        }
    }
    public  interface ItemTouchListener {
        void onLeftMenuClick(View view, int positions, String WaybillNum);
    }
}