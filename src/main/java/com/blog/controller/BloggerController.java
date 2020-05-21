package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.CryptographyUtil;

/*
 * 博主登录相关
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("login")
	public String login(Blogger blogger,HttpServletRequest request){
		String username = blogger.getUserName();
		String password = blogger.getPassword();
		
		//对密码进行转化
		String pw = CryptographyUtil.md5(password,"java1234");
		
		/*
		 * Subject
		 */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,pw);
		System.out.println(pw+"--------------------------------------------------");
		try {
			//传递token给shrio的realm
			subject.login(token);
			return "redirect:/admin/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			//错误将传回前端页面
			request.setAttribute("blogger", blogger);
			request.setAttribute("erroInfor","用户名或密码错误");
		}
		return "login";
	}
	
	/*
	 * 关于博主
	 */
	@RequestMapping({"/aboutMe"})
	public ModelAndView aboutMe(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage","foreground/blogger/info.jsp");
		mav.addObject("pageTitle","关于博主——个人博客系统");
		mav.setViewName("index");
		
		return mav;
	}
}
