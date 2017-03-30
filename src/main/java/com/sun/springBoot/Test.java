package com.sun.springBoot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.system.YmlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试类
 * Created by sun on 2017-1-14.
 */
@Controller
@RequestMapping("/test")
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    @Autowired
    private YmlConfig myProps;
    @Autowired
    private Environment env;
    @Value("${zhoufangyu}")
    String name;


    @RequestMapping("/")
    @ResponseBody
    String home() throws JsonProcessingException {
        logger.info("测试通过！！！");
        ObjectMapper objectMapper = new ObjectMapper();
        //测试加载yml文件
        System.out.println("simpleProp: " + myProps.getSimpleProp());
        System.out.println("arrayProps: " + objectMapper.writeValueAsString(myProps.getArrayProps()));
        System.out.println("listProp1: " + objectMapper.writeValueAsString(myProps.getListProp1()));
        System.out.println("listProp2: " + objectMapper.writeValueAsString(myProps.getListProp2()));
        System.out.println("mapProps: " + objectMapper.writeValueAsString(myProps.getMapProps()));

        //测试加载properties文件
        System.out.println(env.getProperty("sunkai"));
        System.out.println(env.getProperty("abc"));
        System.out.println(name);

        return "Hello World!";
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
