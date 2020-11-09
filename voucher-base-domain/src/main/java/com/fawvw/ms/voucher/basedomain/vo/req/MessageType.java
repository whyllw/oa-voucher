package com.fawvw.ms.voucher.basedomain.vo.req;

/**
 * @program: one-app-server
 * @description: 赞与收藏和想去，报名，审核不通过，取消车友活动的对象类型
 * @author: yiyang.xu
 * @create: 2019-06-18 16:08
 **/

public enum MessageType {
    //点赞圈子的帖子
    LIKE_CIRCLE,
    //点赞圈子的评论
    LIKE_CIRCLE_COMMENT,
    //圈子帖子加精
    ESSENTIAL_CIRCLE,
    //点赞问答帖子的评论
    LIKE_QUESTION_COMMENT,
    //点赞车友活动的评论
    LIKE_ACTIVITY_COMMENT,
    //点赞官方活动的评论
    LIKE_OFFICIAL_COMMENT,
    //收藏的提问
    BOOKMARK_QUESTION,
    //评论圈子帖子
    COMMENT_CIRCLE,
    //评论圈子的评论
    COMMENT_CIRCLE_COMMENT,
    //评论问答帖子
    COMMENT_QUESTION,
    //评论问答的评论
    COMMENT_QUESTION_COMMENT,
    //评论官方活动帖子
    COMMENT_OFFICIAL,
    //评论官方活动的评论
    COMMENT_OFFICIAL_COMMENT,
    //评论车友活动帖子
    COMMENT_ACTIVITY,
    //评论车友活动评论
    COMMENT_ACTIVITY_COMMENT,
    //报名车友活动
    ENROLL_ACTIVITY,
    //报名官方活动
    ENROLL_OFFICIAL,
    //车友活动想去
    WANNA_GO_ACTIVITY,
    //官方活动想去
    WANNA_GO_OFFICIAL,
    //车友活动取消
    CANCEL_ACTIVITY,
    //车友活动删除
    DELETE_ACTIVITY,
    //车友活动审核不通过
    AUDIT_ACTIVITY_NO_PASS,
    //圈子审核不通过
    AUDIT_CIRCLE_NO_PASS,
    //消息提醒-采纳通知
    NOTICE_QUESTION_ADOPTED,
    //消息提醒-反馈通知
    NOTICE_FEEDBACK,
    //920开奖
    LUCKY_DRAW,
    //920上线
    ACTIVITY920_ONLINE,
    //输入车模地址
    ACTIVITY920_INPUT_ADDRESS,
    //卡片被领取
    ACTIVITY920_CARD_ACQUIRED,
    //卡片退回
    ACTIVITY920_CARD_RECYCLE,
    //预约服务（4小时）通知
    RESERVATION_NOTICE,
    //取车通知.送车通知,送达通知
    PICKUP_CAR,
    //优惠券通知
    VOUCHER,
    //道路救援
    ARRIVE_RESCUE_MISSION,
    BEGIN_RESCUE_MISSION,
    //消息提醒-委托书
    NOTICE_PROXY_STATEMENT,
    //消息提醒-结算单
    NOTICE_STATEMENT_OF_ACCOUNT,
    //消息提醒-2000万活动中奖提醒
    TWENTY_ACTIVITY_WINNING,
    //消息提醒-启动充电
    NOTICE_START_CHARGE,
}
