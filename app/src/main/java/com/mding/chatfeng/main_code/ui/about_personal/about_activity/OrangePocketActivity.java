package com.mding.chatfeng.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;
//橙子口袋
public class OrangePocketActivity extends BaseActivity {

    @BindView(R.id.orange_bag_lin_sideChain)
    LinearLayout orangeBagLinSideChain;
    @BindView(R.id.orange_bag_tv_xunChengJi)
    TextView orangeBagTvXunChengJi;
    @BindView(R.id.orange_bag_tv_xiLeCheng)
    TextView orangeBagTvXiLeCheng;
    @BindView(R.id.orange_bag_tv_shangJiaSongCheng)
    TextView orangeBagTvShangJiaSongCheng;
    @BindView(R.id.orange_bag_tv_gongYiCheng)
    TextView orangeBagTvGongYiCheng;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_orange_pocket);
//    }

    String userId;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_orange_pocket;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }

        initText();

    }

    @Override
    protected boolean isGonesStatus() {
        return true;
    }

    private void initText() {

        // 寻“橙”记
        SpannableString xunChengJi = new SpannableString(orangeBagTvXunChengJi.getText());
        xunChengJi.setSpan(new ForegroundColorSpan(Color.parseColor("#f7941f")), 1, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        orangeBagTvXunChengJi.setText(xunChengJi);

        // 喜乐“橙”
        SpannableString xiLeCheng = new SpannableString(orangeBagTvXiLeCheng.getText());
        xiLeCheng.setSpan(new ForegroundColorSpan(Color.parseColor("#f7941f")), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        orangeBagTvXiLeCheng.setText(xiLeCheng);

        // 商家送“橙”
        SpannableString shangJiaSongCheng = new SpannableString(orangeBagTvShangJiaSongCheng.getText());
        shangJiaSongCheng.setSpan(new ForegroundColorSpan(Color.parseColor("#f7941f")), 3, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        orangeBagTvShangJiaSongCheng.setText(shangJiaSongCheng);

        // 公益“橙”
        SpannableString gongYiCheng = new SpannableString(orangeBagTvGongYiCheng.getText());
        gongYiCheng.setSpan(new ForegroundColorSpan(Color.parseColor("#f7941f")), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        orangeBagTvGongYiCheng.setText(gongYiCheng);
    }

    @OnClick({R.id.orange_bag_lin_shouFuBao, R.id.orange_bag_lin_total, R.id.orange_bag_lin_node,
            R.id.orange_bag_lin_only, R.id.orange_bag_lin_sideChain,
            R.id.orange_bag_lin_xunChengJi, R.id.orange_bag_lin_xiLeCheng, R.id.orange_bag_lin_shangJiaSongCheng, R.id.orange_bag_lin_gongYiCheng,
            R.id.orange_bag_lin_more, R.id.orange_bag_lin_wallet, R.id.orange_bag_lin_browser, R.id.orange_bag_lin_quotes, R.id.orange_bag_lin_miner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 收付宝
            case R.id.orange_bag_lin_shouFuBao:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 合计
            case R.id.orange_bag_lin_total:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 节点投票
            case R.id.orange_bag_lin_node:
                DialogUtils.showDialog("敬请期待！");
                break;

            // Only（奥力橙）
            case R.id.orange_bag_lin_only:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 2种侧链资产
            case R.id.orange_bag_lin_sideChain:
                IntentUtils.JumpToHaveOne(SideChainActivity.class, "userId", userId);
                break;

            // 寻“橙”记
            case R.id.orange_bag_lin_xunChengJi:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 喜乐“橙”
            case R.id.orange_bag_lin_xiLeCheng:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 商家送“橙”
            case R.id.orange_bag_lin_shangJiaSongCheng:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 公益“橙”
            case R.id.orange_bag_lin_gongYiCheng:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 更多
            case R.id.orange_bag_lin_more:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 硬件钱包
            case R.id.orange_bag_lin_wallet:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 区块浏览器
            case R.id.orange_bag_lin_browser:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 行情
            case R.id.orange_bag_lin_quotes:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 矿工专区
            case R.id.orange_bag_lin_miner:
                DialogUtils.showDialog("敬请期待！");
                break;
        }
    }
}
