package org.smart4j.framework.entity;

import java.lang.reflect.Method;

/***
 * ��װController��������Ϣʵ��
 * @author acer
 *
 */

public class Handler {
	/***
	 * ���������࣬������Ϣ
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
