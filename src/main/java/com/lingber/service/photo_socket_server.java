package com.lingber.service;

import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.ServerSocket;  
import java.net.Socket;  
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月19日 下午6:31:34 
* 类说明 :
*/
public class photo_socket_server {
	
	public static final oss_service oss_service = new oss_service();
	
    public static void main(String[] args) throws Exception{  
    	 while (true) {
        //1.服务器开始监听5612端口  
        ServerSocket ss = new ServerSocket(10086);  
        System.out.println("服务端已启动，正在监听10086端口...");  
        //等待客户端  
        Socket s = ss.accept();  
        System.out.println("检测到客户端，准备数据接收...");  
        //客户端已连接，获取输入流  
        InputStream in = s.getInputStream();  
        //创建图片字节流  
        FileOutputStream fos = new FileOutputStream("D:/My_project/java/Mycode/Hotel_Server/WebContent/static/media/socket_server.jpg");
        byte[] buf = new byte[5242880];  
        int len = 0;  
        //往字节流里写图片数据  
        while ((len = in.read(buf)) != -1)  
        {  
            fos.write(buf,0,len);  
        } 
        //获取输出流，准备给客户端发送消息  
/*        OutputStream out = s.getOutputStream();  
        out.write("上传成功".getBytes());*/
        //关闭资源  
        fos.close();  
        in.close();  
        /*out.close();  */
        s.close();  
        ss.close();  
        System.out.println("接收成功");
        oss_service.del_oss("socket_server.jpg");
        oss_service.uploadFile_to_oss("D:/My_project/java/Mycode/Hotel_Server/WebContent/static/media/socket_server.jpg", "socket_server.jpg");
        }
    }  

}
