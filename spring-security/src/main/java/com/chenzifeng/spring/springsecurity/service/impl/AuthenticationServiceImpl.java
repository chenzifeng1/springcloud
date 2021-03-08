package com.chenzifeng.spring.springsecurity.service.impl;

import com.chenzifeng.spring.springsecurity.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service.impl
 * @ClassName: AuthenticationServiceImpl
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/26 10:09
 * @Version: 1.0
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public void doCheckLoginForm(String username, String password) {
        log.info("需要进行身份认证的用户为：" + username);
    }
}
