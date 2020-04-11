//package com.cun.realm;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *   ★★★★★★★★    该ShiroRealm特点是登录的时候就把用户权限用 USer中的List缓存起来，之后权限验证的时候就不需要再去查询数据库 ★★★★★★★★
// */
//public class ShiroRealm extends AuthorizingRealm {
//
//    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
//
//    //一般这里都写的是servic，这里省略直接调用dao
//    @Autowired
//    private UUserDao uUserDao;
//    @Autowired
//    private URoleDao uRoleDao;
//    @Autowired
//    private UPermissionDao uPermissionDao;
//
//    /**
//     * 登录认证
//     * @param authenticationToken
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        logger.info("验证当前Subject时获取到token为：" + token.toString());
//        //查出是否有此用户
//        String nickName = token.getUsername();
//        char[] password = token.getPassword();
//        UUser hasUser = uUserDao.selectAllByName(nickName);
//
//        if (hasUser != null) {
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            List<URole> rlist = uRoleDao.findRoleByUid(hasUser.getId());//获取用户角色
//            List<UPermission> plist = uPermissionDao.findPermissionByUid(hasUser.getId());//获取用户权限
//
//
//            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
//            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
//            for (URole role : rlist) {
//                roleStrlist.add(role.getName());
//            }
//            for (UPermission uPermission : plist) {
//                perminsStrlist.add(uPermission.getName());
//            }
//            //清空权限缓存( 实际开发中在 重新为用户分配角色)
//            clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//
//
//            hasUser.setRoleStrlist(roleStrlist);
//            hasUser.setPerminsStrlist(perminsStrlist);
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            return new SimpleAuthenticationInfo(hasUser, hasUser.getPswd(), getName());
//        }
//
//        return null;
//    }
//
//    /**
//     * 权限认证
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        logger.info("##################执行Shiro权限认证##################");
//        UUser user = (UUser) principalCollection.getPrimaryPrincipal();
//        if (user != null) {
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            //用户的角色集合
//            info.addRoles(user.getRoleStrlist());
//            //用户的权限集合
//            info.addStringPermissions(user.getPerminsStrlist());
//
//            return info;
//        }
//        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
//        return null;
//    }
//
//
//
//    /**
//     * 清理权限缓存
//     */
//    public void clearCachedAuthorization(){
//        //清空权限缓存
//        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//    }
//}