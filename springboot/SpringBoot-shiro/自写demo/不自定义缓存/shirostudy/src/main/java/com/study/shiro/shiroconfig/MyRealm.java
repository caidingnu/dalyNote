package com.study.shiro.shiroconfig;

import com.study.shiro.dao.PermissionDao;
import com.study.shiro.dao.RoleDao;
import com.study.shiro.dao.UserDao;
import com.study.shiro.entity.Permission;
import com.study.shiro.entity.Role;
import com.study.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 每次权限验证的时候都去查询数据库
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;
    private Logger logger = LoggerFactory.getLogger(MyRealm.class);


    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("token.getPrincipal:" + token.getPrincipal());
        System.out.println("token.getCredentials:" + token.getCredentials());


        //清空权限缓存,避免重新设置权限不生效( 实际开发中在 在修改用户权限的逻辑执行完之后使用下面代码)
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());

        String userName = token.getPrincipal().toString();
        User user = userDao.getUserByUserName(userName);
        if (user != null) {
            //盐
            ByteSource creanSalt = ByteSource.Util.bytes(user.getUserName());
             /**
			 *SecurityUtils.getSubject().getPrincipal()
			 *   主要看登陆的时候存到 new SimpleAuthenticationInfo(user, user.getPassword(), creanSalt, getName());
			 *  第一个参数是对象，取出来就是对象，存的是userName，取出来就是String
			 */
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), creanSalt, getName());
            return authcInfo;
        }

        return null;

    }


    /**
     * 授权
     * 用户登录成功之后，完成shiro的doGetAuthenticationInfo认证，但是登录认证之后shiro并不会马上执行授权doGetAuthorizationInfo，
     * 而是待用户访问的目标资源或者方法需要权限的时候才会调用doGetAuthorizationInfo进行授权。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("##################执行Shiro权限认证##################");
		 /**
         *SecurityUtils.getSubject().getPrincipal()
         *   主要看登陆的时候存到 new SimpleAuthenticationInfo(user, user.getPassword(), creanSalt, getName());
         *  第一个参数是对象，取出来就是对象，存的是userName，取出来就是String
         */
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        List<Role> rolesByUserName = roleDao.getRolesByUserName(userName);
        for (Role role : rolesByUserName) {
            roles.add(role.getRoleName());
        }
        List<Permission> permissionsByUserName = permissionDao.getPermissionsByUserName(userName);
        for (Permission permission : permissionsByUserName) {
            info.addStringPermission(permission.getPermissionName());
        }
        info.setRoles(roles);
        return info;
    }
 /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
