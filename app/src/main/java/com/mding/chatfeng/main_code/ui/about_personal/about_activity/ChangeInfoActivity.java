package com.mding.chatfeng.main_code.ui.about_personal.about_activity;

import android.Manifest;
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
import com.mding.chatfeng.about_utils.JsonUtils;
import com.mding.chatfeng.main_code.mains.PersonalFragment;
import com.mding.chatfeng.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.mding.model.DataLogin;
import com.mding.model.DataMyZiliao;
import com.mding.model.DataSetHeadResult;
import com.mding.model.HeadImgInfo;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
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

//    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

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
//        setHeadForFile();
        if (aCache==null){
            aCache =  ACache.get(this);
        }
        imageBase64 = aCache.getAsString(PersonalFragment.IMAGE_BASE64);
        if (imageBase64!=null){
            ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, imageBase64);
        }
        String json = aCache.getAsString(AppAllKey.TOKEN_KEY);
        if (!StrUtils.isEmpty(json)){
            initUI(json);
        }else {
            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
    }

    private void initUI(String json) {
        DataLogin dataLogin = JSON.parseObject(json, DataLogin.class);
        DataLogin.RecordBean recordBean = dataLogin.getRecord();
        if (recordBean!=null) {
           changeinfoTvName.setText(recordBean.getNickName());
           changeinfoTvCount.setText(recordBean.getWxSno());
           changeinfoTvSign.setText(recordBean.getPersonaSignature());
        }
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
//    public void onDataSynEvent(final HeadImgInfo headImgInfo) {
//        imageBase64 = headImgInfo.getHeadImgBase64();
//        ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, headImgInfo.getHeadImgBase64());
//    }

    //    从文件中设置头像
//    private void setHeadForFile() {
////        GlideCacheUtil.getInstance().clearImageAllCache(ChangeInfoActivity.this);
////        List<String> fileName = FilePath.getFilesAllName(FilePath.getAbsPath() + "chatHead/");
//        String userId = SplitWeb.getSplitWeb().getUserId();
//        String mPath= FilePath.getAbsPath(FilePath.appPath+userId+"/")+"chatHead/";
//        List<String> fileName = FilePath.getFilesAllName(mPath);
//        Log.e("setHeadForFile","mPath="+mPath+"--------"+fileName.size());
//        if (fileName != null && fileName.size() > 0) {
//            String path = fileName.get(fileName.size() - 1);
//            Log.e("setHeadForFile","path="+path);
//            Glide.with(this).load(path)
//                    .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
//                    .thumbnail(0.1f)
//                    .into(changeinfoIvHead);
//        }
//        else {
//            Glide.with(this).load(R.drawable.first_head_nor)
//                    .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
//                    .thumbnail(0.1f)
//
//                    .into(changeinfoIvHead);
//        }
//    }

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
                if (record != null) {
                    String up_sno_num = record.getUpSnoNum();
                    if (up_sno_num.equals("1"))
                        if (NoDoubleClickUtils.isDoubleClick())
                            doChangeCount();
                }
                break;
        }
    }
    //修改个签
    private void doChangeSign() {
        isChangeName = "2";
        ChangeInfoWindow changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, "修改个性签名", changeinfoTvSign.getText().toString().trim());
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
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(ChangeInfoActivity.this, "修改名字", changeinfoTvName.getText().toString().trim());
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
//                    //权限判断
//                    if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        //申请WRITE_EXTERNAL_STORAGE权限
//                        ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//                    } else {
//                        //跳转到调用系统相机
//                        goToCamera();
//                    }
//                    photoPopWindow.dismiss();
                    break;
                case R.id.btn_open_xaingce:
                    //	相册
//                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, RESULT_LOAD_IMAGE);

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

//    /**
//     * 跳转到照相机
//     */
//    private void goToCamera() {
//        Log.d("==image==", "*****************打开相机********************");
//        //创建拍照存储的图片文件
//        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
//
//        //跳转到调用系统相机
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
//            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(ChangeInfoActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        } else {
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//        }
//        startActivityForResult(intent, REQUEST_CAPTURE);
//    }

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        Log.d("==image==", "*****************打开相册********************");
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
                // Permission Granted
//                goToCamera();
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
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
                    String up_sno_num = record.getUpSnoNum();
//                    int visibility = changeinfoIvWrite.getVisibility();
                    if (up_sno_num.equals("1")) {
                        changeinfoIvWrite.setVisibility(View.VISIBLE);
                    } else {
                        changeinfoIvWrite.setVisibility(View.GONE);
                    }
                    changeinfoTvName.setText(record.getNickName());
                    changeinfoTvCount.setText(record.getWxSno());
                    if (StrUtils.isEmpty(record.getPersonaSignature())) {
                        changeinfoTvSign.setHint("暂未签名");
                        changeinfoTvSign.setHintTextColor(getResources().getColor(R.color.white));
                    } else {
                        changeinfoTvSign.setText(record.getPersonaSignature());
                    }

                    String userId = SplitWeb.getSplitWeb().getUserId();
//                    String substring = headImg.substring(22);
//                    if (!StrUtils.isEmpty(headImg)){
//                        Glide.with(this)
//                                .load(headImg)
//                                .downloadOnly(new SimpleTarget<File>() {
//                                    @Override
//                                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
////                                    这里拿到的resource就是下载好的文件，
//                                        File file = HeadFileUtils.saveHeadPath(ChangeInfoActivity.this, resource);
////                                        Glide.with(ChangeInfoActivity.this).load(file)
////                                                .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                                                .thumbnail(0.1f)
////                                                .into(changeinfoIvHead);
//                                    }
//                                });
//                        ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, headImg);
//                    }
                }
//                }
                break;
            case "upNickName"://修改昵称成功
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NAME, contant);
                SplitWeb.getSplitWeb().NICK_NAME = contant;
//                SPUtils.put(HelpUtils.activity,AppConfig.TYPE_NO,dataLogin.getWxSno());
//                SPUtils.put(HelpUtils.activity,AppConfig.TYPE_SIGN,dataLogin.getPersonaSignature());
//                SplitWeb.getSplitWeb().PERSON_SIGN = dataLogin.getPersonaSignature();
//                SplitWeb.getSplitWeb().WX_SNO = dataLogin.getWxSno();
//                aCache.put(NICK_NAME,contant);
                String json = aCache.getAsString(AppAllKey.TOKEN_KEY);
                if (!StrUtils.isEmpty(json)) {
                    DataLogin.RecordBean recordBean = JSON.parseObject(json, DataLogin.RecordBean.class);
//                    if (dataLogin != null) {
                        recordBean.setNickName(contant);
//                        String jsonString = JSON.toJSONString(recordBean);
                    String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
                        aCache.put(AppAllKey.TOKEN_KEY, jsonString);
//                    }
                }

                changeinfoTvName.setText(contant);
                break;
            case "upPersonSign":
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_SIGN, contant);
                SplitWeb.getSplitWeb().PERSON_SIGN = contant;
                String json2 = aCache.getAsString(AppAllKey.TOKEN_KEY);
                if (!StrUtils.isEmpty(json2)) {
                    DataLogin.RecordBean recordBean = JSON.parseObject(json2, DataLogin.RecordBean.class);
//                    if (dataLogin != null) {
                    recordBean.setPersonaSignature(contant);
//                    String jsonString = JSON.toJSONString(recordBean);
                    String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
                    aCache.put(AppAllKey.TOKEN_KEY, jsonString);
//                    }
                }
                changeinfoTvSign.setText(contant);
                break;
            case "upUserSno":
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NO, contant);
                SplitWeb.getSplitWeb().WX_SNO = contant;
                changeinfoIvWrite.setVisibility(View.GONE);
                String json3 = aCache.getAsString(AppAllKey.TOKEN_KEY);
                if (!StrUtils.isEmpty(json3)) {
                    DataLogin.RecordBean recordBean = JSON.parseObject(json3, DataLogin.RecordBean.class);
//                    if (dataLogin != null) {
                    recordBean.setWxSno(contant);
//                    String jsonString = JSON.toJSONString(recordBean);
                    String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
                    aCache.put(AppAllKey.TOKEN_KEY, jsonString);
//                    }
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
                        SplitWeb.getSplitWeb().USER_HEADER = headImg;
//                        Glide.with(this)
//                                .load(headImg)
//                                .downloadOnly(new SimpleTarget<File>() {
//                                    @Override
//                                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
////                                    这里拿到的resource就是下载好的文件，
//                                        File file = HeadFileUtils.saveHeadPath(ChangeInfoActivity.this, resource);
//                                    }
//                                });
//                        ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, headImg);
                        HeadImgInfo headImgInfo = new HeadImgInfo();
                        headImgInfo.setHeadImgBase64(headImg);
                        EventBus.getDefault().postSticky(headImgInfo);
                    }
                }
////                Glide.with(this).load(save)
//                Glide.with(this).load(decodedByte)
//                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
//                        .thumbnail(0.1f)
//                        .into(changeinfoIvHead);
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
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            //TODO 压缩头像
            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String s1 = ImageUtils.Bitmap2StrByBase64(bm);
            ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, s1);
            sendWeb(SplitWeb.getSplitWeb().upHeadImg(s1));

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

    @Override
    public void onCancle() {

    }

    @OnClick(R.id.changeinfo_iv_qrcode)
    public void onViewClicked() {
        Log.e("qrCode","----------------------------------userId = "+SplitWeb.getSplitWeb().getUserId());
        IntentUtils.JumpToHaveOne(MyAccountActivity.class,"userId",SplitWeb.getSplitWeb().getUserId());
    }
}
