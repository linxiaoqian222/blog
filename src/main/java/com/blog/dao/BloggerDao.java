package com.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.blog.entity.Blogger;

/*
 * ����
 */
public interface BloggerDao {
	
	/**���ݵ�¼����ѯ��������**/
	public Blogger getByUserName(@Param("userName")String userName);
	
	/**���²�������**/
	public Integer update(Blogger blogger);
	
	/**��ѯ����**/
	public Blogger find();
}
