package com.lingber.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月4日 下午4:00:54 
* 类说明 :
 * @param <E>
*/
public class Msg_bean<E> {
	
	private String Msg_code;
	private String Msg_content;
	private Map<String, Object> extend = new HashMap<String,Object>();
	private List<E> result;
	
	public static Msg_bean success(){
		Msg_bean result = new Msg_bean();
		result.setMsg_code("100");
		result.setMsg_content("处理成功！");
		return result;
	}

	public static Msg_bean fail(){
		Msg_bean result = new Msg_bean();
		result.setMsg_code("200");
		result.setMsg_content("处理失败！");
		return result;
	}
	
	public String getMsg_code() {
		return Msg_code;
	}
	public void setMsg_code(String msg_code) {
		Msg_code = msg_code;
	}
	public String getMsg_content() {
		return Msg_content;
	}
	public void setMsg_content(String msg_content) {
		Msg_content = msg_content;
	}
	
	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}



	public Msg_bean(String msg_code, String msg_content, List<E> result) {
		super();
		Msg_code = msg_code;
		Msg_content = msg_content;
		this.result = result;
	}

	
	
	public Msg_bean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Msg_bean add(String key,Object value){
    	this.extend.put(key,value);
    	return this;
    }
	public Map<String, Object> getExtend() {
		return extend;
	}
	
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	

}
