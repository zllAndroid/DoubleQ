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
    ImageView cusLinearlayoutImgLogo;
    TextView cusLinearlayoutTvTitle;
    TextView cusLinearlayoutTvContent;
    ImageView cusLinearlayoutImgToRight;
    LinearLayout cusLinearlayoutLin;
    Context context;

    public CusLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.cus_linearlayout, this);
        cusLinearlayoutLinGreyBac = inflate.findViewById(R.id.cus_linearlayout_lin_grey_bac);
        cusLinearlayoutViewLine = inflate.findViewById(R.id.cus_linearlayout_view_line);
        cusLinearlayoutImgLogo = inflate.findViewById(R.id.cus_linearlayout_img_logo);
        cusLinearlayoutTvTitle = inflate.findViewById(R.id.cus_linearlayout_tv_title);
        cusLinearlayoutTvContent = inflate.findViewById(R.id.cus_linearlayout_tv_content);
        cusLinearlayoutImgToRight = inflate.findViewById(R.id.cus_linearlayout_img_to_right);
        cusLinearlayoutLin = inflate.findViewById(R.id.cus_linearlayout_lin);
    }
    //    设置灰色背景是否可见   默认不可见 false
    public void setLinGreyBacVisible(boolean visible){
        if (visible){
            cusLinearlayoutLinGreyBac.setVisibility(VISIBLE);
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
    //     设置图片
    public void setImgLogo(Drawable image) {
        if (image != null)
            cusLinearlayoutImgLogo.setImageDrawable(image);
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
            cusLinearlayoutImgToRight.setVisibility(GONE);
        } else {
            cusLinearlayoutTvContent.setVisibility(GONE);
            cusLinearlayoutImgToRight.setVisibility(VISIBLE);
        }
    }
    public String getTvContent(){
        String text = cusLinearlayoutTvContent.getText().toString();
        ToastUtil.isDebugShow("缓存有：" + text);
        return text;
    }
    //     设置行尾内容是否可见   默认不可见 false
    public void setTvContentVisible(boolean visible){
        if (visible){
            cusLinearlayoutTvContent.setVisibility(VISIBLE);
            cusLinearlayoutImgToRight.setVisibility(GONE);
        }
        else {
            cusLinearlayoutTvContent.setVisibility(GONE);
            cusLinearlayoutImgToRight.setVisibility(VISIBLE);
        }
    }
    //     设置行点击事件
    public void setOnLinClick(OnClickListener linClick){
        cusLinearlayoutLin.setOnClickListener(linClick);
    }
    public void setLinHeight(int px){
        if (px > 0){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cusLinearlayoutLin.getLayoutParams();
            params.height = px;
            cusLinearlayoutLin.setLayoutParams(params);
        }
    }
}
