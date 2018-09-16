package org.smart4j.framework.entity;

public class Data {
	/***
	 * 由于视图中是可以包含模型数据的，因此在view中包括了视图路径和该视图中所需的模型数据，该模型数据是一个Map类型的“键值对”,可在视图中根据模型的键名获取键值
	 * 模型数据
	 */
	
	
	private Object model;
	
	public Data(Object model){
		this.model = model;
	}
	
	public Object getModel(){
		return this.model;
	}
	
	
}
