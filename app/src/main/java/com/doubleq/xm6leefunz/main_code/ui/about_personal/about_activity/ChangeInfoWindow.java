package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.io.UnsupportedEncodingException;

import javax.xml.transform.Templates;

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
        if (!StrUtils.isEmpty(title))
        {
            if (title.equals("修改备注")){
                Log.e("beizhu","-----------initBeiZhuView--------------"+title);
                initBeiZhuView();
            }
            else{
                initView();
                Log.e("beizhu","-----------initView--------------"+title);
            }
        }

    }
    EditText mEd;
    EditText mEdBeizhu;
    TextView mTvTitle;
    private void initView() {
        Button mBtnCancle = mContentView.findViewById(R.id.pop_btn_cancle);
        Button mBtnSure = mContentView.findViewById(R.id.pop_btn_sure);
        mTvTitle = mContentView.findViewById(R.id.pop_tv_title);
        mEd = mContentView.findViewById(R.id.pop_ed_contant);
        mEdBeizhu = mContentView.findViewById(R.id.pop_ed_beizhu);
        mBtnCancle.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mTvTitle.setText(title);
        if (!StrUtils.isEmpty(mContant)) {
            mEd.setText(mContant);
            mEd.setSelection(mEd.getText().toString().length());
        }
    }
    private void initBeiZhuView() {
        Button mBtnCancle = mContentView.findViewById(R.id.pop_btn_cancle);
        Button mBtnSure = mContentView.findViewById(R.id.pop_btn_sure);
        mTvTitle = mContentView.findViewById(R.id.pop_tv_title);
        mEd = mContentView.findViewById(R.id.pop_ed_contant);
        mEdBeizhu = mContentView.findViewById(R.id.pop_ed_beizhu);

        mBtnCancle.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mEd.setVisibility(View.GONE);
        mEdBeizhu.setVisibility(View.VISIBLE);
        Log.e("beizhu","-----------mContant--------------"+mContant+"+++++"+mContant.length());
        if (!StrUtils.isEmpty(mContant)) {
            mContant = mContant.substring(1,mContant.length()-1);
            Log.e("beizhu","-----------mContant.substring--------------"+mContant+"+++++"+mContant.length());
            mEdBeizhu.setText(mContant);
            mEdBeizhu.setSelection(mEdBeizhu.getText().toString().length(),mEdBeizhu.getText().toString().length());
            mEdBeizhu.addTextChangedListener(textWatcher);
        }
        int num=0;
        try {
            num= mContant.getBytes("gbk").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mTvTitle.setText("修改备注(" + (num) + "/" + MAX_NUM + ")");

    }


    private static final int MAX_NUM = 16;
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

//             统计字母数字个数
            int count_num = 0;

            //统计汉字的个数
            int count_char =0;
            for(int i=0;i<editable.length();i++){
                String b=Character.toString(editable.charAt(i));
//                char cs =editable.charAt(i);
                String Reg="^[\u4e00-\u9fa5]{1}$";  //汉字的正规表达式
                if(b.matches(Reg))
                    count_char = count_char + 2;
                else {
                    count_num++;
                }
            }

            int count;
            count = count_num + count_char;

            int num=0;
            try {
                num= mContant.getBytes("gbk").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (num > MAX_NUM) {
//                mTvTitle.setText("修改备注(" + count + "/" + MAX_NUM + ")");
//                try {
                    editable.delete(MAX_NUM,num);
//                }
//                catch (Exception ignored){
//                }

            }

            else
//            mEd.getText().toString()
            mTvTitle.setText("修改备注(" + num + "/" + MAX_NUM + ")");
//            Log.e("beizhu","+++++editable++++++"+editable.length());
//            if (editable.length() > MAX_NUM) {
//                editable.delete(MAX_NUM, editable.length());
//            }
//            mTvTitle.setText("修改备注(" + editable.length() + "/" + MAX_NUM + ")");


        }
    };

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
                if (!StrUtils.isEmpty(title)){
                    if (title.equals("修改备注"))
                        onAddContantClickListener.onSure(mEdBeizhu.getText().toString().trim());
                    else
                        onAddContantClickListener.onSure(mEd.getText().toString().trim());
                }
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
