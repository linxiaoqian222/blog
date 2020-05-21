package com.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.dao.BloggerTypeDao;
import com.blog.entity.BlogType;
import com.blog.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BloggerTypeDao bloggerTypeDao;
	
	@Override
	public List<BlogType> countList() {
		// TODO Auto-generated method stub
		return bloggerTypeDao.countList();
	}

	@Override
	public BlogType findById(Integer id) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.findById(id);
	}

	@Override
	public List<BlogType> list(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.list(paramMap);
	}

	@Override
	public Long getTotal(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.getTotal(paramMap);
	}

	@Override
	public Integer add(BlogType blogType) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.add(blogType);
	}

	@Override
	public Integer update(BlogType blogType) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.update(blogType);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return bloggerTypeDao.delete(id);
	}

}
