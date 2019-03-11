package com.doubleq.xm6leefunz.about_custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;


public class FindItemLayout extends LinearLayout {
    ImageView mIv;
    TextView mTvContant;
    LinearLayout  mLin;
    public FindItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_find_item, this);
        mLin = inflate.findViewById(R.id.find_item_lin);
        mTvContant = inflate.findViewById(R.id.find_item_tv);
        mIv = inflate.findViewById(R.id.find_item_iv);
    }

    public void setTitle(String title){
        mTvContant.setText(title);
    }

    public void setOnLinearLayoutClick(OnClickListener backClick){
        mLin.setOnClickListener(backClick);
    }
    public void setFindImgDrawable(int drawable){
        mIv.setImageResource(drawable);
    }
}
