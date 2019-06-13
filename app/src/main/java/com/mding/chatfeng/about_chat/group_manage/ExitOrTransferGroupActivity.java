package com.mding.chatfeng.about_chat.group_manage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExitOrTransferGroupActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_exit_or_transfer_group;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        includeTopTvTital.setText(getResources().getString(R.string.exit_group_title));
    }

    @OnClick({R.id.exit_group_tv_transfer, R.id.exit_group_tv_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit_group_tv_transfer:
                //跳转到群成员列表界面
//                IntentUtils.JumpTo();
                DialogUtils.showDialog("跳转到群成员列表");
                break;
            case R.id.exit_group_tv_dismiss:
                DialogUtils.showDialog(getResources().getString(R.string.exit_group_dialog_is_sure_dismiss), new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        DialogUtils.showDialog(getResources().getString(R.string.exit_group_dialog_dismiss_succeed));
                    }
                });
                break;
        }
    }
}
