package com.chenzifeng.spring.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzifeng.spring.springsecurity.dao.MyUserDAO;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author czf
 */

@Service
public class MyUserServiceImpl  extends ServiceImpl<MyUserDAO,MyUser> implements MyUserService {

    @Autowired(required = false)
    MyUserDAO myUserDAO;

    @Override
    public MyUser findByUsername(String username) {
        MyUser user = myUserDAO.findUserByUserName(username);
        return user;
    }

    @Override
    public void saveUser(MyUser user) {
        String psw = user.getPassword();
        bCryptPasswordEncoder().encode(psw);
    }

    @Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
