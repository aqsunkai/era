package com.sun.permission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.permission.model.User;
import com.sun.permission.service.PermissionService;
import com.sun.permission.service.UserService;
import com.sun.entity.Bootgrid;
import com.sun.util.ObjectMapperTool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sun on 2017-4-2.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @RequestMapping("/insert")
    //@RequiresPermissions("user:add")//权限管理;
    public ModelAndView insert(User user){
        ModelAndView model = new ModelAndView();
        logger.info("用户注册!");
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

    /**
     * 根据ID查询user表
     *
     * @param request
     * @return
     */
    @RequestMapping("/showUser")
    public ModelAndView showUser(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        //验证log4j
        logger.info("哈哈哈");
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.selectByPrimaryKey(userId);
        model.addObject("user", user);
        model.setViewName("eagle/showUser");
        return model;
    }

    @RequestMapping("/showUser1")
    public ModelAndView showUser1(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        //验证log4j
        logger.info("哈哈哈");
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.selectByPrimaryKey1(userId);
        model.addObject("user", user);
        model.setViewName("eagle/showUser");
        return model;
    }
//    @RequestMapping("/showUser")
//    public Map<String, Object> toIndex(HttpServletRequest request){
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	int userId = Integer.parseInt(request.getParameter("id"));
//    	User user = this.userService.getUserById(userId);
//    	map.put("v", user);
//    	return map;
//    }

    @RequestMapping("/insertSelective")
    public void insertSelective(User user, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            userService.insertSelective(user);
            response.getWriter().print(1);
        } catch (Exception e) {
            // TODO: handle exception
            response.getWriter().print(0);
        }

    }

    @RequestMapping("/deleteByPrimaryKey")
    public void deleteByPrimaryKey(Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            userService.deleteByPrimaryKey(id);
            response.getWriter().print(1);
        } catch (Exception e) {
            // TODO: handle exception
            response.getWriter().print(0);
        }
    }

    @RequestMapping("/getUserById")
    public void getUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        int userId;
        if (null == req.getParameter("id")) {
            userId = 100;
        } else {
            userId = Integer.parseInt(req.getParameter("id"));
        }
        User user = this.userService.selectByPrimaryKey(userId);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        resp.getWriter().print(result);
    }
    @RequestMapping("/excep")
    public void excep() throws Exception {
        throw new Exception("测试异常处理类");
    }

    @RequestMapping("/edit/{userid}")
    public String getUserList(@PathVariable int userid){
        logger.info("------进入用户信息修改-------");
        return "user_edit";
    }

    @RequestMapping("/queryList")
    public void queryList(Bootgrid bootgrid,HttpServletRequest req,HttpServletResponse resp){
        bootgrid.fieldSort();
        List<User> list = userService.queryList(bootgrid);
        int count = userService.countAll(bootgrid);
        bootgrid.setRows(list);
        bootgrid.setTotal(count);
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            resp.getWriter().write(ObjectMapperTool.toJson(bootgrid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
