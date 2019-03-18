package com.doubleq.xm6leefunz.about_custom.about_top_bar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

/**
 * create by tts
 * create on 2019/2/14 0002
 * email : a463944185@163.com
 */

public class FragmentTopBarLayout extends LinearLayout {

    //    ImageView img_back;
    TextView tv_title;
    //    TextView tv_save;
//    ImageView img_right;
    LinearLayout mSearch;
    LinearLayout mAdd,mTopLinBac;
    View mTopView;
    public FragmentTopBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_top_bar, this);
        mSearch = inflate.findViewById(R.id.frag_top_lin_search);
        mAdd = inflate.findViewById(R.id.frag_top_lin_add);
        tv_title = inflate.findViewById(R.id.include_frag_tv_title);
        mTopView = inflate.findViewById(R.id.main_view_top);
        mTopLinBac = inflate.findViewById(R.id.include_frag_lin_bac);
        setTop(getContext());
//        img_back = inflate.findViewById(R.id.include_top_iv_back);
//        tv_save = inflate.findViewById(R.id.inclu_tv_right);
//        img_right = inflate.findViewById(R.id.include_top_iv_more)
    }

    public void setTitle(String title){
        if (!StrUtils.isEmpty(title))
            tv_title.setText(title);
    }
    public void setTopLinBackground(int color){
        if (color!=0)
            mTopLinBac.setBackgroundColor(color);
    }

    public void setTopLinBackDrawable(Drawable drawable){
        if (drawable!=null){
            mTopLinBac.setBackground(drawable);
//            mTopLinBac.setBackground(getResources().getDrawable(R.drawable.fragment_top));
        }
    }

    public void setTop(Context mContext){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View viewById = mContext.findViewById(R.id.main_view_top);
            // 设置状态栏高度
            int statusBarHeight = WindowBugDeal.getStatusBarHeight(mContext);
            //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
            LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)mTopView.getLayoutParams();
            //获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0,-statusBarHeight,0,0);
            layout.height=statusBarHeight;
            //设置button的新位置属性,left，top，right，bottom
            mTopView.setLayoutParams(layout);
            if (mTopView!=null)
                mTopView .setVisibility(View.VISIBLE);
        }
    }

    public void setOnSearchClick(OnClickListener backClick){
        mSearch.setOnClickListener(backClick);
    }
    public void setOnRightClick(OnClickListener rightImgClick){
        mAdd.setOnClickListener(rightImgClick);
    }

}
