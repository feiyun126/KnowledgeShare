package com.zyb.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public final class Server {
	public void start(int beginPort, int nPort) {
		System.out.println("server starting....");

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);

		bootstrap.childHandler(new ConnectionCountHandler());

		/**
		 * 绑定100个端口号
		 */
		for (int i = 0; i < nPort; i++) {
			int port = beginPort + i;
			bootstrap.bind(port).addListener((ChannelFutureListener) future -> {
				System.out.println("bind success in port: " + port);
			});
		}
		System.out.println("server started!");
	}
}
