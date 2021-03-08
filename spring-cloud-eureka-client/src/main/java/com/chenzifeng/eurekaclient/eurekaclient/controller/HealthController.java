package com.chenzifeng.eurekaclient.eurekaclient.controller;

import com.chenzifeng.eurekaclient.eurekaclient.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: eureka-client
 * @Package: com.chenzifeng.eurekaclient.eurekaclient.controller
 * @ClassName: HealthController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/14 11:14
 * @Version: 1.0
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    HealthStatusService hsrv;

    @GetMapping("/set")
    public String setHealStatus(@RequestParam("status") Boolean isHealth) {
        hsrv.setHealthStatus(isHealth);
        return hsrv.getHealthStatus();
    }
}
