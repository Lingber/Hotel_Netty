package com.lingber.service;

import java.net.InetSocketAddress;

import com.lingber.bean.equipment_iscloseThedoor_bean;
import com.lingber.bean.equipment_messge_bean;

import io.netty.channel.ChannelHandlerContext;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月30日 下午2:54:27 
* 类说明 :
*/
public class ChatUnity {
	
	//新建一个MongoDB对象
	public static final CRUD_Msg_Mongodb crud_msg_Mongodb = new CRUD_Msg_Mongodb();
	
	public String creatChannelID(ChannelHandlerContext ctx){
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
	    String clientIP = insocket.getAddress().getHostAddress();
	    int clientPort = insocket.getPort();
	    String channelID=clientIP+clientPort;
    	 return channelID;
	}
	
	public void messege_Handle(String msg){
	    //将消息分解
		String roomnumber=msg.substring(0, 6);
		String function_Code=msg.substring(6, 8);
		String data_content = msg.substring(8, 12);
		String CRC = msg.substring(12, 16);
		System.out.println("收到数据为："+msg);
		String userName="Lingber";
		String phone_Numb="15078371726";
		equipment_messge_bean equipment_messge_bean = new equipment_messge_bean(roomnumber, 
		userName, phone_Numb, null, null,null, null, 0);
		if (CRC.equals("ffff")) {
			switch (function_Code) {
			//湿度数据
			case "01":
			    System.out.println("温度数据为："+data_content);
			    //存入数据库
			    equipment_messge_bean.setTemperature(data_content);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    break;
			//温度数据
			case "02":
				System.out.println("湿度数据为："+data_content);
				equipment_messge_bean.setHumidity(data_content);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    break;
			case "03":
			    System.out.println("一氧化碳浓度为："+data_content);
			    equipment_messge_bean.setCo_concentration(data_content);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    break;
			case "04":
			    System.out.println("烟雾浓度为："+data_content);
			    equipment_messge_bean.setFog_concentration(data_content);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    break;
			case "05":
			    System.out.println("门锁的状态为："+data_content);
			    equipment_messge_bean.setFog_concentration(data_content);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    if (data_content.equals("0001")) {
			      equipment_iscloseThedoor_bean equipment_iscloseThedoor_bean = new equipment_iscloseThedoor_bean("true", roomnumber);
			      crud_msg_Mongodb.insert_toMongodb_ofdoor(equipment_iscloseThedoor_bean, "tempdoor");
				}
			    if (data_content.equals("0000")) {
			      equipment_iscloseThedoor_bean equipment_iscloseThedoor_bean = new equipment_iscloseThedoor_bean("false", roomnumber);
			      crud_msg_Mongodb.insert_toMongodb_ofdoor(equipment_iscloseThedoor_bean, "tempdoor");
				}
			    break;
			case "06":
			    System.out.println("翻动的次数为："+data_content);
			    int count=Integer.parseInt(data_content);
			    equipment_messge_bean.setSleep_once(count);
			    crud_msg_Mongodb.insert_toMongodb(equipment_messge_bean, "msg");
			    break;
			case "07":
			    System.out.println("图片数据");
			    break;
			default:
			    System.out.println(0);
			}
		}else {
			System.out.println("校验失败，丢弃数据");
		}
	}
	

}
