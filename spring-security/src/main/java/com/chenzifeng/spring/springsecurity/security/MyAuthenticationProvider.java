package com.chenzifeng.spring.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.config
 * @ClassName: MyAuthenticationProvider
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/21 11:12
 * @Version: 1.0
 */
@Service
@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {

    /**
     * 这里写校验 身份认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        System.out.println("username："+username);
        System.out.println("password："+password);
        if (authentication.isAuthenticated()){
            //如果用户已经被授权

        }else {
            //没有被授权，返回登录页面
            log.error("用户名或密码错误");
            //throw new CredentialsExpiredException()
        }
        return authentication;
    }

    /**
     * 是否支持自定义的authen
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
