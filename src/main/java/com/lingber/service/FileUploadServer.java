package com.lingber.service;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月8日 下午9:34:55 
* 类说明 :
*/
public class FileUploadServer {
	 public void bind(int port) throws Exception {
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<Channel>() {

	                @Override
	                protected void initChannel(Channel ch) throws Exception {
	                    ch.pipeline().addLast(new ObjectEncoder());
	                    ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
	                    ch.pipeline().addLast(new FileUploadServerHandler());
	                }
	            });
	            ChannelFuture f = b.bind(port).sync();
	            f.channel().closeFuture().sync();
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) {
	        int port = 10086;
	        if (args != null && args.length > 0) {
	            try {
	                port = Integer.valueOf(args[0]);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	            }
	        }
	        try {
	        	System.out.println("已启动："+port);
	            new FileUploadServer().bind(port);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
