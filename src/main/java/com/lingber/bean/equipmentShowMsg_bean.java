package com.lingber.bean;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月1日 下午1:52:55 
* 类说明 :
*/
public class equipmentShowMsg_bean {
	
	private String roomnumber;  //设备号
	private String temperature;	//温度
	private String humidity;    //湿度
	private String co_concentration;//湿度 fog
	private String fog_concentration;
	private int action_count;  //翻转次数统计
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getCo_concentration() {
		return co_concentration;
	}
	public void setCo_concentration(String co_concentration) {
		this.co_concentration = co_concentration;
	}
	public String getFog_concentration() {
		return fog_concentration;
	}
	public void setFog_concentration(String fog_concentration) {
		this.fog_concentration = fog_concentration;
	}
	public int getAction_count() {
		return action_count;
	}
	public void setAction_count(int action_count) {
		this.action_count = action_count;
	}
	public equipmentShowMsg_bean(String roomnumber, String temperature, String humidity, String co_concentration,
			String fog_concentration, int action_count) {
		super();
		this.roomnumber = roomnumber;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co_concentration = co_concentration;
		this.fog_concentration = fog_concentration;
		this.action_count = action_count;
	}
	public equipmentShowMsg_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
