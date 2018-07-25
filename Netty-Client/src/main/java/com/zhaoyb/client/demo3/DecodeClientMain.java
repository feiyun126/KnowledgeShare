package com.zhaoyb.client.demo3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DecodeClientMain {
	
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
					.handler(new DecodeClientChannelInitializer());

			ChannelFuture future = b.connect("127.0.0.1", 8080).sync();
			future.channel().closeFuture().sync();
		} catch (Exception ex) {
			group.shutdownGracefully();
		}

	}
}
