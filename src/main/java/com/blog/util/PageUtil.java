package com.blog.util;

//��ҳ������--ʵ�ַ�ҳ����
public class PageUtil {
	
	/*
	 *  <li><a href="/index.html?page=1&">��ҳ</a></li>
	    <li class="disabled"><a href="#">��һҳ</a></li>
	    <li class="active"><a href="/index.html?page=1&">1</a></li>
	    <li class="disabled"><a href="#">��һҳ</a></li>
	    <li><a href="/index.html?page=1&">βҳ</a></li>
	 */
	
	/*
	 * ��ҳ����
	 */
	public static String genPagination(String targetUrl,long totalNum,int currentPage,int pageSize,String param){
		if(totalNum == 0){
			return "δ��ѯ������";
		}
		long totalPage = 1;
		   //�ܹ�ҳ��
		 if (totalNum%pageSize == 0) {
		       totalPage = totalNum/pageSize;
		   }else{
			   totalPage = totalNum/pageSize+1;
		   }
		     StringBuffer pageCode = new StringBuffer();
		     pageCode.append("<li><a href='" + targetUrl + "?page=1&" + param + "'>��ҳ</a></li>");
		     //��һҳ
		     if (currentPage > 1) {
		    	 //��ǰҳ���ǵ�һҳ����ʾ��һҳ�����ܵ��
		       pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "&" + param + "'>��һҳ</a></li>");
		     } else {
		    	 //��ǰҳ�ǵ�һҳ����ʾ��һҳ�����ܵ��
		       pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		     }
		     
		     //��ʾҳ��
	       for(int i=1;i<=totalPage;i++){
		       if (i == currentPage) {
			         pageCode.append("<li class='active'><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>");
		         } else { 
		        	 pageCode.append("<li><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>");
		         }
		     }
		     
		     //��һҳ
		     if (currentPage < totalPage) {
		       pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "&" + param + "'>��һҳ</a></li>");
		     } else {
		       pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		     }
		     //βҳ
		     pageCode.append("<li><a href='" + targetUrl + "?page=" + totalPage + "&" + param + "'>βҳ</a></li>");
		   
		     return pageCode.toString();
		   }

}
