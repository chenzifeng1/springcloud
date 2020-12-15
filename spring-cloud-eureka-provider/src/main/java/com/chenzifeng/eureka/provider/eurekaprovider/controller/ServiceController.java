package com.chenzifeng.eureka.provider.eurekaprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: eureka-provider
 * @Package: com.chenzifeng.eureka.provider.eurekaprovider.controller
 * @ClassName: ServiceController
 * @Author: czf
 * @Description: 获取该节点的服务信息
 * @Date: 2020/12/15 19:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("/service-help")
public class ServiceController {

    public static List<String> serviceNames = new ArrayList<>();

    @GetMapping("/port")
    public String getServiceName(){
        serviceNames.add("hello-world");
        return serviceNames.toString();

    }
}
