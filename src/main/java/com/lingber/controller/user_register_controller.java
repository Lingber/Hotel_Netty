package com.lingber.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lingber.bean.Msg_bean;
import com.lingber.bean.User_Bean;
import com.lingber.bean.user_info_bean;
import com.lingber.service.SMS_Radom_tool;
import com.lingber.service.oss_service;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年4月1日 下午2:45:33 
* 类说明 :注册控制器
*/

@RequestMapping("/register") 
@Controller
public class user_register_controller {
	//引入mongoTemplate全局变量
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//引入阿里云短信接口以及图片上传接口
	@Autowired
	private oss_service oss_service;
	
	private String SMS_Code = null;
	
	
	//接收注册请求接口
	@ResponseBody
    @RequestMapping("/register_request")  
    public Msg_bean register_client(String username,String phonenumbRegister,String code,String RegisterpassWord,String userIDcard_Number,HttpServletRequest request,HttpServletResponse response){
		if (code.equals(this.SMS_Code)) {//判断验证码是否正确
			//新建用户
			user_info_bean user_Bean = new user_info_bean();//新建userBean
			user_Bean.setUserName(username);
			user_Bean.setPhone_Numb(phonenumbRegister);
			//生成user_id
			user_Bean.setPassWord(RegisterpassWord);
			URL url =oss_service.get_oss_url("f3b2a2c14fb04d56985b14bfccb8749d.jpg");  //提供默认的头像
			user_Bean.setUserImage_URL(url);
			user_Bean.setUserImage_Name("f3b2a2c14fb04d56985b14bfccb8749d.jpg");
			user_Bean.setUserIDcard_Number(userIDcard_Number);
			mongoTemplate.insert(user_Bean, "mvc");
			System.out.println("插入成功！");
			List<user_info_bean>user_Beans=new ArrayList<>();
			user_Beans.add(user_Bean);
			//添加session
			//创建session
	    	HttpSession httpSession = request.getSession(); 
	        // 向session 用户ID以及用户名，设备号  
	        httpSession.setAttribute("phone_Numb", phonenumbRegister); 
	        System.out.println("已将phone_Numb保存进session:"+phonenumbRegister);
	        httpSession.setAttribute("userName", username); 
	        System.out.println("已将username保存进session:"+username);
	        httpSession.setAttribute("userName", username);
	        Cookie cookie = new Cookie("JSESSIONID2", httpSession.getId());
	        cookie.setMaxAge(43200*60);// 设置为30天  
	        cookie.setPath("/Picture_SpringMvc");  
	        System.out.println("已添加===============");  
	        response.addCookie(cookie);
			Msg_bean msg_bean =new Msg_bean("04", "注册成功",user_Beans);//将返回的消息进行包装
			return msg_bean;
		}else{
			Msg_bean msg_bean =new Msg_bean("05", "注册失败！验证码不符！",null);
			return msg_bean;
		}
    }
	
	    
	
	//生成短信
    @ResponseBody
    @RequestMapping("/sms")  
    public Msg_bean getSMS_code(String phonenumbRegister) throws ClientException{
		//判断用户是否存在
		Query query =new Query(Criteria.where("phone_Numb").is(phonenumbRegister));
		List<User_Bean> find = mongoTemplate.find(query, User_Bean.class, "mvc");
		if(find.size()!=0){
			System.out.println("用户手机号已经存在");
			Msg_bean msg_bean =new Msg_bean("03", "用户手机号已经存在!",null);
			return msg_bean;
		}else{
	    	//aliyun短信接口调用开始
			//设置超时时间-可自行调整
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			//初始化ascClient需要的几个参数
			final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
			//替换成你的AK
			final String accessKeyId = "#";//你的accessKeyId,参考本文档步骤2
			final String accessKeySecret = "#";//你的accessKeySecret，参考本文档步骤2
			//初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
			accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			 //组装请求对象
			 SendSmsRequest request = new SendSmsRequest();
			 //使用post提交
			 request.setMethod(MethodType.POST);
			 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			 request.setPhoneNumbers(phonenumbRegister);
			 //必填:短信签名-可在短信控制台中找到
			 request.setSignName("#");
			 //必填:短信模板-可在短信控制台中找到
			 request.setTemplateCode("#");
			 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			 /*request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");*/
			 
			 //随机生成6为的数字
			 SMS_Radom_tool sms_Radom_tool =new SMS_Radom_tool();
			 String code=sms_Radom_tool.Radom_SMS();
			 //将验证码记录一下
			 this.SMS_Code=code;
			 System.out.println(code);
			 request.setTemplateParam("{\"code\":\""+code+"\"}");
			 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			 //request.setSmsUpExtendCode("90997");
			 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			 request.setOutId("#");
			//请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//请求成功
				System.out.println("OK");
				Msg_bean msg_bean =new Msg_bean("01", "短信发送成功！",null);
				return msg_bean;
			}else{
				System.out.println(sendSmsResponse.getCode());
				Msg_bean msg_bean =new Msg_bean("02", sendSmsResponse.getCode(),null);
				return msg_bean;
			}

		}

    }
    
    
}
