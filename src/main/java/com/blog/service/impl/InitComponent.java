package com.blog.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.blog.entity.Blog;
import com.blog.entity.BlogType;
import com.blog.entity.Blogger;
import com.blog.entity.Link;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.service.BloggerService;
import com.blog.service.LinkService;
import com.blog.util.Const;

//当项目启动的时候就去数据库中查询到博客类型
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	//项目启动的时候执行 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		//博客类别
		BlogTypeService blogTypeService = (BlogTypeService)applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypes = blogTypeService.countList();
		application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypes);
		
		//博主信息
		BloggerService bloggerService = (BloggerService)applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.find();
		blogger.setPassword(null);
		application.setAttribute(Const.BLOGGER, blogger);
	
		//按年月分类的博客数量
		BlogService blogService = (BlogService)applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList();
		application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);
		
		
		//友情链接
		LinkService linkService = (LinkService)applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null);
		application.setAttribute(Const.LINK_LIST, linkList);
	}

}
