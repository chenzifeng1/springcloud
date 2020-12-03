package com.czf.program.programpractice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czf.program.programpractice.entity.User;
import com.czf.program.programpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.controller
 * @ClassName: UserController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/20 14:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findOne")
    public User findUser(JSONObject jsonObject) {
        User user = new User();
        return userService.findById(user);
    }


}
