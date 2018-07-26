package com.zhaoyb.server.demo4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("有客户端连接了服务端");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("来自client的消息" + msg.toString());
		ctx.writeAndFlush("pong");

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			System.err.println(event.state().toString());
			if (event.state() == IdleState.READER_IDLE) {
				System.err.println("10秒没收到此Channel消息，关闭它的连接");
				ctx.close();
			}
		}
	}
}
