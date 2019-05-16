package com.mding.chatfeng.main_code.ui.about_personal.about_activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.main_code.about_login.AboutLoginSaveData;
import com.mding.chatfeng.main_code.about_login.FirstAddHeaderActivity;
import com.mding.chatfeng.main_code.mains.PersonalFragment;
import com.mding.chatfeng.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.mding.model.DataLogin;
import com.mding.model.DataMyZiliao;
import com.mding.model.DataSetHeadResult;
import com.mding.model.HeadImgInfo;
import com.mding.model.PersonInfo;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.mding.chatfeng.about_utils.about_file.HeadFileUtils.getRealFilePathFromUri;
import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

//我的资料页面   在个人中心点击进入此
public class ChangeInfoActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;

    @BindView(R.id.changeinfo_iv_head)
    ImageView changeinfoIvHead;

    //    帐号（唯一码）
    @BindView(R.id.changeinfo_iv_count)
    ImageView changeinfoIvWrite;

    @BindView(R.id.changeinfo_tv_name)
    TextView changeinfoTvName;

    @BindView(R.id.changeinfo_tv_count)
    TextView changeinfoTvCount;

    @BindView(R.id.changeinfo_tv_sign)
    TextView changeinfoTvSign;

    @BindView(R.id.changeinfo_top_lin)
    LinearLayout mLinMain;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    Unbinder bind = null;
    private String imageBase64;
    public static String NICK_NAME = "nickName";
    ACache aCache;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_changeinfo;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopTvTital.setText("我的资料");
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
        if (aCache==null){
            aCache =  ACache.get(this);
        }
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json)){
            try {
                initUI(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
    }
    String signatureText;
    private void initUI(String json) {
        DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
        if (recordBean!=null) {

            ImageUtils.useBase64(ChangeInfoActivity.this,changeinfoIvHead,recordBean.getHeadImg());

            changeinfoTvName.setText(recordBean.getNickName());
            changeinfoTvCount.setText(recordBean.getWxSno());

            signatureText = StrUtils.isEmpty(recordBean.getPersonaSignature()) ? "暂未设置" : recordBean.getPersonaSignature();
            changeinfoTvSign.setText(signatureText);
            String up_sno_num = recordBean.getUpSnoNum();
            if (up_sno_num.equals("1")) {
                changeinfoIvWrite.setVisibility(View.VISIBLE);
            } else {
                changeinfoIvWrite.setVisibility(View.GONE);
            }
        }
    }
    //0 修改昵称   1 修改帐号 2 修改个签
    String isChangeName = "0";
    private PhotoPopWindow photoPopWindow = null;

    @OnClick({R.id.changeinfo_re_head, R.id.changeinfo_tv_name, R.id.changeinfo_lin_sign, R.id.changeinfo_lin_count})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changeinfo_re_head:
                if (photoPopWindow == null)
                    photoPopWindow = new PhotoPopWindow(ChangeInfoActivity.this, MyClick);
                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.changeinfo_tv_name:
                if (NoDoubleClickUtils.isDoubleClick())
                    doChangeName();
                break;
            case R.id.changeinfo_lin_sign:
                if (NoDoubleClickUtils.isDoubleClick())
                    doChangeSign();
                break;
            case R.id.changeinfo_lin_count:
                int visibility = changeinfoIvWrite.getVisibility();
                if (visibility!=View.GONE)
                {
                    if (NoDoubleClickUtils.isDoubleClick())
                        doChangeCount();
                }
                break;
        }
    }
    //修改个签
    private void doChangeSign() {
        isChangeName = "2";
        ChangeInfoWindow changeInfoWindowSign;
        if (signatureText.equals("暂未设置")){
            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, "修改个性签名", "");
        }
        else {
            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, "修改个性签名", changeinfoTvSign.getText().toString().trim());
        }
        changeInfoWindowSign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowSign.setOnAddpopClickListener(this);
    }

    //修改帐号
    private void doChangeCount() {
        isChangeName = "1";
        ChangeInfoWindow changeInfoWindowCount = new ChangeInfoWindow(ChangeInfoActivity.this, "修改帐号", changeinfoTvCount.getText().toString().trim());
        changeInfoWindowCount.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowCount.setOnAddpopClickListener(this);
    }

    //修改昵称
    private void doChangeName() {
        isChangeName = "0";
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(ChangeInfoActivity.this, "修改昵称", changeinfoTvName.getText().toString().trim());
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }
    //为弹出窗口实现监听类
    public View.OnClickListener MyClick = new View.OnClickListener() {
        public void onClick(View v) {
            photoPopWindow.dismiss();
            switch (v.getId()) {
                //   相机
                case R.id.btn_open_cramre:
                    destoryImage();
                    getPicturesFile();
                    break;
                case R.id.btn_open_xaingce:
                    //	相册
                    //权限判断
                    if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        goToAlbum();
                    }
                    photoPopWindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }
    /**
     * 打开截图界面
     */
    public void goToClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImgActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToAlbum();
            }
        }
    }

    DataMyZiliao.RecordBean record;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "personalCenter":
                DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
                record = dataMyZiliao.getRecord();
                if (record != null) {
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                    String up_sno_num = record.getUpSnoNum();
                    if (up_sno_num.equals("1")) {
                        changeinfoIvWrite.setVisibility(View.VISIBLE);
                    } else {
                        changeinfoIvWrite.setVisibility(View.GONE);
                    }
                    changeinfoTvName.setText(record.getNickName());
                    changeinfoTvCount.setText(record.getWxSno());
                    if (StrUtils.isEmpty(record.getPersonaSignature())) {
                        changeinfoTvSign.setHint("暂未设置");
                    } else {
                        changeinfoTvSign.setText(record.getPersonaSignature());
                    }
                }
//                }
                break;
            case "upNickName"://修改昵称成功
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NAME, contant);
                SplitWeb.getSplitWeb().NICK_NAME = contant;
                String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json)) {
                    DataMyZiliao.RecordBean record = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
                    record.setNickName(contant);
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                changeinfoTvName.setText(contant);
                PersonInfo personInfo = new PersonInfo();
                personInfo.setNickName(contant);
                EventBus.getDefault().post(personInfo);
                break;
            case "upPersonSign":
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_SIGN, contant);
                SplitWeb.getSplitWeb().PERSON_SIGN = contant;
                String json2 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json2)) {
                    DataMyZiliao.RecordBean record = JSON.parseObject(json2, DataMyZiliao.RecordBean.class);
                    record.setPersonaSignature(contant);
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                if (StrUtils.isEmpty(contant)) {
                    changeinfoTvSign.setText("暂未设置");
                }
                else {
                    changeinfoTvSign.setText(contant);
                }

                PersonInfo personInfo2 = new PersonInfo();
                personInfo2.setSign(contant);
                EventBus.getDefault().post(personInfo2);
                break;
            case "upUserSno":
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NO, contant);
                SplitWeb.getSplitWeb().WX_SNO = contant;
                changeinfoIvWrite.setVisibility(View.GONE);
                String json3 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json3)) {
                    DataMyZiliao.RecordBean record = JSON.parseObject(json3, DataMyZiliao.RecordBean.class);
                    record.setWxSno(contant);
                    record.setUpSnoNum("0");
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                changeinfoTvCount.setText(contant);
                break;
            case "upHeadImg":
                PersonalFragment.isChangeHead = true;
                DataSetHeadResult dataSetHeadResult = JSON.parseObject(responseText, DataSetHeadResult.class);
                if (dataSetHeadResult != null) {
                    String headImg = dataSetHeadResult.getRecord().getHeadImg();
//                    String substring = headImg.substring(22);
                    if (!StrUtils.isEmpty(headImg)){
                        String s = headImg.contains("_")?"yes" : "no";
                        MyLog.i("imageBase64","------------changeInfoActivity--------------"+s);
                        SplitWeb.getSplitWeb().USER_HEADER = headImg;
                        aCache.put(IMAGE_BASE64, headImg);
                        aCache.put(AppAllKey.User_HEAD_URL, headImg);
                        HeadImgInfo headImgInfo = new HeadImgInfo();
                        headImgInfo.setHeadImgBase64(headImg);
                        EventBus.getDefault().post(headImgInfo);
//                        EventBus.getDefault().postSticky(headImgInfo);


                        String json4 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                        if (!StrUtils.isEmpty(json4)) {
                            DataMyZiliao.RecordBean record = JSON.parseObject(json4, DataMyZiliao.RecordBean.class);
                            record.setHeadImg(headImg);
                            String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                            aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                        }else {
                            String sNo = (String) SPUtils.get(ChangeInfoActivity.this, AppConfig.TYPE_NO, "");
                            String qrCode = aCache.getAsString(AboutLoginSaveData.QR_CODE);
                            String nickName = aCache.getAsString(AppAllKey.User_NI_NAME);
                            DataMyZiliao.RecordBean recordBean = new DataMyZiliao.RecordBean();
                            recordBean.setHeadImg(headImg);
                            recordBean.setNickName(nickName);
                            recordBean.setWxSno(sNo);
                            recordBean.setQrcode(qrCode);
                            String mData = MyJsonUtils.toChangeJson(recordBean);
                            aCache.put(AppAllKey.PPERSON_iNFO,mData);
                        }
                    }
                }
                break;
        }
    }

    private Bitmap photo;

    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;

    private File mPhotoFile;
//    File save;
boolean isBackKey = false;
    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackKey = true;
        System.out.println("按下了back键   onBackPressed()");
    }

    /**
     * 调用相机以及相册的回调 获取的数据
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        //		相机
        if (requestCode == CAMERA_RESULT) {
//        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                bitmapOptions.inJustDecodeBounds = false;
                int be = (int) (bitmapOptions.outHeight / (float) 200);
                if (be <= 0)
                    be = 1;
                bitmapOptions.inSampleSize = be;

                bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                if(resultCode == Activity.RESULT_CANCELED) {
                    return;
                }
                if (isBackKey){
                    isBackKey = false;
                    return;
                }
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                goToClipActivity(Uri.fromFile(mPhotoFile));
//                Bitmap bm = ImageUtils.getBitmapCompress(mPhotoFile.getPath());
//                String s1 = ImageUtils.Bitmap2StrByBase64(bm);
////                save = ImageUtils.saveBitmap(ChangeInfoActivity.this, bm);
////                sendWeb(SplitWeb.getSplitWeb().upHeadImg(ImageUtils.GetStringByImageView(bm)));
//                sendWeb(SplitWeb.getSplitWeb().upHeadImg(s1));
            }
        }
        //		相册
        if (requestCode == REQUEST_PICK && null != data) {
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (isBackKey){
                isBackKey = false;
                return;
            }
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
//            save = ImageUtils.saveBitmap(ChangeInfoActivity.this, bitmap);
//            final Map<String, File> files = new HashMap<String, File>();
//            files.put("file", save);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            c.close();
            goToClipActivity(selectedImage);
        }
        if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
            // 高清头像
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            String OriginBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap,400));
            //TODO 压缩头像
//            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap));
            String totalBase64 = OriginBase64 + "_" + compressBase64;
            ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, compressBase64);
            sendWeb(SplitWeb.getSplitWeb().upHeadImg(totalBase64));

        }
    }

    String mTmpPath;

    /**
     * 7.0 拍照权限
     * 我是直接提取成一个方法了
     */
    public void getPicturesFile() {
        mPhotoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chat_image/" + System.currentTimeMillis() + ".jpg");
        try {
            mPhotoFile.getParentFile().mkdirs();
            mPhotoFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTmpPath = mPhotoFile.getAbsolutePath();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        //判断一下当前的系统版本，然后在分配权限
        if (Build.VERSION.SDK_INT >= 24) {
            //Android 7.0权限申请
            ContentValues contentValues = new ContentValues(1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
            }
            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_RESULT);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
            startActivityForResult(intent, CAMERA_RESULT);
        }
    }

    String contant = null;

    //修改签名等的回调按钮
    @Override
    public void onSure(String contant) {
        this.contant = contant;
        switch (isChangeName) {
            case "0"://昵称
                sendWeb(SplitWeb.getSplitWeb().upNickName(contant));
                break;
            case "1"://帐号
                sendWeb(SplitWeb.getSplitWeb().upUserSno(contant));
                break;
            case "2"://签名
                sendWeb(SplitWeb.getSplitWeb().upPersonSign(contant));
                break;
        }
    }

    @Override()
    public void onCancle() {

    }

    @OnClick(R.id.changeinfo_iv_qrcode)
    public void onViewClicked() {
        Log.e("qrCode","----------------------------------userId = "+SplitWeb.getSplitWeb().getUserId());
        IntentUtils.JumpToHaveOne(MyAccountActivity.class,"userId",SplitWeb.getSplitWeb().getUserId());
    }
}
