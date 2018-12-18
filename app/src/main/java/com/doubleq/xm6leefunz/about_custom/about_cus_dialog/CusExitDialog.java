package com.doubleq.xm6leefunz.about_custom.about_cus_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.doubleq.xm6leefunz.R;
public class CusExitDialog extends Dialog {
    private Button mPositiveBtn;
    private Button mNegativeBtn;
    private RadioButton radioButtonFirst;
    private RadioButton radioButtonSecond;
    private RadioGroup radioGroup;

    private String message;
    private String message_second;
    private String positiveButtonText;
    private String negativeButtonText;
    private OnClickListener positiveButtonClickListener;
    private OnClickListener negativeButtonClickListener;


    public CusExitDialog(Context context,int theme){
        super(context,theme);
    }

    public CusExitDialog(Context context){
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);

        radioButtonFirst = findViewById(R.id.radioBtn_one);
        radioButtonSecond = findViewById(R.id.radioBtn_two);
        radioGroup = findViewById(R.id.exit_radioGroup);
        mPositiveBtn = findViewById(R.id.btn_dialog_positive);
        mNegativeBtn = findViewById(R.id.btn_dialog_negative);

        if (message != null && message_second != null)
        {
            radioButtonFirst.setText(message);
            radioButtonSecond.setText(message_second);
        }
        if (positiveButtonText != null){
            mPositiveBtn.setText(positiveButtonText);
            if (positiveButtonClickListener != null){
                mPositiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positiveButtonClickListener.onClick(CusExitDialog.this,Dialog.BUTTON_POSITIVE);
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
                        negativeButtonClickListener.onClick(CusExitDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
        }else {
            mNegativeBtn.setVisibility(View.GONE);
        }

        radioButtonFirst.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioBtn_one) {
                    radioButtonFirst.setChecked(true);
                }
                else if(checkedId == R.id.radioBtn_two){
                    radioButtonSecond.setChecked(true);
                }
            }
        });

    }

    private void setMessage(String msg,String msg_second){
        message = msg;
        message_second = msg_second;
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
        private String message;
        private String msg;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setMessage(String message,String msg){
            this.message = message;
            this.msg = msg;
            return this;
        }

//        public Builder setMessage(int message){
//            this.message = context.getString(message);
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

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener){
            return setNegativeButton(context.getString(negativeButtonText),listener);
        }

        public Builder setNegativeButton(String negativeButtonText,OnClickListener listener){
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CusExitDialog create(){
            CusExitDialog dialog = new CusExitDialog(context);
            if (dialog.isShowing()){
                dialog.dismiss();
            }
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            dialog.getWindow().setDimAmount((float) 0.3);
            dialog.setMessage(message,msg);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setOnNegativeListener(negativeButtonClickListener);
            dialog.setOnPositiveListener(positiveButtonClickListener);
            return dialog;
        }

    }

}
