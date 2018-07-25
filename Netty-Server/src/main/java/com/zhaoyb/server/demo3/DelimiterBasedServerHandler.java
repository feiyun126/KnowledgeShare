package com.zhaoyb.server.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DelimiterBasedServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 System.out.println("有client与server连接");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 System.out.println("DelimiterBasedFrameDecoder==>server收到client的数据:======>"+msg.toString()); 
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("服务端发生异常："+cause.getMessage());
		ctx.close();
	}


}
