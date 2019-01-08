package com.doubleq.xm6leefunz.main_code.mains;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;

import app.dinus.com.loadingdrawable.LoadingView;
import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.LoadingRendererFactory;
import app.dinus.com.loadingdrawable.render.scenery.ElectricFanLoadingRenderer;
import butterknife.BindView;

public class LoadDataActivity extends BaseActivity {


    @BindView(R.id.electric_fan_view)
    LoadingView electricFanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initBaseView() {
        super.initBaseView();
        ElectricFanLoadingRenderer.Builder builder = new ElectricFanLoadingRenderer.Builder(this);
        electricFanView.setLoadingRenderer(builder.build());

        sendWeb(SplitWeb.getFriendList());
    }

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_load_data;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        if (method.equals("getFriendList")) {



        }
    }

}
