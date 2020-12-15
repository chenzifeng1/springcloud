package com.chenzifeng.eureka.eurekacomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaComsumerApplication.class, args);
    }


    @Bean
    public RestTemplate getRestTemplateInstance(){
        return new RestTemplate();
    }

}
