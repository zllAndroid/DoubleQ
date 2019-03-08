package com.doubleq.xm6leefunz.about_custom.about_top_bar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;

/**
 * create by tts
 * create on 2019/2/14 0002
 * email : a463944185@163.com
 */

public class TopBarLayout extends LinearLayout {

    ImageView img_back;
    TextView tv_title;
    TextView tv_save;
    ImageView img_right;

    public TopBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_topbar, this);
        img_back = inflate.findViewById(R.id.include_top_iv_back);
        tv_title = inflate.findViewById(R.id.include_top_tv_tital);
        tv_save = inflate.findViewById(R.id.inclu_tv_right);
        img_right = inflate.findViewById(R.id.include_top_iv_more);
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public void setOnBackClick(OnClickListener backClick){
        img_back.setOnClickListener(backClick);
    }
    public void setOnSaveClick(OnClickListener saveClick){
        tv_save.setOnClickListener(saveClick);
    }
    public void setOnRightImgClick(OnClickListener rightImgClick){
        img_right.setOnClickListener(rightImgClick);
    }
    public void setRightImgDrawable(int drawable){
        tv_save.setVisibility(GONE);
        img_right.setVisibility(VISIBLE);
        img_right.setImageResource(drawable);
    }
    public void setRightImgVisible(int visible){
        img_right.setVisibility(visible);
    }
    public void setRightTextVisible(int visible){
        tv_save.setVisibility(visible);
    }

}
