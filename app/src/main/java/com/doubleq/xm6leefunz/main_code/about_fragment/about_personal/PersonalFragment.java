package com.doubleq.xm6leefunz.main_code.about_fragment.about_personal;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.main_code.about_fragment.about_personal.about_activity.MyAccountActivity;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener{

    public PersonalFragment() {
    }

    private PopupWindow mNoticePopWindow;
    View view;
    ImageView img_head;
    ImageView img_notice;
    int head;
    TextView tv_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_personal, container, false);
        }

        img_head = view.findViewById(R.id.frag_img_personal_head);
        tv_name = view.findViewById(R.id.frag_tv_personal_name);
        tv_name.setText("小萝莉");
        head = R.drawable.img_personal_head2;

        img_notice = view.findViewById(R.id.frag_img_personal_notice);
        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoticePopWindow();
            }
        });

//        我的账号
        view.findViewById(R.id.ac_lin_personal_myaccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyAccountActivity.class));
            }
        });

////        我的二维码
//        view.findViewById(R.id.ac_lin_personal_QRCode).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), QRCodeActivity.class));
//            }
//        });
        return view;
}

    private void showNoticePopWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_notice_popwindow,null);
        mNoticePopWindow = new PopupWindow(contentView);
        mNoticePopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mNoticePopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv_vibration = contentView.findViewById(R.id.frag_tv_popwindow_vibration);
        TextView tv_voice = contentView.findViewById(R.id.frag_tv_popwindow_voice);
        TextView tv_del_group = contentView.findViewById(R.id.frag_tv_popwindow_del_group);
        tv_vibration.setOnClickListener(this);
        tv_voice.setOnClickListener(this);
        tv_del_group.setOnClickListener(this);

        mNoticePopWindow.setBackgroundDrawable(new ColorDrawable());
        mNoticePopWindow.setOutsideTouchable(true);
        mNoticePopWindow.showAsDropDown(img_notice);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.frag_tv_popwindow_vibration:{
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(),"点击了“震动”",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_voice:{
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(),"点击了“声音”",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.frag_tv_popwindow_del_group:{
                mNoticePopWindow.dismiss();
                Toast.makeText(this.getActivity(),"点击了“删除群组”",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(getActivity()).load(head)
                .bitmapTransform(new CropCircleTransformation(getActivity())).crossFade(1000)
                .into(img_head);

    }
}
