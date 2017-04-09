package com.sun.configuration;

import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 * Created by sun on 2017-4-2.
 */
@Configuration
@EnableTransactionManagement
public class ShiroConfiguration implements EnvironmentAware {

    private final Logger logger = Logger.getLogger(ShiroConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.redis.");
    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    /*@Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehcacheManager;
    }

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(EhCacheManager ehCacheManager){
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCacheManager(ehCacheManager);
        return realm;
    }*/
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm realm = new MyShiroRealm();
        return realm;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(propertyResolver.getProperty("host"));
        redisManager.setPort(Integer.valueOf(propertyResolver.getProperty("port")));
        redisManager.setExpire(1800);//配置过期时间--30分钟
        redisManager.setTimeout(Integer.valueOf(propertyResolver.getProperty("timeout")));
        // redisManager.setPassword(password);
        return redisManager;
    }
    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    /*public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }*/
    /**
     * shiro session的管理
     */
    /*public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }*/

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(MyShiroRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(realm);
        //用户授权/认证信息Cache, 采用EhCache缓存
        //securityManager.setCacheManager(getEhCacheManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis,注入这个会在退出时报错，好像是因为druid连接池的原因
        //securityManager.setSessionManager(SessionManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new MyShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        factoryBean.setSuccessUrl("/welcome");
        factoryBean.setUnauthorizedUrl("/403");
        loadShiroFilterChain(factoryBean);
        logger.info("shiro拦截器工厂类注入成功");
        return factoryBean;
    }

    /**
     * 加载ShiroFilter权限控制规则
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
        /**下面这些规则配置最好配置到配置文件中*/
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        //配置记住我或认证通过可以访问的地址
        //filterChainMap.put("/**", "user");
        /** authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器
         * org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
        //filterChainMap.put("/tUser", "authc");//输入http://localhost:8080/myEra/tUser会跳到登录页面
        //filterChainMap.put("/tUser/edit/**", "authc,perms[user:edit]");
        // anon：它对应的过滤器里面是空的,什么都没做,可以理解为不拦截
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainMap.put("/permission/userInsert", "anon");
        filterChainMap.put("/error", "anon");
        filterChainMap.put("/tUser/insert","anon");
        filterChainMap.put("/gif/getGifCode","anon");
        //filterChainMap.put("/**", "authc");
        //使用记住我可以访问的地址替换上面的，当没有记住我时跳转登录页面，记住我时跳到正常页面
        filterChainMap.put("/**", "user");
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
    }

    /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类，以及EhCacheManager类。
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码。
    3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
    6.ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
    7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
    8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。*/

}
