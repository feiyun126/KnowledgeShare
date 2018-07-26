package com.zhaoyb.client.demo4;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartClientHandler extends ChannelInboundHandlerAdapter{
	
	@Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
	   //scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
		//command:操作 initialDelay初始延迟 period间隔 unit时间单位
      ctx.executor().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("ping");
				ctx.writeAndFlush("ping");
			}
		}, 0,4, TimeUnit.SECONDS);
    }  
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("来自server的消息" + msg.toString());
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		 if (evt instanceof IdleStateEvent) {  
	            IdleStateEvent event = (IdleStateEvent) evt;  
	            if (event.state() == IdleState.WRITER_IDLE) { 
	            	//如果4秒都没有写操作，发一个ping指令
	        		ctx.writeAndFlush("ping");
	            }  
	        }  
	}
}
