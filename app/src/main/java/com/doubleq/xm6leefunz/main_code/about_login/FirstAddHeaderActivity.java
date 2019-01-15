package com.doubleq.xm6leefunz.main_code.about_login;

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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doubleq.model.DataSetHeader;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.ImageUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.main_code.mains.LoadDataActivity;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class FirstAddHeaderActivity extends BaseActivity {
    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @BindView(R.id.include_top_tv_right)
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initBaseView() {
        super.initBaseView();
        mTvTopRight.setVisibility(View.VISIBLE);
        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopTvTital.setText("注册");
        mTvTopRight.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
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
    @OnClick({R.id.include_top_tv_right, R.id.first_re_head, R.id.first_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_tv_right:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadDataActivity.class);
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
                if (imageBase64==null)
                {
                    ToastUtil.show("请选择头像设置");
                    return;
                }
                if (StrUtils.isEmpty(name))
                {
                    ToastUtil.show("请输入您的昵称");
                    return;
                }
                sendWebHaveDialog(SplitWeb.setHeadImg(name, imageBase64),"请稍等...","设置成功");
                break;
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        if (method.equals("setHeadImg"))
        {
            DataSetHeader dataSetHeader = JSON.parseObject(responseText, DataSetHeader.class);
            DataSetHeader.RecordBean record = dataSetHeader.getRecord();
           SplitWeb.USER_HEADER= record.getHeadImg();
           SplitWeb.NICK_NAME= record.getNickName();
            SPUtils.put(this,"header",record.getHeadImg());
            SPUtils.put(this, AppConfig.TYPE_NAME,record.getNickName());
//            把图片保存至本地文件
            if (record!=null) {
                String headImg = record.getHeadImg();
                if (!StrUtils.isEmpty(headImg))
                    Glide.with(this)
                            .load(headImg)
                            .downloadOnly(new SimpleTarget<File>() {
                                @Override
                                public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                    File file = HeadFileUtils.saveHeadPath(FirstAddHeaderActivity.this, resource);
                                }
                            })
                            ;
            }
            DialogUtils.showDialogOne("头像昵称设置成功", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,LoadDataActivity.class);
//                    IntentUtils.JumpFinishTo(FirstAddHeaderActivity.this,MainActivity.class);
//                AppManager.getAppManager().finishActivity();
                    overridePendingTransition(0,0);
                }
            });
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
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    break;
                default:
                    break;
            }
        }
    };
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;

    private File mPhotoFile;
    File save;
    /**
     * 调用相机以及相册的回调 获取的数据
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        //		相机
        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
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
                imageBase64=ImageUtils.GetStringByImageView(bitmap);
            }
        }
        //		相册
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            File saveBitmap = null;
//                saveBitmap = ImageUtils.saveFile(bitmap);
            save = ImageUtils.saveBitmap(FirstAddHeaderActivity.this, bitmap);
            final Map<String, File> files = new HashMap<String, File>();
            files.put("file", save);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            Glide.with(this).load(save)
                    .bitmapTransform(new CropCircleTransformation(FirstAddHeaderActivity.this))
                   .into(firstIvHead);
            //                Glide.with(this).load(saveBitmap)
//                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
//                        .into(changeinfoIvHead);
//
//            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
//            SendDataImg(files);
//            mTvChange.setText("");
//            changeinfoIvHead.setImageBitmap(bitmap);
            c.close();
//            sendWeb(SplitWeb.upHeadImg(save));
            imageBase64=ImageUtils.GetStringByImageView(bitmap);
//            sendWeb(SplitWeb.upHeadImg(ImageUtils.GetStringByImageView(bitmap)));
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
