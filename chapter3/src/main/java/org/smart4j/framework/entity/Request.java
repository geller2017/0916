package org.smart4j.framework.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/***
 * 客户端请求信息封装类
 * @author acer
 *
 */

public class Request {
	/***
	 * 请求方法（get/post） 请求路径
	 */
	private String requestMethod;
	private String requestPath;
	
	
	public String getRequestMethod() {
		return requestMethod;
	}
	public String getRequestPath() {
		return requestPath;
	}
	
	public Request(String requestMethod,String requestPath){
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}
	
	//该对象需要放到hashMap的key中，所以要重写hashcode和equals
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
	
	
}
