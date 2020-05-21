package com.blog.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.Const;

public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	
	/*
	 * 获取权限信息(non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		return null;
	}

	/*
	 * 登录验证(non-Javadoc)
	 * token：令牌，基于用户名和密码的令牌
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1. 从令牌中取出用户名
		String username = (String)token.getPrincipal();
		
		//2. shrio验证用户名密码
		Blogger blogger = bloggerService.getByUserName(username);
		System.out.println(blogger.getPassword()+"---"+blogger.getUserName()+"---------------------");
		if(blogger != null){
			//将博主信息放在session中方便所有会话都能访问
			SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
			AuthenticationInfo authenInfo =new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "xx");
			return authenInfo;
		}
		System.out.println("为空-----------------------------------");
		return null;
	}

}
