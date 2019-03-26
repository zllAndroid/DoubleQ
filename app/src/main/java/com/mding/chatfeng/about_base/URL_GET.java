//package com.mding.chatfeng.about_base;
//
//import java.util.ArrayList;
//
///**
// * Created by Administrator on 2017/11/6 0006.
// */
//
//public class URL_GET {
////    public static String AD_TIME= "time";
////    public static String USER_HEAD_URL= "";
////    public static String USER_PHONE= "";
////    public static String USER_COIN= "";
////    public static String USER_NI_NAME= "";
////    public static String USER_ID= "";
////    public static String IMEI= "";
//    public static String USER_TOKEN= "";
//    public static String MOBILE= "";
//    public static String WX_SNO= "";
//    public static String PERSON_SIGN= "";
//    public static String QR_CODE= "";
//    public static String NICK_NAME= "";
//    public static String USER_ID= "";
//
//    public static ArrayList<String> mList=new ArrayList<String>();
////    正式
//    public static String URL = "http://192.168.4.133:9092/IMController/";
////    public static String URL = "http://www.xm6leefun.cn:3030/wwwroot/app/index.php?api_act=";
//    //    public static String isLogin(){
////    public static String isLogin(String mobile, String password){
////        mList.clear();
////        mList.add("mobile="+mobile);
////        mList.add("password="+password);
//////        mList.add("mobile="+"13860169273");
//////        mList.add("password="+"1234566");
////        return URL+"userLogin"+SignForXm6leefunJava.getSing(mList);
////    }
//
//    public static String loginIn(String mobile, String password){
//        mList.clear();
//        mList.add("sno="+mobile);
//        mList.add("password="+password);
////        mList.add("mobile="+"13860169273");
////        mList.add("password="+"1234566");
//        return URL+"loginIn?"+SignForXm6leefunJava.getSing(mList);
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
//
//
//
////    public static String comfirmRegister(String mobile, String vcode, String password, String yaoqingma){
////        mList.clear();
////        mList.add("mobile="+mobile);
////        mList.add("vcode="+vcode);
////        mList.add("password="+password);
////        mList.add("yaoqingma="+yaoqingma);
////        return URL+"comfirmRegister"+SignForXm6leefun.getSing(mList);
////    }
////    public static String changeMobile(String mobile, String vcode){
////        mList.clear();
////        mList.add("mobile="+mobile);
////        mList.add("vcode="+vcode);
////        return URL+"changeMobile"+SignForXm6leefun.getSing(mList);
////    }
//    //    短信验证码接口  1支付 2提现 3修改支付密码 4修改登录密码 5注册 6修改手机号
//    public static String smsCode(String mobile, String type){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("type="+type);
//        return URL+"smsCode"+SignForXm6leefun.getSing(mList);
//    }
////    public static String intoNetSmsConfirm(String pay_sign, String verify_code, String id){
////        mList.clear();
////        mList.add("pay_sign="+pay_sign);
////        mList.add("verify_code="+verify_code);
////        mList.add("id="+id);
////        return URL+"intoNetSmsConfirm"+SignForXm6leefun.getSing(mList);
////    }
////// 重新发送验证码
////    public static String reSendInNetSMs(String pay_sign, String valid, String cvn2){
////        mList.clear();
////        mList.add("pay_sign="+pay_sign);
////        mList.add("valid="+valid);
////        mList.add("cvn2="+cvn2);
////        return URL+"reSendInNetSMs"+SignForXm6leefun.getSing(mList);
////    }
////    // 输入新手机号接口
////    public static String inputNewMobile(String old_mobile, String new_mobile){
////        mList.clear();
////        mList.add("old_mobile="+old_mobile);
////        mList.add("new_mobile="+new_mobile);
////        return URL+"inputNewMobile"+SignForXm6leefun.getSing(mList);
////    }
//////    激活黑卡接口
////    public static String activationBlack(String card_no){
////        mList.clear();
////        mList.add("card_no="+card_no);
////        return URL+"activationBlack"+SignForXm6leefun.getSing(mList);
////    }
////    public static String appUpdate(){
////        mList.clear();
////        return URL+"appUpdate"+SignForXm6leefun.getSing(mList);
////    }
////    //找回登录密码接口
////    public static String forgetLoginPassword(String mobile, String vcode, String password, String repassword){
////        mList.clear();
////        mList.add("mobile="+mobile);
////        mList.add("vcode="+vcode);
////        mList.add("password="+password);
////        mList.add("repassword="+repassword);
////        return URL+"forgetLoginPassword"+SignForXm6leefun.getSing(mList);
////    }
////
////    public static String resetLoginPassword(String old_password, String new_password, String repassword){
////        mList.clear();
////        mList.add("old_password="+old_password);
////        mList.add("new_password="+new_password);
////        mList.add("repassword="+repassword);
////        return URL+"resetLoginPassword"+SignForXm6leefun.getSing(mList);
////    }
////    //交易查询
////    public static String selectTradQuery(){
////        mList.clear();
////        return URL+"selectTradQuery"+SignForXm6leefun.getSing(mList);
////    }
//////首页
////
////    public static String myRate(){
////        mList.clear();
////        return URL+"myRate"+SignForXm6leefun.getSing(mList);
////    }
////
////    //    找回登录密码页面接口
////    public static String forgetLoginPasswordWeb(){
////        mList.clear();
////        return URL+"forgetLoginPasswordWeb"+SignForXm6leefun.getSing(mList);
////    }
////    //    下调费率页面
////    public static String downRate(){
////        mList.clear();
////        return URL+"downRate"+SignForXm6leefun.getSing(mList);
////    }
////    //    帮助好友注册页面
////    public static String helpRegisterWeb(){
////        mList.clear();
////        return URL+"helpRegisterWeb"+SignForXm6leefun.getSing(mList);
////    }
////    //    二维码推广
////    public static String userPromote(){
////        mList.clear();
////        return URL+"userPromote"+SignForXm6leefun.getSing(mList);
////    }
////    public static String wxShareContent(String share_type){
////        mList.clear();
////        mList.add("share_types="+share_type);
////        return URL+"wxShareContent"+SignForXm6leefun.getSing(mList);
////    }
////    public static String allRate(){
////        mList.clear();
////        return URL+"allRate"+SignForXm6leefun.getSing(mList);
////    }
////    //    下发地址数据
////    public static String getAddressData(){
////        mList.clear();
////        return URL+"getAddressData"+SignForXm6leefun.getSing(mList);
////    }
////    public static String addressManage(){
////        mList.clear();
////        return URL+"addressManage"+SignForXm6leefun.getSing(mList);
////    }
////    //    设置默认地址
////    public static String setDefaultAddress(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"setDefaultAddress"+SignForXm6leefun.getSing(mList);
////    }
////    //    删除地址
////    public static String deleteAddress(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"deleteAddress"+SignForXm6leefun.getSing(mList);
////    }
////    public static String editAddress(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"editAddress"+SignForXm6leefun.getSing(mList);
////    }
////    //    修改保存地址接口
////    public static String updateAddress(String user_name, String user_mobile, String pca_address, String cata_address, String type, String id){
////        mList.clear();
////        mList.add("user_name="+user_name);
////        mList.add("user_mobile="+user_mobile);
////        mList.add("pca_address="+pca_address);
////        mList.add("cata_address="+cata_address);
////        mList.add("type="+type);
////        mList.add("id="+id);
////        return URL+"updateAddress"+SignForXm6leefun.getSing(mList);
////    }
////    //    (name,phone,addr,isDefault,details);
////    public static String addAddress(String user_name, String user_mobile, String pca_address, String cata_address, String type){
////        mList.clear();
////        mList.add("user_name="+user_name);
////        mList.add("user_mobile="+user_mobile);
////        mList.add("pca_address="+pca_address);
////        mList.add("cata_address="+cata_address);
////        mList.add("type="+type);
////        return URL+"addAddress"+SignForXm6leefun.getSing(mList);
////    }
////
////    public static String shopIndex(){
////        mList.clear();
////        return URL+"shopIndex"+SignForXm6leefun.getSing(mList);
////    }
////    public static String detailShop(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"detailShop"+SignForXm6leefun.getSing(mList);
////    }
////
////    public static String orderDetail(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"orderDetail"+SignForXm6leefun.getSing(mList);
////    }
////    public static String confirmReceive(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"confirmReceive"+SignForXm6leefun.getSing(mList);
////    }
////    public static String selectLogistics(String order_id){
////        mList.clear();
////        mList.add("order_id="+order_id);
////        return URL+"selectLogistics"+SignForXm6leefun.getSing(mList);
////    }
////    public static String confirmBuy(String id, String bank_id){
////        mList.clear();
////        mList.add("id="+id);
////        mList.add("bank_id="+bank_id);
////        mList.add("pay_type="+"BANKPAY");
////
////        return URL+"confirmBuy"+SignForXm6leefun.getSing(mList);
////    }
////    public static String buyShop(String shop_id, String address_id, String num, String all_money){
////        mList.clear();
////        mList.add("shop_id="+shop_id);
////        mList.add("address_id="+address_id);
////        mList.add("num="+num);
////        mList.add("all_money="+all_money);
////        return URL+"buyShop"+SignForXm6leefun.getSing(mList);
////    }
////
////    /**
////     * 钱包相关
////     * cashWithDrawal
////     * @return
////     */
////    //    我的钱包页面接口
////    public static String myWallet(String page){
////        mList.clear();
////        mList.add("page="+page);
////        return URL+"myWallet"+SignForXm6leefun.getSing(mList);
////    }
////    //佣金转出到余额页面
////    public static String commissionWeb(){
////        mList.clear();
////        return URL+"commissionWeb"+SignForXm6leefun.getSing(mList);
////    }
//////    佣金转余额---确认转出
////    public static String commissionOut(String out_money){
////        mList.clear();
////        mList.add("out_money="+out_money);
////        return URL+"commissionOut"+SignForXm6leefun.getSing(mList);
////    }
//////    public static String orderDetail(String id){
//////        mList.clear();
//////        mList.add("id="+id);
//////        return URL+"orderDetail"+SignForXm6leefun.getSing(mList);
//////    }
////    public static String commissionOutDetail(String type, String page){
////        mList.clear();
////        mList.add("type="+type);
////        mList.add("page="+page);
////        return URL+"commissionOutDetail"+SignForXm6leefun.getSing(mList);
////    }
//////    我的订单
////    public static String myOrderRecord(String type){
////        mList.clear();
////        mList.add("type="+type);
////        return URL+"myOrderRecord"+SignForXm6leefun.getSing(mList);
////    }
////    public static String indexNews(String page){
////        mList.clear();
////        mList.add("page="+page);
////        return URL+"indexNews"+SignForXm6leefun.getSing(mList);
////    }
////    public static String indexNews(String page, String classification){
////        mList.clear();
////        mList.add("page="+page);
////        mList.add("classification="+classification);
////        return URL+"indexNews"+SignForXm6leefun.getSing(mList);
////    }
//////    佣金转出--输入支付密码
////    public static String inputPayPassword(String out_money, String order_id, String pay_password){
////        mList.clear();
////        mList.add("out_money="+out_money);
////        mList.add("order_id="+order_id);
////        mList.add("pay_password="+pay_password);
////        return URL+"inputPayPassword"+SignForXm6leefun.getSing(mList);
////    }
////
////
////    //    余额提现
////    public static String cashWithDrawal(String money, String bank_id, String password){
////        mList.clear();
////        mList.add("money="+money);
////        mList.add("bank_id="+bank_id);
////        mList.add("password="+password);
////        return URL+"cashWithDrawal"+SignForXm6leefun.getSing(mList);
////    }
////    //    商户通道支付选择列表
////    public static String channelList(){
////        mList.clear();
////        return URL+"channelList"+SignForXm6leefun.getSing(mList);
////    }
////
////    public static String getArticleForType(String article_type){
////        mList.clear();
////        mList.add("article_type="+article_type);
////        return URL+"getArticleForType"+SignForXm6leefun.getSing(mList);
////    }
////    //    激活通道接口
////    public static String modifyNet(String channel_code){
////        mList.clear();
////        mList.add("channel_code="+channel_code);
////        return URL+"modifyNet"+SignForXm6leefun.getSing(mList);
////    }
////    //    验证交易密码接口
////    public static String valPayPassword(String pay_password){
////        mList.clear();
////        mList.add("pay_password="+pay_password);
////        return URL+"valPayPassword"+SignForXm6leefun.getSing(mList);
////    }
////    //    输入短信确认支付
////    public static String smsConfirm(String order_id, String code){
////        mList.clear();
////        mList.add("order_id="+order_id);
////        mList.add("code="+code);
////        return URL+"smsConfirm"+SignForXm6leefun.getSing(mList);
////    }
////    //    重新发送短信
////    public static String resendSms(String order_id){
////        mList.clear();
////        mList.add("order_id="+order_id);
////        return URL+"resendSms"+SignForXm6leefun.getSing(mList);
////    }
////    //    同名快捷支付请求接口
////    public static String sendUrl(String order_amt, String id, String credit_id, String passageway_code){
////        mList.clear();
////        mList.add("order_amt="+order_amt);
////        mList.add("id="+id);
////        mList.add("credit_id="+credit_id);
////        mList.add("passageway_code="+passageway_code);
////        return URL+"sendUrl"+SignForXm6leefun.getSing(mList);
////    }
////    //    升级支付接口
////    public static String immediatePayment(String money, String new_type, String bank_id){
////        mList.clear();
////        mList.add("money="+money);
////        mList.add("new_type="+new_type);
////        mList.add("pay_type="+"BANKPAY");
////        mList.add("bank_id="+bank_id);
////        return URL+"immediatePayment"+SignForXm6leefun.getSing(mList);
////    }
////
////    /**
////     * 我的页面相关
////     * cashWithDrawal
////     * @return
////     */
//////    我的 页面接口
////    public static String myWeb(){
////        mList.clear();
////        return URL+"myWeb"+SignForXm6leefun.getSing(mList);
////    }
////    public static String indexAnnouncement(){
////        mList.clear();
////        return URL+"indexAnnouncement"+SignForXm6leefun.getSing(mList);
////    }
////    //    我的商户 myMerchant
////    public static String myMerchant(){
////        mList.clear();
////        return URL+"myMerchant"+SignForXm6leefun.getSing(mList);
////    }
////    //    意见反馈
////    public static String feedBack(){
////        mList.clear();
////        return URL+"feedBack"+SignForXm6leefun.getSing(mList);
////    }
////    //    账户详情接口
////    public static String userInfo(){
////        mList.clear();
////        return URL+"userInfo"+SignForXm6leefun.getSing(mList);
////    }
////
////    //    我的结算卡
////    public static String bankCardList(String bankType){
////        mList.clear();
////        mList.add("bankType="+bankType);
////        return URL+"bankCardList"+SignForXm6leefun.getSing(mList);
////    }
////
////    //    智能还款  首页  aiTotalBill  还款计划数据汇总
////    public static String aiTotalBill(){
////        mList.clear();
////        return URL+"aiTotalBill"+SignForXm6leefun.getSing(mList);
////    }
////    public static String aiPlanList(String status, String page){
////        mList.clear();
////        mList.add("status="+status);
////        mList.add("page="+page);
////        return URL+"aiPlanList"+SignForXm6leefun.getSing(mList);
////    }
////    public static String aiPayParam(){
////        mList.clear();
////        return URL+"aiPayParam"+SignForXm6leefun.getSing(mList);
////    }
////    //    提交 制定计划
////    public static String aiPlanOverview(String bank_id, String totoal_money, String assure_money, String procedure
////            , String star_time, String end_time){
////        mList.clear();
////        mList.add("bank_id="+bank_id);
////        mList.add("totoal_money="+totoal_money);
////        mList.add("assure_money="+assure_money);
////        mList.add("procedure="+procedure);
////        mList.add("star_time="+star_time);
////        mList.add("end_time="+end_time);
////        return URL+"aiPlanOverview"+SignForXm6leefun.getSing(mList);
////    }
////    //    aiPlanParticulars 智能还款计划详情接口
////    public static String aiPlanParticulars(String order_id, String range){
////        mList.clear();
////        mList.add("order_id="+order_id);
////        mList.add("range="+range);
////        return URL+"aiPlanParticulars"+SignForXm6leefun.getSing(mList);
////    }
//////    智能还款付费接口
////    public static String aiPayMoney(String bank_card_id, String order_id, String assure_money, String procedure, String pay_password){
////        mList.clear();
////        mList.add("bank_card_id="+bank_card_id);
////        mList.add("order_id="+order_id);
////        mList.add("assure_money="+assure_money);
////        mList.add("procedure="+procedure);
////        mList.add("pay_password="+pay_password);
////        return URL+"aiPayMoney"+SignForXm6leefun.getSing(mList);
////    }
////    //    添加银行卡
////    public static String tiedCard(String cardNo, String mobile, String bankId, String address, String payeeBranchBankName){
////        mList.clear();
////        mList.add("bankType="+"1");
////        mList.add("cardNo="+cardNo);
////        mList.add("mobile="+mobile);
////        mList.add("bankId="+bankId);
////        mList.add("address="+address);
////        mList.add("payeeBranchBankName="+payeeBranchBankName);
////        return URL+"tiedCard"+SignForXm6leefun.getSing(mList);
////    }
////    //    实名认证
////    public static String relNameCertification(String userName, String idNumber, String cardNo,
////                                              String mobile, String bankId, String address , String payeeBranchBankName){
////        mList.clear();
////        mList.add("userName="+userName);
////        mList.add("idNumber="+idNumber);
////        mList.add("bankType="+"1");
////        mList.add("cardNo="+cardNo);
////        mList.add("mobile="+mobile);
////        mList.add("bankId="+bankId);
////        mList.add("address="+address);
////        mList.add("payeeBranchBankName="+payeeBranchBankName);
////        return URL+"relNameCertification"+SignForXm6leefun.getSing(mList);
////    }
////    //    添加信用卡
////    public static String tiedCard(String cardNo, String mobile, String bankId){
////        mList.clear();
////        mList.add("bankType="+"2");
////        mList.add("cardNo="+cardNo);
////        mList.add("mobile="+mobile);
////        mList.add("bankId="+bankId);
////        return URL+"tiedCard"+SignForXm6leefun.getSing(mList);
////    }
////    //    个人
////    public static String tiedCardWeb(){
////        mList.clear();
////        return URL+"tiedCardWeb"+SignForXm6leefun.getSing(mList);
////    }
//////    补充信用卡信息页面接口
////    public static String editCreditCard(String id){
////        mList.clear();
////        mList.add("id="+id);
////        return URL+"editCreditCard"+SignForXm6leefun.getSing(mList);
////    }
//////    补充信用卡完整信息接口
////    public static String upCreditCard(String id, String bill_date, String reimbursement_date,
////                                      String quota, String valid, String cvn2){
////        mList.clear();
////        mList.add("id="+id);
////        mList.add("bill_date="+bill_date);
////        mList.add("reimbursement_date="+reimbursement_date);
////        mList.add("quota="+quota);
////        mList.add("valid="+valid);
////        mList.add("cvn2="+cvn2);
////        return URL+"upCreditCard"+SignForXm6leefun.getSing(mList);
////    }
////    //    联系我们页面接口
////    public static String companyInformation(){
////        mList.clear();
////        return URL+"companyInformation"+SignForXm6leefun.getSing(mList);
////    }
////    //    获取银行名字信息
////    public static String getBankName(){
////        mList.clear();
////        return URL+"getBankName"+SignForXm6leefun.getSing(mList);
////    }
////    //    获取银行支行信息
////    public static String getBranchCode(String bankId, String address){
////        mList.clear();
////        mList.add("bankId="+bankId);
////        mList.add("address="+address);
////        return URL+"getBranchCode"+SignForXm6leefun.getSing(mList);
////    }
////    //    输入原交易密码接口（重置交易密码）
////    public static String varifyPassword(String old_paypassword){
////        mList.clear();
////        mList.add("old_paypassword="+old_paypassword);
////        return URL+"varifyPassword"+SignForXm6leefun.getSing(mList);
////    }
////    public static String resetPayPassword(String new_paypassword){
////        mList.clear();
////        mList.add("new_paypassword="+new_paypassword);
////        return URL+"resetPayPassword"+SignForXm6leefun.getSing(mList);
////    }
////    //    /设置交易密码    （一开始）
////    public static String setPayPassword(String new_paypassword){
////        mList.clear();
////        mList.add("new_paypassword="+new_paypassword);
////        return URL+"setPayPassword"+SignForXm6leefun.getSing(mList);
////    }
////    public static String inputNewPayPassword(String new_paypassword){
////        mList.clear();
////        mList.add("new_paypassword="+new_paypassword);
////        return URL+"inputNewPayPassword"+SignForXm6leefun.getSing(mList);
////    }
////    //    找回交易密码（2）
////    public static String forgetPayPassword(String mobile, String vcode){
////        mList.clear();
////        mList.add("mobile="+mobile);
////        mList.add("vcode="+vcode);
////        return URL+"forgetPayPassword"+SignForXm6leefun.getSing(mList);
////    }
//}
