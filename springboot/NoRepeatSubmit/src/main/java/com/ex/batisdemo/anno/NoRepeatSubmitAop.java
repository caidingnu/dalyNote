package com.ex.batisdemo.anno;

import com.ex.batisdemo.util.RedisUtil;
import com.ex.batisdemo.domain.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
public class NoRepeatSubmitAop {

    @Autowired
    RedisUtil redisUtil;

    /**
     * <p> 【环绕通知】 用于拦截指定方法，判断用户表单保存操作是否属于重复提交 <p>
     * <p>
     * 定义切入点表达式： execution(public * (…))
     * 表达式解释： execution：主体 public:可省略 *：标识方法的任意返回值 任意包+类+方法(…) 任意参数
     * <p>
     * com.zhengqing.demo.modules.*.api ： 标识AOP所切服务的包名，即需要进行横切的业务类
     * .*Controller ： 标识类名，*即所有类
     * .*(..) ： 标识任何方法名，括号表示参数，两个点表示任何参数类型
     *
     * @param pjp：切入点对象
     * @param noRepeatSubmit:自定义的注解对象
     * @return: java.lang.Object
     */
    @Around("execution(* com.ex.batisdemo.controller.*.*(..)) && @annotation(noRepeatSubmit)")
    public Object doAround(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) {
        Result result = new Result();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            // 拿到ip地址、请求路径、token
            String ip = request.getRemoteAddr();
            String url = request.getRequestURL().toString();
//            String token = request.getHeader(Constants.REQUEST_HEADERS_TOKEN);
            // 现在时间
            long now = System.currentTimeMillis();
            // 自定义key值方式
            String key = "REQUEST_FORM_" + ip;
            if (redisUtil.hasKey(key)) {
                // 上次表单提交时间
                long lastTime = Long.parseLong((String) redisUtil.get(key));
                // 如果现在距离上次提交时间小于设置的默认时间 则 判断为重复提交 否则 正常提交 -> 进入业务处理
                if ((now - lastTime) > noRepeatSubmit.time()) {
                    // 非重复提交操作 - 重新记录操作时间
                    redisUtil.set(key, String.valueOf(now));
                    // 进入处理业务
                    result.setData(pjp.proceed());
                    return result;
                } else {
                    result.setStatus(444);
                    result.setMessage("请勿重复提交");
                    return result;
                }
            } else {
                // 这里是第一次操作
                redisUtil.set(key, String.valueOf(now));
                result.setStatus(555);
                result.setData(pjp.proceed());
                return result;
            }
        } catch (Throwable e) {
            result.setStatus(500);
            result.setMessage("校验表单重复提交时异常");
            return result;
        }

    }

}