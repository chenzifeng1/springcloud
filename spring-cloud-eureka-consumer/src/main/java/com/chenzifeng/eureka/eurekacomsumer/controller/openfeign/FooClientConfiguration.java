package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: FooClientConfiguration
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/25 19:49
 * @Version: 1.0
 */
//@Configuration
public class FooClientConfiguration {

    @Bean
    public FeignClientConfigurer feignClientConfigurer() {
        return new FeignClientConfigurer() {

            /**
             * 可以通过重写 inheritParentConfiguration()方法让其返回false，将FeignClient配置为不从父context中继承bean
             * 这一点有什么用暂时位置， 官方文档
             * https://docs.spring.io/spring-cloud-openfeign/docs/2.2.5.RELEASE/reference/html/#spring-cloud-feign-hystrix
             * @return
             */
            @Override
            public boolean inheritParentConfiguration(){
                return false;
            }
        };
    }
}
