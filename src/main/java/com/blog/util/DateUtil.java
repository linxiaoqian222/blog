package com.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * ���ڹ�����
 */
public class DateUtil {

	/*
	 * �õ���ǰ�����ʱ���ַ���
	 */
	public static String getCurrentDateStr(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
		
	}
	
	public static String formatDate(Date date,String format){
		String result =" ";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date != null){
			result+=sdf.format(date);
		}
		return result;
		
	}
}
