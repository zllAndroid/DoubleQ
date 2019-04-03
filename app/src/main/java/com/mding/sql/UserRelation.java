package com.mding.sql;

/**
 * 项目：DoubleQ
 * 文件描述：好友关系表
 * 作者：zll
 * 创建时间：2019/4/3 0003
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class UserRelation {

    public static String _USER_ID ="userId";
    String friendId;//好友id

    String groupId;//对应好友分组id 关联表t_group_manage

    String status;    //拉黑状态  2为拉黑 1为好友关系状态

    String remarkName;    //    好友备注名字
    String shieldType;    //屏蔽状态 1否 2是
    String queueType;    //删除消息   1否/2是
    String disturbType;    // 消息免打扰 1否 2是
    String nickName;    //    昵称
     String topType;//置顶状态
    //    头像
    String headImg;
    //    消息时间
    String created;//创建时间
    String modified;//修改时间

    String recordTime;//清除聊天记录的时间

    public static String toUserRelationEntryString(){
        return
                        "friendId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "groupId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "status" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "remarkName" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "shieldType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "queueType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "disturbType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "nickName" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "topType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "headImg" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "created" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "modified" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "recordTime" + TotalEntry.TEXT_TYPE ;
    }
}
