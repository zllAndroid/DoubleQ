package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
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
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomDialog;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
            initView();
        }
    }
    EditText mEd;
    TextView mTvTitle;
    TextView mTvMax;
    private static final int BEIZHU_MAX_NUM = 10;
    private static final int FENZU_MAX_NUM = 10;
    private static final int COUNT_MAX_NUM = 25;
    private static final int SIGN_MAX_NUM = 30;
    private static final int REPLY_MAX_NUM = 20;
    private static int MAX_NUM = 0;
    private void initView() {
        Button mBtnCancle = mContentView.findViewById(R.id.pop_btn_cancle);
        Button mBtnSure = mContentView.findViewById(R.id.pop_btn_sure);
        mTvTitle = mContentView.findViewById(R.id.pop_tv_title);
        mTvMax = mContentView.findViewById(R.id.pop_tv_max);
        mEd = mContentView.findViewById(R.id.pop_ed_contant);

        mBtnCancle.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mTvTitle.setText(title);

        switch (title) {
            case "修改备注": {
                if (mContant.length() > BEIZHU_MAX_NUM) {
                    mTvMax.setText(BEIZHU_MAX_NUM + " / " + BEIZHU_MAX_NUM);
                    mEd.setText(mContant.substring(0, BEIZHU_MAX_NUM ));
                } else{
                    if (mContant.equals(""))
                        mTvMax.setText("0 / " + BEIZHU_MAX_NUM);
                    else{
                        mTvMax.setText(mContant.length() + " / " + BEIZHU_MAX_NUM);
                        InputFilter[] filter = {new InputFilter.LengthFilter(10)};
                        mEd.setFilters(filter);
                    }
                }
                break;
            }
            case "修改名字": {
                if (mContant.length() > BEIZHU_MAX_NUM) {
                    mTvMax.setText(BEIZHU_MAX_NUM + " / " + BEIZHU_MAX_NUM);
                    mEd.setText(mContant.substring(0, BEIZHU_MAX_NUM));
                } else
                    mTvMax.setText(mContant.length() + " / " + BEIZHU_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(BEIZHU_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
            case "修改帐号": {
//                if (mContant.length() > COUNT_MAX_NUM) {
//                    mTvMax.setText(COUNT_MAX_NUM + " / " + COUNT_MAX_NUM);
//                    mEd.setText(mContant.substring(0, COUNT_MAX_NUM));
//                } else
                mTvMax.setText("0 / " + COUNT_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(COUNT_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
            case "修改个性签名": {
                if (mContant.length() > SIGN_MAX_NUM) {
                    mTvMax.setText(SIGN_MAX_NUM + " / " + SIGN_MAX_NUM);
                    mEd.setText(mContant.substring(0, SIGN_MAX_NUM));
                } else
                    mTvMax.setText(mContant.length() + " / " + SIGN_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(SIGN_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
            case "修改分组":mEd.setHint("请输入分组名称");
            case "修改群名":
            case "修改群名片":
            {
                if (mContant.length() > FENZU_MAX_NUM) {
                    mTvMax.setText(FENZU_MAX_NUM + " / " + FENZU_MAX_NUM);
                    mEd.setText(mContant.substring(0, FENZU_MAX_NUM));
                } else
                    mTvMax.setText(mContant.length() + " / " + FENZU_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(FENZU_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
            case "增加分组":{
                mEd.setHint("请输入分组名称");
                mTvMax.setText(mContant.length() + " / " + FENZU_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(FENZU_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
            case "回复":{
                mTvMax.setText(mContant.length() + " / " + REPLY_MAX_NUM);
                InputFilter[] filter = {new InputFilter.LengthFilter(REPLY_MAX_NUM)};
                mEd.setFilters(filter);
                break;
            }
        }

        if (title.equals("修改备注") || title.equals("修改群名片")){
            if (mContant.equals("")){
//            if (mContant.trim().equals("暂未设置备注") || mContant.equals("")){
                mEd.setText("");
                mEd.addTextChangedListener(textWatcher);
            }
            else{
                mEd.setText(mContant);
                mEd.setSelection(mEd.getText().toString().length());
                mEd.addTextChangedListener(textWatcher);
            }

        }
        else if (title.equals("修改帐号") || title.equals("增加分组") ||  title.equals("回复") ){
            mEd.setText("");
            mEd.setSelection(mEd.getText().toString().length());
            mEd.addTextChangedListener(textWatcher);
        }
        else if (!StrUtils.isEmpty(mContant)) {
            mEd.setText(mContant);
            mEd.setSelection(mEd.getText().toString().length());
            mEd.addTextChangedListener(textWatcher);
        }
    }

    String string;
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (title) {
                case "修改备注":
                case "修改名字":
                case "修改分组":
                case "增加分组":
                case "修改群名":
                case "修改群名片":
                    MAX_NUM = BEIZHU_MAX_NUM;
                    break;
                case "修改帐号":
                    MAX_NUM = COUNT_MAX_NUM;
                    if (editable.length() > 0) {
                        for (int i = 0; i < editable.length(); i++) {
                            char c = editable.charAt(i);
                            if (c >= 0x4e00 && c <= 0X9fff) { // 根据字节码判断
                                // 如果是中文，则清除输入的字符，否则保留
                                editable.delete(i, i + 1);
                            }
                        }
                        string = editable.toString().replace(" ", "");
                        Log.e("modifyCount", "-------------------------" + string);
                    }
                    break;
                case "修改个性签名":
                    MAX_NUM = SIGN_MAX_NUM;
                    break;
                case "回复":
                    MAX_NUM = REPLY_MAX_NUM;
                    break;
            }
            mEd.setSelection(mEd.getText().toString().length());
            if (editable.length() > MAX_NUM) {
                editable.delete(MAX_NUM, editable.length());
            }
            mTvMax.setText(editable.length() + " / " + MAX_NUM);
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
                    if (title.equals("修改帐号")){
                        DialogUtils.showDialog("帐号只能修改一次，\n确定要修改为“"+ string +"”吗？", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                                onAddContantClickListener.onSure(string);
                            }
                        });
                    }
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
