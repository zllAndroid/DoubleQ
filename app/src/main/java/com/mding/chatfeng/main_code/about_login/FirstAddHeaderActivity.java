package com.mding.chatfeng.main_code.about_login;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ClipImgActivity;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.mding.model.DataSetHeader;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.about_file.HeadFileUtils;
import com.mding.chatfeng.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.mding.chatfeng.about_utils.about_file.HeadFileUtils.getRealFilePathFromUri;
import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;

/**
 * 项目：DoubleQ
 * 文件描述：第一次登陆成功后进入的设置昵称和头像界面
 * 作者：zll
 * 修改人：ljj
 */
public class FirstAddHeaderActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @BindView(R.id.inclu_tv_right)
    TextView mTvTopRight;
    @BindView(R.id.first_iv_head)
    ImageView firstIvHead;
    @BindView(R.id.first_ed_name)
    EditText firstEdName;
    @BindView(R.id.first_btn_sure)
    Button firstBtnSure;
    @BindView(R.id.first_lin_main)
    LinearLayout mLinMain;
    @BindView(R.id.first_re_head)
    RelativeLayout mReHead;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    ACache aCache;
    //    String imageBase64Acache;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        mTvTopRight.setVisibility(View.VISIBLE);
        mTvTopRight.setText("跳过");
//        mTvTopRight.setTextColor(getResources().getColor(R.color.gray666));
        mTvTopRight.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopTvTital.setText("用户设置");
    }

    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_first_add;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

    private PhotoPopWindow photoPopWindow = null;
    @OnClick({R.id.inclu_tv_right, R.id.first_re_head, R.id.first_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inclu_tv_right:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadLinkManActivity.class);
//                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadDataActivity.class);
//                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,MainActivity.class);
                }
                break;
            case R.id.first_re_head:
                if (photoPopWindow == null)
                    photoPopWindow = new PhotoPopWindow(FirstAddHeaderActivity.this, MyClick);
                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.first_btn_sure:
                String name = firstEdName.getText().toString().trim();
//                if (imageBase64==null)
//                {
//                    ToastUtil.show("请选择头像设置");
//                    return;
//                }
//
                if (StrUtils.isEmpty(name))
                {
                    ToastUtil.show("请输入您的昵称");
                    return;
                }


//                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//
//
//                netWorkUtlis.setOnNetWorkNormal(SplitWeb.getSplitWeb().setHeadImg(name, imageBase64), new NetWorkUtlis.OnNetWork() {
//                    @Override
//                    public void onNetSuccess(String result) {
//                        try {
//                            initImgData(result);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });

//                TreeMap<String, String> map = new TreeMap<>();
//                map.put()
//                String img=imageBase64+"_"+imageBase64;
                TreeMap<String, String> stringStringTreeMap = SplitWeb.getSplitWeb().setHeadImg(name, imageBase64 );
                Log.e("stringStringTreeMap","---------------------->>stringStringTreeMap="+stringStringTreeMap);
                VolleyRequest.RequestPost(FirstAddHeaderActivity.this,SplitWeb.getSplitWeb().getURLRequest()+"setHeadImg?","",stringStringTreeMap,new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
                    @Override
                    public void onSuccess(final String result) {
                        try {
                            initImgData(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(VolleyError result) {
                        try {
                            ToastUtil.show(AppConfig.ERROR);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
//                sendWebOnlyDialog(SplitWeb.getSplitWeb().setHeadImg(name, imageBase64),"请稍等...");
                break;
        }
    }

//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        initImgData(responseText);
//    }

    private void initImgData(String responseText) {
        Log.e("result","--------------------post--->"+responseText);
        String method = HelpUtils.backMethod(responseText);
        if (method.equals("setHeadImg"))
        {
            DataSetHeader dataSetHeader = JSON.parseObject(responseText, DataSetHeader.class);
            DataSetHeader.RecordBean record = dataSetHeader.getRecord();
            SplitWeb.getSplitWeb().USER_HEADER= record.getHeadImg();
            SplitWeb.getSplitWeb().NICK_NAME= record.getNickName();
//            把图片保存至本地文件
            if (record!=null) {
                String headImg = record.getHeadImg();
                String nickName = record.getNickName();
                if (!StrUtils.isEmpty(nickName))
                    SPUtils.put(this, AppConfig.TYPE_NAME, nickName);

                if (!StrUtils.isEmpty(headImg)) {
                    aCache.put(IMAGE_BASE64, headImg);
                    SPUtils.put(this,AppConfig.User_HEAD_URL,headImg);
//                    ImageUtils.useBase64(FirstAddHeaderActivity.this, firstIvHead, headImg.substring(0, headImg.indexOf("_")));
                    ImageUtils.useBase64(FirstAddHeaderActivity.this, firstIvHead, headImg);
//                    Glide.with(this)
//                            .load(headImg)
//                            .downloadOnly(new SimpleTarget<File>() {
//                                @Override
//                                public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
////                                    这里拿到的resource就是下载好的文件，
//                                    File file = HeadFileUtils.saveHeadPath(FirstAddHeaderActivity.this, resource);
//                                }
//                            });
                }
            }
            IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadLinkManActivity.class);
//            IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadDataActivity.class);
//                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,MainActivity.class);
//                AppManager.getAppManager().finishActivity();
            overridePendingTransition(0,0);
//            DialogUtils.showDialogOne("头像昵称设置成功", new DialogUtils.OnClickSureListener() {
//                @Override
//                public void onClickSure() {
//                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadDataActivity.class);
////                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,MainActivity.class);
////                AppManager.getAppManager().finishActivity();
//                    overridePendingTransition(0,0);
//                }
//            });
        }
    }

    private Bitmap photo;
    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }
    //为弹出窗口实现监听类
    public View.OnClickListener MyClick = new View.OnClickListener() {
        public void onClick(View v) {
            photoPopWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_open_cramre:
                    destoryImage();
                    getPicturesFile();
                    break;
                case R.id.btn_open_xaingce:
                    //	相册
//                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                    //权限判断
                    if (ContextCompat.checkSelfPermission(FirstAddHeaderActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(FirstAddHeaderActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
        Log.d("==image==", "*****************打开图库********************");
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

    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;
    //请求相册
    private static final int REQUEST_PICK = 401;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 402;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 303;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 404;

    private File mPhotoFile;
    File save;
    /**
     * 调用相机以及相册的回调 获取的数据
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        //		相机
        if (requestCode == CAMERA_RESULT && null != data) {
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
                File saveBitmap = ImageUtils.saveBitmap(FirstAddHeaderActivity.this, bitmap);
                final Map<String, File> files = new HashMap<String, File>();
                files.put("file", saveBitmap);
//                UpLoadIdCard(requestCode,files,CAMERA_RESULT_Btn1);
                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
//                pdIvHead.setBackgroundResource(0);
//                changeinfoIvHead
                Glide.with(this).load(saveBitmap)
                        .bitmapTransform(new CropCircleTransformation(FirstAddHeaderActivity.this))
                        .into(firstIvHead);
//                Glide.with(ChangeInfoActivity.this).load(drawable.getBitmap()).;
//                changeinfoIvHead.setImageBitmap(drawable.getBitmap());
//                SendDataImg(files);
//                imageBase64=ImageUtils.GetStringByImageView(bitmap);
                goToClipActivity(Uri.fromFile(mPhotoFile));
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
//            File saveBitmap = null;
//                saveBitmap = ImageUtil.saveFile(bitmap);
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
//            save = ImageUtils.saveBitmap(FirstAddHeaderActivity.this, bitmap);
//            final Map<String, File> files = new HashMap<String, File>();
//            files.put("file", save);
//            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//            Glide.with(this).load(save)
//                    .bitmapTransform(new CropCircleTransformation(FirstAddHeaderActivity.this))
//                   .into(firstIvHead);
//            //                Glide.with(this).load(saveBitmap)
////                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                        .into(changeinfoIvHead);
////
////            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
////            SendDataImg(files);
////            mTvChange.setText("");
////            changeinfoIvHead.setImageBitmap(bitmap);
            c.close();
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(save));
//            imageBase64=ImageUtils.GetStringByImageView(bitmap);
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(ImageUtil.GetStringByImageView(bitmap)));
            goToClipActivity(selectedImage);
        }
        if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String s1 = ImageUtils.Bitmap2StrByBase64(bm);

            // 高清头像
            String OriginBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap,400));
            //TODO 压缩头像
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(bm);
             imageBase64 = OriginBase64 + "_" + compressBase64;

//            imageBase64 = s1;
            ImageUtils.useBase64(FirstAddHeaderActivity.this, firstIvHead, s1);
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(s1));

        }
    }
    String imageBase64=null;
    String mTmpPath;
    /**
     * 7.0 拍照权限
     * 我是直接提取成一个方法了
     */
    public void getPicturesFile(){
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
                ActivityCompat.requestPermissions(FirstAddHeaderActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
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
}
