package com.lingber.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingber.bean.Msg_bean;
import com.lingber.bean.User_Bean;
import com.lingber.bean.equipment_bean;
import com.lingber.bean.equipment_iscloseThedoor_bean;
import com.lingber.bean.equipment_messge_bean;
import com.lingber.service.CRUD_Mongodb;
import com.lingber.service.ChatChanneHashlMap;
import com.lingber.service.ChatServerHandler;
import com.lingber.service.ChatServerMain;
import com.lingber.service.Chat_Messege_collection;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月25日 下午8:08:58 
* 类说明 :
*/
@RequestMapping("/netty") 
@Controller
public class Netty_controller {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	//消息类
	public static final Chat_Messege_collection chat_messege_collection = new Chat_Messege_collection();
	//通道操作类
	public static final ChatServerHandler chatserverhandler= new ChatServerHandler();
	
	public static final CRUD_Mongodb crud_Mongodb = new CRUD_Mongodb();
	
	public static final ChatChanneHashlMap channeHashlMap = new ChatChanneHashlMap();
	//session控制器
	public static final user_login_controller user_login_controller =new user_login_controller();
    @ResponseBody
    @RequestMapping("/openServer")
	public Msg_bean openServer() throws Exception {
    	new ChatServerMain(5500).run(); 
		return null;
	}
    
    @ResponseBody
    @RequestMapping("/sendMsg")
	public Msg_bean sendMsg(String function,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	//从session中提取设备号，用户信息
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
    	String room_number=(String) httpSession.getAttribute("room_number");
    	if (room_number!=null) {	
    	//如果扫描到设备号
        	if (function.equals("open")) {
        		equipment_bean equipment_bean =crud_Mongodb.find_EquipByRoomnumber(room_number, "equip");
        		chatserverhandler.sendMessge(room_number,chat_messege_collection.openLock());
        		equipment_bean equipment_bean2 = new equipment_bean(room_number, null, "true", "true", equipment_bean.getChannel_id());
        		crud_Mongodb.updata_toMongodb(room_number, equipment_bean2, "equip");
/*    			Query query = new Query(Criteria.where("roomnumber").is(room_number));
        		Update update =new Update();
        		mongoTemplate.upsert(query,update.update("is_connection", "true"),equipment_bean.class,"equip");*/
        		Msg_bean msg_bean =new Msg_bean("01", "开锁成功",null);
        		return msg_bean;
    		}else {
    			if (function.equals("close")) {
    			chatserverhandler.sendMessge(room_number,chat_messege_collection.lookLock());
        		equipment_bean equipment_bean = new equipment_bean(room_number, null, "false", "true", "123456");
        		crud_Mongodb.updata_toMongodb(room_number, equipment_bean, "equip");
        		//查询设备是否已经关闭
        		Query query_of_closeThedoor= new Query(Criteria.where("roomnumber").is(room_number));
        		List<equipment_iscloseThedoor_bean>equipment_iscloseThedoor_beans=mongoTemplate.find(query_of_closeThedoor,
        				equipment_iscloseThedoor_bean.class, "tempdoor");
        		Collections.reverse(equipment_iscloseThedoor_beans);
        		String isclose=equipment_iscloseThedoor_beans.get(0).getIscloseThedoor();
        		System.out.println("关门状态"+isclose);
        		mongoTemplate.dropCollection("tempdoor");
        		if (isclose.equals("false")) {
        			System.out.println("关门！");
            		//删除消息记录
            		Query query = new Query(Criteria.where("roomnumber").is(room_number));
            		System.out.println("要删除的查询消"+query);
            		mongoTemplate.remove(query, equipment_messge_bean.class, "msg");
            		//删除会话
            		user_login_controller.del_session(request, response);
            		Msg_bean msg_bean =new Msg_bean("02", "设备状态检查成功",null);
            		return msg_bean;
				}else {
					System.out.println("无法关门！");
            		Msg_bean msg_bean =new Msg_bean("03", "无法结束，门没关",null);
            		return msg_bean;
				}

    			}else {
    				System.out.println("消息无效");
    				Msg_bean msg_bean =new Msg_bean("00", "错误",null);
    				return msg_bean;
    			}
    		}
		}else {
	   //如果没有扫描到设备号
			Msg_bean msg_bean =new Msg_bean("00", "错误",null);
			return msg_bean;
		}
	}
}
