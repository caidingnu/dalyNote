package com.cdn.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdn.springsecurity.entity.TUser;
import com.cdn.springsecurity.mapper.TPermissionMapper;
import com.cdn.springsecurity.mapper.TUserMapper;
import com.cdn.springsecurity.mapper.TUserRoleMapper;
import com.cdn.springsecurity.service.ITUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
@Slf4j
@Service("userServiceImpl")
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Autowired
    TUserRoleMapper roleMapper;
    @Autowired
    TPermissionMapper tPermissionMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<TUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TUser::getUserName, s);
        TUser tUser = new TUser();
        TUser tUser1 = tUser.selectOne(lambdaQueryWrapper);

        if (tUser1 == null) {
            throw new UsernameNotFoundException("账号密码错误");
        }

        /**
         *=================================================================================================
         *  AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_USER"));//设置权限和角色
         *         // 1. commaSeparatedStringToAuthorityList放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
         *         // 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
         ==================================================================================================
         */

//        查询用户角色---------------------角色加 ROLE_   前缀
        List<String> roleList = roleMapper.selectRoleByUserId(tUser1.getId());
        String roleString =  roleList.stream().map(role->"ROLE_"+role).collect(Collectors.joining(","));
//        查询用户权限-------------------
        List<String> authorityMarkByUserId = tPermissionMapper.getAuthorityMarkByUserId(tUser1.getId());
        String authString = String.join(",", authorityMarkByUserId);
//        构建权限、角色集合
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleString + "," + authString);
        return User.withUsername(tUser1.getUserName()).password(tUser1.getPassword()).authorities(authorities).build();
    }


    /**
     * 获取当前用户
     */
    @Override
    public TUser getCurrUser() {
        Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authencation.getPrincipal();
        TUser user = new TUser();
        if (!(principal instanceof UserDetails)) {
            user.setRemarks("用户未登录");
        } else {
            UserDetails userDetails = (UserDetails) principal;
            user.setUserName(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());

            Set<String> roleSet = new HashSet<>();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                roleSet.add(authority.getAuthority());
            }
            user.setRole(roleSet);
        }
        return user;
    }


    @Override
    public String login(String username, String password) {
        String token = "成功";
        UserDetails userDetails = this.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            log.error("登录异常:{}","密码不正确");
            throw new UsernameNotFoundException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            UserDetails userDetails = (UserDetails) principal;

            Set<String> urls = new HashSet<>(); // 数据库读取 //读取用户所拥有权限的所有URL

            for(GrantedAuthority ga : userDetails.getAuthorities()){
                urls.add(ga.getAuthority());
            }


            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                String requestUrl = request.getRequestURI();
                System.out.println("访问路径：" + requestUrl);
                System.out.println("当前拥有的权限url：" + url);
                if (antPathMatcher.match("/" + url, requestUrl)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
