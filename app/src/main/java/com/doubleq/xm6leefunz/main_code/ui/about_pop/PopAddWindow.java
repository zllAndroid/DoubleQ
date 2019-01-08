package com.doubleq.xm6leefunz.main_code.ui.about_pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

public class PopAddWindow extends PopupWindow implements View.OnClickListener {

    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    public PopAddWindow(Context context,View mAsDropDown) {
        super(context);
        this.mContext = context;
        //打气筒
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_notice_popwindow,null);
        //设置View
        setContentView(mContentView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
//        setBackgroundDrawable(new ColorDrawable());
        ColorDrawable dw = new ColorDrawable(0x33000000);
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
        //在某控件之下
        showAsDropDown(mAsDropDown);
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
    private void initView() {
        TextView tv_vibration = mContentView.findViewById(R.id.frag_tv_popwindow_vibration);
        TextView tv_voice = mContentView.findViewById(R.id.frag_tv_popwindow_voice);
        TextView tv_del_group = mContentView.findViewById(R.id.frag_tv_popwindow_del_group);
        tv_vibration.setOnClickListener(this);
        tv_voice.setOnClickListener(this);
        tv_del_group.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.frag_tv_popwindow_vibration:{
                onAddpopClickListener.onVibration();
                dismiss();
                Toast.makeText(mContext,"点击了“震动”",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_voice:{
                dismiss();
                onAddpopClickListener.onVoide();
                Toast.makeText(mContext,"点击了“声音”",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_del_group:{
                dismiss();
                onAddpopClickListener.onVdel();
                Toast.makeText(mContext,"点击了“删除群组”",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

   public interface OnAddpopClickListener {
        void onVibration();
        void onVoide();
        void onVdel();
    }
    OnAddpopClickListener onAddpopClickListener;
    public void setOnAddpopClickListener(OnAddpopClickListener onAddpopClickListener) {
        this.onAddpopClickListener = onAddpopClickListener;
    }

}
