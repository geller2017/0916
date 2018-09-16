package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodecUtil {
	public static String decodeUrl(String url){
		String target = "";
		try {
			target = URLDecoder.decode(url,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return target;
	}
	
	public static String EncodeUrl(String url){
		String target = "";
		try {
			target = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return target;
	}
}
