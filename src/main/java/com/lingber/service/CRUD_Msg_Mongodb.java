package com.lingber.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import com.google.gson.Gson;
import com.lingber.bean.equipment_bean;
import com.lingber.bean.equipment_iscloseThedoor_bean;
import com.lingber.bean.equipment_messge_bean;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年7月1日 下午12:15:30 
* 类说明 :
*/
public class CRUD_Msg_Mongodb {
	
	
	//全局化CRUD_Mongodb类
	public static final CRUD_Msg_Mongodb crud_msg_Mongodb = new CRUD_Msg_Mongodb();
	
	//连接数据库
	public DBCollection connect_toMongodb(String collection) {
		if (collection!=null) {
	    try{   
	        // 连接到 mongodb 服务
	        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	        // 连接到数据库
	        DB database = mongoClient.getDB("hotel");
	        DBCollection mongo=database.getCollection(collection);
	        System.out.println("Connect to database successfully");
	        System.out.println("集合"+collection+"选择成功");
	        return mongo;
	     }catch(Exception e){
	        System.err.println( e.getClass().getName() + ": " + e.getMessage());
	        return null;
	     }
	  }else {
			System.out.println("参数为空");
			return null;
		}
	}
	
	//转换对象
	public DBObject changeObject(equipment_messge_bean object) {
		if (object!=null) {
        //转换成json字符串，再转换成DBObject对象
		JSONObject jsonObject = new JSONObject(object);	//这里不能转
		String string=jsonObject.toString();
        DBObject dbObject = (DBObject) JSON.parse(string);
        System.out.println(dbObject);
        return dbObject;
		}else {
			System.out.println("正解析对象参数为空");
			return null;
		}
	}
	
	//转换对象
	public DBObject changeObject_ofdoor(equipment_iscloseThedoor_bean object) {
		if (object!=null) {
        //转换成json字符串，再转换成DBObject对象
		JSONObject jsonObject = new JSONObject(object);	//这里不能转
		String string=jsonObject.toString();
        DBObject dbObject = (DBObject) JSON.parse(string);
        System.out.println(dbObject);
        return dbObject;
		}else {
			System.out.println("正解析对象参数为空");
			return null;
		}
	}
	
	//转换对象
	public equipment_bean rechangeObject(DBCursor cursor) {
		if (cursor!=null) {
		 //反转
	         Gson gson=new Gson();
		     while(cursor.hasNext())
		     {
		      DBObject obj=cursor.next();
		      //反转
		      equipment_bean equipment_bean=gson.fromJson(obj.toString(),equipment_bean.class);
		      return equipment_bean;
		     }
		     return null;
		}else {
			System.out.println("反解析对象参数为空");
			return null;
		}
	}
	
	//转换对象
	public List<equipment_messge_bean> rechangeObjects(DBCursor cursor) {
		if (cursor!=null) {
		 //反转
			List<equipment_messge_bean>equipment_messge_beans = new ArrayList<>();
	         Gson gson=new Gson();
		     while(cursor.hasNext())
		     {
		      DBObject obj=cursor.next();
		      //反转
		      equipment_messge_bean equipment_bean=gson.fromJson(obj.toString(),equipment_messge_bean.class);
		      System.out.println(equipment_bean);
		      equipment_messge_beans.add(equipment_bean);
		      return equipment_messge_beans;
		     }
		     return null;
		}else {
			System.out.println("反解析对象参数为空");
			return null;
		}
	}
	
	//插入对象
	public void insert_toMongodb(equipment_messge_bean object,String collection) {
			DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
	        mongo.insert(crud_msg_Mongodb.changeObject(object));
	        System.out.println("插入成功");  
	}
	
	//插入对象
	public void insert_toMongodb_ofdoor(equipment_iscloseThedoor_bean object,String collection) {
			DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
	        mongo.insert(crud_msg_Mongodb.changeObject_ofdoor(object));
	        System.out.println("插入成功");  
	}
	
	//查找
	public String find_toMongodb(String roomnumber,String collection) {
		if (roomnumber!=null) {
		DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
		//查找
		//查找参数
        DBObject obj = new BasicDBObject();
        obj.put( "roomnumber",roomnumber);
		DBCursor cursor = (DBCursor) mongo.find(obj);
		equipment_bean equipment_bean =crud_msg_Mongodb.rechangeObject(cursor);
			if (equipment_bean!=null) {
		    /*String roomnumber=equipment_bean.getRoomnumber();*/
			String  Channel_id=equipment_bean.getChannel_id();
			return Channel_id;
				}else {
					System.out.println("查询失败，没有相关记录");
					return null;
				}
		}else {
			System.out.println("设备号参数为空");
			return null;
		}
	}
	
	//查找
	public equipment_bean find_EquipByChannelID(String ChannelID,String collection) {
		if (ChannelID!=null) {
		DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
		//查找
		//查找参数
        DBObject obj = new BasicDBObject();
        obj.put( "channel_id",ChannelID);
		DBCursor cursor = (DBCursor) mongo.find(obj);
		equipment_bean equipment_bean =crud_msg_Mongodb.rechangeObject(cursor);
			if (equipment_bean!=null) {
				return equipment_bean;
			}else {
				return null;
			}
		}else {
			System.out.println("设备号参数为空");
			return null;
		}
	}
	
	
	//查找
	public List<equipment_messge_bean> find_EquipByRoomNumber(String roomnumber,String collection) {
		if (roomnumber!=null) {
		DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
		//查找
		//查找参数
        DBObject obj = new BasicDBObject();
        obj.put( "roomnumber",roomnumber);
		DBCursor cursor = (DBCursor) mongo.find(obj);
		List<equipment_messge_bean> equipment_messge_beans =crud_msg_Mongodb.rechangeObjects(cursor);
			if (equipment_messge_beans.size()!=0) {
				return equipment_messge_beans;
			}else {
				return null;
			}
		}else {
			System.out.println("设备号参数为空");
			return null;
		}
	}
	
	//查找
	public List<equipment_messge_bean> del_MsgByRoomNumber(String roomnumber,String collection) {
		if (roomnumber!=null) {
		DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
		//查找
		//查找参数
        DBObject obj = new BasicDBObject();
        obj.put( "roomnumber",roomnumber);
		DBCursor cursor = (DBCursor) mongo.findAndRemove(obj);
		List<equipment_messge_bean> equipment_messge_beans =crud_msg_Mongodb.rechangeObjects(cursor);
			if (equipment_messge_beans.size()!=0) {
				return equipment_messge_beans;
			}else {
				return null;
			}
		}else {
			System.out.println("设备号参数为空");
			return null;
		}
	}
	
	//更新
/*	public void updata_toMongodb(String roomnumber,equipment_bean equipment_bean,String collection) {
		if (roomnumber!=null) {
		DBCollection mongo=crud_msg_Mongodb.connect_toMongodb(collection);
		//查找
        DBObject obj = new BasicDBObject();
        obj.put( "roomnumber",roomnumber);
        System.out.println("根据roomnumber更新");
        equipment_bean equipment_bean2 = new equipment_bean("000001", null, "false", "false", "123456");
        System.out.println(equipment_bean.getChannel_id());
		mongo.update(obj,crud_msg_Mongodb.changeObject(equipment_bean));
	 }
	}*/
	
	public static void main(String[] args) {
	/*	crud_msg_Mongodb.updata_toMongodb("000001", null, "equip");*/
	}

}
