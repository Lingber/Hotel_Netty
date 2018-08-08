package com.lingber.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lingber.dao.MyQRCodeImage;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月28日 下午8:25:37 
* 类说明 :
*/
public class scan_server {
	
	  public String decoderQRCode(String imgPath) throws IOException {   
	      //图片路径
	      File file = new File(imgPath);
	      //读取图片到缓冲区
	      BufferedImage bufferedImage = ImageIO.read(file);
	      //QRCode解码器
	      QRCodeDecoder codeDecoder = new QRCodeDecoder();
	      /**
	       *codeDecoder.decode(new MyQRCodeImage())
	       *这里需要实现QRCodeImage接口，移步最后一段代码
	       */
	      //通过解析二维码获得信息
	      String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
		return result;   
		
	 }

 
public static void main(String[] args) throws IOException {
	scan_server scan_server =new scan_server();
	String result=scan_server.decoderQRCode("F:\\QR.jpg");
	System.out.println(result);
}

}
