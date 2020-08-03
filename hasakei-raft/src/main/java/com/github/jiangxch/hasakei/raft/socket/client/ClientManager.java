package com.github.jiangxch.hasakei.raft.socket.client;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 管理多个client
 * @author: jiangxch
 * @date: 2020/7/26 下午5:13
 */
public class ClientManager {
    private List<Client> clientList;

    public ClientManager(InetSocketAddress[] clusterAddresses) {
        for (int i = 0; i < clusterAddresses.length; i++) {
            InetSocketAddress address = clusterAddresses[i];
            Client client = new Client(address.getHostName(), address.getPort(),"Client-" + i);
            clientList.add(client);
        }
    }

    public void start() throws InterruptedException {
        for (Client client : clientList) {
            client.start();
        }
    }
}
