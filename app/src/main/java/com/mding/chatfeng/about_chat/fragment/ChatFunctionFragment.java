package com.mding.chatfeng.about_chat.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.ChatActivity;
import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.MD5Utils;
import com.mding.chatfeng.about_utils.MathUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.base.ChatBaseFragment;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mding.chatfeng.about_base.BaseActivityForResult.send;
import static com.mding.chatfeng.about_utils.about_file.HeadFileUtils.getRealFilePathFromUri;
import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

/**
 * 项目：DoubleQ
 * 文件描述：聊天点击加号后界面的点击事件
 * 作者：zll
 * 修改者：ljj
 */
public class ChatFunctionFragment extends ChatBaseFragment {
    Unbinder unbinder;
    private View rootView;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    //请求相机
    private static final int CAMERA_RESULT = 600;
    //请求相册
    private static final int REQUEST_PICK = 601;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 602;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 603;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 604;
    private File output;
    private Uri imageUri;
    String messageTypeImg = "2";
    public static String imgTotalFunFrag;
    public static String imgMD5FunFrag;
    public static String smallImgByBase64;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_chat_function, container, false);
            ButterKnife.bind(this, rootView);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        callBackValue = (CallBackValue) getActivity();
//    }

    //    @OnClick({R.id.chat_function_photo, R.id.chat_function_camera})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.chat_function_photo:
//                if (ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);
//
//                } else {
//                    choosePhoto();
//                }
//                break;
//            case R.id.chat_function_camera:
//                if (ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);
//
//                } else {
//                    takePhoto();
//                }
//                break;
//        }
//    }

    /**
     * 拍照
     */
    private void takePhoto() {
        /**
         * 最后一个参数是文件夹的名称，可以随便起
         */
        File file = new File(Environment.getExternalStorageDirectory(), "拍照");
        if (!file.exists()) {
            file.mkdir();
        }
        /**
         * 这里将时间作为不同照片的名称
         */
        output = new File(file, System.currentTimeMillis() + ".jpg");

        /**
         * 如果该文件夹已经存在，则删除它，否则创建一个
         */
        try {
            if (output.exists()) {
                output.delete();
            }
            output.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
         */
        imageUri = Uri.fromFile(output);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

//        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getActivity(),BuildConfig.APPLICATION_ID + ".provider", tempFile)); //Uri.fromFile(tempFile)
        startActivityForResult(intent, CROP_PHOTO);

    }

    /**
     * 从相册选取图片
     */
    private void choosePhoto() {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

    }
    public void onActivityResult(int req, int res, Intent data) {
//        switch (req) {
//            case CROP_PHOTO:
//                if (res == Activity.RESULT_OK) {
//                    try {
//                        MessageInfo messageInfo = new MessageInfo();
//                        messageInfo.setMsgType("2");  //发送消息类型为：图片
//                        messageInfo.setImageUrl(imageUri.getPath());
//                        EventBus.getDefault().post(messageInfo);
//                    } catch (Exception e) {
//                    }
//                } else {
//                    Log.d(Constants.TAG, "失败");
//                }
//
//                break;
//            case REQUEST_CODE_PICK_IMAGE:
//                if (res == Activity.RESULT_OK) {
//                    try {
//                        Uri uri = data.getData();
//                        MessageInfo messageInfo = new MessageInfo();
//                        messageInfo.setMsgType("2");   //发送消息类型为：图片
//                        messageInfo.setImageUrl(getRealPathFromURI(uri));
//                        EventBus.getDefault().post(messageInfo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.d(Constants.TAG, e.getMessage());
//                    }
//                } else {
//                    Log.d(Constants.TAG, "失败");
//                }
//
//                break;
//
//            default:
//                break;
//        }

        //		相机
        if (req == CAMERA_RESULT) {
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
                if(res==Activity.RESULT_CANCELED)
                {
                    return;
                }
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                backToChatActivity(Uri.fromFile(mPhotoFile));
            }
        }
        //		相册
        if (req == REQUEST_PICK && null != data) {
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            c.close();
            backToChatActivity(selectedImage);
        }
        if (req == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
//             原图
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//            String imgByOrigin = ImageUtils.Bitmap2StrByBase64(bitMap);

            //相对高清图
            Bitmap bigImg = ImageUtils.imageZoom(bitMap,200);
            String bigImgByBase64 = ImageUtils.Bitmap2StrByBase64(bigImg);

//             压缩图
            Bitmap smallImg = ImageUtils.imageZoom(bigImg);
            smallImgByBase64 = ImageUtils.Bitmap2StrByBase64(smallImg);
            // 发送图片（格式：压缩图片的base64_高清原图的base64）
//            String imgTotal2 = imgByZoom + "_" + imgByZoom;

            // TODO  请求MD5的key查询
            String imgTotalMD5 = MD5Utils.encryptMD5(bigImgByBase64);
            imgTotalFunFrag = smallImgByBase64 + "_" + bigImgByBase64 + "_" + imgTotalMD5;
            imgMD5FunFrag = "__"+imgTotalMD5;
            if (SplitWeb.getSplitWeb().IS_CHAT.equals("1")){  // 私聊
                //  type：发送类型 1私聊图片 2群聊
                send(SplitWeb.getSplitWeb().getQueryRepetition("1", imgTotalMD5));
            }
            else  if (SplitWeb.getSplitWeb().IS_CHAT_GROUP.equals("2")){    // 群聊
                send(SplitWeb.getSplitWeb().getQueryRepetition("2", imgTotalMD5));
            }
            ToastUtil.show("正在发送图片...");
        }
    }

    /**
     * 返回聊天界面
     */
    public void backToChatActivity(Uri uri) {
        if (uri == null) {
            return;
        }
//        callBackValue.sendUri(uri);
        Intent intent = new Intent();
//        if (SplitWeb.getSplitWeb().IS_CHAT.equals("1"))
//            intent.setClass(getActivity(), ChatActivity.class);
//        else if (SplitWeb.getSplitWeb().IS_CHAT_GROUP.equals("2"))
//            intent.setClass(getActivity(), ChatGroupActivity.class);
        intent.setClass(getActivity(), ShowImgActivity.class);
        intent.setData(uri);
//        startActivity(intent);
//        getActivity().finish();
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
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                takePhoto();
//            } else {
//                ToastUtil.show("请同意系统权限后继续");
//            }
//        }
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                choosePhoto();
//            } else {
//                ToastUtil.show("请同意系统权限后继续");
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private File mPhotoFile;
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
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
            }
            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
            Uri uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_RESULT);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
            startActivityForResult(intent, CAMERA_RESULT);
        }
    }

    private Bitmap photo;
    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        Log.d("==image==", "*****************打开相册********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    private void dealSendResult(String responseText) {
        DataJieShou dataJieShou = JSON.parseObject(responseText, DataJieShou.class);
        DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            String time = (String) SPUtils.get(getActivity(), AppConfig.CHAT_SEND_TIME, "");
            SPUtils.put(getActivity(), AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    if (MathUtils.abs(i) < 5) {
                        record.setRequestTime("");
                    } else {
                        record.setRequestTime(record.getRequestTime());
//                        SPUtils.put(this,AppConfig.CHAT_SEND_TIME,"");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick({R.id.chat_function_photo, R.id.chat_function_camera, R.id.chat_function_video, R.id.chat_function_voice, R.id.chat_function_transfer, R.id.chat_function_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_function_photo:
                //权限判断
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    goToAlbum();
                }
//                ToastUtil.isDebugShow(getResources().getString(R.string.stay_tuned));
                break;
            case R.id.chat_function_camera:
                destoryImage();
                getPicturesFile();
//                ToastUtil.isDebugShow(getResources().getString(R.string.stay_tuned));
                break;
            case R.id.chat_function_video:

                ToastUtil.show(getResources().getString(R.string.stay_tuned));
                break;
            case R.id.chat_function_voice:

                ToastUtil.show(getResources().getString(R.string.stay_tuned));
                break;
            case R.id.chat_function_transfer:

                ToastUtil.show(getResources().getString(R.string.stay_tuned));
                break;
            case R.id.chat_function_card:

                ToastUtil.show(getResources().getString(R.string.stay_tuned));
                break;
        }
    }
//    CallBackValue callBackValue;
//    public interface CallBackValue{
//        void sendUri(Uri uri);
//    }
}
