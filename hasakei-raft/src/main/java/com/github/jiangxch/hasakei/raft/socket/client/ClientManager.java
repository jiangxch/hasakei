package com.github.jiangxch.hasakei.raft.socket.client;

import com.github.jiangxch.hasakei.raft.core.RaftConfig;
import com.github.jiangxch.hasakei.raft.enums.MessageType;
import com.github.jiangxch.hasakei.raft.socket.model.BaseMessage;
import com.github.jiangxch.hasakei.raft.socket.model.InviteVoteMessage;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 管理多个client
 *
 * @author: jiangxch
 * @date: 2020/7/26 下午5:13
 */
public class ClientManager {
    private List<Client> clientList = new ArrayList<>();
    private RaftConfig raftConfig;

    public ClientManager(InetSocketAddress[] clusterAddresses, RaftConfig raftConfig) {
        this.raftConfig = raftConfig;
        for (int i = 0; i < clusterAddresses.length; i++) {
            InetSocketAddress address = clusterAddresses[i];
            if (address.equals(raftConfig.getSelfInetAddress())) {
                continue;
            }
            Client client = new Client(address.getHostName(), address.getPort(), "Client-" + i, raftConfig);
            clientList.add(client);
        }
    }

    public void start() throws InterruptedException {
        for (Client client : clientList) {
            boolean startSuccess;
            do {
                TimeUnit.SECONDS.sleep(raftConfig.clientReConnectionWaitingSecondTime);
                startSuccess = client.start();
            } while (!startSuccess);
        }
    }

    private void sendMsg(BaseMessage message) {
        clientList.forEach(client -> {
            client.sendMsg(message);
        });
    }

    public void sendInviteVoteMsg() {
        InviteVoteMessage inviteVoteMessage = new InviteVoteMessage();
        inviteVoteMessage.setFromAddress(raftConfig.getSelfIpPort());
        BaseMessage<InviteVoteMessage> msg = new BaseMessage<>(MessageType.INVITE_VOTE_REQUEST.type, inviteVoteMessage);
        sendMsg(msg);
    }
}
