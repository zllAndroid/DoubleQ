package com.projects.zll.utilslibrarybyzll.about_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.projects.zll.utilslibrarybyzll.R;


/**
 * Created by Administrator on 2017/10/20 0020.
 */

public  class CustomDialog extends Dialog {
    private TextView mMessageTv;
    private Button mPositiveBtn;
    private Button mNegativeBtn;
//    private View mButtonDividerView;

    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private OnClickListener positiveButtonClickListener;
    private OnClickListener negativeButtonClickListener;
    public CustomDialog(Context context) {
        super(context);
    }

//    public CustomDialog(Context context, int theme) {
//        super(context, theme);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        mMessageTv = (TextView) findViewById(R.id.tv_dialog_message);
        mPositiveBtn = (Button) findViewById(R.id.btn_dialog_positive);
        mNegativeBtn = (Button) findViewById(R.id.btn_dialog_negative);
//        mButtonDividerView = findViewById(R.id.view_dialog_button_divider);

        if (message != null) {
            mMessageTv.setText(message);
        }
        if (positiveButtonText != null) {
            mPositiveBtn.setText(positiveButtonText);
            if (positiveButtonClickListener != null) {
                mPositiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveButtonClickListener.onClick(CustomDialog.this, Dialog.BUTTON_POSITIVE);
                    }
                });
            }
        } else {
            mPositiveBtn.setVisibility(View.GONE);
//            mButtonDividerView.setVisibility(View.GONE);
        }

        if (negativeButtonText != null) {
            mNegativeBtn.setText(negativeButtonText);
            if (negativeButtonClickListener != null) {
                mNegativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(CustomDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
        } else {
            mNegativeBtn.setVisibility(View.GONE);
//            mButtonDividerView.setVisibility(View.GONE);
        }

    }

    private void setMessage(String msg) {
        message = msg;
    }

    private void setPositiveButtonText(String text) {
        positiveButtonText = text;
    }

    private void setNegativeButtonText(String text) {
        negativeButtonText = text;
    }

    private void setOnNegativeListener(OnClickListener listener) {
        negativeButtonClickListener = listener;
    }

    private void setOnPositiveListener(OnClickListener listener) {
        positiveButtonClickListener = listener;
    }

    public static class Builder {
        private Context context;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = context.getString(message);
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            return setPositiveButton(context.getString(positiveButtonText), listener);
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            return setNegativeButton(context.getString(negativeButtonText), listener);
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            CustomDialog dialog = new CustomDialog(context);

            if (dialog.isShowing() && null != dialog){
                dialog.dismiss();
            }
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            dialog.setMessage(message);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setOnNegativeListener(negativeButtonClickListener);
            dialog.setOnPositiveListener(positiveButtonClickListener);
            return dialog;
        }
    }
}
