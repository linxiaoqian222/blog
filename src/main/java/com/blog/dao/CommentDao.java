package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.entity.Comment;

/*
 * 评论
 */
public interface CommentDao {
	
	/*
	 * 添加一条评论
	 */
	public int add(Comment comment);
	
	/*
	 * 更新一条评论
	 */
	public int update(Comment comment);
	
	/*
	 * 查询一个博客下的所有评论
	 */
	public List<Comment> list(Map<String, Object> map);
	
	/*
	 * 评论数量
	 */
	public Long getTotal(Map<String, Object> map);
	
	/*
	 * 删除
	 */
	public Integer delete(Integer id);
	
	/*
	 * 根据博客id删除评论
	 */
	public Integer deleteByBlogId(Integer blogId);
}

