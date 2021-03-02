package com.chenzifeng.spring.springsecurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.dao
 * @ClassName: UserDao
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/24 16:15
 * @Version: 1.0
 */
@Mapper
public interface MyUserDAO extends BaseMapper<MyUser> {

    /**
     * 根据用户名获取user对象
     * @param name
     * @return
     */
    MyUser findUserByUserName(@Param("name") String name);

    /**
     *
     * @param myUser
     */
    void insertUser(@Param("myUser") MyUser myUser);
}
