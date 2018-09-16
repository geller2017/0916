package org.smart4j.chapter2.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 属性文件工具类
 * @author Administrator
 *
 */
public final class PropsUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);
	
	//根据文件名称，加载属性文件
	public static Properties loadProps(String fileName){
		Properties props = null;
		InputStream is = null;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is==null){
				logger.error("文件不存在");
			}
			props = new Properties();
			props.load(is);
		}catch(Exception e){
			logger.error("加载属性文件失败");
		}finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭输入流失败");
				}
			}
		}
		return props;
	}
	//根据属性key，获取属性值，字符型
	public static String getString(Properties props,String key){
		return getString(props,key,"");
	}
	//根据属性key，获取属性值，数字型
	public static int getInt(Properties props,String key){
		return getInt(props,key,0);
	}
	
	//根据属性key，获取属性值，bool型
	public static boolean getBoolean(Properties props,String key){
		return getBoolean(props,key,false);
	}
	
	
	private static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castBoolean(props.get(key)); //props.getProperty(key);
		}
		return value;
	}
	private static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castInt(props.get(key)); //props.getProperty(key);
		}
		return value;
	}
	private static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if(props.containsKey(key)){
			value = props.getProperty(key);
		}
		return value;
	}
	
}
