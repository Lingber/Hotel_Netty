package com.lingber.service;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月29日 下午9:39:44 
* 类说明 :
*/

import java.net.InetSocketAddress;


import com.lingber.bean.equipment_bean;
import com.lingber.bean.equipment_iscloseThedoor_bean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
	//新建group用来存储通道
	public static final ChannelGroup group = new DefaultChannelGroup(
	        GlobalEventExecutor.INSTANCE);
	//新建一个hashmap用来保存channel
	public static final ChatChanneHashlMap channeHashlMap = new ChatChanneHashlMap();
	
	//新建一个MongoDB对象
	public static final CRUD_Mongodb crud_Mongodb = new CRUD_Mongodb();
	
	//全局工具类
	public static final ChatUnity chat_unity = new ChatUnity(); 
	
	//消息类
	public static final Chat_Messege_collection chat_messege_collection = new Chat_Messege_collection(); 
	
	/*channelRead0：当channel的可读就绪时，该方法被调用，即是接收到客户端发过来的消息时被调用*/
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1)
	        throws Exception {
	    Channel channel = arg0.channel();
	    //新建equip对象保存信息
        String roomnumber=arg1.substring(0, 6);
        //将收到数据进行保存处理
        chat_unity.messege_Handle(arg1);
            //生成ID，将channel以键值对的形式存进HashMap
        	/*ChatChanneHashlMap channeHashlMap = new ChatChanneHashlMap();*/
        	String channelID=chat_unity.creatChannelID(arg0);
        	//生成键值对，存入HashMap
        	channeHashlMap.addChannel(channelID, channel);
     	    equipment_bean equipment_bean = new equipment_bean(roomnumber, null, "false", "true",channelID);
         if (crud_Mongodb.find_toMongodb(roomnumber, "equip")!=null) {
          //如果此机器之前有过连接记录，则将channelID进行更新
   	      //更新
   	         crud_Mongodb.updata_toMongodb(roomnumber, equipment_bean, "equip");
		}else {
		  //如果之前没有过记连接录，则插入新的
	 	      //将新的连接信息存入数据库
	 	     crud_Mongodb.insert_toMongodb(equipment_bean, "equip");	
		}
/*  	    //从通道缓存组里把所有通道拿出来比较
	      String channel_id= crud_Mongodb.find_toMongodb(roomnumber, "equip");
	      Channel channelHash=channeHashlMap.getChannelByName(channel_id);
	    //连接回应
	      channelHash.writeAndFlush(chat_messege_collection.connctionResponse());*/
	}

	
	/*有新连接时触发*/
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    for (Channel ch : group) {
	        ch.writeAndFlush(
	                "[" + channel.remoteAddress() + "] " + "is comming");
	    }
	    group.add(channel);
	}

	/*有连接断开时触发*/
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    for (Channel ch : group) {
	        ch.writeAndFlush(
	                "[" + channel.remoteAddress() + "] " + "is leaving");
	    }
	    //将HashMap里的键值对删除
	    String ChannelID=chat_unity.creatChannelID(ctx);
	    channeHashlMap.removeChannelByName(ChannelID);
	    //将组里的channel删除
	    //将数据库里的及其状态进行修改
	    equipment_bean equipment_room= crud_Mongodb.find_EquipByChannelID(ChannelID, "equip");
	    System.out.println(equipment_room);
	    if (equipment_room!=null) {
		    equipment_bean equipment_bean = new equipment_bean(equipment_room.getRoomnumber(), null, "false", "false","123456");
		    crud_Mongodb.updata_toMongodb(equipment_room.getRoomnumber(), equipment_bean, "equip");
		}else {
			System.out.println("数据库里没有该机器记录");
		}
	    group.remove(channel);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    ChannelId id=channel.id();
	    System.out.println("[" + channel.remoteAddress() + "] " + "online");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    System.out.println("[" + channel.remoteAddress() + "] " + "offline");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	        throws Exception {
	    System.out.println(
	            "[" + ctx.channel().remoteAddress() + "]" + "exit the room");
	    ctx.close().sync();
	}
	
	public void sendMessge(String roomnumber,String Msg){
		String ChannelID=crud_Mongodb.find_toMongodb(roomnumber, "equip");
		Channel ctx= channeHashlMap.getChannelByName(ChannelID);
		if (ctx!=null) {
			ctx.writeAndFlush(Msg);
		}else {
			System.out.println("设备未连接");
		}
		
	}

}
