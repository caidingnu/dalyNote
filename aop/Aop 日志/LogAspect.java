package com.rx.erp.config;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Order(5)
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ObjectMapper objectMapper;

    private ThreadLocal<Date> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.rx.erp.controller.admin..*.*(..))")
    public void pointcut() {

    }

    /**
     * 前置通知，在Controller层操作前拦截
     *
     * @param joinPoint 切入点
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取当前调用时间
        startTime.set(new Date());
    }

    /**
     * 正常情况返回
     *
     * @param joinPoint 切入点
     * @param rvt       正常结果
     */
    @AfterReturning(pointcut = "pointcut()", returning = "rvt")
    public void doAfter(JoinPoint joinPoint, Object rvt) throws Exception {
        handleLog(joinPoint, null, rvt);
    }

    /**
     * 异常信息拦截
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) throws Exception {
        handleLog(joinPoint, e, null);
    }

    @Async
    public void handleLog(JoinPoint joinPoint, final Exception e, Object rvt) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获得注解
        Method method = getMethod(joinPoint);
        Date now = new Date();
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        StringBuilder params = new StringBuilder();
        if (args != null && paramNames != null) {
            params = handleParams(params, args, Arrays.asList(paramNames));
        }
        if (e != null) {
            logger.error(e.getMessage());
        }
//        entityLog.setResponseValue(rvt.toString());
        logger.info("\n#####[AOP-LOG-REQUEST]######\n\trequestMark: {}\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                        "requestMethod: {}\n\trequestParams: {}\n\ttargetClassAndMethod: {}\n\tmethodName:{}", IdUtil.simpleUUID(), request.getRemoteAddr(),
                request.getHeader("Content-Type"), request.getRequestURL(), request.getMethod(), params,
                method.getDeclaringClass().getName(), method.getName());
        try {
            logger.info("\n######[AOP-LOG-RESPONSE]######{}\n\t{}",now.getTime() - startTime.get().getTime(), rvt);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出NoSuchMethodException 异常则存在 toString 方法 ，安全的writeValueAsString ，否则 走 Object的 toString方法
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append("  ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append("  ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }
}
