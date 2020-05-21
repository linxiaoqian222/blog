package com.blog.service;

import java.util.List;
import java.util.Map;

import com.blog.entity.BlogType;

public interface BlogTypeService {
	
	/**�޲�����ѯ���в��������б�**/
	public List<BlogType> countList();

	/**����id��ѯһ����������**/
	public BlogType findById(Integer id);
	
	/**���ݲ��̶�������ѯ���������б�**/
	public List<BlogType> list(Map<String,Object> paramMap);
	
	/**���ݲ��̶�������ѯ����������**/
	public Long getTotal(Map<String,Object> paramMap);

	/**���һ����������**/
	public Integer add(BlogType blogType);
	
	/**�޸�һ����������**/
	public Integer update(BlogType blogType);
	
	/**ɾ��һ����������**/
	public Integer delete(Integer id);
}
