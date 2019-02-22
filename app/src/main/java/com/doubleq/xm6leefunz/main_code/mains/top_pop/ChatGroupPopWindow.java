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
import com.doubleq.xm6leefunz.about_chat.chat_group.GroupChatDetailsActivity;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.ChooseGroupActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataMixActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;

import butterknife.BindView;


/**
 * 弹窗视图
 */
public class ChatGroupPopWindow extends PopupWindow implements View.OnClickListener, ChangeInfoWindow.OnAddContantClickListener {
    @BindView(R.id.cp_tv_remark)
    TextView cpTvRemark;
    @BindView(R.id.cp_tv_group)
    TextView cpTvGroup;

    private Context context;
    private String friendId, groupId, remarkName, groupName;
    private View chat_data, chat_group, chat_remark;
    LinearLayout chatLinMainWhole;

    public ChatGroupPopWindow(Context context, String friendId, String groupId, String remarkName, String groupName, LinearLayout chatLinMainWhole) {
        super(context);
        this.context = context;
        this.friendId = friendId;
        this.groupId = groupId;
        this.remarkName = remarkName;
        this.groupName = groupName;
        this.chatLinMainWhole = chatLinMainWhole;
        initalize();
    }


    private void initalize() {
//        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.activity_chat_pop,null);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (friendId != null && groupId == null){
            view= inflater.inflate(R.layout.activity_chat_pop, null);
        }else{
            view= inflater.inflate(R.layout.activity_chat_group_pop, null);
        }
        chat_data = view.findViewById(R.id.cp_tv_data);//群资料
        chat_group = view.findViewById(R.id.cp_lin_group);//消息免打扰
        chat_remark = view.findViewById(R.id.cp_lin_remark);//群名片
        setContentView(view);
//        if (remarkName != null) {
//            cpTvRemark.setText(remarkName+"");
//        }else
//            cpTvRemark.setText("");
//        if (groupName != null) {
//            cpTvGroup.setText(groupName+"");
//        }else
//            cpTvGroup.setText("");
        chat_data.setOnClickListener(this);
        chat_group.setOnClickListener(this);
        chat_remark.setOnClickListener(this);
        initWindow();
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
                if (friendId != null) {
                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    dismiss();
                } else if (groupId != null) {
                    IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, "id", groupId);
                    dismiss();
                }
                break;
            case R.id.cp_lin_group:
                if (friendId != null) {
                    IntentUtils.JumpToHaveOne(ChooseGroupActivity.class, "FriendId", friendId);
                    dismiss();
                }
                break;
            case R.id.cp_lin_remark:
                if (remarkName != null) {
                    doChangeName();
                    dismiss();
                }
                break;
            default:
                break;
        }
    }

    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow;
        if (remarkName.equals("")) {
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        } else {
//            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", cpTvRemark.getText().toString().trim());
            changeInfoWindow = new ChangeInfoWindow(context, "修改备注", "");
            changeInfoWindow.showAtLocation(chatLinMainWhole, Gravity.CENTER, 0, 0);
        }

//        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(FriendDataMixActivity.this, "修改备注", fdTvBeizhu.getText().toString().trim());
//        changeInfoWindow.showAtLocation(gfLinTop, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    String contant = null;

    @Override
    public void onSure(String contant) {
        this.contant = contant;
        remarkName = contant;
//        BaseActivityForResult.sendWeb(SplitWeb.friendRemarkName(friendId, contant));
    }

    @Override
    public void onCancle() {

    }

    OnClickChatListener onClickChatListener = null; //事件回调接口

    public interface OnClickChatListener {
        void reRemark(String remark);
    }

    //设置回调接口
    public void setOnClickChatListener(OnClickChatListener onClickChatListener) {
        this.onClickChatListener = onClickChatListener;
    }
}
