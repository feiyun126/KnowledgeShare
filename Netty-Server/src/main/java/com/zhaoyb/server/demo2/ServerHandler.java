package com.zhaoyb.server.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private int num;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		int i=++num;
		System.out.println("第"+ i +"收到客户端数据 : " + msg.toString() );
		ctx.writeAndFlush(""+i);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
