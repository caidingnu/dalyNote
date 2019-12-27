package com.study.shiro.controller;

import com.study.shiro.common.JsonResult;
import com.study.shiro.dao.UserDao;
import com.study.shiro.entity.User;
import com.study.shiro.exception.MyException;
import com.study.shiro.shiroconfig.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDao userDao;

    /**
     * desc:注册
     * param:
     * return:
     * author: CDN
     * date: 2019/11/30
     */
    @RequestMapping("/register")
    public Object register(User user) {
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            throw new MyException("账号或密码不能为空");
        }
        User user1 = userDao.getUserByUserName(user.getUserName());
        if (null != user1) {
            throw new MyException("用户名已存在");
        }
//        此处把唯一的用户名作为盐值
        String pwd = ShiroUtil.encrypt(user.getPassword(), user.getUserName());
        user.setPassword(pwd);
        int register = userDao.register(user);

        if (register != 1) {
            throw new MyException("注册失败");
        }
        return JsonResult.buildSuccess("注册成功");
    }


    @PostMapping("/login")
    public Map<String, Object> login(User user, boolean rememberMe) {

        Map<String, Object> map = new HashMap<>();
        // 2、Shiro
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        // timeout:-1000ms 永不超时
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        //记住我(下次登录则不会调用login方法)
        token.setRememberMe(rememberMe);

        logger.info("对用户[" + user.getUserName() + "]进行登录验证..验证开始");
        try {
            currentUser.login(token);
            //验证是否登录成功
            if (currentUser.isAuthenticated()) {
                logger.info("用户[" + user.getUserName() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                System.out.println("用户[" + user.getUserName() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                map.put("success", true);
                map.put("msg", "登录成功");
                return map;
            } else {
                token.clear();
                System.out.println("用户[" + user.getUserName() + "]登录认证失败,重新登陆");
                map.put("success", false);
                map.put("msg", "登录失败");
                return map;
            }
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + user.getUserName() + "]进行登录验证..验证失败-username wasn't in the system");
            map.put("success", false);
            map.put("errorInfo", "用户不存在!");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + user.getUserName() + "]进行登录验证..验证失败-password didn't match");
            map.put("success", false);
            map.put("errorInfo", "用户名或者密码错误!");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + user.getUserName() + "]进行登录验证..验证失败-account is locked in the system");
            map.put("success", false);
            map.put("errorInfo", "用户锁定!");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            logger.error(ae.getMessage());
        }
        return map;
    }

    @RequiresPermissions({"user:select"}) //没有的话 AuthorizationException
    @PostMapping("/select")
    public Map<String, Object> selectPermission() {
        System.out.println("select");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前角色有查看的权力");
        return map;
    }


    @RequiresPermissions({"user:insert"}) //没有的话 AuthorizationException
    @PostMapping("/insert")
    public Map<String, Object> insertPermission() {
        System.out.println("insert");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前角色有增加的权力");
        return map;
    }

    @RequiresPermissions({"user:update"}) //没有的话 AuthorizationException
    @PostMapping("/update")
    public Map<String, Object> updatePermission() {
        System.out.println("update");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前角色有更新的权力");
        return map;
    }

    @RequiresPermissions({"user:delete"}) //没有的话 AuthorizationException
    @PostMapping("/delete")
    public Map<String, Object> deletePermission() {
        System.out.println("delete");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前角色有删除的权力");
        return map;
    }

    @RequiresRoles({"vip"}) //没有的话 AuthorizationException
    @PostMapping("/vip")
    public Map<String, Object> vipRole() {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户具有 vip 角色");
        return map;
    }

    @RequiresRoles({"ip"}) //没有的话 AuthorizationException
    @PostMapping("/ip")
    public Map<String, Object> ipRole() {
        System.out.println("ip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户具有 ip 角色");
        return map;
    }

    @RequiresRoles({"p"}) //没有的话 AuthorizationException
    @PostMapping("/p")
//    @RequiresRoles(value = {"admin","manager","writer"}, logical = Logical.OR)
    public Map<String, Object> pRole() {
        System.out.println("p");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户具有 p 角色");
        return map;
    }
  /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/12/1
     */
    @RequestMapping("rememberMe")
    @ResponseBody
    public String rememberMe() {

        return "记住我就可已访问";
    }


    @RequestMapping("reat")
    @ResponseBody
    public String reat() {

        return "admin登陆才行";
    }

}
