//package com.mding.chatfeng.about_base.web_base;
//
//
//import android.util.Log;
//
//import com.mding.chatfeng.about_base.SignForXm6leefunJava;
//import com.mding.chatfeng.about_utils.HelpUtils;
//import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
//import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
//
//import java.util.ArrayList;
//import java.util.TreeMap;
//
//public class SplitWeb.getSplitWeb()Old {
//
//    public static String IS_CHAT= "00";
//    public static String IS_CHAT_GROUP= "33";
//    public static String USER_TOKEN= "";
//    public static String MOBILE= "";
//    public static String WX_SNO= "";
//    public static String PERSON_SIGN= "";
//    public static String QR_CODE= "";
//    public static String NICK_NAME= "";
//    public static String USER_ID="";
//    public static String USER_HEADER= "";
//
////      SPUtils.put(this,"header",record.getHeadImg());
////            SPUtils.put(this,"name",record.getNickName());
//
//    public static String getUserHeader() {
//
//        USER_HEADER=(String ) SPUtils.get(HelpUtils.getACt(),"header","");
//        return USER_HEADER;
//    }
//    public static String getUserId() {
//        if(StrUtils.isEmpty(USER_ID))
//            USER_ID=(String ) SPUtils.get(HelpUtils.getACt(), AppAllKey.USER_ID_KEY,"");
//        return USER_ID;
//    }
//    public static String getUserToken() {
//        if(StrUtils.isEmpty(USER_TOKEN))
//            USER_TOKEN= (String )SPUtils.get(HelpUtils.activity,AppAllKey.USER_Token,"");
//        return USER_TOKEN;
//    }
//    public static String getNiName() {
//
//        NICK_NAME=(String ) SPUtils.get(HelpUtils.getACt(),"name","");
//
//        return NICK_NAME;
//    }
//
//    /**
//     * WebSocket 请求参数
//     */
//    static TreeMap<String, String> map = new TreeMap<>();
//
//    /**
//     * ------------------------------------------------------------------------------------------------------------------------------
//     * Http请求参数
//     */
//    public static ArrayList<String> mList=new ArrayList<String>();
////   测试
////    public static String URL = "http://192.168.4.133:5052/LoginController/";
////    public static String WebSocket_URL = "ws://192.168.4.133:5053";
//
//    //   Http 正式
////    public static String WebSocket_URL = "ws://192.168.4.133:9093";
////    public static String URL = "http://192.168.4.133:9092/LoginController/";
//////    外网
////    public static String WebSocket_URL = "ws://192.168.4.133:5053";
////    public static String URL = "http://192.168.4.133:5052/LoginController/";
//
//    public static String WebSocket_URL = "ws://119.23.229.66:9093";
//    public static String URL = "http://119.23.229.66:9092/LoginController/";
//
//    public static String loginIn(String mobile, String password){
//        mList.clear();
//        mList.add("sno="+mobile);
//        mList.add("password="+password);
////        mList.add("mobile="+"13860169273");
////        mList.add("password="+"1234566");
//        return URL+"loginIn?"+ SignForXm6leefunJava.getSing(mList);
//    }
//    public static String register(String mobile, String password,String code){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("password="+password);
//        mList.add("code="+code);
////        mList.add("mobile="+"13860169273");
////        mList.add("password="+"1234566");
//        return URL+"register?"+SignForXm6leefunJava.getSing(mList);
//    }
//    //    短信验证码接口  1支付 2提现 3修改支付密码 4修改登录密码 5注册 6修改手机号
//    public static String smsCode(String mobile, String type){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("type="+type);
//        return URL+"getSmsCode?"+ SignForXm6leefunJava.getSing(mList);
//    }
//    public static String smsLogin(String mobile, String code){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("code="+code);
//        return URL+"smsLogin?"+ SignForXm6leefunJava.getSing(mList);
//    }
//
//    /**
//     * -------------------------------------------------------------------------------------------------------------------------------------------
//     */
//
//    /**
//     * web
//     * 绑定uid接口
//     * @return
//     */
//    public static String userIdParameter= "userId";
//    public static String userTokenParameter= "token";
//
//    public  static  String bindUid(){
//        dealMap();
//        Log.e("WebSocketLib","userIdParameter="+userIdParameter+"-------------------------------------"+USER_ID);
//        String request = WebUrl.request("PersonCenter", "bindUid", map);
//        return  request;
//    }
//
//    private static void dealMap() {
//        map.clear();
//        if(StrUtils.isEmpty(USER_ID))
//            getUserId();
//        map.put(userIdParameter,USER_ID);
//        if(StrUtils.isEmpty(USER_TOKEN))
//            getUserToken();
//        map.put(userTokenParameter,USER_TOKEN);
//    }
//
//    //注册时设置头像接口
//    public static String setHeadImg(String nickName, String headImg){
//        dealMap();
//        map.put("nickName",nickName);
//        map.put("headImg",headImg);
//        String request = WebUrl.request("Login", "setHeadImg", map);
//        return  request;
//    }
//    /**
//     * 我的资料接口
//     * @return
//     */
//    public  static  String personalCenter(){
//        dealMap();
//        String request = WebUrl.request("PersonCenter", "personalCenter", map);
//        return  request;
//    }
//    /**
//     * 名片分享、消息提醒、隐私设置接口
//     * @param type  1为名片分享 2为消息提醒 3为隐私设置 (修改类型接)
//     *当type==1时
//     *   是否分享名片 1: is_share  0为禁止被分享1为允许 2为需开启验证
//     * 当type==2时
//     *              0为否 1为
//     *   （是否接收新消息提醒 is_voice_remind）（是否接收语音消息提示 is_voice_remind）
//     *              （是否接收视频消息提示 is_video_remind）
//     * 当type==3时
//     *    是否帐号显示 is_sno_show  （是否二维码显示 is_qrcode_show）
//     *可变长参数 String ... type
//     */
//    public  static  String permissionSetOne(String type,String isShare){
//        dealMap();
//        map.put("type",type);
//        map.put("isShare",isShare);
//        String request = WebUrl.request("PersonCenter", "permissionSet", map);
//        return  request;
//    }
//    public  static  String permissionSetTwo(String type,String isMsgRemind,String isVoiceRemind,String isVideoRemind){
//        dealMap();
//        map.put("type",type);
//        map.put("isMsgRemind",isMsgRemind);
//        map.put("isVoiceRemind",isVoiceRemind);
//        map.put("isVideoRemind",isVideoRemind);
//        String request = WebUrl.request("PersonCenter", "permissionSet", map);
//        return  request;
//    }
//    public  static  String permissionSetThr(String type,String isSnoShow,String isQrcodeShow){
//        dealMap();
//        map.put("type",type);
//        map.put("isSnoShow",isSnoShow);
//        map.put("isQrcodeShow",isQrcodeShow);
//        String request = WebUrl.request("PersonCenter", "permissionSet", map);
//        return  request;
//    }
//
//    /**
//     * 查询状态接口
//     * @param type
//     * @return
//     */
//    public  static  String getPermissStatu(String type){
//        dealMap();
//        map.put("type",type);
//        String request = WebUrl.request("PersonCenter", "getPermissStatu", map);
//        return  request;
//    }
//    /**
//     * 修改个性签名接口
//     * @param personaSignature
//     * @return
//     */
//    public  static  String upPersonSign(String personaSignature){
//        dealMap();
//        map.put("personaSignature",personaSignature);
//        String request = WebUrl.request("PersonCenter", "upPersonSign", map);
//        return  request;
//    }
//    /**
//     * 修改昵称接口
//     * @param nickName
//     * @return
//     */
//    public  static  String upNickName(String nickName){
//        dealMap();
//        map.put("nickName",nickName);
//        String request = WebUrl.request("PersonCenter", "upNickName", map);
//        return  request;
//    }
//    //    修改备注
//    public  static  String friendRemarkName(String friendsId,String remarkName){
//        dealMap();
//        map.put("friendsId",friendsId);
//        map.put("remarkName",remarkName);
//        String request = WebUrl.request("Contact", "friendRemarkName", map);
//        return  request;
//    }
//    static TreeMap<String, String> mapFile = new TreeMap<>();
//    public  static  String upHeadImg(String headImg){
//        dealMap();
//        map.put("headImg",headImg);
//        String   request = WebUrl.request("PersonCenter", "upHeadImg", map);
//        return  request;
//    }
//
//    /**
//     *修改帐号（只能改一次）
//     * @param newSno
//     * @return
//     */
//    public  static  String upUserSno(String newSno){
//        dealMap();
//        map.put("newSno",newSno);
//        String request = WebUrl.request("PersonCenter", "upUserSno", map);
//        return  request;
//    }
//    /**
//     * 屏蔽黑名单列表接口
//     * @return
//     */
//    public  static  String blackList(String nick_name){
//        dealMap();
//        String request = WebUrl.request("PersonCenter", "blackList", map);
//        return  request;
//    }
//
//    /**
//     *   修改密码
//     * @param oldPwd
//     * @param newPwd
//     * @param comfirmPwd
//     * @return
//     */
//    public  static  String upPassWord(String oldPwd,String newPwd,String comfirmPwd){
//        dealMap();
//        map.put("oldPwd",oldPwd);
//        map.put("newPwd",newPwd);
//        map.put("comfirmPwd",comfirmPwd);
//        String request = WebUrl.request("PersonCenter", "upPassWord", map);
//        return  request;
//    }
//
//    /**
//     * 拉黑列表
//     * @return
//     */
//
//    public  static  String blackList(){
//        dealMap();
//        String request = WebUrl.request("PersonCenter", "blackList", map);
//        return  request;
//    }
//    /**
//     * 拉黑
//     * @param user_id
//     * @return
//     */
//    public  static  String removeBlack(String user_id){
//        dealMap();
//        String request = WebUrl.request("PersonCenter", "removeBlack", map);
//        return  request;
//    }
//    //    检测用户绑定Uid
//    public  static  String coroutineUid(){
//        dealMap();
//        String request = WebUrl.request("PersonCenter", "coroutineUid", map);
//        return  request;
//    }
//
//    /**
//     * 短信验证码修改登录密码接口
//     * @return
//     */
//    public  static  String upPassWordSms(String mobile,String code,String newPwd){
//        dealMap();
//        map.put("mobile",mobile);
//        map.put("code",code);
//        map.put("newPwd",newPwd);
//        String request = WebUrl.request("PersonCenter", "upPassWordSms", map);
//        return  request;
//    }
//
//    /**
//     * 联系人部分
//     */
////    好友列表
//    public  static  String getFriendList(){
//        dealMap();
//        String request = WebUrl.request("Contact", "getFriendList", map);
//        return  request;
//    }
//    //    用户好友分组修改
//    public  static  String friendGroupModify(String friendsId,String groupId){
//        dealMap();
//        map.put("friendsId",friendsId);
//        map.put("groupId",groupId);
//        String request = WebUrl.request("Contact", "friendGroupModify", map);
//        return  request;
//    }
//
//    /**
//     * 群组列表
//     * @return
//     */
//    public  static  String getGroupManage(){
//        dealMap();
//        String request = WebUrl.request("Contact", "getGroupManage", map);
//        return  request;
//    }
//    public  static  String addFriendQrCode(String friendId){
//        dealMap();
//        map.put("friendId",friendId);
//        String request = WebUrl.request("Contact", "addFriendQrCode", map);
//        return  request;
//    }
////    获取群成员资料信息
//    public  static  String getGroupMemberInfo(String memberId,String groupOfId){
//        dealMap();
//        map.put("memberId",memberId);
//        map.put("groupOfId",groupOfId);
//        String request = WebUrl.request("Contact", "getGroupMemberInfo", map);
//        return  request;
//    }
////    获取群成员列表
//    public  static  String getGroupMemberList(String groupOfId){
//        dealMap();
//        map.put("groupOfId",groupOfId);
//        String request = WebUrl.request("Contact", "getGroupMemberList", map);
//        return  request;
//    }
//    //    搜索好友接口
//    public  static  String searchInfo(String  wx_sno,String type){
//        dealMap();
//        map.put("wxSno",wx_sno);
//        map.put("type",type);
//        String request = WebUrl.request("Contact", "searchInfo", map);
//        return  request;
//    }
//    //获取联系人好友列表接口
//    public  static  String getGroupWebInfo(){
//        dealMap();
//        String request = WebUrl.request("Contact", "getGroupWebInfo", map);
//        return  request;
//    }
//
////    邀请入群
//    public  static  String groupInvitationf(String groupId,String friendIds){
//        dealMap();
//        map.put("groupId",groupId);
//        map.put("friendIds",friendIds);
//        String request = WebUrl.request("Contact", "groupInvitationf", map);
//        return  request;
//    }
////    邀请好友入群 列表接口
//    public  static  String groupInvitationfFriend(String groupId){
//        dealMap();
//        map.put("groupId",groupId);
//        String request = WebUrl.request("Contact", "groupInvitationfFriend", map);
//        return  request;
//    }
//    public  static  String delGroupMember(String groupOfId,String userIds){
//        dealMap();
//        map.put("groupOfId",groupOfId);
//        map.put("userIds",userIds);
//
//        String request = WebUrl.request("Contact", "groupInvitationf", map);
//        return  request;
//    }
//    public  static  String createdUserGroup(String fromUserIds,String groupName,String groupImg){
//        dealMap();
//        map.put("fromUserIds",fromUserIds);
//        map.put("groupName",groupName);
//        map.put("groupImg",groupImg);
//        String request = WebUrl.request("Contact", "createdUserGroup", map);
//        return  request;
//    }
//    /**
//     * 搜索相关
//     */
//    public  static  String searchDetailInfo(String group_id){
//        dealMap();
//        map.put("groupId",group_id);
//        String request = WebUrl.request("Contact", "searchDetailInfo", map);
//        return  request;
//    }
//    //    删除好友接口
//    public  static  String deleteFriend(String friendsId){
//        dealMap();
//        map.put("friendsId",friendsId);
//        String request = WebUrl.request("Contact", "deleteFriend", map);
//        return  request;
//    }
////    屏蔽好友接口
//    public  static  String shieldFriend(String friendsId,String type){
//        dealMap();
//        map.put("friendsId",friendsId);
//        map.put("type",type);
//        String request = WebUrl.request("Contact", "shieldFriend", map);
//        return  request;
//    }
////    退出群聊
//    public  static  String outGroupChat(String groupOfId){
//        dealMap();
//        map.put("groupOfId",groupOfId);
//        String request = WebUrl.request("Contact", "outGroupChat", map);
//        return  request;
//    }
////    群主转让群接口
//    public  static  String transferGroupOf(String groupOfId,String transferUserId){
//        dealMap();
//        map.put("groupOfId",groupOfId);
////        要转让给群成员的user_id
//        map.put("transferUserId",transferUserId);
//        String request = WebUrl.request("Contact", "outGroupChat", map);
//        return  request;
//    }
//    //    首页
//    public  static  String getUserRelation(){
//        dealMap();
//        String request = WebUrl.request("Chat", "getUserRelation", map);
//        return  request;
//    }
//    //    用户同步聊天记录(首页聊天列表)
//    public  static  String messageQueue(){
//        dealMap();
//        String request = WebUrl.request("Chat", "messageQueue", map);
//        return  request;
//    }
//
//    //    消息通知列表页面接口
//    public  static  String messageList(){
//        dealMap();
//        String request = WebUrl.request("Contact", "messageList", map);
//        return  request;
//    }
//    //同意添加好友接口
//    public  static  String agreeFriend(String messageId){
//        dealMap();
//        map.put("messageId",messageId);
//        String request = WebUrl.request("Contact", "agreeFriend", map);
//        return  request;
//    }
//    //    消息通知详情页面接口
//    public  static  String messageDetail(String pushMsgId){
//        dealMap();
//        map.put("pushMsgId",pushMsgId);
//        String request = WebUrl.request("Contact", "messageDetail", map);
//        return  request;
//    }
//    //加好友通知---拒绝、删除接口 1为拒绝操作 2为删除操作
//    public  static  String refuseFriend(String messageId,String type){
//        dealMap();
//        map.put("messageId",messageId);
//        map.put("type",type);
//        String request = WebUrl.request("Contact", "refuseFriend", map);
//        return  request;
//    }
//
//
//    /**
//     * 用户分组管理接口
//     * @param  type 1 表示获取好友分组管理 ;2 表示获取好友群分组管理
//     * @return
//     */
//    public  static  String groupManageInfo(String type){
//        dealMap();
//        map.put("type",type);
//        String request = WebUrl.request("Contact", "groupManageInfo", map);
//        return  request;
//    }
////    添、删、改好友分组or群分组分组管理接口
////    group_type  1表示添加好友分组 2表示添加群分组
////     type   1添加分组 2修改分组 3删除分组
////    group_name type为1和2时必传
////    分组主键id message_id type为2和3时必传
//
//    public  static  String addFriendGroup(String group_type,String type,String group_name,String message_id){
//        dealMap();
//        map.put("groupType",group_type);
//        map.put("type",type);
//        if (!StrUtils.isEmpty(group_name))
//            map.put("groupName",group_name);
//        if (!StrUtils.isEmpty(message_id))
//            map.put("messageId",message_id);
//        String request = WebUrl.request("Contact", "addFriendGroup", map);
//        return  request;
//    }
//    public  static  String moveGroupSort(String groupInfo){
//        dealMap();
//        map.put("groupInfo",groupInfo);
//        String request = WebUrl.request("Contact", "moveGroupSort", map);
//        return  request;
//    }
//    //    获取好友详细信息
//    public  static  String getFriendInfo(String friendId){
//        dealMap();
//        map.put("friendId",friendId);
//        String request = WebUrl.request("Contact", "getFriendInfo", map);
//        return  request;
//    }
//    //    添加好友
//    public  static  String addFriend(String friend_sno,String group_id,String remark,String remark_name){
//        dealMap();
//        putData("friendSno",friend_sno);
////        选择要放置的分组id
//        if (!StrUtils.isEmpty(group_id))
//            map.put("groupId",group_id);
//        if (!StrUtils.isEmpty(remark))
//            map.put("remark",remark);
//        if (!StrUtils.isEmpty(remark_name))
//            map.put("remarkName",remark_name);
//        String request = WebUrl.request("Contact", "addFriend", map);
//        return  request;
//    }
//
//
//    public  static  void putData(String key,String value){
//        if (!StrUtils.isEmpty(key)&&!StrUtils.isEmpty(value))
//        map.put(key,value);
//    }
//    public  static  String addGroupOf(String groupCode,String remark){
//        dealMap();
//        map.put("groupCode",groupCode);
////        选择要放置的分组id
//        if (!StrUtils.isEmpty(remark))
//            map.put("remark",remark);
//        String request = WebUrl.request("Contact", "addGroupOf", map);
//        return  request;
//    }
//    /**
//     * 聊天 用户在线群聊 - 给服务器发送消息
//     * @param groupId
//     * @return
//     */
//    public  static  String groupSend(String groupId,String message,String messageType,String requestTime){
//        dealMap();
//        map.put("groupId",groupId);
//        map.put("message",message);
//        map.put("messageType",messageType);
//        map.put("requestTime",requestTime);//发送的时间戳
//        String request = WebUrl.request("Chat", "groupSend", map);
//        return  request;
//    }
//    /**
//     * 聊天 用户在线私聊 - 给服务器发送消息
//     * @param friendsId
//     * @return
//     */
//    public  static  String privateSend(String friendsId,String message,String messageType,String requestTime){
//        dealMap();
//        map.put("friendsId",friendsId);
//        map.put("message",message);
//        map.put("messageType",messageType);
//        map.put("requestTime",requestTime);//发送的时间戳
//        String request = WebUrl.request("Chat", "privateSend", map);
//        return  request;
//    }
//
//    public  static  String messageObtain(String friendsId){
//        dealMap();
//        map.put("friendsId",friendsId);
//        String request = WebUrl.request("Chat", "messageObtain", map);
//        return  request;
//    }
////    TreeMap<String, String> map = new TreeMap<>();
////    map.put(userIdParameter,userInfo.getUser_id());
////    String request = WebUrl.request("Ws", "bindUid", map);
//}
