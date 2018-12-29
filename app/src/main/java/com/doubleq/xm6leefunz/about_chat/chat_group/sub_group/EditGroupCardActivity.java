package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EditGroupCardActivity extends BaseActivity {

    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.edit_groupCard_lin_seach)
    LinearLayout editGroupCardLinSearch;
    @BindView(R.id.edit_groupCard_et)
    EditText editGroupCardEt;
    @BindView(R.id.edit_groupCard_iv_clear)
    ImageView editGroupCardIvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_edit_group_card;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("编辑群名片");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setText("完成");

        editGroupCardEt.addTextChangedListener(textWatcher);
        editGroupCardEt.setSelection(editGroupCardEt.getText().toString().length());
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(final Editable editable) {
            if (!editGroupCardEt.getText().toString().equals("")) {
                editGroupCardIvClear.setVisibility(View.VISIBLE);
            } else
                editGroupCardIvClear.setVisibility(View.INVISIBLE);
            editGroupCardIvClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editGroupCardEt.setText("");
                }
            });
        }
    };

    @OnClick(R.id.inclu_tv_right)
    public void onViewClicked() {


    }
}
