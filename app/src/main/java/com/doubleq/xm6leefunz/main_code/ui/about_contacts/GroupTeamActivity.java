package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataBlack;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupTeamAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.SeachAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.Allcity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.Cities;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.LetterBar;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice.NoticeAdapter;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：群聊成员
public class GroupTeamActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.tv_abc)
    TextView mTvAbc;

    @BindView(R.id.group_team_recyc)
    RecyclerView mRecyclerView;
    @BindView(R.id.group_team_letter)
    LetterBar mLetterBar;
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
    @BindView(R.id.group_lin_list)
    LinearLayout groupLinList;

    //    未搜索到东西
    @BindView(R.id.seach_tv_noSearch)
    TextView seachTvNoSearch;
    @BindView(R.id.seach_lin_noSearch)
    RelativeLayout seachLinNoSearch;

    private Runnable runnable;
    String mShare = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    LinearLayoutManager linearLayoutManager;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_team;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊成员");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(GroupTeamActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        seachRecyc.setLayoutManager( new LinearLayoutManager(GroupTeamActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupTeamActivity.this));
//        sendWeb( SplitWeb.blackList());
        initGroup();
    }

    private void initGroup() {
        initUI();
        initABC2();
        initHttp();

    }
    //    private ArrayList<Allcity> allCityList = new ArrayList<Allcity>();
    SeachAdapter mSeachAdapter;
    private ArrayList<Allcity> searchCityList = new ArrayList<Allcity>();
    private void initUI() {
//        实时搜索把按钮隐藏
        seachIvFind.setVisibility(View.GONE);


        mSeachAdapter = new SeachAdapter(GroupTeamActivity.this,searchCityList);
        seachRecyc.setAdapter(mSeachAdapter);
        //设置EditText文本监听事件
        seachEdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取用户输入文本
                String putStr = seachEdInput.getText().toString();
                //搜索的Listview显示
                seachLinList.setVisibility(View.VISIBLE);
                groupLinList.setVisibility(View.GONE);
                searchCityList.clear();
                for (int i = 0; i < allCusList.size(); i++) {
                    //判断输入的文本首字母  第一中文是否一致
                    String name = allCusList.get(i).getName();
                    Log.e("searchCityList",name+"---------输入------------->"+putStr);
//                    if (name.indexOf(putStr)!=-1)
                    if (name.contains(putStr))
                    {
                        Log.e("searchCityList",name+"-----进来了----输入------------->"+putStr);
                        searchCityList.add(allCusList.get(i));
                    }
//                    if (allCusList.get(i).getPinyin().startsWith(putStr)
//                            || allCusList.get(i).getName().startsWith(putStr)) {
//                        searchCityList.add(allCusList.get(i));
//                    }
                }

                //为搜索布局的显示隐藏
                if (searchCityList.size() == 0) {
                    seachLinNoSearch.setVisibility(View.VISIBLE);
                } else {
                    seachLinNoSearch.setVisibility(View.GONE);
                }

                //输入框为空时，返回城市列表
                if ("".equals(putStr)) {
                    searchCityList.clear();
                    seachLinList.setVisibility(View.GONE);
                    seachLinNoSearch.setVisibility(View.GONE);
                    groupLinList.setVisibility(View.VISIBLE);
                }
                //RecyclerView列表进行批量UI数据更新
//                mSeachAdapter.notifyItemRangeInserted(0,searchCityList.size());
//                // scrollToPosition（0）作用是把列表移动到顶端
//                seachRecyc.scrollToPosition(0);


                mSeachAdapter.notifyDataSetChanged();
            }
        });
    }

    String url = "http://omyk3uve8.bkt.clouddn.com/cities.txt";
    private ArrayList<Allcity> allCusList = new ArrayList<Allcity>();

    private void initHttp() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, url, new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                Cities cities = JSON.parseObject(result, Cities.class);
                List<Allcity> list = cities.getAllcity();
                allCusList.clear();
                allCusList.addAll(list);
                GroupTeamAdapter groupTeamAdapter = new GroupTeamAdapter(GroupTeamActivity.this, allCusList);
                mRecyclerView.setAdapter(groupTeamAdapter);
                groupTeamAdapter.notifyDataSetChanged();
            }
        });
    }

    List<String> ABCList = new ArrayList<>();

    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouuchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧")) {
                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
//                    mRecyclerView.scrollToPosition(0);
//                    mRecyclerView.setSelection(0);
                    return;
                }
//				tv_abc.setText(letterBar.getABCList().get(index));
                String lala = "1";
                for (int i = 0; i < allCusList.size(); i++) {
//                for (int i = 1; i < allCusList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (allCusList.get(i).getPinyin());
                    if (letter.equals(firstLetter)) {
                        linearLayoutManager.scrollToPositionWithOffset(i, 0);
                        linearLayoutManager.setStackFromEnd(true);
                        lala = "0";
                        break;
                    }
                }
//                if (lala.equals("1"))
//                {
//                    ToastUtil.show("暂无"+letter);
//                }
            }

            @Override
            public void onTouchUp() {
                mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }

    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "blackList":
                DataBlack dataBlack = JSON.parseObject(responseText, DataBlack.class);
                List<DataBlack.RecordBean> record = dataBlack.getRecord();

                if (record != null) {
                    initAdapter(record);
                }
                break;
            case "removeBlack":
                if (blackAdapter != null) {
                    blackAdapter.delItem(positions);
                }
                ToastUtil.show("移除成功");
                Log.e("position", "点击了移除" + positions);
                break;
        }
    }

    NoticeAdapter blackAdapter = null;
    public int positions;

    private void initAdapter(List<DataBlack.RecordBean> record) {
//        blackAdapter = new NoticeAdapter(this, record, mItemTouchListener);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setAdapter(blackAdapter);
//        blackAdapter.notifyDataSetChanged();
//        blackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Log.e("position", "点击了" + position);
//                ToastUtil.show("点击同意");
////                positions=position;
////                DataBlack.RecordBean item = (DataBlack.RecordBean)adapter.getItem(position);
////                String user_id = item.getUser_id();
////                if (!StrUtils.isEmpty(user_id))
////                    sendWeb(SplitWeb.removeBlack(user_id));
//            }
//        });
    }

    NoticeAdapter.ItemTouchListener mItemTouchListener = new NoticeAdapter.ItemTouchListener() {
        @Override
        public void onLeftMenuClick(View view, int positions, String WaybillNum) {
            ToastUtil.show("点击了删除");
            blackAdapter.delItem(positions);

        }
    };


    @OnClick({R.id.seach_iv_close, R.id.seach_iv_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seach_iv_close:
                seachEdInput.setText("");
                break;
            case R.id.seach_iv_find:
                break;
        }
    }
}
