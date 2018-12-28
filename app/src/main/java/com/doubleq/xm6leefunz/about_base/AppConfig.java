package com.doubleq.xm6leefunz.about_base;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class AppConfig {
//    保存图片位置
public static final String TYPE_FRIEND= "friendimg";//好友头像
public static final String TYPE_GROUP= "groupimg";//群组头像


    //    notification
//     类型
    public static final String TYPE_NOTICE= "notice";//好友通知

    public static final String TYPE_CHAT= "chat_chat";//聊天通知
    public static final String TYPE_CHAT_QUN= "chat_chat_qun";//聊天通知

    public static final String TYPE_KEY= "key_notifity";//聊天通知
    public static final String TYPE_KEY_FRIEND= "key_notifityfirend";//聊天通知
    public static final String INTENT_ACTION= "intent_notification";//聊天通知


    public static final String CHAT_SEND_TIME= "chat_time_send";//聊天发送时间
    public static final String CHAT_SEND_TIME_REALM= "chat_time_send_realm";//聊天发送时间
    public static final String CHAT_SEND_TIME_REALM_GROUP= "chat_time_send_realm_group";//聊天发送时间
    public static final String CHAT_RECEIVE_TIME= "chat_time_receive";//聊天发送时间
    public static final String CHAT_RECEIVE_TIME_REALM= "chat_time_receive";//聊天发送时间

    public static final String CHAT_RECEIVE_TIME_REALM_GROUP= "chat_time_receive_group";//聊天群发送时间


    public static final String LINKMAN_FRIEND_NUM= "friendNum";//联系人好友 界面的数量

//1文字 2图片 3表情 4文件
    public static final String SEND_MESSAGE_TYPE_TEXT= "1";//消息类型
    public static final String SEND_MESSAGE_TYPE_IMG= "2";//消息类型
    public static final String SEND_MESSAGE_TYPE_EMONI= "3";//消息类型
    public static final String SEND_MESSAGE_TYPE_FILE= "4";//消息类型




    public static  String CHAT_FRIEND_ID= "friendid";
    public static  String CHAT_GROUP_ID= "groupid";

    //好友分组请求码
    public static final int FRIEND_ADD_GROUP_REQUEST= 0x0;//AddGoodFriendActivity  页面的请求码
    public static final int FRIEND_DATA_GROUP_REQUEST=0x1;//FriendDataActivity 页面的请求码

    public static final int FRIEND_ADD_GROUP_RESULT=0x3;//AddGoodFriendActivity
    public static final int FRIEND_DATA_GROUP_RESULT=0x4;//返回给FriendDataActivity



    public static final String GROUP_ID= "group_id";
    public static final String GROUP_ADDKEY= "group_key";
    public static final String GROUP_SNO= "group_sno";
    public static final String LodingNormal= "0";
    public static final String LodingFlower= "1";
    public static final String LodingFlowerHaveResult= "3";
    public static final String LodingGIF= "2";
    public static final String NORMAL= "4";
    public  static  final  String  Resgister_KEY = "register_key";
    public static final String GOH5_TITAL_KEY= "tital_html";
    public static final String GOH5_ARTICAL_KEY= "artical_html";
    //登录返回的信息key
    public static final String TOKEN_KEY= "token_key";
    public static final String SP_LOGIN_ACCOUNT= "account";
    public static final String SP_LOGIN_PSW= "password";
    public static final String SP_DOWN_RATE= "up_data";
    public static final String SP_ALL_RATE= "all_rate_data";
    public static final String NO_RESULT= "333";

    public static final String ERROR = "网络请求失败，请检测网络是否断开";
    //    缓存的key
    public static final String HOME_MY_FEILV= "my_feilv";//首页 - -我的费率
    public static final String HOME_DOWN_FEILV= "down_feilv";//首页 - -下调费率

    //在地址列表点击  返回选择的地址
    public static final String ADDR_CHOOSE_KEY= "choose_addr";
    public static final int  ADDR_SENDER_CODE= 0x01;
    public static final String ED_ADDR_KEY= "edit_addr";


    public static final String User_NI_NAME= "real_name";
    public static final String User_HEAD_URL= "url_head";
    public static final String User_IS_SET_PSW= "is_psw";
    public static final String User_PHONE= "phone";
    public static final String User_ID= "id";
    public static final String IS_BLACK_CARD= "is_activation";
    /** 服务器返回码
     * String 类型
     */
    public static final String CODE_OK = "200";//成功
    public static final String CODE_EPC = "9999";
    public static final String CODE_KEY_INVALID = "1001";
    public static final String CODE_SIGN_EMPTY = "1002";
    public static final String CODE_SIGN_FAIL = "1003";
    public static final String CODE_LACK = "1004";
    public static final String CODE_TIMEOUT= "1007";
    public static final String CODE_TOKEN_OUT= "9001";
    public static final String CODE_NO_METHOD= "1006";


    public static final int HANDLE_MSG_SUCCESS= 0x01;//成功
    public static final int HANDLE_TOWN_SUCCESS= 0x06;//成功
    public static final int HANDLE_TOWN_SUCCESS_ONE= 0x07;//成功
    public static final int HANDLE_TOWN_SUCCESS_FIVE= 0x08;//成功

    public static final int HANDLE_MSG_SUCCESS_FIRST= 0x02;//成功  第一次

    public static final int HANDLE_MSG_SUCCESS_TWCE= 0x03;//成功   第二次以后

    public static final int HANDLE_MSG_FAILED= 0x04;//失败
    public static final int HANDLE_MSG_ERROR= 0x05;//错误

}
