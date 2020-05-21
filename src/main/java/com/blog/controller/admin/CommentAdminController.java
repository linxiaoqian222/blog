package com.blog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Comment;
import com.blog.entity.PageBean;
import com.blog.service.CommentService;
import com.blog.util.DateJsonValueProcess;
import com.blog.util.ResponseUtil;

/*
 * 评论管理
 */
@Controller
@RequestMapping({"/admin/comment"})
public class CommentAdminController {
	
	@Resource
	private CommentService commentService;
	
	/*
	 * 查询评论列表
	 */
	@RequestMapping({"/list"})
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="state",required=false)String state,
			HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		//加载翻页
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		map.put("state", state);
		
		//查询评论列表
		List<Comment> commentList = commentService.list(map);
		
		//查询评论总数
		Long total = commentService.getTotal(map);
		
		//放入json
		JSONObject result = new JSONObject();
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcess("yyy-mm-dd"));
		JSONArray jsonArray = JSONArray.fromObject(commentList,config);
		
		result.put("rows", jsonArray);
		result.put("total",total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/*
	 * 删除评论
	 */
	@RequestMapping({"/delete"})
	public String delete(@RequestParam(value="ids")String ids,
			          HttpServletResponse response) throws Exception{
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			commentService.delete(Integer.parseInt(idsStr[i]));
		}
		//放入json
		JSONObject result = new JSONObject();
		result.put("success",Boolean.TRUE);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/*
	 * 审核评论
	 */
	@RequestMapping({"/review"})
	public String review(@RequestParam(value="ids")String ids,
			   @RequestParam(value="state")String state,
			   HttpServletResponse response) throws Exception{
		
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			//可以使用设计模式，避免多个评论就要生产多个对象
			Comment comment = new Comment();
			comment.setId(Integer.parseInt(idsStr[i]));
			comment.setState(Integer.parseInt(state));
			commentService.update(comment);
		}
		//放入json
		JSONObject result = new JSONObject();
		result.put("success",Boolean.TRUE);
		ResponseUtil.write(response, result);
		return null;
	}
}
