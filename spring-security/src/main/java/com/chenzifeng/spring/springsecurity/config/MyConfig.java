package com.chenzifeng.spring.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.util.Collections;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.config
 * @ClassName: MyConfig
 * @Author: czf
 * @Description: 这里是进行用户访问配置的地方，我们可以重写configure方法 使用AuthenticationManagerBuilder对象进行配置
 *               也可以写一个UserDetailsService的bean来进行配置
 *               关于configure有两个重载的方法，一个参数是WebSecurity，另一个是
 * @Date: 2021/2/20 16:43
 * @Version: 1.0
 */
@Configuration
@Service
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    /**
     * 这个是第一步拦截，可以在这里配置忽略静态资源的认证
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("./img/**")
                .antMatchers("./**.js")
                .antMatchers("./login");
    }

    /**
     * 这里配置授权信息
     *
     * @param auth
     * @throws Exception
     */



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //我们可以重写configure来定义在内存中存储哪些用户信息 小项目的用户名信息可以存在内存，项目打起来必定要进行数据库的校验
        JdbcUserDetailsManager  manager = auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
//        manager.setAuthenticationManager(new );

    }



    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        //UserDetails是个接口，实现该接口的类可以进行验证。User是Security自带的实现UserDetails的类
        InMemoryUserDetailsManager manager =
                new InMemoryUserDetailsManager();

        User user = new User(
                "czf",
                bCryptPasswordEncoder().encode("123"),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("admin")));
        manager.createUser(user);

        //根据用户名加载用户对象
        User loadUSer = (User) manager.loadUserByUsername("cyb");
        //修改用户的密码 但是这里有个问题，InMemoryUserDetailsManage是管理了多个User的吧，那么如何来实现修改指定用户的用户名及密码呢
        manager.changePassword("123","321");

        return manager;


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
