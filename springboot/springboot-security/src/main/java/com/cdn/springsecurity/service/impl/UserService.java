package com.cdn.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cdn.springsecurity.entity.TUser;
import com.cdn.springsecurity.mapper.TPermissionMapper;
import com.cdn.springsecurity.mapper.TUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * CDN
 * 2020/05/17 22:29
 */
@Service
public class UserService implements UserDetailsService {


    @Autowired
    TUserRoleMapper roleMapper;
    @Autowired
    TPermissionMapper tPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<TUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TUser::getUserName, s);
        TUser tUser = new TUser();
        TUser tUser1 = tUser.selectOne(lambdaQueryWrapper);

        if (tUser1 == null) {
//          security的  provider 会自己抛异常，直接返回null就行
            return null;
        }

        List<String> roleAndAuthorityLsit = new ArrayList<>();
        /**
         *=================================================================================================
         *  AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_USER"));//设置权限和角色
         *         // 1. commaSeparatedStringToAuthorityList放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
         *         // 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
         ==================================================================================================
         */
//        角色
//        查询用户角色
        Set<String> roleSet = roleMapper.selectRoleByUserId(tUser1.getId());
        if (roleSet.size() > 0) {
            for (String s1 : roleSet) {
//                加 ROLE_前缀
                roleAndAuthorityLsit.add("ROLE_" + s1);
            }
        }

//        权限-------------------
        Set<String> authorityMarkByUserId = tPermissionMapper.getAuthorityMarkByUserId(tUser1.getId());
        for (String s1 : authorityMarkByUserId) {
            roleAndAuthorityLsit.add(s1);
        }

//        集合转数组
        String[] grantedAuthorities = new String[roleAndAuthorityLsit.size()];
        roleAndAuthorityLsit.toArray(grantedAuthorities);

        UserDetails userDetail = User.withUsername(tUser1.getUserName()).password(tUser1.getPassword()).authorities(grantedAuthorities).build();
        return userDetail;
    }


    /**
     * 获取当前用户
     */
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

}
