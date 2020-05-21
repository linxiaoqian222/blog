package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.ResponseUtil;
/*
 * ǰ���û��ύ����
 */
@Controller
@RequestMapping({"/comment"})
public class CommentController {

	@Resource
	private CommentService commentService;
	
	@Resource
	private BlogService blogService;
	/*
	 * �ύ����
	 */
	@RequestMapping({"/save"})
	public String save(Comment comment,
			@RequestParam("imageCode")String imageCode,
			HttpServletRequest request ,HttpServletResponse response,
			HttpSession session) throws Exception{
		int resultTotal = 0;
		//��ȡ��֤��
		String sRand = (String)session.getAttribute("sRand");
		JSONObject result = new JSONObject();
		if(!imageCode.equals(sRand)){
			//��֤�����
			result.put("success",Boolean.TRUE);
			result.put("erroInfo", "��֤����д����");
		}else{
			String userIp = request.getRemoteAddr();
			comment.setUserIp(userIp);
			if(comment.getId()==null){
				resultTotal = commentService.add(comment);
				
				//����Ӧ�Ĳ�����������1
				Blog blog = blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
			}
		}
		if(resultTotal >0){
			result.put("success",Boolean.TRUE);
		}else{
			result.put("success",Boolean.FALSE);
		}
		
		ResponseUtil.write(response, result);
		return null;
	}
}
