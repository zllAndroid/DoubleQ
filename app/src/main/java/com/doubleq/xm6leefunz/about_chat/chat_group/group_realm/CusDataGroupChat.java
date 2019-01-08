package com.doubleq.xm6leefunz.about_chat.chat_group.group_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
//群聊自定义realm群成员头像数据
public class CusDataGroupChat extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由群id和用户id合并组成）

    String friendId;//好友id

    String groupId;

    String imgPath;

    String time;

    String headImg;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
