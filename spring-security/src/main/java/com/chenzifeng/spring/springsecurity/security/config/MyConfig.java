package com.chenzifeng.spring.springsecurity.security.config;

import com.chenzifeng.spring.springsecurity.entity.RoleEnum;
import com.chenzifeng.spring.springsecurity.security.MyAuthenticationProvider;
import com.chenzifeng.spring.springsecurity.security.MyLogoutSuccessHandler;
import com.chenzifeng.spring.springsecurity.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;

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
    UserService userService;

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    /**
     *  拦截http请求，这里是比较早的拦截器
     *
     * @param http http请求
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()

                //使用默认的登录表单
                //设置自己的登陆界面
                .and()
                .logout()
                .logoutSuccessHandler(new MyLogoutSuccessHandler())

                /*
                    勾选remember me之后，登录会生成一个remember me的token(JWT TOKEN是来维持无状态的会话的) 原因：
                    1. 集群式的会话 如果用session共享来实现，太消耗资源
                    2. jwt 可以通过token令牌来进行用户的授权认证
                 */
                //remember me
                //.rememberMe()
                .and()
                // rememberMe与设置登录碰撞 冲突了。 rememberMe是通过设置一个rememberMe的token来让用户在多个子系统内实现一次登录，处处使用。
                // 而登录碰撞则是通过一个map来记录用户登录情况，当同一个用户在线次数超过阈值之后会使之前有效的session失效
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                //.rememberMe()
                .and()
                .and()
                .authorizeRequests();
                //Role 角色 ; Authority 权限
                //一般不这样配置权限，因为太不灵活了 可以在方法级别配置
                //.antMatchers("/admin/**").hasRole("admin")
                //.antMatchers("/user/**").hasRole("user");


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
                .antMatchers("/img/*")
                .antMatchers("/*.js")
                .antMatchers("/login");
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
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //设置 默认值
                .withDefaultSchema()
                .withUser("user")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles(RoleEnum.USER.getRoleName())
                .and()
                //
                .and()
                .userDetailsService(userService)
                .and()
                .authenticationProvider(myAuthenticationProvider);



    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
