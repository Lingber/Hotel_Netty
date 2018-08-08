package com.lingber.bean;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;

import io.netty.channel.ChannelHandlerContext;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月23日 上午11:42:11 
* 类说明 :
*/
public class equipment_bean {
	
	private String roomnumber;
	private double[]  location;
	private String is_inuse;  //设备是否正在被用户使用
	private String is_connection; //设备是否已经连接服务器
	private String channel_id; //IP地址
	
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public double[] getLocation() {
		return location;
	}
	public void setLocation(double[] location) {
		this.location = location;
	}
	public String getIs_inuse() {
		return is_inuse;
	}
	public void setIs_inuse(String is_inuse) {
		this.is_inuse = is_inuse;
	}
	public String getIs_connection() {
		return is_connection;
	}
	public void setIs_connection(String is_connection) {
		this.is_connection = is_connection;
	}
	
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	
	public equipment_bean(String roomnumber, double[] location, String is_inuse, String is_connection,
			String channel_id) {
		super();
		this.roomnumber = roomnumber;
		this.location = location;
		this.is_inuse = is_inuse;
		this.is_connection = is_connection;
		this.channel_id = channel_id;
	}
	public equipment_bean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
