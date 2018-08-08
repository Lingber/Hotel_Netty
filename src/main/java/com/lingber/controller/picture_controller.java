package com.lingber.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingber.bean.Msg_bean;
import com.lingber.bean.picrure_content_bean;
import com.lingber.service.base64_server;
import com.lingber.service.oss_service;
import com.lingber.service.scan_server;

import sun.misc.BASE64Decoder;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月28日 下午6:56:41 
* 类说明 :
*/

@RequestMapping("/picture") 
@Controller
public class picture_controller {
	
	@Autowired
	private oss_service oss_service;
	
	@ResponseBody
    @RequestMapping("scan")
    public Msg_bean<picrure_content_bean> scan_pic(HttpServletRequest request,String img) throws IllegalStateException, IOException
    {
    	System.out.println("进入！");
    	/*System.out.println(img);*/
    	List<picrure_content_bean>lPicrure_content_beans =new ArrayList<>();
    	
    	String[] arr = img.split(","); 
    	String img_string =arr[1];
    	System.out.println(img_string);
    	
    	Msg_bean<picrure_content_bean>msg_bean_fales = new Msg_bean<>("14", "未上传图片！", null);
    	base64_server base64_server = new base64_server();
    	base64_server.GenerateImage(img_string);
    	
        //生成jpeg图片  
        String imgFilePath = "f://scan_over.jpg";//新生成的图片  
        scan_server scan_server = new scan_server();
        String result=null;
		try {
			result = scan_server.decoderQRCode(imgFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("不能识别");
		}
/*	        File file_del =new File(imgFilePath);
	       //从服务器的缓存中删除文件
	        file_del.delete();*/

        picrure_content_bean picrure_content_bean =new picrure_content_bean(result);
        lPicrure_content_beans.add(picrure_content_bean);
        Msg_bean<picrure_content_bean>msg_bean_ture = new Msg_bean<>("15", "图片扫描成功！", lPicrure_content_beans);
        System.out.println(result);
        return msg_bean_ture;  
        }
    
    
	    @ResponseBody
        @RequestMapping("situ")
    	public URL situOfOut() {
    		URL url = oss_service.get_oss_url("socket_server.jpg");
			return url;
		}

}
