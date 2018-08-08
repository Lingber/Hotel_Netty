package com.lingber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingber.bean.Msg_bean;
import com.lingber.bean.OpenDoorsRecord_bean;
import com.lingber.bean.RoomUseRecord_Bean;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月27日 下午4:40:05 
* 类说明 :
*/

@RequestMapping("/open") 
@Controller
public class open_controller {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/*查询职位基本信息控制器*/
	@RequestMapping("/getall")
	@ResponseBody
	public Msg_bean getRoughWithJson_computer(@RequestParam(value="pn",defaultValue="1")Integer pn){
		    //引入pageHelper插件
			PageHelper.startPage(pn, 10);
		    //startPage后紧跟的查询就是分页查询
    		List<OpenDoorsRecord_bean> find = mongoTemplate.findAll(OpenDoorsRecord_bean.class, "open");
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
    		List<OpenDoorsRecord_bean> find = mongoTemplate.find(query, OpenDoorsRecord_bean.class, "open");
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
	
	
}
