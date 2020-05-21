package com.blog.service;

import com.blog.entity.Blogger;

public interface BloggerService {
	
	public Blogger getByUserName(String paramString);
	
	/**更新博主对象**/
	public Integer update(Blogger blogger);
	
	/**查询博主**/
	public Blogger find();
}
