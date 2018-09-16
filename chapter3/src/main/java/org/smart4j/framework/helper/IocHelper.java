package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;

public class IocHelper {
	/***
	 * 依赖注入实现类
	 */
	static{
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(beanMap!=null){
			for(Entry<Class<?>, Object> entry:beanMap.entrySet()){
				Class<?> clazz = entry.getKey();
				Object obj = entry.getValue();
				Field[]  fields = clazz.getFields();
				if(ArrayUtils.isNotEmpty(fields)){
					for(Field f:fields){
						if(f.isAnnotationPresent(Inject.class)){
							Object fieldBean = beanMap.get(f.getType());
							f.setAccessible(true);
							try {
								f.set(obj, fieldBean);
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
					}
				}
			}
		}
	}
}
