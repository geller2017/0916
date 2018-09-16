package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.entity.Handler;
import org.smart4j.framework.entity.Request;

/***
 * 控制器存储类，将所有的controller解析，其中标注@action的方法解析，放入容器
 * @author acer
 *
 */

public class ControllerHelper {
	private static Map<Request, Handler> ACTION_MAP = new HashMap<>();
	static{
		Set<Class<?>> controllerSet = ClassHelper.getControllerClassSet();
		if(CollectionUtils.isNotEmpty(controllerSet)){
			for(Class<?> controller:controllerSet){
				Method[] methods = controller.getDeclaredMethods();//取得controller勒种所有的方法
				if(ArrayUtils.isNotEmpty(methods)){
					for(Method method : methods){
						if(method.isAnnotationPresent(Action.class)){
							Action action = method.getAnnotation(Action.class);
							String actionValue = action.value();
							//requestMapping(value="post:/user")
							if(actionValue.matches("\\w+:/\\w*")){
								String[] array = actionValue.split(":");
								if(ArrayUtils.isNotEmpty(array)&&array.length==2){
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controller, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	//提供一个公共方法：根据客户端请求path和method来找到对应的handler方法
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
	
}
