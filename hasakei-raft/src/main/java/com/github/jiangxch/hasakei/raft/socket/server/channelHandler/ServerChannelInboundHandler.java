package com.github.jiangxch.hasakei.raft.socket.server.channelHandler;

import com.github.jiangxch.hasakei.common.exceptions.HasakeiException;
import com.github.jiangxch.hasakei.common.model.ResultCode;
import com.github.jiangxch.hasakei.common.util.JsonUtil;
import com.github.jiangxch.hasakei.raft.enums.MessageType;
import com.github.jiangxch.hasakei.raft.socket.model.InviteVoteMessage;
import com.github.jiangxch.hasakei.raft.socket.server.Server;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Optional;


/**
 * @author: jiangxch
 * @date: 2020/7/26 下午3:57
 */
@Slf4j
public class ServerChannelInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Server server;

    public ServerChannelInboundHandler(Server server) {
        this.server = server;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        int msgType = msg.getInt(0);
        String body = new String(msg.array(), Charset.defaultCharset());
        if (MessageType.INVITE_VOTE_REQUEST.type.equals(msgType)) {
            processInviteRequest(body);
        } else {
            throw new HasakeiException(ResultCode.SOCKET_MESSAGE_TYPE_NOT_SUPPORT);
        }
    }

    private void processInviteRequest(String body) {
        InviteVoteMessage.Request msg = JsonUtil.fromJson(body, InviteVoteMessage.Request.class);
        String fromAddress = Optional.of(msg).get().getFromAddress();
        server.sendMessage();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Raft Server Node exception,ex={}",cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
