package com.github.jiangxch.hasakei.raft.socket.server.channelHandler;

import com.github.jiangxch.hasakei.raft.socket.server.Server;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: jiangxch
 * @date: 2020/7/26 下午3:57
 */
public class ServerChannelInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Server server;

    public ServerChannelInboundHandler(Server server) {
        this.server = server;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }
}
