package com.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Link;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.service.LinkService;
import com.blog.util.ResponseUtil;

/*
 * �������ӹ���
 */
@Controller
/**�����Ŀ¼**/
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;
	
	
	/**��ѯҳ�棬���������б�
	 * @throws Exception **/
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		//��ѯ���������б�
		List<Link> linkList = linkService.list(map);
		
		//��ѯ�ܹ��ж���������
		Long total = linkService.getTotal(map);
		
		//������д��response
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(linkList);
		result.put("rows",jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * ���沩�������Ϣ
	 * @throws Exception 
	 */
	@RequestMapping({"/save"})
	public String save(Link link ,HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(link.getId() == null){
			//���
			resultTotal = linkService.add(link).intValue();
		}else{
			//����
			resultTotal = linkService.update(link).intValue();
		}
		
		JSONObject result = new JSONObject();
		if(resultTotal>0){
			result.put("success",Boolean.valueOf(true));
		}else{
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.write(response,result);
		return null;
	} 
	
	/**
	 * ɾ����������
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("ids")String ids,HttpServletResponse response) throws Exception{
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			//��ѯ��ɾ�������������Ƿ��Ѿ�������
			linkService.delete(Integer.valueOf(idsStr[i]));
		}
		result.put("success", Boolean.valueOf(true));
		ResponseUtil.write(response, result);
		return null;
	}
}
