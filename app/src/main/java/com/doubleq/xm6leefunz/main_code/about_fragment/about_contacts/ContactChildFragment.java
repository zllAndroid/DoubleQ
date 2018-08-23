package com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.doubleq.model.linkman.good_friends.DataContactsFriend;
import com.doubleq.model.linkman.good_friends.DataContactsFriendChild;
import com.doubleq.model.linkman.group_list.DataContactsGroup;
import com.doubleq.model.linkman.group_list.DataContactsGroupChild;
import com.doubleq.model.linkman.group_manager.DataContactsManage;
import com.doubleq.model.linkman.group_manager.DataContactsManageChild;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts.about_contacts_adapter.ContactFriendAdapter;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts.about_contacts_adapter.ContactGroupAdapter;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_contacts.about_contacts_adapter.ContactManageAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactChildFragment extends Fragment {

    private ExpandableListView fListView;
    private ExpandableListView gListView;
    private ExpandableListView mListView;
    private ContactFriendAdapter contactFriendAdapter;
    private ContactGroupAdapter contactGroupAdapter;
    private ContactManageAdapter contactManageAdapter;
    public ContactChildFragment() {
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        String text = (String) bundle.get("text");
//        View view = getLayoutInflater().inflate(R.layout., null);
        if (view==null) {
            if (position == 0) {
                view = inflater.inflate(R.layout.fragment_contacts_friends, container, false);
                initFriend(view);
            }
            else if (position == 1)
            {
                view = inflater.inflate(R.layout.fragment_contacts_manage,container,false);
                initManage(view);
            }
            else {
                view = inflater.inflate(R.layout.fragment_contacts_groups, container, false);
                initGroup(view);
            }
        }

        return view;
    }

    private List<DataContactsFriend> fList;
    private void initDataFriend() {
        fList = new ArrayList<>();
        {
            List<DataContactsFriendChild> fcList = new ArrayList<>();
            DataContactsFriendChild dataContactsFriendChild = new DataContactsFriendChild(R.drawable.img_personal_head,"Tom","WiFi在线","知足常乐");
            DataContactsFriendChild dataContactsFriendChild2 = new DataContactsFriendChild(R.drawable.img_personal_head,"Amy","WiFi在线","欢喜就好");
            DataContactsFriendChild dataContactsFriendChild3 = new DataContactsFriendChild(R.drawable.img_personal_head,"Ann","4G在线","浪起来~");
            fcList.add(dataContactsFriendChild);
            fcList.add(dataContactsFriendChild2);
            fcList.add(dataContactsFriendChild3);
            DataContactsFriend dataContactsFriend = new DataContactsFriend("我的好友","10","20",fcList);
            fList.add(dataContactsFriend);
        }
        {
            List<DataContactsFriendChild> fcList = new ArrayList<>();
            DataContactsFriendChild dataContactsFriendChild = new DataContactsFriendChild(R.drawable.img_personal_head,"Bob","WiFi在线","知足常乐");
            DataContactsFriendChild dataContactsFriendChild2 = new DataContactsFriendChild(R.drawable.img_personal_head,"Aha","WiFi在线","欢喜就好");
            DataContactsFriendChild dataContactsFriendChild3 = new DataContactsFriendChild(R.drawable.img_personal_head,"Young","4G在线","浪起来~");
            fcList.add(dataContactsFriendChild);
            fcList.add(dataContactsFriendChild2);
            fcList.add(dataContactsFriendChild3);
            DataContactsFriend dataContactsFriend = new DataContactsFriend("大学那些人","25","56",fcList);
            fList.add(dataContactsFriend);
        }
        {
            List<DataContactsFriendChild> fcList = new ArrayList<>();
            DataContactsFriendChild dataContactsFriendChild = new DataContactsFriendChild(R.drawable.img_personal_head,"Aha","4G在线","知足常乐");
            DataContactsFriendChild dataContactsFriendChild2 = new DataContactsFriendChild(R.drawable.img_personal_head,"Amy","WiFi在线","欢喜就好");
            DataContactsFriendChild dataContactsFriendChild3 = new DataContactsFriendChild(R.drawable.img_personal_head,"Bob","WiFi在线","浪起来~");
            fcList.add(dataContactsFriendChild);
            fcList.add(dataContactsFriendChild2);
            fcList.add(dataContactsFriendChild3);
            DataContactsFriend dataContactsFriend = new DataContactsFriend("年轻人","136","156",fcList);
            fList.add(dataContactsFriend);
        }
    }

    List<DataContactsManage> mList = new ArrayList<>();
    private void initDataManage() {
        mList = new ArrayList<>();
        {
            List<DataContactsManageChild> mcList = new ArrayList<>();
            DataContactsManageChild dataContactsManageChild = new DataContactsManageChild(R.drawable.img_personal_head,"Amy","WiFi在线","知足常乐");
            DataContactsManageChild dataContactsManageChild2 = new DataContactsManageChild(R.drawable.img_personal_head,"Catherine","WiFi在线","欢喜就好");
            DataContactsManageChild dataContactsManageChild3 = new DataContactsManageChild(R.drawable.img_personal_head,"Kyrie","4G在线","浪起来~");
            mcList.add(dataContactsManageChild);
            mcList.add(dataContactsManageChild2);
            mcList.add(dataContactsManageChild3);
            DataContactsManage dataContactsManage = new DataContactsManage("特别关心","3","8",mcList);
            mList.add(dataContactsManage);
        }
        {
            List<DataContactsManageChild> mcList = new ArrayList<>();
            DataContactsManageChild dataContactsManageChild = new DataContactsManageChild(R.drawable.img_personal_head,"Ann","WiFi在线","知足常乐");
            DataContactsManageChild dataContactsManageChild2 = new DataContactsManageChild(R.drawable.img_personal_head,"Tom","WiFi在线","欢喜就好");
            DataContactsManageChild dataContactsManageChild3 = new DataContactsManageChild(R.drawable.img_personal_head,"Tony","4G在线","浪起来~");
            mcList.add(dataContactsManageChild);
            mcList.add(dataContactsManageChild2);
            mcList.add(dataContactsManageChild3);
            DataContactsManage dataContactsManage = new DataContactsManage("我的好友","120","169",mcList);
            mList.add(dataContactsManage);
        }
        {
            List<DataContactsManageChild> mcList = new ArrayList<>();
            DataContactsManageChild dataContactsManageChild = new DataContactsManageChild(R.drawable.img_personal_head,"Ann","WiFi在线","知足常乐");
            DataContactsManageChild dataContactsManageChild2 = new DataContactsManageChild(R.drawable.img_personal_head,"Bob","WiFi在线","欢喜就好");
            DataContactsManageChild dataContactsManageChild3 = new DataContactsManageChild(R.drawable.img_personal_head,"Tony","4G在线","浪起来~");
            mcList.add(dataContactsManageChild);
            mcList.add(dataContactsManageChild2);
            mcList.add(dataContactsManageChild3);
            DataContactsManage dataContactsManage = new DataContactsManage("同事","120","169",mcList);
            mList.add(dataContactsManage);
        }
    }

    List<DataContactsGroup> gList = new ArrayList<>();
    private void initDataGroup() {
        gList = new ArrayList<>();
        {
            List<DataContactsGroupChild> gcList = new ArrayList<>();
            DataContactsGroupChild dataContactsGroupChild = new DataContactsGroupChild("嘻嘻哈哈");
            DataContactsGroupChild dataContactsGroupChild2 = new DataContactsGroupChild("咕咕呱呱");
            DataContactsGroupChild dataContactsGroupChild3 = new DataContactsGroupChild("劈里啪啦");
            gcList.add(dataContactsGroupChild);
            gcList.add(dataContactsGroupChild2);
            gcList.add(dataContactsGroupChild3);
            DataContactsGroup dataContactsGroup = new DataContactsGroup("我的管理群","35","56",gcList);
            gList.add(dataContactsGroup);
        }
        {
            List<DataContactsGroupChild> gcList = new ArrayList<>();
            DataContactsGroupChild dataContactsGroupChild = new DataContactsGroupChild("6leefun总群");
            DataContactsGroupChild dataContactsGroupChild2 = new DataContactsGroupChild("6leefun技术组讨论群");
            DataContactsGroupChild dataContactsGroupChild3 = new DataContactsGroupChild("同事群");
            DataContactsGroupChild dataContactsGroupChild4 = new DataContactsGroupChild("同学群");
            DataContactsGroupChild dataContactsGroupChild5 = new DataContactsGroupChild("我的家人们");
            DataContactsGroupChild dataContactsGroupChild6 = new DataContactsGroupChild("嘻嘻哈哈");
            DataContactsGroupChild dataContactsGroupChild7 = new DataContactsGroupChild("咕咕呱呱");
            DataContactsGroupChild dataContactsGroupChild8 = new DataContactsGroupChild("劈里啪啦");
            gcList.add(dataContactsGroupChild);
            gcList.add(dataContactsGroupChild2);
            gcList.add(dataContactsGroupChild3);
            gcList.add(dataContactsGroupChild4);
            gcList.add(dataContactsGroupChild5);
            gcList.add(dataContactsGroupChild6);
            gcList.add(dataContactsGroupChild7);
            gcList.add(dataContactsGroupChild8);
            DataContactsGroup dataContactsGroup = new DataContactsGroup("我加入的群","89","100",gcList);
            gList.add(dataContactsGroup);
        }
    }
    private void initFriend(View view) {
        fListView = view.findViewById(R.id.frag_listview_contacts_friend);
        initDataFriend();
        contactFriendAdapter = new ContactFriendAdapter(fList,this.getActivity());
        fListView.setAdapter(contactFriendAdapter);
        fListView.setGroupIndicator(null);
    }

    private void initManage(View view) {
        mListView = view.findViewById(R.id.frag_listview_contacts_manage);
        initDataManage();
        contactManageAdapter = new ContactManageAdapter(mList,this.getActivity());
        mListView.setAdapter(contactManageAdapter);
        mListView.setGroupIndicator(null);
    }

    private void initGroup(View view) {
        gListView = view.findViewById(R.id.frag_listview_contacts_group);
        initDataGroup();
        contactGroupAdapter = new ContactGroupAdapter(gList,this.getActivity());
        gListView.setAdapter(contactGroupAdapter);
        gListView.setGroupIndicator(null);
    }
}
