package org.smart4j.framework.entity;

import java.lang.reflect.Method;

/***
 * 封装Controller处理方法信息实体
 * @author acer
 *
 */

public class Handler {
	/***
	 * 方法所在类，方法信息
	 */
	
	private Class<?> clz;
	private Method method;
	public Class<?> getClz() {
		return clz;
	}
	public Method getMethod() {
		return method;
	}
	
	
	public Handler(Class<?> clz,Method method){
		this.clz = clz;
		this.method = method;
	}
	
		
}
