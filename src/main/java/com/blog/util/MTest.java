package com.blog.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MTest {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		String[] str = ac.getBeanDefinitionNames();
		for(String s:str){
			System.out.println(s);
		}
	}
	
}	
