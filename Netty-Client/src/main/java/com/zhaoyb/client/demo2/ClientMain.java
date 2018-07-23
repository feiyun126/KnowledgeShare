package com.zhaoyb.client.demo2;

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

public class ClientMain {

	//粘包拆包
	public static void main(String[] args) {
		String aa ="ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
				+"ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler,ChannelHandlerContext,ChannelPipeline"
                + "ChannelHandler,ChannelHandlerContext,ChannelPipelineChannelHandler";
		
		String bb="ChannelHandler";
		EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY,true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
//                     p.addLast(new LineBasedFrameDecoder(1024));
                     p.addLast(new StringDecoder());
                     p.addLast(new StringEncoder());
                     p.addLast(new ClientHandler());
                 }
             });
 
            ChannelFuture future = b.connect("127.0.0.1", 8080).sync();
            //future.channel().writeAndFlush(aa);
            for(int i=0;i<=100;i++) {
            	future.channel().writeAndFlush(bb);
            	System.out.println("第"+i+"次发送数据");
            }
            future.channel().closeFuture().sync();
        } catch(Exception ex) {
            group.shutdownGracefully();
        }


	}

}
