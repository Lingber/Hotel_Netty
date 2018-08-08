package com.lingber.service;

import java.io.FileInputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月19日 下午6:32:15 
* 类说明 :
*/
public class photo_socket_client {
	
    public static void main(String[] args) throws Exception{  
        //1.连接诶服务器  
        Socket s = new Socket("127.0.0.1",10086);  
        System.out.println("已连接到服务器5612端口，准备传送图片...");  
        //获取图片字节流  
        FileInputStream fis = new FileInputStream("F:/socket_server.jpg");
        //获取输出流  
        OutputStream out = s.getOutputStream();  
        byte[] buf = new byte[5242880]; 
        int len = 0;  
        //2.往输出流里面投放数据  
        while ((len = fis.read(buf)) != -1)  
        {  
            out.write(buf,0,len);  
        }  
        //通知服务端，数据发送完毕  
        s.shutdownOutput();  
        //3.获取输出流，接受服务器传送过来的消息“上传成功”  
        InputStream in = s.getInputStream();  
        byte[] bufIn = new byte[5242880];  
        int num = in.read(bufIn);  
        /*System.out.println(new String(bufIn,0,num));*/  
        System.out.println("上传成功");
        //关闭资源  
        fis.close();  
        out.close();  
        in.close();  
        s.close();  
    }  

}
