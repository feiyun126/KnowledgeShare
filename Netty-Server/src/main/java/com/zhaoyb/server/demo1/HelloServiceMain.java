package com.zhaoyb.server.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServiceMain {

	public static void main(String[] args) {
		int port = 8080;
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();// Netty用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new HelloServerChannelInitializer());
			// 调用同步阻塞方法sync 等待绑定操作完成，完成后Netty会返回一个ChannelFuture，
			ChannelFuture future = b.bind(port).sync(); // 绑定端口，同步等待成功
			
			// 它的功能类似于JDK 的 java.util.concurrent.Future，主要用于异步操作的通知回调。
			// 等待服务端链路关闭之后main函数才退出。
			future.channel().closeFuture().sync();

		} catch (Exception ex) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
