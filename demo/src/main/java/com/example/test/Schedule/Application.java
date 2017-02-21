package com.example.test.Schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by 陈子枫 on 2016/12/21.
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
