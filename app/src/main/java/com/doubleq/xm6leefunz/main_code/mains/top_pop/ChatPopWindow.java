package com.doubleq.xm6leefunz.main_code.mains.top_pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.suke.widget.SwitchButton;

/**
 * 弹窗视图
 */
public class ChatPopWindow extends PopupWindow implements View.OnClickListener, ChangeInfoWindow.OnAddContantClickListener {
    TextView cpTvRemark;
    TextView cpTvGroup;
    SwitchButton switchButton;
    LinearLayout chatLinMainWhole, cpLinGroup;

    private Context context;
    private String friendId, groupId, remarkName, cardName;
    private View chat_data, chat_group, chat_remark;

    public ChatPopWindow(Context context, String friendId, String groupId, String remarkName, String cardName, LinearLayout chatLinMainWhole) {
        super(context);
        this.context = context;
        this.friendId = friendId;
        this.groupId = groupId;
        this.remarkName = remarkName;
        this.cardName = cardName;
        this.chatLinMainWhole = chatLinMainWhole;
        initalize();
    }


    boolean isClicked = true;
    private void initalize() {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view;
//        私聊
        if (!friendId.equals("") && groupId.equals("")){
            view= inflater.inflate(R.layout.activity_chat_pop, null);
            initFriendWindow(view);
        }
//        群聊
        else{
            view= inflater.inflate(R.layout.activity_chat_group_pop, null);
            initGroupWindow(view);
        }
        initWindow();
        setContentView(view);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                LinearLayout chatPopwindow = view.findViewById(R.id.cp_lin_window);
//                int top = chatPopwindow.getTop();
//                int bottom = chatPopwindow.getBottom();
//                int left = chatPopwindow.getLeft();
//                int right = chatPopwindow.getRight();
//                int x = (int) motionEvent.getX();
//                int y = (int) motionEvent.getY();
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    if (y < top){
//                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
//                    }
//                    if (y > bottom){
//                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
//                    }
//                    if (x < left){
//                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
//                    }
//                    if (x > right){
//                        dismiss();
//                        onClickBacListener.Clicked(isClicked);
//                    }
//                }
//                return true;
//            }
//        });
    }

    private void initFriendWindow(View view) {
        chat_data = view.findViewById(R.id.cp_tv_data);//好友资料
        chat_group = view.findViewById(R.id.cp_lin_group);//好友分组
        cpTvGroup = view.findViewById(R.id.cp_tv_group);//好友分组tv
        chat_remark = view.findViewById(R.id.cp_lin_remark);//备注
        cpTvRemark = view.findViewById(R.id.cp_tv_remark);//备注tv
        if (!remarkName.equals("")) {
            cpTvRemark.setText(remarkName);
        }else
            cpTvRemark.setText("暂未设置备注");
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
        if (!cardName.equals("")) {
            cpTvRemark.setText(cardName);
        }else
            cpTvRemark.setText("暂未设置群名片");
        chat_data.setOnClickListener(this);
        cpLinGroup.setOnClickListener(this);
        chat_remark.setOnClickListener(this);
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
//        this.setWidth(LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (d.widthPixels * 0.5));
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
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
                if (!friendId.equals("")) {
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    dismiss();
                } else if (!groupId.equals("")) {
                    IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupId);
                    dismiss();
                }
                break;
            case R.id.cp_lin_group:
                if (!friendId.equals("")) {
                    IntentUtils.JumpToHaveOne(ChooseGroupActivity.class, "FriendId", friendId);
                    dismiss();
                }else {
                    switchButton.setChecked(!switchButton.isChecked());
                }
                break;
            case R.id.cp_lin_remark:
                if (!friendId.equals("")) {
                    doChangeName();
//                    onClickBacListener.Clicked(isClicked);
                    dismiss();
                }else {
                    doChangeCardName();
//                    onClickBacListener.Clicked(isClicked);
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
        if (cardName.equals("")) {
            changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        } else {
            changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", cpTvRemark.getText().toString().trim());
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        }
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    //修改备注
    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow;
        if (remarkName.equals("")) {
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        } else {
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", cpTvRemark.getText().toString().trim());
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        }
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    String contant = null;
    @Override
    public void onSure(String contant) {
        this.contant = contant;
        if (!friendId.equals("")){
            remarkName = contant;
            cpTvRemark.setText(contant);
            onReRemarkListener.reRemark(contant);
        }
        else{
            cardName = contant;
            cpTvRemark.setText(contant);
            onReCardNameListener.reCardName(contant);
        }
    }

    @Override
    public void onCancle() {

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
    OnClickBacListener onClickBacListener = null;
    public interface OnClickBacListener {
        void Clicked(boolean clicked);
    }
    public void setOnClickBacListener(OnClickBacListener onClickBacListener) {
        this.onClickBacListener = onClickBacListener;
    }
}
