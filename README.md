2017-03-21 jsp页面正常访问，资源文件正常加载，配置了全局异常处理类,输入项目名跳转首页正常
2017-03-22 IDEA启动后当java文件修改后默认不会自动编译，需要修改默认配置
           File-Settings-Compiler-Build Project automatically
           以及ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running
           参考地址:http://blog.csdn.net/wjc475869/article/details/52442484
           spring-boot热启动热部署配置成功(2个jar，yml文件配置jsp 资源文件不启动)
2017-03-23 引入了 spring security jar包，在WebSecurityConfig类中重写了方法，还需要创建用户角色权限表才能
           验证spring security安全框架
2017-03-26 准备使用gitHub托管项目，在gitHub for windows下载gitHub客户端安装包,在http://git-scm.com/downloads
           上下载git客户端安装包
2017-03-27 参考地址:http://www.cnblogs.com/java-maowei/p/5950930.html
           安装git客户端,在IDEA的设置里GitHub和Git上配置了git选项,把项目上传到git服务器上.
           切记,在把项目里的文件add前,先创建.gitignore文件,用于忽略项目中不要提交的文件,一旦文件提交后,
           就不能忽略了.在项目文件夹上右键-Git Bash Here,命令行输入 touch .gitignore,创建忽略git提交的文件
           gitHub网站用户名: aqsunkai@163.com,密码: aqsunkai16838678
           本机配置的提交到git显示的账号: sunkai,邮箱: aqsunkai@163.com,密码: 123456
2017-03-28 安装了gitHub for windows,发现并没有什么用,不是本来想的gitHub客户端,在这上面看不到
           https://github.com/网站上我自己托管的项目
2017-03-30 把spring-boot升级到最新的1.5.2版本后,发现@ConfigurationProperties注解中的locations属性无法使用了
           一时找不到加载yml配置文件的方法.直接把文件的内容方法写在application.yml文件中,spring启动后会直接加载.
           同时通过@PropertySource注解加载properties配置文件.在业务处理类中可以使用@Value注解和@AutoWired注解
           获得properties文件中key的值.
           IDEA控制台中文乱码的解决办法: (1) 安装目录/bin/idea.exe.vmoptions或idea64.exe.vmoptions文件中加上
           -Dfile.encoding=UTF-8 (2) Edit Configurations中的VM options加上-Dfile.encoding=UTF-8
           (3) File-Settings-File Encodings中IDE Encoding/Project Encoding/Default Encoding都改为UTF-8
2017-04-02 创建权限用到的5个表,用户表/角色表/用户角色表/权限表/角色权限表
           添加spring-boot整合shiro所需的四个jar包: shiro-spring,shiro-core,shiro-web,shiro-ehcache
           shiro权限框架所需的三个类,在启动时加载:ShiroConfiguration/MyShiroFilterFactoryBean/MyShiroRealm
           输入未配置过滤的url时都会跳转到login页面,当用户登录成功后,会把当前用户添加到缓存中,输入未配置过滤的url后
           成功跳转
           return一个String类型的字符串时,如果不加"/"时默认不走后台方法,可以在ViewControllerRegistry.properties
           让其直接跳转到jsp
           加了"/"的String类型的字符串默认走后台方法,也可以在ViewControllerRegistry.properties让其直接跳转到jsp
           return "/login" 相当于 return "redirect:login"
           如果需要用到shiro的权限认证,则需要在jsp页面使用shiro标签,当然了,使用前要引入shiro标签库
           <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
           shiro权限认证缓存已实现
2017-04-03 密码加密已实现,permissionController中对密码先加密,再验证
           把当前用户放入session中,在controller中获取已实现
2017-04-05 重新建个数据库,权限表代码改造,修改druid启动代码,监控正常了
           shiro使用redis缓存,不用每次都调用MyShiroRealm类中的doGetAuthorizationInfo方法获取权限信息
           前端弹出框美化js:sweetalert.min.js在showUser.jsp中使用
           spring-boot整合redis成功,项目中可以使用redis注解
           
