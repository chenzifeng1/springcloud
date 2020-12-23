package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: ServiceRemoteHystrix
 * @Author: czf
 * @Description: 服务的回调类,这里是做降级处理的
 * @Date: 2020/12/23 20:00
 * @Version: 1.0
 */
@Component
public class ServiceRemoteHystrix implements ServiceApi {
    @Override
    public UserAccount getUserInfo(@RequestParam("userId")Integer userId) {
        return UserAccount.getInstanceAccount() ;
    }

    @Override
    public List<UserAccount> getUserIdList() {
        List<UserAccount> list = new ArrayList<>();
        list.add(UserAccount.getInstanceAccount());
        return list;
    }

    @Override
    public UserAccount getFirstUser() {
        return UserAccount.getInstanceAccount() ;
    }

    @Override
    public String addUser(@RequestBody UserAccount user) {
        return "暂时无法添加用户";
    }
}
