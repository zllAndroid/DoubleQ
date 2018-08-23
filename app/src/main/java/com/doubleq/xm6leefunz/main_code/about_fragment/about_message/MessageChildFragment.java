package com.doubleq.xm6leefunz.main_code.about_fragment.about_message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.home_msg.DataMsgFriend;
import com.doubleq.model.home_msg.DataMsgGroup;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_message.about_message_adapter.MsgFriendAdapter;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_message.about_message_adapter.MsgGroupAdapter;
import com.projects.zll.utilslibrarybyzll.aboututils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageChildFragment extends Fragment {

    RecyclerView fRecyclerview;
    RecyclerView gRecyclerview;
    public MessageChildFragment() {
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        String text = (String) bundle.get("text");
        if (view==null) {
            if (position == 0) {
                view = inflater.inflate(R.layout.fragment_message_friends, container, false);
                initFriend(view);
            } else {
                view = inflater.inflate(R.layout.fragment_message_groups, container, false);
                initGroup(view);
            }
        }
        return view;
    }

    List<DataMsgFriend> fList = new ArrayList<>();
    MsgFriendAdapter msgFriendAdapter = null;
    private void initDataFriend() {
        DataMsgFriend dataMsgFriend = new DataMsgFriend();
        dataMsgFriend.setMsg_f_image("http://osi6wlev1.bkt.clouddn.com/pic001.png");
        dataMsgFriend.setMsg_f_name("大哥");
        dataMsgFriend.setMsg_f_contants("等会泡个茶~");
        dataMsgFriend.setMsg_f_time("下午4:51");
        dataMsgFriend.setMsg_f_num("2");

        DataMsgFriend dataMsgFriend2 = new DataMsgFriend();
        dataMsgFriend2.setMsg_f_image("http://pbmyhukzs.bkt.clouddn.com/img_personal_head.png");
        dataMsgFriend2.setMsg_f_name("闺蜜");
        dataMsgFriend2.setMsg_f_contants("散步走起啊！");
        dataMsgFriend2.setMsg_f_time("下午2:35");
        dataMsgFriend2.setMsg_f_num("4");

        fList.clear();
        fList.add(dataMsgFriend);
        fList.add(dataMsgFriend2);
        fList.add(dataMsgFriend);
        fList.add(dataMsgFriend2);

        if (msgFriendAdapter==null) {
            msgFriendAdapter = new MsgFriendAdapter(fList, this.getActivity());
        }
        fRecyclerview.setAdapter(msgFriendAdapter);
        msgFriendAdapter.notifyDataSetChanged();
        msgFriendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show(""+position);
//                ChatActivity
                IntentUtils.JumpTo(ChatActivity.class);
            }
        });
    }

    List<DataMsgGroup> gList = new ArrayList<>();
    MsgGroupAdapter msgGroupAdapter = null;
    private void initDataGroup() {
        DataMsgGroup dataMsgGroup = new DataMsgGroup();
        dataMsgGroup.setMsg_g_image("http://pbmyhukzs.bkt.clouddn.com/first_page_taoqianggou2.png");
        dataMsgGroup.setMsg_g_name("瘦友群");
        dataMsgGroup.setMsg_g_contants("周末一起去郊游！");
        dataMsgGroup.setMsg_g_time("下午4:05");
        dataMsgGroup.setMsg_g_num("86");

        DataMsgGroup dataMsgGroup2 = new DataMsgGroup();
        dataMsgGroup2.setMsg_g_image("http://pbmyhukzs.bkt.clouddn.com/Conan.png");
        dataMsgGroup2.setMsg_g_name("柯南群");
        dataMsgGroup2.setMsg_g_contants("下午看柯南约起~");
        dataMsgGroup2.setMsg_g_time("上午9:20");
        dataMsgGroup2.setMsg_g_num("99+");

        gList.clear();
        gList.add(dataMsgGroup);
        gList.add(dataMsgGroup2);
        gList.add(dataMsgGroup);
        gList.add(dataMsgGroup2);

        if (msgGroupAdapter==null) {
            msgGroupAdapter = new MsgGroupAdapter(gList, this.getActivity());
        }
        gRecyclerview.setAdapter(msgGroupAdapter);
        msgGroupAdapter.notifyDataSetChanged();
    }

    private void initFriend(View view) {
        fRecyclerview = view.findViewById(R.id.frag_home_recyc_friends);
        fRecyclerview.setHasFixedSize(true);
        fRecyclerview.setNestedScrollingEnabled(false);
        fRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        fRecyclerview.setLayoutManager(new GridLayoutManager(this.getActivity(),1));
        initDataFriend();
    }

    private void initGroup(View view) {
        gRecyclerview = view.findViewById(R.id.frag_home_recyc_groups);
        gRecyclerview.setHasFixedSize(true);
        gRecyclerview.setNestedScrollingEnabled(false);
        gRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        gRecyclerview.setLayoutManager(new GridLayoutManager(this.getActivity(),1));
        initDataGroup();
    }
}
