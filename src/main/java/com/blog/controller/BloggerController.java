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
 * ������¼���
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
		
		//���������ת��
		String pw = CryptographyUtil.md5(password,"java1234");
		
		/*
		 * Subject
		 */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,pw);
		System.out.println(pw+"--------------------------------------------------");
		try {
			//����token��shrio��realm
			subject.login(token);
			return "redirect:/admin/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			//���󽫴���ǰ��ҳ��
			request.setAttribute("blogger", blogger);
			request.setAttribute("erroInfor","�û������������");
		}
		return "login";
	}
	
	/*
	 * ���ڲ���
	 */
	@RequestMapping({"/aboutMe"})
	public ModelAndView aboutMe(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage","foreground/blogger/info.jsp");
		mav.addObject("pageTitle","���ڲ����������˲���ϵͳ");
		mav.setViewName("index");
		
		return mav;
	}
}
