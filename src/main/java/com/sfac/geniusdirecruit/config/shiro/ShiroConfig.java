package com.sfac.geniusdirecruit.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: Shiro Config
 * @author: HymanHu
 * @date: 2020年11月29日
 */
@Configuration
public class ShiroConfig {
	/**
	 * 配置密码匹配器
	 * @return
	 */
	@Autowired
	private MyRealm myRealm;

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		//配置登录页面
		bean.setLoginUrl("/user/login");
		//配置权限页面
		bean.setUnauthorizedUrl("/user/login");
		//配置权限的map
		HashMap<String, String> map = new HashMap<String, String>();

		//必须登录才能访问
		map.put("/common/dashboard", "authc");
		//设置访问用户管理业页面需要userInforList权限
		map.put("/api/user", "perms[user]");
		//设置访问用户管理新增页面需要userInforAdd权限
		map.put("/api/role", "perms[role]");
		////设置访问用户管理修改页面需要admin角色
		map.put("/api/news", "roles[admin]");
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}

	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}

	/**
	 * --自动代理类，支持Shiro的注解
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * --开启Shiro的注解
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * --记住我之rememberMeCookie
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，
		//使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
		simpleCookie.setHttpOnly(true);
		//记住我cookie生效时间,单位是秒
		simpleCookie.setMaxAge(1 * 24 * 60 * 60);
		return simpleCookie;
	}

	/**
	 * --记住我之cookie管理器
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
		cookieRememberMeManager.setCipherService(new AesCipherService());
		cookieRememberMeManager.setCipherKey(cipherKey);
		return cookieRememberMeManager;
	}
}
