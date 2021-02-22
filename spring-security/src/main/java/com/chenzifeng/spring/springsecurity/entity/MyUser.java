package com.chenzifeng.spring.springsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.entity
 * @ClassName: MyUser
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/21 13:49
 * @Version: 1.0
 */

public class MyUser extends User {


    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
