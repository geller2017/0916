package org.smart4j.framework.util;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;

/***
 * 用于统一加载各个helper
 * @author acer
 *
 */
public class HelperLoader {
	public static void init(){
		Class<?>[] helperList = {
				ClassHelper.class,
				BeanHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for(Class<?> clz:helperList){
			ClassUtil.loadClass(clz.getName(), true);
		}
	}
}
