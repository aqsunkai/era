package com.sun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by sun on 2017-3-21.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("config/ViewControllerRegistry.properties");
            Enumeration<?> e = props.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                registry.addViewController(key).setViewName(props.getProperty(key));
            }
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        } catch (IOException e) {
            System.out.println("Error load ViewControllerRegistry.properties !!! Check the file.");
        }
    }

    /**
     * 此方法把该拦截器实例化成一个bean,否则在拦截器里无法注入其它bean
     * @return
     */
    @Bean
    SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }
    /**
     * 配置拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/permission/userInsert",
                        "/error","/tUser/insert","/gif/getGifCode");
    }

}
