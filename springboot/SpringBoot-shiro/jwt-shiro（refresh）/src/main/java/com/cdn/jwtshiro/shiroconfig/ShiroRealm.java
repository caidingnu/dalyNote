package com.cdn.jwtshiro.shiroconfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cdn.jwtshiro.constant.CommonConstant;
import com.cdn.jwtshiro.constant.DefContants;
import com.cdn.jwtshiro.entity.Role;
import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.jwt.JwtToken;
import com.cdn.jwtshiro.service.IRoleService;
import com.cdn.jwtshiro.service.ISysUserService;
import com.cdn.jwtshiro.utils.BeanUtils;
import com.cdn.jwtshiro.utils.JwtUtil;
import com.cdn.jwtshiro.utils.RedisUtil;
import com.cdn.jwtshiro.utils.oConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.ServletRequest;
import java.util.List;
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

    @Autowired
    IRoleService roleService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
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

        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合，比如“admin,test”
        List<Role> role;
        if (redisUtil.lGet(DefContants.LOGIN_USER_CACHERULES_ROLE+sysUser.getId()+sysUser.getId(),0,-1)!=null){
             role =  (List<Role> )redisUtil.get(DefContants.LOGIN_USER_CACHERULES_ROLE+sysUser.getId());
        }else {
            role = roleService.list(new QueryWrapper<Role>().lambda().eq(Role::getUserId, sysUser.getId()));
            redisUtil.lSet(DefContants.LOGIN_USER_CACHERULES_ROLE+sysUser.getId()+sysUser.getId(),role);
        }
        Set<String> roleSet = new TreeSet<>();
        role.forEach(role1 -> roleSet.add(role1.getRoleName()));
        info.setRoles(roleSet);

        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<String> permissionSet = new TreeSet<>();
        role.forEach(role1 -> permissionSet.add(role1.getPermission()));
        info.addStringPermissions(permissionSet);
        log.info("===============Shiro权限认证结束==============");
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
        String token = (String) auth.getCredentials();
        if (token == null) {
            log.info("————————身份认证失败——————————IP地址:  ");
            throw new AuthenticationException("token为空!");
        }
        SysUser sysUser1 = checkUserTokenIsEffect(token);
        // 校验token有效性
        log.info("============= token验证结束{}", token);
        return new SimpleAuthenticationInfo(sysUser1, token, getName());
    }



    /**
     * 校验token的有效性
     *
     * @param token
     */
    public SysUser checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String userId = JwtUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        SysUser sysUser = sysUserService.getById(userId);
        if (sysUser == null) {
            throw new AuthenticationException("用户不存在!");
        }

        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, userId, sysUser.getPassword())) {
            throw new AuthenticationException("Token失效请重新登录!");
        }

        // 判断用户状态
        if (!"0".equals(sysUser.getDelFlag())) {
//            throw new AuthenticationException("账号已被删除,请联系管理员!");
        }

        return sysUser;
    }


    /**
     * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
     * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 5、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 6、每次当返回为true情况下，都会给Response的Header中设置Authorization，该Authorization映射的v为cache对应的v值。
     * 7、注：当前端接收到Response的Header中的Authorization值会存储起来，作为以后请求token使用
     * 参考方案：https://blog.csdn.net/qq394829044/article/details/82763936
     *
     * @param
     * @param passWord
     * @return
     */
    public boolean jwtTokenRefresh(String token, String userId, String passWord) {
        String cacheToken = String.valueOf(redisUtil.get(DefContants.PREFIX_USER_TOKEN + token));
        if (StringUtils.isNotBlank(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verify(cacheToken, userId)) {
                String newAuthorization = JwtUtil.sign(userId);
                redisUtil.set(DefContants.PREFIX_USER_TOKEN + token, newAuthorization);
                // 设置超时时间
                redisUtil.expire(DefContants.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
            } else {
                redisUtil.set(DefContants.PREFIX_USER_TOKEN + token, cacheToken);
                // 设置超时时间
                redisUtil.expire(DefContants.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
            }
            return true;
        }
        return false;
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
