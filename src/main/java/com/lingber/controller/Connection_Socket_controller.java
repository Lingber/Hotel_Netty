package com.lingber.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingber.bean.equipment_bean;
import com.lingber.service.CRUD_Mongodb;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月30日 下午4:45:00 
* 类说明 :
*/


@Controller
public class Connection_Socket_controller {
	@Autowired
	private static MongoTemplate mongoTemplate;
	//新建一个MongoDB对象
	public static final CRUD_Mongodb crud_Mongodb = new CRUD_Mongodb();
	
	
	/*查询职位基本信息控制器*/
	/*@ResponseBody*/
	@RequestMapping("/qr_creat")
	public String getRoughWithJson_computer(HttpServletRequest request,HttpServletResponse response,String room_number)throws ServletException, IOException{
		System.out.println("设备号"+room_number);
		//检查设备是否连接服务器，是否正在使用
		Query query = new Query(Criteria.where("roomnumber").is(room_number));
		System.out.println("1");
		equipment_bean equipment_bean;
		try {
			equipment_bean = crud_Mongodb.find_EquipByRoomnumber(room_number, "equip");
			/*	equipment_bean equipment_bean =mongoTemplate.findOne(query, equipment_bean.class, "equip");*/
			String isconnect=equipment_bean.getIs_connection();
			String isinuser =equipment_bean.getIs_inuse();
			//如果设备有过登入
			//如果设备处于离线状态
			if (isconnect.equals("true")) {
				//如果设备处于空闲状态
				if (isinuser.equals("false")) {
					//创建session
			    	HttpSession httpSession = request.getSession(); 
			        // 向session保存一个Scoket连接  
			        httpSession.setAttribute("room_number", room_number);
			        System.out.println("已经将设备号保存进：session:");
			        System.out.println(httpSession.getAttribute("room_number"));
			        Cookie cookie = new Cookie("JSESSIONID1", httpSession.getId());
			        cookie.setMaxAge(43200*60);// 设置为30天  
			        cookie.setPath("/Hotel_Server");  
			        System.out.println("已添加===============");  
			        response.addCookie(cookie);
					return "userLogin";
				}else {
					System.out.println("机器正在使用，不可再开启");
					return "wangning1";
				}
			}else {
				System.out.println("机器未打开，无法使用");
				return "wangning2";
			}
		} catch (Exception e) {
			return "wangning3";
		}
    }
}
