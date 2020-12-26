package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: FeignService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/26 9:10
 * @Version: 1.0
 */
public interface FeignService {

    UserAccount getUserInfo(@RequestParam("userId") Integer userId);

    List<UserAccount> getUserIdList();

    UserAccount getFirstUser();

    String addUser(@RequestBody UserAccount user);

    String exceptionRequestTest();
}
