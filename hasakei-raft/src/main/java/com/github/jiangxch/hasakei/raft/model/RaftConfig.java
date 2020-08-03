package com.github.jiangxch.hasakei.raft.model;

import com.github.jiangxch.hasakei.raft.enums.NodeStatus;
import lombok.Data;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:33
 */

public class RaftConfig {
    private String clusterIpPorts;

    public InetSocketAddress[] getClusterAddresses() {
        return new InetSocketAddress[0];
    }

    public InetSocketAddress getSlfInetAddress() {
        return null;
    }
}
