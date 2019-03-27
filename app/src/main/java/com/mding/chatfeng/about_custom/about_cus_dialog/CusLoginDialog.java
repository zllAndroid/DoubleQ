package com.mding.chatfeng.about_custom.about_cus_dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mding.chatfeng.R;

public class CusLoginDialog extends Dialog {

    private Context context;
    private TextView tv_forgetPWD;
    private TextView tv_notice;
    private Button btn_reWrite;
    private ImageView img_close;
    private String message;
    private String forgetPwdButtonText;
    private String reInputButtonText;
    private OnClickListener forgetPwdButtonClickListener;
    private OnClickListener reInputButtonClickListener;
    private OnClickListener closeClickListener;

    public CusLoginDialog(Context context) {
        super(context);
    }

    public CusLoginDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);

        tv_notice = findViewById(R.id.dialog_tv_notice);
        tv_forgetPWD = findViewById(R.id.tv_dialog_forgetPWD);
        btn_reWrite = findViewById(R.id.btn_dialog_reInput);
        img_close = findViewById(R.id.dialog_img_close);

//        TextView添加下划线
        tv_forgetPWD.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        if (message != null)
        {
            tv_notice.setText(message);
        }
        if (forgetPwdButtonText != null){
            tv_forgetPWD.setText(forgetPwdButtonText);
            if (forgetPwdButtonClickListener != null){
                tv_forgetPWD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        forgetPwdButtonClickListener.onClick(CusLoginDialog.this,Dialog.BUTTON_POSITIVE);
                    }
                });
            }
        }else {
            tv_forgetPWD.setVisibility(View.GONE);
        }

        if (reInputButtonText != null){
            btn_reWrite.setText(reInputButtonText);
            if (reInputButtonClickListener != null) {
                btn_reWrite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reInputButtonClickListener.onClick(CusLoginDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
        }else {
            btn_reWrite.setVisibility(View.GONE);
        }

        if (closeClickListener!=null)
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    closeClickListener.onClick(CusLoginDialog.this,Dialog.BUTTON_NEGATIVE);
                }
            });

    }

    private void setMessage(String msg){
        message = msg;
    }

    private void setForgetPwdButtonText(String text){
        forgetPwdButtonText = text;
    }
    private void setReInputButtonText(String text){
        reInputButtonText = text;
    }

    private void setOnForgetPwdListener(OnClickListener listener){
        forgetPwdButtonClickListener = listener;
    }

    private void setOnCloseListener(OnClickListener listener){
        closeClickListener = listener;
    }

    private void setOnReInputListener(OnClickListener listener){
        reInputButtonClickListener = listener;
    }


    public static class Builder {
        private Context context;
        private String message;
        private String forgetPwdButtonText;
        private String reInputButtonText;
        private OnClickListener forgetPwdButtonClickListener;
        private OnClickListener reInputButtonClickListener;
        private OnClickListener closeClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }


        public Builder setForgetPwdButton(int forgetPwdButtonText, OnClickListener listener) {
            return setForgetPwdButton(context.getString(forgetPwdButtonText), listener);
        }

        public Builder setForgetPwdButton(String forgetPwdButtonText, OnClickListener listener) {
            this.forgetPwdButtonText = forgetPwdButtonText;
            this.forgetPwdButtonClickListener = listener;
            return this;
        }

        public Builder setReInputButton(int reInputButtonText, OnClickListener listener) {
            return setReInputButton(context.getString(reInputButtonText), listener);
        }

        public Builder setReInputButton(String reInputButtonText, OnClickListener listener) {
            this.reInputButtonText = reInputButtonText;
            this.reInputButtonClickListener = listener;
            return this;
        }

        public Builder setClose(OnClickListener listener) {
            this.closeClickListener = listener;
            return this;
        }

        public CusLoginDialog create(){
            CusLoginDialog dialog = new CusLoginDialog(context);
            if (dialog.isShowing()){
                dialog.dismiss();
            }
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            dialog.getWindow().setDimAmount((float) 0.3);
            dialog.setMessage(message);
            dialog.setReInputButtonText(reInputButtonText);
            dialog.setForgetPwdButtonText(forgetPwdButtonText);
            dialog.setOnReInputListener(reInputButtonClickListener);
            dialog.setOnForgetPwdListener(forgetPwdButtonClickListener);
            dialog.setOnCloseListener(closeClickListener);
            return dialog;
        }

    }
}
