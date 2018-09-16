package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectUtil;

/***
 * 对象管理类
 * 存储所有注入的对象，并且提供获取对象的方法
 * @author Administrator
 *
 */
public class BeanHelper {
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();
	static{
		Set<Class<?>> beanClass = ClassHelper.getBeanClassSet();
		for(Class<?> clazz:beanClass){
			BEAN_MAP.put(clazz, ReflectUtil.newInstance(clazz));
		}
	}
	
	//获取对象集合
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	//根据类获取对象实例
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		if(!BEAN_MAP.containsKey(clazz)){
			throw new RuntimeException("has no this class instance:"+clazz.getName());
		}
		return (T) BEAN_MAP.get(clazz);
	}
	
}
