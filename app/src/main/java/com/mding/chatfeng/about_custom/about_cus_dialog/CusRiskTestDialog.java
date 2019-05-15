package com.mding.chatfeng.about_custom.about_cus_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.ScreenUtils;

import org.w3c.dom.Text;

public class CusRiskTestDialog extends Dialog {
    private Button mPositiveBtn;
    private Button mNegativeBtn;
//    private Button mUpdateBtn;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvOrganization;
    private TextView tvTime;
    private FrameLayout frameLayout;
//    private LinearLayout linearLayoutBtn;

    private String mTitle;
    private String mContent;
    private String mOrganization;
    private String mTime;
    private String positiveButtonText;
    private String negativeButtonText;
//    private String updateButtonText;
    private OnClickListener positiveButtonClickListener;
    private OnClickListener negativeButtonClickListener;
//    private OnClickListener updateButtonClickListener;

    Context context;
    public CusRiskTestDialog(Context context){
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext()).inflate(R.layout.dialog_risk_test,null);
        setContentView(view);

        mPositiveBtn = findViewById(R.id.btn_dialog_positive);
        mNegativeBtn = findViewById(R.id.btn_dialog_negative);
//        mUpdateBtn = findViewById(R.id.btn_dialog_update);
        tvTitle = findViewById(R.id.dialog_tv_title);
        tvContent = findViewById(R.id.dialog_tv_content);
        tvOrganization = findViewById(R.id.dialog_tv_organization);
        tvTime = findViewById(R.id.dialog_tv_time);
//        linearLayoutBtn = findViewById(R.id.dialog_lin_btn);
        frameLayout = findViewById(R.id.dialog_fra);
        int screenWidth = ScreenUtils.getScreenWidth(context);
        int screenHeight = ScreenUtils.getScreenHeight(context);
        frameLayout.getLayoutParams().width = (screenWidth/4)*3;
        frameLayout.getLayoutParams().height = (screenHeight/10)*7;

        if (positiveButtonText != null){
//            linearLayoutBtn.setVisibility(View.GONE);
            mPositiveBtn.setText(positiveButtonText);
            if (positiveButtonClickListener != null){
                mPositiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positiveButtonClickListener.onClick(CusRiskTestDialog.this,Dialog.BUTTON_POSITIVE);
                    }
                });
            }
        }else {
            mPositiveBtn.setVisibility(View.GONE);
        }

        if (negativeButtonText != null){
//            linearLayoutBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setText(negativeButtonText);
//            mUpdateBtn.setText(updateButtonText);
            if (negativeButtonClickListener != null) {
                mNegativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(CusRiskTestDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
//                mUpdateBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        updateButtonClickListener.onClick(CusRiskTestDialog.this, Dialog.BUTTON_POSITIVE);
//                    }
//                });
            }
        }else {
//            linearLayoutBtn.setVisibility(View.GONE);
            mNegativeBtn.setVisibility(View.GONE);
        }

        if (mTitle != null){
            tvTitle.setText(mTitle);
        }

        if (mContent != null){
            tvContent.setText(mContent);
        }

        if (mOrganization != null){
            tvOrganization.setText(mOrganization);
        }

        if (mTime != null){
            tvTime.setText(mTime);
        }
    }
    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }

    private void setMessage(String title,String content, String organization, String time){
        mTitle = title;
        mContent= content;
        mOrganization= organization;
        mTime= time;
    }

    private void setPositiveButtonText(String text){
        positiveButtonText = text;
    }
    private void setNegativeButtonText(String text){
        negativeButtonText = text;
    }
//    private void setUpdateButtonText(String text){
//        updateButtonText = text;
//    }

    private void setOnPositiveListener(OnClickListener listener){
        positiveButtonClickListener = listener;
    }
    private void setOnNegativeListener(OnClickListener listener){
        negativeButtonClickListener = listener;
    }
//    private void setOnUpdateListener(OnClickListener listener){
//        updateButtonClickListener = listener;
//    }

    public static class Builder{
        private Context context;
        private String title;
        private String content;
        private String time;
        private String organization;
        private String positiveButtonText;
        private String negativeButtonText;
//        private String updateButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
//        private OnClickListener updateButtonClickListener;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setMessage(String title,String content,String organization, String time){
            this.title = title;
            this.content = content;
            this.organization = organization;
            this.time = time;
            return this;
        }
        public Builder setMessage(String content){
            this.content = content;
            return this;
        }
//        public Builder setUpdateButton(int updateButtonText,OnClickListener listener){
//            return setUpdateButton(context.getString(updateButtonText),listener);
//        }
//        public Builder setUpdateButton(String updateButtonText,OnClickListener listener){
//            this.updateButtonText = updateButtonText;
//            this.updateButtonClickListener = listener;
//            return this;
//        }

        public Builder setPositiveButton(int positiveButtonText,OnClickListener listener){
            return setPositiveButton(context.getString(positiveButtonText),listener);
        }
        public Builder setPositiveButton(String positiveButtonText,OnClickListener listener){
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButtonText(int positiveButtonText){
            return setPositiveButtonText(context.getString(positiveButtonText));
        }
        public Builder setPositiveButtonText(String positiveButtonText){
            this.positiveButtonText = positiveButtonText;
            return this;
        }
//        public Builder setPositiveButtonListener(OnClickListener listener){
//            this.positiveButtonClickListener = listener;
//            return this;
//        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener){
            return setNegativeButton(context.getString(negativeButtonText),listener);
        }
        public Builder setNegativeButton(String negativeButtonText,OnClickListener listener){
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CusRiskTestDialog create(){
            CusRiskTestDialog dialog = new CusRiskTestDialog(context);
            if (dialog.isShowing()){
                dialog.dismiss();
            }
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
//            dialog.getWindow().setDimAmount((float) 0.3);
            dialog.setMessage(title,content,organization,time);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setPositiveButtonText(positiveButtonText);
//            dialog.setUpdateButtonText(updateButtonText);
            dialog.setOnNegativeListener(negativeButtonClickListener);
            dialog.setOnPositiveListener(positiveButtonClickListener);
//            dialog.setOnUpdateListener(updateButtonClickListener);
            return dialog;
        }

    }

}
