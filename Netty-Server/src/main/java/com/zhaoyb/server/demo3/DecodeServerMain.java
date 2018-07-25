package com.zhaoyb.server.demo3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DecodeServerMain {

	public static void main(String[] args) {
		int port = 8080;
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 2048)
			.childHandler(new DecodeServerChannelInitializer());
			
			ChannelFuture future = b.bind(port).sync();
			System.out.println("服务端启动了。。。。。。。。。。。。。。。");
			future.channel().closeFuture().sync();

		} catch (Exception ex) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
