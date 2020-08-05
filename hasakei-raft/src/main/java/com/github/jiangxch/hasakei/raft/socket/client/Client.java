package com.github.jiangxch.hasakei.raft.socket.client;


import com.github.jiangxch.hasakei.common.util.JsonUtil;
import com.github.jiangxch.hasakei.raft.core.RaftConfig;
import com.github.jiangxch.hasakei.raft.socket.client.channelHandler.ClientChannelInboundHandler;
import com.github.jiangxch.hasakei.raft.socket.model.BaseMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:58
 */
@Slf4j
public class Client {
    private String clientName;
    private String host;
    private Integer port;
    private Channel clientChannel;
    private NioEventLoopGroup group;
    private RaftConfig raftConfig;
    private Bootstrap bootstrap;

    public Client(String host, Integer port, String clientName, RaftConfig raftConfig) {
        this.host = host;
        this.port = port;
        this.clientName = clientName;
        this.raftConfig = raftConfig;
    }

    public boolean start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        this.group = group;
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 订长编码,拒绝粘包,半包
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        //处理client接收的消息
                        pipeline.addLast(new ClientChannelInboundHandler(Client.this));
                    }
                });
        boolean res = reConnect();
        return res;
    }

    public boolean reConnect() throws InterruptedException {
        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            clientChannel = future.channel();
            // 关闭阻塞
            future.channel().closeFuture();
            log.info("Raft {} start successfully,address={}:{}", this.clientName, host, port);
            return true;
        } catch (Exception e) {
            log.warn("Raft Node failed to connect another Node,address={}:{},ex={}", host, port, e.getMessage());
            if (e instanceof InterruptedException) {
                throw e;
            } else {
                return false;
            }
        }
    }

    public void close() {
        group.shutdownGracefully();
    }

    public void sendMsg(BaseMessage msg) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(msg.getType());
        String body = JsonUtil.toJson(msg.getData());
        buffer.writeBytes(body.getBytes(Charset.defaultCharset()));
        clientChannel.writeAndFlush(buffer);
    }
}
