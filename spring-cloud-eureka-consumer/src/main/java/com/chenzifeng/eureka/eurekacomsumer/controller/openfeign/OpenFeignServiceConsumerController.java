package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller
 * @ClassName: OpenFeignServiceConsumerController
 * @Author: czf
 * @Description: 使用OpenFeign来完成服务调用
 * @Date: 2020/12/21 9:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/accountConsumer")
public class OpenFeignServiceConsumerController {

    @Autowired(required = false)
    private ServiceApi serviceApi;

    @GetMapping("/getAccountById")
    public UserAccount getUserAccount(@RequestParam("userId")Integer id){
        return serviceApi.getUserInfo(id);
    }

    @GetMapping("/getAccountList")
    public List<UserAccount> getUserAccounts(){
        return serviceApi.getUserIdList();
    }


    @GetMapping("/getFirst")
    public UserAccount getFirstAccount(){
        return serviceApi.getFirstUser();
    }
}
