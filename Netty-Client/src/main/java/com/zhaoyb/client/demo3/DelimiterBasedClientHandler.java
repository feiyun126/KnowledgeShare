package com.zhaoyb.client.demo3;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DelimiterBasedClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("成功连接服务器");
		for (int i = 1; i <=20; i++) {
			System.out.println("第"+i+"次发送");
			ctx.writeAndFlush(Unpooled.copiedBuffer(("server您好,我是老&").getBytes()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("客户端发声异常："+cause.getMessage());
		ctx.close();
	}
}