package com.example.test;

import com.example.config.DiConfig;
import com.example.service.UseFunctionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Created by 陈子枫 on 2016/12/6.
 */

public class Example1 {
    public static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService sExample = annotationConfigApplicationContext.getBean(UseFunctionService.class);
        System.out.println(sExample.sayHello("di"));
        annotationConfigApplicationContext.close();

    }

}
