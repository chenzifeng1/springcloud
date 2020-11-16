package com.chenzifeng.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController {

    @Value("${sever.port=8081}")
    private String port;


    @GetMapping("/config")
    public void getConfig(){
        Map<String,Object> result = new HashMap<>();
        result.put("port",port);
       // result.put("profile",env.)
    }
}
