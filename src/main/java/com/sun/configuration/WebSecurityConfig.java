//package com.sun.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 通过@EnableWebSecurity注解开启Spring Security的功能
// * 继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
// * @EnableGlobalMethodSecurity允许进入页面方法前检验
// * Created by sun on 2017-3-23.
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * configure(HttpSecurity http)方法，通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
//     * 例如以下代码指定了/和/test/不需要任何认证就可以访问，其他的路径都必须通过身份验证。
//     * 通过formLogin()定义当需要用户登录时候，转到的登录页面。
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/test/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                //设置默认登录成功跳转页面
//                .formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/error").permitAll()
//                .and()
//                //默认注销行为为logout，可以通过下面的方式来修改
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
//                .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
//                .and().and()
//                .exceptionHandling().accessDeniedPage("/accessDenied")
//                 .and()
//                //开启cookie保存用户数据
//                .rememberMe()
//                //设置cookie有效期
//                .tokenValiditySeconds(60 * 60 * 24 * 7)
//                //设置cookie的私钥
//                .key("sun");
//    }
//
//    /**
//     * configureGlobal(AuthenticationManagerBuilder auth)方法，
//     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
//     * 在内存中创建了一个用户，该用户的名称为admin，密码为123456，用户角色为USER。
//     * @param auth
//     * @throws Exception
//     */
//    /*@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER");
//    }*/
//
//    /**
//     * 用来配置无需安全检查的路径
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/image/**", "/**/favicon.ico");
//    }
//}
