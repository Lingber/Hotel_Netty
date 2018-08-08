package com.lingber.bean;

import java.net.URL;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月23日 上午11:39:17 
* 类说明 :
*/
public class room_use_bean {
	private String roomnumber;  //设备号
	private double[] location;  //地址
	private String openDoorTime;//开门时间
	private String overUserTime;//结束时间
	private String userName;	//用户名
	private String phone_Numb;	//电话号码
	private String userImage_Name;	//头像地址
	private URL userImage_URL;		//图片地址
	private String userIDcard_Image_Name;//身份证图片名
	private URL userIDcard_Image_URL;	 //图片地址
	private String userIDcard_Number;	 //身份证号
	private int sleep_count;			 //翻转次数
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
	public String getOpenDoorTime() {
		return openDoorTime;
	}
	public void setOpenDoorTime(String openDoorTime) {
		this.openDoorTime = openDoorTime;
	}
	public String getOverUserTime() {
		return overUserTime;
	}
	public void setOverUserTime(String overUserTime) {
		this.overUserTime = overUserTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone_Numb() {
		return phone_Numb;
	}
	public void setPhone_Numb(String phone_Numb) {
		this.phone_Numb = phone_Numb;
	}
	public String getUserImage_Name() {
		return userImage_Name;
	}
	public void setUserImage_Name(String userImage_Name) {
		this.userImage_Name = userImage_Name;
	}
	public URL getUserImage_URL() {
		return userImage_URL;
	}
	public void setUserImage_URL(URL userImage_URL) {
		this.userImage_URL = userImage_URL;
	}
	public String getUserIDcard_Image_Name() {
		return userIDcard_Image_Name;
	}
	public void setUserIDcard_Image_Name(String userIDcard_Image_Name) {
		this.userIDcard_Image_Name = userIDcard_Image_Name;
	}
	public URL getUserIDcard_Image_URL() {
		return userIDcard_Image_URL;
	}
	public void setUserIDcard_Image_URL(URL userIDcard_Image_URL) {
		this.userIDcard_Image_URL = userIDcard_Image_URL;
	}
	public String getUserIDcard_Number() {
		return userIDcard_Number;
	}
	public void setUserIDcard_Number(String userIDcard_Number) {
		this.userIDcard_Number = userIDcard_Number;
	}
	public int getSleep_count() {
		return sleep_count;
	}
	public void setSleep_count(int sleep_count) {
		this.sleep_count = sleep_count;
	}
	public room_use_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public room_use_bean(String roomnumber, double[] location, String openDoorTime, String overUserTime,
			String userName, String phone_Numb, String userImage_Name, URL userImage_URL, String userIDcard_Image_Name,
			URL userIDcard_Image_URL, String userIDcard_Number, int sleep_count) {
		super();
		this.roomnumber = roomnumber;
		this.location = location;
		this.openDoorTime = openDoorTime;
		this.overUserTime = overUserTime;
		this.userName = userName;
		this.phone_Numb = phone_Numb;
		this.userImage_Name = userImage_Name;
		this.userImage_URL = userImage_URL;
		this.userIDcard_Image_Name = userIDcard_Image_Name;
		this.userIDcard_Image_URL = userIDcard_Image_URL;
		this.userIDcard_Number = userIDcard_Number;
		this.sleep_count = sleep_count;
	}
}
