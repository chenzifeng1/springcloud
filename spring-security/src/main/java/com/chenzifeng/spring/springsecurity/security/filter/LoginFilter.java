package com.chenzifeng.spring.springsecurity.security.filter;

import com.chenzifeng.spring.springsecurity.utils.StaticValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.security.filter
 * @ClassName: LoginFilter
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/3/5 15:33
 * @Version: 1.0
 */

public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(StaticValue.HTTP_REQUEST_METHOD_POST)) {
            //如果登录请求不是post请求
            throw new AuthenticationServiceException("登录请求必须为 POST 方式");
        }
        //验证码 可以使用图形验证码
        String verify_code = (String) request.getSession().getAttribute("verify_code");


        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
            } finally {
                String code = loginData.get("code");
                checkCode(response, code, verify_code);
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            System.out.println("username:" + username);
            System.out.println("password:" + password);
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            // 根据username password 构造UsernamePasswordAuthenticationToken 对象
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            // 设置details对象 一般是WebAuthenticationDetails 包含remoteAddress和sessionId
            setDetails(request, authRequest);
            //调用AuthenticationManager#authenticate 一般是ProviderManager实现的
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            checkCode(response, request.getParameter("code"), verify_code);
            return super.attemptAuthentication(request, response);
        }
    }


    public void checkCode(HttpServletResponse resp, String code, String verify_code) {
        if (code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
            //验证码不正确
            throw new AuthenticationServiceException("验证码不正确");
        }
    }
}
