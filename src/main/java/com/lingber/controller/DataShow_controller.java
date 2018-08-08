package com.lingber.controller;

import java.util.List;

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

import com.lingber.bean.equipmentShowMsg_bean;
import com.lingber.bean.equipment_messge_bean;
import com.lingber.service.CRUD_Msg_Mongodb;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月1日 下午1:24:54 
* 类说明 :   此类用来做入住期间的数据统计展示
*/

@RequestMapping("/datashow") 
@Controller
public class DataShow_controller {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//全局化CRUD_Mongodb类
	public static final CRUD_Msg_Mongodb crud_msg_Mongodb = new CRUD_Msg_Mongodb();
	
	//将msg数据库的数据进行统一化
    @ResponseBody
    @RequestMapping("/count")
	public equipmentShowMsg_bean count_Msg_Data(HttpServletRequest request) {
	  HttpSession httpSession = request.getSession(false);
	  String roomnumber=(String) httpSession.getAttribute("room_number");
    	System.out.println(roomnumber);
      Query query = new Query(Criteria.where("roomnumber").is(roomnumber));
      System.out.println(query);
       System.out.println("1");
	  //从msg数据库中取出数据
       /*List<equipment_messge_bean>messge_beans= crud_msg_Mongodb.find_EquipByRoomNumber(roomnumber, "equip");*/
      List<equipment_messge_bean>messge_beans= mongoTemplate.find(query, equipment_messge_bean.class, "msg");
      System.out.println("2");
      //将数据进行统计,将所有信息条的内容进行统一计算，存入temporary数据库中
	  if (messge_beans.size()!=0) {
	       int count = 0;
		   String temperature = null;
		   String humidity = null;
		   String co_concentration = null;
		   String fog_concentration = null;
		  for (equipment_messge_bean equipment_messge_bean : messge_beans) {
			  if (equipment_messge_bean.getRoomnumber().equals(roomnumber)) {
			
			  if (equipment_messge_bean.getTemperature()!=null) {
				  temperature = equipment_messge_bean.getTemperature();
			 }
			  if (equipment_messge_bean.getHumidity()!=null) {
				  humidity = equipment_messge_bean.getHumidity();
			 }
			  if (equipment_messge_bean.getCo_concentration()!=null) {
				  co_concentration = equipment_messge_bean.getCo_concentration();
			 }
			  if (equipment_messge_bean.getFog_concentration()!=null) {
				  fog_concentration = equipment_messge_bean.getFog_concentration();
			 }
			  int sleep_once = equipment_messge_bean.getSleep_once();
			  count=count+sleep_once;
		    }
		  }
		   int action_count = count;
		  //装进展示对象里,存入临时表
		  equipmentShowMsg_bean equipmentShowMsg_bean = new equipmentShowMsg_bean(messge_beans.get(0).getRoomnumber(),
				  temperature, humidity, co_concentration, fog_concentration, action_count);
		  //删除原来的临时表
		  mongoTemplate.dropCollection("temp");
		  mongoTemplate.insert(equipmentShowMsg_bean, "temp");
		  //统计完成
		  //将临时表中的数据提交到前台
		  List<equipmentShowMsg_bean> returnmsg=mongoTemplate.find(query, equipmentShowMsg_bean.class, "temp");
		  if (returnmsg.size()!=0) {
			  return returnmsg.get(0);
		  }else {
			return null;
		}
	 }
	  return null;
 }
    
    
    public static void main(String[] args) {
		String string ="0100";
		int sleep=Integer.parseInt(string);
		System.out.println(sleep);
	}
    
    
}