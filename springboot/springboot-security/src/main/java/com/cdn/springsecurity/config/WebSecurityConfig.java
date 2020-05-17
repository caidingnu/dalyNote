package com.cdn.springsecurity.config;

import com.cdn.springsecurity.handler.LoginFailurehandler;
import com.cdn.springsecurity.handler.LoginSuccessHandler;
import com.cdn.springsecurity.utils.Md5En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * CDN
 * 2020/05/17 22:47
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailurehandler loginFailurehandler;


// 定义用户信息服务（查询用户信息）


    //    密码编码器 ---------------------------------密码编码器 start---------------------------------------
    //    - -->>>>不需要加密
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        return NoOpPasswordEncoder.getInstance();
//    }


//     ---->>>>>>  security  自带的盐值加密 --BCrypt
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }

    //     自定义MD5 加密
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new Md5En();
    }

    //--------------------------------------密码编码器 end --------------------------------------
    //     安全拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/list").hasAuthority("student:list")   //访问  /student/list  需要 student:list权限
                .antMatchers("/student/add").hasRole("student")  // //访问  /student/add  需要 student 角色
                .antMatchers("/admin/**","/example/**")
                .authenticated()  //  /admin/ 开头的需要认证
                .anyRequest().permitAll()   //其他的不需要认证
                .and()
                .formLogin()  //允许表单登陆
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailurehandler)
                .loginPage("/login-v")   // 登陆页面 ( springsecurity 自带登陆页面， 这个是自已定义的登陆页面)
                .loginProcessingUrl("/login") // 登陆处理器
                .successForwardUrl("/index")   //自定义登陆成功之后的页面 -->>>>>  设置登陆成功跳转页面之后，登陆成功的hangler 回调就不起作用了
//        .and()    //对session控制   IF_REQUIRED 有需要就创建session
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

//        自定义登出
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-v?logout")   //登出成功之后到登陆页面
//        .logoutSuccessHandler()   //自定义登出回调
        ;
        http.csrf().disable();

    }
}
