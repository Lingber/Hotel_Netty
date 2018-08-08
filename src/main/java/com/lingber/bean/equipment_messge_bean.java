package com.lingber.bean;
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月1日 上午11:01:13 
* 类说明 :
*/
public class equipment_messge_bean {
	
	private String roomnumber;  //设备号
	private String userName;	//用户名
	private String phone_Numb;	//电话号码
	private String temperature;	//温度
	private String humidity;    //湿度
	private String co_concentration;//co
	private String fog_concentration;
	private int sleep_once;  //翻转次数一次统计
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
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
	public int getSleep_once() {
		return sleep_once;
	}
	public void setSleep_once(int sleep_once) {
		this.sleep_once = sleep_once;
	}
	
	public equipment_messge_bean(String roomnumber, String userName, String phone_Numb, String temperature,
			String humidity, String co_concentration, String fog_concentration, int sleep_once) {
		super();
		this.roomnumber = roomnumber;
		this.userName = userName;
		this.phone_Numb = phone_Numb;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co_concentration = co_concentration;
		this.fog_concentration = fog_concentration;
		this.sleep_once = sleep_once;
	}
	public equipment_messge_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
}
