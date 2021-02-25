package com.chenzifeng.spring.springsecurity.security;


import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import com.chenzifeng.spring.springsecurity.service.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: UserService
 * @Author: czf
 * @Description: 这里是从获取用户信息的接口实现类，我们可以使用mybatis,hibernate,spring data jpa甚至redis等等获取用户信息
 * 此处为了方便，我们就直接造一些用户数据
 * @Date: 2021/2/20 16:07
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    MyUserService myUserServiceImpl = null;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("做用户验证:");
        if (username == null) {
            throw new IllegalArgumentException("用户名为null");
        }
        MyUser myUser = myUserServiceImpl.findByUsername(username);
        //从数据库查询：注 这里其实可以先从redis中查询，没有命中再去数据库查询（没有必要）
        if (myUser != null) {
            return myUser;
        } else {
            throw new UsernameNotFoundException("没有找到该用户");
        }
    }

    /**
     * 使用set方法进行依赖注入
     * @param myUserServiceImpl
     */
    public void setMyUserServiceImpl(MyUserService myUserServiceImpl) {
        this.myUserServiceImpl = myUserServiceImpl;
    }
}
