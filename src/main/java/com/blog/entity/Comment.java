package com.blog.entity;

import java.util.Date;
//评论类
public class Comment
{
	
  private Integer id;
  /**用户ip**/
  private String userIp;
  /**评论类别**/
  private String content;
  /**评论的博客--blogId**/
  private Blog blog;
  /**评论日期**/
  private Date commentDate;
  /**评论状态**/
  private Integer state;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getUserIp()
  {
    return this.userIp;
  }
  
  public void setUserIp(String userIp)
  {
    this.userIp = userIp;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public Blog getBlog()
  {
    return this.blog;
  }
  
  public void setBlog(Blog blog)
  {
    this.blog = blog;
  }
   
  public Date getCommentDate()
  {
    return this.commentDate;
  }
  
  public void setCommentDate(Date commentDate)
  {
    this.commentDate = commentDate;
  }
  
  public Integer getState()
  {
    return this.state;
  }
  
  public void setState(Integer state)
  {
    this.state = state;
  }
}





