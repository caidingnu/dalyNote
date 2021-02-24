package com.example.demo.aspect;

import com.example.demo.annotation.SwitchDataSource;
import com.example.demo.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author CDN
 * @desc
 * @date 2020/07/09 15:38
 */
@Aspect
@Component
public class DynamicDataSourceAspect {


    @Pointcut( "@annotation(com.example.demo.annotation.SwitchDataSource)")
    public void mybatisExecutionSqlPointcut(){

    }
    /**
     * 在SwitchDataSource注解的方法之前执行
     */
    @Before("mybatisExecutionSqlPointcut()")
    public void beforeMybatisExecutionSql(JoinPoint joinPoint){
        //获取当前访问的class
        Class<?> aClass = joinPoint.getTarget().getClass();
        //获取方法的签名
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        //获取访问的方法名字
        String methodName = methodSignature.getName();
        //得到当前方法的参数类型
        Class[] argsClass = methodSignature.getParameterTypes();
        String dataSourceName = DataSourceContextHolder.DEFAULT_DATDASOURCE_NAME;
        try {
            Method method = aClass.getMethod(methodName, argsClass);
            //判断是否存在数据源切换注解
            if (method.isAnnotationPresent(SwitchDataSource.class)) {
                SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);
                //赋值数据源的名称
                dataSourceName = annotation.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //切换数据源
        DataSourceContextHolder.setDatasourceName(dataSourceName);
    }

    /**
     * 在SwitchDataSource注解的方法之后执行
     */
    @After("mybatisExecutionSqlPointcut()")
    public void afterMybatisExecutionSql(){
        //清除进程中数据源的名字（恢复默认数据源）
        DataSourceContextHolder.clearDatasourceName();
    }

}
