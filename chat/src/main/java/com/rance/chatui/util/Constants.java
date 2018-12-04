package com.rance.chatui.util;

/**
 */
public class Constants {
    public static final String TAG = "rance";
    /** 0x002-接受消息  0x001-发送消息**/
    public static final int CHAT_ITEM_TYPE_LEFT = 0x002;
    public static final int CHAT_ITEM_TYPE_RIGHT = 0x001;
    /** 0x003-发送中  0x004-发送失败  0x005-发送成功**/
    public static final int CHAT_ITEM_SENDING = 0x003;
    public static final int CHAT_ITEM_SEND_ERROR = 0x004;
    public static final int CHAT_ITEM_SEND_SUCCESS = 0x005;

    /** 1文字 2图片 3表情 4文件**/
    public static final String CHAT_TEXT = "1";
    public static final String CHAT_PICTURE = "2";
    public static final String CHAT_EMOTION = "3";
    public static final String CHAT_FILE= "4";
//    key  传入的key
    public static final String KEY_FRIEND_HEADER= "header";
    public static final String KEY_FRIEND_ID= "friendid";
}
