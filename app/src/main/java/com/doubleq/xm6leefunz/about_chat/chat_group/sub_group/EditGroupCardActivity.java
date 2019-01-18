package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑群名片页面
 */
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

    String groupofId;
    String userId;
    String cardName;

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
        includeTopTvRight.setText("完成");

        editGroupCardEt.setFocusable(true);
        editGroupCardEt.setFocusableInTouchMode(true);
        editGroupCardEt.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Intent intent = getIntent();
        if (intent!=null){
            groupofId = intent.getStringExtra("groupofId");
            cardName = intent.getStringExtra("cardName");
            Log.e("cardName","-------------------------"+cardName);
            if (cardName != null)
                editGroupCardEt.setText(cardName);
            editGroupCardEt.addTextChangedListener(textWatcher);
            editGroupCardEt.setSelection(editGroupCardEt.getText().toString().length());
        }

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
            Log.e("editGroupCardEt","-------------------"+cardName);
            Log.e("editable","-------------------"+editable.toString());
            // 判断当下输入的内容是否与之前的内容相同并且不为空，相同或者为空则不出现保存按钮，否则显示
            if (!cardName.equals(editable.toString()) && !editable.toString().equals(""))
                includeTopTvRight.setVisibility(View.VISIBLE);
            else
                includeTopTvRight.setVisibility(View.INVISIBLE);

            // 如果当下输入的内容不为空则显示清除按钮
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
        if (groupofId!=null){
                sendWeb(SplitWeb.setGroupCarteModify(groupofId, editGroupCardEt.getText().toString()));
        }
        else
            ToastUtil.show("系统故障");
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "setGroupCarteModify":
                // 将当下输入框中的内容传给群聊资料（GroupChatDetailsActivity）界面
                Intent intent = new Intent();
                intent.putExtra("myGroupCard",editGroupCardEt.getText().toString());
                setResult(AppConfig.EDIT_GROUP_CARD_RESULT,intent);
                AppManager.getAppManager().finishActivity();
                break;
        }
    }
}
