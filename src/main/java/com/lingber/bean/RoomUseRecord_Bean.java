package com.lingber.bean;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月19日 下午5:37:32 
* 类说明 :
*/
public class RoomUseRecord_Bean {
	
	private String roomnumber;
	private String startTime;
	private String overTime;
	private String phone_Numb;
	private String userName;
	private String iDNumber;
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
	public String getPhone_Numb() {
		return phone_Numb;
	}
	public void setPhone_Numb(String phone_Numb) {
		this.phone_Numb = phone_Numb;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIDNumber() {
		return iDNumber;
	}
	public void setIDNumber(String iDNumber) {
		iDNumber = iDNumber;
	}
	public RoomUseRecord_Bean(String roomnumber, String startTime, String overTime, String phone_Numb, String userName,
			String iDNumber) {
		super();
		this.roomnumber = roomnumber;
		this.startTime = startTime;
		this.overTime = overTime;
		this.phone_Numb = phone_Numb;
		this.userName = userName;
		this.iDNumber = iDNumber;
	}
	public RoomUseRecord_Bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
