package com.github.jiangxch.hasakei.raft.enums;

import lombok.Getter;

/**
 * @author: jiangxch
 * @date: 2020/8/5 上午12:13
 */
public enum  MessageType {
    INVITE_VOTE_REQUEST(1,"邀请投票"),
    INVITE_VOTE_RESPONSE(2,"投票响应"),
    LEADER_HEART_BEAT_REQUEST(3,"leader发送心跳请求"),
    LEADER_HEART_BEAT_RESPONSE(3,"leader发送心跳响应"),
    ;
    public Integer type;
    public String msg;

    MessageType(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
