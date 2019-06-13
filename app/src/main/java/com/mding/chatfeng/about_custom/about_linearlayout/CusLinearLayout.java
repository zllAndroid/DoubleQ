package com.mding.chatfeng.about_custom.about_linearlayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

/**
 * 项目：DoubleQ
 * 文件描述：封装自定义的 LinearLayout 行布局
 * 作者：ljj
 * 创建时间：2019/5/14
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class CusLinearLayout extends LinearLayout {


    LinearLayout cusLinearlayoutLinGreyBac;
    View cusLinearlayoutViewLine;
    View cusLinearlayoutViewLineMatch;
    ImageView cusLinearlayoutImgLogo;
    TextView cusLinearlayoutTvTitle;
    TextView cusLinearlayoutTvContent;
    ImageView cusLinearlayoutImgToRight;
    ImageView cusLinearlayoutImgInRight;
    LinearLayout cusLinearlayoutLin;
    Context context;

    public CusLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.cus_linearlayout, this);
        cusLinearlayoutLinGreyBac = inflate.findViewById(R.id.cus_linearlayout_lin_grey_bac);
        cusLinearlayoutViewLine = inflate.findViewById(R.id.cus_linearlayout_view_line);
        cusLinearlayoutViewLineMatch = inflate.findViewById(R.id.cus_linearlayout_view_line_match);
        cusLinearlayoutImgLogo = inflate.findViewById(R.id.cus_linearlayout_img_logo);
        cusLinearlayoutTvTitle = inflate.findViewById(R.id.cus_linearlayout_tv_title);
        cusLinearlayoutTvContent = inflate.findViewById(R.id.cus_linearlayout_tv_content);
        cusLinearlayoutImgToRight = inflate.findViewById(R.id.cus_linearlayout_img_to_right);
        cusLinearlayoutImgInRight = inflate.findViewById(R.id.cus_linearlayout_img_in_right);
        cusLinearlayoutLin = inflate.findViewById(R.id.cus_linearlayout_lin);
    }
    //    设置灰色背景是否可见   默认不可见 false  顶部灰色背景显示的同时将分割线隐藏
    public void setLinGreyBacVisible(boolean visible){
        if (visible){
            cusLinearlayoutLinGreyBac.setVisibility(VISIBLE);
            cusLinearlayoutViewLine.setVisibility(GONE);
            cusLinearlayoutViewLineMatch.setVisibility(GONE);
        }
        else {
            cusLinearlayoutLinGreyBac.setVisibility(GONE);
        }
    }
    //     设置线是否可见   默认可见 true
    public void setViewLineVisible(boolean visible){
        if (visible){
            cusLinearlayoutViewLine.setVisibility(VISIBLE);
        }
        else {
            cusLinearlayoutViewLine.setVisibility(GONE);
        }
    }
    //     设置线是否可见   默认可见 false
    public void setViewLineMatchVisible(boolean visible){
        if (visible){
            cusLinearlayoutViewLineMatch.setVisibility(VISIBLE);
        }
        else {
            cusLinearlayoutViewLineMatch.setVisibility(GONE);
        }
    }
    //     设置图片
    public void setImgLogo(Drawable image) {
        if (image != null)
            cusLinearlayoutImgLogo.setImageDrawable(image);
    }
    //    设置Logo是否可见   默认可见 true
    public void setLinImgLogoVisible(boolean visible){
        if (visible){
            cusLinearlayoutImgLogo.setVisibility(VISIBLE);
        }
        else {
            cusLinearlayoutImgLogo.setVisibility(GONE);
            cusLinearlayoutViewLine.setVisibility(GONE);
            cusLinearlayoutViewLineMatch.setVisibility(VISIBLE);
        }
    }
    //     设置行标题
    public void setTvTitle(String title) {
        if (!StrUtils.isEmpty(title)) {
            cusLinearlayoutTvTitle.setText(title);
        }
    }
    //     设置行尾内容文字 + 是否可见
    public void setTvContent(String content) {
        if (!StrUtils.isEmpty(content)) {
            cusLinearlayoutTvContent.setText(content);
            cusLinearlayoutTvContent.setVisibility(VISIBLE);
        } else {
            cusLinearlayoutTvContent.setVisibility(GONE);
        }
    }
    //      获取尾部的内容
    public String getTvContent(){
        String text = cusLinearlayoutTvContent.getText().toString();
        ToastUtil.isDebugShow("缓存有：" + text);
        return text;
    }
    //     设置行尾内容是否可见   默认不可见 false
    public void setTvContentTextColor(int color){
        cusLinearlayoutTvContent.setTextColor(color);
    }
    //     设置行尾向右图标是否可见   默认不可见 true
    public void setImgToRightVisible(boolean visible){
        if (visible){
            cusLinearlayoutImgToRight.setVisibility(VISIBLE);
        }
        else {
            cusLinearlayoutImgToRight.setVisibility(GONE);

        }
    }
    //      设置右侧图片   默认不可见  当有图片传入时，设置可见
    public void setImgInRight(Drawable drawable){
        if (drawable != null){
            cusLinearlayoutImgInRight.setVisibility(VISIBLE);
            cusLinearlayoutImgInRight.setImageDrawable(drawable);
        }
//        else {
//            cusLinearlayoutImgInRight.setVisibility(GONE);
//        }
    }
    //     设置行点击事件
    public void setOnLinClick(OnClickListener linClick){
        cusLinearlayoutLin.setOnClickListener(linClick);
    }
    //     设置高度
    public void setLinHeight(int px){
        if (px > 0){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cusLinearlayoutLin.getLayoutParams();
            params.height = px;
            cusLinearlayoutLin.setLayoutParams(params);
        }
    }
}
