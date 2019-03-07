package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataCreatGroupChat;
import com.doubleq.model.DataCreatGroupResult;
import com.doubleq.model.DataLinkManList;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusJumpGroupChatData;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.ImageUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.CreatGroupChatAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.CreatGroupSeachAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.LetterBar;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.changephoto.PhotoPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

//位置：创建群聊
public class CreatGroupChatActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.tv_abc)
    TextView mTvAbc;

    //    @BindView(R.id.group_team_recyc)
//    RecyclerView mRecyclerView;
    @BindView(R.id.group_team_letter)
    LetterBar mLetterBar;
    @BindView(R.id.seach_ed_input)
    EditText seachEdInput;
    @BindView(R.id.creat_chat_ed_groupname)
    EditText mEdGroupName;
    @BindView(R.id.seach_iv_close)
    ImageView seachIvClose;
    @BindView(R.id.seach_iv_find)
    ImageView seachIvFind;
    @BindView(R.id.creat_chat_tv_head)
    ImageView creatIvHead;
    @BindView(R.id.seach_recyc)
    RecyclerView seachRecyc;
    @BindView(R.id.seach_lin_list)
    LinearLayout seachLinList;
//    @BindView(R.id.group_lin_list)
//    LinearLayout groupLinList;

    //    未搜索到东西
    @BindView(R.id.seach_tv_noSearch)
    TextView seachTvNoSearch;
    @BindView(R.id.seach_lin_noSearch)
    RelativeLayout seachLinNoSearch;
    @BindView(R.id.creat_exlist_friend)
    ExpandableListView mExList;
    @BindView(R.id.group_chat_lin_main)
    LinearLayout mLinMain;
    @BindView(R.id.creat_chat_lin_top)
    LinearLayout mLinTop;
    @BindView(R.id.creat_chat_tv_yixuanze)
    TextView creatChatTvYixuanze;

    private Runnable runnable;
    String mShare = "1";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

//    LinearLayoutManager linearLayoutManager;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_creat_group_chat;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("创建群聊");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setText("确定");
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        linearLayoutManager = new LinearLayoutManager(CreatGroupChatActivity.this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
        seachRecyc.setLayoutManager(new LinearLayoutManager(CreatGroupChatActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupTeamActivity.this));
//        sendWeb( SplitWeb.blackList());
        initGroup();
    }

    private void initGroup() {
        initUI();
        initABC2();
//        initHttp();
        sendWeb(SplitWeb.getGroupWebInfo());
    }

    //    private ArrayList<Allcity> allCityList = new ArrayList<Allcity>();
    CreatGroupSeachAdapter mSeachAdapter;
    private ArrayList<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> searchCityList = new ArrayList<>();

    private void initUI() {
        mSeachAdapter = new CreatGroupSeachAdapter(CreatGroupChatActivity.this, searchCityList);
        seachRecyc.setAdapter(mSeachAdapter);
        initListenSearch();

        //设置EditText文本监听事件
        seachEdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取用户输入文本
                String putStr = seachEdInput.getText().toString();
                //搜索的Listview显示
                seachLinList.setVisibility(View.VISIBLE);
                searchCityList.clear();
                for (int a = 0; a < mFriendList.size(); a++) {
                    List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> group_list = mFriendList.get(a).getGroupList();
                    for (int b = 0; b < group_list.size(); b++) {
                        String nick_name = group_list.get(b).getNickName();
                        Log.e("searchCityList", nick_name + "---------输入------------->" + putStr);
                        if (nick_name.contains(putStr)) {
                            searchCityList.add(group_list.get(b));
                            Log.e("searchCityList", nick_name + "---------jinlai------------->" + putStr);
                        }
                    }
                }
                //为搜索布局的显示隐藏
                if (searchCityList.size() == 0) {
                    seachLinNoSearch.setVisibility(View.VISIBLE);
                } else {
                    seachLinNoSearch.setVisibility(View.GONE);
                }

                //输入框为空时，返回城市列表
                if ("".equals(putStr)) {
                    searchCityList.clear();
                    seachLinList.setVisibility(View.GONE);
                    seachLinNoSearch.setVisibility(View.GONE);
                }
                //RecyclerView列表进行批量UI数据更新
//                mSeachAdapter.notifyItemRangeInserted(0,searchCityList.size());
//                // scrollToPosition（0）作用是把列表移动到顶端
//                seachRecyc.scrollToPosition(0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (creatGroupChatAdapter!=null)
                        {
//                            List<String> checkString= creatGroupChatAdapter.getCheckString();
                            if (mList!=null&&mList.size()!=0)
                                mSeachAdapter.setChoose(mList);
                            Log.e("checkChat","mList="+mList.toString());
                        }

                        mSeachAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void initListenSearch() {
        List<String> checkSearch = mSeachAdapter.getCheckString();
        if (creatGroupChatAdapter!=null) {
            List<String> checkString1 = creatGroupChatAdapter.getCheckString();
        }
        mSeachAdapter.setCheckedChangeListener(new CreatGroupSeachAdapter.OnMyCheckedChangeListener() {
            @Override
            public void onCheckedChanged(String friendId, boolean isChecked) {
                if (isChecked)
                {

                }

            }
        });
    }

    List<String> ABCList = new ArrayList<>();

    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouuchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧")) {
                    mExList.setSelection(0);
//                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
//                    mRecyclerView.scrollToPosition(0);
//                    mRecyclerView.setSelection(0);
                    return;
                }
                for (int i = 0; i < mFriendList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (mFriendList.get(i).getGroupName());
                    if (letter.equals(firstLetter)) {
                        mExList.setSelectedGroup(i);
                        break;
                    }
                }
            }

            @Override
            public void onTouchUp() {
                mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }

    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }
    //    大列表选中的数据
    List<String> mList = new ArrayList<>();
    List<DataCreatGroupChat.RecordBean.FriendListBean> mFriendList = new ArrayList<>();
    DataCreatGroupResult.RecordBean record1;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "getGroupWebInfo":
                DataCreatGroupChat dataCreatGroupChat = JSON.parseObject(responseText, DataCreatGroupChat.class);
                DataCreatGroupChat.RecordBean record = dataCreatGroupChat.getRecord();
                if (record != null) {
                    List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list = record.getFriendList();
                    mFriendList.addAll(friend_list);
                    if (friend_list.size() > 0)
                        initAdapter(friend_list);
                }
                break;
//            case "groupSend":
//                if (record1!=null)
//                {
//
//                }
//                break;
//                创建群成功
            case "createdUserGroup":
//                在application处理
                DataCreatGroupResult dataCreatGroupResult = JSON.parseObject(responseText, DataCreatGroupResult.class);
                record1 = dataCreatGroupResult.getRecord();
                if (record1 != null) {
                    DialogUtils.showDialogOne("群创建成功，快去聊天吧", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
                            cusJumpGroupChatData.setGroupId(record1.getGroupOfId());
                            cusJumpGroupChatData.setGroupName(record1.getGroupNickName());
                            IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
                            AppManager.getAppManager().finishActivity(CreatGroupChatActivity.this);
                        }
                    });
//                    send(SplitWeb.groupSend(record1.getGroupOfId(),"群创建成功，快去聊天吧",AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
                }

//                if (blackAdapter != null) {
//                    blackAdapter.delItem(positions);
//                }
//                ToastUtil.show("移除成功");
//                Log.e("position", "点击了移除" + positions);
                break;
        }
    }

    CreatGroupChatAdapter creatGroupChatAdapter = null;

    private void initAdapter(final List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list) {
        creatGroupChatAdapter = new CreatGroupChatAdapter(this, friend_list);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mExList.setAdapter(creatGroupChatAdapter);
        mExList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });
        for (int i = 0; i < creatGroupChatAdapter.getGroupCount(); i++) {
            mExList.expandGroup(i);
        }
        creatGroupChatAdapter.setOnMyLinChangeListener(new CreatGroupChatAdapter.OnMyLinChangeListener() {
            @Override
            public void onCheckedChanged(String friendId, boolean isChecked) {
                if (isChecked)
                {
                    if (!mList.contains(friendId))
                        mList.add(friendId);
                }else
                {
                    if (mList.contains(friendId))
                        mList.remove(friendId);
                }
                creatChatTvYixuanze.setText("已选择"+mList.size()+"人");
                if (mList.size()>0) {
                    creatChatTvYixuanze.setVisibility(View.VISIBLE);
                    creatChatTvYixuanze.setTextColor(getResources().getColor(R.color.app_theme));

                }
                else {
                    creatChatTvYixuanze.setVisibility(View.INVISIBLE);
                    creatChatTvYixuanze.setTextColor(getResources().getColor(R.color.gray999));
                }
                List<String> checkString = creatGroupChatAdapter.getCheckString();
                Log.e("checkChat","friendId="+friendId+isChecked+"++++"+mList.toString()+"---"+checkString.toString());
            }
        });
//        mExList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = friend_list.get(groupPosition).getGroupList().get(childPosition);
////                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
////                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",userId);
//                Log.e("checkChat","groupListBean="+groupListBean.getNickName());
//
//                return false;
//            }
//        });
        creatGroupChatAdapter.notifyDataSetChanged();
    }

    private PhotoPopWindow photoPopWindow = null;

    @OnClick({R.id.seach_iv_close,  R.id.inclu_tv_right, R.id.creat_chat_tv_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seach_iv_close:
                seachEdInput.setText("");
                break;
//                点击头像
            case R.id.creat_chat_tv_head:
                if (photoPopWindow == null)
                    photoPopWindow = new PhotoPopWindow(CreatGroupChatActivity.this, MyClick);
                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
                break;
//                点击确定
            case R.id.inclu_tv_right:
                List<String> checkString = creatGroupChatAdapter.getCheckString();
                if (checkString.size() > 0) {
//                    String check[]= new String[checkData.size()];
                    String checkChat = "";
                    for (int i = 0; i < checkString.size(); i++) {
                        if (i == 0) {
                            checkChat += checkString.get(i);
                        } else {
                            checkChat += "," + checkString.get(i);
                        }
//                        check[i]=checkData.get(i).getWx_sno();
                    }
                    Log.e("checkChat","checkChat="+checkChat);
//                    String replace =Arrays.toString(check).replace("[", "").replace("]", "").replace(" ","");
                    String trim = mEdGroupName.getText().toString().trim();
                    sendWebHaveDialog(SplitWeb.createdUserGroup(checkChat, trim, imageBase64)
                            , "创建中...", "群聊创建成功");
//                    sendWeb(SplitWeb.createdUserGroup(checkChat,"zll",""));
                } else {
                    ToastUtil.show("请选择群聊成员");
                }

                break;
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
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    break;
                default:
                    break;
            }
        }
    };
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
    File save;

    String imageBase64 = "";

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
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                save = ImageUtils.saveBitmap(CreatGroupChatActivity.this, bitmap);
//                final Map<String, File> files = new HashMap<String, File>();
//                files.put("file", save);
////                UpLoadIdCard(requestCode,files,CAMERA_RESULT_Btn1);
//                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
////                pdIvHead.setBackgroundResource(0);
                imageBase64 = ImageUtils.GetStringByImageView(bitmap);
                Glide.with(this).load(save)
                        .bitmapTransform(new CropCircleTransformation(CreatGroupChatActivity.this))
                        .into(creatIvHead);
//                Glide.with(ChangeInfoActivity.this).load(drawable.getBitmap()).;
//                changeinfoIvHead.setImageBitmap(drawable.getBitmap());
//                SendDataImg(files);
//                sendWeb(SplitWeb.upHeadImg(ImageUtils.GetStringByImageView(bitmap)));
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
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
            save = ImageUtils.saveBitmap(CreatGroupChatActivity.this, bitmap);
            final Map<String, File> files = new HashMap<String, File>();
            files.put("file", save);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);

//            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
//            SendDataImg(files);
//            mTvChange.setText("");
//            changeinfoIvHead.setImageBitmap(bitmap);
            c.close();
            imageBase64 = ImageUtils.GetStringByImageView(bitmap);
            Glide.with(this).load(save)
                    .bitmapTransform(new CropCircleTransformation(CreatGroupChatActivity.this))
                    .into(creatIvHead);
//            sendWeb(SplitWeb.upHeadImg(save));
//            sendWeb(SplitWeb.upHeadImg("123"));
//            sendWeb(SplitWeb.upHeadImg(ImageUtils.GetStringByImageView(bitmap)));
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
                ActivityCompat.requestPermissions(CreatGroupChatActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
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
