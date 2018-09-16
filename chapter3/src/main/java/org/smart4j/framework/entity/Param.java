package org.smart4j.framework.entity;

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

/***
 * 封装客户端请求参数
 * @author acer
 *
 */

public class Param {
	private Map<String,Object> paramMap;
	public Param(Map<String,Object> paramMap){
		this.paramMap = paramMap;
	}
	//获取所有字段信息
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	//根据参数名，获取long类型参数
	public long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	
	
}
