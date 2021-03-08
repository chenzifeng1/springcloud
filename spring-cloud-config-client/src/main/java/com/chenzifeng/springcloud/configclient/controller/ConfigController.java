package com.chenzifeng.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: config-client
 * @Package: com.chenzifeng.springcloud.configclient.controller
 * @ClassName: ConfigController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/1/8 20:22
 * @Version: 1.0
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Value("${creator}")
    private String creator;


    @GetMapping("/t")
    public String getConfig() {
        return creator;
    }
}
