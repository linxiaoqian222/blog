package com.blog.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcess implements JsonValueProcessor{

	private String format;
	
	public DateJsonValueProcess(String format){
		this.format = format;
	}
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonconfig) {
		if(value == null){
			return "";
		}
		if(value instanceof Timestamp){
			String str = new SimpleDateFormat(this.format).format((Timestamp)value);
			return str;
		}
		if(value instanceof Date){
			String str = new SimpleDateFormat(this.format).format((Date)value);
			return str;
		}
		
		return value.toString();
	}

}
