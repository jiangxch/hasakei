package com.github.jiangxch.hasakei.raft.core;

import com.github.jiangxch.hasakei.raft.enums.NodeStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 进行leader选举的timer
 *
 * @author: jiangxch
 * @date: 2020/8/2 下午10:19
 */
@Slf4j
public class LeaderElectorTimer {
    private Node node;

    private Thread workerThread;

    public LeaderElectorTimer(Node node) {
        this.node = node;
        int leaderElectorWaitingTimeMillisecond = node.getRaftConfig().leaderElectorWaitingTimeMillisecond;
        Worker worker = new Worker(leaderElectorWaitingTimeMillisecond,node);
        this.workerThread = new Thread(worker, "LeaderElectorTimer");
    }

    /**
     * 开始进行leader elector
     */
    public void start() {
        workerThread.start();
        log.info("Raft Node start leader elector timeout successfully...");
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

    static class Worker implements Runnable{
        /**
         * 0:初始状态 1:leader elector timeout
         * 2:等待其他node回复
         */
        private volatile int state = 0;
        private int leaderElectorWaitingTimeMillisecond;
        private Node node;

        public Worker(int leaderElectorWaitingTimeMillisecond, Node node) {
            this.leaderElectorWaitingTimeMillisecond = leaderElectorWaitingTimeMillisecond;
            this.node = node;
        }

        @Override
        public void run() {
            while (true) {
                if (state == 0) {
                    continue;
                }
                if (state == 1) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(leaderElectorWaitingTimeMillisecond);
                        // leader elector 超时,节点进入 candidate 状态
                        node.setNodeStatus(NodeStatus.CANDIDATE);
                        log.info("Node enter candidate status");
                        // 通过客户端向其他节点发送vote请求
                        node.getClientManager().sendInviteVoteMsg();
                    } catch (InterruptedException e) {
                        // pass
                    }
                }
            }
        }

        private void setState(int state) {
            this.state = state;
        }
    }
}
