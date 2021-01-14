package com.chenzifeng.eureka.eurekacomsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller
 * @ClassName: ConfigTestController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/1/7 18:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/config")
public class ConfigTestController {

    @Value("${myconfig.version_no}")
    private String version;

    @Value("${myconfig.label}")
    private String label;

    @GetMapping("/test")
    public String test(){
        String str = String.format("从配置中心的获取的配置信息,label:%s,version:%s",label,version);
        return str;
    }


}
