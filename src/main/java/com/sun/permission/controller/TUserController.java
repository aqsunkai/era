package com.sun.permission.controller;

import com.sun.permission.model.TUser;
import com.sun.permission.service.PermissionService;
import com.sun.util.CommonUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by sun on 2017-4-2.
 */
@Controller
@RequestMapping("tUser")
public class TUserController {
    private static final Logger logger = Logger.getLogger(TUserController.class);

    private PermissionService permissionService;

    @RequestMapping("/insert")
    //@RequiresPermissions("user:add")//权限管理;
    public ModelAndView insert(TUser user){
        ModelAndView model = new ModelAndView();
        logger.info("用户注册!");
        user.setCreatedTime(new Date());
        user.setUpdateTime(new Date());
        //user.setPassword(CommonUtils.encrypt(user.getPassword()));
        int num = permissionService.insertSelective(user);
        if(num == 1){
            model.setViewName("login");
        }else{
            model.addObject("message", "注册失败!");
            model.setViewName("permission/userInsert");
        }
        return model;
    }
}
