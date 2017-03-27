package com.sun.springBoot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.springBoot.model.User;
import com.sun.springBoot.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户controller
 *
 * @author sun.kai
 *         2017年01月15日
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //@Value("#{application['server.address']}")
    private String localAdress;

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
        //验证枚举
        //System.out.println(thirdType.getCode());

        //验证全局配置文件
        /*System.out.println(properties.getProperty("csdn.url"));*/

        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.selectByPrimaryKey(userId);
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
}
