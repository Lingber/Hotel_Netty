package com.lingber.bean;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月20日 下午3:00:59 
* 类说明 :
*/
public class OpenDoorsRecord_bean {
	
	private String roomnumber;
	private String openDoorTime;
	private String phone_Numb;
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public String getOpenDoorTime() {
		return openDoorTime;
	}
	public void setOpenDoorTime(String openDoorTime) {
		this.openDoorTime = openDoorTime;
	}
	public String getPhone_Numb() {
		return phone_Numb;
	}
	public void setPhone_Numb(String phone_Numb) {
		this.phone_Numb = phone_Numb;
	}
	public OpenDoorsRecord_bean(String roomnumber, String openDoorTime, String phone_Numb) {
		super();
		this.roomnumber = roomnumber;
		this.openDoorTime = openDoorTime;
		this.phone_Numb = phone_Numb;
	}
	public OpenDoorsRecord_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
