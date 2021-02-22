package com.chenzifeng.spring.springsecurity.service;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import org.springframework.security.authentication.AuthenticationProvider;
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
public class MyAuthenticationProvider implements AuthenticationProvider {

    /**
     * 这里写校验 身份认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        if (authentication.isAuthenticated()){
            //如果用户已经被授权
            User user = (User) authentication.getDetails();
            System.out.println("Username:"+user.getUsername());
        }else {
            //没有被授权，返回登录页面
            System.out.println("登录过期，请重新登录");
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
