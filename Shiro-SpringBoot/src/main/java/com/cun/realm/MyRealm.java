package com.cun.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cun.dao.PermissionDao;
import com.cun.dao.RoleDao;
import com.cun.dao.UserDao;
import com.cun.entity.Permission;
import com.cun.entity.Role;
import com.cun.entity.User;


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
            // Object principal, Object credentials, String realmName
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            return authcInfo;
        } else {
            return null;
        }
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        logger.info("##################执行Shiro权限认证##################");
        String userName = (String) SecurityUtils.getSubject().getPrincipal();


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
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


}
