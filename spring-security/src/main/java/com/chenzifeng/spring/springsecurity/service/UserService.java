package com.chenzifeng.spring.springsecurity.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: UserService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/20 16:07
 * @Version: 1.0
 */
@Service
public class UserService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return null;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
