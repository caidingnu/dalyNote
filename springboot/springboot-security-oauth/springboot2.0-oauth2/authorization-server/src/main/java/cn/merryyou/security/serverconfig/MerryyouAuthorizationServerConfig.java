package cn.merryyou.security.serverconfig;

import cn.merryyou.security.properties.OAuth2ClientProperties;
import cn.merryyou.security.properties.OAuth2Properties;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Configuration
@EnableAuthorizationServer
public class MerryyouAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private OAuth2Properties oAuth2Properties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    AuthorizationCodeServices authorizationCodeServices;


    /**
     * 配置客户端一些信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//         -----------  配置到用户详情到内存
//        InMemoryClientDetailsServiceBuilder build = clients.inMemory();
//        if (ArrayUtils.isNotEmpty(oAuth2Properties.getClients())) {
//            for (OAuth2ClientProperties config : oAuth2Properties.getClients()) {
//                build.withClient(config.getClientId())  //client_id
//                        .secret(passwordEncoder.encode(config.getClientSecret())) //client_security
//                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
//                        .refreshTokenValiditySeconds(60 * 60 * 24 * 15)
//                        .authorizedGrantTypes("refresh_token", "password", "authorization_code")//OAuth2支持的验证模式
//                        .resourceIds("")
//                        .redirectUris("http://www.merryyou.cn")  //验证回调地址
//                          .autoApprove(false)  //跳转到授权页面,false就是跳转到授权页面，true则直接跳转到目标url
//                        .scopes("all");
//            }
//        }
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        //允许表单认证
//        oauthServer.allowFormAuthenticationForClients();
//        oauthServer.passwordEncoder(passwordEncoder);
//    }

    /**
     * springSecurity 授权表达式，
     * 令牌访问端点 安全策略
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        //允许使用clientId和clientSecret 获取access_token，**********  不配置的话密码模式访问不了
        security.allowFormAuthenticationForClients();   //密码模式需要
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore) //令牌管理
                .authenticationManager(authenticationManager)  //密码模式需要
                .authorizationCodeServices(authorizationCodeServices)  //授权码模式需要
                .reuseRefreshTokens(true)
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .userDetailsService(userDetailsService);


        //扩展token返回结果-----------------
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            //jwt
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * token存储策略---
     *  此处是简单生成，jwt的方式还需要一些配置
     * <p>
     * 要存储到redis必须要使用oauth2.0和spring-security-oauth2
     * 使用spring-cloud-starter-oauth2  无法存入redis
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
//        return new InMemoryTokenStore();  //内存
//        return new JdbcTokenStore(dataSource); //数据库
//        return new JwtTokenStore(jwtAccessTokenConverter);
        return new RedisTokenStore(redisConnectionFactory);  //redis
    }


}
