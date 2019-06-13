package com.mding.chatfeng.about_utils;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomDialog;
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomUpDateDialog;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.text.DecimalFormat;


/**
 * Created by zll on 2017/10/20 0020.
 */

public class UpDataUtils {
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};//PERMISSIONS
    private static final String TAG = "VersionUpdataHelper";

    private final String DOWNLOAD_FILE_NAME;

    private Context mContext;
    private DownloadManager mDownloadManager;
    private String mUrl;
    private long mDownloadId;
    private CompleteReceiver mReceiver;
    private int mCancleDownload;
    private CustomUpDateDialog mUpDateDialog;
    private CustomDialog mDialog;
    private CustomUpDateDialog.Builder mUpdateBuilder;
    private boolean mCancelable;
    private String mUpdateInfo;

    public UpDataUtils(Activity activity, String url) {
        this(activity, url, true);
    }

    public UpDataUtils(Activity activity, String url, boolean cancelable) {
        this(activity, url, cancelable, "");
    }
    //shifou
    public UpDataUtils(Activity activity, String url, boolean cancelable, String info){
        DOWNLOAD_FILE_NAME = System.currentTimeMillis() + ".apk";
        if (activity == null || activity.isFinishing()){
            return;
        }
        mContext = activity;
        mUrl = url;
        mCancelable = cancelable;
        mUpdateInfo = info;
        showNewVersionDialog();
    }

    private void showNewVersionDialog() {
        String message = BaseApplication.getAppContext().getString(R.string.dialog_tv_update);
//        String message = "发现新版本，是否下载并更新";
        if (!TextUtils.isEmpty(mUpdateInfo)){
            // TODO 替换成 换行 + 空两格
            mUpdateInfo = mUpdateInfo.replace("_","\n");
            message = message + "\n" + mUpdateInfo;
            MyLog.i(TAG, "---message===" + message);
        }
        mUpdateBuilder = new CustomUpDateDialog.Builder(mContext);
        mUpdateBuilder.setMessage(message)
                .setPositiveButton(BaseApplication.getAppContext().getResources().getString(R.string.dialog_btn_sure), new DialogInterface.OnClickListener() {
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (!StrUtils.isEmpty(mUrl)) {
                            startDownload(mUrl);
                            initDownloadingDialog();
                            mDialog.show();
                            mCancleDownload = 0;
                        }
                    }
                });
        if (!mCancelable) {
            mUpdateBuilder.setNegativeButton(BaseApplication.getAppContext().getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
//            mUpdateBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        mUpDateDialog = mUpdateBuilder.create();
        mUpDateDialog.setCancelable(false);
        mUpDateDialog.show();
    }
    CustomDialog.Builder mCusbuilder;
    private void initDownloadingDialog() {
        mCancleDownload = 0;
        mCusbuilder  = new CustomDialog.Builder(mContext);
        mCusbuilder.setProgressBar(true)
                .setMessage("软件更新中...")
//        mBuilder = new CustomUpDateDialog.Builder(mContext);
//        mBuilder.setMessage("软件更新中...");
                .setNegativeButton(BaseApplication.getAppContext().getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCancelable) {
                            mDownloadManager.remove(mDownloadId);
                            dialog.dismiss();
                            mCancleDownload = 1;
                            unregisterUpdataReceiver();
                        }
                    }
                });

//        mUpDateDialog = mBuilder.create();
        mDialog = mCusbuilder.create();
        mReceiver = new CompleteReceiver();
        mContext.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void unregisterUpdataReceiver() {
        mContext.unregisterReceiver(mReceiver);
    }

    private void startDownload(String url) {
        mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri resource = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(resource);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);
        request.setShowRunningNotification(true);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, DOWNLOAD_FILE_NAME);
        try {
            mDownloadId = mDownloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mContext.getContentResolver().registerContentObserver(Uri.parse("content://downloads/"),
                true, new DownloadObserver(handler, mContext, mDownloadId));
    }
    private PermissionUtils permissionUtils;
    //启动安装
    Context mContext1;
    private void openFile(long downloadId) {
        mContext1 = AppManager.getAppManager().currentActivity().getApplicationContext();
        permissionUtils = new PermissionUtils(AppManager.getAppManager().currentActivity(), mContext1);
        permissionUtils.requestPermissions(PERMISSIONS, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);
        permissionUtils.requestPermissions(PERMISSIONS, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE);
        Uri downloadFileUri = mDownloadManager.getUriForDownloadedFile(downloadId);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent updateApk = new Intent(Intent.ACTION_VIEW);
            updateApk.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            updateApk.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            mContext.startActivity(updateApk);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            mContext.startActivity(install);
        } else {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/", DOWNLOAD_FILE_NAME);
            openFile(file, mContext);
        }
    }

    public void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String mimeType = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), mimeType);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMIMEType(File file) {
        String type = "";
        String name = file.getName();
        String fileName = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileName);
        return type;
    }

    public class CompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (mDownloadId == completeDownloadId && mCancleDownload == 0) {
                mDialog.dismiss();
                openFile(completeDownloadId);
                unregisterUpdataReceiver();
            }
        }
    }

    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            float mDownloadSoFar = (float) msg.arg1 / (1024 * 1024);
            float mDownloadAll = (float) msg.arg2 / (1024 * 1024);

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            if (mDialog.isShowing()) {
                ProgressBar progressBar = mDialog.findViewById(R.id.progressbar_dialog_update);
                TextView mDownloadDialogMessageCancelTv = (TextView) mDialog.findViewById(R.id.tv_dialog_message_progress);
                if (mDownloadSoFar != 0)
                    progressBar.setProgress((int) ((mDownloadSoFar*100)/mDownloadAll));
                MyLog.i("updateDialog", "-------------------------------" + (int)((mDownloadSoFar*100)/mDownloadAll));
                mDownloadDialogMessageCancelTv.setText("已下载" + decimalFormat.format(mDownloadSoFar) + "M，共" + decimalFormat.format(mDownloadAll) + "M");
            }
        }
    };

    public class DownloadObserver extends ContentObserver {
        private long downid;
        private Handler handler;
        private Context context;

        public DownloadObserver(Handler handler, Context context, long downid) {
            super(handler);
            this.handler = handler;
            this.downid = downid;
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            DownloadManager.Query query = new DownloadManager.Query().setFilterById(downid);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor cursor = downloadManager.query(query);
            while (cursor.moveToNext()) {
                int mDownload_so_far = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                int mDownload_all = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                if (mDownload_so_far < 0) {
                    mDownload_so_far = 0;
                }
                Message message = Message.obtain();
                message.arg1 = mDownload_so_far;
                message.arg2 = mDownload_all;
                message.obj = downid;
                handler.sendMessage(message);
            }
        }
    }

}
