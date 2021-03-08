package com.chenzifeng.eureka.provider.eurekaprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

    public static ConcurrentMap<String, Integer> consumerRequestTime = new ConcurrentHashMap<>();


    @Value("${server.port}")
    private String port;


    @GetMapping("/port")
    public String getServiceName(@RequestParam("consumer") String consumer) {
        if (!consumerRequestTime.containsKey(consumer)) {
            consumerRequestTime.put(consumer, 1);
        } else {
            consumerRequestTime.put(consumer, consumerRequestTime.get(consumer) + 1);
        }
        return "访问者：" + consumer + " 访问次数：" + consumerRequestTime.get(consumer) + " port:" + port;
    }
}
