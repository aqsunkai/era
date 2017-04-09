package com.sun.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.lang.reflect.Method;

/**
 * 项目启动加载redis
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport implements EnvironmentAware {

    private final Logger logger = Logger.getLogger(RedisConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.redis.");
    }

    @Bean("jedisPool")
    public JedisPool redisPoolFactory() {
        logger.info("redis地址：" + propertyResolver.getProperty("host") +
                ":" + propertyResolver.getProperty("port"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.valueOf(propertyResolver.getProperty("pool.max-idle")));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(propertyResolver.getProperty("pool.max-wait")));

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, propertyResolver.getProperty("host"),
                  Integer.valueOf(propertyResolver.getProperty("port")),
                  Integer.valueOf(propertyResolver.getProperty("timeout")));
        logger.info("jedisPool注入成功！！");
        return jedisPool;
    }
    /**
     * 生成key的策略
     * 缓存注解没有配置key参数走此方法
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    /**
     * RedisTemplate配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    /**
     * 管理缓存
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间--一天
        rcm.setDefaultExpiration(86400);//秒
        return rcm;
    }

    /*@Bean
    @ConditionalOnMissingBean(RedisClient.class)//容器中如果没有RedisClient这个类,那么自动配置这个RedisClient
    public RedisClient redisClient(@Qualifier("jedisPool")JedisPool pool) {
        RedisClient redisClient = new RedisClient();
        redisClient.setJedisPool(pool);
        return redisClient;
    }*/
}
