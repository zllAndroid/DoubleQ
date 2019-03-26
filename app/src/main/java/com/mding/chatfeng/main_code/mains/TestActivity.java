package com.mding.chatfeng.main_code.mains;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.main_code.mains.top_pop.ChatPopWindow;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.include_top_iv_drop)
    ImageView includeTopIvDrop;

    boolean isDrop = true;
    @BindView(R.id.include_top_lin_title)
    LinearLayout includeTopLinTitle;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_test;
    }

    ChatPopWindow chatPopWindow;

    @Override
    protected void initBaseView() {
        super.initBaseView();

//        chatPopWindow = new ChatPopWindow(TestActivity.this);
        includeTopLinTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isDrop) {
                    includeTopIvDrop.setImageResource(R.drawable.spinner_down);
//                    ChatPopWindow chatPopWindow = new ChatPopWindow(MyApplication.getAppContext());
                    chatPopWindow.showAtBottom(view.findViewById(R.id.include_top_lin_title));
//                    return;
                } else {
                    chatPopWindow.dismiss();
                    includeTopIvDrop.setImageResource(R.drawable.spinner_right);
                }
                isDrop = !isDrop;
            }
        });

//spinner
//        includeTopSpinner.setBackgroundResource(R.drawable.spinner_selector_chat);
////        设置下拉框的偏移量
//        includeTopSpinner.setDropDownVerticalOffset(100);
//
//
//        List<SpinnerItem> spinnerItemsList = new ArrayList<>();
////        spinnerItemsList.add(new SpinnerItem("",""));
//        spinnerItemsList.add(new SpinnerItem("好友资料", ""));
//        spinnerItemsList.add(new SpinnerItem("好友分组", "同学"));
//        spinnerItemsList.add(new SpinnerItem("备注", ""));
//
//        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, spinnerItemsList);
//        includeTopSpinner.setAdapter(spinnerAdapter);
////        includeTopSpinner.attachDataSource(spinnerItems);
//
////        ArrayList<String> list = new ArrayList<>();
////        list.add("好友资料");
////        list.add("好友分组");
////        list.add("备注");
////        /*
////         * 第二个参数是显示的布局
////         * 第三个参数是在布局显示的位置id
////         * 第四个参数是将要显示的数据
////         */
////        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_spinner_chat,list);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        includeTopSpinner.setAdapter(adapter);
//
//        includeTopLinSpinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                includeTopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                        String selected = adapterView.getItemAtPosition(position).toString();
//                        ToastUtil.show("选择了：" + selected);
//                        Log.e("spinner", "选择了：" + selected);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//                        ToastUtil.show("没有选择");
//                    }
//                });
//            }
//        });
//
    }

    @OnClick(R.id.ac_lin_test)
    public void onViewClicked() {
        chatPopWindow.dismiss();
        includeTopIvDrop.setImageResource(R.drawable.spinner_right);
    }

}

//class SpinnerItem {
//    private String spinnerName;
//    private String spinnerContact;
//
//    SpinnerItem(String spinnerName, String spinnerContact) {
//        this.spinnerName = spinnerName;
//        this.spinnerContact = spinnerContact;
//    }
//
//    String getSpinnerName() {
//        return spinnerName;
//    }
//
//    public void setSpinnerName(String spinnerName) {
//        this.spinnerName = spinnerName;
//    }
//
//    String getSpinnerContact() {
//        return spinnerContact;
//    }
//
//    public void setSpinnerContact(String spinnerContact) {
//        this.spinnerContact = spinnerContact;
//    }
//}
//
//class SpinnerAdapter extends BaseAdapter {
//    private List<SpinnerItem> mList;
//    private Context mContext;
//
//    SpinnerAdapter(Context context, List<SpinnerItem> list) {
//        this.mContext = context;
//        this.mList = list;
//    }
//
//    @Override
//    public int getCount() {
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @SuppressLint({"ViewHolder", "InflateParams"})
//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        convertView = layoutInflater.inflate(R.layout.item_spinner_chat, null);
//        if (convertView != null) {
//            TextView tvSpinnerName = convertView.findViewById(R.id.item_spinner_tv_name);
//            TextView tvSpinnerContact = convertView.findViewById(R.id.item_spinner_tv_contact);
//            tvSpinnerName.setText(mList.get(position).getSpinnerName());
//            tvSpinnerContact.setText(mList.get(position).getSpinnerContact());
//        }
//        return convertView;
//    }

//    //    监听是否被点击的接口
//    private OnSpinnerClickListener mSpinnerClickListener;
//
//    public void setOnSpinnerClickListener(OnSpinnerClickListener onSpinnerClickListener) {
//        mSpinnerClickListener = onSpinnerClickListener;
//    }
//
//    public interface OnSpinnerClickListener {
//        void onIsClicked(boolean isClicked);
//    }
//}