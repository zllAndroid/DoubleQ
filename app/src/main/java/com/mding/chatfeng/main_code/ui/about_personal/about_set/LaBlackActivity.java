package com.mding.chatfeng.main_code.ui.about_personal.about_set;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.model.DataBlack;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.List;

import butterknife.BindView;


public class LaBlackActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.black_recyc)
    RecyclerView mRecyclerView;

//    @BindView(R.id.share_switch_accept)
//    SwitchButton shareSwitchAccept;
//    @BindView(R.id.share_switch_nouse)
//    SwitchButton shareSwitchNouse;
//    @BindView(R.id.share_switch_no)
//    SwitchButton shareSwitchNo;

    String mShare= "1";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("屏蔽名单");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(LaBlackActivity.this));
        sendWeb( SplitWeb.blackList());
    }
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "blackList":
                DataBlack dataBlack = JSON.parseObject(responseText, DataBlack.class);
                List<DataBlack.RecordBean> record = dataBlack.getRecord();

                if (record!=null)
                {
                    try {
                        if (record.size()>0&&!StrUtils.isEmpty(record.get(0).getUserId()))
                                initAdapter(record);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "removeBlack":
                if (blackAdapter!=null)
                {
                    blackAdapter.delItem(positions);
                }
                ToastUtil.show("移除成功");
                Log.e("position","点击了移除"+positions);
                break;
        }
    }
    BlackAdapter  blackAdapter =null;
    public int positions;
    private void initAdapter( List<DataBlack.RecordBean> record) {
        blackAdapter = new BlackAdapter(this,record);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("position","点击了"+position);
                positions=position;
                DataBlack.RecordBean item = (DataBlack.RecordBean)adapter.getItem(position);
                String user_id = item.getUserId();
                if (!StrUtils.isEmpty(user_id))
                    sendWeb(SplitWeb.removeBlack(user_id));
            }
        });

    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_black;
    }

}
