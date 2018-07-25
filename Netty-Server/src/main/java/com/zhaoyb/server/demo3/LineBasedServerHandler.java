package com.zhaoyb.server.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LineBasedServerHandler extends ChannelInboundHandlerAdapter{

	int num=0;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 System.out.println("linebasedframedecoder==>server收到client的数据:======>"+msg.toString());
		 ctx.writeAndFlush("第"+ ++num +"次收到数据"+System.getProperty("line.separator"));
		 
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("服务端发生异常："+cause.getMessage());
		ctx.close();
	}

}
