package com.czf.program.programpractice.service;

import com.czf.program.programpractice.entity.User;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.service
 * @ClassName: UserService
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/20 14:14
 * @Version: 1.0
 */

public interface UserService {
    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查找用户
     * @param user
     * @return
     */
    User findById(User user);

    /**
     * 根据用户名查找
     * @param user
     * @return
     */
    User findByUserName(User user);


}
