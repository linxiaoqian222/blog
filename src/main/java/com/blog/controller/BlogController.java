package com.blog.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.Blog;
import com.blog.lucene.BlogIndex;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.StringUtil;

@Controller
@RequestMapping({"/blog"})
public class BlogController {

	@Resource
	private BlogService blogService;
	private BlogIndex blogIndex = new BlogIndex();
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping({"/articles/{id}"})
	public ModelAndView details(@PathVariable("id")Integer id,
					HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		//����id��ѯ������Ϣ
		Blog blog = blogService.findById(id);
		mav.addObject("blog",blog);
		
		//�Ķ�������1
		blog.setClickHit(blog.getClickHit()+1);
		blogService.update(blog);
		
		mav.addObject("mainPage","foreground/blog/view.jsp");
		mav.addObject("pageTitle",blog.getTitle()+"_���˲���ϵͳ");
		//��һҳ��һҳ
		mav.addObject("pageCode",genUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id), request.getServletContext().getContextPath()));
		
		//��ѯ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);
		commentService.list(map);
		mav.addObject("commentList",commentService.list(map));
		
		//����ؼ���
		String keyWord = blog.getKeyWord();
		if(StringUtil.isEmpty(keyWord)){
			mav.addObject("keyWords",null);
		}else{
			String[] arr = keyWord.split(" ");
			mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(keyWord)));
		}
		
		mav.setViewName("index");
		return mav;
	}
	
	
	
		//���չؼ��ֲ�ѯ
	  @RequestMapping({"/q"})
	  public ModelAndView search(@RequestParam(value="q", required=false) String q, @RequestParam(value="page", required=false) String page, HttpServletRequest request)
	    throws Exception
	  {
	    if (StringUtil.isEmpty(page)) {
	      page = "1";
	    }
	    ModelAndView mav = new ModelAndView();
	    //������ҳ��
	    mav.addObject("mainPage", "foreground/blog/result.jsp");
	   //��lucene�в�ѯ�ؼ���
	    List<Blog> blogList = this.blogIndex.searchBlog(q.trim());
	    //���ص�ǰ���
	    int toIndex = 0;
	    if(blogList.size() >= Integer.parseInt(page)*10){
	    	toIndex = Integer.parseInt(page)*10;
	    }else{
	    	toIndex = blogList.size();
	    }
	    mav.addObject("blogList",blogList.subList((Integer.parseInt(page) - 1) * 10, toIndex));
	    mav.addObject("pageCode", genUpAndDownPageCode(Integer.valueOf(Integer.parseInt(page)), Integer.valueOf(blogList.size()), q, Integer.valueOf(10), request.getServletContext().getContextPath()));
	    mav.addObject("q", q);
	    mav.addObject("resultTotal", blogList.size());
	    mav.addObject("pageTitle", "�����ؼ���'" + q + "'���ҳ��_Java��Դ����ϵͳ");
	    
	    mav.setViewName("index");
	    return mav;
	  }
	  
	  
	  /*
	   * ��һƪ/��һƪ
	   */
	  private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext)
	  {
	    StringBuffer pageCode = new StringBuffer();
	    if ((lastBlog == null) || (lastBlog.getId() == null)) {
	      pageCode.append("<p>��һƪ��û����</p>");
	    } else {
	      pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>" + lastBlog.getTitle() + "</a></p>");
	    }
	    if ((nextBlog == null) || (nextBlog.getId() == null)) {
	      pageCode.append("<p>��һƪ��û����</p>");
	    } else {
	      pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>" + nextBlog.getTitle() + "</a></p>");
	    }
	    return pageCode.toString();
	  }
	  
	  /*
	   * ��ѯ����ķ�ҳ
	   */
	  private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize, String projectContext)
	  {
		  //����һ���ж���ҳ
	    long totalPage = totalNum.intValue() % pageSize.intValue() == 0 ? totalNum.intValue() / pageSize.intValue() : totalNum.intValue() / pageSize.intValue() + 1;
	    StringBuffer pageCode = new StringBuffer();
	    if (totalPage == 0L) {
	      return "";
	    }
	    pageCode.append("<nav>");
	    pageCode.append("<ul class='pager' >");
	    
	    //�����ǰҳ����1
	    if (page.intValue() > 1) {
	      pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page.intValue() - 1) + "&q=" + q + "'>��һҳ</a></li>");
	    } else {
	      pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
	    }
	    if (page.intValue() < totalPage) { //�������һҳ
	      pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page.intValue() + 1) + "&q=" + q + "'>��һҳ</a></li>");
	    } else {
	      pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
	    }
	    pageCode.append("</ul>");
	    pageCode.append("</nav>");
	    
	    return pageCode.toString();
	  }
	  
	

}
