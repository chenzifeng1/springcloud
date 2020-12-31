package com.chenzifeng.eureka.provider.eurekaprovider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @ProjectName: eureka-provider
 * @Package: com.chenzifeng.eureka.provider.eurekaprovider.config
 * @ClassName: WebSecurityConfig
 * @Author: czf
 * @Description:
 * CSRF (Cross Site Request Forgery)攻击，跨站请求伪造。
 * 其原理是攻击者构造网站后台某个功能接口的请求地址，诱导用户去点击或者用特殊方法让该请求地址自动加载。
 * 用户在登录状态下这个请求被服务端接收后会被误以为是用户合法的操作
 * @Date: 2020/12/31 9:14
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    /**
     * 自定义配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 关闭csrf
        http.csrf().disable();
        // 所有访问都必须进行认证，认证之后才能正常进行访问
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        // 所有rest请求设为无状态，这也可以提高效率
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
