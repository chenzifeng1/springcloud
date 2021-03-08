package com.chenzifeng.spring.springsecurity.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.controller
 * @ClassName: TestController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/21 16:45
 * @Version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }
}
