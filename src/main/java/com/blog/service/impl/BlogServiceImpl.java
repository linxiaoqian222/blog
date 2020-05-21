package com.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.entity.Blog;
import com.blog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Resource
	private BlogDao blogDao;
	@Resource
	private CommentDao commentDao;
	
	@Override
	public List<Blog> countList() {
		// TODO Auto-generated method stub
		return blogDao.countList();
	}

	@Override
	public List<Blog> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogDao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogDao.getTotal(map);
	}

	@Override
	public Blog findById(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.findById(id);
	}

	@Override
	public Integer add(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.add(blog);
	}

	@Override
	public Integer update(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.update(blog);
	}

	//在删除博客的同时也要删除博客对应的评论
	@Override
	public Integer delete(Integer id) {
		commentDao.deleteByBlogId(id);
		return blogDao.delete(id);
	}

	@Override
	public Integer getBlogByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return blogDao.getBlogByTypeId(typeId);
	}

	@Override
	public Blog getLastBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getLastBlog(id);
	}

	@Override
	public Blog getNextBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getNextBlog(id);
	}

}
