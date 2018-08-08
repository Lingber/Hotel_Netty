package com.lingber.service;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
 
import java.io.RandomAccessFile;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月9日 下午9:25:33 
* 类说明 :
*/
public class FileControl {
    private ChannelHandlerContext channel;
    
    public FileControl(ChannelHandlerContext channel) {
        this.channel = channel;
    }
 
    /**
     * 通知服务端我要发送一个文件了
     * 里面的信息你可以第一为自己想要信息格式，包括文件名，长度，类型等
     * 这里只简单的通知文件名
     */
    public void sendNotice(){
        channel.writeAndFlush("00".getBytes(CharsetUtil.UTF_8));
    }
 
    /**
     * 传送文件
     * @param fileUrl
     */
    public void sendFile(String fileUrl) {
        // 开始执行
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileUrl, "rw");
            ChunkedFile chunkedFile = new ChunkedFile(raf);
            channel.writeAndFlush(chunkedFile).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    future.channel().close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
