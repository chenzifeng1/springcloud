package com.chenzifeng.eureka.provider.eurekaprovider.controller;

import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.ProviderConsumeApi;
import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: eureka-provider
 * @Package: com.chenzifeng.eureka.provider.eurekaprovider.controller
 * @ClassName: UserAccountController
 * @Author: czf
 * @Description: 用户账号服务接口
 * @Date: 2020/12/21 10:22
 * @Version: 1.0
 */
@RestController
@RequestMapping("/userAccount")
@Slf4j
public class UserAccountController {
    @Autowired
    ProviderConsumeApi userAccountService;

    @PostMapping("/getUserById")
    public UserAccount getUserInfo(@RequestBody Integer userId){
        log.info("请求id："+userId);
        return userAccountService.getUserInfo(userId);
    }

    @PostMapping("/getUserList")
    public List<UserAccount> getUserIdList(){
        return userAccountService.getUserIdList();
    }

    @GetMapping("/getFirstUser")
    public UserAccount getFirstUser(){
        return userAccountService.getUserInfo(1);
    }

}
