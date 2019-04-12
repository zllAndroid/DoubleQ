package com.mding.sql;

/**
 * 项目：DoubleQ
 * 文件描述：群信息表
 * 作者：ljj
 * 创建时间：2019/4/4
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class GroupInfo {

    public static String _GROUP_ID ="groupId";   //  群信息表的主键
    String groupName;   //  群名称
    String groupSno;   //  群账号
    String groupType;   //  群类型 1：综合
    String groupHeadImg;   //  群头像地址
    String groupInstruction;   //  群介绍说明
    String groupQrcode;   //  群二维码地址
    String status;   //  群设置：1可直接加入 2需审核加入 3 禁止加入
    String maxNum;   //  群上限人数
    String nowNum;   //  群当前添加人数
    String createdId;   //  创建者id
    String created;   //  创建时间
    String modified;   //  修改时间


    public static String toGroupInfoString(){
        return
//                "_ID" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                "group_name" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "groupSno" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "groupType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "groupHeadImg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "groupInstruction" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "groupQrcode" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "status" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "maxNum" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "nowNum" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP   +
                        "createdId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
                        "created" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
                        "modified" + TotalEntry.TEXT_TYPE ;
    }
}
