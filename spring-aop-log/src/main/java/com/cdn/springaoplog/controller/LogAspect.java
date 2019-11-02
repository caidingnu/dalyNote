package com.cdn.springaoplog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

/**
 * @Description: 日志记录切点
 * @Date: 2018/11/1
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * controller 层切点
     */
    @Pointcut("execution(* com.cdn.springaoplog.controller..*.*(..))")
    public void controllerPointcut() {

    }

    /**
     * controller 层出入参日志记录
     *
     * @param joinPoint 切点
     * @return
     */
    @Around(value = "controllerPointcut()")
    public Object controllerLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 获取 request 中包含的请求参数
         */
        String uuid = UUID.randomUUID().toString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /**
         * 获取切点请求参数(class,method)
         */
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        StringBuilder params = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Object[] objects = joinPoint.getArgs();
            for (Object arg : objects) {
                params.append(mapper.writeValueAsString(arg));
            }
        }
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            params.append(request.getQueryString());
        }
        /**
         * 入参日志
         */
        logger.info("\n#####AOP日志打印日志开始######\n[AOP-LOG-START]\n\trequestMark: {}\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                        "requestMethod: {}\n\trequestParams: {}\n\ttargetClassAndMethod: {}\n\t调用接口:{}", uuid, request.getRemoteAddr(),
                request.getHeader("Content-Type"), request.getRequestURL(), request.getMethod(), params.toString(),
                method.getDeclaringClass().getName(), method.getName());
        /**
         * 出参日志
         */
        Object result = joinPoint.proceed();
        logger.info("\n[AOP-LOG-END]\n##############AOP日志打印日志结束##############\n\t返回值：{}", result);
        return result;
    }

    /**
     *     * 前置通知：目标方法执行之前执行以下方法体的内容      * @param jp    
     */
    @Before("execution(* com.cdn.springaoplog.dao.*.*(..))")
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
        logger.info("===============================================");
    }

    /**
     *     * 返回通知：目标方法正常执行完毕时执行以下代码     * @param jp     * @param result    
     */
    @AfterReturning(value = "execution(* com.cdn.springaoplog.dao.*.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        logger.info("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
        logger.info("===============================================");
    }


    /**
     *     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。     * @param jp    
     */
    @After("execution(* com.cdn.springaoplog.dao.*.*(..))")
    public void afterMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("【后置通知】this is a afterMethod advice...【" + methodName + "】");
        logger.info("===============================================");
    }


    /**
     *     * 异常通知：目标方法发生异常的时候执行以下代码    
     */
    @AfterThrowing(value = "execution(* com.cdn.springaoplog.dao.*.*(..))", throwing = "e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e) {
        String methodName = jp.getSignature().getName();
        logger.info("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
        logger.info("===============================================");
    }

}