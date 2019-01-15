package com.doubleq.xm6leefunz.main_code.mains;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.MessageEvent;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.BaseFilePathUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;

import app.dinus.com.loadingdrawable.LoadingView;
import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.LoadingRendererFactory;
import app.dinus.com.loadingdrawable.render.scenery.ElectricFanLoadingRenderer;
import butterknife.BindView;

public class LoadDataActivity extends BaseActivity {


    @BindView(R.id.electric_fan_view)
    LoadingView electricFanView;


    ACache aCache;
    RealmLinkFriendHelper realmLinkFriendHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();

        aCache =  ACache.get(this);
        realmLinkFriendHelper = new RealmLinkFriendHelper(this);
//        ElectricFanLoadingRenderer.Builder builder = new ElectricFanLoadingRenderer.Builder(this);
//        electricFanView.setLoadingRenderer(builder.build());

//        sendWebHaveData("获取数据中...","获取成功");
        sendWeb(SplitWeb.getGroupManage());
        sendWeb(SplitWeb.getFriendList());
        timer.start();
    }
    int durlation=5000;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_load_data;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
    boolean isFriend=false;
    boolean isGroup=false;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        String responseText = messageEvent.getMessage();
        String isSucess = HelpUtils.HttpIsSucess(responseText);
        if (isSucess.equals(AppAllKey.CODE_OK)) {
            dealDataMsg(responseText);
        }

//        if (timer == null) {
//            timer = new Timer();
//            timer.schedule(task, 1500);
//        }
    }
    private CountDownTimer timer =new CountDownTimer(durlation, 1000) {
        @Override
        public void onTick(long l) {
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
            Log.e("CountDownTimer","onTick="+l+"");
        }
        @Override
        public void onFinish() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                electricFanView.stopNestedScroll();
            }
            Log.e("CountDownTimer","onFinish="+durlation+"");
            if (isFriend&&isGroup)
            {
                IntentUtils.JumpFinishTo(LoadDataActivity.this,MainActivity.class);
                overridePendingTransition(0,0);
//                new Handler().postDelayed(new Runnable(){
//
//                    public void run() {
//
////                      mHandler.sendEmptyMessageDelayed(LOAD_SUCCESS, 500);
//                        IntentUtils.JumpFinishTo(LoadDataActivity.this,MainActivity.class);
//                        overridePendingTransition(0,0);
//                    }
//
//                }, 7333);

            }

        }
    };
    private void dealDataMsg(String responseText) {
        String method = HelpUtils.backMethod(responseText);
        String md5 = HelpUtils.backMD5(responseText);
        if (!StrUtils.isEmpty(md5))
            SPUtils.put(this, AppConfig.KEY_MD5,md5);
        switch (method)
        {
            case "getFriendList":
                DataLinkManList dataLinkManList = JSON.parseObject(responseText, DataLinkManList.class);
                DataLinkManList.RecordBean record = dataLinkManList.getRecord();
                if (record==null)
                {
                    return;
                }

                dealFriendData(record);
//                sendWeb(SplitWeb.getGroupManage());
                isFriend=true;

                break;
            case "getGroupManage":
                DataLinkGroupList dataLinkGroupList = JSON.parseObject(responseText, DataLinkGroupList.class);
                DataLinkGroupList.RecordBean  record_group = dataLinkGroupList.getRecord();
                if (record_group==null)
                {
                    return;
                }

                dealGroupData(record_group);
                isGroup=true;
                break;
        }
    }

    private void dealGroupData(DataLinkGroupList.RecordBean  record_group) {

        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record_group.getGroupInfoList();
        if (group_info_list.size()>0) {
//            try {
                for (int i = 0; i < group_info_list.size(); i++) {
                    String userId = group_info_list.get(i).getGroupList().get(0).getGroupOfId();
                    String groupName = group_info_list.get(i).getGroupName();
                    if (group_info_list.get(i).getType().equals("1")) {
                        if (StrUtils.isEmpty(userId)) {
                            group_info_list.get(i).getGroupList().remove(0);
                        }
                        if (StrUtils.isEmpty(groupName)) {
                            group_info_list.remove(i);
                        }
                    }
                    if (group_info_list.get(i).getType().equals("2"))
                        dealGroupRealm(group_info_list, i);
                }
            String json_group = JSON.toJSON(record_group).toString();
            aCache.remove(AppAllKey.GROUD_DATA);
            aCache.put(AppAllKey.GROUD_DATA, json_group);

//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    private void dealGroupRealm(List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        for (int j=0;j<groupList.size();j++)
        {
            final String modified = groupList.get(j).getModified();
            final String friendId = groupList.get(j).getGroupOfId();
            final String headImg = groupList.get(j).getHeadImg();
            CusDataLinkFriend cusDataLinkFriend = realmLinkFriendHelper.queryLinkFriend(friendId);
            if (StrUtils.isEmpty(headImg))
            {
                return;
            }
            if (cusDataLinkFriend!=null) {

                String time = cusDataLinkFriend.getTime();
                if (modified!=null&&!modified.equals(time))
                {
                    setGlideData(true,false,modified, friendId, headImg);
                }
                else {
                    setGlideData(true,false,modified, friendId, headImg);
                }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
            }else {
                setGlideData(false,false,modified, friendId, headImg);
            }
        }
    }

    private void setGlideData(final boolean isSame,final boolean isFriend,final String modified, final String friendId, final String headImg) {
        Glide.with(this)
                .load(headImg)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                        File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_FRIEND, friendId, modified);
                        if (isSame)
                            realmLinkFriendHelper.updateHeadPath(friendId, file.toString(), headImg, modified);
                        else
                        {
                            CusDataLinkFriend linkFriend = new CusDataLinkFriend();
                            linkFriend.setHeadImg(headImg);
                            linkFriend.setFriendId(friendId);
                            linkFriend.setTime(modified);
                            linkFriend.setImgPath(file.toString());
                            if (isFriend)
                                linkFriend.setWhoType("1");
                            else
                                linkFriend.setWhoType("2");
                            realmLinkFriendHelper.addRealmLinkFriend(linkFriend);
                        }
                    }
                });
    }

    private void dealFriendData(DataLinkManList.RecordBean record) {
        List<DataLinkManList.RecordBean.FriendListBean> friendList = record.getFriendList();
        if (friendList.size()>0)
            for (int i=0;i<friendList.size();i++)
            {
                String userId = friendList.get(i).getGroupList().get(0).getUserId();
                String groupName = friendList.get(i).getGroupName();
                if (friendList.get(i).getType().equals("1"))
                {
                    if (StrUtils.isEmpty(userId)) {
                        friendList.get(i).getGroupList().remove(0);
                    }
                    if (StrUtils.isEmpty(groupName)) {
                        friendList.remove(i);
                    }
                }
                dealFriendRealm(friendList, i);
            }
        String json = JSON.toJSON(record).toString();
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, json);
    }

    private void dealFriendRealm(List<DataLinkManList.RecordBean.FriendListBean> friendList, int i) {
        if (friendList.get(i).getType().equals("2"))
        {
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();

            for (int j=0;j<groupList.size();j++) {
                final String modified = groupList.get(j).getModified();
                final String friendId = groupList.get(j).getUserId();
                final String headImg = groupList.get(j).getHeadImg();
                CusDataLinkFriend cusDataLinkFriend = realmLinkFriendHelper.queryLinkFriend(friendId);
                if (StrUtils.isEmpty(headImg))
                {
                    return;
                }
                if (cusDataLinkFriend!=null) {

                    String time = cusDataLinkFriend.getTime();
                    if ( !modified.equals(time))
                    {
                        setGlideData(true,true,modified, friendId, headImg);
                    }else {
                        setGlideData(false,true,modified, friendId, headImg);
                    }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
                }else {
                    setGlideData(false,true,modified, friendId, headImg);
                }
//                if (cusDataLinkFriend != null) {
//                    if (StrUtils.isEmpty(headImg)) {
//                        return;
//                    }
//                    String time = cusDataLinkFriend.getTime();
//                    boolean equals = modified.equals(time);
//                    setGlideData(!equals,true,modified, friendId, headImg);
//                }
            }
        }
    }
}
