package com.lingber.service;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月1日 下午12:50:18 
* 类说明 :
*/
public class Chat_Messege_collection {
	
	//开锁操作
	public String openLock() {
/*		char sendToEquip_Data[]={0x00,0x00,0x01,0x05,0x00,0x01,0xff,0xff};
		return sendToEquip_Data.toString();*/
		String sendToEquip_Data="000001050001ffff";
		return sendToEquip_Data;
	}
	
	//查询锁头状态,结束使用
	public String lookLock() {
/*		char sendToEquip_Data[]={0x00,0x00,0x01,0x05,0x00,0x00,0xff,0xff};
		return sendToEquip_Data.toString();*/
		String sendToEquip_Data="000001080000ffff";
		return sendToEquip_Data;
	}
	
	//连接上服务器后，次消息通知设备已经连上
	public String connctionResponse() {
/*		char sendToEquip_Data[]={0x00,0x00,0x01,0x00,0x00,0x00,0xff,0xff};
		return sendToEquip_Data.toString();*/
		String sendToEquip_Data="000001000000ffff";
		return sendToEquip_Data;
	}

}
