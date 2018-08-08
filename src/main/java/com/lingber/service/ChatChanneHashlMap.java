package com.lingber.service;

import java.util.HashMap;
import java.util.UUID;

import io.netty.channel.Channel;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年6月30日 下午12:09:31 
* 类说明 :
*/
public class ChatChanneHashlMap {
	//通道个数
    public static int channelNum=0;
    //定义HashMap
    private static HashMap<String,Channel> channelHashMap=null;
    
    public static HashMap<String, Channel> getChannelHashMap() {
        return channelHashMap;
    }
    //根据Key查找HashMap里的Value
    public static Channel getChannelByName(String name){
        if(channelHashMap==null||channelHashMap.isEmpty()){
        	System.out.println("查找为空");
            return null;
        }
        return channelHashMap.get(name);
    }
    
    //向HashMap里添加键值对
    public static void addChannel(String name,Channel channel){
        if(channelHashMap==null){
            channelHashMap=new HashMap<>(100);
        }
        channelHashMap.put(name,channel);
        channelNum++;
    }
    
    //删除键值对
    public static int removeChannelByName(String name){
        if(channelHashMap.containsKey(name)){
            channelHashMap.remove(name);
            System.out.println("HashMap key-Value删除成功！");
            return 0;
        }else{
            return 1;
        }
    }
    
	public String randomName(){
    	UUID uuid = UUID.randomUUID();
    	String str = uuid.toString();
    		// 去掉"-"符号
    	String temp = str.substring(0, 4);
    	 return temp;
	}
	
}
