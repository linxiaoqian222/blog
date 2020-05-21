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

//����Ŀ������ʱ���ȥ���ݿ��в�ѯ����������
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

	//��Ŀ������ʱ��ִ�� 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		//�������
		BlogTypeService blogTypeService = (BlogTypeService)applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypes = blogTypeService.countList();
		application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypes);
		
		//������Ϣ
		BloggerService bloggerService = (BloggerService)applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.find();
		blogger.setPassword(null);
		application.setAttribute(Const.BLOGGER, blogger);
	
		//�����·���Ĳ�������
		BlogService blogService = (BlogService)applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList();
		application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);
		
		
		//��������
		LinkService linkService = (LinkService)applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null);
		application.setAttribute(Const.LINK_LIST, linkList);
	}

}
