package com.chenzifeng.serviceapi.serviceapi.service;

import com.chenzifeng.serviceapi.serviceapi.model.UserAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: UserAccountServiceApi-api
 * @Package: com.chenzifeng.serviceapi.serviceapi
 * @ClassName: UserAccountServiceApi
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/21 16:08
 * @Version: 1.0
 */
@RequestMapping("/userAccount")
public interface UserAccountServiceApi {

    @GetMapping("/getUserById")
    UserAccount getUserInfo(Integer userId);

    @PostMapping("/getUserList")
    List<UserAccount> getUserIdList();

    @GetMapping("/getFirstUser")
    UserAccount getFirstUser();

}
