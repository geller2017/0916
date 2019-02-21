package org.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;


/***
 * 类操作帮助类
 * @author Administrator
 *
 */

public final class ClassHelper {
	/***
	 * 定义类集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	static{
		String base_package = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(base_package);
	}
	
	//获取应用下所有的类
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	//获取应用下所有的Service类
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> serviceClassSet = new HashSet<>();
		for(Class<?> clazz:serviceClassSet){
			if(clazz.isAnnotationPresent(Service.class)){
				serviceClassSet.add(clazz);
			}
		}
		return serviceClassSet;
	}
	//获取应用下所有的Controller类
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> controllerClassSet = new HashSet<>();
		for(Class<?> clazz:controllerClassSet){
			if(clazz.isAnnotationPresent(Service.class)){
				controllerClassSet.add(clazz);
			}
		}
		return controllerClassSet;
	}
	
	//获取系统中全部被注解的类
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> cotrollerClasses = getControllerClassSet();
		Set<Class<?>> serviceClasses = getServiceClassSet();
		Set<Class<?>> beans = new HashSet<>();
		beans.addAll(cotrollerClasses);
		beans.addAll(serviceClasses);
		return beans;
	}
	
}
