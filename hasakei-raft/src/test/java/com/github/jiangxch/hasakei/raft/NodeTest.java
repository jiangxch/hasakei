package com.github.jiangxch.hasakei.raft;

import com.github.jiangxch.hasakei.raft.core.Node;
import com.github.jiangxch.hasakei.raft.core.RaftConfig;
import org.junit.Test;

/**
 * @author: jiangxch
 * @date: 2020/8/3 下午10:48
 */
public class NodeTest {
    private RaftConfig c1 = new RaftConfig();
    private RaftConfig c2 = new RaftConfig();
    private RaftConfig c3 = new RaftConfig();
    {
        String clusterIpPorts = "127.0.0.1:9090,127.0.0.1:9091,127.0.0.1:9092";
        c1.setClusterIpPorts(clusterIpPorts);
        c1.setSelfIpPort("127.0.0.1:9090");

        c2.setClusterIpPorts(clusterIpPorts);
        c2.setSelfIpPort("127.0.0.1:9091");

        c3.setClusterIpPorts(clusterIpPorts);
        c3.setSelfIpPort("127.0.0.1:9092");
    }

    @Test
    public void testTwoNode() throws InterruptedException {
        Node n1 = new Node(c1);
        Node n2 = new Node(c2);
        Node n3 = new Node(c3);

        n1.start();
        n2.start();
        n3.start();
    }
}
