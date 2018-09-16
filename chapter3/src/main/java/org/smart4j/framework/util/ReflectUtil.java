package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 反射工具
 * @author Administrator
 *
 */
public final class ReflectUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class); 
	//创建实例
	public static Object newInstance(Class<?> clazz){
		Object o = null;
		try {
			o = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instance failed");
			e.printStackTrace();
		} 
		return o;
	}
	//执行方法，返回结果
	public static Object invokeMethod(Object object,Method method,Object... params){
		Object result = null;
		
		try {
			method.setAccessible(true);
			result = method.invoke(object, params);
		} catch (Exception e) {
			LOGGER.error("method invoke failed");
			e.printStackTrace();
		} 
		return result;
	}
	//设置成员变量的值
	public static void setField(Object object,Field field,Object value){
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			LOGGER.error("field setted failed");
			e.printStackTrace();
		}
	}
}
