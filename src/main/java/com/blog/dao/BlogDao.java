package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.blog.entity.Blog;
import com.blog.entity.Blogger;

/*
 * 博客
 */
public interface BlogDao {
	
	/**无参数查询博客列表**/
	public List<Blog> countList();
	
	/**带参数查询博客列表**/
	public List<Blog> list(Map<String,Object> map);
	
	/**带参数查询博客数量**/
	public Long getTotal(Map<String,Object> map);
	
	/**根据主键查询一条博客信息*/
	public Blog findById(Integer id);
	
	/**添加一条博客*/
	public Integer add(Blog blog);
	
	/**跟新一条博客*/
	public Integer update(Blog blog);
	
	/**删除一条博客*/
	public Integer delete(Integer id);
	
	/**根据类型查询博客数量**/
	public Integer getBlogByTypeId(Integer typeId);

	/**上一篇**/
	public Blog getLastBlog(Integer id);
	
	/**上一篇**/
	public Blog getNextBlog(Integer id);
}
