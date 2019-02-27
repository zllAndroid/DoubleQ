package com.doubleq.xm6leefunz.main_code.mains.top_pop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_chat.chat_group.GroupChatDetailsActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.ChooseGroupActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataMixActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.suke.widget.SwitchButton;

/**
 * 弹窗视图
 */
public class ChatPopWindow extends PopupWindow implements View.OnClickListener, ChangeInfoWindow.OnAddContantClickListener{
    TextView cpTvRemark;
    TextView cpTvGroup;
    SwitchButton switchButton;
    LinearLayout chatLinMainWhole, cpLinGroup,mLinWindow;

    private Context context;
    private String friendId, groupId, groupingName, remarkName, cardName;
    private View chat_data, chat_group, chat_remark;

    /** ChatPopWindow
     * @param context  上下文
     * @param friendId  好友id
     * @param groupId  群id
     * @param groupingName  好友所在分组名
     * @param remarkName  备注
     * @param cardName  群名片
     * @param chatLinMainWhole  全局布局
     */
    public ChatPopWindow(Context context, String friendId, String groupId, String groupingName, String remarkName, String cardName, LinearLayout chatLinMainWhole) {
        super(context);
        this.context = context;
        this.friendId = friendId;
        this.groupId = groupId;
        this.groupingName = groupingName;
        this.remarkName = remarkName;
        this.cardName = cardName;
        this.chatLinMainWhole = chatLinMainWhole;
        initialize();
    }
    private LayoutInflater mInflater;
    View view;
    private void initialize() {

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final LayoutInflater inflater = LayoutInflater.from(context);
//        私聊
        if (!StrUtils.isEmpty(friendId) && groupId.equals("")){
            view= mInflater.inflate(R.layout.activity_chat_pop, null);
            initBackWindow(view);
            initFriendWindow(view);
        }
//        群聊
        else{
            view= mInflater.inflate(R.layout.activity_chat_group_pop, null);
            initBackWindow(view);
            initGroupWindow(view);
        }
//        initWindow();


    }

    private void initBackWindow(final View view) {
        setContentView(view);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        /**
         * 设置可以获取集点
         */
        setFocusable(true);
        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);
        /**
         *设置可以触摸
         */
        setTouchable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                int top = mLinWindow.getTop();
                int bottom = mLinWindow.getBottom();
                int left = mLinWindow.getLeft();
                int right = mLinWindow.getRight();
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                Log.e("popupwindow","------------------------------x="+x);
                Log.e("popupwindow","------------------------------y="+y);
                Log.e("popupwindow","------------------------------top="+top);
                Log.e("popupwindow","------------------------------bottom="+bottom);
                Log.e("popupwindow","------------------------------left="+left);
                Log.e("popupwindow","------------------------------right="+right);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (absInt(y) < top){
                        onClickBacListener.Clicked();
                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
                    }
                    if (absInt(y) > bottom){
                        onClickBacListener.Clicked();
                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
                    }
                    if (absInt(x) < left){
                        onClickBacListener.Clicked();
                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
                    }
                    if (absInt(x) > right){
                        onClickBacListener.Clicked();
                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
                    }
                }
                return false;



//                /**
//                 * 判断是不是点击了外部
//                 */
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    return true;
//                }
//                //不是点击外部
//                return false;
            }
        });
    }
    public  int absInt(int a)
    {
        if (a<0)
        {
            a=-a;
        }
        return a;
    }
    private void initFriendWindow(View view) {
        chat_data = view.findViewById(R.id.cp_tv_data);//好友资料
        chat_group = view.findViewById(R.id.cp_lin_group);//好友分组
        cpTvGroup = view.findViewById(R.id.cp_tv_group);//好友分组tv
        chat_remark = view.findViewById(R.id.cp_lin_remark);//备注
        cpTvRemark = view.findViewById(R.id.cp_tv_remark);//备注tv
        mLinWindow = view.findViewById(R.id.cp_lin_window);//备注tv
        cpTvRemark.setText(remarkName);
        cpTvGroup.setText(groupingName);
//        if (!StrUtils.isEmpty(remarkName)) {
//            cpTvRemark.setText(remarkName);
//        }else
//            cpTvRemark.setText("暂未设置备注");
//        if (!StrUtils.isEmpty(groupingName)) {
//            cpTvGroup.setText(groupingName);
//        }else
//            cpTvGroup.setText("暂未设置分组");
        chat_data.setOnClickListener(this);
        chat_group.setOnClickListener(this);
        chat_remark.setOnClickListener(this);
    }

    private void initGroupWindow(View view) {
        chat_data = view.findViewById(R.id.cp_tv_data);//群资料
        cpLinGroup = view.findViewById(R.id.cp_lin_group);//消息免打扰
        switchButton = view.findViewById(R.id.disturb_switch_btn);
        chat_remark = view.findViewById(R.id.cp_lin_remark);//群名片
        cpTvRemark = view.findViewById(R.id.cp_tv_remark);//群名片tv
        cpTvRemark.setText(cardName);
//        if (!StrUtils.isEmpty(cardName)) {
//            cpTvRemark.setText(cardName);
//        }else
//            cpTvRemark.setText("暂未设置群名片");
        chat_data.setOnClickListener(this);
        cpLinGroup.setOnClickListener(this);
        chat_remark.setOnClickListener(this);
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
//        this.setWidth(LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        this.setWidth((int) (d.widthPixels * 0.5));
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
//        //实例化一个ColorDrawable颜色为半透明

//        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
//        this.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha((Activity) context, 1f);
//            }
//        });
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
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 0);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cp_tv_data:
                if (!StrUtils.isEmpty(friendId)) {
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    onClickBacListener.Clicked();
                    dismiss();
                } else if (!StrUtils.isEmpty(groupId)) {
                    IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupId);
                    onClickBacListener.Clicked();
                    dismiss();
                }
                break;
            case R.id.cp_lin_group:
                if (!StrUtils.isEmpty(friendId)) {

                    IntentUtils.JumpToHaveOne(ChooseGroupActivity.class, "FriendId", friendId);
                    onClickBacListener.Clicked();
                    dismiss();
                }else {
                    switchButton.setChecked(!switchButton.isChecked());
                }
                break;
            case R.id.cp_lin_remark:
                if (!StrUtils.isEmpty(friendId)) {
                    doChangeName();
                    onClickBacListener.Clicked();
                    dismiss();
                }else {
                    doChangeCardName();
                    onClickBacListener.Clicked();
                    dismiss();
                }
                break;
            default:
                break;
        }
    }

    //修改群名片
    private void doChangeCardName() {
        ChangeInfoWindow changeInfoWindow;
//        if (cardName.equals("暂未设置群名片")) {
////            changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", "暂未设置群名片");
////            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
////        } else {
        changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", cpTvRemark.getText().toString().trim());
        changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
//        }
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    //修改备注
    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow;
//        if (remarkName.equals("")) {
//            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", "");
//            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
//        } else {
        changeInfoWindow = new ChangeInfoWindow(context, "修改备注", cpTvRemark.getText().toString().trim());
        changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
//        }
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    String contant = null;
    @Override
    public void onSure(String contant) {
        this.contant = contant;
        if (!StrUtils.isEmpty(friendId)){
            remarkName = contant;
            cpTvRemark.setText(contant);
            onReRemarkListener.reRemark(contant);
//            onClickBacListener.Clicked(isClicked);
        }
        else{
            cardName = contant;
            cpTvRemark.setText(contant);
            onReCardNameListener.reCardName(contant);
//            onClickBacListener.Clicked(isClicked);
        }
    }

    @Override
    public void onCancle() {
//        onClickBacListener.Clicked(isClicked);
    }

    //修改备注事件回调接口
    OnReRemarkListener onReRemarkListener = null;
    public interface OnReRemarkListener {
        void reRemark(String remarkName);
    }
    public void setOnReRemarkListener(OnReRemarkListener onReRemarkListener) {
        this.onReRemarkListener = onReRemarkListener;
    }

    //修改群名片事件回调接口
    OnReCardNameListener onReCardNameListener = null;
    public interface OnReCardNameListener {
        void reCardName(String cardName);
    }
    public void setOnReCardNameListener(OnReCardNameListener onReCardNameListener) {
        this.onReCardNameListener = onReCardNameListener;
    }

    //点击外围弹窗消失接口
    OnClickOutSideListener onClickBacListener = null;
    public interface OnClickOutSideListener {
        void Clicked();
    }
    public void setOnClickOutSideListener(OnClickOutSideListener onClickBacListener) {
        this.onClickBacListener = onClickBacListener;
    }
    //点击外围弹窗消失接口
}
