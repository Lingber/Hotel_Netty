package com.lingber.dao;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月28日 下午8:35:51 
* 类说明 :
*/
public class MyQRCodeImage implements QRCodeImage {

	
    BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
    }

    //宽
    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    //高
    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    //像素还是颜色
    @Override
    public int getPixel(int i, int j) {
        return bufferedImage.getRGB(i,j);
    }

}
