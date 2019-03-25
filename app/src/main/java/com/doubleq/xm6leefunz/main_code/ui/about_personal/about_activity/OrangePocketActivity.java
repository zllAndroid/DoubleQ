package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.ZXingUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.PersonData;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
        if (intent!=null)
        {
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

    @OnClick({R.id.orange_bag_lin_only, R.id.orange_bag_lin_sideChain, R.id.orange_bag_tv_xunChengJi, R.id.orange_bag_tv_xiLeCheng, R.id.orange_bag_tv_shangJiaSongCheng, R.id.orange_bag_tv_gongYiCheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            // 2种侧链资产
            case R.id.orange_bag_lin_sideChain:
                IntentUtils.JumpToHaveOne(SideChainActivity.class,"userId",userId);
                break;

            // 寻“橙”记
            case R.id.orange_bag_tv_xunChengJi:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 喜乐“橙”
            case R.id.orange_bag_tv_xiLeCheng:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 商家送“橙”
            case R.id.orange_bag_tv_shangJiaSongCheng:
                DialogUtils.showDialog("敬请期待！");
                break;

            // 公益“橙”
            case R.id.orange_bag_tv_gongYiCheng:
                DialogUtils.showDialog("敬请期待！");
                break;
        }
    }
}
