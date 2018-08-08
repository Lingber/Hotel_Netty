package com.lingber.bean;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月2日 下午9:01:55 
* 类说明 :
*/
public class equipment_iscloseThedoor_bean {
	private String iscloseThedoor;
	private String roomnumber;
	public String getIscloseThedoor() {
		return iscloseThedoor;
	}
	public void setIscloseThedoor(String iscloseThedoor) {
		this.iscloseThedoor = iscloseThedoor;
	}
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public equipment_iscloseThedoor_bean(String iscloseThedoor, String roomnumber) {
		super();
		this.iscloseThedoor = iscloseThedoor;
		this.roomnumber = roomnumber;
	}
	public equipment_iscloseThedoor_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
