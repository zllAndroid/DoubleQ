package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataSearchResult;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.GroupChatDetailsActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataAddActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataMixActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.GroupDataAddActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.doubleq.xm6leefunz.about_utils.IntentUtils.JumpToHaveOne;

//搜索好友界面
public class SearchActivity extends BaseActivity {

    //    RecyclerView tvRecyclerView;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    //    @BindView(R.id.include_top2_lin)
//    LinearLayout includeTop2Lin;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLinBack;
    @BindView(R.id.seach_ed_input)
    EditText seachEdInput;
    @BindView(R.id.seach_iv_close)
    ImageView seachIvClose;
    @BindView(R.id.seach_iv_find)
    ImageView seachIvFind;
    @BindView(R.id.seach_recyc)
    RecyclerView seachRecyc;
    @BindView(R.id.seach_lin_list)
    LinearLayout seachLinList;
    @BindView(R.id.seach_tv_noSearch)
    TextView seachTvNoSearch;
    @BindView(R.id.seach_lin_noSearch)
    RelativeLayout seachLinNoSearch;
    @BindView(R.id.search_recyc)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.search_tv_empty)
//    TextView searchTvEmpty;
    public  static  String SeacchKey="search_type";
    public  static  String SeacchFriend="1";
    public  static  String SeacchGroup="2";
    /**
     * 搜索关键字全部匹配的适配器
     */
    private SearchAdapter alterSearchAdapter = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String type;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent!=null)
            type = intent.getStringExtra(SeacchKey);

        includeTopTvTital.setText("搜索");


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));
        LinearLayout footer=  (LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
//        监听软键盘的回车键
        listenEnter();

//        mRecyclerView.addHeaderView(footer);
    }
    //    监听软键盘的回车键
    private void listenEnter() {
        seachEdInput.setImeOptions(EditorInfo.IME_ACTION_SEND);
        seachEdInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //处理事件
                    clickSearch();
                }
                return false;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        seachEdInput.setFocusable(true);
        seachEdInput.setFocusableInTouchMode(true);
        seachEdInput.requestFocus();
        seachEdInput.findFocus();

//        seachEdInput.setFocusable(true);
//        seachEdInput.setFocusableInTouchMode(true);
//        seachEdInput.requestFocus();
//        InputMethodManager inputManager =
//                (InputMethodManager) seachEdInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.showSoftInput(seachEdInput, 0);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_friend_search;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

    @OnClick({R.id.seach_iv_close, R.id.seach_iv_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seach_iv_close:
                seachEdInput.setText("");
                break;
            case R.id.seach_iv_find:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    clickSearch();
                }
                break;
        }
    }
    List<DataSearch> mList =new ArrayList<>();
    //    List<DataSearch> keyWordList =new ArrayList<>();
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        switch (s)
        {
            case "searchInfo":
//                try
//                {
                DataSearchResult dataSearchResult = JSON.parseObject(responseText, DataSearchResult.class);
                DataSearchResult.RecordBean record = dataSearchResult.getRecord();
                if (record!=null)
                {
                    mList.clear();
                    List<DataSearchResult.RecordBean.SeachGroupInfoBean> seachGroupInfo = record.getSeachGroupInfo();
                    List<DataSearchResult.RecordBean.SeachUserInfoBean> seachUserInfo = record.getSeachUserInfo();
                    if (seachGroupInfo.size()>0) {
                        String id = seachGroupInfo.get(0).getId();
                        if (!StrUtils.isEmpty(id)) {
                            for (int i = 0; i < seachGroupInfo.size(); i++) {
                                DataSearchResult.RecordBean.SeachGroupInfoBean seachGroupInfoBean = seachGroupInfo.get(i);
                                DataSearch dataSearch = new DataSearch();
                                dataSearch.setHeadImg(seachGroupInfoBean.getGroupHeadImg());
                                dataSearch.setId(seachGroupInfoBean.getId());
                                dataSearch.setName(seachGroupInfoBean.getGroupName());
                                dataSearch.setSno(seachGroupInfoBean.getGroupSno());
                                dataSearch.setQrcode(seachGroupInfoBean.getGroupQrcode());
                                dataSearch.setIsRelation(seachGroupInfoBean.getIsRelation());
//                                2代表群组
                                dataSearch.setType("2");
                                mList.add(dataSearch);
                            }
                        }
                    }
                    if (seachUserInfo.size()>0) {
                        String id = seachUserInfo.get(0).getUserId();
                        if (!StrUtils.isEmpty(id)) {
                            for (int j = 0; j < seachUserInfo.size(); j++) {
                                DataSearchResult.RecordBean.SeachUserInfoBean seachGroupInfoBean = seachUserInfo.get(j);
                                DataSearch dataSearch = new DataSearch();
                                dataSearch.setHeadImg(seachGroupInfoBean.getHeadImg());
                                dataSearch.setId(seachGroupInfoBean.getUserId());
                                dataSearch.setName(seachGroupInfoBean.getNickName());
                                dataSearch.setSno(seachGroupInfoBean.getWxSno());
                                dataSearch.setQrcode(seachGroupInfoBean.getQrcode());
                                dataSearch.setIsRelation(seachGroupInfoBean.getIsRelation());
                                dataSearch.setSign(seachGroupInfoBean.getPersonaSignature());
//                                1代表好友
                                dataSearch.setType("1");
                                mList.add(dataSearch);
                            }
                        }
                    }
                    if (mList.size()>0)
                    {
                        seachLinNoSearch.setVisibility(View.GONE);
                        seachLinList.setVisibility(View.VISIBLE);
                        initadapter(mList);
                    }else {
                        seachLinNoSearch.setVisibility(View.VISIBLE);
                        seachLinList.setVisibility(View.GONE);
                    }
                }
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                break;
        }
    }
    private void initadapter(List<DataSearch> seach_info) {
        SearchFriendAndGroupAdapter searchAdapter = new SearchFriendAndGroupAdapter(SearchActivity.this, seach_info);
        mRecyclerView.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DataSearch item =(DataSearch) adapter.getItem(position);
                if (item.getType().equals("1"))
                {
                    if (item.getId().equals(SplitWeb.getUserId()))
                    {
                        IntentUtils.JumpTo(ChangeInfoActivity.class);
                        return;
                    }
//                    if (item.getIsRelation().equals("1"))
//                        IntentUtils.JumpToHaveObj(FriendDataAddActivity.class,"dataSearch",item);
//                    else
//                    {
//                        IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",item.getId());
//                    }
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class,"id",item.getId());
                }else
                {
//                    添加群
                    Log.e("qrCode","----------getIsRelation------------------"+item.getIsRelation());
                    if (item.getIsRelation().equals("1"))
//                    未添加过该群，跳转未添加的群详情，部分信息看不到
                        IntentUtils.JumpToHaveObj(GroupDataAddActivity.class, AppConfig.GROUP_ADDKEY,item);
                    else
                    {
//                        添加过该群，跳转群详情
                        JumpToHaveOne(GroupChatDetailsActivity.class,AppConfig.GROUP_ID,item.getId());
//                        IntentUtils.JumpToHaveOne(GroupDataAddActivity.class, AppConfig.GROUP_ID,item.getId());
                    }
                }
//                ToastUtil.show("好友");
            }
        });
        LinearLayout footer=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
        searchAdapter.addFooterView(footer);
        searchAdapter.notifyDataSetChanged();
    }
    private void clickSearch() {
        String edInput = seachEdInput.getText().toString().trim();
        if (StrUtils.isEmpty(edInput))
        {
            DialogUtils.showDialog("搜索内容不能为空");
            return;
        }
        if (type==null)
            type=SeacchFriend;
        sendWebHaveDialog(SplitWeb.searchInfo(edInput,type),"搜索中...","搜索成功");
//        alterSearchAdapter.setText(edInput);
//        doChangeColor(edInput);
    }

//    private void doChangeColor(String text) {
//        //  clear是必须的，不然只要改变EditText的数据，list 就会一直添加数据进来
//        mList.clear();
//        //  不需要匹配，把所有数据都传进来，不需要变色
//        if (text.equals("")){
////            mList.addAll(mList);
//            //  防止匹配过文字之后点击删除按钮，字体仍然变色的问题
//            searchAdapter.setText(null);
////            refershUI();
//            initadapter(mList);
//            Log.e("search","---------mList_doChangeColor----0-----"+mList.size());
//        }else {
//            //  如果EditText里面有数据，则根据EditText里面的数据进行匹配，用contains判断是否包含该数据，包含的话则加入到list中
//            for (DataSearch i : mList){
//
//                if (i.getName().contains(text) || i.getId().contains(text)){
//                    mList.add(i);
//                    Log.e("search","---------mList_doChangeColor----1----"+mList.size());
////                    refershUI();
//                }
//            }
//            //  设置要变色的关键字
//            searchAdapter.setText(text);
//            initadapter(mList);
//        }
//    }

}
