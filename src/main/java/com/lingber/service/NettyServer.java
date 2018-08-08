package com.lingber.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月9日 下午2:00:03 
* 类说明 :
*/
public class NettyServer implements Runnable{


    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            /*b.option(ChannelOption.SO_BACKLOG, 5242880);*/
            b.childHandler(new MyServerHandler());
            b.childHandler(new ChatServerIniterHandler());
            
            // 绑定端口
            ChannelFuture f = b.bind(10086).sync();

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅的退出
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("启动NettyServer start... ...");
        Thread thread = new Thread(new NettyServer());
        thread.start();
        System.out.println("启动NettyServer end... ...");
    }

}