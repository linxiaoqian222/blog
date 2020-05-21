package com.blog.entity;

import java.io.Serializable;
/*
 * 博客类型，实现序列化，方便于传递对象
 */
public class BlogType
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer id;
  
  /**类型名称**/
  private String typeName;
  
  /**该类型下博客的数量**/
  private Integer blogCount;
  
  /**序号**/
  private Integer orderNo;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getTypeName()
  {
    return this.typeName;
  }
  
  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }
  
  public Integer getBlogCount()
  {
    return this.blogCount;
  }
  
  public void setBlogCount(Integer blogCount)
  {
    this.blogCount = blogCount;
  }
  
  public Integer getOrderNo()
  {
    return this.orderNo;
  }
  
  public void setOrderNo(Integer orderNo)
  {
    this.orderNo = orderNo;
  }
}





