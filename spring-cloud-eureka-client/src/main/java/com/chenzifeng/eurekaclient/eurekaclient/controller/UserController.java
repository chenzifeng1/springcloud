package com.chenzifeng.eurekaclient.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: eureka-client
 * @Package: com.chenzifeng.eurekaclient.eurekaclient.controller
 * @ClassName: UserController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/7 19:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getHi")
    public String getHi() {
        return "hi,czf";
    }


}
