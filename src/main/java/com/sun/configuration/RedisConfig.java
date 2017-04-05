package com.sun.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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

    @Bean
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

}
