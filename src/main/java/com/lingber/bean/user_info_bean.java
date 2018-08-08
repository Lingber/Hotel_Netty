package com.lingber.bean;

import java.net.URL;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月23日 上午11:35:18 
* 类说明 :
*/
public class user_info_bean {
	
	private String userName;
	private String passWord;
	private String phone_Numb;
	private String userImage_Name;
	private URL userImage_URL;
	private String userIDcard_Image_Name;
	private URL userIDcard_Image_URL;
	private String userIDcard_Number;
	private String String;
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
	public String getString() {
		return String;
	}
	public void setString(String string) {
		String = string;
	}
	public user_info_bean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public user_info_bean(java.lang.String userName, java.lang.String passWord, java.lang.String phone_Numb,
			java.lang.String userImage_Name, URL userImage_URL, java.lang.String userIDcard_Image_Name,
			URL userIDcard_Image_URL, java.lang.String userIDcard_Number, java.lang.String string) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.phone_Numb = phone_Numb;
		this.userImage_Name = userImage_Name;
		this.userImage_URL = userImage_URL;
		this.userIDcard_Image_Name = userIDcard_Image_Name;
		this.userIDcard_Image_URL = userIDcard_Image_URL;
		this.userIDcard_Number = userIDcard_Number;
		String = string;
	}
	
}
