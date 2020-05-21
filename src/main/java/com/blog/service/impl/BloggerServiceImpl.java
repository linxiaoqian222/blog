package com.blog.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.blog.dao.BloggerDao;
import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.Const;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;
	
	@Override
	public Blogger getByUserName(String paramString) {
		Blogger blogger = bloggerDao.getByUserName(paramString);
		System.out.println(blogger.getPassword()+"---"+blogger.getUserName()+"---------------------");
		return blogger;
	}

	@Override
	public Integer update(Blogger blogger) {
		SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
		return bloggerDao.update(blogger);
	}

	@Override
	public Blogger find() {
		// TODO Auto-generated method stub
		return bloggerDao.find();
	}

}
