package com.czf.program.programpractice.service.impl;

import com.czf.program.programpractice.entity.User;
import com.czf.program.programpractice.mapper.UserMapper;
import com.czf.program.programpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.service.impl
 * @ClassName: UserServiceImpl
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/20 14:15
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(User user) {
        if(user!=null){
            return userMapper.findOneById(user.getId());
        }else {
            return null;
        }
    }

    @Override
    public User findByUserName(User user) {
        return null;
    }
}
