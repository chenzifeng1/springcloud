package com.chenzifeng.eureka.eurekacomsumer;

import com.chenzifeng.eureka.eurekacomsumer.Interceptor.LoggingClientHttpRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaComsumerApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplateInstance(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return template;
    }

//    @Bean
//    public IRule myRule(){
//        return new
//
//    }

}
