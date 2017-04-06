package com.sun.permission.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.configuration.YmlConfig;
import com.sun.permission.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 测试类
 * Created by sun on 2017-1-14.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private YmlConfig config;
    @Autowired
    private Environment env;
    @Value("${age}")
    String name;


    @RequestMapping("/")
    @ResponseBody
    String home(HttpServletRequest req) throws JsonProcessingException, UnsupportedEncodingException {
        logger.info("测试通过！！！");
        ObjectMapper objectMapper = new ObjectMapper();
        //测试加载yml文件
        System.out.println("simpleProp: " + config.getSimpleProp());
        System.out.println("arrayProps: " + objectMapper.writeValueAsString(config.getArrayProps()));
        System.out.println("listProp1: " + objectMapper.writeValueAsString(config.getListProp1()));
        System.out.println("listProp2: " + objectMapper.writeValueAsString(config.getListProp2()));
        System.out.println("mapProps: " + objectMapper.writeValueAsString(config.getMapProps()));

        //测试加载properties文件
        System.out.println(env.getProperty("name"));//孙凯
        System.out.println(env.getProperty("abc"));//null
        System.out.println(name);//26

        System.out.println(req.getSession().getAttribute("currentUser"));
        User tUser = (User) req.getSession().getAttribute("currentUser");
        System.out.println(tUser.getId()+","+tUser.getNickname());
        return "Hello World!112222222";
    }

    public static void main(String[] args) {
        System.out.println("孙凯");
    }
    @RequestMapping("/hello/{myName}")
    @ResponseBody
    String index(@PathVariable String myName) {
        logger.info("测试通过2！！！");
        return "Hello " + myName + "!!!";  //地址栏输入 localhost:8080/hello/{myName} 即可访问
    }

    @RequestMapping("/hello/{myName}/{age}")
    @ResponseBody
    String register(@PathVariable String myName, @PathVariable String age) {
        logger.info("测试通过3！！！");
        return myName + " is  " + age;
    }

}
