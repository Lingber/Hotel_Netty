package com.lingber.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingber.bean.Msg_bean;
import com.lingber.bean.RoomUseRecord_Bean;
import com.lingber.bean.user_info_bean;
import com.lingber.service.oss_service;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月27日 上午11:30:26 
* 类说明 :
*/

@RequestMapping("/user") 
@Controller
public class user_Controller {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Autowired
	private oss_service oss_service;
	
	
	@RequestMapping("/getuserinfo")
	@ResponseBody
	public user_info_bean getUserInfo(HttpServletRequest request,HttpServletResponse response) {
    	HttpSession httpSession = request.getSession(false);
    	String phone_Numb=(String) httpSession.getAttribute("phone_Numb");
    	Query query = new Query(Criteria.where("phone_Numb").is(phone_Numb));
    	user_info_bean user_info_bean =mongoTemplate.findOne(query, user_info_bean.class, "mvc");
    	URL url =oss_service.get_oss_url(user_info_bean.getUserImage_Name());
    	user_info_bean.setUserImage_URL(url);
		return user_info_bean;
	}
	
	
	
	
	/*查询职位基本信息控制器*/
	@RequestMapping("/getall")
	@ResponseBody
	public Msg_bean getRoughWithJson_computer(@RequestParam(value="pn",defaultValue="1")Integer pn){
		    //引入pageHelper插件
			PageHelper.startPage(pn, 10);
		    //startPage后紧跟的查询就是分页查询
    		List<RoomUseRecord_Bean> find = mongoTemplate.findAll(RoomUseRecord_Bean.class, "record");
    		System.out.println("查找成功！");
		    //使用PageInfo包装查询后的结果
			//PageInfo封装了详细的分页信息包括查询出的数据，只需将pageInfo交给页面即可
			//参数5表示连续显示5页
			PageInfo page = new PageInfo(find,10);
			//JSONObject jsonObject = new JSONObject(page);
			return Msg_bean.success().add("pageInfo",page);
	}
	
	/*查询职位基本信息控制器*/
	@RequestMapping("/get_by_key")
	@ResponseBody
	public Msg_bean getRoughWithJson_computer_by_key(String phone_Numb){
		    //引入pageHelper插件
			PageHelper.startPage(1, 10);
		    //startPage后紧跟的查询就是分页查询
            Query query =new Query(Criteria.where("phone_Numb").is(phone_Numb));
    		List<RoomUseRecord_Bean> find = mongoTemplate.find(query, RoomUseRecord_Bean.class, "record");
		    //使用PageInfo包装查询后的结果
			//PageInfo封装了详细的分页信息包括查询出的数据，只需将pageInfo交给页面即可
			//参数5表示连续显示5页
			PageInfo page = new PageInfo(find,10);
			
    		if(find.size()!=0){
    			return Msg_bean.success().add("pageInfo", page);
    		}else{
    			Msg_bean msg_bean =new Msg_bean("1", "用户不存在!",null);
    			return msg_bean;
    		}
	}
	
	/*用户单个查询时用到*/
	@RequestMapping("/client_get_by_key")
	@ResponseBody
	public Msg_bean<RoomUseRecord_Bean> getuser_by_key_client(String phone_Numb){
			System.out.println(phone_Numb);
            Query query =new Query(Criteria.where("phone_Numb").is(phone_Numb));
            RoomUseRecord_Bean find = mongoTemplate.findOne(query, RoomUseRecord_Bean.class, "record");

    		List<RoomUseRecord_Bean>user_Beans = new ArrayList<>();
    		user_Beans.add(find);

    		if(find!=null){
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("13","用户查找成功",user_Beans);
    			System.out.println("查找成功");
    			return msg_bean;
    		}else{
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("12","用户不存在！",null);
    			System.out.println("查找失败");
    			return msg_bean;
    		}
	}
	
	
	
    @ResponseBody
    @RequestMapping("/server_updata")
    public Msg_bean MongoJDBC_updata_server(String roomnumber_update,String overTime_updata) {
    		System.out.println(roomnumber_update+overTime_updata);
    	
    		   // Update
    		Query query =new Query(Criteria.where("roomnumber").is(roomnumber_update));
    		
    		RoomUseRecord_Bean find = mongoTemplate.findOne(query, RoomUseRecord_Bean.class, "record");
    		if(find!=null){
        		Update update =new Update();
        		mongoTemplate.upsert(query,update.update("overTime", overTime_updata),RoomUseRecord_Bean.class,"record");
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("20","续房成功",null);
    			System.out.println("入住者信息修改成功！");
    			return msg_bean;
    		}else{
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("21","用户没有开房记录！",null);
    			System.out.println("退房失败,用户不存在");
    			return msg_bean;
    		}
	}
    
    
	/*用户退房*/
	@RequestMapping("/user_overUse_room")
	@ResponseBody
	public Msg_bean<RoomUseRecord_Bean> user_overUse_room(String roomNumber){
			System.out.println(roomNumber);
            Query query =new Query(Criteria.where("roomnumber").is(roomNumber));
            RoomUseRecord_Bean find = mongoTemplate.findOne(query, RoomUseRecord_Bean.class, "record");
            
    		if(find!=null){
 		        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
 		        String Nowdate = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        		Update update =new Update();
        		mongoTemplate.upsert(query,update.update("overTime", Nowdate),RoomUseRecord_Bean.class,"record");
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("20","退房成功",null);
    			System.out.println("退房成功");
    			return msg_bean;
    		}else{
    			Msg_bean<RoomUseRecord_Bean> msg_bean = new Msg_bean<>("21","用户没有开房记录！",null);
    			System.out.println("退房失败,用户不存在");
    			return msg_bean;
    		}
	}
	
	
	
}
