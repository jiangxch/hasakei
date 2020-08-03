package com.github.jiangxch.hasakei.raft.socket.client.channelHandler;

import com.github.jiangxch.hasakei.raft.socket.client.Client;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author: jiangxch
 * @date: 2020/7/26 下午1:26
 */
public class ClientChannelInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Client client;

    public ClientChannelInboundHandler(Client client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }
}
