package com.zhaoyb.client.demo3;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FixedLengthClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String str = "EventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroupEventLoopGroup";
		System.out.println("成功连接服务器");
        ctx.writeAndFlush(str);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("客户端发声异常：" + cause.getMessage());
		ctx.close();
	}
}