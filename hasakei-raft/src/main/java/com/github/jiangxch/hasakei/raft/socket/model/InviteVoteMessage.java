package com.github.jiangxch.hasakei.raft.socket.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: jiangxch
 * @date: 2020/8/4 下午11:35
 */
public class InviteVoteMessage {
    @Data
    public static class Request implements Serializable {
        private String fromAddress;
    }

    @Data
    public static class Response implements Serializable {
        private String sendAddress;
        /** 1:已经给其他节点投票了 2:投票给发送者节点 */
        private Integer voteType;
    }
}
