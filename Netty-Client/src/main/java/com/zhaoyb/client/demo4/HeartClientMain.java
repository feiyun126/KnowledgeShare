package com.zhaoyb.client.demo4;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartClientMain {
	
	public static void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    ChannelPipeline p = ch.pipeline();
                    //读超时时间，写超时时间，所的类型超时时间，时间单位
                    //每5秒钟检查一下客户端的写操作
                    p.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));  
                    p.addLast(new StringEncoder());
					p.addLast(new StringDecoder());
                    p.addLast(new HeartClientHandler());

                }
            });

            ChannelFuture future = b.connect("127.0.0.1", 8000).sync();

            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
	

	public static void main(String[] args) throws Exception {
		connect();
	}

}
