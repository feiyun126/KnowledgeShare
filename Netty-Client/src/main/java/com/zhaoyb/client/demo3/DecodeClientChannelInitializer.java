package com.zhaoyb.client.demo3;

import java.nio.charset.Charset;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class DecodeClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		
		//ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());
		//ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
		
		ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
		
		ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
		
		//ch.pipeline().addLast(new LineBasedClientHandler());
		
		//ch.pipeline().addLast(new DelimiterBasedClientHandler());
		
		ch.pipeline().addLast(new FixedLengthClientHandler());
	}

}
