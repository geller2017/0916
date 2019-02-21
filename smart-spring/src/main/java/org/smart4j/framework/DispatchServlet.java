package org.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.smart4j.framework.entity.Data;
import org.smart4j.framework.entity.Handler;
import org.smart4j.framework.entity.Param;
import org.smart4j.framework.entity.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.HelperLoader;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectUtil;
import org.smart4j.framework.util.StreamUtil;
import org.smart4j.framework.util.StringUtil;
@WebServlet(urlPatterns="/*",loadOnStartup=0)
public class DispatchServlet extends HttpServlet{
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//初始化工具类
		HelperLoader.init();
		//获取servletContext，注册servlet
		ServletContext servletContext = servletConfig.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		//注册默认静态资源servlet
		ServletContext defaultContext = servletConfig.getServletContext();
		ServletRegistration defaultServlet = defaultContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestMethod = request.getMethod();
		String requestPath = request.getPathInfo();
		//获取action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler!=null){
			//获取处理器对应的实例
			Class<?> controllerClass = handler.getClz();
			Object obj = BeanHelper.getBean(controllerClass);
			Enumeration<String> paramNameEnum = request.getParameterNames();
			//存放参数的容器
			Map<String,Object> paramMap = new HashedMap<>();
			while(paramNameEnum.hasMoreElements()){
				String paramName = paramNameEnum.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			//对url中的参数解析
			String body = CodecUtil.decodeUrl(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String[] paramEntityArray = body.split("&");
				if(ArrayUtils.isNotEmpty(paramEntityArray)){
					for(String paramEntity:paramEntityArray){
						String[] paramArray = paramEntity.split("=");
						if(ArrayUtils.isNotEmpty(paramArray)&&paramArray.length==2){
							String key = paramArray[0];
							String value = paramArray[1];
							paramMap.put(key, value);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			Method method = handler.getMethod();
			Object result = ReflectUtil.invokeMethod(obj, method, param);
			//解析返回结果
			if(result instanceof View){
				//返回的是jsp页面
				View view = (View) result;
				String path = view.getPath();
				
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){
						response.sendRedirect(request.getContextPath()+path);
					}else{
						Map<String,Object> model = view.getModel();
						for(Map.Entry<String, Object> entity : model.entrySet()){
							request.setAttribute(entity.getKey(), entity.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
					}
				}
			}else if(result instanceof Data){
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				String json = JsonUtil.toJson(result);
				writer.println(json);
				writer.flush();
				writer.close();
			}
			
			
		}
		
	}
}
