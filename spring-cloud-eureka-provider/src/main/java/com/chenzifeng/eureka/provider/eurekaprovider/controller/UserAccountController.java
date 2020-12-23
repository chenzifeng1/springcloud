package com.chenzifeng.eureka.provider.eurekaprovider.controller;

import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.ProviderConsumeApi;
import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Value("${server.port}")
    private String port;

    @GetMapping("/getUserById")
    public UserAccount getUserInfo(Integer userId) {
        log.info("请求id：" + userId);
        return userAccountService.getUserInfo(userId);
    }

    @PostMapping("/getUserList")
    public List<UserAccount> getUserIdList() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            log.error("线程中断异常",e);
        }
        log.info(String.format("请求用户列表,请求服务节点端口：%1$s",port));
        return userAccountService.getUserIdList();
    }

    @GetMapping("/getFirstUser")
    public UserAccount getFirstUser() {
        log.info(String.format("请求首位用户信息,请求服务节点端口：%1$s",port));
        return userAccountService.getUserInfo(1);
    }

    @PostMapping("/add")
    public String addUserAccount(@RequestBody UserAccount userAccount) {
        if (userAccount == null) {
            return "用户信息错误";
        }
        log.info(String.format("添加用户信息,请求服务节点端口：%1$s",port));
        return userAccountService.addUser(userAccount);
    }

}
