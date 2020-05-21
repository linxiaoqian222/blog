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
	 * ��ȡȨ����Ϣ(non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		return null;
	}

	/*
	 * ��¼��֤(non-Javadoc)
	 * token�����ƣ������û��������������
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1. ��������ȡ���û���
		String username = (String)token.getPrincipal();
		
		//2. shrio��֤�û�������
		Blogger blogger = bloggerService.getByUserName(username);
		System.out.println(blogger.getPassword()+"---"+blogger.getUserName()+"---------------------");
		if(blogger != null){
			//��������Ϣ����session�з������лỰ���ܷ���
			SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
			AuthenticationInfo authenInfo =new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "xx");
			return authenInfo;
		}
		System.out.println("Ϊ��-----------------------------------");
		return null;
	}

}
