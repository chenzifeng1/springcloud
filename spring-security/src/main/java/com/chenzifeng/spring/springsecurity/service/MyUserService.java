package com.chenzifeng.spring.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.service
 * @ClassName: MyUserService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/24 16:23
 * @Version: 1.0
 */
@Service
public interface MyUserService extends IService<MyUser> {

    /**
     * 根据用户名获取user对象
     * @param username
     * @return
     */
    MyUser findByUsername(String username);
}
