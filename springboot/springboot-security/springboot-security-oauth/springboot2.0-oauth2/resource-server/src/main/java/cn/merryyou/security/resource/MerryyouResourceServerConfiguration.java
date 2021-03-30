package cn.merryyou.security.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

@Configuration
@EnableResourceServer
public class MerryyouResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCES_ID = "testResouecesId";  //数据库表oauth_client_details
    private static final String scop = "resouecesScop"; //数据库表oauth_client_details



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCES_ID)
                .stateless(true)
        ;  //标识资源id,数据库可以配置
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api")
                // 拦截用户，必须具有所列权限
                .hasAuthority("ROLE_USER");
    }
}
