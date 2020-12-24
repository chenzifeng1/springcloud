package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: HystrixDowngradeService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/24 19:38
 * @Version: 1.0
 */

@Component
public class HystrixDowngradeService implements ServiceApi {
    @Override
    public UserAccount getUserInfo(Integer userId) {
        return UserAccount.getInstanceAccount();
    }

    @Override
    public List<UserAccount> getUserIdList() {
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(UserAccount.getInstanceAccount());
        return userAccounts;
    }

    @Override
    public UserAccount getFirstUser() {
        return UserAccount.getInstanceAccount();
    }

    @Override
    public String addUser(UserAccount user) {
        return "暂时无法添加信息";
    }
}
