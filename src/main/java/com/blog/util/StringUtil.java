package com.blog.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	/*
	 * 在字符串前后加%，进行模糊查询
	 */
	public static String formatLike(String str){
		if(isNotEmpty(str)){
			return "%"+str+"%";
		}
		return null;
	}
	
	/*
	 * 判断字符串是否不为空
	 */
	public static boolean isNotEmpty(String str){
		if(str != null && "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/*
	 * 判断是否为空
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/*
	 * 处理字符中多空格的问题
	 */
	public static List<String> filterWhite(List<String> list){
		List<String> resultList = new ArrayList<>();
		for(String l:list){
			if(isNotEmpty(l)){
				resultList.add(l);
			}
		}
		return resultList;
	}
}
