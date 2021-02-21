package com.chenzifeng.spring.springsecurity.config;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.config
 * @ClassName: MyAuthManageBuilder
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/21 10:44
 * @Version: 1.0
 */
public class MyAuthManageBuilder extends AuthenticationManagerBuilder {

    public MyAuthManageBuilder(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);

    }
}
