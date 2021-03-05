package com.chenzifeng.spring.springsecurity.security;

import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @Autowired
    MyUserService myUserService = null;

    /**
     * 这里写校验 身份认证 这个方法在UsernamePasswordAuthenticationFilter中的attemptAuthentication方法中调用
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        MyUser user = myUserService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("没有对应的用户信息");
        }

        System.out.println("password：" + password);
        System.out.println("username：" + username);
        return authentication;
    }

    /**
     * 是否支持自定义的authen
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void setMyUserService(MyUserService myUserServiceImpl) {
        this.myUserService = myUserServiceImpl;
    }
}
