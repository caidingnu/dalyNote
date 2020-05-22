//package cn.merryyou.security.config;
//
//import cn.merryyou.security.properties.OAuth2Properties;
//import cn.merryyou.security.security.jwt.MerryyouJwtTokenEnhancer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
//    token 相关配置
// */
//@Configuration
//public class TokenStoreConfig {
//    /**
//     * redis连接工厂
//     */
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//
//    /**
//     * 用于存放token
//        *InMemoryTokenStore:token存储在本机的内存之中
//                *JdbcTokenStore:token存储在数据库之中
//                *JwtTokenStore:token不会存储到任何介质中
//                *RedisTokenStore:token存储在Redis数据库之中
//     * @return
//     */
//    @Bean
//    @ConditionalOnProperty(prefix = "merryyou.security.oauth2", name = "storeType", havingValue = "redis")
//    public TokenStore redisTokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
//    }
//
//    /**
//     * jwt TOKEN配置信息
//     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "merryyou.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
//    public static class JwtTokenCofnig{
//
//        @Autowired
//        private OAuth2Properties oAuth2Properties;
//
//        /**
//         * 使用jwtTokenStore存储token
//         * @return
//         */
//        @Bean
//        public TokenStore jwtTokenStore(){
//            return new JwtTokenStore(jwtAccessTokenConverter());
//        }
//
//        /**
//         * 用于生成jwt
//         * @return
//         */
//        @Bean
//        public JwtAccessTokenConverter jwtAccessTokenConverter(){
//            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//            accessTokenConverter.setSigningKey(oAuth2Properties.getJwtSigningKey());//生成签名的key
//            return accessTokenConverter;
//        }
//
//        /**
//         * 用于扩展JWT
//         * @return
//         */
//        @Bean
//        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
//        public TokenEnhancer jwtTokenEnhancer(){
//            return new MerryyouJwtTokenEnhancer();
//        }
//
//    }
//}
