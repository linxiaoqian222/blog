package com.blog.service;

import com.blog.entity.Blogger;

public interface BloggerService {
	
	public Blogger getByUserName(String paramString);
	
	/**���²�������**/
	public Integer update(Blogger blogger);
	
	/**��ѯ����**/
	public Blogger find();
}
