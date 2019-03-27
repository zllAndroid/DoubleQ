package com.mding.chatfeng.about_chat;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mding.chatfeng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 物品选择
 */
public class ChatNewsWindow extends PopupWindow {

    Context mContext;
    //    @BindView(R.id.pop_choose_iv_close)
//    ImageView popChooseIvClose;
//   @BindView(R.id.pop_choose_recyc)
//   RecyclerView mRecyclerView;
    private LayoutInflater mInflater;
    private View mContentView;
    RecyclerView mRecyclerView;
    String text;
    List<String> arrayList = new ArrayList<>();
    public ChatNewsWindow(Context context,String text) {
        super(context);

        this.text = text;
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.item_chat_new, null);
        //设置View
        setContentView(mContentView);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);

//        setHeight((MainActivity.screenHeight) / 4);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        /**
         * 设置进出动画
         */
        setAnimationStyle(R.style.AnimDown);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
//        setBackgroundDrawable(new ColorDrawable());
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);


        /**pop_lin_choose
         * 设置点击外部可以消失
         */
        mContentView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mContentView.findViewById(R.id.item_tv_news).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
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
        TextView mTv = mContentView.findViewById(R.id.item_tv_news);
        mTv.setText(text);
    }

}