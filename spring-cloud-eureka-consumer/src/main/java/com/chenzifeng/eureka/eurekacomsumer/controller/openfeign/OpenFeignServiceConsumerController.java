package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/accountConsumer")
public class OpenFeignServiceConsumerController {

    @Autowired
    private ServiceApi serviceApi;



    @Autowired
    private FeignService feignService;

    @GetMapping("/e")
    public String exceptionTest(){
        return feignService.exceptionRequestTest();
    }

    @GetMapping("/getAccountById")
    public UserAccount getUserAccount(@RequestParam("userId")Integer id){
        return feignService.getUserInfo(id);
    }

    @GetMapping("/getAccountList")
    public List<UserAccount> getUserAccounts(){
        log.info("请求获取用户列表");
        return feignService.getUserIdList();
    }

    @GetMapping("/getFirst")
    public UserAccount getFirstAccount(){
        return feignService.getFirstUser();
    }

    @GetMapping("/addUser")
    public String addUserAccount(Integer id,String name,Integer status,String email){
        return feignService.addUser(new UserAccount(id,name,status,email));
    }


}
