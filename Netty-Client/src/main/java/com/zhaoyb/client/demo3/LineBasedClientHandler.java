package com.zhaoyb.client.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LineBasedClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 1; i <=20; i++) {
			ctx.writeAndFlush("server您好,我是老"+i+System.getProperty("line.separator"));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 System.out.println("client收到server的数据:======>"+msg.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("客户端发声异常："+cause.getMessage());
		ctx.close();
	}

}
