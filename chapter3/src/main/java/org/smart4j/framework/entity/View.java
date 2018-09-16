package org.smart4j.framework.entity;

import java.util.HashMap;
import java.util.Map;

/***
 * 封装服务返回信息
 * @author acer
 *
 */

public class View {
	//参数路径
	private String path;
	//模型数据
	private Map<String,Object> model;
	
	public View(String path){
		this.path = path;
		this.model = new HashMap<>();
	}

	public String getPath() {
		return path;
	}

	public Map<String, Object> getModel() {
		return model;
	}
	
	
	public View addModel(String key,Object value){
		model.put(key, value);
		return this;
	}
	
	
}
