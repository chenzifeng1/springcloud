package com.chenzifeng.spring.springsecurity.security;

import com.chenzifeng.spring.springsecurity.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: MyLogoutSuccessHandler
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/22 17:00
 * @Version: 1.0
 */
@Slf4j
public class MyLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //在这里我们可以释放掉用户的一些资源 或者记录一下用户登出的时间信息

        log.info("我走了，时间是:" + TimeUtils.getNowTimeString());
    }
}
