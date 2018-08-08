package com.lingber.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.lingber.bean.Msg_bean;
import com.lingber.bean.RoomUseRecord_Bean;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月20日 下午12:10:17 
* 类说明 :
*/
public class RoomUserRecord_Server {
	
	
	@Autowired
	private static MongoTemplate mongoTemplate;
	
	
	public Msg_bean insertRoomUserRecord(String roomnumber,String phone_Numb,String userName,String IDNumber) {
	   
	   //插入开房时间
	   SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	   String startTime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
	   //插入最后退房时间
	   String overTime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		
	   RoomUseRecord_Bean roomUseRecord_Bean =new RoomUseRecord_Bean(roomnumber, startTime, overTime,  phone_Numb, userName, IDNumber);
		
	   mongoTemplate.insert(roomUseRecord_Bean, "record");
	   System.out.println("插入成功！");
	   Msg_bean msg_bean =new Msg_bean("01", "写入成功",null);
	   return msg_bean;
	}
	

	

}
