package com.github.jiangxch.hasakei.raft.socket.client;


import com.github.jiangxch.hasakei.raft.socket.client.channelHandler.ClientChannelInboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:58
 */
@Slf4j
public class Client {
    private String host;
    private Integer port;
    private Channel clientChannel;
    private NioEventLoopGroup group;

    public Client(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        this.group = group;
        Bootstrap b = new Bootstrap();
        b.group(group)
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
        ChannelFuture future = b.connect(host, port).sync();
        clientChannel = future.channel();
        log.info("Raft Client init successfully...");
        // 关闭阻塞
        future.channel().closeFuture().sync();
    }

    public void close() {
        group.shutdownGracefully();
    }

    public void sendMsg(Object msg) {
        clientChannel.writeAndFlush(msg);
    }

    public void sendMessage() {

    }
}
