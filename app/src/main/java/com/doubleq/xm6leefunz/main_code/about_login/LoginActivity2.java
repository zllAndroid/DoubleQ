//package com.doubleq.xm6leefunz.main_code.about_login;
//
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.doubleq.model.DataLogin;
//import com.doubleq.xm6leefunz.R;
//import com.doubleq.xm6leefunz.about_base.BaseActivity;
//import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
//import com.doubleq.xm6leefunz.about_utils.EditCheckUtils;
//import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
//import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
//import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
//import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
//import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
//import com.projects.zll.utilslibrarybyzll.aboututils.IntentUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
//import com.zll.websocket.ErrorResponse;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
//public class LoginActivity2 extends BaseActivity {
//    @BindView(R.id.include_top_iv_back)
//    ImageView includeTopIvBack;
//    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
//    @BindView(R.id.login_ed_phone)
//    EditText loginEdPhone;
//    @BindView(R.id.login_ed_psw)
//    EditText loginEdPsw;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
////
////    //绑定成功后的操作
////    @Override
////    public void onServiceBindSuccess() {
////        super.onServiceBindSuccess();
////        Log.e("bindUid", "----------onServiceBindSuccess--------------------------------------");
//////        String string = "{\"ctn\":\"IM\", \"mtn\":\"bindUid\",\"data\":[{\"user_id\":\"1\",\"msg\":\"" + 22 + "\"}]}";
//////        TreeMap<String, String> map = new TreeMap<>();
//////        map.put("user_id","10001");
//////        map.put("user_name","zll");
//////        String request = WebUrl.request("Ws", "personalCenter", map);
//////        sendText(request);
////    }
//    public static int screenWidth;
//    public static int screenHeight;
//
//    private ACache mCache;
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
////        includeTopIvBack.setVisibility(View.INVISIBLE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        screenWidth = displayMetrics.widthPixels;
//        screenHeight = displayMetrics.heightPixels;
//        includeTopTvTital.setText("登录");
//        initCaChe();
//    }
//    private void initCaChe() {
//        mCache = ACache.get(this);
//        if (mCache!=null){
//            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
//            if (!StrUtils.isEmpty(asString))
//            {
//                Log.e("result","token信息"+asString.toString());
//                DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
//                if (dataLogin!=null)
//                    initSetData(dataLogin);
////               自动登录
//                IntentUtils.JumpFinishTo(MainActivity.class);
////                sendText(SplitWeb.bindUid());
//            }
//        }
//    }
//
//    private void initSetData(DataLogin.RecordBean dataLogin) {
//        SplitWeb.USER_TOKEN = dataLogin.getUser_token();
//        SplitWeb.MOBILE = dataLogin.getMobile();
//        SplitWeb.QR_CODE = dataLogin.getQrcode();
//        SplitWeb.NICK_NAME = dataLogin.getNick_name();
//        SplitWeb.PERSON_SIGN = dataLogin.getPersona_signature();
//        SplitWeb.QR_CODE = dataLogin.getQrcode();
//        SplitWeb.WX_SNO = dataLogin.getWx_sno();
//        SplitWeb.USER_ID = dataLogin.getUser_id();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        String phone =(String ) SPUtils.get(LoginActivity2.this, AppAllKey.SP_LOGIN_ACCOUNT, "");
//        if (!phone.isEmpty()) {
//            loginEdPhone.setText(phone);
//            loginEdPhone.setSelection(phone.length());//将光标移至文字末尾
//        }
//    }
//
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_login;
//    }
//
//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }
//
//    @Override
//    protected boolean isGones() {
//        return true;
//    }
//    @Override
//    protected boolean isSupportSwipeBack() {
//        return false;
//    }
//
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        Log.e("message", "receiveResultMsg" +responseText);
//            IntentUtils.JumpFinishTo(MainActivity.class);
//    }
//
//
//    @OnClick({R.id.login_tv_code_login, R.id.login_tv_forget_pwd, R.id.login_btn_login, R.id.login_tv_new_resgister})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.login_tv_code_login:
//                IntentUtils.JumpFinishTo(PwdLoginActivity.class);
//                break;
//            case R.id.login_tv_forget_pwd:
//                DialogUtils.showDialogKnow("请直接输入手机号，用短信登录","知道了");
//                break;
//            case R.id.login_btn_login:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    clickLogin();
//                break;
//            case R.id.login_tv_new_resgister:
//                IntentUtils.JumpTo(RegisterActivity.class);
//                break;
//        }
//    }
//
//    private void clickLogin() {
//        final   String phone = loginEdPhone.getText().toString().trim();
//        String pwd = loginEdPsw.getText().toString().trim();
//
//        if (StrUtils.isEmpty(phone)) {
//            DialogUtils.showDialog(getResources().getString(R.string.phone_is_null));
//            return;
//        }
//        if (!EditCheckUtils.isMobileNO(phone)) {
//            DialogUtils.showDialog(getResources().getString(R.string.phone_is_error));
////            Tip.getDialog(LoginActivity.this,getResources().getString(R.string.phone_is_error));
//            return;
//        }
//        if (StrUtils.isEmpty(pwd)) {
//            DialogUtils.showDialog("密码不得为空");
////            Tip.getDialog(LoginActivity.this,"密码不得为空");
//            return;
//        }
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower,SplitWeb.loginIn(phone, pwd), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                SPUtils.put(LoginActivity2.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
//                DataLogin dataLogin = JSON.parseObject(msg, DataLogin.class);
//                DataLogin.RecordBean record = dataLogin.getRecord();
//                if (record != null)
//                    SaveLoginResultData(record);
////                IntentUtils.JumpFinishTo(MainActivity.class);
//            }
//        });
//    }
//    private void SaveLoginResultData(DataLogin.RecordBean userInfo) {
//        String json = JSON.toJSON(userInfo).toString();
//        mCache.clear();
//        mCache.put(AppAllKey.TOKEN_KEY, json);
//        if (userInfo!=null)
//            initSetData(userInfo);
//        sendWeb(SplitWeb.bindUid());
//    }
//}
