package com.lingber.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import com.lingber.bean.OpenDoorsRecord_bean;
import com.lingber.bean.RoomUseRecord_Bean;
import com.lingber.service.MultiThreadServer;
import com.lingber.service.RoomUserRecord_Server;
import com.lingber.service.time_server;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月13日 下午9:45:08 
* 类说明 :
*/

@RequestMapping("/socket") 
@Controller
public class Socket_controller {
	
	@Autowired
	private MongoTemplate mongoTemplate;

    private ExecutorService executorService ;//线程池
    private final int POOL_SIZE=100;//单个CPU线程池大小
    
    @ResponseBody
    @RequestMapping("/creatSocket") 
	public Msg_bean  creatSocket(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
    	MultiThreadServer multiThreadServer =new MultiThreadServer();
    	
    	Socket socket =multiThreadServer.service();
    	System.out.println("获得Scoket");
		executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
		System.out.println("启动新线程");
		executorService.execute(new Reader(socket));
    	
		//创建session
    	HttpSession httpSession = request.getSession(); 
        // 向session保存一个Scoket连接  
        httpSession.setAttribute("socket", socket); 
        System.out.println("已将socket保存进session:");
        // 向session保存一个ServerScoket
        ServerSocket serverSocket= multiThreadServer.GetServerSocket();
        httpSession.setAttribute("serverSocket", serverSocket); 
        System.out.println("已将socket保存进session:");
        Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
        cookie.setMaxAge(43200*60);// 设置为30天  
        cookie.setPath("/Hotel_Server");  
        System.out.println("已添加===============");  
        response.addCookie(cookie);
    	
    	List<Msg_bean>Msg_bean_list =new ArrayList<>();
		
		Msg_bean<Msg_bean> msg_bean =new Msg_bean("01", "socket开启成功！",Msg_bean_list);
		return msg_bean;
	}
    
    
    @ResponseBody
    @RequestMapping("/waitClient") 
	public Msg_bean  wait_client_data(HttpServletRequest request,HttpServletResponse response,String roomnumber,
			String phone_Numb,String overTime,String userName,String iDNumber) throws IOException{
    	System.out.println(phone_Numb+iDNumber);
		System.out.println("进入");
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
        if (null==cookies) {  
	            System.out.println("没有cookie=========");
				Msg_bean msg_bean =new Msg_bean("03", "写入失败，没有Cooke！",null);
				return msg_bean;
        } else {  
        		if (httpSession.getAttribute("socket")!=null) {
        			System.out.println("有东西！");
        			Socket socket= (Socket) httpSession.getAttribute("socket");
        			executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        			executorService.execute(new Handler(socket,roomnumber,phone_Numb,overTime,userName,iDNumber));
				}else {
					System.out.println("没有东西！");
					Msg_bean msg_bean =new Msg_bean("04", "Socket不存在",null);
					return msg_bean;
				}
        		System.out.println("写完！");
				Msg_bean msg_bean =new Msg_bean("05", "写入成功！",null);
				return msg_bean;
        }  
	}
    
    @ResponseBody
    @RequestMapping("/closeScoket") 
	public Msg_bean  close_client_socket(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("进入");
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
        if (null==cookies) {  
	            System.out.println("没有cookie=========");
				Msg_bean msg_bean =new Msg_bean("03", "写入失败，没有Cooke！",null);
				return msg_bean;
        } else {  
        		if (httpSession.getAttribute("socket")!=null) {
        			System.out.println("有东西！");
        			Socket socket= (Socket) httpSession.getAttribute("socket");
        			socket.close();
				}else {
					System.out.println("没有东西！");
					Msg_bean msg_bean =new Msg_bean("04", "Socket不存在",null);
					return msg_bean;
				}
        		System.out.println("关完！");
				Msg_bean msg_bean =new Msg_bean("06", "关闭成功！",null);
				return msg_bean;
        }  
	}
    
    

    
    
    //信息读取 方法
	class Reader implements Runnable{
	    private Socket socket;
	    private String roomnumber;
	    private String phone_Numb;
	    private String overTime;
	    RoomUserRecord_Server roomUserRecord_Server =new RoomUserRecord_Server();
	    public Reader(Socket socket){
	        this.socket=socket;
	    }
	    private PrintWriter getWriter(Socket socket) throws IOException{
	        OutputStream socketOut=socket.getOutputStream();
	        return new PrintWriter(socketOut,true);
	    }
	    private BufferedReader getReader(Socket socket) throws IOException{
	        InputStream socketIn=socket.getInputStream();
	        return new BufferedReader(new InputStreamReader(socketIn));
	    }


	    public void run(){
	        try {
	        	 while (true) {
	        	System.out.println("开始获取服务器信息！");
	        	BufferedReader br=getReader(socket);
	            System.out.println("New你爹de  connection accepted:"+socket.getInetAddress()+":"+socket.getPort());
	            PrintWriter pw=getWriter(socket);
	            String msg=br.readLine();
	            String head=msg.substring(0, 2);
	            System.out.println("head+++"+head);
		         //   if (head.equals("10")) {
            	System.out.println(":"+head+"/n");
            	System.out.println("1123+"+br.readLine());
    	        this.roomnumber=msg.substring(0, 5);
    	        this.phone_Numb=msg.substring(5, 16);
    	        System.out.println(roomnumber);
    	        System.out.println(phone_Numb);
            	//根据电话号查用户持卡记录
				Query query_time =new Query(Criteria.where("phone_Numb").is(phone_Numb));
				List<RoomUseRecord_Bean> roomUseRecord_finds=mongoTemplate.find(query_time, RoomUseRecord_Bean.class, "record");
				 Collections.reverse(roomUseRecord_finds); // 倒序排列 
         	    //获取当前时间
         	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
         	    String NowTime = df.format(new Date());//new Date()为获取当前系统时间，也可使用当前时间戳
		         	    //获取最后退房时间
		         	  //  for (RoomUseRecord_Bean roomUseRecord_Bean : roomUseRecord_finds) {
         	    		RoomUseRecord_Bean roomUseRecord_Bean = roomUseRecord_finds.get(0);
			         	    int result=0;
			            	if (roomUseRecord_Bean!=null) {
			            		String room_overtime=roomUseRecord_Bean.getOverTime();
			            		System.out.println(room_overtime);
			            		result=room_overtime.compareTo(NowTime);
			            		System.out.println(room_overtime.compareTo(NowTime));
			            		//增加开门记录
							}else {
								System.out.println("查询为空");
							}
			            	if(result>0){
			            	   System.out.println("允许开门！");
				         	   System.out.println("增加开门记录！");
				         	  System.out.println(roomnumber);
				         	  System.out.println(NowTime);
				         	  System.out.println(phone_Numb);
			 	         	   OpenDoorsRecord_bean openDoorsRecord_bean =new OpenDoorsRecord_bean(roomnumber, NowTime, phone_Numb);
			 	         	   if (openDoorsRecord_bean!=null) {
			 	         		 mongoTemplate.insert(openDoorsRecord_bean, "open");
			 	         		 System.out.println("开门记录成功");
							}else {
								System.out.println("开门记录不成功");
							}
			            		//在时间范围内，允许开门
			            		pw.println("0100");
			            	}else{
			            		//超时,不允许开门
			            		System.out.println("不允许开门！");
			            		pw.println("0000");
			            	}
							
						//}
				}

	                
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
            try {
	                if(socket!=null)
	                    socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
    
	
    
    //信息写入方法
	class Handler implements Runnable{
	    private Socket socket;
	    private String sent_data;
	    private String roomnumber;
	    private String phone_Numb;
	    private String overTime;
		private String userName;
		private String iDNumber;
	    RoomUserRecord_Server roomUserRecord_Server =new RoomUserRecord_Server();
	    time_server time_server =new time_server();
	    public Handler(Socket socket,String roomnumber,String phone_Numb,String overTime,String userName,String iDNumber){
	        this.socket=socket;
	        this.sent_data=roomnumber+phone_Numb;
	        this.roomnumber=roomnumber;
	        this.phone_Numb=phone_Numb;
	        this.overTime=overTime;
	        this.userName=userName;
	        this.iDNumber=iDNumber;
	    }
	    private PrintWriter getWriter(Socket socket) throws IOException{
	        OutputStream socketOut=socket.getOutputStream();
	        return new PrintWriter(socketOut,true);
	    }
	    private BufferedReader getReader(Socket socket) throws IOException{
	        InputStream socketIn=socket.getInputStream();
	        return new BufferedReader(new InputStreamReader(socketIn));
	    }


	    public void run(){
	        try {
	            System.out.println("New connection accepted:"+socket.getInetAddress()+":"+socket.getPort());
	            BufferedReader br=getReader(socket);
	            PrintWriter pw=getWriter(socket);
	            String msg=null;
	            
	           /* char firstCode=(msg=br.readLine()).charAt(0);
	            char seconCode=(msg=br.readLine()).charAt(1);*/
	            
	      //      if ((msg=br.readLine()).equals("0001")) {
	          //  	System.out.println(msg);
	            	pw.println(sent_data);
	         	   //插入开房时间
	         	   SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	         	   String startTime = df.format(new Date());//new Date()为获取当前系统时间，也可使用当前时间戳
	         	   
	         	   RoomUseRecord_Bean roomUseRecord_Bean =new RoomUseRecord_Bean(roomnumber, startTime, overTime, phone_Numb, userName, iDNumber);
	         	   mongoTemplate.insert(roomUseRecord_Bean, "record");
	         	   System.out.println("插入成功！");
	            	/*roomUserRecord_Server.insertRoomUserRecord(roomnumber, phone_Numb);*/
				//}else {
					//触发检索程序
					
			//	}
	                

	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
/*	            try {
	                if(socket!=null)
	                    socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }*/
	        }
	    }

	}
}
