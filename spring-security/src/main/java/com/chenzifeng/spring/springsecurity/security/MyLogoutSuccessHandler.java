package com.chenzifeng.spring.springsecurity.security;

import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: MyLogoutSuccessHandler
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/22 17:00
 * @Version: 1.0
 */
public class MyLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //在这里我们可以释放掉用户的一些资源 或者记录一下用户登出的时间信息

        System.out.println("我走了，时间是");
    }
}
