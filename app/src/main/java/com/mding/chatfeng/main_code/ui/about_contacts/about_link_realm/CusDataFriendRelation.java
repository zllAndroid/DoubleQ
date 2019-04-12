package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//好友关系表数据
public class CusDataFriendRelation extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    String friendId;//好友id

    String userid;
    String groupId;     //对应好友分组id 关联表t_group_manage

    String status;      //拉黑状态  2为拉黑 1为好友关系状态

    String remarkName;  //    好友备注名字
    String shieldType;  //屏蔽状态 1否 2是
    String queueType;   //删除消息   1否/2是
    String disturbType;    // 消息免打扰 1否 2是
    String nickName;    //    昵称
    String topType;     //置顶状态

    //    头像
    String headImg;
    //    消息时间
    String created;//创建时间
    String modified;//修改时间

    String recordTime;//清除聊天记录的时间

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getShieldType() {
        return shieldType;
    }

    public void setShieldType(String shieldType) {
        this.shieldType = shieldType;
    }

    public String getDisturbType() {
        return disturbType;
    }

    public void setDisturbType(String disturbType) {
        this.disturbType = disturbType;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
