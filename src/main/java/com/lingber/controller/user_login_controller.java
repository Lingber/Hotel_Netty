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
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingber.bean.Msg_bean;
import com.lingber.bean.user_info_bean;
import com.lingber.service.oss_service;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年4月9日 下午8:55:02 
* 类说明 :登录控制器接口
*/
@RequestMapping("/login") 
@Controller
public class user_login_controller {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private oss_service oss_service;
	
	
    @ResponseBody
    @RequestMapping("/server_loginCheckUp") 
	public Msg_bean  server_login_CheckUp(String phonenumbLogin,String Loginpassword,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Query query_phone =new Query(Criteria.where("phone_Numb").is(phonenumbLogin));
		user_info_bean find_phone = mongoTemplate.findOne(query_phone, user_info_bean.class, "mvc");
		if (find_phone!=null) {
				if (find_phone.getPassWord().equals(Loginpassword)) {//如果密码正确
					//查询改用户用户名
					String username=find_phone.getUserName();
					//添加session
					//创建session
			    	HttpSession httpSession = request.getSession(); 
			        // 向session 用户ID以及用户名，设备号  
			        httpSession.setAttribute("phone_Numb", phonenumbLogin); 
			        System.out.println("已将phone_Numb保存进session:"+phonenumbLogin);
			        httpSession.setAttribute("userName", username); 
			        System.out.println("已将username保存进session:"+username);
			        httpSession.setAttribute("userName", username);
			        Cookie cookie = new Cookie("JSESSIONID2", httpSession.getId());
			        cookie.setMaxAge(43200*60);// 设置为30天  
			        cookie.setPath("/Hotel_Server");  
			        System.out.println("已添加===============");  
			        response.addCookie(cookie);
				}else {
					Msg_bean msg_bean =new Msg_bean("08", "登录失败！密码错误！",null);
					return msg_bean;
				}
				Msg_bean msg_bean =new Msg_bean("06", "登录成功！",null);
				return msg_bean;
		}else {
			Msg_bean msg_bean =new Msg_bean("07", "登录失败！账号不存在！",null);
			return msg_bean;
		}
	}
	
    @RequestMapping("/outlogin") 
	public String  del_session(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	//获取客户端保存的session,若没有过期，则可以提取出信息，如果过期，则要求重新登录。
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
        if (null==cookies) {  
	            System.out.println("没有cookie=========");
				return "userLogin";
        } else {
	    		if (httpSession!=null) {//如果session尚未过期（cookie还存在）
	            	System.out.println("读取userid:"+httpSession.getAttribute("phone_Numb"));
	    		}
	    		for(Cookie cookie : cookies){ //选定所有
                    cookie.setValue(null);  
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/Hotel_Server"); 
                    response.addCookie(cookie);
	    		}
				return "userLogin";
        }  
	}
    
    
    @ResponseBody
    @RequestMapping("/sessionCheckUp") 
	public Msg_bean  session_CheckUp(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	//获取客户端保存的session,若没有过期，则可以提取出信息，如果过期，则要求重新登录。
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
        if (null==cookies) {  
	            System.out.println("没有cookie=========");
				Msg_bean msg_bean =new Msg_bean("08", "登录超时！请重新登录！",null);
				return msg_bean;
        } else {  
        		if (httpSession.getAttribute("userID")!=null) {
    	    		for(Cookie cookie : cookies){ //选定所有
    		    		cookie.setMaxAge(43200*60);// 重新设置时间 
    		    		cookie.setPath("/Picture_SpringMvc");
    		    		response.addCookie(cookie); 
    		    		}
				}else {
					Msg_bean msg_bean =new Msg_bean("08", "登录超时！请重新登录！",null);
					return msg_bean;
				}
				Msg_bean msg_bean =new Msg_bean("09", "重新激活！",null);
				return msg_bean;
        }  
	}
    
}
