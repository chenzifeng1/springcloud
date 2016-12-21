package com.example.test;

import com.example.config.ScopeConfig;
import com.example.service.DemoSingletonService;
import com.example.service.DemoprototypeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 陈子枫 on 2016/12/20.
 */
public class Example2 {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(ScopeConfig.class);

        DemoSingletonService service0 = context.getBean(DemoSingletonService.class);
        DemoSingletonService service1 = context.getBean(DemoSingletonService.class);

        DemoprototypeService p0 = context.getBean(DemoprototypeService.class);
        DemoprototypeService p1 = context.getBean(DemoprototypeService.class);

        System.out.println("service0与servicec1是否相等:" + service0.equals(service1));
        System.out.println(service0);
        System.out.println(service1);
        System.out.println("***************************************************************************");
        System.out.println("p0与p1是否相等:" + p0.equals(p1));
        System.out.println(p0);
        System.out.println(p1);
        System.out.println("***************************************************************************");
        context.close();
    }
}
