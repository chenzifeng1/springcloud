package com.chenzifeng.spring.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzifeng.spring.springsecurity.dao.MyUserDao;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import org.springframework.stereotype.Service;

/**
 * @author czf
 */

@Service
public class MyUserServiceImpl  extends ServiceImpl<MyUserDao,MyUser> implements MyUserService {


    @Override
    public MyUser findByUsername(String username) {
        QueryWrapper<MyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MyUser::getUsername,username);
        return getOne(queryWrapper);
    }


}
