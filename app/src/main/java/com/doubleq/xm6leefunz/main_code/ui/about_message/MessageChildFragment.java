package com.doubleq.xm6leefunz.main_code.ui.about_message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.home_msg.DataMsgFriend;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_message.about_message_adapter.MsgFriendAdapter;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageChildFragment extends Fragment {

    RecyclerView fRecyclerview;
    public MessageChildFragment() {
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_message, container, false);
        }
        initFriend(view);
        return view;
    }
    private void initFriend(View view) {
        fRecyclerview = view.findViewById(R.id.frag_home_recyc_friends);
        fRecyclerview.setHasFixedSize(true);
        fRecyclerview.setNestedScrollingEnabled(false);
        fRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        fRecyclerview.setLayoutManager(new GridLayoutManager(this.getActivity(),1));
        initDataFriend();
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




}
