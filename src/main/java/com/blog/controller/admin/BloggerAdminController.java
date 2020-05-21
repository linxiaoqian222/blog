package com.blog.controller.admin;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.Const;
import com.blog.util.CryptographyUtil;
import com.blog.util.DateUtil;
import com.blog.util.ResponseUtil;
import com.sun.xml.internal.rngom.binary.DataExceptPattern;

@Controller
@RequestMapping({"/admin/blogger"})
public class BloggerAdminController {
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping({"/save"})
	public String save(@RequestParam("imageFile")MultipartFile imageFile,
						Blogger blogger,HttpServletRequest request,
						HttpServletResponse response ) throws Exception{
		if(!imageFile.isEmpty()){
			String filePath = request.getServletContext().getRealPath("/");
			String imageName = DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		
		int resultTotal = bloggerService.update(blogger);
		
		
		StringBuffer result = new StringBuffer();
		
		if(resultTotal >0){
			//��ǰ�˷��ͳɹ���Ϣ
			result.append("<script lauguage='javascript'>alert('�޸ĳɹ�')</script>");
		}else{
			//��ǰ�˷���ʧ����Ϣ
			result.append("<script lauguage='javascript'>alert('�޸�ʧ��')</script>");
		}
		
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/*
	 * ��ȡ������json��ʽ
	 */
	@RequestMapping({"/find"})
	public String find(HttpServletResponse response) throws Exception{
		Blogger blogger = (Blogger)SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER);
		
		JSONObject jsonObject = JSONObject.fromObject(blogger);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	/*
	 * �޸�����
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(@RequestParam("id")String id,@RequestParam("newPassword")String newPassword,HttpServletResponse response) throws Exception{
		Blogger blogger = new Blogger();
		blogger.setId(Integer.parseInt(id));
		blogger.setPassword(CryptographyUtil.md5(newPassword, "java1234"));
		int resultTotal = bloggerService.update(blogger);
		JSONObject result = new JSONObject();
		if(resultTotal >0){
			result.put("success", Boolean.TRUE);
		}else {
			result.put("success", Boolean.FALSE);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	//��ȫ�˳�--ʹ��shiro�����ķ���
	@RequestMapping({"/logout"})
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
