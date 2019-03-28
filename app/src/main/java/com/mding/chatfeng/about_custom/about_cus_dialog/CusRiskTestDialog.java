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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

import org.w3c.dom.Text;

public class CusRiskTestDialog extends Dialog {
    private Button mPositiveBtn;
    private Button mNegativeBtn;
    private TextView tvTitle;
    private TextView tvContent;

    private String mTitle;
    private String mContent;
    private String positiveButtonText;
    private String negativeButtonText;
    private OnClickListener positiveButtonClickListener;
    private OnClickListener negativeButtonClickListener;


    public CusRiskTestDialog(Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext()).inflate(R.layout.dialog_risk_test,null);
        setContentView(view);

        mPositiveBtn = findViewById(R.id.btn_dialog_positive);
        mNegativeBtn = findViewById(R.id.btn_dialog_negative);
        tvTitle = findViewById(R.id.dialog_tv_title);
        tvContent = findViewById(R.id.dialog_tv_content);


        if (positiveButtonText != null){
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
            mNegativeBtn.setText(negativeButtonText);
            if (negativeButtonClickListener != null) {
                mNegativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(CusRiskTestDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
        }else {
            mNegativeBtn.setVisibility(View.GONE);
        }

        if (mTitle != null){
            tvTitle.setText(mTitle);
        }

        if (mContent != null){
            tvContent.setText(mContent);
        }
    }
    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }

    private void setMessage(String title,String content){
        mTitle = title;
        mContent= content;
    }

    private void setPositiveButtonText(String text){
        positiveButtonText = text;
    }
    private void setNegativeButtonText(String text){
        negativeButtonText = text;
    }

    private void setOnPositiveListener(OnClickListener listener){
        positiveButtonClickListener = listener;
    }

    private void setOnNegativeListener(OnClickListener listener){
        negativeButtonClickListener = listener;
    }

    public static class Builder{
        private Context context;
        private String title;
        private String content;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setMessage(String title,String content){
            this.title = title;
            this.content = content;
            return this;
        }

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

        public Builder setPositiveButtonListener(OnClickListener listener){
            this.positiveButtonClickListener = listener;
            return this;
        }


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
            dialog.getWindow().setDimAmount((float) 0.3);
            dialog.setMessage(title,content);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setOnNegativeListener(negativeButtonClickListener);
            dialog.setOnPositiveListener(positiveButtonClickListener);
            return dialog;
        }

    }

}
