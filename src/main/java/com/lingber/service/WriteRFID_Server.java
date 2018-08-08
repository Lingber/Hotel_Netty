package com.lingber.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月20日 上午9:13:34 
* 类说明 :
*/
public class WriteRFID_Server{

	class Handler implements Runnable{
	    private Socket socket;
	    time_server time_server =new time_server();
	    public Handler(Socket socket){
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
	    public String echo(String msg){
	        return "echo:"+msg;
	    }
	    public void run(){
	        try {
	            System.out.println("New connection accepted:"+socket.getInetAddress()+":"+socket.getPort());
	            BufferedReader br=getReader(socket);
	            PrintWriter pw=getWriter(socket);
	            String msg=null;
	            while((msg=br.readLine())!=null){
	            	//主要的设计逻辑
	            	
	                System.out.println(msg);
	                /*time_server.compareTime(RFID_time, Last_time);*/
	                pw.println(echo(msg));
	                /*if(msg.equals("/n"))*/
	                    break;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                if(socket!=null){
	                	socket.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
}
