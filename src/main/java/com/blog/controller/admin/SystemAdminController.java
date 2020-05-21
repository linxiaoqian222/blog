package com.blog.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.blog.entity.Blog;
import com.blog.entity.BlogType;
import com.blog.entity.Blogger;
import com.blog.entity.Link;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.service.BloggerService;
import com.blog.service.LinkService;
import com.blog.util.Const;
import com.blog.util.ResponseUtil;

//刷新系统缓存
@Controller
@RequestMapping({"/admin/system"})
public class SystemAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BloggerService bloggerService;
	@Resource
	private BlogService blogService;
	@Resource
	private LinkService linkService;
	
	/*
	 * 刷新系统缓存，当执行InitComponent时，spring不一定加载成功
	 */
	@RequestMapping({"/refreshSystem"})
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();
		//博客类别
		List<BlogType> blogTypes = blogTypeService.countList();
		application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypes);
		
		//博主信息
		Blogger blogger = bloggerService.find();
		blogger.setPassword(null);
		application.setAttribute(Const.BLOGGER, blogger);
	
		//按年月分类的博客数量
		List<Blog> blogCountList = blogService.countList();
		application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);
		
		
		//友情链接
		List<Link> linkList = linkService.list(null);
		application.setAttribute(Const.LINK_LIST, linkList);
		
		JSONObject result = new JSONObject();
		result.put("success",Boolean.TRUE);
		ResponseUtil.write(response, result);
		return null;
	}
}
