package com.cdn.jwtshiro.shiroconfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cdn.jwtshiro.constant.CommonConstant;
import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.service.ISysUserService;
import com.cdn.jwtshiro.utils.JwtUtil;
import com.cdn.jwtshiro.utils.RedisUtil;
import com.cdn.jwtshiro.utils.oConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Description: 用户登录鉴权和获取用户授权
 * @Author: Scott
 * @Date: 2019-4-23 8:13
 * @Version: 1.1
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private ISysUserService sysUserService;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 权限信息认证(包括角色以及权限)是用户访问controller的时候才进行验证(redis存储的此处权限信息)
     * 触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @param principals 身份信息
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("===============Shiro权限认证开始============ [ roles、permissions]==========");
        String username = null;
        if (principals != null) {
            SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
            username = sysUser.getUsername();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合，比如“admin,test”
//		Set<String> roleSet = sysUserService.getUserRolesSet(username);
        Set<String> roleSet = new TreeSet<>();
        info.setRoles(roleSet);

        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
//		Set<String> permissionSet = sysUserService.getUserPermissionsSet(username);
        Set<String> permissionSet = new TreeSet<>();
        info.addStringPermissions(permissionSet);
        log.info("===============Shiro权限认证成功==============");
        return info;
    }

    /**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
     * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
     *
     * @param auth 用户登录的账号密码信息
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) auth;

        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            throw new AuthenticationException("用户不存在!");
        }

        // 校验token有效性
//        SysUser SysUser = this.checkUserTokenIsEffect(token);
        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }



    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

}
