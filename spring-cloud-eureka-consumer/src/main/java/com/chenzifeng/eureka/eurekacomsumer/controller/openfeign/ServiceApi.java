package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: ServiceApi
 * @Author: czf
 * @Description: name是服务名，另外就是openFeign里面内置了ribbon实现，同时引入ribbon会报错
 * @Date: 2020/12/21 10:20
 * @Version: 1.0
 */
@FeignClient(name = "provider")
public interface ServiceApi {

    @GetMapping("/userAccount/getUserById")
    UserAccount getUserInfo(Integer userId);

    @PostMapping("/userAccount/getUserList")
    List<UserAccount> getUserIdList();

    @GetMapping("/userAccount/getFirstUser")
    UserAccount getFirstUser();

}
