package com.chenzifeng.spring.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.controller
 * @ClassName: AuthenticationController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/25 11:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @PostMapping("/form")
    public String form(String username,String password){

        return "success";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("在这里做重定向");
        return "redirect:/static/login.html";
    }
    @GetMapping("/logout")
    public void logout(){

    }
}
