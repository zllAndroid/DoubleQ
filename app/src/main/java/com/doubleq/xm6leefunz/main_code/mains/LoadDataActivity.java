package com.doubleq.xm6leefunz.main_code.mains;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

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

    ACache aCache;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        aCache =  ACache.get(this);
        ElectricFanLoadingRenderer.Builder builder = new ElectricFanLoadingRenderer.Builder(this);
        electricFanView.setLoadingRenderer(builder.build());

        sendWeb(SplitWeb.getFriendList());
        sendWeb(SplitWeb.getGroupManage());
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
    boolean isFriend=false;
    boolean isGroup=false;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        String md5 = HelpUtils.backMD5(responseText);
        if (!StrUtils.isEmpty(md5))
            SPUtils.put(this, AppConfig.KEY_MD5,md5);
        switch (method)
        {
            case "getFriendList":
                DataLinkManList dataLinkManList = JSON.parseObject(responseText, DataLinkManList.class);
                DataLinkManList.RecordBean record = dataLinkManList.getRecord();
                if (record==null)
                {
                    return;
                }
                isFriend=true;
                String json = JSON.toJSON(record).toString();
                aCache.remove(AppAllKey.FRIEND_DATA);
                aCache.put(AppAllKey.FRIEND_DATA, json);
                break;
            case "getGroupManage":
                DataLinkGroupList dataLinkGroupList = JSON.parseObject(responseText, DataLinkGroupList.class);
                DataLinkGroupList.RecordBean  record_group = dataLinkGroupList.getRecord();
                if (record_group==null)
                {
                    return;
                }
                isGroup=true;
                String json_group = JSON.toJSON(record_group).toString();
                aCache.remove(AppAllKey.GROUD_DATA);
                aCache.put(AppAllKey.GROUD_DATA, json_group);
                break;
        }
        if (isFriend&&isGroup)
        {
            IntentUtils.JumpFinishTo(LoadDataActivity.this,MainActivity.class);
            overridePendingTransition(0,0);
        }

    }

}
