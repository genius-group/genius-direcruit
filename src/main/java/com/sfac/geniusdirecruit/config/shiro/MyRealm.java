package com.sfac.geniusdirecruit.config.shiro;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Role;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.UserRole;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.RoleService;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		User user = (User) principals.getPrimaryPrincipal();
		if (user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}
		
		List<Role> roles = new ArrayList<Role>();
		if (user.getUserName().equals("admin")) {
			roles = roleService.selectAllRoles();
			if (roles.isEmpty()) {
				simpleAuthorizationInfo.addRole("admin");
			}
		} else {
			//roles = roleService.getRolesByUserId(user.getUserId());
		}
		for (Role role :roles) {
			simpleAuthorizationInfo.addRole(role.getRoleName());
			/*List<UserRole> resources = resourceService.getResourcesByRoleId(role.getRoleId());
			for (UserRole resource : resources) {
				simpleAuthorizationInfo.addStringPermission(resource.getPermission());
			}*/
		}
		
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = userService.selectUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}
		
		// 身份验证器，包装用户名和密码
		return new SimpleAuthenticationInfo(user, user.getUserPwd(), getName());
	}

}
