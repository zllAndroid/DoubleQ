package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

public class ChangeInfoWindow extends PopupWindow implements View.OnClickListener {

    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    String title;
    String cancleText=null;
    String mContant=null;
    String sureText=null;
    public ChangeInfoWindow(Context context,String title,String mContant)
    {
        this.mContext = context;
        this.mContant = mContant;
        this.title = title;
        initPop(context);
    }
//    public ChangeInfoWindow(Context context, View mAsDropDown) {
//        super(context);
//        this.mContext = context;
//        initPop(context);
//
//    }

    private void initPop(Context context) {
        //打气筒
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.pop_changeinfo,null);
        //设置View
        setContentView(mContentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
//        setBackgroundDrawable(new ColorDrawable());
        ColorDrawable dw = new ColorDrawable(0x99000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
//        setBackgroundDrawable(new ColorDrawable());

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);
//        setOutsideTouchable(false);
        //在某控件之下
//        showAsDropDown(mAsDropDown);
        /**
         *设置可以触摸
         */
        setTouchable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });

        /**
         * 初始化View与监听器
         */
        initView();
    }
    EditText mEd;
    private void initView() {
        Button mBtnCancle = mContentView.findViewById(R.id.pop_btn_cancle);
        Button mBtnSure = mContentView.findViewById(R.id.pop_btn_sure);
        TextView mTvTitle = mContentView.findViewById(R.id.pop_tv_title);
        mEd = mContentView.findViewById(R.id.pop_ed_contant);
        mBtnCancle.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mTvTitle.setText(title);
        if (!StrUtils.isEmpty(mContant)) {
            mEd.setText(mContant);
            mEd.setSelection(mEd.getText().toString().length());
        }
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.pop_btn_cancle:{
                dismiss();
                onAddContantClickListener.onCancle();
            }
            break;
            case R.id.pop_btn_sure:{
                dismiss();
                onAddContantClickListener.onSure(mEd.getText().toString().trim());
            }
            break;
        }
    }

    public interface OnAddContantClickListener {
        void onSure(String contant);
        void onCancle();
    }
    OnAddContantClickListener onAddContantClickListener;
    public void setOnAddpopClickListener(OnAddContantClickListener onAddContantClickListener) {
        this.onAddContantClickListener = onAddContantClickListener;
    }

}
