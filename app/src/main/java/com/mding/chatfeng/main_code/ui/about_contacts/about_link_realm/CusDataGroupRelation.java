package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//好友表数据
public class CusDataGroupRelation extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由用户id和群id合并组成）

    String groupId;
    String userId;
    String nickName;   //  名称
    String wxSno;   //  账号
    String headImg;   //  头像地址
    String cardName;   //  群名片
    String qrcode;   //  二维码
    String sign;   //  个性签名
    String created;   //  创建时间
    String modified;   //  修改时间
    String msgTime;   //  最后发送消息时间


}
