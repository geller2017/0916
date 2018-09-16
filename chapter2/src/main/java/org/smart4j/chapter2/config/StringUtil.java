package org.smart4j.chapter2.config;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	//主要判断字符串是不是空的
	public static boolean isEmpty(String str){
		if(str!=null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		if(str!=null){
			str = str.trim();
		}
		return StringUtils.isNotEmpty(str);
	}
}
