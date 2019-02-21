package org.smart4j.framework.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/***
 * �ͻ���������Ϣ��װ��
 * @author acer
 *
 */

public class Request {
	/***
	 * ���󷽷���get/post�� ����·��
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
	
	//�ö�����Ҫ�ŵ�hashMap��key�У�����Ҫ��дhashcode��equals
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
	
	
}
