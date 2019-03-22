package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataNews;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice.NoticeAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice.NoticeDetailsActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//位置：联系人 - 通知
public class NoticeActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.notice_recyc)
    RecyclerView mRecyclerView;

//    @BindView(R.id.share_switch_accept)
//    SwitchButton shareSwitchAccept;
//    @BindView(R.id.share_switch_nouse)
//    SwitchButton shareSwitchNouse;
//    @BindView(R.id.share_switch_no)
//    SwitchButton shareSwitchNo;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String mShare= "1";
    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        includeTopTvTital.setText("通知");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(NoticeActivity.this));
        sendWeb(SplitWeb.messageList());
        initAdapter();

        SPUtils.put(this, AppConfig.LINKMAN_FRIEND_NUM,0);
        Intent intent = new Intent();
        intent.putExtra("num", 0);
        intent.setAction("action.addFriend");
        sendBroadcast(intent);
    }
    List<DataNews.RecordBean.ListInfoBean> mList=new ArrayList<>();
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "messageList":
                DataNews dataBlack = JSON.parseObject(responseText, DataNews.class);
                List<DataNews.RecordBean.ListInfoBean> listInfo = dataBlack.getRecord().getListInfo();
                if (listInfo!=null)
                {
                    if (listInfo.size()==0)
                    {
                        return;
                    }
                    mList.clear();
                    mList.addAll(listInfo);
                    initAdapter();
                }

                break;
            case "agreeFriend":
                if (blackAdapter!=null)
                {
//                    blackAdapter.delItem(positions);
                    mList.get(positions).setIsAgree("1");
                    blackAdapter.notifyItemChanged(positions);
                }
                ToastUtil.show("同意好友请求成功");
                dealAgreeFriend();
                break;
            case "refuseFriend":
                if (blackAdapter!=null)
                {
                    blackAdapter.delItem(delPositions);
                }
                ToastUtil.show("删除本条消息成功");
                break;
        }
    }

    private void dealAgreeFriend() {
        if (item!=null) {

//            CusChatData cusRealmChatMsg = new CusChatData();
//            cusRealmChatMsg.setCreated(TimeUtil.getTime());
//            cusRealmChatMsg.setMessage("我们已经是好友了，快来聊一聊吧");
//            cusRealmChatMsg.setMessageType("1");
//            cusRealmChatMsg.setReceiveId(item.getSendUserId());
//            cusRealmChatMsg.setSendId(SplitWeb.getUserId());
//            cusRealmChatMsg.setUserMessageType(1);
//            cusRealmChatMsg.setTotalId(item.getSendUserId()+SplitWeb.getUserId());
//            realmChatHelper.addRealmChat(cusRealmChatMsg);//更新聊天数据


//            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
//            cusJumpChatData.setHeadImg(item.getHeadImg());
//            cusJumpChatData.setFriendId(item.getSendUserId());
//            cusJumpChatData.setNickName(item.getNickName());
//            cusJumpChatData.setMsg("我们已经是好友了，快来聊一聊吧");
//            cusJumpChatData.setTime(TimeUtil.getTime());
//            cusJumpChatData.setNum(0);
//            realmHelper.addRealmMsg(cusJumpChatData);

//            send(SplitWeb.privateSend(item.getSendUserId(),"我们已经是好友了，快来聊一聊吧",ChatActivity.messageType, TimeUtil.getTime()));
//
//            Intent intent = new Intent();
//            intent.putExtra("message","我们已经是好友了，快来聊一聊吧");
//            intent.putExtra("id",item.getSendUserId());
//            intent.setAction("action.refreshMsgFragment");
//            sendBroadcast(intent);
        }
    }

    NoticeAdapter blackAdapter =null;
    DataNews.RecordBean.ListInfoBean item=null;
    public int positions;
    public int delPositions;
    private void initAdapter() {
        blackAdapter = new NoticeAdapter(this,mList,mItemTouchListener);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                positions=position;
                item = (DataNews.RecordBean.ListInfoBean)adapter.getItem(position);
                String id = item.getId();
                switch (view.getId())
                {
//                    点击行
                    case R.id.item_re_notice:
                        switch (item.getIsAgree())
                        {
                            case  "0":
                                IntentUtils.JumpToHaveObj(NoticeDetailsActivity.class,"id",item);
//                                AppManager.getAppManager().finishActivity();
                                break;
                            case "1":
                                ToastUtil.show("已经操作过，不能重复操作");
                                break;
                            case "2":
                                ToastUtil.show("已经操作过，不能重复操作");
                                break;
                        }

                        break;
//                    点击同意
                    case R.id.item_tv_click_ok:
                        switch (item.getIsAgree())
                        {
                            case  "0":
//                                final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
//                                cusJumpChatData.setHeadImg(item.getHeadImg());
//                                cusJumpChatData.setFriendId(item.getSendUserId());
//                                cusJumpChatData.setNickName(item.getNickName());
//                                cusJumpChatData.setMsg("新添加的好友");
//                                cusJumpChatData.setTime(TimeUtil.getTime());
//                                cusJumpChatData.setNum(0);
//                                realmHelper.addRealmMsg(cusJumpChatData);

                                sendWeb(SplitWeb.agreeFriend(id));
                                break;
                            case "1":
                                ToastUtil.show("已经同意该请求");
                                break;
                            case "2":
                                ToastUtil.show("已经拒绝该请求");
                                break;
                        }
                        break;
                }
            }
        });
    }
    NoticeAdapter.ItemTouchListener mItemTouchListener = new NoticeAdapter.ItemTouchListener() {
        @Override
        public void onLeftMenuClick(View view,final int position, String WaybillNum) {
//            ToastUtil.show("点击了删除");
            DialogUtils.showDialog("是否确定删除该条消息", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    delPositions=position;
                    DataNews.RecordBean.ListInfoBean listInfoBean = mList.get(position);
                    sendWeb(SplitWeb.refuseFriend(listInfoBean.getId(),"2"));
                }
            });
        }
    };
    @Override
    protected int getLayoutView() {
        return R.layout.activity_notice;
    }
}
