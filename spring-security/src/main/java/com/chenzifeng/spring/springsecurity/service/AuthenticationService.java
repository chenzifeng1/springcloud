package com.chenzifeng.spring.springsecurity.service;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: AuthenticationService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/26 10:08
 * @Version: 1.0
 */
public interface AuthenticationService {

    void doCheckLoginForm(String username, String password);
}
