package com.cun;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@SpringBootApplication
public class SpringBootUrpApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootUrpApplication.class, args);
//获取restcontroller注解的类名
        String[] beanNamesForAnnotation = run.getBeanNamesForAnnotation(RestController.class);
//获取类对象
        for (String str : beanNamesForAnnotation) {
            Object bean = run.getBean(str);
            Class<?> forName = bean.getClass();
//获取requestmapping注解的类
            RequiresPermissions declaredAnnotation = forName.getAnnotation(RequiresPermissions.class);
            String url_path = "";
            if (declaredAnnotation != null) {
                String[] value = (declaredAnnotation.value());
//地址
                url_path = value[0];
            }
            for (Method method : forName.getDeclaredMethods()) {
                RequestMapping annotation2 = method.getAnnotation(RequestMapping.class);
                if (annotation2 != null) {
                    url_path += annotation2.value()[0];
                    System.out.println("方法路径" + url_path + "方法名" + method.getName());
                    Parameter[] parameters = method.getParameters();
//                    for (int i = 0; i < parameters.length; i++) {
//                        String[] split = parameters[i].getType().toString().split("\\.");
//                        System.out.println("参数类型" + split[split.length - 1] + "参数名字" + parameters[i].getName());
//                        if ((parameters[i].getType().getClassLoader() != null)) {
//                            for (Field dield : parameters[i].getType().getDeclaredFields()) {
//                                dield.setAccessible(true);
//                                String parametername = dield.getName();
//                                Class<?> type2 = dield.getType();
//                                String[] split2 = type2.getName().split("\\.");
//                                System.out.println("字段类型" + split2[split2.length - 1] + "字段名字" + parametername);
//                            }
//                        }
//                    }
                }
            }
        }
    }
}