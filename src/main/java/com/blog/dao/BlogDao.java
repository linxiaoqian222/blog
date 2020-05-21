package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.blog.entity.Blog;
import com.blog.entity.Blogger;

/*
 * ����
 */
public interface BlogDao {
	
	/**�޲�����ѯ�����б�**/
	public List<Blog> countList();
	
	/**��������ѯ�����б�**/
	public List<Blog> list(Map<String,Object> map);
	
	/**��������ѯ��������**/
	public Long getTotal(Map<String,Object> map);
	
	/**����������ѯһ��������Ϣ*/
	public Blog findById(Integer id);
	
	/**���һ������*/
	public Integer add(Blog blog);
	
	/**����һ������*/
	public Integer update(Blog blog);
	
	/**ɾ��һ������*/
	public Integer delete(Integer id);
	
	/**�������Ͳ�ѯ��������**/
	public Integer getBlogByTypeId(Integer typeId);

	/**��һƪ**/
	public Blog getLastBlog(Integer id);
	
	/**��һƪ**/
	public Blog getNextBlog(Integer id);
}
