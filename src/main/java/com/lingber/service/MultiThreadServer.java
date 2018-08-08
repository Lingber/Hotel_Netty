package com.lingber.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月13日 下午10:03:16 
* 类说明 :
*/
public class MultiThreadServer {
	
	  private int port=10086;
	    private ServerSocket serverSocket;
/*	    private ExecutorService executorService ;//线程池
	    private final int POOL_SIZE=100;//单个CPU线程池大小
*/	    
	    public MultiThreadServer() throws IOException{
	        /*serverSocket = new ServerSocket(port);*/
	    	serverSocket = new ServerSocket(port);
	        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
	        /*executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);*/
	        System.out.println("服务器启动");
	    }
	    
	    public Socket service(){
	            Socket socket=null;
	            try {
	                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
	                socket=serverSocket.accept();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return socket;
/*	    	
	        while(true){
	            Socket socket=null;
	            try {
	                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
	                socket=serverSocket.accept();
	                executorService.execute(new Handler(socket));
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	  */      
	    }
	    
	    public ServerSocket GetServerSocket() {
	    	
			return this.serverSocket;
		}
	}


/*
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
	                time_server.compareTime(RFID_time, Last_time);
	                pw.println(echo(msg));
	                if(msg.equals("/n"))
	                    break;
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
*/	
	
	
