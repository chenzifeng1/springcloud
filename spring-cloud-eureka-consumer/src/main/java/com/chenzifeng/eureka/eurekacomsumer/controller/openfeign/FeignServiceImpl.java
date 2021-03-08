package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: FeignServiceImpl
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/26 9:12
 * @Version: 1.0
 */
@Service
public class FeignServiceImpl implements FeignService {

    @Autowired
    private ServiceApi serviceApi;

    @Override
    @HystrixCommand(fallbackMethod = "ert")
    public String exceptionRequestTest() {
        return serviceApi.exceptionRequestTest();
    }

    @Override
    public UserAccount getUserInfo(Integer userId) {
        return serviceApi.getUserInfo(userId);
    }

    @Override
    public List<UserAccount> getUserIdList() {
        return serviceApi.getUserIdList();
    }

    @Override
    public UserAccount getFirstUser() {
        return serviceApi.getFirstUser();
    }

    @Override
    public String addUser(UserAccount user) {
        return serviceApi.addUser(user);
    }

    public String ert() {
        return "ert error";
    }

}
