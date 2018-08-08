package com.lingber.bean;

import java.net.URL;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月19日 下午5:47:06 
* 类说明 :
*/
public class User_Bean {
	
	private String userName;
	private String passWord;
	private String IDNumber;
	private String phone_Numb;
	private String userAge;
	private String sex;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public String getPhone_Numb() {
		return phone_Numb;
	}
	public void setPhone_Numb(String phone_Numb) {
		this.phone_Numb = phone_Numb;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public User_Bean(String userName, String passWord, String iDNumber, String phone_Numb, String userAge, String sex) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		IDNumber = iDNumber;
		this.phone_Numb = phone_Numb;
		this.userAge = userAge;
		this.sex = sex;
	}
	public User_Bean() {
		super();
		// TODO Auto-generated constructor stub
	}
}
