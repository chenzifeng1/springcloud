package com.chenzifeng.spring.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public JSONObject hi() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "hi");
        jsonObject.put("kind", "user");
        return jsonObject;
    }
}
