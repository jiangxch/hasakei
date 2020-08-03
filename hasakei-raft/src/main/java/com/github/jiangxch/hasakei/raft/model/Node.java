package com.github.jiangxch.hasakei.raft.model;


import com.github.jiangxch.hasakei.raft.enums.NodeStatus;
import com.github.jiangxch.hasakei.raft.socket.client.ClientManager;
import com.github.jiangxch.hasakei.raft.socket.server.Server;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:35
 */
@Slf4j
public class Node {

    private RaftConfig raftConfig;
    private Server socketServer;
    private ClientManager clientManager;
    private volatile NodeStatus nodeStatus = NodeStatus.FOLLOW;
    private LeaderElectorTimer leaderElectorTimer;

    public Node(RaftConfig raftConfig) {
        this.raftConfig = raftConfig;
        init();
    }

    private void init() {
        InetSocketAddress address = raftConfig.getSlfInetAddress();
        socketServer = new Server(address.getHostName(),address.getPort());

        InetSocketAddress[] clusterAddresses = raftConfig.getClusterAddresses();
        clientManager = new ClientManager(clusterAddresses);

        leaderElectorTimer = new LeaderElectorTimer();
    }

    public void start() throws InterruptedException {
        socketServer.start();
        clientManager.start();
        log.info("Raft Node start successfully...");
    }
}