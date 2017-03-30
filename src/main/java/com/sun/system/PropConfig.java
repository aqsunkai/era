package com.sun.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by sun on 2017-3-30.
 */
@Configuration
@PropertySource(value = {"classpath:/config/propConfigs.properties", "classpath:/config/abc.properties"}, ignoreResourceNotFound = true)
public class PropConfig {

    // PropertySourcesPlaceholderConfigurer这个bean，
    // 这个bean主要用于解决@value中使用的${…}占位符。
    // 假如你不使用${…}占位符的话，可以不使用这个bean。
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
