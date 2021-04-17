package com.cdn.springsecurity.config;

import com.cdn.springsecurity.handler.*;
import com.cdn.springsecurity.utils.Md5En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * CDN
 * 2020/05/17 22:47
 */
@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailurehandler loginFailurehandler;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    LogOutSuccessHandler loginOutSuccessHandler;
    @Autowired
    CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
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

    /**
     * 以下的详细权限也可以以注解的形式写在Controller的接口上
     * 实际开发中实际只需要配置哪些开放（匿名）访问就行，其他的都需要鉴权
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/student/list").hasAuthority("student:list")   //访问  /student/list  需要 student:list权限
//                // /ha开头的需要同时拥有  ha:ha和ha2:ha2 才能访问
//                .antMatchers("/ha").access("hasAuthority('ha:ha') and hasAuthority('ha2:ha2')")
//                .antMatchers("/student/add").hasRole("student")  // //访问  /student/add  需要 student 角色
//                // /dba/开头的同时拥有多角色才可以访问
//                .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
//                //  /admin/    /example开头的需要认证
//                .antMatchers("/admin/**", "/example/**").authenticated()
                .antMatchers("/login-v", "/t-user/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();   //其他的不需要认证
//                // 允许对于网站静态资源的无授权访问
//                .antMatchers(HttpMethod.GET,
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**",
//                        "/swagger-ui.html/*"
//                ).permitAll()
//                .and()
        http.formLogin()  //允许表单登陆
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailurehandler)
//                .failureUrl("/login/err")   //可以配置登陆失败的controller，配置该项则 .failureHandler(loginFailurehandler) 失效
                .loginPage("/login-v")   // 登陆页面 ( springsecurity 自带登陆页面， 这个是自已定义的登陆页面)
                .loginProcessingUrl("/login") // 登陆处理器
                .successForwardUrl("/index")   //自定义登陆成功之后的页面 -->>>>>  设置登陆成功跳转页面之后，登陆成功的hangler 回调就不起作用了
                .and()    //对session控制   IF_REQUIRED 有需要就创建session
                .sessionManagement()
                //  基于token，所以不需要session,一定设置为STATELESS，
                //  否则会出现权限缓存（不回调AccessDeniedHandler 和 AuthenticationEntryPoint）
//                此处不是基于jwt，所有需要开启会话
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)//同一账号同时登录最大用户数
                // 是否保留已经登录的用户；为true，新用户无法登录；为 false，旧用户被踢出
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(sessionInformationExpiredStrategy); //会话信息过期策略会话信息过期策略(账号被挤下线)

//        自定义登出
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-v?logout")   //登出成功之后到登陆页面
                .deleteCookies("JSESSIONID") //登出之后删除cookie
//        .logoutSuccessHandler(loginOutSuccessHandler)   //自定义登出回调,设置登出回调之后，登出成功的url就失效
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
        ;
        http.csrf().disable();

    }
}
