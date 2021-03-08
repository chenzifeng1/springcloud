package com.spring.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "session test";
    }

    @PostMapping("/setSessionMapping")
    public String setSessionMapping(HttpServletRequest request,JSONObject json){
        String username = json.getString("username");
        String password = json.getString("password");
        System.out.println("username: "+username);
        System.out.println("password: "+password);
        request.getSession().setAttribute(username,password);
        return "success";
    }

    @GetMapping("/getSessionMapping")
    public String getSessionMapping(HttpServletRequest request,String parameter) {
        System.out.println("请求的parameter是："+parameter);
        return (String) request.getSession().getAttribute(parameter);
    }
}
