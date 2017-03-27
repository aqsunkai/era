package com.sun.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sun on 2017-3-23.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String hello(HttpServletRequest request){
        return "login";
    }
}

