package com.stu.aoppermission.aspect;

import com.stu.aoppermission.anno.Permission;
import com.stu.aoppermission.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

@Aspect
@Component
public class PermissionAspect {
    @Autowired
    private UserService userService;

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.stu.aoppermission.controller.*.*(..))")
    public void privilege() {
    }

    /**
     * 权限环绕通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @ResponseBody
    @Around(value = "privilege() && @annotation(permission)")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        //如果该方法上没有权限注解，直接调用目标方法
        if (StringUtils.isEmpty(permission)) {
            return joinPoint.proceed();
        } else {
            //获取当前用户
            Object[] args = joinPoint.getArgs();
            if (args == null || args[0] == null) {
                return ("无法获取当前用户信息");
            }
            String currentUser = args[0].toString();
            System.out.println("访问用户：" + currentUser);
            if (!userService.isAdmin(currentUser)) {
                return "您不是管理员";
            } else {
                System.out.println("您是管理员");
                //是管理员时，才返回所需要的信息
                return joinPoint.proceed();
            }
        }
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     * @param p
     */
    @Before("privilege()&& @annotation(p)")
    public void Before(JoinPoint joinPoint, Permission p) {
        System.out.println(p);
    }


    /**
     * 正常情况返回
     *
     * @param joinPoint 切入点
     * @param obj       正常返回结果
     */
    @AfterReturning(pointcut = "privilege()&& @annotation(p)", returning = "obj")
    public void doAfter(JoinPoint joinPoint, Permission p, Object obj) {
        System.out.println(p);
    }


    /**
     * 异常信息拦截
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "privilege()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) throws Exception {
        System.out.println("出现异常");
        e.printStackTrace();
    }

}
