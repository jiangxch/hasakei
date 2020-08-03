package com.github.jiangxch.hasakei.raft.model;

/**
 * 进行leader选举的timer
 *
 * @author: jiangxch
 * @date: 2020/8/2 下午10:19
 */
public class LeaderElectorTimer {
    /**
     * 开始进行leader elector
     */
    public void start() {

    }

    /**
     * 当收到leader的心跳,或者其他情况
     * 调用该方法重置follow节点的
     * leader elector的等待时间
     */
    public void reset() {

    }

    /**
     * 停止当前节点进行的 leader elector
     */
    public void stop() {

    }
}
