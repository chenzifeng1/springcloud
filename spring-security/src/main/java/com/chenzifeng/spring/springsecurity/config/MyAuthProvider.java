package com.chenzifeng.spring.springsecurity.config;

import com.chenzifeng.spring.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.config
 * @ClassName: MyAuthProvider
 * @Author: czf
 * @Description: 自定义用户权限校验
 * @Date: 2021/2/20 16:26
 * @Version: 1.0
 */
public class MyAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
