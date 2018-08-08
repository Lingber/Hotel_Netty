package com.lingber.service;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月29日 下午9:47:25 
* 类说明 :
*/

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServerIniterHandler extends  ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
	    ChannelPipeline pipeline = arg0.pipeline();
	    pipeline.addLast("docode",new StringDecoder());
	    pipeline.addLast("encode",new StringEncoder());
	    pipeline.addLast("chat",new ChatServerHandler());
	}
}
