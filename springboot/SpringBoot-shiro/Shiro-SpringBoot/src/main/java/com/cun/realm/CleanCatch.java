package com.cun.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * desc：
 * author CDN
 * create 2019-11-30 00:45
 * version 1.0.0
 */
public class CleanCatch {


    /**
     * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限)
     * @param myRealm 自定义的realm
     * @param username 用户名
     */
    public static void reloadAuthorizing(MyRealm myRealm,String username){
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户
        SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName);
        subject.runAs(principals);
        myRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
    }
}
