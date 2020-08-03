package com.github.jiangxch.hasakei.raft.core;

import lombok.Data;

import java.net.InetSocketAddress;

/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:33
 */
@Data
public class RaftConfig {
    /** 集群中全部节点IP port */
    private String clusterIpPorts;
    /** 自身节点的ip port */
    private String selfIpPort;
    public InetSocketAddress[] getClusterAddresses() {
        String[] ipPorts = clusterIpPorts.split(",");
        InetSocketAddress[] socketAddresses = new InetSocketAddress[ipPorts.length];
        for (int i = 0; i < ipPorts.length; i++) {
            String[] split = ipPorts[i].split(":");
            String ip = split[0];
            String port = split[1];
            socketAddresses[i] = new InetSocketAddress(ip,Integer.parseInt(port));
        }
        return socketAddresses;
    }

    public InetSocketAddress getSelfInetAddress() {
        String[] split = selfIpPort.split(":");
        String ip = split[0];
        String port = split[1];
        return new InetSocketAddress(ip,Integer.parseInt(port));
    }
}
