package com.doubleq.xm6leefunz.main_code.ui.about_contacts.add_friend;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataGroupManage;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.PopFenzuAdapter;

import java.util.List;


/**
 * 弹窗视图
 */
public class FenZuFriendPopWindow extends PopupWindow {
    private Context context;
    RecyclerView mRecycLerView;
    List<DataGroupManage.RecordBean.GroupInfoBean> mList;
    public FenZuFriendPopWindow(Context context, List<DataGroupManage.RecordBean.GroupInfoBean> mList) {
        super(context);
        this.context = context;
        this.mList = mList;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_fenzu, null);
        setContentView(view);
        mRecycLerView = view.findViewById(R.id.pop_recyc_fenzu);//发起群聊
        initWindow();
        initAdapter();
    }

    private void initAdapter() {
        mRecycLerView.setLayoutManager(new LinearLayoutManager(context));
        PopFenzuAdapter popFenzuAdapter = new PopFenzuAdapter(mList);
        mRecycLerView.setAdapter(popFenzuAdapter);
        popFenzuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DataGroupManage.RecordBean.GroupInfoBean item =(DataGroupManage.RecordBean.GroupInfoBean) adapter.getItem(position);
                listener.WhoListener(item.getGroupName(),item.getId());
                dismiss();
            }
        });
        popFenzuAdapter.notifyDataSetChanged();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
//        this.setWidth((int) (d.widthPixels * 0.5));
//        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x33000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }
    public OnClickWhoListener listener = null; //事件回调接口

    public interface OnClickWhoListener {
        void WhoListener(String msg,String id);
    }

    //设置回调接口
    public void setOnClickWhoListener(OnClickWhoListener rockerListener) {
        listener = rockerListener;
    }

}
