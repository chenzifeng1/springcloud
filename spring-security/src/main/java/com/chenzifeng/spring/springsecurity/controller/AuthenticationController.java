package com.chenzifeng.spring.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import com.chenzifeng.spring.springsecurity.service.impl.MyUserServiceImpl;
import com.chenzifeng.spring.springsecurity.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthenticationController {

    @Autowired
    private MyUserService myUserService = null;

    @PostMapping("/form")
    public String form(String username, String password) {
        System.out.println(username + " : " + password);
        return "redirect:/index.html";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("在这里做重定向");
        return "redirect:/static/login.html";
    }

    @GetMapping("/logout")
    public void logout() {

    }

    /**
     * 注册接口
     *
     * @param myUser
     * @return
     */
    @PostMapping("/logOn")
    public JSONObject logOn(MyUser myUser) {
        myUserService.save(myUser);
        return JsonUtils.defaultSuccessResponse();
    }

    public void setMyUserService(MyUserServiceImpl myUserServiceImpl) {
        this.myUserService = myUserServiceImpl;
    }
}
