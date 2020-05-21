package com.blog.dao;

import java.util.List;

import java.util.Map;

import com.blog.entity.Link;

public interface LinkDao {
	

	/**����id��ѯһ����������**/
	public Link findById(Integer id);
	
	/**���ݲ��̶�������ѯ���������б�**/
	public List<Link> list(Map<String,Object> paramMap);
	
	/**���ݲ��̶�������ѯ����������**/
	public Long getTotal(Map<String,Object> paramMap);

	/**���һ����������**/
	public Integer add(Link link);
	
	/**�޸�һ����������**/
	public Integer update(Link link);
	
	/**ɾ��һ����������**/
	public Integer delete(Integer id);
}
