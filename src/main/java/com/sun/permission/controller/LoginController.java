package com.sun.permission.controller;

import com.sun.permission.model.User;
import com.sun.permission.service.PermissionService;
import com.sun.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2017-4-2.
 */
@Controller
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public ModelAndView loginForm(){
        ModelAndView model = new ModelAndView();
        model.addObject("user", new User());
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(@Valid User user, boolean rememberMe,String gifCode,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "redirect:login";
        }
        String email = user.getEmail();
        if(StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPswd())){
            logger.info("用户名或密码为空! ");
            redirectAttributes.addFlashAttribute("message", "用户名或密码为空!");
            return "redirect:login";
        }
        //判断验证码
        if(StringUtils.isBlank(gifCode)){
            logger.info("验证码为空了！");
            redirectAttributes.addFlashAttribute("message", "验证码不能为空！");
            return "redirect:login";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute("gifCode");
        if(!gifCode.equalsIgnoreCase(code)){
            logger.info("验证码错误！");
            redirectAttributes.addFlashAttribute("message", "验证码错误！");
            return "redirect:login";
        }
        logger.info("进行登录次数验证");
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+email))){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数大于5次,账户已锁定");
            return "redirect:login";
        }
        opsForValue.increment(SHIRO_LOGIN_COUNT+email, 1);
        //计数大于3时，设置用户被锁定一小时
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+email))>=5){
            opsForValue.set(SHIRO_IS_LOCK+email, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+email, 1, TimeUnit.HOURS);
        }
        //对密码进行加密后验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), CommonUtils.encrypt(user.getPswd()),rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + email + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + email + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数大于5次,账户已锁定");
        }catch (DisabledAccountException sae){
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,帐号已经禁止登录");
            redirectAttributes.addFlashAttribute("message", "帐号已经禁止登录");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + email + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            logger.info("用户[" + email + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT+email, "0");
            //设置未锁定状态
            opsForValue.set(SHIRO_IS_LOCK+email,"UNLOCK");
            //把当前用户放入session
            User tUser = permissionService.findByUserEmail(email);
            session.setAttribute("currentUser",tUser);
            //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
            session.setTimeout(-1000l);
            return "/welcome";
        }else{
            token.clear();
            return "redirect:login";
        }
        // 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        // 登陆失败还到login页面
       // return "login";
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        logger.info("------没有权限-------");
        return "errorPermission";
    }

}
