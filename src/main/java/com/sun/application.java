package com.sun;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.test.MyProps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Configuration：标注此文件为一个配置项
 * @EnableAutoConfiguration：使用自动配置
 * @ComponentScan：可扫描的
 * @SpringBootApplication 包含上面三个
 * @ServletComponentScan 设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
 * @EnableConfigurationProperties 启动时加载配置的类, 该类用于加载配置文件, 可以在项目中通过依赖注入使用
 * @MapperScan 扫描的mapper接口
 * application：启动管理器
 * Created by sun on 2017-1-14.
 */
@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties({MyProps.class})
@MapperScan("com.sun.**.mapper")
public class application extends SpringBootServletInitializer {
    /* Servlet容器默认的Context路径是/
    DispatherServlet匹配的路径(servlet-mapping中的url-patterns)是*//*
    @ComponentScan路径被默认设置为SampleController的同名package，
    也就是该package下的所有@Controller，@Service, @Component, @Repository都会被实例化后并加入Spring Context中。*/
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(application.class);
        springApplication.run(args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
    //DataSource配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}