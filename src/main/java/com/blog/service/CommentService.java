package com.blog.service;

import java.util.List;
import java.util.Map;

import com.blog.entity.Comment;

public interface CommentService {
	
	/*
	 * ���һ������
	 */
	public int add(Comment comment);
	
	/*
	 * ����һ������
	 */
	public int update(Comment comment);
	
	/*
	 * ��ѯһ�������µ���������
	 */
	public List<Comment> list(Map<String, Object> map);
	
	/*
	 * ��������
	 */
	public Long getTotal(Map<String, Object> map);
	
	/*
	 * ɾ��
	 */
	public Integer delete(Integer id);
}
