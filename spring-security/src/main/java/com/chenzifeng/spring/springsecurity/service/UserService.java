package com.chenzifeng.spring.springsecurity.service;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: UserService
 * @Author: czf
 * @Description: 这里是从获取用户信息的接口实现类，我们可以使用mybatis,hibernate,spring data jpa甚至redis等等获取用户信息
 *              此处为了方便，我们就直接造一些用户数据
 * @Date: 2021/2/20 16:07
 * @Version: 1.0
 */
@Service
public class UserService implements UserDetailsService {


//    @Autowired
//    UserDao userDao

    public static final HashMap<String,UserDetails> userDataSources  = new HashMap<>();

    static {
        User czf = new User("czf","czf", Collections.singletonList(new SimpleGrantedAuthority("admin")));
        User lyq = new User("lyq","lyq", Collections.singletonList(new SimpleGrantedAuthority("admin")));
        User cyb = new User("cyb","cyb", Collections.singletonList(new SimpleGrantedAuthority("user")));
        User ddq = new User("ddq","ddq", Collections.singletonList(new SimpleGrantedAuthority("user")));

        userDataSources.put("czf",czf);
        userDataSources.put("lyq",lyq);
        userDataSources.put("cyb",cyb);
        userDataSources.put("ddq",ddq);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        for (Map.Entry<String, UserDetails> entry : userDataSources.entrySet()) {
            if(s.equals(entry.getKey())){
                return entry.getValue();
            }
        }
        throw new UsernameNotFoundException("没有找到该用户");
    }

}
