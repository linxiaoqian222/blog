package com.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//博客
public class Blog
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  /**主键**/
  private Integer id;
  /**标题**/
  private String title;
  /**摘要**/
  private String summary;
  /**发表时间**/
  private Date releaseDate;
  /**点击量**/
  private Integer clickHit;
  /**评论数**/
  private Integer replyHit;
  /**内容**/
  private String content;
  /**纯文本格式的内容**/
  private String contentNoTag;
  /**所属博客类型**/
  private BlogType blogType;
  /**博客数量*/
  private Integer blogCount;
  /**发表时间**/
  private String releaseDateStr;
  /**关键字**/
  private String keyWord;
  private List<String> imagesList = new LinkedList();
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getSummary()
  {
    return this.summary;
  }
  
  public void setSummary(String summary)
  {
    this.summary = summary;
  }
  
  public Date getReleaseDate()
  {
    return this.releaseDate;
  }
  
  public void setReleaseDate(Date releaseDate)
  {
    this.releaseDate = releaseDate;
  }
  
  public Integer getClickHit()
  {
    return this.clickHit;
  }
  
  public void setClickHit(Integer clickHit)
  {
    this.clickHit = clickHit;
  }
  
  public Integer getReplyHit()
  {
    return this.replyHit;
  }
  
  public void setReplyHit(Integer replyHit)
  {
    this.replyHit = replyHit;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getContentNoTag()
  {
    return this.contentNoTag;
  }
  
  public void setContentNoTag(String contentNoTag)
  {
    this.contentNoTag = contentNoTag;
  }
  
  public BlogType getBlogType()
  {
    return this.blogType;
  }
  
  public void setBlogType(BlogType blogType)
  {
    this.blogType = blogType;
  }
  
  public Integer getBlogCount()
  {
    return this.blogCount;
  }
  
  public void setBlogCount(Integer blogCount)
  {
    this.blogCount = blogCount;
  }
  
  public String getReleaseDateStr()
  {
    return this.releaseDateStr;
  }
  
  public void setReleaseDateStr(String releaseDateStr)
  {
    this.releaseDateStr = releaseDateStr;
  }
  
  public String getKeyWord()
  {
    return this.keyWord;
  }
  
  public void setKeyWord(String keyWord)
  {
    this.keyWord = keyWord;
  }
  
  public List<String> getImagesList()
  {
    return this.imagesList;
  }
  
  public void setImagesList(List<String> imagesList)
  {
    this.imagesList = imagesList;
  }
}





