package com.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.dao.LinkDao;
import com.blog.entity.Link;
import com.blog.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	@Override
	public Link findById(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.findById(id);
	}

	@Override
	public List<Link> list(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return linkDao.list(paramMap);
	}

	@Override
	public Long getTotal(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return linkDao.getTotal(paramMap);
	}

	@Override
	public Integer add(Link link) {
		// TODO Auto-generated method stub
		return linkDao.add(link);
	}

	@Override
	public Integer update(Link link) {
		// TODO Auto-generated method stub
		return linkDao.update(link);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.delete(id);
	}

}
