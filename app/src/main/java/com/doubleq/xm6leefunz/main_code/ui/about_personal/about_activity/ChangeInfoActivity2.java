//package com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity;
//
//import android.Manifest;
//import android.content.ContentValues;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.util.Base64;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.FutureTarget;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.Target;
//import com.doubleq.model.DataMyZiliao;
//import com.doubleq.xm6leefunz.R;
//import com.doubleq.xm6leefunz.about_base.BaseActivity;
//import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
//import com.doubleq.xm6leefunz.about_utils.HelpUtils;
//import com.doubleq.xm6leefunz.about_utils.ImageUtils;
//import com.doubleq.xm6leefunz.main_code.ui.about_personal.changephoto.PhotoPopWindow;
//import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;
//
//
//public class ChangeInfoActivity2 extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {
//
//    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
//    @BindView(R.id.include_top_iv_back)
//    ImageView includeTopIvBack;
//
//    @BindView(R.id.changeinfo_iv_head)
//    ImageView changeinfoIvHead;
//
////    帐号（唯一码）
//    @BindView(R.id.changeinfo_iv_count)
//    ImageView changeinfoIvWrite;
//
//    @BindView(R.id.changeinfo_tv_name)
//    TextView changeinfoTvName;
//
//    @BindView(R.id.changeinfo_tv_count)
//    TextView changeinfoTvCount;
//
//    @BindView(R.id.changeinfo_tv_sign)
//    TextView changeinfoTvSign;
//
//    @BindView(R.id.changeinfo_top_lin)
//    LinearLayout mLinMain;
//    Unbinder bind = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_changeinfo;
//    }
//
//    @Override
//    protected boolean isTopBack() {
//        return true;
//    }
//
//    @Override
//    protected boolean isGones() {
//        return true;
//    }
//
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        includeTopIvBack.setVisibility(View.VISIBLE);
//        includeTopTvTital.setText("我的资料");
//        sendWeb(SplitWeb.personalCenter());
//
//
//    }
////0 修改昵称   1 修改帐号 2 修改个签
//    String isChangeName = "0";
//    private PhotoPopWindow photoPopWindow = null;
//
//    @OnClick({R.id.changeinfo_re_head, R.id.changeinfo_tv_name, R.id.changeinfo_lin_sign, R.id.changeinfo_lin_count})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.changeinfo_re_head:
//                if (photoPopWindow == null)
//                    photoPopWindow = new PhotoPopWindow(ChangeInfoActivity2.this, MyClick);
//                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
//                break;
//            case R.id.changeinfo_tv_name:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    doChangeName();
//                break;
//            case R.id.changeinfo_lin_sign:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    doChangeSign();
//                break;
//            case R.id.changeinfo_lin_count:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    doChangeCount();
//                break;
//        }
//    }
////修改个签
//   private void doChangeSign() {
//        isChangeName = "2";
//        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(ChangeInfoActivity2.this, "修改个性签名", changeinfoTvSign.getText().toString().trim());
//        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
//        changeInfoWindowsign.setOnAddpopClickListener(this);
//    }
////修改帐号
//    private void doChangeCount() {
//        isChangeName = "1";
//        ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(ChangeInfoActivity2.this, "修改帐号", changeinfoTvCount.getText().toString().trim());
//        changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
//        changeInfoWindowsign.setOnAddpopClickListener(this);
//    }
////修改昵称
//   private void doChangeName() {
//
//        isChangeName = "0";
//        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(ChangeInfoActivity2.this, "修改名字", changeinfoTvName.getText().toString().trim());
//        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
//        changeInfoWindow.setOnAddpopClickListener(this);
//    }
//
//    //为弹出窗口实现监听类
//    public View.OnClickListener MyClick = new View.OnClickListener() {
//        public void onClick(View v) {
//            photoPopWindow.dismiss();
//            switch (v.getId()) {
//                case R.id.btn_open_cramre:
//                    destoryImage();
//                    getPicturesFile();
//                    break;
//                case R.id.btn_open_xaingce:
//                    //	相册
//                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, RESULT_LOAD_IMAGE);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//    Bitmap bitmapHead = null;
//    File fileHead;
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String method = HelpUtils.backMethod(responseText);
//        switch (method) {
//            case "personalCenter":
//                DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
//              final   DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
//                if (record != null) {
//                    String up_sno_num = record.getUpSnoNum();
////                    int visibility = changeinfoIvWrite.getVisibility();
//                    if (up_sno_num.equals("1")) {
//                        changeinfoIvWrite.setVisibility(View.VISIBLE);
//                    } else {
//                        changeinfoIvWrite.setVisibility(View.GONE);
//                    }
//                    changeinfoTvName.setText(record.getNickName());
//                    changeinfoTvCount.setText(record.getWxSno());
//                    changeinfoTvSign.setText(record.getPersonaSignature());
////                    if (bitmapHead !=null)
////                    {
////                        try {
////                            File file = ImageUtils.saveFile(bitmapHead);
////                            Glide.with(ChangeInfoActivity.this).load(file)
////                                    .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                                    .crossFade(1000).into(changeinfoIvHead);
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                    if (!StrUtils.isEmpty(absolutePath))
////                    {
//////                        absolutePath=record.getHeadImg();
////                        Glide.with(this).load(imagePath+SplitWeb.getUserId()+".jpg")
////                                .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                                .crossFade(1000).into(changeinfoIvHead);
////                    }
//
////                    Glide.with(this).load(record.getHeadImg())
////                            .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                            .crossFade(1000).into(changeinfoIvHead);
//
////                  saveImg(record.getHeadImg());
//
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
////                                bitmapHead = Glide.with(ChangeInfoActivity.this)
////                                        .load(record.getHeadImg())
////                                        .asBitmap()
////                                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
////                                        .get();
//                                 fileHead = ImageUtils.saveBitmap(ChangeInfoActivity2.this, Glide.with(ChangeInfoActivity2.this)
//                                         .load(record.getHeadImg())
//                                         .asBitmap()
//                                         .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                                         .get());
//
//                                Log.e("ImageUtils",bitmapHead+"+++"+fileHead.toString()+"-------------------------------------"+fileHead.getAbsolutePath());
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//
//
//
//                    Glide.with(this)
//                            .load(record.getHeadImg())
//                            .downloadOnly(new FutureTarget<File>() {
//                                @Override
//                                public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
////                                    这里拿到的resource就是下载好的文件，至于如何处理，或保存到SD，或上传，就不啰嗦了。。。
//                                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                                    bitmapOptions.inJustDecodeBounds = true;
//                                    Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
//                                    bitmapOptions.inJustDecodeBounds = false;
//                                    int be = (int) (bitmapOptions.outHeight / (float) 200);
//                                    if (be <= 0)
//                                        be = 1;
//                                    bitmapOptions.inSampleSize = be;
//                                    bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
//                                    save = ImageUtils.saveBitmap(ChangeInfoActivity2.this, bitmap);
//
//                                }
//                            });
//
//                }
//                break;
//            case "upNickName"://修改昵称成功
//                changeinfoTvName.setText(contant);
//                break;
//            case "upPersonSign":
//                changeinfoTvSign.setText(contant);
//                break;
//            case "upUserSno":
//                changeinfoTvCount.setText(contant);
//                break;
//            case "upHeadImg":
//                Glide.with(this).load(save)
////                    .asBitmap()
//                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity2.this))
//                        .thumbnail(0.1f)
//                        .into(changeinfoIvHead);
////                changeinfoTvCount.setText("555");
//                break;
//        }
//    }
//    String absolutePath;
//    private String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/chatHead/";
//// Environment.getExternalStorageDirectory().getPath() + "/chat_image";
//    private void saveImg(String url) {
//        String path =   getImagePath(url);
//        /**
//         * 拷贝到指定路径
//         */
//
//        File file = new File(imagePath+SplitWeb.getUserId()+".jpg");
//        if (!file.exists()) {
//            try {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        copyFile(path, imagePath+SplitWeb.getUserId()+".jpg");
//        Intent intentBroadcast = new Intent(
//                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        intentBroadcast.setData(Uri.fromFile(file));
//        sendBroadcast(intentBroadcast);
//    }
//
//    /**
//     * Glide 获得图片缓存路径
//     */
//    private String getImagePath(String imgUrl) {
//        String path = null;
//        FutureTarget<File> future = Glide.with(this)
//                .load(imgUrl)
//                .downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL);
//        try {
//            File cacheFile = future.get();
//            path = cacheFile.getAbsolutePath();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        return path;
//    }
//
//    public void copyFile(String oldPath, String newPath) {
//        try {
//            int bytesum = 0;
//            int byteread = 0;
//            File oldfile = new File(oldPath);
//
//            if (oldfile.exists()) { //文件存在时
//                InputStream inStream = new FileInputStream(oldPath); //读入原文件
//                FileOutputStream fs = new FileOutputStream(newPath);
//                byte[] buffer = new byte[1444];
//                int length;
//                while ( (byteread = inStream.read(buffer)) != -1) {
//                    bytesum += byteread; //字节数 文件大小
//                    System.out.println(bytesum);
//                    fs.write(buffer, 0, byteread);
//                }
//                inStream.close();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    private Bitmap photo;
//
//    private void destoryImage() {
//        if (photo != null) {
//            photo.recycle();
//            photo = null;
//        }
//    }
//
//    private int CAMERA_RESULT = 100;
//    private int RESULT_LOAD_IMAGE = 200;
//
//    private File mPhotoFile;
//    File save;
//    /**
//     * 调用相机以及相册的回调 获取的数据
//     */
//    @Override
//    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
//        //		相机
//        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
//            if (mPhotoFile != null && mPhotoFile.exists()) {
//                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                bitmapOptions.inJustDecodeBounds = true;
//                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
//                bitmapOptions.inJustDecodeBounds = false;
//                int be = (int) (bitmapOptions.outHeight / (float) 200);
//                if (be <= 0)
//                    be = 1;
//                bitmapOptions.inSampleSize = be;
//                bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
//                save = ImageUtils.saveBitmap(ChangeInfoActivity2.this, bitmap);
////                final Map<String, File> files = new HashMap<String, File>();
////                files.put("file", save);
//////                UpLoadIdCard(requestCode,files,CAMERA_RESULT_Btn1);
////                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
//////                pdIvHead.setBackgroundResource(0);
////                Glide.with(this).load(saveBitmap)
////                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                        .crossFade(1000).into(changeinfoIvHead);
////                Glide.with(ChangeInfoActivity.this).load(drawable.getBitmap()).;
////                changeinfoIvHead.setImageBitmap(drawable.getBitmap());
////                SendDataImg(files);
//                sendWeb(SplitWeb.upHeadImg(ImageUtils.GetStringByImageView(bitmap)));
//            }
//        }
//
//        //		相册
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumns = {MediaStore.Images.Media.DATA};
//            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            String imagePath = c.getString(columnIndex);
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
////            File saveBitmap = null;
////                saveBitmap = ImageUtils.saveFile(bitmap);
//             save = ImageUtils.saveBitmap(ChangeInfoActivity2.this, bitmap);
//            final Map<String, File> files = new HashMap<String, File>();
//            files.put("file", save);
//            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//
////            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
////            SendDataImg(files);
////            mTvChange.setText("");
////            changeinfoIvHead.setImageBitmap(bitmap);
//            c.close();
////            sendWeb(SplitWeb.upHeadImg(save));
////            sendWeb(SplitWeb.upHeadImg("123"));
//            sendWeb(SplitWeb.upHeadImg(ImageUtils.GetStringByImageView(bitmap)));
//        }
//    }
//
//    private static String readString3( File file)
//    {
//        String str = "";
//        try
//        {
//            FileInputStream in = new FileInputStream(file);
//            // size  为字串的长度 ，这里一次性读完
//            int size = in.available();
//            byte[] buffer = new byte[size];
//            in.read(buffer);
//            in.close();
//            str = new String(buffer, "GB2312");
//        } catch(
//                IOException e)
//        {
//            // TODO Auto-generated catch block
//            return null;
//        }
//        return str;
//    }
//    private static String readString( File file)
//    {
//        StringBuffer str=new StringBuffer("");
////        File file=new File(FILE_IN);
//        try {
//            FileReader fr=new FileReader(file);
//            int ch = 0;
//            while((ch = fr.read())!=-1 )
//            {
//                System.out.print((char)ch+" ");
//            }
//            fr.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("File reader出错");
//        }
//        return str.toString();
//    }
//    public String getXmlString( File xmlfile ) {
//        String xmlString;
//        byte[] strBuffer = null;
//        int flen = 0;
////        File xmlfile = new File("/data/local/getHomePage.xml");
//        try {
//            InputStream in = new FileInputStream(xmlfile);
//            flen = (int) xmlfile.length();
//            strBuffer = new byte[flen];
//            in.read(strBuffer, 0, flen);
//        } catch (FileNotFoundException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        xmlString = new String(strBuffer); //构建String时，可用byte[]类型，
//
//        return xmlString;
//    }
//    // 获取imageview的图片，并且转化为可传递的string
//    public static String GetStringByImageView( Bitmap bitmap){
//        // 从ImageView得到Bitmap对象
////        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//        // 把Bitmap转码成字符串
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 50,baos);
//        String imageBase64 = new String (Base64.encode(baos.toByteArray(), 0));
//        Log.e("imageBase64","data:image/jpg;base64,"+imageBase64);
//        return "data:image/jpg;base64,"+imageBase64;
//    }
//
//    String mTmpPath;
//    /**
//     * 7.0 拍照权限
//     * 我是直接提取成一个方法了
//     */
//    public void getPicturesFile(){
//        mPhotoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chat_image/" + System.currentTimeMillis() + ".jpg");
//        try {
//            mPhotoFile.getParentFile().mkdirs();
//            mPhotoFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mTmpPath = mPhotoFile.getAbsolutePath();
//        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
//        //判断一下当前的系统版本，然后在分配权限
//        if (Build.VERSION.SDK_INT >= 24) {
//            //Android 7.0权限申请
//            ContentValues contentValues = new ContentValues(1);
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(ChangeInfoActivity2.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
//            }
//            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
//            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            startActivityForResult(intent, CAMERA_RESULT);
//        } else {
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
//            startActivityForResult(intent, CAMERA_RESULT);
//        }
//    }
//    String contant =null;
//    //修改签名等的回调按钮
//    @Override
//    public void onSure(String contant) {
//        this.contant=contant;
//        switch (isChangeName)
//        {
//            case "0"://昵称
//                sendWeb(SplitWeb.upNickName(contant));
//                break;
//            case "1"://帐号
//                sendWeb(SplitWeb.upUserSno(contant));
//                break;
//            case "2"://签名、
//                sendWeb(SplitWeb.upPersonSign(contant));
//                break;
//        }
//    }
//
//    @Override
//    public void onCancle() {
//
//    }
//}
