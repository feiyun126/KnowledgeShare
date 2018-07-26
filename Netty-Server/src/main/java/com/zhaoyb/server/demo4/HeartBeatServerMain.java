package com.zhaoyb.server.demo4;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartBeatServerMain {
	
	public static void start(int port) throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {

							ChannelPipeline p = ch.pipeline();
							//读超时时间，写超时时间，所的类型超时时间，时间单位
							//服务端每10秒检查一下channelRead
							p.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
							p.addLast(new StringEncoder());
							p.addLast(new StringDecoder());
						    p.addLast("pong", new HeartBeatServerHandler());
						}
					});

			ChannelFuture f = b.bind(port).sync(); // (7)
            System.out.println("服务端启动了。。。。。。。。。。。。。。");
			f.channel().closeFuture().syncUninterruptibly().sync();

		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}


	public static void main(String[] args) throws Exception {
          start(8000);
	}

}
