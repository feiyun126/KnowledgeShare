package com.zhaoyb.server.demo1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//当与客户端连接时触发
		System.out.println("服务端：channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("服务端开始接收数据--------------------");
        System.out.println(msg.toString());
        ctx.write("客户您好,你发送的的内容是【"+msg.toString()+"】已接收到，谢谢");
        ctx.flush();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//读取客户端数据完成后触发
		System.out.println("服务端： channelReadComplete");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//当发生异常时触发
		System.out.println("服务端出现异常："+cause.getMessage());
		System.out.println("现在关闭服务端连接");
		ctx.close();
	}

	

}
