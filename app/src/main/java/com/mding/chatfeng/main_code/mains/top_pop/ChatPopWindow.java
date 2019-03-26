package com.mding.chatfeng.main_code.mains.top_pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.MessageEvent;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.chat_group.GroupChatDetailsActivity;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.FriendDataMixActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.MyApplication;
import com.mding.model.CusChatPop;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 项目：DoubleQ
 * 文件描述：聊天界面的弹窗选择界面
 * 作者：zll
 */
public class ChatPopWindow extends PopupWindow implements View.OnClickListener, ChangeInfoWindow.OnAddContantClickListener {
    TextView cpTvRemark;
    TextView cpTvGroup;
    TextView cpTvLock;
    SwitchButton switchButton;
    LinearLayout chatLinMainWhole, cpLinGroup, mLinWindow,cpLinLock;

    private Context context;
    private String friendId, groupId, groupingName, remarkName, cardName, isChecked, type;
    private View chat_data, chat_group, chat_remark;
    private boolean isLocked;

    public ChatPopWindow(CusChatPop cusChatPop) {
        super(cusChatPop.getContext());
        this.context = cusChatPop.getContext();
        this.friendId = cusChatPop.getFriendId();
        this.groupId = cusChatPop.getGroupId();
        this.groupingName = cusChatPop.getGroupingName();
        this.remarkName = cusChatPop.getRemarkName();
        this.cardName = cusChatPop.getCardName();
        this.chatLinMainWhole = cusChatPop.getChatLinMainWhole();
        this.isChecked = cusChatPop.getIsChecked();
        this.type = cusChatPop.getType();
        this.isLocked = cusChatPop.isLocked();
        initialize();
    }

    private LayoutInflater mInflater;
    View view;

    private void initialize() {

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        私聊
        if (type.equals("1")) {
            view = mInflater.inflate(R.layout.activity_chat_pop, null);
            initBackWindow(view);
            initFriendWindow(view);
        }
//        群聊
        else {
            view = mInflater.inflate(R.layout.activity_chat_group_pop, null);
            initBackWindow(view);
            initGroupWindow(view);
        }
        EventBus.getDefault().register(this);
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
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (absInt(y) < top) {
                        onClickBacListener.Clicked("");
                        dismiss();
                    }
                    if (absInt(y) > bottom) {
                        onClickBacListener.Clicked("");
                        dismiss();
                    }
                    if (absInt(x) < left) {
                        onClickBacListener.Clicked("");
                        dismiss();
                    }
                    if (absInt(x) > right) {
                        onClickBacListener.Clicked("");
                        dismiss();
                    }
                }
                return false;
            }
        });
    }

    public int absInt(int a) {
        if (a < 0) {
            a = -a;
        }
        return a;
    }

    private void initFriendWindow(View view) {
        chat_data = view.findViewById(R.id.cp_tv_data);//好友资料
        chat_group = view.findViewById(R.id.cp_lin_group);//好友分组
        cpTvGroup = view.findViewById(R.id.cp_tv_group);//好友分组tv
        chat_remark = view.findViewById(R.id.cp_lin_remark);//备注
        cpTvRemark = view.findViewById(R.id.cp_tv_remark);//备注tv
        mLinWindow = view.findViewById(R.id.cp_lin_window);//popUpWindow
        cpLinLock = view.findViewById(R.id.cp_lin_lock);//加密聊天
        cpTvLock = view.findViewById(R.id.cp_tv_lock);//加密聊天tv
        cpTvRemark.setText(remarkName);
        cpTvGroup.setText(groupingName);

        chat_data.setOnClickListener(this);
        chat_group.setOnClickListener(this);
        chat_remark.setOnClickListener(this);
        cpLinLock.setOnClickListener(this);
    }

    private void initGroupWindow(View view) {
        chat_data = view.findViewById(R.id.cp_tv_data);//群资料
        cpLinGroup = view.findViewById(R.id.cp_lin_group);//消息免打扰
        switchButton = view.findViewById(R.id.disturb_switch_btn);
        chat_remark = view.findViewById(R.id.cp_lin_remark);//群名片
        cpTvRemark = view.findViewById(R.id.cp_tv_remark);//群名片tv
        mLinWindow = view.findViewById(R.id.cp_lin_window);//popUpWindow
        cpTvRemark.setText(cardName);
        if (!StrUtils.isEmpty(isChecked)) {
            if (isChecked.equals("2"))    //  消息免打扰： 1 否     2 是
                switchButton.setChecked(true);
            else
                switchButton.setChecked(false);
        }

        chat_data.setOnClickListener(this);
        cpLinGroup.setOnClickListener(this);
        chat_remark.setOnClickListener(this);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                String daRao = isChecked ? "2":"1";
                MyApplication.getmConnectManager().sendText(SplitWeb.setUserGroupDisturb(groupId, daRao));
            }
        });
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
                if (type.equals("1")) {
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    onClickBacListener.Clicked("");
                    dismiss();
                } else{
                    IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupId);
                    onClickBacListener.Clicked("");
                    dismiss();
                }
                break;
            case R.id.cp_lin_group:
                if (type.equals("1")) {
//                    给聊天界面回调处理
//                    IntentUtils.JumpToHaveOne(ChooseGroupActivity.class, "FriendId", friendId);
                    onClickBacListener.Clicked("1");
                    dismiss();
                }
                break;
            case R.id.cp_lin_remark:
                if (type.equals("1")) {
                    doChangeName();
                    onClickBacListener.Clicked("");
                    dismiss();
                } else {
                    doChangeCardName();
                    onClickBacListener.Clicked("");
                    dismiss();
                }
                break;
            case R.id.cp_lin_lock:
                isLocked = !isLocked;
                if (isLocked){
                    cpTvLock.setText("已加密");
                }
                else {
                    cpTvLock.setText("");
                }
                onClickLockListener.Locked(isLocked);
                onClickBacListener.Clicked("");
                dismiss();
                break;
            default:
                break;
        }
    }

    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        String method = HelpUtils.backMethod(messageEvent.getMessage());
        switch (method){
            case "setUserGroupDisturb":
//        int i = 0;
//                DataChatGroupPop dataChatGroupPop = JSON.parseObject(messageEvent.getMessage(),DataChatGroupPop.class);
//                DataChatGroupPop.RecordBean recordBean = dataChatGroupPop.getRecord();
//                if (recordBean != null){
//                    DataChatGroupPop.RecordBean.GroupDetailInfoBean groupDetailInfoBean = recordBean.getGroupDetailInfo();
//                    DataChatGroupPop.RecordBean.GroupDetailInfoBean.UserInfoBean userInfoBean = groupDetailInfoBean.getUserInfo();
//                    if (userInfoBean.getDisturbType().equals("2"))
//                        switchButton.setChecked(true);
//                    else
//                        switchButton.setChecked(false);
//                    Log.e("setUserGroupDisturb","-----------------------请求成功时----------------------"+userInfoBean.getDisturbType()+"-----------i="+(i +1));
//                }
                break;
        }
    }

    //修改群名片
    private void doChangeCardName() {
        ChangeInfoWindow changeInfoWindow;
//        if (!StrUtils.isEmpty(cardName)){
        if (StrUtils.isEmpty(cardName) || cardName.equals("暂未设置")) {
            changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        } else {
            changeInfoWindow = new ChangeInfoWindow(context, "修改群名片", cpTvRemark.getText().toString().trim());
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        }
        changeInfoWindow.setOnAddpopClickListener(this);
    }
//    }

    //修改备注
    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow;
//        if (!StrUtils.isEmpty(remarkName)){
        if (StrUtils.isEmpty(remarkName) || remarkName.equals("暂未设置")) {
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        } else {
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", cpTvRemark.getText().toString().trim());
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        }
        changeInfoWindow.setOnAddpopClickListener(this);
//        }
    }

    String contant = null;
    @Override
    public void onSure(String contant) {
        this.contant = contant;
        if (type.equals("1")) {
            if (contant.equals("") ){
                remarkName = contant;
                cpTvRemark.setText("暂未设置");
                onReRemarkListener.reRemark("");
            }else {
                remarkName = contant;
                cpTvRemark.setText(contant);
                onReRemarkListener.reRemark(contant);
            }
        }
        else{
            if (contant.equals("")){
                cardName = contant;
                cpTvRemark.setText("暂未设置");
                onReCardNameListener.reCardName("");
            }else {
                cardName = contant;
                cpTvRemark.setText(contant);
                onReCardNameListener.reCardName(contant);
            }
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
    OnClickOutSideListener onClickBacListener = null;
    public interface OnClickOutSideListener {
        void Clicked(String type);
    }
    public void setOnClickOutSideListener(OnClickOutSideListener onClickBacListener) {
        this.onClickBacListener = onClickBacListener;
    }

    //加密聊天接口
    OnClickLockListener onClickLockListener = null;
    public interface OnClickLockListener {
        void Locked(boolean isLocked);
    }
    public void setOnClickLockListener(OnClickLockListener onClickLockListener) {
        this.onClickLockListener = onClickLockListener;
    }
}
