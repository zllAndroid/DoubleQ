package com.mding.chatfeng.about_base.web_base;

import android.content.Context;
import android.util.Log;

import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.SignForXm6leefunJava;
import com.mding.chatfeng.about_base.deal_application.DealFriendAdd;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.MD5Utils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.TreeMap;

public class SplitWeb {
    //    是否需要首页叠加消息条数   true显示
    public  boolean IS_CHAT_Zero= true;

    public  boolean IS_SET_PERSON_HEAD=false;
    public  String IS_CHAT= "00";
    public  String IS_SET_ACTIVITY= "00";
    public  String IS_CHAT_GROUP= "33";
    public  String USER_TOKEN= "";
    public  String MOBILE= "";
    public  String PSW= "";
    public  String WX_SNO= "";
    public  String PERSON_SIGN= "";
    public  String QR_CODE= "";
    public  String NICK_NAME= "";
    public  String USER_ID="";
    public  String USER_HEADER= "";
    public  String WS_REQUEST= "";
    public  String HTTP_REQUEST= "";

    private static SplitWeb splitWeb;
    // 构造函数必须是私有的 这样在外部便无法使用 new 来创建该类的实例
    private SplitWeb() {}
    /**
     * 单一实例
     */
    public synchronized static SplitWeb getSplitWeb(){
        if (splitWeb == null) {
            splitWeb= new SplitWeb();
        }
        return splitWeb;
    }
    //固定第一级请求
//    public  final String PreRequest= "http://192.168.4.131:40003/";
//    public  final String PreRequest= "http://39.108.3.131/server_api.php";

//    public  final String PreRequest= "http://192.168.4.210:30092/V2_0_0/LoginController/getClusterIp";
    //正式
    public  final String PreRequest= "http://47.112.116.35:30092/V2_0_0/LoginController/getClusterIp";
//    public  final String PreRequest= "http://120.78.92.225:9092/V2_0_0/LoginController/getClusterIp";
//    public  final String PreRequest= "http://192.168.4.68/server_api.php";

//      SPUtils.put(this,"header",record.getHeadImg());
//            SPUtils.put(this,"name",record.getNickName());

    public  String getUserHeader() {
        USER_HEADER=(String ) SPUtils.get(HelpUtils.getACt(),"header","");
        return USER_HEADER;
    }
    //    public  String getUserId() {
//        if(StrUtils.isEmpty(USER_ID))
//            USER_ID=(String ) SPUtils.get(MyApplication.getAppContext(), AppAllKey.USER_ID_KEY,"");
//        Log.e("getUserId","getUserId="+USER_ID);
//        return USER_ID;
//    }
    public  String getUserId() {
//        if(StrUtils.isEmpty(USER_ID))
        USER_ID= BaseApplication.getaCache().getAsString(AppAllKey.USER_ID_KEY);
        return USER_ID;
    }
    public  String getUserToken() {
//        if(StrUtils.isEmpty(USER_TOKEN))
        USER_TOKEN= BaseApplication.getaCache().getAsString(AppAllKey.USER_Token);
//            USER_TOKEN= (String )SPUtils.get(BaseApplication.getAppContext(),AppAllKey.USER_Token,"");
        return USER_TOKEN;
    }
    //     SPUtils.put(this,AppConfig.TYPE_WS_REQUEST,dataLogin.getServerIp());
    public  String getWsRequest(Context mContext) {
        if(StrUtils.isEmpty(WS_REQUEST))
            WS_REQUEST=(String ) SPUtils.get(mContext, AppConfig.TYPE_WS_REQUEST,"");
        return WS_REQUEST;
    }
    public  String getUserMobile() {
        if(StrUtils.isEmpty(MOBILE))
            MOBILE=(String ) SPUtils.get(BaseApplication.getAppContext(), AppConfig.TYPE_PHONE,"");
        return MOBILE;
    }
    public  String getUserPSW() {
        if(StrUtils.isEmpty(PSW))
            PSW=(String ) SPUtils.get(BaseApplication.getAppContext(), AppConfig.TYPE_PSW,"");
        return PSW;
    }
    public  String getName() {
        if(StrUtils.isEmpty(NICK_NAME))
            NICK_NAME=(String )SPUtils.get(BaseApplication.getAppContext(), AppConfig.TYPE_NAME,"");
        return NICK_NAME;
    }
    public  String getSign() {
        if(StrUtils.isEmpty(PERSON_SIGN))
            PERSON_SIGN=(String )SPUtils.get(BaseApplication.getAppContext(), AppConfig.TYPE_SIGN,"");
        return PERSON_SIGN;
    }


    public  String getNiName() {

        NICK_NAME=(String ) SPUtils.get(HelpUtils.getACt(),"name","");

        return NICK_NAME;
    }

    /**
     * WebSocket 请求参数
     */
    TreeMap<String, String> map = new TreeMap<>();

    /**
     * ------------------------------------------------------------------------------------------------------------------------------
     * Http请求参数
     */
    public  ArrayList<String> mList=new ArrayList<String>();
//   测试
//    public  String URL = "http://192.168.4.133:5052/LoginController/";
//    public  String WebSocket_URL = "ws://192.168.4.133:5053";

    //   Http 正式
//    public  String WebSocket_URL = "ws://192.168.4.133:9093";
//    public  String URL = "http://192.168.4.133:9092/LoginController/";
//    外网
//    public  String WebSocket_URL = "ws://192.168.4.133:5053";
//    public  String URL = "http://192.168.4.133:5052/LoginController/";

//    public  String WebSocket_URL = "ws://192.168.4.55:9093";
//    public  String WebSocket_URL = "ws://192.168.4.55:9093";
//    public  String URL = "http://192.168.4.55:9092/LoginController/";
//192.168.4.48

//    public  String WebSocket_URL = "ws://192.168.4.55:9093";
//    public  String Error_URL = "http://39.108.3.131/check_ip_statu.php?";

    //    public  String WebSocket_URL = "ws://192.168.4.48:9093";
//    public  String URL = "http://192.168.4.48:9092/LoginController/";
//    public  String WebSocket_URL = "ws://119.23.229.66:9093";

    public  String HttpURL = "";
    //    public  String HttpURL = "192.168.4.55:9092";
    private  String getURL() {
        if(StrUtils.isEmpty(HttpURL))
            HttpURL= BaseApplication.getaCache().getAsString(AppConfig.TYPE_URL);
        return HttpURL+"/V2_0_0";
    }
    public  String getFirstUrl() {
        return BaseApplication.getaCache().getAsString(AppConfig.TYPE_URL);
    }
    public  String getURLRequest() {
        return getURL()+"/LoginController/";
//        return "http://"+getURL()+"/LoginController/";
    }
    public  String getURLMessage() {
        return getURL()+"/MyAMQPTaskController/";
//        return "http://"+getURL()+"/LoginController/";
    }
    public  String getURLCustom(String http) {
        return http+"/V2_0_0/MyAMQPTaskController/";
//        return "http://"+getURL()+"/LoginController/";
    }

    public  String loginIn(String mobile, String password){
        mList.clear();
        mList.add("sno="+mobile);
        mList.add("password="+getMd5(password));
//        mList.add("mobile="+"13860169273");
//        mList.add("password="+"1234566");
        return getURLRequest()+"loginIn?"+ SignForXm6leefunJava.getSing(mList);
    }
    public  String addrPort(){
        mList.clear();
        return getURLRequest()+"addrPort?"+ SignForXm6leefunJava.getSing(mList);
    }
    //    public  String errorRequest(String url){
//        return Error_URL+"url="+url;
//    }
    public  String register(String mobile, String password,String code){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("password="+getMd5(password));
        mList.add("code="+code);
//        mList.add("mobile="+"13860169273");
//        mList.add("password="+"1234566");
        return getURLRequest()+"register?"+SignForXm6leefunJava.getSing(mList);
    }
    public  String  getMd5(String psw)
    {
        String md5ResultCode =  MD5Utils.encryptMD5((MD5Utils.encryptMD5((psw)) + "mding"));
        return  md5ResultCode;
    }
    //    （旧短信验证码接口  1支付 2提现 3修改支付密码 4修改登录密码 5注册 6修改手机号）
    //    新短信验证码接口  1为登录 2为注册 3为修改登录密码 4修改绑定手机号（旧） 5修改绑定手机号（新）
    public  String smsCode(String mobile, String type){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("type="+type);
        return getURLRequest()+"getSmsCode?"+ SignForXm6leefunJava.getSing(mList);
    }
    public  String smsLogin(String mobile, String code){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("code="+code);
        return getURLRequest()+"smsLogin?"+ SignForXm6leefunJava.getSing(mList);

    }
    public  String contactsListHttp(){
        mList.clear();
        if(StrUtils.isEmpty(USER_ID))
            getUserId();
        mList.add("userId="+USER_ID);
        if(StrUtils.isEmpty(USER_TOKEN))
            getUserToken();
        mList.add("token="+USER_TOKEN);
        return getURLRequest()+"contactsList?"+ SignForXm6leefunJava.getSing(mList);
//        String request = WebUrl.request("Contact", "contactsList", map);
//        return  request;
    }
    public  String appUpdateHttp(String nowVs){
        mList.clear();
        mList.add("nowVs="+nowVs);
        mList.add("device="+"1");
        return getURLRequest()+"appUpdate?"+ SignForXm6leefunJava.getSing(mList);
//        String request = WebUrl.request("Contact", "contactsList", map);
//        return  request;
    }
    //    http://192.168.4.133/MyAMQPTaskController/pullMergeChat?
//    合并拉取离线消息http
    public  String pullMergeChat(String http){
        mList.clear();
        mList.add("userId="+ getUserId());
        return getURLCustom(http)+"pullMergeChat?"+ SignForXm6leefunJava.getSing(mList);
//        String request = WebUrl.request("Contact", "contactsList", map);
//        return  request;
    }
//    public  String setHeadImg(String nickName,String headImg){
//        mList.clear();
//        mList.add("userId="+ getUserId());
//        mList.add("nickName="+ nickName);
//        mList.add("headImg="+ headImg);
//        return getURLRequest()+"setHeadImg?"+ SignForXm6leefunJava.getSing(mList);
//        String request = WebUrl.request("Contact", "contactsList", map);
//        return  request;

        public  TreeMap<String, String> setHeadImg(String nickName, String headImg){
            dealMap();
            putData("nickName",nickName);
            putData("headImg",headImg);
            return  map;

    }
    /**
     * -------------------------------------------------------------------------------------------------------------------------------------------
     */

    /**
     * web
     * 绑定uid接口
     * @return
     */
    public  String userIdParameter= "userId";
    public  String userTokenParameter= "token";

    public    String bindUid(){
        dealMap();
//        Log.e("WebSocketLib","userIdParameter="+userIdParameter+"-------------------------------------"+USER_ID);
        String request = WebUrl.request("PersonCenter", "bindUid", map);
        return  request;
    }
    //    合并拉取离线消息
//    public    String pullMergeChat(){
//        dealMap();
////        Log.e("WebSocketLib","userIdParameter="+userIdParameter+"-------------------------------------"+USER_ID);
//        String request = WebUrl.request("MyAMQPTask", "pullMergeChat", map);
//        return  request;
//    }
    public    String WsBindUid(String userId ,String token){
        map.clear();
        putData(userIdParameter,userId);
        putData(userTokenParameter,token);
//        Log.e("WebSocketLib","userIdParameter="+userIdParameter+"-------------------------------------"+USER_ID);
        String request = WebUrl.request("PersonCenter", "bindUid", map);
        return  request;
    }
    public    String kickUid(){
        dealMap();
//        Log.e("WebSocketLib","userIdParameter="+userIdParameter+"-------------------------------------"+USER_ID);
        String request = WebUrl.request("PersonCenter", "kickUid", map);
        return  request;
    }
    //    确认私聊回执接口
    public    String messageConfirmReceipt(String messageList){
        dealMap();
        putData("messageList",messageList);
        String request = WebUrl.request("MyAMQPTask", "messageConfirmReceipt", map);
        return  request;
    }
    //    确认群聊回执
    public    String messageGroupConfirmReceipt(String messageList){
        dealMap();
        putData("messageList",messageList);
        String request = WebUrl.request("MyAMQPTask", "messageGroupConfirmReceipt", map);
        return  request;
    }

    private  void dealMap() {
        map.clear();
        if(StrUtils.isEmpty(USER_ID))
            getUserId();
        putData(userIdParameter,USER_ID);
        if(StrUtils.isEmpty(USER_TOKEN))
            getUserToken();
        putData(userTokenParameter,USER_TOKEN);
    }

    //注册时设置头像接口
//    public  String setHeadImg(String nickName, String headImg){
//        dealMap();
//        putData("nickName",nickName);
//        putData("headImg",headImg);
//        String request = WebUrl.request("Login", "setHeadImg", map);
//        return  request;
//    }
    //    版本检测
    public  String appUpdate(String nowVs){
        dealMap();
        putData("nowVs",nowVs);
        putData("device","1");
        String request = WebUrl.request("Login", "appUpdate", map);
        return  request;
    }
    public  String contactsList(){
        dealMap();
        String request = WebUrl.request("Contact", "contactsList", map);
        return  request;
    }
    /**
     * 我的资料接口
     * @return
     */
    public    String personalCenter(){
        dealMap();
        String request = WebUrl.request("PersonCenter", "personalCenter", map);
        return  request;
    }
    // 更换绑定  旧手机号码接口
    public    String replaceMobileOld(String mobile,String code){
        dealMap();
        putData("mobile",mobile);
        putData("code",code);
        String request = WebUrl.request("PersonCenter", "replaceMobileOld", map);
        return  request;
    }
    // 更换绑定  新手机号码接口
    public    String replaceMobileNew(String mobile,String code){
        dealMap();
        putData("mobile",mobile);
        putData("code",code);
        String request = WebUrl.request("PersonCenter", "replaceMobileNew", map);
        return  request;
    }
    /**
     * 名片分享、消息提醒、隐私设置接口
     * @param type  1为名片分享 2为消息提醒 3为隐私设置 (修改类型接)
     *当type==1时
     *   是否分享名片 1: is_share  0为禁止被分享1为允许 2为需开启验证
     * 当type==2时
     *              0为否 1为
     *   （是否接收新消息提醒 is_voice_remind）（是否接收语音消息提示 is_voice_remind）
     *              （是否接收视频消息提示 is_video_remind）
     * 当type==3时
     *    是否帐号显示 is_sno_show  （是否二维码显示 is_qrcode_show）
     *可变长参数 String ... type
     */
    public    String permissionSetOne(String type,String isShare){
        dealMap();
        putData("type",type);
        putData("isShare",isShare);
        String request = WebUrl.request("PersonCenter", "permissionSet", map);
        return  request;
    }
    public    String permissionSetTwo(String type,String isMsgRemind,String isVoiceRemind,String isVideoRemind){
        dealMap();
        putData("type",type);
        putData("isMsgRemind",isMsgRemind);
        putData("isVoiceRemind",isVoiceRemind);
        putData("isVideoRemind",isVideoRemind);
        String request = WebUrl.request("PersonCenter", "permissionSet", map);
        return  request;
    }
    public    String permissionSetThr(String type,String isSnoShow,String isQrcodeShow){
        dealMap();
        putData("type",type);
        putData("isSnoShow",isSnoShow);
        putData("isQrcodeShow",isQrcodeShow);
        String request = WebUrl.request("PersonCenter", "permissionSet", map);
        return  request;
    }

    /**
     * 查询状态接口
     * @param type
     * @return
     */
    public    String getPermissStatu(String type){
        dealMap();
        putData("type",type);
        String request = WebUrl.request("PersonCenter", "getPermissStatu", map);
        return  request;
    }
    /**
     * 修改个性签名接口
     * @param personaSignature
     * @return
     */
    public    String upPersonSign(String personaSignature){
        dealMap();
        putData("personaSignature",personaSignature);
        String request = WebUrl.request("PersonCenter", "upPersonSign", map);
        return  request;
    }
    /**
     * 修改昵称接口
     * @param nickName
     * @return
     */
    public    String upNickName(String nickName){
        dealMap();
        putData("nickName",nickName);
        String request = WebUrl.request("PersonCenter", "upNickName", map);
        return  request;
    }
    //    修改备注
    public    String friendRemarkName(String friendsId,String remarkName){
        dealMap();
        putData("friendsId",friendsId);
        putData("remarkName",remarkName);
        String request = WebUrl.request("Contact", "friendRemarkName", map);
        return  request;
    }
    TreeMap<String, String> mapFile = new TreeMap<>();
    public    String upHeadImg(String headImg){
        dealMap();
        putData("headImg",headImg);
        String   request = WebUrl.request("PersonCenter", "upHeadImg", map);
        return  request;
    }

    /**
     *修改帐号（只能改一次）
     * @param newSno
     * @return
     */
    public    String upUserSno(String newSno){
        dealMap();
        putData("newSno",newSno);
        String request = WebUrl.request("PersonCenter", "upUserSno", map);
        return  request;
    }
    /**
     * 屏蔽黑名单列表接口
     * @return
     */
    public    String blackList(String nick_name){
        dealMap();
        String request = WebUrl.request("PersonCenter", "blackList", map);
        return  request;
    }

    /**
     *   修改密码
     * @param oldPwd
     * @param newPwd
     * @param comfirmPwd
     * @return
     */
    public    String upPassWord(String oldPwd,String newPwd,String comfirmPwd){
        dealMap();
        putData("oldPwd",getMd5(oldPwd));
        putData("newPwd",getMd5(newPwd));
        putData("comfirmPwd",getMd5(comfirmPwd));
        String request = WebUrl.request("PersonCenter", "upPassWord", map);
        return  request;
    }

    /**
     * 拉黑列表
     * @return
     */
    public    String blackList(){
        dealMap();
        String request = WebUrl.request("PersonCenter", "blackList", map);
        return  request;
    }
    /**
     * 拉黑
     * @return
     */
    public    String removeBlack(){
        dealMap();
        String request = WebUrl.request("PersonCenter", "removeBlack", map);
        return  request;
    }
    //    检测用户绑定Uid
    public    String coroutineUid(){
        dealMap();
        String request = WebUrl.request("PersonCenter", "coroutineUid", map);
        return  request;
    }

    /**
     * 短信验证码修改登录密码接口
     * @return
     */
    public    String upPassWordSms(String mobile,String code,String newPwd){
        dealMap();
        putData("mobile",mobile);
        putData("code",code);
        putData("newPwd",getMd5(newPwd));
        String request = WebUrl.request("PersonCenter", "upPassWordSms", map);
        return  request;
    }

    /**
     * 联系人部分
     */
//    好友列表
    public    String getFriendList(){
        dealMap();
        String request = WebUrl.request("Contact", "getFriendList", map);
        return  request;
    }
    //    用户好友分组修改
    public    String friendGroupModify(String friendsId,String groupId){
        dealMap();
        putData("friendsId",friendsId);
        putData("groupId",groupId);
        String request = WebUrl.request("Contact", "friendGroupModify", map);
        return  request;
    }
    //    用户群分组管理修改
    public    String groupOfGroupModify(String groupOfId,String groupId){
        dealMap();
        putData("groupOfId",groupOfId);
        putData("groupId",groupId);
        String request = WebUrl.request("Contact", "groupOfGroupModify", map);
        return  request;
    }

    //    私聊popWindow
    public    String privateSendInterface(String friendId){
        dealMap();
        putData("friendId",friendId);
        String request = WebUrl.request("Contact", "privateSendInterface", map);
        return  request;
    }

    //    群聊popWindow
    public    String groupSendInterface(String groupId){
        dealMap();
        putData("groupId",groupId);
        String request = WebUrl.request("Contact", "groupSendInterface", map);
        return  request;
    }

    /**
     * 群组列表
     * @return
     */
    public    String getGroupManage(){
        dealMap();
        String request = WebUrl.request("Contact", "getGroupManage", map);
        return  request;
    }
    /**
     * 修改群名
     * @return
     */
    public    String upGroupName(String groupId,String groupName){
        dealMap();
        putData("groupId",groupId);
        putData("groupName",groupName);
        String request = WebUrl.request("Contact", "upGroupName", map);
        return  request;
    }
    /**
     * 修改群头像
     * @return
     */
    public    String upGroupHeadImg(String groupId,String headImg){
        dealMap();
        putData("groupId",groupId);
        putData("headImg",headImg);
        String request = WebUrl.request("Contact", "upGroupHeadImg", map);
        return  request;
    }
    /**
     * 添加好友二维码
     * @return
     */
    public    String addFriendQrCode(String friendId){
        dealMap();
        putData("friendId",friendId);
        String request = WebUrl.request("Contact", "addFriendQrCode", map);
        return  request;
    }
    /**
     * 添加群二维码
     * @return
     */
    public    String addGroupOfQrCode(String groupSno){
        dealMap();
        putData("groupSno",groupSno);
        String request = WebUrl.request("Contact", "addGroupOfQrCode", map);
        return  request;
    }
    //    获取群成员资料信息
    public    String getGroupMemberInfo(String memberId,String groupOfId){
        dealMap();
        putData("memberId",memberId);
        putData("groupOfId",groupOfId);
        String request = WebUrl.request("Contact", "getGroupMemberInfo", map);
        return  request;
    }
    //    获取群成员列表
    public    String getGroupMemberList(String groupOfId){
        dealMap();
        putData("groupOfId",groupOfId);
        String request = WebUrl.request("Contact", "getGroupMemberList", map);
        return  request;
    }
    public    String getGroupMemberList(String groupOfId,String verificationMD5){
        dealMap();
        putData("groupOfId",groupOfId);
        putData("verificationMD5",verificationMD5);
        String request = WebUrl.request("Contact", "getGroupMemberList", map);
        return  request;
    }
    //    获取群主转让群成员列表
    public    String getTransterGroupMemberInfo(String groupOfId){
        dealMap();
        putData("groupOfId",groupOfId);
        String request = WebUrl.request("Contact", "getTransterGroupMemberInfo", map);
        return  request;
    }
    //    搜索好友接口
    public    String searchInfo(String  wx_sno,String type){
        dealMap();
        putData("wxSno",wx_sno);
        putData("type",type);
        String request = WebUrl.request("Contact", "searchInfo", map);
        return  request;
    }
    //获取联系人好友列表接口
    public    String getGroupWebInfo(){
        dealMap();
        String request = WebUrl.request("Contact", "getGroupWebInfo", map);
        return  request;
    }
    //    删除群成员列表
    public    String delGroupMemberList(String groupId){
        dealMap();
        putData("groupId",groupId);
        String request = WebUrl.request("Contact", "delGroupMemberList", map);
        return  request;
    }

    //    邀请入群
    public    String groupInvitationf(String groupId,String friendIds){
        dealMap();
        putData("groupId",groupId);
        putData("friendIds",friendIds);
        String request = WebUrl.request("Contact", "groupInvitationf", map);
        return  request;
    }
    //    邀请好友入群 列表接口
    public    String groupInvitationfFriend(String groupId){
        dealMap();
        putData("groupId",groupId);
        String request = WebUrl.request("Contact", "groupInvitationfFriend", map);
        return  request;
    }
    public    String delGroupMember(String groupOfId,String userIds){
        dealMap();
        putData("groupOfId",groupOfId);
        putData("userIds",userIds);

        String request = WebUrl.request("Contact", "delGroupMember", map);
        return  request;
    }
    public    String createdUserGroup(String fromUserIds,String groupName,String groupImg){
        dealMap();
        putData("fromUserIds",fromUserIds);
        putData("groupName",groupName);
        putData("groupImg",groupImg);
        String request = WebUrl.request("Contact", "createdUserGroup", map);
        return  request;
    }
    //    编辑群公告信息接口
    public    String editGroupNotice(String chatGroupId,String content){
        dealMap();
        putData("chatGroupId",chatGroupId);
        putData("content",content);
        String request = WebUrl.request("Contact", "editGroupNotice", map);
        return  request;
    }
    //    查看群公告信息接口
    public    String groupNoticeInfo(String chatGroupId){
        dealMap();
        putData("chatGroupId",chatGroupId);
        String request = WebUrl.request("Contact", "groupNoticeInfo", map);
        return  request;
    }
    /**
     * 回复备注信息接口
     */
    public    String messageReply(String pushMsgId, String message){
        dealMap();
        putData("pushMsgId",pushMsgId);
        putData("message",message);
        String request = WebUrl.request("Contact", "messageReply", map);
        return  request;
    }
    /**
     * 搜索相关
     */
    public String searchDetailInfo(String group_id){
        dealMap();
        putData("groupId",group_id);
        String request = WebUrl.request("Contact", "searchDetailInfo", map);
        return  request;
    }
    public String searchDetailInfo(String group_id, String verificationMD5){
        dealMap();
        putData("groupId",group_id);
        putData("verificationMD5",verificationMD5);
        String request = WebUrl.request("Contact", "searchDetailInfo", map);
        return  request;
    }
    //    删除好友接口
    public    String deleteFriend(String friendsId){
        dealMap();
        putData("friendsId",friendsId);
        String request = WebUrl.request("Contact", "deleteFriend", map);
        return  request;
    }
    //    屏蔽好友接口
    public    String shieldFriend(String friendsId,String type){
        dealMap();
        putData("friendsId",friendsId);
        putData("type",type);
        String request = WebUrl.request("Contact", "shieldFriend", map);
        return  request;
    }
    //    用户设置好友置顶
    public    String topFriend(String friendsId,String type){
        dealMap();
        putData("friendsId",friendsId);
        putData("type",type);
        String request = WebUrl.request("Contact", "topFriend", map);
        return  request;
    }
    //    用户设置群消息提示（消息免打扰）
    public    String setUserGroupDisturb(String groupOfId,String disturbType){
        dealMap();
        putData("groupOfId",groupOfId);
        putData("disturbType",disturbType);
        String request = WebUrl.request("Contact", "setUserGroupDisturb", map);
        return  request;
    }
    public    String setUserGroupAssistant(String groupOfId,String assistantType){
        dealMap();
        putData("groupOfId",groupOfId);
        putData("assistantType",assistantType);
        String request = WebUrl.request("Contact", "setUserGroupAssistant", map);
        return  request;
    }
    //    消息免打扰
    public    String disturbFriend(String friendsId,String type){
        dealMap();
        putData("friendsId",friendsId);
        putData("type",type);
        String request = WebUrl.request("Contact", "disturbFriend", map);
        return  request;
    }
    //    退出群聊
    public    String outGroupChat(String groupOfId){
        dealMap();
        putData("groupOfId",groupOfId);
        String request = WebUrl.request("Contact", "outGroupChat", map);
        return  request;
    }
    //    群主转让群接口
    public    String transferGroupOf(String groupOfId,String transferUserId){
        dealMap();
        putData("groupOfId",groupOfId);
//        要转让给群成员的user_id
        putData("transferUserId",transferUserId);
        String request = WebUrl.request("Contact", "transferGroupOf", map);
        return  request;
    }
    //    首页
    public    String getUserRelation(){
        dealMap();
        String request = WebUrl.request("Chat", "getUserRelation", map);
        return  request;
    }
    //    用户同步聊天记录(首页聊天列表)
    public    String messageQueue(){
        dealMap();
        String request = WebUrl.request("Chat", "messageQueue", map);
        return  request;
    }

    //    消息通知列表页面接口
    public    String messageList(){
        dealMap();
        String request = WebUrl.request("Contact", "messageList", map);
        return  request;
    }
    //同意添加好友接口
    public    String agreeFriend(String messageId){
        dealMap();
        putData("messageId",messageId);
        String request = WebUrl.request("Contact", "agreeFriend", map);
        return  request;
    }
    //    消息通知详情页面接口
    public    String messageDetail(String pushMsgId){
        dealMap();
        putData("pushMsgId",pushMsgId);
        String request = WebUrl.request("Contact", "messageDetail", map);
        return  request;
    }
    //加好友通知---拒绝、删除接口 1为拒绝操作 2为删除操作
    public    String refuseFriend(String messageId,String type){
        dealMap();
        putData("messageId",messageId);
        putData("type",type);
        String request = WebUrl.request("Contact", "refuseFriend", map);
        return  request;
    }
    //设置群名片接口
    public  String setGroupCarteModify(String groupofId,String carteName){
        dealMap();
        putData("groupOfId",groupofId);
        putData("carteName",carteName);
        String request = WebUrl.request("Contact", "setGroupCarteModify", map);
        return request;
    }

    public    String coroutineKeep(){
        dealMap();
        String request = WebUrl.request("ChatScript", "coroutineKeep", map);
        return  request;
    }
    /**
     * 用户分组管理接口
     * @param  type 1 表示获取好友分组管理 ;2 表示获取好友群分组管理
     * @return
     */
    public    String groupManageInfo(String type){
        dealMap();
        putData("type",type);
        String request = WebUrl.request("Contact", "groupManageInfo", map);
        return  request;
    }
//    添、删、改好友分组or群分组分组管理接口
//    group_type  1表示添加好友分组 2表示添加群分组
//     type   1添加分组 2修改分组 3删除分组
//    group_name type为1和2时必传
//    分组主键id message_id type为2和3时必传

    public    String addFriendGroup(String group_type,String type,String group_name,String message_id){
        dealMap();
        putData("groupType",group_type);
        putData("type",type);
        if (!StrUtils.isEmpty(group_name))
            putData("groupName",group_name);
        if (!StrUtils.isEmpty(message_id))
            putData("messageId",message_id);
        String request = WebUrl.request("Contact", "addFriendGroup", map);
        return  request;
    }
    public    String moveGroupSort(String groupInfo){
        dealMap();
        putData("groupInfo",groupInfo);
        String request = WebUrl.request("Contact", "moveGroupSort", map);
        return  request;
    }
    //    获取好友详细信息
    public    String getFriendInfo(String friendId){
        dealMap();
        putData("friendId",friendId);
        String request = WebUrl.request("Contact", "getFriendInfo", map);
        return  request;
    }
    //    用户好友分组列表
    public    String friendGroupList(String friendsId){
        dealMap();
        putData("friendsId",friendsId);
        String request = WebUrl.request("Contact", "friendGroupList", map);
        return  request;
    }
    //    用户群分组列表
    public    String groupOfGroupList(String groupOfId){
        dealMap();
        putData("groupOfId",groupOfId);
        String request = WebUrl.request("Contact", "groupOfGroupList", map);
        return  request;
    }
    //    添加好友
    public    String addFriend(String friend_sno,String group_id,String remark,String remark_name){
        dealMap();
        putData("friendSno",friend_sno);
//        选择要放置的分组idgroupOfGroupList
        if (!StrUtils.isEmpty(group_id))
            putData("groupId",group_id);
        if (!StrUtils.isEmpty(remark))
            putData("remark",remark);
        if (!StrUtils.isEmpty(remark_name))
            putData("remarkName",remark_name);
        String request = WebUrl.request("Contact", "addFriend", map);
        return  request;
    }


    public    void putData(String key,String value){
        if (!StrUtils.isEmpty(key)&&!StrUtils.isEmpty(value))
            map.put(key,value);
    }
    public    String addGroupOf(String groupCode,String remark){
        dealMap();
        putData("groupCode",groupCode);
//        选择要放置的分组id
        if (!StrUtils.isEmpty(remark))
            putData("remark",remark);
        String request = WebUrl.request("Contact", "addGroupOf", map);
        return  request;
    }
    /**
     * 聊天 用户在线群聊 - 给服务器发送消息
     * @param groupId
     * @return
     */
    public    String groupSend(String groupId,String message,String messageType,String requestTime){
        dealMap();
        putData("groupId",groupId);
        putData("message",message);
        putData("messageType",messageType);
        putData("requestTime",requestTime);//发送的时间戳
//        String request = WebUrl.request("Chat", "groupSend", map);
        String request = WebUrl.request("MyAMQPTask","sendGroupChat", map);
        return  request;
    }
    /**
     * 聊天 用户在线私聊 - 给服务器发送消息
     * @param friendsId
     * @return
     */
    public String privateSend(String friendsId,String message,String messageType,String requestTime){
        dealMap();
        putData("friendsId",friendsId);
        putData("message",message);
        putData("messageType",messageType);
        putData("requestTime",requestTime);//发送的时间戳
        String request = WebUrl.request("MyAMQPTask", "sendPrivateChat", map);
//        String request = WebUrl.request("Chat","2.0.0", "privateSend", map);
        return  request;
    }

    /**
     * 聊天 发送图片前检测
     */
    public String getQueryRepetition(String type, String fileMd5){
        dealMap();
        putData("type",type);
        putData("fileMd5",fileMd5);
        String request = WebUrl.request("MyAMQPTask", "getQueryRepetition", map);
//        String request = WebUrl.request("Chat","2.0.0", "privateSend", map);
        return  request;
    }
    public    String messageObtain(String friendsId){
        dealMap();
        putData("friendsId",friendsId);
        String request = WebUrl.request("Chat", "messageObtain", map);
        return  request;
    }
}
