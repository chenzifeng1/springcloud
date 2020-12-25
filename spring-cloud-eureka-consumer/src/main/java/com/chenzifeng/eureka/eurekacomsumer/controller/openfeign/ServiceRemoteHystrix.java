package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;
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
 * @Description: 服务的回调类, 这里是做降级处理的
 * @Date: 2020/12/23 20:00
 * @Version: 1.0
 */
@Slf4j
public class ServiceRemoteHystrix extends HystrixCommand<UserAccount> {


    protected ServiceRemoteHystrix(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected UserAccount run() throws Exception {
        log.info("降级处理");
        return UserAccount.getInstanceAccount();
    }

    /**
     * run方法抛出异常的时候返回备用结果
     */
    @Override
    protected UserAccount getFallback() {
        log.info("降级处理");
        return new UserAccount(-1,"error",-1,"error@gmail.com");
    }
}
