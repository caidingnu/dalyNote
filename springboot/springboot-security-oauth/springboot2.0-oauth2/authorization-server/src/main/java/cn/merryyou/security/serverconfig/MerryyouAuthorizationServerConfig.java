package cn.merryyou.security.serverconfig;

import cn.merryyou.security.properties.OAuth2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/1/15 0015.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
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


    /**
     * 配置客户端一些信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//        InMemoryClientDetailsServiceBuilder build = clients.inMemory();
//        if (ArrayUtils.isNotEmpty(oAuth2Properties.getClients())) {
//            for (OAuth2ClientProperties config : oAuth2Properties.getClients()) {
//                build.withClient(config.getClientId())
//                        .secret(passwordEncoder.encode(config.getClientSecret()))
//                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
//                        .refreshTokenValiditySeconds(60 * 60 * 24 * 15)
//                        .authorizedGrantTypes("refresh_token", "password", "authorization_code")//OAuth2支持的验证模式
//                        .redirectUris("http://www.merryyou.cn")
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
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients(); //允许使用clientId和clientSecret 获取access_token
    }

    /**
     * 要存储到redis必须要使用oauth2.0和spring-security-oauth2
     * 使用spring-cloud-starter-oauth2  无法存入redis
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore(RedisConnectionFactory redisConnectionFactory) {

//        return new JdbcTokenStore(dataSource);
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        //扩展token返回结果
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
}
