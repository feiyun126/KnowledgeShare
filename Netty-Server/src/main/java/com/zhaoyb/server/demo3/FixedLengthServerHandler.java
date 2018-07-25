package com.zhaoyb.server.demo3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FixedLengthServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		 System.out.println("FixedLengthframedecoder==>server收到client的数据:======>"+msg.toString());
		 
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("服务端发生异常："+cause.getMessage());
		ctx.close();
	}
}
