package com.github.jiangxch.hasakei.raft.socket.server;

import com.github.jiangxch.hasakei.raft.socket.server.channelHandler.ServerChannelInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:58
 */
@Slf4j
public class Server {
    private String host;
    private Integer port;
    private ChannelFuture serverChannelFuture;
    private NioEventLoopGroup bossLoopGroup;
    private NioEventLoopGroup workLoopGroup;

    public Server(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        // 初始化主从线程
        bossLoopGroup = new NioEventLoopGroup();
        workLoopGroup = new NioEventLoopGroup();

        // 初始化服务启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 配置主从线程，配置通道处理器
        serverBootstrap.group(bossLoopGroup, workLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 定长编解码
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        ch.pipeline().addLast(new ServerChannelInboundHandler(Server.this));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        serverChannelFuture = serverBootstrap.bind(host,port).sync();
        log.info("Raft Node Socket Server start successfully...");

        // 关闭会阻塞
        serverChannelFuture.channel().closeFuture().sync();
    }

    public void close() {
        bossLoopGroup.shutdownGracefully();
        workLoopGroup.shutdownGracefully();
    }

    public void sendMessage(Object msg) {
        serverChannelFuture.channel().writeAndFlush(msg);
    }
}
