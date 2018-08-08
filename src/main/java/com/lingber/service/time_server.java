package com.lingber.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月19日 下午2:20:44 
* 类说明 :
*/
public class time_server {
	
	//判断RFID卡里时间数据是否超过现在时间
	public String compareTime(String RFID_time,String Last_time) {
	       SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	       String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
	       int i =Last_time.compareTo(RFID_time);
	       
	       if (i==1) {
	    	  return "1";
	       }    
	       return "0";
	}
	
	//判断RFID卡里时间数据是否超过现在时间
	public String WTime_ToRFID() {
	       SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	       String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
	       return date+"00";
	}
	
	
	
	
	public static void main(String[] args) {
		
		
 	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
 	    String NowTime = df.format(new Date());//new Date()为获取当前系统时间，也可使用当前时间戳
 	    System.out.println(NowTime);
 	    
		String iString="20180520171800";
		String zString="20180520171700";
		
		int j =iString.compareTo(zString);
		System.out.println(j);
	}
	
	
	
	

}
