package com.chenzifeng.spring.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author czf
 */

public interface MyUserService extends IService<MyUser> {

    /**
     * 根据用户名获取user对象
     * @param username
     * @return
     */
    MyUser findByUsername(String username);


    /**
     *
     * @param myUser
     * @return
     */
    default Boolean checkUserInfo(MyUser myUser){
        return  !(myUser == null||myUser.getUsername()==null||myUser.getPassword()==null);
    }
}
