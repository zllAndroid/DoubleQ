package com.doubleq.xm6leefunz.main_code.ui.about_top;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
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
import com.doubleq.xm6leefunz.about_chat.cus_data_group.RealmGroupChatHelper;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataAddActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.GroupDataAddActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchFriendAndGroupAdapter;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.doubleq.xm6leefunz.about_utils.IntentUtils.JumpToHaveOne;

//搜索好友界面
public class SearchChatRecordActivity extends BaseActivity {

    //    RecyclerView tvRecyclerView;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLinBack;


    @BindView(R.id.search_record_ed_input)
    EditText seachEdInput;
    @BindView(R.id.search_record_iv_close)
    ImageView seachIvClose;
    @BindView(R.id.search_record_iv_find)
    ImageView seachIvFind;
    @BindView(R.id.search_record_lin_recyc)
    LinearLayout seachLinList;
    @BindView(R.id.search_record_tv_noSearch)
    TextView seachTvNoSearch;
    @BindView(R.id.search_record_lin_noSearch)
    RelativeLayout seachLinNoSearch;
    @BindView(R.id.search_record_recyc)
    RecyclerView mRecyclerView;

    public  static  String SeacchKey="search_type";
    public  static  String SeacchFriend="1";
    public  static  String SeacchGroup="2";
    /**
     * 搜索关键字全部匹配的适配器
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String type;

    RealmChatHelper realmChatHelper;
    RealmGroupChatHelper realmGroupChatHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        Intent intent = getIntent();
        if (intent!=null)
            type = intent.getStringExtra(SeacchKey);

        includeTopTvTital.setText("搜索");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(SearchChatRecordActivity.this, 1));
        LinearLayout footer=  (LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
//        监听软键盘的回车键
        listenEnter();

         realmChatHelper = new RealmChatHelper(this);
         realmGroupChatHelper = new RealmGroupChatHelper(this);


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
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_search_record;
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
    List<CusDataSearchRecord> mList =new ArrayList<>();
    //    List<DataSearch> keyWordList =new ArrayList<>();
    private void initadapter(List<DataSearch> seach_info) {
        SearchHistoryAdapter searchAdapter = new SearchHistoryAdapter(SearchChatRecordActivity.this, mList);
        mRecyclerView.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CusDataSearchRecord item =(CusDataSearchRecord) adapter.getItem(position);


//                if (item.getType().equals("1"))
//                {
//                    if (item.getIsRelation().equals("1"))
//                    IntentUtils.JumpToHaveObj(FriendDataAddActivity.class,"dataSearch",item);
//                    else
//                    {
//                        IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",item.getId());
//                    }
//                }else
//                {
////                    添加群
//                    if (item.getIsRelation().equals("1"))
////                    未添加过该群，跳转未添加的群详情，部分信息看不到
//                         IntentUtils.JumpToHaveObj(GroupDataAddActivity.class, AppConfig.GROUP_ADDKEY,item);
//                    else
//                    {
////                        添加过该群，跳转群详情
//                        JumpToHaveOne(GroupChatDetailsActivity.class,AppConfig.GROUP_ID,item.getId());
////                        IntentUtils.JumpToHaveOne(GroupDataAddActivity.class, AppConfig.GROUP_ID,item.getId());
//                    }
//                }
//                ToastUtil.show("好友");
            }
        });
        LinearLayout footer=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
        searchAdapter.addFooterView(footer);
        searchAdapter.notifyDataSetChanged();
    }
    private void clickSearch() {
//        String edInput = seachEdInput.getText().toString().trim();
//        if (StrUtils.isEmpty(edInput))
//        {
//            DialogUtils.showDialog("搜索内容不能为空");
//            return;
//        }
//        if (type==null)
//            type=SeacchFriend;
//        sendWebHaveDialog(SplitWeb.searchInfo(edInput,type),"搜索中...","搜索成功");
//        alterSearchAdapter.setText(edInput);
//        doChangeColor(edInput);
//        realmChatHelper.queryAllRealmChat()
    }


}
