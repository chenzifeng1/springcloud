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
public class UserService implements UserDetailsService {

    @Autowired
    MyUserService myUserService;


    public static final HashMap<String, UserDetails> userDataSources = new HashMap<>();

    static {
        User czf = new User("czf", "czf", Collections.singletonList(new SimpleGrantedAuthority("admin")));
        User lyq = new User("lyq", "lyq", Collections.singletonList(new SimpleGrantedAuthority("admin")));
        User cyb = new User("cyb", "cyb", Collections.singletonList(new SimpleGrantedAuthority("user")));
        User ddq = new User("ddq", "ddq", Collections.singletonList(new SimpleGrantedAuthority("user")));

        userDataSources.put("czf", czf);
        userDataSources.put("lyq", lyq);
        userDataSources.put("cyb", cyb);
        userDataSources.put("ddq", ddq);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new IllegalArgumentException("用户名为null");
        }
        MyUser myUser = myUserService.findByUsername(username);
        //从数据库查询：注 这里其实可以先从redis中查询，没有命中再去数据库查询（没有必要）
        if (myUser != null) {
            return myUser;
        } else {
            throw new UsernameNotFoundException("没有找到该用户");
        }
    }

}
