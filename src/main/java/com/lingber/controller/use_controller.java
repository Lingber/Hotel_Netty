package com.lingber.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingber.bean.Msg_bean;
import com.lingber.bean.RoomUseRecord_Bean;
import com.lingber.service.RoomUserRecord_Server;
import com.lingber.service.time_server;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月30日 下午8:48:20 
* 类说明 :
*/

@RequestMapping("/use") 
@Controller
public class use_controller {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

    private ExecutorService executorService ;//线程池
    private final int POOL_SIZE=100;//单个CPU线程池大小
	
    @ResponseBody
    @RequestMapping("/use") 
	public Msg_bean  server_login_CheckUp(HttpServletRequest request,HttpServletResponse response,String action) throws ServletException, IOException{
    	System.out.println("进入");
    	System.out.println(action);
    	HttpSession httpSession = request.getSession(false);
    	Cookie[] cookies = request.getCookies();
        if (null==cookies) {  
	            System.out.println("没有cookie=========");
				Msg_bean msg_bean =new Msg_bean("02", "登录超时！请重新登录！",null);
				return msg_bean;
        } else {  
        		System.out.println(httpSession.getAttribute("room_number"));
        		String room_number =(String) httpSession.getAttribute("room_number");
        		System.out.println(room_number);
        		String action_send=action;
        		char send_datas[];
        		if (action_send.equals("01")) {
        			char send_data[]={0x00,0x01,0x01,0x01,0x23,0x45,0x67,0x00,0x00,0x00};
        			send_datas =send_data;
				}else {
        			char send_data[]={0x00,0x01,0x02,0x01,0x23,0x45,0x67,0x00,0x00,0x00};
					send_datas =send_data;
				}
                		if (httpSession.getAttribute("socket")!=null) {
                			System.out.println("有东西！");
                			Socket socket= (Socket) httpSession.getAttribute("socket");
                			executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
                			executorService.execute(new Handler(socket,send_datas));
        				}else {
        					System.out.println("没有东西！");
        					Msg_bean msg_bean =new Msg_bean("04", "Socket不存在",null);
        					return msg_bean;
        				}
                		System.out.println("写完！");
        				Msg_bean msg_bean =new Msg_bean("03", "开启成功！",null);
        				return msg_bean;
	                }  
	              //写完
        		}
	}
	
	

//信息写入方法
class Handler implements Runnable{
    private Socket socket;
    private char[] sent_data;
    RoomUserRecord_Server roomUserRecord_Server =new RoomUserRecord_Server();
    time_server time_server =new time_server();
    public Handler(Socket socket,char[] send_datas){
        this.socket=socket;
        this.sent_data=send_datas;
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
            System.out.println("New尼玛 connection accepted:"+socket.getInetAddress()+":"+socket.getPort());
            BufferedReader br=getReader(socket);
            PrintWriter pw=getWriter(socket);
            String msg=null;
            	pw.println(sent_data);
            	msg =br.readLine();
            	if (msg!=null) {
					System.out.println(msg);
				}

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
