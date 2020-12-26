package com.chenzifeng.eureka.eurekacomsumer;

import com.chenzifeng.eureka.eurekacomsumer.Interceptor.LoggingClientHttpRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
// 启用熔断器
@EnableCircuitBreaker
//// 启用Hystrix
@EnableHystrixDashboard
/**
 * @author czf
 */
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplateInstance(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return template;
    }


}
