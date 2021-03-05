package com.chenzifeng.spring.springsecurity.security.config;

import com.alibaba.fastjson.JSONObject;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.security.MyAuthenticationProvider;
import com.chenzifeng.spring.springsecurity.security.MyLogoutSuccessHandler;
import com.chenzifeng.spring.springsecurity.security.filter.LoginFilter;
import com.chenzifeng.spring.springsecurity.utils.JsonUtils;
import com.chenzifeng.spring.springsecurity.utils.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.config
 * @ClassName: MyConfig
 * @Author: czf
 * @Description: 这里是进行用户访问配置的地方，我们可以重写configure方法 使用AuthenticationManagerBuilder对象进行配置
 * 也可以写一个UserDetailsService的bean来进行配置
 * 关于configure有两个重载的方法，一个参数是WebSecurity，另一个是
 * @Date: 2021/2/20 16:43
 * @Version: 1.0
 */
@Configuration
@Service
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userServiceImpl;

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    /**
     * 拦截http请求，这里是比较早的拦截器
     *
     * @param http http请求
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                //自定义 登录界面
                .formLogin()
                .loginPage("/login.html")
                .successForwardUrl("/index.html")
                .failureForwardUrl("/error.html")
                //处理登录请求的接口 这里是表单提交后的处理的接口地址
                .loginProcessingUrl("/form")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .permitAll()
                .and()
                //.rememberMe()
                // rememberMe与设置登录碰撞 冲突了。 rememberMe是通过设置一个rememberMe的token来让用户在多个子系统内实现一次登录，处处使用。
                // 而登录碰撞则是通过一个map来记录用户登录情况，当同一个用户在线次数超过阈值之后会使之前有效的session失效
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .and()
                .and()
                // 这里对放开static的页面和身份认证接口的访问 避免无限循环授权认证的重定向
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/login","/logout").permitAll()
                .antMatchers(HttpMethod.POST,"/form","/logOn").permitAll()
                .antMatchers("/login.html", "/logon.html")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;
                /*
                    勾选remember me之后，登录会生成一个remember me的token(JWT TOKEN是来维持无状态的会话的) 原因：
                    1. 集群式的会话 如果用session共享来实现，太消耗资源
                    2. jwt 可以通过token令牌来进行用户的授权认证
                 */
            http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * 这个是拦截，可以在这里配置忽略静态资源的认证
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/img/*");
    }

    /**
     * 这里配置授权信息，
     *
     * @param auth 授权信息
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //我们可以重写configure来定义在内存中存储哪些用户信息 小项目的用户名信息可以存在内存，项目大起来必定要进行数据库的校验
        MyUser myUser = new MyUser(
                "czf",
                bCryptPasswordEncoder().encode("123"),
                Collections.singletonList(new SimpleGrantedAuthority("admin")));
        myUser.setEmail("704734862@qq.com");
        myUser.setPhone("17815987462");
        auth.jdbcAuthentication()
                .dataSource(dataSource);
//                .withUser(myUser)  withUser 创建一个User
                //设置 默认值
//                .and()
//                .userDetailsService(userServiceImpl)
//                .and()
//                .authenticationProvider(myAuthenticationProvider);

    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                MyUser user = (MyUser) authentication.getPrincipal();
                user.setPassword(null);
                RespBean ok = RespBean.ok("登录成功!", user);
                String s = new ObjectMapper().writeValueAsString(ok);
                out.write(s);
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                RespBean respBean = RespBean.error(exception.getMessage());
                if (exception instanceof LockedException) {
                    respBean.setMsg("账户被锁定，请联系管理员!");
                } else if (exception instanceof CredentialsExpiredException) {
                    respBean.setMsg("密码过期，请联系管理员!");
                } else if (exception instanceof AccountExpiredException) {
                    respBean.setMsg("账户过期，请联系管理员!");
                } else if (exception instanceof DisabledException) {
                    respBean.setMsg("账户被禁用，请联系管理员!");
                } else if (exception instanceof BadCredentialsException) {
                    respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        return loginFilter;
    }

    public void setUserServiceImpl(UserDetailsService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
}
