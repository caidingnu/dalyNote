package com.ocr.mytest.aop;


import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring AOP实现日志管理
 */
@Aspect
@Component
public class AopAspect {

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    Logger logger = LoggerFactory.getLogger(AopAspect.class);


//    @Autowired
//    private LogService logService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private IpInfoUtil ipInfoUtil;

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * Controller层切点,注解方式
     */
    //@Pointcut("execution(* *..controller..*Controller*.*(..))")
    @Pointcut("@annotation(com.ocr.mytest.aop.CdnLog)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 (在方法执行之前返回)用于拦截Controller层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {

        //线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);

    }


    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CdnLog annotation = method.getAnnotation(CdnLog.class);
        String value = annotation.description();

        System.out.println(value);

        try {
            String username = "";
            String description = getControllerMethodInfo(joinPoint).get("description").toString();
            Map<String, String[]> requestParams = request.getParameterMap();

            Log log = new Log();

            //请求用户
            //后台操作(非登录)
//            if (Objects.equals(getControllerMethodInfo(joinPoint).get("type"), 0)) {
//                //后台操作请求(已登录)
//                User user = userService.getLoginUser(request);
//                if (user != null) {
//                    username = user.getUsername();
//                }
//                log.setUsername(username);
//            }

            log.setRequestMethodName(method.toString());
            log.setUsername("暂时写死，根据情况来定");
            //日志标题
            log.setName(description);
            //日志类型
            log.setLogType((String) getControllerMethodInfo(joinPoint).get("type"));
            //日志请求url
            log.setRequestUrl(request.getRequestURI());
            //请求方式
            log.setRequestType(request.getMethod());
            //请求参数
            log.setRequestParam(mapToString(requestParams));
            log.setSystem(System.getProperty("os.name"));
            //其他属性
            log.setIp(getIpAddr(request));
            log.setCreateBy("system");
            log.setUpdateBy("system");
            log.setCreateTime(new Date());
            log.setUpdateTime(new Date());
            log.setDelFlag(0);

            //.......
            //请求开始时间
            long beginTime = beginTimeThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            //请求耗时
            Long logElapsedTime = endTime - beginTime;
            log.setCostTime(logElapsedTime.intValue());

//            请求日期
            log.setRequestTime(timeFormat(beginTime));
            System.out.println(log);

//            警告形式保存
            logger.warn(log.toString());
            //持久化(存储到数据或者ES，可以考虑用线程池)
//            logService.insert(log);
//            ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(log, logService));
        } catch (Exception e) {
            System.out.println("AOP后置通知异常--->>>" + e);
        }
    }


//    /**
//     * 保存日志至数据库
//     */
//    private static class SaveSystemLogThread implements Runnable {
//
//        private Log log;
//        private LogService logService;
//
//        public SaveSystemLogThread(Log esLog, LogService logService) {
//            this.log = esLog;
//            this.logService = logService;
//        }
//
//        @Override
//        public void run() {
//            logService.insert(log);
//        }
//    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/10/21
     */
    public String timeFormat(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>(16);
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";
        String type = null;

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                //比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
                continue;
            }
            description = method.getAnnotation(CdnLog.class).description();
            type = method.getAnnotation(CdnLog.class).type();
            map.put("description", description);
            map.put("type", type);
        }
        return map;
    }


    public static String mapToString(Map<String, String[]> paramMap) {

        if (paramMap == null) {
            return "";
        }
        Map<String, Object> params = new HashMap<>(16);
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {

            String key = param.getKey();
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
//            String obj = StrUtil.endWithIgnoreCase(param.getKey(), "password") ? "你是看不见我的" : paramValue;
            String obj = key.equalsIgnoreCase("password") ? "密码隐藏" : paramValue;
            params.put(key, obj);
        }
        return new Gson().toJson(params);
    }


    /**
     * 获取客户端IP地址
     *
     * @param request 请求
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
